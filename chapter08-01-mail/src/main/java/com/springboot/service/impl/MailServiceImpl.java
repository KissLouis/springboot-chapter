package com.springboot.service.impl;

import java.io.File;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.springboot.entity.MailSenderModal;
import com.springboot.service.MailService;

/**
 * 
 * 邮件发送接口实现
 * 
 * @author Louis
 *
 */
@Service
public class MailServiceImpl implements MailService {

	/**
	 * 注入MailSender
	 */
	@Autowired
	private JavaMailSender mailSender;

	/**
	 * 读取配置文件中的发送用户信息
	 */
	@Value("${spring.mail.username}")
	private String fromEmail;

	/**
	 * 文本类型邮件发送业务实现
	 */
	@Override
	public MailSenderModal sendSimple(@NotNull String toEmail, @NotNull String subject, @NotNull String body) {
		MailSenderModal mailSenderModal = new MailSenderModal();
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom(fromEmail);

			/**
			 * 收件人邮件地址
			 */
			message.setTo(toEmail);
			message.setSubject(subject);
			message.setText(body);
			mailSender.send(message);
		} catch (MailException e) {
			e.printStackTrace();
			mailSenderModal.setCode(-1);
			mailSenderModal.setMsg(e.getMessage());
		}
		return mailSenderModal;
	}

	/**
	 * Mime类型邮件发送业务实现
	 */
	@Override
	public MailSenderModal sendMime(@NotNull String toEmail, @NotNull String subject, @NotNull String mailBody,
			Map<String, String> photos, boolean isAttachment) {
		MailSenderModal mailSenderModal = new MailSenderModal();
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(fromEmail);
			helper.setTo(toEmail);
			helper.setSubject(subject);
			helper.setText(mailBody, true);

			/**
			 * 判断附件是否为空
			 */
			if (!StringUtils.isEmpty(photos)) {
				/**
				 * 多附件处理
				 */
				photos.entrySet().forEach(entry -> {
					try {
						FileSystemResource file = new FileSystemResource(new File(entry.getValue()));

						if (isAttachment) {
							helper.addAttachment(entry.getKey(), file);
						} else {
							helper.addInline(entry.getKey(), file);
						}
					} catch (MessagingException e) {
						e.printStackTrace();
						mailSenderModal.setCode(-2);
						mailSenderModal.setMsg(e.getMessage());
					}

				});

			}

			mailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
			mailSenderModal.setCode(-2);
			mailSenderModal.setMsg(e.getMessage());
		} catch (MailException e) {
			e.printStackTrace();
			mailSenderModal.setCode(-1);
			mailSenderModal.setMsg(e.getMessage());
		}
		return mailSenderModal;
	}

}
