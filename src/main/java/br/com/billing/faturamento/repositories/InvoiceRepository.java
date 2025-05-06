package br.com.billing.faturamento.repositories;

import br.com.billing.faturamento.model.DashboardModel;
import br.com.billing.faturamento.model.InvoiceFilterModel;
import br.com.billing.faturamento.model.InvoiceModel;
import br.com.billing.faturamento.repositories.invoice.InvoiceRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<InvoiceModel, Integer>, InvoiceRepositoryQuery {

    Optional<InvoiceModel> verifyIfExist(InvoiceModel invoice);

    List<InvoiceModel> findByFilter(InvoiceFilterModel filter);

    @Query(value = "SELECT i.price FROM InvoiceModel i WHERE i.type = 'EXPENSE' " +
            "AND i.dateRelease BETWEEN ?1 AND ?2 ORDER BY i.price DESC LIMIT 1")
    BigDecimal findExpenseMaxPrice(LocalDate startDate, LocalDate endDate);

    @Query(value = "SELECT i.price FROM InvoiceModel i WHERE i.type = 'EXPENSE' " +
            "AND i.dateRelease BETWEEN ?1 AND ?2 ORDER BY i.price ASC LIMIT 1")
    BigDecimal findExpenseMinPrice(LocalDate startDate, LocalDate endDate);

    @Query(value = "SELECT i.price FROM InvoiceModel i WHERE i.type = 'RECIPE' " +
            "AND i.dateRelease BETWEEN ?1 AND ?2 ORDER BY i.price DESC LIMIT 1")
    BigDecimal findRecipeMaxPrice(LocalDate startDate, LocalDate endDate);

    @Query(value = "SELECT i.price FROM InvoiceModel i WHERE i.type = 'RECIPE' " +
            "AND i.dateRelease BETWEEN ?1 AND ?2 ORDER BY i.price ASC LIMIT 1")
    BigDecimal findRecipeMinPrice(LocalDate startDate, LocalDate endDate);
}
