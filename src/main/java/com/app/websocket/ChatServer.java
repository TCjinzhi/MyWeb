package com.app.websocket;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;
import java.util.logging.Logger;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import net.sf.json.JSONObject;
/**
 * 聊天服务器类
 * @author shiyanlou
 *
 */
@ServerEndpoint("/websocket")
public class ChatServer {
  private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");	// 日期格式化
  
  private Logger logger = Logger.getLogger(this.getClass().getName());

  static Map<String,Session> sessionMap = new Hashtable<String,Session>();
  
  @OnOpen
  public void onOpen(Session session) {
  	sessionMap.put(session.getId(), session);
  }
  /**
   * 接受客户端的消息，并把消息发送给所有连接的会话
   * @param message 客户端发来的消息
   * @param session 客户端的会话
   */
  @OnMessage
  public void getMessage(String message, Session session) {
    // 把客户端的消息解析为JSON对象
    JSONObject jsonObject = JSONObject.fromObject(message);
    // 在消息中添加发送日期
    jsonObject.put("date", DATE_FORMAT.format(new Date()));
    // 把消息发送给所有连接的会话
    for (Session openSession : session.getOpenSessions()) {
      // 添加本条消息是否为当前会话本身发的标志
      jsonObject.put("isSelf", openSession.equals(session));
      // 发送JSON格式的消息
      openSession.getAsyncRemote().sendText(jsonObject.toString());
    }
  }
  
  
  @OnClose
  public void onClose(Session session, CloseReason closeReason) {
  	sessionMap.remove(session.getId());
      logger.info(String.format("Session %s closed because of %s", session.getId(), closeReason));
  }
  
  @OnError
  public void error(Session session, java.lang.Throwable throwable){
  	sessionMap.remove(session.getId());
      System.err.println("session "+session.getId()+" error:"+throwable);
  }
}
