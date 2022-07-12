package br.com.icarros.icontas.exception;

public class CorrentistaNaoEncontradoException extends RegraDeNegocioException{
    public CorrentistaNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
