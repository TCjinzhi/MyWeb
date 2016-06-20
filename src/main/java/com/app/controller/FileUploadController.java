package com.app.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Controller
@RequestMapping("/upload")
public class FileUploadController {

	@RequestMapping("/fileupload")
	public String MultiUpload(HttpServletRequest request, Model model) {
		// 创建一个通用的多部分解析器
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 判断 request 是否有文件上传,即多部分请求
		if (multipartResolver.isMultipart(request)) {
			// 转换成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 取得request中的所有文件名
			Iterator<String> iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				// 记录上传过程起始时的时间，用来计算上传时间
				int pre = (int) System.currentTimeMillis();
				// 取得上传文件
				MultipartFile file = multiRequest.getFile(iter.next());
				if (file != null) {
					// 取得当前上传文件的文件名称
					String myFileName = file.getOriginalFilename();
					// 如果名称不为“”,说明该文件存在，否则说明该文件不存在
					if (myFileName.trim() != "") {
						System.out.println(myFileName);

						// 注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：
						// c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
						// 处理获取到的上传文件的文件名的路径部分，只保留文件名部分
						myFileName = myFileName.substring(myFileName.lastIndexOf("\\") + 1);
						// 得到上传文件的扩展名
						String fileExtName = myFileName.substring(myFileName.lastIndexOf(".") + 1);
						// 如果需要限制上传的文件类型，那么可以通过文件的扩展名来判断上传的文件类型是否合法
						System.out.println("上传的文件的扩展名是：" + fileExtName);
						// 得到文件保存的名称
						String saveFilename = makeFileName(myFileName);

						// 定义上传路径
						String path = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");

						// 得到文件的保存目录
						String realSavePath = makePath(saveFilename, path);

						File localFile = new File(realSavePath, saveFilename);
						try {
							file.transferTo(localFile);
						} catch (IllegalStateException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
				// 记录上传该文件后的时间
				int finaltime = (int) System.currentTimeMillis();
				System.out.println(finaltime - pre);
			}

		}

		request.setAttribute("message", "上传成功");
		return "fileupload";
	}

	@RequestMapping("/fileList")
	public String fileList(HttpServletRequest request, Model model) {

		// 获取上传文件的目录
		String uploadFilePath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");
		// 存储要下载的文件名
		Map<String, String> fileNameMap = new HashMap<String, String>();
		// 递归遍历filepath目录下的所有文件和目录，将文件的文件名存储到map集合中
		listfile(new File(uploadFilePath), fileNameMap);// File既可以代表一个文件也可以代表一个目录
		// 将Map集合发送到fileList.jsp页面进行显示
		request.setAttribute("fileNameMap", fileNameMap);

		return "fileList";
	}

	@RequestMapping("/download")
	public void download(HttpServletRequest request,HttpServletResponse response, Model model) {
		try {
			// 得到要下载的文件名
			String fileName = request.getParameter("filename"); // 23239283-92489-阿凡达.avi
			//如果有乱码，加上下行
			//fileName = new String(fileName.getBytes("iso8859-1"), "UTF-8");
			// 上传的文件都是保存在/WEB-INF/upload目录下的子目录当中
			String fileSaveRootPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");
			// 通过文件名找出文件的所在目录
			String path = findFileSavePathByFileName(fileName, fileSaveRootPath);
			// 得到要下载的文件
			File file = new File(path + "\\" + fileName);
			// 如果文件不存在
			if (!file.exists()) {
				request.setAttribute("message", "您要下载的资源已被删除！！");
			}
			// 处理文件名
			String realname = fileName.substring(fileName.indexOf("_") + 1);
			// 设置响应头，控制浏览器下载该文件
			response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(realname, "UTF-8"));
			// 读取要下载的文件，保存到文件输入流
			FileInputStream in;
			in = new FileInputStream(path + "\\" + fileName);
			// 创建输出流
			OutputStream out = response.getOutputStream();
			// 创建缓冲区
			byte buffer[] = new byte[1024];
			int len = 0;
			// 循环将输入流中的内容读取到缓冲区当中
			while ((len = in.read(buffer)) > 0) {
				// 输出缓冲区的内容到浏览器，实现文件下载
				out.write(buffer, 0, len);
			}
			// 关闭文件输入流
			in.close();
			// 关闭输出流
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @Method: makeFileName
	 * @Description: 生成上传文件的文件名，文件名以：uuid+"_"+文件的原始名称
	 * @param filename
	 *            文件的原始名称
	 * @return uuid+"_"+文件的原始名称
	 */
	private String makeFileName(String filename) {
		// 为防止文件覆盖的现象发生，要为上传文件产生一个唯一的文件名
		return UUID.randomUUID().toString() + "_" + filename;
	}

	/**
	 * 为防止一个目录下面出现太多文件，要使用hash算法打散存储
	 *
	 * @Method: makePath
	 * @Description:
	 *
	 * @param filename
	 *            文件名，要根据文件名生成存储目录
	 * @param savePath
	 *            文件存储路径
	 * @return 新的存储目录
	 */
	private String makePath(String filename, String savePath) {
		// 得到文件名的hashCode的值，得到的就是filename这个字符串对象在内存中的地址
		int hashcode = filename.hashCode();
		int dir1 = hashcode & 0xf; // 0--15
		int dir2 = (hashcode & 0xf0) >> 4; // 0-15
		// 构造新的保存目录
		String dir = savePath + "\\" + dir1 + "\\" + dir2; // upload\2\3
		// upload\3\5
		// File既可以代表文件也可以代表目录
		File file = new File(dir);
		// 如果目录不存在
		if (!file.exists()) {
			// 创建目录
			file.mkdirs();
		}
		return dir;
	}

	/**
	 * @Method: listfile
	 * @Description: 递归遍历指定目录下的所有文件
	 * @param file
	 *            即代表一个文件，也代表一个文件目录
	 * @param map
	 *            存储文件名的Map集合
	 */
	public void listfile(File file, Map<String, String> map) {
		// 如果file代表的不是一个文件，而是一个目录
		if (!file.isFile()) {
			// 列出该目录下的所有文件和目录
			File files[] = file.listFiles();
			// 遍历files[]数组
			for (File f : files) {
				// 递归
				listfile(f, map);
			}
		} else {
			/**
			 * 处理文件名，上传后的文件是以uuid_文件名的形式去重新命名的，去除文件名的uuid_部分
			 * file.getName().indexOf("_")检索字符串中第一次出现"_"字符的位置，如果文件名类似于：
			 * 9349249849-88343-8344_阿_凡_达.avi
			 * 那么file.getName().substring(file.getName().indexOf("_")+1)
			 * 处理之后就可以得到阿_凡_达.avi部分
			 */
			String realName = file.getName().substring(file.getName().indexOf("_") + 1);
			// file.getName()得到的是文件的原始名称，这个名称是唯一的，因此可以作为key，realName是处理过后的名称，有可能会重复
			map.put(file.getName(), realName);
		}
	}
	
	/**
	    * @Method: findFileSavePathByFileName
	    * @Description: 通过文件名和存储上传文件根目录找出要下载的文件的所在路径
	    * @param filename 要下载的文件名
	    * @param saveRootPath 上传文件保存的根目录，也就是/WEB-INF/upload目录
	    * @return 要下载的文件的存储目录
	    */ 
	    public String findFileSavePathByFileName(String filename,String saveRootPath){
	        int hashcode = filename.hashCode();
	        int dir1 = hashcode&0xf;  //0--15
	        int dir2 = (hashcode&0xf0)>>4;  //0-15
	        String dir = saveRootPath + "\\" + dir1 + "\\" + dir2;  //upload\2\3  upload\3\5
	        File file = new File(dir);
	        if(!file.exists()){
	            //创建目录
	            file.mkdirs();
	        }
	        return dir;
	    }

}
