package br.com.faturamento.repositories;

import br.com.faturamento.model.InvoiceModel;
import br.com.faturamento.repositories.invoice.InvoiceRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<InvoiceModel, Integer>, InvoiceRepositoryQuery {

    Optional<InvoiceModel> verifyIfExist(InvoiceModel invoice);
}
