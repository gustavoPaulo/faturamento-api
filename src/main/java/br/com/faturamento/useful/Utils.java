package br.com.faturamento.useful;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utils {

    public static final String ERROR_DEFAULT_VALIDATION = "Validation error. Check 'errors' field for details.";

    public static final String ERROR_UNKNOW = "Unknow error occurred.";

    public static final String ERROR_LANCAMENTO_EXISTS = "Já existe um lançamento com os dados informados.";

    public static final String ERROR_LANCAMENTO_NOT_FOUND = "Lançamento com código code, não encontrado!";

    public static final String DATE_PATTERN = "dd/MM/yyyy";

    public static final String DATE_TIME_PATTERN = "dd/MM/yyyy HH:mm:ss";

    public static final String DATE_LOCALE = "pt-BR";

    public static final String DATE_TIMEZONE = "Brazil/East";

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
