package br.com.icarros.icontas.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_usuario")
public class Usuario extends AbstractEntity{
	
	@Column(nullable = false, unique = true)
	private String username;
	
	@Column(nullable = false)
	private String senha;
	
	@OneToOne(mappedBy = "usuario")
	private Correntista correntista;

	public Usuario() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Correntista getCorrentista() {
		return correntista;
	}

	public void setCorrentista(Correntista correntista) {
		this.correntista = correntista;
	}
	
}
