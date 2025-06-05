package br.com.billing.faturamento.services;

import br.com.billing.faturamento.model.InvoiceFilterModel;
import br.com.billing.faturamento.model.InvoiceModel;
import br.com.billing.faturamento.model.MailModel;
import br.com.billing.faturamento.repositories.InvoiceRepository;
import br.com.billing.faturamento.services.exceptions.ObjectAlreadyExistsException;
import br.com.billing.faturamento.useful.Utility;
import jakarta.validation.Valid;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private MailService mailService;

    public List<InvoiceModel> list() {
        return invoiceRepository.findAll();
    }

    public InvoiceModel save(InvoiceModel invoice) {
        verifyIfExist(invoice);

        invoice.setRegistration(LocalDateTime.now());

        return invoiceRepository.save(invoice);
    }

    public InvoiceModel findByCode(Integer code) {
        return invoiceRepository.findById(code).orElseThrow(() ->
                new ObjectNotFoundException(Utility.ERROR_INVOICE_NOT_FOUND
                        .replace(Utility.MODEL_INVOICE_CODE, Integer.toString(code))
                        , InvoiceModel.class));
    }

    public void delete(Integer code) {
        findByCode(code);
        invoiceRepository.deleteById(code);
    }

    public InvoiceModel update(InvoiceModel invoice) {
        InvoiceModel invoiceRecovered = findByCode(invoice.getCode());

        BeanUtils.copyProperties(invoice, invoiceRecovered, "code");
        return this.invoiceRepository.save(invoiceRecovered);
    }

    public List<InvoiceModel> findByFilter(InvoiceFilterModel filter) {
        return invoiceRepository.findByFilter(filter);
    }

    private void verifyIfExist(InvoiceModel invoice) {
        Optional<InvoiceModel> invoiceRecovered = invoiceRepository.verifyIfExist(invoice);

        if (Objects.nonNull(invoiceRecovered.get().getCode()) && invoiceRecovered.isPresent()) {
            throw new ObjectAlreadyExistsException();
        }
    }

    public MailModel sendInvoiceByEmail(@Valid InvoiceModel invoice, String userEmail) {
        return mailService.sendEmail(invoice, userEmail);
    }
}
