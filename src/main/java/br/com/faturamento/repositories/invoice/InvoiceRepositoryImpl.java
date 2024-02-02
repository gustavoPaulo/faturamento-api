package br.com.faturamento.repositories.invoice;

import br.com.faturamento.model.InvoiceModel;
import br.com.faturamento.useful.Utility;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    private Predicate[] createPredicatesOfEqualities(Root<InvoiceModel> root, InvoiceModel invoice,
                                                     CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = new ArrayList<>();

        predicates.add(criteriaBuilder.equal(root.get(Utility.MODEL_INVOICE_PRICE), invoice.getPrice()));
        predicates.add(criteriaBuilder.equal(root.get(Utility.MODEL_INVOICE_DESCRIPTION), invoice.getDescription()));
        predicates.add(criteriaBuilder.equal(root.get(Utility.MODEL_INVOICE_TYPE), invoice.getType()));
        predicates.add(criteriaBuilder.equal(root.get(Utility.MODEL_INVOICE_DATERELEASE), invoice.getDateRelease()));

        return predicates.toArray(new Predicate[predicates.size()]);
    }
}
