package com.app.websocket;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.alibaba.fastjson.JSON;

import net.sf.json.JSONObject;
//注意此访问地址格式如:"ws://"+ window.location.host+"/${pageContext.request.contextPath}/wechat"是ws开头的,而不是以http:开头的.
@ServerEndpoint(value = "/wechat")
public class Socket {

	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");	// 日期格式化
	
    private Logger logger = Logger.getLogger(this.getClass().getName());

    static Map<String,Session> sessionMap = new Hashtable<String,Session>();
    
    @OnOpen
    public void onOpen(Session session) {
    	sessionMap.put(session.getId(), session);
    }

//    @OnMessage
//    public void onMessage(String unscrambledWord, Session session) {
//    	broadcastAll("message",unscrambledWord);
//    }
    
    @OnMessage
    public void getMessage(String message, Session session) {
    	
    	Set<Map.Entry<String,Session>> set = sessionMap.entrySet();
        // 把客户端的消息解析为JSON对象
        JSONObject jsonObject = JSONObject.fromObject(message);
        // 在消息中添加发送日期
        jsonObject.put("date", DATE_FORMAT.format(new Date()));
//        // 把消息发送给所有连接的会话
//        for (Session openSession : session.getOpenSessions()) {
//          // 添加本条消息是否为当前会话本身发的标志
//          jsonObject.put("isSelf", openSession.equals(session));
//          // 发送JSON格式的消息
//          openSession.getAsyncRemote().sendText(jsonObject.toString());
//        }
        for(Map.Entry<String,Session> i: set){
            try {
            	jsonObject.put("isSelf", i.getValue().equals(session));
            	i.getValue().getBasicRemote().sendText(JSON.toJSONString(jsonObject));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
      }
//    /**
//     * 广播给所有人
//     * @param message
//     */
//    public static void broadcastAll(String type,String message){
//        Set<Map.Entry<String,Session>> set = sessionMap.entrySet();
//        for(Map.Entry<String,Session> i: set){
//            try {
//            	i.getValue().getBasicRemote().sendText("{type:'"+type+"',text:'"+message+"'}");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }

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