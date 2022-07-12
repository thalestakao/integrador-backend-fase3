package br.com.icarros.icontas.service;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import br.com.icarros.icontas.dto.request.GerenteRequest;
import br.com.icarros.icontas.dto.response.GerenteResponse;
import br.com.icarros.icontas.entity.Gerente;
import br.com.icarros.icontas.repository.GerenteRepository;
import lombok.AllArgsConstructor;

@Service 
@AllArgsConstructor
public class GerenteService {
	private final GerenteRepository gerenteRepository;
	private final ModelMapper modelMapper;
	
	public GerenteResponse createGerente(GerenteRequest request) {
		Optional<Gerente> findByCpf = gerenteRepository.findByCpf(request.getCpf());
		if(findByCpf.isPresent()) throw new RuntimeException("hghg");
		Gerente gerente = this.fromDTO(request);
		gerenteRepository.save(gerente);
		return this.toDTO(gerente);
		
	}
	@Transactional
	public GerenteResponse updateTotalyGerente(GerenteRequest request,Long id) {
		Optional<Gerente> gerenteOptional = gerenteRepository.findById(id);
		if(!gerenteOptional.isPresent()) throw new RuntimeException("gerente não encontrado");
		Gerente gerente = gerenteOptional.get();
		gerente.setCpf(request.getCpf());
		gerente.setEmail(request.getEmail());
		gerente.setNome(request.getNome());
		return this.toDTO(gerente);
	}
	
	public GerenteResponse updatePartityGerente(Map<String,Object> request,Long id) {
		Optional<Gerente> gerenteOptional = gerenteRepository.findById(id);
		if(!gerenteOptional.isPresent())throw new RuntimeException("gerente não encontrado");
		Gerente gerente = gerenteOptional.get();
		request.forEach((key , value)->{
			Field field = ReflectionUtils.findField(Gerente.class, key);
			if(field==null) throw new RuntimeException("Campo "+key+" não faz parte de gerente.");
			field.setAccessible(true);
			ReflectionUtils.setField(field, gerente, value);
		});
		return this.toDTO(gerente);
	}
	
    public Gerente fromDTO(GerenteRequest request) {
    	return modelMapper.map(request, Gerente.class);
    }
    public GerenteResponse toDTO(Gerente entity) {
    	return modelMapper.map(entity, GerenteResponse.class);
    }
	
	
	
	
	
	
	
	
}
