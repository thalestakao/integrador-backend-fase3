package br.com.icarros.icontas.dto.request;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.br.CPF;

import br.com.icarros.icontas.entity.Gerente;
import br.com.icarros.icontas.entity.enums.UF;
import lombok.Data;

@Data
public class CorrentistaRequest {
	
	@CPF
    private String cpf;
	
	@NotBlank
    private String agencia;

	@NotBlank
    private String conta;

	@NotBlank
    private String nome;

    @Email
    private String email;

    @NotBlank
    @Pattern(regexp = "(\\d{2})\\d{9}")
    private String telefone;

    @NotBlank
    private String endereco;

    @NotBlank
    @Pattern(regexp = "\\d{5}-\\d{3}")
    private String cep;

    @NotBlank
    private String bairro;

    @NotBlank
    private String cidade;  
    
    @NotNull
    @Enumerated(EnumType.STRING)
    private UF uf;

    private Gerente gerente;

    private Boolean situacao;
}
