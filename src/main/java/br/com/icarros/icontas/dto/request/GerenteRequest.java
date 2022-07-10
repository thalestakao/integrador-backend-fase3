package br.com.icarros.icontas.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.br.CPF;

import lombok.Getter;
@Getter
public class GerenteRequest {
	@CPF
    private String cpf;
	
	@NotBlank
    private String senha;

	@NotBlank
    private String nome;

    @Email
    private String email;

}
