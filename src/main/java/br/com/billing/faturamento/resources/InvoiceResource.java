package br.com.billing.faturamento.resources;

import br.com.billing.faturamento.model.InvoiceFilterModel;
import br.com.billing.faturamento.model.InvoiceModel;
import br.com.billing.faturamento.model.MailModel;
import br.com.billing.faturamento.services.InvoiceService;
import br.com.billing.faturamento.useful.Utility;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(Utility.RESOURCE_REQUEST_MAPPING)
public class InvoiceResource {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping
    public List<InvoiceModel> list() {
        return invoiceService.list();
    }

    @GetMapping(value = Utility.RESOURCE_INVOICE_FILTER, params = "filter")
    public List<InvoiceModel> findByFilter(InvoiceFilterModel filter) {
        return invoiceService.findByFilter(filter);
    }

    @GetMapping(Utility.RESOURCE_INVOICE_CODE)
    public ResponseEntity<InvoiceModel> findByCode(@PathVariable Integer code) {
        InvoiceModel invoiceRecovered = invoiceService.findByCode(code);
        return ResponseEntity.ok(invoiceRecovered);
    }

    @PostMapping
    public ResponseEntity<InvoiceModel> save(@Valid @RequestBody InvoiceModel invoice) {
        InvoiceModel invoiceSaved = invoiceService.save(invoice);
        return ResponseEntity.status(HttpStatus.CREATED).body(invoiceSaved);
    }

    @DeleteMapping(Utility.RESOURCE_INVOICE_CODE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer code) {
        invoiceService.delete(code);
    }

    @PutMapping
    public ResponseEntity<InvoiceModel> update(@Valid @RequestBody InvoiceModel invoice) {
        InvoiceModel invoiceUpdated = invoiceService.update(invoice);
        return ResponseEntity.ok(invoiceUpdated);
    }

    @PostMapping(Utility.RESOURCE_INVOICE_USER_EMAIL)
    public ResponseEntity<MailModel> sendInvoiceByEmail(@Valid @RequestBody List<InvoiceModel> invoices, @PathVariable String userEmail) {
        MailModel mailResponse = invoiceService.sendInvoiceByEmail(invoices, userEmail);
        return ResponseEntity.status(HttpStatus.OK).body(mailResponse);
    }
}
