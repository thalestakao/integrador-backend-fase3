package br.com.icarros.icontas.dto.response;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class GerenteResponse {
	
	private Long id;
	
    private String cpf;
		
    private String senha;
	
    private String nome;

    private String email;

}
