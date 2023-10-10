package br.com.faturamento.useful;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utils {

    public static final String ERROR_DEFAULT_VALIDATION = "Validation error. Check 'errors' field for details.";

    public static final String ERROR_UNKNOW = "Unknow error occurred.";

    public static final String ERROR_LANCAMENTO_EXISTS = "Já existe um lançamento com os dados informados.";

    public static final String ERROR_LANCAMENTO_NOT_FOUND = "Lançamento com código codigoLancamento, não encontrado!";

    public static final String DATE_PATTERN = "dd/MM/yyyy";

    public static final String DATE_TIME_PATTERN = "dd/MM/yyyy HH:mm:ss";

    public static final String DATE_LOCALE = "pt-BR";

    public static final String DATE_TIMEZONE = "Brazil/East";

    public static final String MODEL_LANCAMENTO_CODIGO = "codigoLancamento";

    public static final String LOG_INFO_REMOCAO_SUCESSO = "Dados removidos com sucesso!";

    public static final String LOG_INFO_REMOCAO_DADOS_INEXISTENTES = "Não existem dados a serem removidos.";

    public static final String TESTE_LANCAMENTO_DESCRICAO = "Pagamento de conta de luz";

    public static final String TESTE_LANCAMENTO_DESCRICAO_ATUALIZADA = "Lançamento alterado";

    public static final String TESTE_TITULO_LISTAR = "Listando todos os lançamentos";

    public static final String TESTE_TITULO_SALVAR = "Criando novo lançamento";

    public static final String TESTE_TITULO_PESQUISAR_POR_CODIGO = "Recuperando lançamento pelo código";

    public static final String TESTE_TITULO_REMOVER = "Removendo lançamento";

    public static final String TESTE_TITULO_ATUALIZAR = "Atualizando lançamento";

    public static final String TESTE_LISTAR_LISTA_NAO_PODE_VAZIA = "A lista não pode ser vazia.";

    public static final String TESTE_SALVAR_LANCAMENTO_NAO_PODE_NULO = "O lançamento não pode ser nulo.";

    public static final String TESTE_REMOVER_LANCAMENTO_NAO_PODE_EXISTIR = "Este método deve retornar uma exceção de objeto não encontrado.";

    public static final String TESTE_ATUALIZAR_LANCAMENTO_CODIGO_DEVE_SER_IGUAL = "Os códigos devem ser iguais.";

    public static final String TESTE_ATUALIZAR_LANCAMENTO_VALOR_DEVE_SER_IGUAL_600 = "O valor deve ser igual a 600.";

    public static final String TESTE_ATUALIZAR_LANCAMENTO_TIPO_DEVE_SER_IGUAL = "O tipo do lançamento deve ser de Receita";

    public static final String getDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_PATTERN);
        Date HORA = Calendar.getInstance().getTime();

        return sdf.format(HORA);
    }

    public static final String getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
        Date HORA = Calendar.getInstance().getTime();

        return sdf.format(HORA);
    }
}
