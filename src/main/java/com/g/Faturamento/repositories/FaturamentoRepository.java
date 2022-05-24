package com.g.Faturamento.repositories;

import com.g.Faturamento.model.Faturamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface FaturamentoRepository extends JpaRepository<Faturamento, Integer> {

    @Modifying
    @Query(value = "ALTER TABLE faturamento AUTO_INCREMENT = 1", nativeQuery = true)
    void restauraSequence();
}
