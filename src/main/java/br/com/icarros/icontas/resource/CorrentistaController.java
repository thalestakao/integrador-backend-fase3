package br.com.icarros.icontas.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.icarros.icontas.dto.request.CorrentistaRequest;
import br.com.icarros.icontas.service.CorrentistaService;

@RestController
@RequestMapping("/correntista")
public class CorrentistaController {

    @Autowired
    private CorrentistaService correntistaService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void post(@Valid @RequestBody CorrentistaRequest correntistaRequest) {

    }
}
