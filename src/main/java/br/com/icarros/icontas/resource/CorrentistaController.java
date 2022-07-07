package br.com.icarros.icontas.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<Object>  post(@Valid @RequestBody CorrentistaRequest correntistaRequest, Errors errors) {
    	CorrentistaResponse response;
    	if(errors.hasErrors()) {
    		return ResponseEntity.badRequest().body("Erro de validação do DTO");
    	}

    	try {
			response = correntistaService.create(correntistaRequest);
	    	return new ResponseEntity<>(response, HttpStatus.CREATED);
	    	
		} catch (CorrentistaJaAtivoException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (RegraDeNegocioException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

    }
}
