package com.springboot.entity;

import java.io.Serializable;

import javax.websocket.Session;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client implements Serializable {
	
	private static final long serialVersionUID = 8957107006902627635L;
	
	private String userName;

	private Session session;

}
