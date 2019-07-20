package com.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * 
 * @类描述： 开启websocket的支持
 * 
 * @项目名称：chapter08-03-websocket @包名： com.springboot.config
 * @类名称：WebSocketConfig
 * @创建人：Louis
 * @创建时间：2019年7月19日下午1:53:42
 */
@Configuration
public class WebSocketConfig {

	/**
	 * 
	 * @描述: 注入ServerEndpointExporter
	 * @方法名: serverEndpointExporter
	 * @return
	 * @返回类型 ServerEndpointExporter
	 * @创建人 T-liul4
	 * @创建时间 2019年7月18日下午5:09:12
	 */
	@Bean
	public ServerEndpointExporter serverEndpointExporter() {
		return new ServerEndpointExporter();
	}

}
