package br.com.rafael.api.specification;

import br.com.rafael.api.model.Conta;
import br.com.rafael.api.model.Transacao;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SpecificationTransacao {

    public static Specification<Transacao> filtra(Conta conta, LocalDateTime dataInicial, LocalDateTime dataFinal) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(criteriaBuilder.equal(root.get("idConta"), conta));

            if (dataInicial != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("dataTransacao"), dataInicial));
            }
            if (dataFinal != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("dataTransacao"), dataFinal));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
