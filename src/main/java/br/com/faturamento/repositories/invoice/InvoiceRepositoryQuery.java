package br.com.faturamento.repositories.invoice;

import br.com.faturamento.model.InvoiceModel;

import java.util.Optional;

public interface InvoiceRepositoryQuery {

    Optional<InvoiceModel> verifyIfExist(InvoiceModel invoice);
}
