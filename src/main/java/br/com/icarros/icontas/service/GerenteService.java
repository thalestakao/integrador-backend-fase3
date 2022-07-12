package br.com.icarros.icontas.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.icarros.icontas.dto.request.CorrentistaRequest;
import br.com.icarros.icontas.entity.Correntista;
import br.com.icarros.icontas.entity.Gerente;
import br.com.icarros.icontas.exception.RegraDeNegocioException;
import br.com.icarros.icontas.repository.CorrentistaRepository;
import br.com.icarros.icontas.repository.GerenteRepository;
import lombok.AllArgsConstructor;

@Service 

public class GerenteService {
	@Autowired
    private  CorrentistaRepository correntistaRepository;
	@Autowired
    private  GerenteRepository gerenteRepository;
	@Autowired
    private  ModelMapper modelMapper;

	public void create(CorrentistaRequest correntistaRequest) throws RegraDeNegocioException {

        correntistaRepository.findByCpf(correntistaRequest.getCpf())
                .orElseThrow(() -> new RegraDeNegocioException("Esse correntista já possui um cadastro ativo"));

        this.correntistaRepository.save(fromDTO(correntistaRequest));

    }

    
    public Correntista fromDTO(CorrentistaRequest request) {
    	return modelMapper.map(request, Correntista.class);
    }
}
