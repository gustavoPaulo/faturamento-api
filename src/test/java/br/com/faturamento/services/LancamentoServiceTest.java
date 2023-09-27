package br.com.faturamento.services;

import br.com.faturamento.model.LancamentoModel;
import br.com.faturamento.model.enums.TipoLancamento;
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

    public static final Logger LOG = Logger.getLogger(LancamentoServiceTest.class.getName());

    private static final String LANCAMENTO_DESCRICAO = "Pagamento de conta de luz";

    @Autowired
    private LancamentoService lancamentoService;

    private final LancamentoModel lancamentoTeste = new LancamentoModel(
            BigDecimal.valueOf(150), LANCAMENTO_DESCRICAO,
            TipoLancamento.DESPESA, LocalDate.now());

    @AfterEach
    void cleanDataTests() {
        try {
            lancamentoService.remover(lancamentoTeste.getCodigo());
            LOG.info("Dados removidos com sucesso!");

        } catch (ObjectNotFoundException e) {
            LOG.info("Não existem dados a serem removidos.");
        }
    }

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

        lancamentoService.remover(lancamento.getCodigo());

        String messageExpected = "Lançamento com código " + lancamento.getCodigo() + ", não encontrado!";
        String messageFromException = "";

        try {
            lancamentoService.pesquisarPorCodigo(lancamento.getCodigo());

        } catch (ObjectNotFoundException e) {
            messageFromException = e.getEntityName();
        }

        Assert.isTrue(messageFromException.equals(messageExpected),
        "Este método deve retornar uma exceção de objeto não encontrado.");
    }

    @Test
    @DisplayName("Atualizando lançamento")
    void atualizar() {
        LancamentoModel lancamento = lancamentoService.salvar(lancamentoTeste);

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
