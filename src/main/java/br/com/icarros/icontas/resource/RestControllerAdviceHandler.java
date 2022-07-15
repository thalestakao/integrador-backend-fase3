package br.com.icarros.icontas.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import br.com.icarros.icontas.base.ServerSideResponse;
import br.com.icarros.icontas.exception.CampoNaoExistente;
import br.com.icarros.icontas.exception.CorrentistaJaAtivoException;
import br.com.icarros.icontas.exception.CorrentistaNaoEcontradoException;
import br.com.icarros.icontas.exception.GerenteInexistenteException;
import br.com.icarros.icontas.exception.GerenteJaAtivo;
import br.com.icarros.icontas.exception.RegraDeNegocioException;
import br.com.icarros.icontas.exception.SaldoInsuficienteException;
import br.com.icarros.icontas.exception.UsuarioJaCriado;

@RestControllerAdvice
public class RestControllerAdviceHandler {
	
	@ExceptionHandler(CorrentistaJaAtivoException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ServerSideResponse<?> correntistaJaExiste(CorrentistaJaAtivoException e, WebRequest request) {
		return ServerSideResponse.builder().mensagem("Este correntista já existe").statusCode(HttpStatus.BAD_REQUEST.value()).build();
	}
	@ExceptionHandler(CampoNaoExistente.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ServerSideResponse<?> campoNaoExiste(CampoNaoExistente e, WebRequest request) {
		return ServerSideResponse.builder().mensagem(e.getMessage()).statusCode(HttpStatus.BAD_REQUEST.value()).build();
	}
	
	@ExceptionHandler(GerenteJaAtivo.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ServerSideResponse<?> gerenteJaExiste(GerenteJaAtivo e, WebRequest request) {
		return ServerSideResponse.builder().mensagem("Este gerente já existe").statusCode(HttpStatus.BAD_REQUEST.value()).build();
	}

	@ExceptionHandler(GerenteInexistenteException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ServerSideResponse<?> gerenteInexiste(GerenteInexistenteException e, WebRequest request) {
		return ServerSideResponse.builder().mensagem("Gerente informado não encontrado.").statusCode(HttpStatus.BAD_REQUEST.value()).build();
	}
	@ExceptionHandler(UsuarioJaCriado.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ServerSideResponse<?> gerenteInexiste(UsuarioJaCriado e, WebRequest request) {
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

		return ServerSideResponse.builder().mensagem("Validação: parâmetros de entrada estão incorretos")
				.statusCode(HttpStatus.BAD_REQUEST.value()).extra(errosDeValidacao).build();
	}

	@ExceptionHandler(RegraDeNegocioException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ServerSideResponse<?> regraDeNegocioException(RegraDeNegocioException e, WebRequest request) {
		return ServerSideResponse.builder().mensagem(e.getMessage()).statusCode(HttpStatus.BAD_REQUEST.value()).build();
	}


	@ExceptionHandler(CorrentistaNaoEcontradoException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ServerSideResponse<?> correntistaNaoEcontradoException(CorrentistaNaoEcontradoException e, WebRequest request) {
		return ServerSideResponse.builder().mensagem(e.getMessage()).statusCode(HttpStatus.NOT_FOUND.value()).build();
	}
	
	@ExceptionHandler(SaldoInsuficienteException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ServerSideResponse<?> saldoInsuficienteException(SaldoInsuficienteException e, WebRequest request) {
		return ServerSideResponse.builder().mensagem("Você não possui saldo suficiente").statusCode(HttpStatus.BAD_REQUEST.value()).build();
	}
}
