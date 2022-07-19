package br.com.icarros.icontas.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.com.icarros.icontas.dto.request.UsuarioRequest;
import br.com.icarros.icontas.entity.Usuario;
import br.com.icarros.icontas.exception.UsuarioJaCriado;
import br.com.icarros.icontas.repository.UsuarioRepository;
import lombok.AllArgsConstructor;

@Service 
@AllArgsConstructor
public class UsuarioService {
	
	private UsuarioRepository usuarioRepository;
	private final ModelMapper modelMapper ;
	@Transactional
	public String createUser(UsuarioRequest request) throws UsuarioJaCriado {
		Optional<Usuario> usuarioOptional = usuarioRepository.findByUsername(request.getConta());
		if(usuarioOptional.isPresent()) throw new UsuarioJaCriado("usuario já existente");
		Usuario usr = this.fromDTO(request);
		usr.setSenha("$2a$10$AVGA6EPiQ1L9L/1EC9AzbOFmN1v3MCon03doZVOwHrIR1nW7guGS.");
		usr.setPapel("ROLE_CORRENTISTA");
		usr.setUsername(request.getConta());
		usuarioRepository.save(usr);
		
		return "Usuario Criado";
		
	}
	
    public Usuario fromDTO(UsuarioRequest request) {
    	return modelMapper.map(request, Usuario.class);
    }
}
