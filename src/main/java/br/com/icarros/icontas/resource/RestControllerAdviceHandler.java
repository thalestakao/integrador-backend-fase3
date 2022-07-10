package br.com.icarros.icontas.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import br.com.icarros.icontas.base.ServerSideResponse;
import br.com.icarros.icontas.exception.CorrentistaJaAtivoException;
import br.com.icarros.icontas.exception.GerenteInexistenteException;

@RestControllerAdvice
public class RestControllerAdviceHandler {

	@ExceptionHandler(CorrentistaJaAtivoException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ServerSideResponse<?> correntistaJaExiste(CorrentistaJaAtivoException e, WebRequest request) {
		return ServerSideResponse.builder().mensagem("Este correntista já existe").statusCode(HttpStatus.BAD_REQUEST.value()).build();
	}

	@ExceptionHandler(GerenteInexistenteException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ServerSideResponse<?> gerenteInexiste(GerenteInexistenteException e, WebRequest request) {
		return ServerSideResponse.builder().mensagem("Gerente informado não encontrado.").statusCode(HttpStatus.BAD_REQUEST.value()).build();
	}
	
	@ExceptionHandler(BadCredentialsException.class)
	@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
	public ServerSideResponse<?> dadosLoginInvalidos(BadCredentialsException e, WebRequest request) {
		return ServerSideResponse.builder().mensagem(e.getMessage()).statusCode(HttpStatus.BAD_REQUEST.value()).build();
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ServerSideResponse<?> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		List<String> details = new ArrayList<>();
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			details.add(error.getDefaultMessage());
		}

		Map<String, Object> errosDeValidacao = new HashMap<String, Object>();

		errosDeValidacao.put("detalhes", details);

		return ServerSideResponse.builder().mensagem("Valição: parâmetros de entrada estão incorretos")
				.statusCode(HttpStatus.BAD_REQUEST.value()).extra(errosDeValidacao).build();
	}

}
