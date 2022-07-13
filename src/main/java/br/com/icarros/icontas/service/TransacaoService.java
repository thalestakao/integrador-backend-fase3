package br.com.icarros.icontas.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.icarros.icontas.dto.request.DepositaRequest;
import br.com.icarros.icontas.dto.response.DepositaResponse;
import br.com.icarros.icontas.entity.Correntista;
import br.com.icarros.icontas.entity.Transacao;
import br.com.icarros.icontas.entity.enums.TipoOperacao;
import br.com.icarros.icontas.exception.CorrentistaNaoEcontradoException;
import br.com.icarros.icontas.exception.RegraDeNegocioException;
import br.com.icarros.icontas.repository.CorrentistaRepository;
import br.com.icarros.icontas.repository.TransacaoRepository;
import lombok.AllArgsConstructor;

@Service 
@AllArgsConstructor
public class TransacaoService {
	
	private final CorrentistaRepository correntistaRepository;
	
	private final TransacaoRepository transacaoRepository;
	
	private final ModelMapper mapper;
	
	
	public DepositaResponse deposita(DepositaRequest depositaRequest) throws RegraDeNegocioException{
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		String username = authentication.getName();
		System.out.println(username);
		Correntista correntista = correntistaRepository.findByConta(username)
                .orElseThrow(() -> new CorrentistaNaoEcontradoException("Correntista não encontrado"));
		
		Optional<Transacao> ultimaTransacaoCorrentistaOPT = transacaoRepository.findTopByCorrentistaIdOrderByIdDesc(correntista.getId());
		
		BigDecimal saldoAnterior = BigDecimal.ZERO;
		
		if(ultimaTransacaoCorrentistaOPT.isPresent()) {
			saldoAnterior = ultimaTransacaoCorrentistaOPT.get().getSaldoAtual();
		}
		
		BigDecimal saldoAtual = saldoAnterior.add(depositaRequest.getVlrDeposita());
	
		Transacao deposito = Transacao.builder()
				.valor(depositaRequest.getVlrDeposita())
				.saldoAnterior(saldoAnterior)
				.saldoAtual(saldoAtual)
				.correntista(correntista)
				.tipoOperacao(TipoOperacao.ENTRADA)
				.build();
		
		Transacao transacao = transacaoRepository.save(deposito);
				
		return toResponse(transacao);
	}
	
	private DepositaResponse toResponse(Transacao deposita) {
		return mapper.map(deposita, DepositaResponse.class);
	}
	
	

}
