package br.com.faturamento.repositories.lancamento;

import br.com.faturamento.model.LancamentoModel;

import java.util.Optional;

public interface LancamentoRepositoryQuery {

    Optional<LancamentoModel> verifyIfExist(LancamentoModel lancamento);
}
