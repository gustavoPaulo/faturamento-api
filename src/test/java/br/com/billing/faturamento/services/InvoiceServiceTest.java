package br.com.billing.faturamento.services;

import br.com.billing.faturamento.model.InvoiceModel;
import br.com.billing.faturamento.model.enums.InvoiceType;
import br.com.billing.faturamento.useful.Utility;
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
public class InvoiceServiceTest {

    private static final Logger LOG = Logger.getLogger(InvoiceServiceTest.class.getName());

    private final InvoiceModel invoiceTest = new InvoiceModel(BigDecimal.valueOf(150),
            Utility.TEST_INVOICE_DESCRIPTION, InvoiceType.EXPENSE, LocalDate.now());

    @Autowired
    private InvoiceService invoiceService;

    @AfterEach
    void cleanDataTests() {
        try {
            invoiceService.delete(invoiceTest.getCode());
            LOG.info(Utility.LOG_INFO_REMOVE_SUCCESS);

        } catch (ObjectNotFoundException e) {
            LOG.info(Utility.LOG_INFO_REMOVE_DATA_NOTEXIST);
        }
    }

    @Test
    @DisplayName(Utility.TEST_TITLE_LIST)
    void list() {
        invoiceService.save(invoiceTest);

        List<InvoiceModel> list = invoiceService.list();

        Assert.notEmpty(list, Utility.TEST_LIST_LIST_CANNOT_EMPTY);
    }

    @Test
    @DisplayName(Utility.TEST_TITLE_SAVE)
    void save() {
        InvoiceModel invoice = invoiceService.save(invoiceTest);

        Assert.notNull(invoice, Utility.TEST_SAVE_INVOICE_CANNOT_NULL);
    }

    @Test
    @DisplayName(Utility.TEST_TITLE_FINDBYCODE)
    void findByCode() {
        InvoiceModel invoiceSaved = invoiceService.save(invoiceTest);

        InvoiceModel invoiceRecovered = invoiceService.findByCode(invoiceSaved.getCode());

        Assert.notNull(invoiceRecovered, Utility.TEST_SAVE_INVOICE_CANNOT_NULL);
    }

    @Test
    @DisplayName(Utility.TEST_TITLE_DELETE)
    void delete() {
        InvoiceModel invoice = invoiceService.save(invoiceTest);

        invoiceService.delete(invoice.getCode());

        String messageExpected = Utility.ERROR_INVOICE_NOT_FOUND
                .replace(Utility.MODEL_INVOICE_CODE, Integer.toString(invoice.getCode()));
        String messageFromException = "";

        try {
            invoiceService.findByCode(invoice.getCode());

        } catch (ObjectNotFoundException e) {
            messageFromException = e.getEntityName();
        }

        Assert.isTrue(messageFromException.equals(messageExpected),
        Utility.TEST_DELETE_INVOICE_CANNOT_EXIST);
    }

    @Test
    @DisplayName(Utility.TEST_TITLE_UPDATE)
    void update() {
        InvoiceModel invoice = invoiceService.save(invoiceTest);

        invoice.setPrice(BigDecimal.valueOf(600));
        invoice.setDescription(Utility.TEST_INVOICE_DESCRIPTION_UPDATED);
        invoice.setType(InvoiceType.EXPENSE);

        InvoiceModel invoiceUpdated = invoiceService.update(invoice.getCode(), invoice);

        Assert.isTrue(invoice.getCode().equals(invoiceUpdated.getCode()),
        Utility.TEST_UPDATE_INVOICE_NEEDS_TOBESAME);

        Assert.isTrue(invoiceUpdated.getPrice().compareTo(BigDecimal.valueOf(600)) == 0,
        Utility.TEST_UPDATE_INVOICE_PRICE_NEEDS_TOBESAME_600);

        Assert.isTrue(invoiceUpdated.getType().equals(InvoiceType.RECIPE),
        Utility.TEST_UPDATE_INVOICE_TYPE_NEEDS_TOBESAME);
    }
}
