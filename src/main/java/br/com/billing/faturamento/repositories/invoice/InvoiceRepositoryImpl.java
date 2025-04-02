package br.com.billing.faturamento.repositories.invoice;

import br.com.billing.faturamento.model.InvoiceFilterModel;
import br.com.billing.faturamento.model.InvoiceModel;
import br.com.billing.faturamento.useful.Utility;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class InvoiceRepositoryImpl implements InvoiceRepositoryQuery {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<InvoiceModel> verifyIfExist(InvoiceModel invoice) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<InvoiceModel> criteriaQuery = criteriaBuilder.createQuery(InvoiceModel.class);
        Root<InvoiceModel> root = criteriaQuery.from(InvoiceModel.class);

        Predicate[] predicates = createPredicatesOfEqualities(root, invoice, criteriaBuilder);
        criteriaQuery.select(root).where(predicates);

        TypedQuery<InvoiceModel> typedQuery = entityManager.createQuery(criteriaQuery);

        return Optional.ofNullable(typedQuery.getResultList().isEmpty() ? new InvoiceModel()
                : typedQuery.getSingleResult());
    }

    @Override
    public List<InvoiceModel> findByFilter(InvoiceFilterModel filter) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<InvoiceModel> criteria = builder.createQuery(InvoiceModel.class);

        Root<InvoiceModel> root = criteria.from(InvoiceModel.class);

        Predicate[] predicates = createPredicatesByFilter(root, filter, builder);
        criteria.select(root).where(predicates);

        TypedQuery<InvoiceModel> query = entityManager.createQuery(criteria);

        return query.getResultList();
    }

    private Predicate[] createPredicatesOfEqualities(Root<InvoiceModel> root, InvoiceModel invoice,
                                                     CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = new ArrayList<>();

        predicates.add(criteriaBuilder.equal(root.get(Utility.MODEL_INVOICE_PRICE), invoice.getPrice()));
        predicates.add(criteriaBuilder.equal(root.get(Utility.MODEL_INVOICE_DESCRIPTION), invoice.getDescription()));
        predicates.add(criteriaBuilder.equal(root.get(Utility.MODEL_INVOICE_TYPE), invoice.getType()));
        predicates.add(criteriaBuilder.equal(root.get(Utility.MODEL_INVOICE_DATERELEASE), invoice.getDateRelease()));

        return predicates.toArray(new Predicate[predicates.size()]);
    }

    private Predicate[] createPredicatesByFilter(Root<InvoiceModel> root, InvoiceFilterModel filter,
                                                     CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = new ArrayList<>();

        if(Objects.nonNull(filter.getDescription())) {
            predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(root.get(Utility.MODEL_INVOICE_DESCRIPTION))
                    , "%" + filter.getDescription().toLowerCase() + "%"));
        }
        if(filter.getPriceMin() > 0) {
            predicates.add(
                    criteriaBuilder.greaterThanOrEqualTo(root.get(Utility.MODEL_INVOICE_PRICE), filter.getPriceMin()));
        }
        if(filter.getPriceMax() > 0) {
            predicates.add(
                    criteriaBuilder.lessThanOrEqualTo(root.get(Utility.MODEL_INVOICE_PRICE), filter.getPriceMax()));
        }
        if(Objects.nonNull(filter.getType()) && !filter.getType().equals("TODOS")) {
            predicates.add(criteriaBuilder.equal(root.get(Utility.MODEL_INVOICE_TYPE), filter.getType()));
        }
        if (Objects.nonNull(filter.getDateStart())) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(Utility.MODEL_INVOICE_DATERELEASE),
                    stringToLocalDate(filter.getDateStart())));
        }
        if (Objects.nonNull(filter.getDateEnd())) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(Utility.MODEL_INVOICE_DATERELEASE),
                    stringToLocalDate(filter.getDateEnd())));
        }

        return predicates.toArray(new Predicate[predicates.size()]);
    }

    private LocalDate stringToLocalDate(String date) {
        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.ofInstant(new Date(date).toInstant(), ZoneId.systemDefault());
    }
}
