package br.com.icarros.icontas.exception;

public class SaldoInsuficienteException extends RegraDeNegocioException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SaldoInsuficienteException(String mensagem) {
        super(mensagem);
    }

}
