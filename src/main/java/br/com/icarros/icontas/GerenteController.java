package br.com.icarros.icontas;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.icarros.icontas.entity.Gerente;
import br.com.icarros.icontas.service.GerenteService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/gerentes")
@AllArgsConstructor
public class GerenteController {
	private GerenteService gerenteService;
	@GetMapping
	public List<Gerente> readClientes(){
//		Cliente cliente = new Cliente();
//		if(endereco!=null) cliente.setEndereco(endereco);
//		if(nome!=null) cliente.setNome(nome);
//		if(fone!=null) cliente.setFone(fone);
//		if(id!=null) cliente.setId(id);
//		
//		
//		Example<Cliente> c = Example.of(cliente);
//		
	
		
		// clienteRepository.findAll(c);
		
		return gerenteService.getGerentes();

	}
}



