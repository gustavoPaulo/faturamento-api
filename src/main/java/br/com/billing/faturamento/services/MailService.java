package br.com.billing.faturamento.services;

import br.com.billing.faturamento.model.InvoiceModel;
import br.com.billing.faturamento.model.MailModel;
import br.com.billing.faturamento.model.enums.InvoiceType;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

@Service
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Value("${spring.mail.password}")
    private String password;

    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.port}")
    private String port;

    public MailModel sendEmail(List<InvoiceModel> invoices, String userEmail) {
        String mailUser = userEmail.replace("'", "");

        MailModel mail = new MailModel();
        mail.setInvoice(invoices);
        mail.setDestination(mailUser);

        try {
            Properties properties = new Properties();
            properties.put("mail.smtp.host", host);
            properties.put("mail.smtp.port", port);
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.auth", "true");

            Session session = Session.getInstance(properties, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(sender, password);
                }
            });

            MimeMessage message = new MimeMessage(session);
            message.setFrom(this.sender);
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(mailUser));
            message.setSubject("Billing - Envio de faturamento");
            message.setText(fillEmail(invoices));
            message.setContent(fillEmail(invoices), "text/html; charset=UTF-8");

            Transport.send(message);

            mail.setStatus("OK");
            mail.setMessage("Faturamento enviado com sucesso para [" + mailUser + "]");

        } catch (Exception e) {
            mail.setStatus("NOK");
            mail.setMessage("Error ao enviar faturamento por e-mail para [" + mailUser + "]: " + e.getMessage());
        }

        return mail;
    }

    private String getPeriod() {
        int moment = Integer.parseInt(LocalTime.now().withNano(0).withSecond(0).toString().replace(":", ""));
        String resposta = "";

        if (moment >= 600 && moment <= 1200) {
            resposta += "Bom dia";
        } else if (moment >= 1201 && moment <= 1859) {
            resposta += "Boa tarde";
        } else if (moment >= 1900 && moment <= 559) {
            resposta += "Boa noite";
        }

        return resposta;
    }

    private String fillEmail(List<InvoiceModel> list) {

        StringBuilder builder = new StringBuilder();
        builder.append(getPeriod() + ", ");
        builder.append("<br><br>");
        builder.append("Seguem os dados do(s) faturamento(s) solicitados!");
        builder.append("<br><br>");
        builder.append(fillDataTableInHtml(list));
        builder.append("<br><br>");
        builder.append("Obrigado por usar nosso serviço!");

        return builder.toString();
    }

    private String fillDataTableInHtml(List<InvoiceModel> list) {

        String html = " <html><head></head><body>                       "+
                      "     <style>                                     "+
                      "         body {                                  "+
                      "             font-family: Arial, sans-serif;     "+
                      "         }                                       "+
                      "         table {                                 "+
                      "             border: 2px solid black;            "+
                      "              width: 100%;                       "+
                      "          }                                      "+
                      "          th, td {                               "+
                      "              border: 1px solid black;           "+
                      "              padding: 5px;                      "+
                      "          }                                      "+
                      "        </style>                                 "+
                      "      <table>                                    "+
                      "          <tr>                                   "+
                      "            <th>Tipo</th>                        "+
                      "            <th>Descrição</th>                   "+
                      "            <th>Valor</th>                       "+
                      "            <th>Data de pagamento</th>           "+
                      "            <th>Registro</th>                    "+
                      "          </tr>                                  ";

        for (InvoiceModel invoice : list) {
            html += "<tr><td>" + (invoice.getType().equals(InvoiceType.RECIPE)? "Receita" : "Despesa") + "</td>";
            html += "<td>" + invoice.getDescription() + "</td>";
            html += "<td>" + convertFromBigdecimalToString(invoice.getPrice())  + "</td>";
            html += "<td>" + convertLocalDateToString(invoice.getDateRelease()) + "</td>";
            html += "<td>" + convertLocalDateTimeToString(invoice.getRegistration()) + "</td></tr>";
        }

        html += "</table></body></html>";

        return html;
    }

    private String convertFromBigdecimalToString(BigDecimal invoice) {
        Locale brasilLocale = new Locale("pt", "BR");
        NumberFormat formatador = NumberFormat.getCurrencyInstance(brasilLocale);
        return formatador.format(invoice);
    }

    private String convertLocalDateToString(LocalDate invoiceDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return invoiceDate.format(formatter);
    }

    private String convertLocalDateTimeToString(LocalDateTime invoiceDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return invoiceDateTime.format(formatter);
    }
}
