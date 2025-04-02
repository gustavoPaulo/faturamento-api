package br.com.billing.faturamento.repositories;

import br.com.billing.faturamento.model.InvoiceFilterModel;
import br.com.billing.faturamento.model.InvoiceModel;
import br.com.billing.faturamento.repositories.invoice.InvoiceRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<InvoiceModel, Integer>, InvoiceRepositoryQuery {

    Optional<InvoiceModel> verifyIfExist(InvoiceModel invoice);

    List<InvoiceModel> findByFilter(InvoiceFilterModel filter);
}
