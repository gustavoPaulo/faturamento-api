package br.com.faturamento.services;

import br.com.faturamento.model.LancamentoModel;
import br.com.faturamento.model.enums.TipoLancamento;
import br.com.faturamento.useful.Utils;
import org.hibernate.ObjectNotFoundException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

@SpringBootTest
public class LancamentoServiceTest {

    private static final Logger LOG = Logger.getLogger(LancamentoServiceTest.class.getName());

    private final LancamentoModel lancamentoTeste = new LancamentoModel(
            BigDecimal.valueOf(150), Utils.TESTE_LANCAMENTO_DESCRICAO,
            TipoLancamento.DESPESA, LocalDate.now());

    @Autowired
    private LancamentoService lancamentoService;

    @AfterEach
    void cleanDataTests() {
        try {
            lancamentoService.remover(lancamentoTeste.getCodigo());
            LOG.info(Utils.LOG_INFO_REMOCAO_SUCESSO);

        } catch (ObjectNotFoundException e) {
            LOG.info(Utils.LOG_INFO_REMOCAO_DADOS_INEXISTENTES);
        }
    }

    @Test
    @DisplayName(Utils.TESTE_TITULO_LISTAR)
    void listar() {
        lancamentoService.salvar(lancamentoTeste);

        List<LancamentoModel> lista = lancamentoService.listar();

        Assert.notEmpty(lista, Utils.TESTE_LISTAR_LISTA_NAO_PODE_VAZIA);
    }

    @Test
    @DisplayName(Utils.TESTE_TITULO_SALVAR)
    void salvar() {
        LancamentoModel lancamento = lancamentoService.salvar(lancamentoTeste);

        Assert.notNull(lancamento, Utils.TESTE_SALVAR_LANCAMENTO_NAO_PODE_NULO);
    }

    @Test
    @DisplayName(Utils.TESTE_TITULO_PESQUISAR_POR_CODIGO)
    void pesquisarPorCodigo() {
        LancamentoModel lancamentoSalvo = lancamentoService.salvar(lancamentoTeste);

        LancamentoModel lancamentoRecuperado = lancamentoService.pesquisarPorCodigo(lancamentoSalvo.getCodigo());

        Assert.notNull(lancamentoRecuperado, Utils.TESTE_SALVAR_LANCAMENTO_NAO_PODE_NULO);
    }

    @Test
    @DisplayName(Utils.TESTE_TITULO_REMOVER)
    void remover() {
        LancamentoModel lancamento = lancamentoService.salvar(lancamentoTeste);

        lancamentoService.remover(lancamento.getCodigo());

        String messageExpected = Utils.ERROR_LANCAMENTO_NOT_FOUND
                .replace(Utils.MODEL_LANCAMENTO_CODIGO, lancamento.getCodigo().toString());
        String messageFromException = "";

        try {
            lancamentoService.pesquisarPorCodigo(lancamento.getCodigo());

        } catch (ObjectNotFoundException e) {
            messageFromException = e.getEntityName();
        }

        Assert.isTrue(messageFromException.equals(messageExpected),
        Utils.TESTE_REMOVER_LANCAMENTO_NAO_PODE_EXISTIR);
    }

    @Test
    @DisplayName(Utils.TESTE_TITULO_ATUALIZAR)
    void atualizar() {
        LancamentoModel lancamento = lancamentoService.salvar(lancamentoTeste);

        lancamento.setValor(BigDecimal.valueOf(600));
        lancamento.setDescricao(Utils.TESTE_LANCAMENTO_DESCRICAO_ATUALIZADA);
        lancamento.setTipo(TipoLancamento.RECEITA);

        LancamentoModel lancamentoAtualizado = lancamentoService.atualizar(
                lancamento.getCodigo(), lancamento);

        Assert.isTrue(lancamento.getCodigo() == lancamentoAtualizado.getCodigo(),
        Utils.TESTE_ATUALIZAR_LANCAMENTO_CODIGO_DEVE_SER_IGUAL);

        Assert.isTrue(lancamentoAtualizado.getValor().compareTo(BigDecimal.valueOf(600)) == 0,
        Utils.TESTE_ATUALIZAR_LANCAMENTO_VALOR_DEVE_SER_IGUAL_600);

        Assert.isTrue(lancamentoAtualizado.getTipo().equals(TipoLancamento.RECEITA),
        Utils.TESTE_ATUALIZAR_LANCAMENTO_TIPO_DEVE_SER_IGUAL);
    }
}
