package com.springboot.util.i18n;

import java.util.Locale;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * 
 * @项目名称：chapter08-02-i18n @包名： com.springboot.common
 * @类名称：LocaleMessageSourceService
 * @创建人：Louis
 * @创建时间：2019年7月10日下午2:01:53
 */
@Component
public class LocaleMessage {

	@Resource
	private MessageSource messageSource;

	/**
	 * 
	 * @描述: 对应地区的语言消息字符串
	 * @方法名: getMessage
	 * @param code
	 * @return
	 * @返回类型 String
	 * @创建人 T-liul4
	 * @创建时间 2019年7月10日下午2:05:29
	 */
	public String getMessage(String code) {
		return getMessage(code, new Object[] {});
	}

	public String getMessage(String code, String defaultMessage) {
		return this.getMessage(code, null, defaultMessage);
	}

	public String getMessage(String code, String defaultMessage, Locale locale) {
		return this.getMessage(code, null, defaultMessage, locale);
	}

	public String getMessage(String code, Object[] args) {
		return this.getMessage(code, args, "");
	}

	public String getMessage(String code, Object[] args, Locale locale) {
		return this.getMessage(code, args, "", locale);
	}

	public String getMessage(String code, Object[] args, String defaultMessage) {
		Locale locale = LocaleContextHolder.getLocale();
		return this.getMessage(code, args, defaultMessage, locale);
	}

	public String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
		return messageSource.getMessage(code, args, defaultMessage, locale);
	}

}
