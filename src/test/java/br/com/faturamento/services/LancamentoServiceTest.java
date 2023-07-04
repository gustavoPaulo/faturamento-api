package br.com.faturamento.services;

import br.com.faturamento.model.LancamentoModel;
import br.com.faturamento.model.enums.TipoLancamento;
import org.hibernate.ObjectNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class LancamentoServiceTest {

    @Autowired
    private LancamentoService lancamentoService;

    private final LancamentoModel lancamentoTeste = new LancamentoModel(
            BigDecimal.valueOf(150), "Pagamento de conta de luz",
            TipoLancamento.DESPESA, new Date());

    @Test
    @DisplayName("Listando todos os lançamentos")
    void listar() {
        lancamentoService.salvar(lancamentoTeste);

        List<LancamentoModel> lista = lancamentoService.listar();

        Assert.notEmpty(lista, "A lista não pode ser vazia.");
    }

    @Test
    @DisplayName("Criando novo lançamento")
    void salvar() {
        LancamentoModel lancamento = lancamentoService.salvar(lancamentoTeste);

        Assert.notNull(lancamento, "O lançamento não pode ser nulo.");
    }

    @Test
    @DisplayName("Recuperando lançamento pelo código")
    void pesquisarPorCodigo() {
        LancamentoModel lancamentoSalvo = lancamentoService.salvar(lancamentoTeste);

        LancamentoModel lancamentoRecuperado = lancamentoService.pesquisarPorCodigo(lancamentoSalvo.getCodigo());

        Assert.notNull(lancamentoRecuperado, "O lançamento não pode ser nulo.");
    }

    @Test
    @DisplayName("Removendo lançamento")
    void remover() {
        LancamentoModel lancamento = lancamentoService.salvar(lancamentoTeste);

        Assert.notNull(lancamento, "O lançamento não pode ser nulo.");

        lancamentoService.remover(lancamento.getCodigo());

        String messageExpected = "Lançamento com código " + lancamento.getCodigo() + ", não encontrado!";
        String messageFromException = "";

        try {
            lancamentoService.pesquisarPorCodigo(lancamento.getCodigo());

        } catch (ObjectNotFoundException ex) {
            messageFromException = ex.getEntityName();
        }

        Assert.isTrue(messageFromException.equals(messageExpected),
        "Este método deve retornar uma exceção de objeto não encontrado.");
    }

    @Test
    @DisplayName("Atualizando lançamento")
    void atualizar() {
        LancamentoModel lancamento = lancamentoService.salvar(lancamentoTeste);

        Assert.notNull(lancamento, "O lançamento não pode ser nulo.");

        lancamento.setValor(BigDecimal.valueOf(600));
        lancamento.setDescricao("Lançamento alterado");
        lancamento.setTipo(TipoLancamento.RECEITA);

        LancamentoModel lancamentoAtualizado = lancamentoService.atualizar(
                lancamento.getCodigo(), lancamento);

        Assert.isTrue(lancamento.getCodigo() == lancamentoAtualizado.getCodigo(),
        "Os códigos devem ser iguais.");

        Assert.isTrue(lancamentoAtualizado.getValor().compareTo(BigDecimal.valueOf(600)) == 0,
        "O valor deve ser igual a 600.");

        Assert.isTrue(lancamentoAtualizado.getTipo().equals(TipoLancamento.RECEITA),
        "O tipo do lançamento deve ser de Receita");
    }
}
