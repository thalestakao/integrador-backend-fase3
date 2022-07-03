package br.com.icarros.icontas.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.com.icarros.icontas.entity.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
	Optional<Usuario> findByUsername(String username);
}
