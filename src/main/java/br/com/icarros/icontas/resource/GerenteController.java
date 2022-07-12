package br.com.icarros.icontas.resource;

import java.net.URI;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.icarros.icontas.dto.request.GerenteRequest;
import br.com.icarros.icontas.dto.response.GerenteResponse;
import br.com.icarros.icontas.service.GerenteService;

@RestController
@RequestMapping("/gerente")
public class GerenteController {
	@Autowired
	private GerenteService gereneteService;
	
	@PostMapping
	public ResponseEntity<GerenteResponse> postGerente(@RequestBody @Valid GerenteRequest request,UriComponentsBuilder uriBuilder){
		GerenteResponse body = gereneteService.createGerente(request);
		URI uri = uriBuilder.path("gerente/{id}").buildAndExpand(body.getId()).toUri();
		return ResponseEntity.created(uri).body(body);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<GerenteResponse> putGerente(@RequestBody @Valid GerenteRequest request,@PathVariable Long id){
		GerenteResponse body = gereneteService.updateTotalyGerente(request, id);
		return ResponseEntity.ok(body);
	}
	@PatchMapping("/{id}")
	public ResponseEntity<GerenteResponse> patchGerente(@RequestBody  Map<String,Object> request,@PathVariable Long id){
		GerenteResponse body = gereneteService.updatePartityGerente(request, id);
		return ResponseEntity.ok(body);
	}
	
	
	
	
}
