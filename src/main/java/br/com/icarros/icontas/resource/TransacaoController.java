package br.com.icarros.icontas.resource;

import javax.validation.Valid;

import br.com.icarros.icontas.dto.response.SaldoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.icarros.icontas.base.ServerSideResponse;
import br.com.icarros.icontas.dto.request.DepositaRequest;
import br.com.icarros.icontas.dto.request.SaqueRequest;
import br.com.icarros.icontas.dto.response.DepositaResponse;
import br.com.icarros.icontas.dto.response.SaqueResponse;
import br.com.icarros.icontas.exception.RegraDeNegocioException;
import br.com.icarros.icontas.service.TransacaoService;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {
	
	@Autowired
	private TransacaoService transacaoService;
	
	@PostMapping(path = "/deposita")
	public ResponseEntity<ServerSideResponse<DepositaResponse>> deposita(
			@Valid @RequestBody DepositaRequest depositaRequest) throws RegraDeNegocioException{
		
		DepositaResponse depositaResponse;
		depositaResponse = transacaoService.deposita(depositaRequest);
		
		ServerSideResponse<DepositaResponse> ssr = ServerSideResponse.<DepositaResponse>builder()
				.dado(depositaResponse).statusCode(HttpStatus.OK.value()).build();
		
		return new ResponseEntity<ServerSideResponse<DepositaResponse>>(ssr, HttpStatus.OK);
		
	}
	
	@PostMapping(path = "/saque")
	public ResponseEntity<ServerSideResponse<SaqueResponse>> saque(
			@Valid @RequestBody SaqueRequest saqueRequest) throws RegraDeNegocioException{
		
		SaqueResponse saqueResponse;
		saqueResponse = transacaoService.saque(saqueRequest);
		
		ServerSideResponse<SaqueResponse> ssr = ServerSideResponse.<SaqueResponse>builder()
				.dado(saqueResponse).statusCode(HttpStatus.OK.value()).build();
		
		return new ResponseEntity<ServerSideResponse<SaqueResponse>>(ssr, HttpStatus.OK);
		
	}

	@GetMapping(path = "/saldo")
	public ResponseEntity<ServerSideResponse<SaldoResponse>> saldo() throws RegraDeNegocioException{

		SaldoResponse saldoResponse;
		saldoResponse = transacaoService.saldo();

		ServerSideResponse<SaldoResponse> ssr = ServerSideResponse.<SaldoResponse>builder()
				.dado(saldoResponse).statusCode(HttpStatus.OK.value()).build();

		return new ResponseEntity<ServerSideResponse<SaldoResponse>>(ssr, HttpStatus.OK);
	}
}
