package br.com.billing.faturamento.repositories.invoice;

import br.com.billing.faturamento.model.InvoiceModel;

import java.util.Optional;

public interface InvoiceRepositoryQuery {

    Optional<InvoiceModel> verifyIfExist(InvoiceModel invoice);
}
