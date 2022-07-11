package br.com.icarros.icontas.repository;

import br.com.icarros.icontas.entity.Correntista;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CorrentistaRepository extends CrudRepository<Correntista, Long> {

    public Optional<Correntista> findByCpf (String cpf);

    public Optional<Correntista> findByConta (String conta);
}
