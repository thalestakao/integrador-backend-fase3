package br.com.icarros.icontas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.icarros.icontas.entity.Correntista;

public interface CorrentistaRepository extends JpaRepository<Correntista, Long> {

    public Optional<Correntista> findByCpf (String cpf);

    public Optional<Correntista> findByConta (String conta);
}
