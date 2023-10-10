package br.com.faturamento.repositories;

import br.com.faturamento.model.LancamentoModel;
import br.com.faturamento.repositories.lancamento.LancamentoRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LancamentoRepository extends JpaRepository<LancamentoModel, Integer>, LancamentoRepositoryQuery {

    Optional<LancamentoModel> verifyIfExist(LancamentoModel lancamento);
}
