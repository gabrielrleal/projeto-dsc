package io.github.gabrielrleal.projetodsc.repository;

import io.github.gabrielrleal.projetodsc.model.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {

}
