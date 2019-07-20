package com.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springboot.server.WebSocketServer;

@Controller
@SuppressWarnings("all")
public class WebSocketController {

	@Autowired
	private WebSocketServer webSocketServer;

	@RequestMapping(value = "/index")
	public String idnex() {
		return "index";
	}

	@RequestMapping(value = "/admin")
	public String admin(Model model) {
		int num = webSocketServer.getOnlineNum();
		List<String> list = webSocketServer.getOnlineUsers();

		model.addAttribute("num", num);
		model.addAttribute("users", list);
		return "admin";
	}

	@RequestMapping("sendAll")
	@ResponseBody
	public String sendAll(String msg) {
		webSocketServer.sendAll(msg);
		return "success";
	}
}
