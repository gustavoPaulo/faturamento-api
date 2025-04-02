package br.com.billing.faturamento.useful;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utility {

    public static final String ERROR_DEFAULT_VALIDATION = "Validation error. Check 'errors' field for details.";

    public static final String ERROR_UNKNOW = "Unknow error occurred.";

    public static final String ERROR_INVOICE_ALREADY_EXISTS = "Já existe um lançamento com os dados informados.";

    public static final String ERROR_INVOICE_NOT_FOUND = "Lançamento com código codeInvoice, não encontrado!";

    public static final String DATE_PATTERN = "dd/MM/yyyy";

    public static final String DATE_TIME_PATTERN = "dd/MM/yyyy HH:mm:ss";

    public static final String DATE_LOCALE = "pt-BR";

    public static final String DATE_TIMEZONE = "Brazil/East";

    public static final String ENTITY_NAME_INVOICE = "invoice";

    public static final String MODEL_INVOICE_CODE = "codeInvoice";

    public static final String MODEL_INVOICE_PRICE = "price";

    public static final String MODEL_INVOICE_DESCRIPTION = "description";

    public static final String MODEL_INVOICE_TYPE = "type";

    public static final String MODEL_INVOICE_DATERELEASE = "dateRelease";

    public static final String RESOURCE_INVOICE_CODE = "/{code}";

    public static final String RESOURCE_INVOICE_FILTER = "/findByFilter";

    public static final String RESOURCE_REQUEST_MAPPING = "/invoices";

    public static final String LOG_INFO_REMOVE_SUCCESS = "Dados removidos com sucesso!";

    public static final String LOG_INFO_REMOVE_DATA_NOTEXIST = "Não existem dados a serem removidos.";

    public static final String TEST_INVOICE_DESCRIPTION = "Pagamento de conta de luz";

    public static final String TEST_INVOICE_DESCRIPTION_UPDATED = "Lançamento alterado";

    public static final String TEST_TITLE_LIST = "Listando todos os lançamentos";

    public static final String TEST_TITLE_SAVE = "Criando novo lançamento";

    public static final String TEST_TITLE_FINDBYCODE = "Recuperando lançamento pelo código";

    public static final String TEST_TITLE_DELETE = "Removendo lançamento";

    public static final String TEST_TITLE_UPDATE = "Atualizando lançamento";

    public static final String TEST_LIST_LIST_CANNOT_EMPTY = "A lista não pode ser vazia.";

    public static final String TEST_SAVE_INVOICE_CANNOT_NULL = "O lançamento não pode ser nulo.";

    public static final String TEST_DELETE_INVOICE_CANNOT_EXIST = "Este método deve retornar uma exceção de objeto não encontrado.";

    public static final String TEST_UPDATE_INVOICE_NEEDS_TOBESAME = "Os códigos devem ser iguais.";

    public static final String TEST_UPDATE_INVOICE_PRICE_NEEDS_TOBESAME_600 = "O valor deve ser igual a 600.";

    public static final String TEST_UPDATE_INVOICE_TYPE_NEEDS_TOBESAME = "O tipo do lançamento deve ser de Receita";

    public static String getDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_PATTERN);
        Date dataHora = Calendar.getInstance().getTime();

        return sdf.format(dataHora);
    }
}
