package com.springboot;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import com.springboot.entity.MailSenderModal;
import com.springboot.service.MailService;

@SpringBootApplication
public class Application {
	
	@Resource
	private Environment env;
	
	@Autowired
	MailService mailService;
	
	@PostConstruct
	public void testMailSender() throws IOException {
		String toEmail = "xxxx@xxx.com";
		
		/**
		 * 发送文本信息
		 */
		{
			MailSenderModal mailSenderModal = mailService.sendSimple(toEmail, "文本发送测试标题", "文本发送测试内容");
			System.out.println(mailSenderModal.toString());
		}
		
		/**
		 * 发送Html信息
		 */
		{
			String mailBody = new StringBuffer()
					.append("<h1>我是标题</h1>")
                    .append("<p style=\"color:#F00\">我是红的</p>").toString();
			
			MailSenderModal mailSenderModal = mailService.sendMime(toEmail, "Html发送测试标题", mailBody, null, false);
			System.out.println(mailSenderModal.toString());
		}
		
		/**
		 * 发送多个附件信息
		 */
		{
			String mailBody = new StringBuffer()
					.append("<h1>我是标题</h1>")
                    .append("<p style=\"color:#000\">我有附件</p>").toString();
			
			/**
			 * 封装附件名称及附件地址
			 */
			Map<String, String> photos = new HashMap<String, String>();
			photos.put("photo1.jpg", "src/main/resources/static/photos/1.jpg");
			photos.put("photo2.jpg", "src/main/resources/static/photos/2.jpg");
			
			MailSenderModal mailSenderModal = mailService.sendMime(toEmail, "附件发送测试标题", mailBody, photos, true);
			System.out.println(mailSenderModal.toString());
		}
		
		/**
		 * 发送多个静态文件信息
		 */
		{
			String mailBody = new StringBuffer()
					.append("<h1>我是标题</h1>")
                    .append("<html><body>带资源的邮件内容 图片:<img src='cid:photo1.jpg' /><img src='cid:photo2.jpg' /></body></html>").toString();
			
			/**
			 * 封装附件名称及附件地址
			 */
			Map<String, String> photos = new HashMap<String, String>();
			photos.put("photo1.jpg", "src/main/resources/static/photos/1.jpg");
			photos.put("photo2.jpg", "src/main/resources/static/photos/2.jpg");
			
			MailSenderModal mailSenderModal = mailService.sendMime(toEmail, "资源发送测试标题", mailBody, photos, false);
			System.out.println(mailSenderModal.toString());
		}
		
	}
	

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
