package com.springboot.server;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

import com.springboot.entity.Client;

import lombok.extern.slf4j.Slf4j;

@ServerEndpoint(value = "/socketServer/{userName}")
@Component
@Slf4j
public class WebSocketServer {

	// 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
	private static int onlineCount = 0;

	// concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
	private static CopyOnWriteArraySet<Client> webSocketSet = new CopyOnWriteArraySet<Client>();

	// 与某个客户端的连接会话，需要通过它来给客户端发送数据
	private Session session;

	/**
	 *
	 * 服务端的userName,因为用的是set，每个客户端的username必须不一样，否则会被覆盖。
	 * 要想完成ui界面聊天的功能，服务端也需要作为客户端来接收后台推送用户发送的信息
	 */
	private final static String SYS_USERNAME = "Louis";

	/**
	 * 
	 * @描述: 连接建立成功调用的方法
	 * @方法名: onOpen
	 * @param session
	 * @param id
	 * @param name
	 * @throws Exception
	 * @返回类型 void
	 * @创建人 T-liul4
	 * @创建时间 2019年7月18日下午5:18:31
	 */
	@OnOpen
	public void onOpen(Session session, @PathParam("userName") String userName) throws Exception {
		this.session = session;
		System.out.println(this.session.getId());
		addOnlineCount();
		webSocketSet.add(new Client(userName, session));
		log.info("有新连接加入！ userName={}", userName);
	}

	/**
	 * 
	 * @描述: 连接关闭调用的方法
	 * @方法名: onClose
	 * @返回类型 void
	 * @创建人 T-liul4
	 * @创建时间 2019年7月18日下午5:18:23
	 */
	@OnClose
	public void onClose() {
		webSocketSet.forEach(client -> {
			if (client.getSession().getId().equals(session.getId())) {
				log.info("客户端:【{}】断开连接", client.getUserName());
				webSocketSet.remove(client);
			}
		});
	}

	/**
	 * 
	 * @描述: 收到客户端消息后调用的方法
	 * @方法名: onMessage
	 * @param message
	 * @param session
	 * @返回类型 void
	 * @创建人 T-liul4
	 * @创建时间 2019年7月18日下午5:18:15
	 */
	@OnMessage
	public void onMessage(String message) {

		Client client = webSocketSet.stream().filter(cli -> cli.getSession() == session).collect(Collectors.toList())
				.get(0);

		if (client.getUserName().equals(SYS_USERNAME)) {
			// 服务端发送信息全部客户端接收
			sendAll(client.getUserName() + "<--" + message);
		} else {
			// 客户端发送消息服务端接收
			sendMessage(client.getUserName() + "<--" + message, SYS_USERNAME);
		}
		log.info("客户端:【{}】发送信息:{}", client.getUserName(), message);
	}

	/**
	 *
	 * 信息发送的方法，通过客户端的userName 拿到其对应的session，调用信息推送的方法
	 * 
	 * @param message
	 * @param userName
	 */
	public synchronized static void sendMessage(String message, String userName) {
		webSocketSet.forEach(client -> {
			// 仅限服务端显示
			if (userName.equals(client.getUserName())) {
				try {
					client.getSession().getBasicRemote().sendText(message);
					log.info("服务端:【{}】推送给客户端 :{}", client.getUserName(), message);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 *
	 * 信息群发，我们要排除服务端自己不接收到推送信息 所以我们在发送的时候将服务端排除掉
	 * 
	 * @param message
	 */
	public synchronized static void sendAll(String message) {
		// 群发，不能发送给服务端自己
		webSocketSet.stream().filter(cli -> cli.getUserName() != SYS_USERNAME).forEach(client -> {
			try {
				client.getSession().getBasicRemote().sendText(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

		log.info("服务端推送给所有客户端 :【{}】", message);
	}

	/**
	 * 
	 * @描述: 发生错误时调用
	 * @方法名: onError
	 * @param session
	 * @param error
	 * @返回类型 void
	 * @创建人 T-liul4
	 * @创建时间 2019年7月18日下午5:19:20
	 */
	@OnError
	public void onError(Session session, Throwable error) {
		log.error("发送错误：{}", error);
		error.printStackTrace();
	}

	/**
	 * 
	 * @描述: 获取当前在线数量
	 * @方法名: getOnlineNum
	 * @return
	 * @返回类型 int
	 * @创建人 T-liul4
	 * @创建时间 2019年7月19日下午2:48:57
	 */
	public synchronized static int getOnlineNum() {
		return webSocketSet.stream().filter(client -> !client.getUserName().equals(SYS_USERNAME))
				.collect(Collectors.toList()).size();
	}

	/**
	 * 
	 * @描述: 获取当前在线用户
	 * @方法名: getOnlineUsers
	 * @return
	 * @返回类型 List<String>
	 * @创建人 T-liul4
	 * @创建时间 2019年7月19日下午2:49:10
	 */
	public synchronized static List<String> getOnlineUsers() {
		List<String> onlineUsers = webSocketSet.stream().filter(client -> !client.getUserName().equals(SYS_USERNAME))
				.map(client -> client.getUserName()).collect(Collectors.toList());
		return onlineUsers;
	}

	public static synchronized int getOnlineCount() {
		return onlineCount;
	}

	public static synchronized void addOnlineCount() {
		WebSocketServer.onlineCount++;
	}

	public static synchronized void subOnlineCount() {
		WebSocketServer.onlineCount--;
	}

}
