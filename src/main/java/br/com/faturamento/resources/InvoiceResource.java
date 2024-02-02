package br.com.faturamento.resources;

import br.com.faturamento.model.InvoiceModel;
import br.com.faturamento.services.InvoiceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoices")
public class InvoiceResource {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping
    public List<InvoiceModel> list() {
        return invoiceService.list();
    }

    @GetMapping("/{code}")
    public ResponseEntity<InvoiceModel> findByCode(@PathVariable Integer code) {
        InvoiceModel invoiceRecovered = invoiceService.findByCode(code);
        return ResponseEntity.ok(invoiceRecovered);
    }

    @PostMapping
    public ResponseEntity<InvoiceModel> save(@Valid @RequestBody InvoiceModel invoice) {
        InvoiceModel invoiceSaved = invoiceService.save(invoice);
        return ResponseEntity.status(HttpStatus.CREATED).body(invoiceSaved);
    }

    @DeleteMapping("/{code}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer code) {
        invoiceService.delete(code);
    }

    @PutMapping("/{code}")
    public ResponseEntity<InvoiceModel> update(@PathVariable Integer code,
                                                  @Valid @RequestBody InvoiceModel invoice) {

        InvoiceModel invoiceUpdated = invoiceService.update(code, invoice);
        return ResponseEntity.ok(invoiceUpdated);
    }
}
