package br.com.icarros.icontas.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.icarros.icontas.entity.enums.TipoOperacao;

@Entity
@Table(name="tb_transacao")
public class Transacao extends AbstractEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private BigDecimal valor;
	
	@Column(nullable = false)
	private BigDecimal saldoAnterior;
	
	@Column(nullable = false)
	private BigDecimal saldoAtual;
	
	@Column(nullable = false)
	private TipoOperacao tipoOperacao;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "correntista_id")
	private Correntista correntista;
	
	public Transacao() {
	}
	
	public Transacao(BigDecimal valor, BigDecimal saldoAnterior, BigDecimal saldoAtual, TipoOperacao tipoOperacao) {
		super();
		this.valor = valor;
		this.saldoAnterior = saldoAnterior;
		this.saldoAtual = saldoAtual;
		this.tipoOperacao = tipoOperacao;
	}
	
	public Transacao(Long id, Integer version, BigDecimal valor, BigDecimal saldoAnterior, BigDecimal saldoAtual, TipoOperacao tipoOperacao) {
		super(id, version);
		this.valor = valor;
		this.saldoAnterior = saldoAnterior;
		this.saldoAtual = saldoAtual;
		this.tipoOperacao = tipoOperacao;
	}
	
	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getSaldoAnterior() {
		return saldoAnterior;
	}

	public void setSaldoAnterior(BigDecimal saldoAnterior) {
		this.saldoAnterior = saldoAnterior;
	}

	public BigDecimal getSaldoAtual() {
		return saldoAtual;
	}

	public void setSaldoAtual(BigDecimal saldoAtual) {
		this.saldoAtual = saldoAtual;
	}

	public TipoOperacao getTipoOperacao() {
		return tipoOperacao;
	}

	public void setTipoOperacao(TipoOperacao tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}

	
}
