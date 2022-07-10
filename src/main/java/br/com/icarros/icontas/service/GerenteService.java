package br.com.icarros.icontas.service;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import br.com.icarros.icontas.dto.request.GerenteRequest;
import br.com.icarros.icontas.dto.response.GerenteResponse;
import br.com.icarros.icontas.entity.Gerente;
import br.com.icarros.icontas.exception.AtributoNaoExistente;
import br.com.icarros.icontas.exception.RecursoNaoEncontrado;
import br.com.icarros.icontas.exception.RegraDeNegocioException;
import br.com.icarros.icontas.repository.GerenteRepository;

@Service 

public class GerenteService {

	@Autowired
    private  GerenteRepository gerenteRepository;
	@Autowired
    private  ModelMapper modelMapper;

	public GerenteResponse createGerente(GerenteRequest gerenteRequest) throws RegraDeNegocioException {  
		Optional<Gerente> g = gerenteRepository.findByCpf(gerenteRequest.getCpf());
		if(g.isPresent()) throw  new RegraDeNegocioException("Esse gerente já possui um cadastro ativo.");
		Gerente gerente = fromDTO(gerenteRequest);
		gerente.setCreationDate();
		gerente.setLastModifiedDate(gerente.getCreationDateTime());
		gerente = this.gerenteRepository.save(gerente);
		return this.toDTO(gerente);
    }

    
    public Gerente fromDTO(GerenteRequest request) {
    	return modelMapper.map(request, Gerente.class);
    }
    public GerenteResponse toDTO(Gerente entity) {
    	return modelMapper.map(entity, GerenteResponse.class);
    }
    
	@Transactional
	public GerenteResponse totallyUpdate(GerenteRequest gerenteRequest,Long id) {
		Optional<Gerente> gerenteOptional = gerenteRepository.findById(id);
		if(!gerenteOptional.isPresent()) throw new RecursoNaoEncontrado("O id informado não correspo com nenhum id de gerente");
		Gerente gerente = gerenteOptional.get();
		gerente.setCpf(gerenteRequest.getCpf());
		gerente.setEmail( gerenteRequest.getEmail());
		gerente.setNome(gerenteRequest.getNome());
		gerente.setSenha(gerenteRequest.getSenha());
		gerente.setLastModifiedDate(LocalDateTime.now());
		return this.toDTO(gerente);
	}
	@Transactional
	public GerenteResponse partilyUpdate(Map<String,Object> gerenteRequest,Long id) throws IllegalArgumentException, IllegalAccessException {
		Optional<Gerente> gerenteOptional = gerenteRepository.findById(id);
		if(!gerenteOptional.isPresent()) throw new RecursoNaoEncontrado("O id informado não correspo com nenhum id de gerente");
		Gerente gerente = gerenteOptional.get();
		gerenteRequest.forEach((key , value)->{
			Field field = ReflectionUtils.findField(Gerente.class, key);
			if(field==null)throw new AtributoNaoExistente("O atributo "+key+" não é um atributo do gerente");
			field.setAccessible(true);
			ReflectionUtils.setField(field, gerente, value);
		});
		gerente.setLastModifiedDate(LocalDateTime.now());
		return this.toDTO(gerente);
	}
    
    
}
