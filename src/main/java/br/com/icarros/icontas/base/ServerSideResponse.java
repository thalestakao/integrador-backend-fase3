package br.com.icarros.icontas.base;

import java.util.Map;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ServerSideResponse<T> {

	private T dado;
	
	private Map<String, Object> extra;
	
	private String mensagem;
	
	@Builder.Default
	private long timestamp = System.currentTimeMillis();
	
	private int statusCode;
	
	
}
