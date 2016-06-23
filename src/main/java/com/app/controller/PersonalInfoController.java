package com.app.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.app.commonTool.FilePathHandler;
import com.app.pojo.PersonalInfo;
import com.app.service.IPersonalInfoService;

@Controller
@RequestMapping("/personalInfo")
public class PersonalInfoController {
	
	@Resource
	private IPersonalInfoService personalInfoService;
	
	@RequestMapping("/insertOrUpdate")
	public String insertAtFirst(HttpServletRequest request,Model model){
		PersonalInfo personalInfo = new PersonalInfo();
		if(request.getSession().getAttribute("username") != null)
			personalInfo.setUsername((String)request.getSession().getAttribute("username"));
		if(request.getParameter("realname") != null)
			personalInfo.setRealname((String)request.getParameter("realname"));
		if(request.getParameter("email") != null)
			personalInfo.setEmail((String)request.getParameter("email"));
		if(request.getParameter("telephone") != null)
			personalInfo.setTelephone((String)request.getParameter("telephone"));
		if(request.getParameter("qq") != null)
			personalInfo.setQq((String)request.getParameter("qq"));
		if(request.getParameter("twitter") != null)
			personalInfo.setTwitter((String)request.getParameter("twitter"));
		if(request.getParameter("intro") != null)
			personalInfo.setIntro((String)request.getParameter("intro"));
		int result = 0;
		try{
			if(personalInfoService.checkUsername(personalInfo.getUsername()) == 0)
				result =  personalInfoService.insertAtFirst(personalInfo);
			else 
				result = personalInfoService.updatePersonalInfo(personalInfo);
		} catch(Exception e){
			System.out.println(e);
		}
		if(result > 0){
			model.addAttribute("message","保存成功");
		}else {
			model.addAttribute("message","发生错误，请重新提交");
		}
		model.addAttribute(personalInfo);
			
		return "personalCenter";
	}
	
	@RequestMapping("/uploadImage")
	public String uploadImage(HttpServletRequest request,Model model){
		System.out.println("upload start");
		
		CommonsMultipartResolver commonsMultipartResolver = 
				new CommonsMultipartResolver(request.getSession().getServletContext());
		
		if(commonsMultipartResolver.isMultipart(request)){
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
			
			Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
			
			while(iterator.hasNext()){
				MultipartFile file = multipartHttpServletRequest.getFile(iterator.next());
				
				if(file != null){
					String filename = file.getOriginalFilename();
					System.out.println(filename);
					if(filename != null){
						// 注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：
						// c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
						// 处理获取到的上传文件的文件名的路径部分，只保留文件名部分
						filename = filename.substring(filename.lastIndexOf("\\") + 1);
						// 得到上传文件的扩展名
//						String fileExtName = filename.substring(filename.lastIndexOf(".") + 1);
						// filename加上uuid唯一标识
						filename = FilePathHandler.makeFileName(filename);
						// 定义上传路径
						String path = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");
						// 根据文件名，hash分散存储，避免同一文件夹下文件过多 
						path = FilePathHandler.makePath(filename, path);
						
						String image = path+"\\"+filename;
						System.out.println(image);
						
						String hashImage = String.valueOf(image.hashCode());
						
						String username = (String) request.getSession().getAttribute("username");
						
						//服务器创建文件
						File localFile = new File(path, filename);
						try {
							//上传到服务器文件
							file.transferTo(localFile);
							// 保存数据库
							int result = personalInfoService.uploadImage(hashImage, image, username);
							if(result > 0){
								model.addAttribute("uploadResult","保存成功");
							}else {
								model.addAttribute("uploadResult","发生错误，请重新提交");
							}
						} catch (IllegalStateException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
						
					}
				}
			}
					
		}
		return "personalCenter";
	}
	

}
