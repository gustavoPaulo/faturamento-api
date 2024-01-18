package br.com.faturamento.repositories.lancamento;

import br.com.faturamento.model.LancamentoModel;
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

public class LancamentoRepositoryImpl implements LancamentoRepositoryQuery {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<LancamentoModel> verifyIfExist(LancamentoModel lancamento) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<LancamentoModel> criteriaQuery = criteriaBuilder.createQuery(LancamentoModel.class);
        Root<LancamentoModel> root = criteriaQuery.from(LancamentoModel.class);

        Predicate[] predicates = createPredicatesOfEqualities(root, lancamento, criteriaBuilder);
        criteriaQuery.select(root).where(predicates);

        TypedQuery<LancamentoModel> typedQuery = entityManager.createQuery(criteriaQuery);

        Optional<LancamentoModel> lancamentoRecuperado = Optional.ofNullable(
                typedQuery.getResultList().isEmpty() ? new LancamentoModel() : typedQuery.getSingleResult());

        return lancamentoRecuperado;
    }

    private Predicate[] createPredicatesOfEqualities(Root<LancamentoModel> root, LancamentoModel lancamento,
                                                     CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = new ArrayList<>();

        predicates.add(criteriaBuilder.equal(root.get("valor"), lancamento.getValor()));
        predicates.add(criteriaBuilder.equal(root.get("descricao"), lancamento.getDescricao()));
        predicates.add(criteriaBuilder.equal(root.get("tipo"), lancamento.getTipo()));
        predicates.add(criteriaBuilder.equal(root.get("dataLancamento"), lancamento.getDataLancamento()));

        return predicates.toArray(new Predicate[predicates.size()]);
    }
}
