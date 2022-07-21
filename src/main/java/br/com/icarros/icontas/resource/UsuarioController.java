package br.com.icarros.icontas.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.icarros.icontas.base.ServerSideResponse;
import br.com.icarros.icontas.dto.request.CreateUsuarioRequest;
import br.com.icarros.icontas.exception.UsuarioJaCriado;
import br.com.icarros.icontas.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;
	@PostMapping
	public ResponseEntity<ServerSideResponse<String>> createUsuario(@RequestBody CreateUsuarioRequest payload) throws UsuarioJaCriado {
		String body = usuarioService.createUser(payload);
		ServerSideResponse<String> ssr = ServerSideResponse.<String>builder()
				.dados(body).statusCode(HttpStatus.CREATED.value()).build();
		return new ResponseEntity<ServerSideResponse<String>>(ssr, HttpStatus.CREATED);

	}
	
	
}
