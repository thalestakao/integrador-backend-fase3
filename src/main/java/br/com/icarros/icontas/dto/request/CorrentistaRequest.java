package br.com.icarros.icontas.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.icarros.icontas.entity.Gerente;
import br.com.icarros.icontas.entity.enums.UF;
import lombok.Data;

@Data
public class CorrentistaRequest {
	
	@Size(min = 11, max = 11)
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
    private String telefone;

    @NotBlank
    private String endereco;

    @NotBlank
    private String cep;

    @NotBlank
    private String bairro;

    @NotBlank
    private String cidade;  
    
    @NotBlank
    private UF uf;

    private Gerente gerente;

}
