package br.com.rafael.api.repository;

import br.com.rafael.api.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

    @Query("SELECT c FROM Conta c WHERE c.flagAtivo = true AND c.idConta = :idConta")
    Optional<Conta> findContaAtivaPorId(@Param("idConta") Long id);
}
