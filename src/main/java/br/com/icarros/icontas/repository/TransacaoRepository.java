package br.com.icarros.icontas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.icarros.icontas.entity.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
	
	public Optional<Transacao> findTopByCorrentistaIdOrderByIdDesc (Long id);
	
}
