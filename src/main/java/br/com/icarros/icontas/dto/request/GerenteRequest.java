package br.com.icarros.icontas.dto.request;


import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.br.CPF;

import br.com.icarros.icontas.dto.request.CorrentistaRequest.GerenteCorrentistaRequest;
import br.com.icarros.icontas.entity.enums.UF;
import lombok.Data;

@Data
public class GerenteRequest {
	@CPF(message = "cpf inválido")
    private String cpf;
	
	@NotBlank(message = "nome é um campo obrigatório")
    private String nome;

    @Email(message = "email inválido.")
    private String email;

}
