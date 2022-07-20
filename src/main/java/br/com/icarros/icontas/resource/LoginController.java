package br.com.icarros.icontas.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.icarros.icontas.base.ServerSideResponse;
import br.com.icarros.icontas.dto.request.UsuarioRequest;
import br.com.icarros.icontas.dto.response.TokenResponse;
import br.com.icarros.icontas.exception.UsuarioEOuSenhaInvalidoException;
import br.com.icarros.icontas.service.LoginService;

@RestController
public class LoginController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private LoginService loginService;

	@PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
	public ResponseEntity<ServerSideResponse<TokenResponse>> loginCorrentista(
			@RequestBody UsuarioRequest usuarioRequest) throws UsuarioEOuSenhaInvalidoException {
		UsernamePasswordAuthenticationToken usuarioLogin = usuarioRequest.converter();

		Authentication authenticate = authenticationManager.authenticate(usuarioLogin);

		String token = loginService.geraToken(authenticate);

		TokenResponse tokenResponse = new TokenResponse(token);

		return ResponseEntity.ok(ServerSideResponse.<TokenResponse>builder().dado(tokenResponse)
				.statusCode(HttpStatus.OK.value()).build());
	}
}