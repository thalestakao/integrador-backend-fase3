package br.com.icarros.icontas.service;

import java.math.BigDecimal;
import java.util.Optional;

import br.com.icarros.icontas.dto.response.SaldoResponse;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.icarros.icontas.dto.request.DepositaRequest;
import br.com.icarros.icontas.dto.request.SaqueRequest;
import br.com.icarros.icontas.dto.response.DepositaResponse;
import br.com.icarros.icontas.dto.response.SaqueResponse;
import br.com.icarros.icontas.entity.Correntista;
import br.com.icarros.icontas.entity.Transacao;
import br.com.icarros.icontas.entity.enums.TipoOperacao;
import br.com.icarros.icontas.exception.CorrentistaNaoEncontradoException;
import br.com.icarros.icontas.exception.RegraDeNegocioException;
import br.com.icarros.icontas.exception.SaldoInsuficienteException;
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

		Correntista correntista = correntistaRepository.findByConta(username)
				.orElseThrow(() -> new CorrentistaNaoEncontradoException("Correntista não encontrado"));

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

		return toResponseDeposita(transacao);
	}

	public DepositaResponse toResponseDeposita(Transacao deposita) {
		return mapper.map(deposita, DepositaResponse.class);
	}

	public SaqueResponse saque(SaqueRequest saqueRequest) throws RegraDeNegocioException, SaldoInsuficienteException{

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String username = authentication.getName();

		Correntista correntista = correntistaRepository.findByConta(username)
				.orElseThrow(() -> new CorrentistaNaoEncontradoException("Correntista não encontrado"));

		Optional<Transacao> ultimaTransacaoCorrentistaOPT = transacaoRepository.findTopByCorrentistaIdOrderByIdDesc(correntista.getId());

		BigDecimal saldoAnterior = BigDecimal.ZERO;

		if(ultimaTransacaoCorrentistaOPT.isPresent()) {
			saldoAnterior = ultimaTransacaoCorrentistaOPT.get().getSaldoAtual();
		}

		if(saldoAnterior.compareTo(saqueRequest.getVlrSaque())< 0) {
			
			throw new SaldoInsuficienteException("Saldo insuficiente.");
			
		}else {
			BigDecimal saldoAtual = saldoAnterior.subtract(saqueRequest.getVlrSaque());

			Transacao saque = Transacao.builder()
					.valor(saqueRequest.getVlrSaque())
					.saldoAnterior(saldoAnterior)
					.saldoAtual(saldoAtual)
					.correntista(correntista)
					.tipoOperacao(TipoOperacao.SAIDA)
					.build();

			Transacao transacao = transacaoRepository.save(saque);
			return toResponseSaque(saque);
		}
	}

	private SaqueResponse toResponseSaque(Transacao saque) {
		return mapper.map(saque, SaqueResponse.class);
	}

	public SaldoResponse saldo() throws RegraDeNegocioException{

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String username = authentication.getName();

		Correntista correntista = correntistaRepository.findByConta(username)
				.orElseThrow(() -> new CorrentistaNaoEncontradoException("Correntista não encontrado"));

		Optional<Transacao> transacaoCorrentistaOPT = transacaoRepository.findTopByCorrentistaIdOrderByIdDesc(correntista.getId());

		if(transacaoCorrentistaOPT.isPresent()){
			return toResponseSaldo(transacaoCorrentistaOPT.get());
		}
			return new SaldoResponse(new BigDecimal(0));
		}

	private SaldoResponse toResponseSaldo(Transacao transacao) {
		return mapper.map(transacao, SaldoResponse.class);
	}

}
