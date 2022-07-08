package br.com.icarros.icontas.service;

import br.com.icarros.icontas.dto.request.CorrentistaRequest;
import br.com.icarros.icontas.dto.response.CorrentistaResponse;
import br.com.icarros.icontas.entity.Correntista;
import br.com.icarros.icontas.exception.CorrentistaJaAtivoException;
import br.com.icarros.icontas.exception.RegraDeNegocioException;
import br.com.icarros.icontas.repository.CorrentistaRepository;
import br.com.icarros.icontas.repository.GerenteRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service 
@AllArgsConstructor
public class CorrentistaService {

    private final CorrentistaRepository correntistaRepository;

    private final GerenteRepository gerenteRepository;

    private final ModelMapper modelMapper;

    @Transactional
	public CorrentistaResponse create(CorrentistaRequest correntistaRequest) throws RegraDeNegocioException, CorrentistaJaAtivoException {

    	Optional<Correntista> correntista = correntistaRepository.findByCpf(correntistaRequest.getCpf());
    	 
    	 if (!correntista.isEmpty()){
               throw  new CorrentistaJaAtivoException("Esse correntista já possui um cadastro ativo.");
         }

        gerenteRepository.findByCpf(correntistaRequest.getGerente().getCpf())
                .orElseThrow(() -> new RegraDeNegocioException("Gerente informado não encontrado."));

        Correntista newCorrentista = correntistaRepository.save(fromDTO(correntistaRequest));
        
        return toResponse(newCorrentista);
    }
    
    private Correntista fromDTO(CorrentistaRequest request) {
    	return modelMapper.map(request, Correntista.class);
    }
    
    private CorrentistaResponse toResponse(Correntista correntista) {
    	return modelMapper.map(correntista, CorrentistaResponse.class);
    }
   
}
