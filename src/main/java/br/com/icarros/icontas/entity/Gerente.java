package br.com.icarros.icontas.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_gerente")
public class Gerente extends AbstractEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String email;

	@Column(nullable = false, unique = true)
	private String cpf;
	
	@Column(nullable = false)
	private String senha;
	
	@OneToMany(mappedBy = "gerente")
	private List<Correntista> correntistas;
	
	public Gerente() {
		// Construtor padrão
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Correntista> getCorrentistas() {
		return correntistas;
	}

	public void setCorrentistas(List<Correntista> correntistas) {
		this.correntistas = correntistas;
	}

}
