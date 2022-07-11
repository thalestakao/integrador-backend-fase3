package br.com.icarros.icontas.exception;

public class CorrentistaNaoEcontradoException extends RegraDeNegocioException{
    public CorrentistaNaoEcontradoException(String mensagem) {
        super(mensagem);
    }
}
