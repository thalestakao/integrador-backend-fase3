package br.com.icarros.icontas.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.icarros.icontas.base.ServerSideResponse;
import br.com.icarros.icontas.dto.request.CorrentistaRequest;
import br.com.icarros.icontas.dto.response.CorrentistaResponse;
import br.com.icarros.icontas.exception.CorrentistaJaAtivoException;
import br.com.icarros.icontas.exception.RegraDeNegocioException;
import br.com.icarros.icontas.service.CorrentistaService;

@RestController
@RequestMapping("/correntista")
public class CorrentistaController {

	@Autowired
	private CorrentistaService correntistaService;

	@PostMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity<ServerSideResponse<CorrentistaResponse>> post(
			@Valid @RequestBody CorrentistaRequest correntistaRequest)
			throws CorrentistaJaAtivoException, RegraDeNegocioException {
		
		CorrentistaResponse correntistaResponse;		

		correntistaResponse = correntistaService.create(correntistaRequest);

		ServerSideResponse<CorrentistaResponse> ssr = ServerSideResponse.<CorrentistaResponse>builder()
				.dado(correntistaResponse).statusCode(HttpStatus.CREATED.value()).build();

		return new ResponseEntity<ServerSideResponse<CorrentistaResponse>>(ssr, HttpStatus.CREATED);

	}

	@DeleteMapping(path = "/{numConta}")
	public ResponseEntity<ServerSideResponse<CorrentistaResponse>> delete(@PathVariable String numConta) throws RegraDeNegocioException {

		CorrentistaResponse correntistaResponse;
		correntistaResponse= correntistaService.delete(numConta);

		ServerSideResponse<CorrentistaResponse> ssr = ServerSideResponse.<CorrentistaResponse>builder()
				.dado(correntistaResponse).statusCode(HttpStatus.OK.value()).build();

		return new ResponseEntity<ServerSideResponse<CorrentistaResponse>>(ssr, HttpStatus.OK);
	}

	@PutMapping(path = "/{numConta}")
	public ResponseEntity<ServerSideResponse<CorrentistaResponse>> update(
			@Valid @RequestBody CorrentistaRequest correntistaRequest, @PathVariable String numConta) throws RegraDeNegocioException {

		CorrentistaResponse correntistaResponse;
		correntistaResponse = correntistaService.update(correntistaRequest, numConta);

		ServerSideResponse<CorrentistaResponse> ssr = ServerSideResponse.<CorrentistaResponse>builder()
				.dado(correntistaResponse).statusCode(HttpStatus.OK.value()).build();

		return new ResponseEntity<ServerSideResponse<CorrentistaResponse>>(ssr, HttpStatus.OK);
	}
}
