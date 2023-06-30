package br.com.faturamento.services;

import br.com.faturamento.model.LancamentoModel;
import br.com.faturamento.model.TipoLancamento;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class LancamentoServiceTest {

    @Autowired
    private LancamentoService lancamentoService;
    private final LancamentoModel lancamentoTeste = new LancamentoModel(
            BigDecimal.valueOf(150), "Pagamento de conta de luz",
            TipoLancamento.DESPESA, new Date());

    @Test
    @DisplayName("Listando todo os lançamentos")
    public void listar() {
        List<LancamentoModel> lista = lancamentoService.listar();

        if (lista.size() < 0) {
            throw new RuntimeException("Não foi possível listar os lançamentos.");
        }
    }

    @Test
    @DisplayName("Criando novo lançamento")
    public void salvar() {
        LancamentoModel lancamento = lancamentoService.salvar(lancamentoTeste);

        if (lancamento.getCodigo() == null && lancamento.getCodigo() <= 0) {
            throw new RuntimeException("Não foi possível registrar o lançamento.");
        }
    }

    @Test
    @DisplayName("Recuperando lançamento pelo código")
    public void buscarPorCodigo() {
        Optional<LancamentoModel> lancamento = lancamentoService.pesquisarPorCodigo(1);

        if (lancamento.get().getCodigo() != 1) {
            throw new RuntimeException("Não foi possível recuperar o lançamento.");
        }
    }
}
