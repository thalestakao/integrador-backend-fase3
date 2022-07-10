package br.com.icarros.icontas.repository;

import br.com.icarros.icontas.entity.Gerente;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface GerenteRepository extends CrudRepository<Gerente, Long> {

    public Optional<Gerente> findByCpf (String cpf);

}
