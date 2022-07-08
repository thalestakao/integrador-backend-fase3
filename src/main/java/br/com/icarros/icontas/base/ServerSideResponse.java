package br.com.icarros.icontas.base;

import java.util.Map;

import org.springframework.http.HttpStatus;


public class ServerSideResponse<T> {

	private T dado;
	
	private Map<String, Object> extra;
	
	private String mensagem;
	
	private long timestamp;
	
	private int statusCode;
	
	
	private ServerSideResponse(T dado,  String mensagem, HttpStatus status) {
		this.dado = dado;
		this.mensagem = mensagem;
		this.timestamp = System.currentTimeMillis();
		this.statusCode = status.value();
	}
	
}
