package com.pinyougou.shop.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import entity.Result;
import util.FastDFSClient;

@RestController
public class UploadController {

	@Value("${FILE_SERVER_URL}")
	private String file_server_url;
	
	
	@RequestMapping("/upload")
	public Result upload(MultipartFile file){
		
			//1、取文件的扩展名
		String filename = file.getOriginalFilename();
		String extName = filename.substring(filename.lastIndexOf(".")+1);

		try {
			//2、创建一个 FastDFS 的客户端
			FastDFSClient fastDFSClient = new FastDFSClient("classpath:config/fdfs_client.conf");
			//3、执行上传处理
			String fileId = fastDFSClient.uploadFile(file.getBytes(), extName);
			//4、拼接返回的 url 和 ip 地址，拼装成完整的 url
			String url = file_server_url+fileId;
			return new Result(true, url);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "图片上传失败");
		}


		
	}
	
}
