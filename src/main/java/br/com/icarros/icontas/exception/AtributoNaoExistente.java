package br.com.icarros.icontas.exception;

public class AtributoNaoExistente extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public AtributoNaoExistente(String message) {
		super(message);
	}
}
