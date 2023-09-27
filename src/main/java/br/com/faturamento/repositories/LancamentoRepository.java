package br.com.faturamento.repositories;

import br.com.faturamento.model.LancamentoModel;
import br.com.faturamento.model.enums.TipoLancamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface LancamentoRepository extends JpaRepository<LancamentoModel, Integer> {

    @Query("SELECT l FROM LancamentoModel l WHERE l.valor = ?1 AND l.descricao = ?2 AND l.tipo = ?3 " +
            "AND l.dataLancamento = ?4")
    Optional<LancamentoModel> verifyIfExist(BigDecimal valor, String descricao, TipoLancamento tipo,
                                           LocalDate dataLancamento);
}
