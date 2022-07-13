package br.com.icarros.icontas.exception;

public class SaldoInsuficienteException extends RegraDeNegocioException{
	
	public SaldoInsuficienteException(String mensagem) {
        super(mensagem);
    }

}
