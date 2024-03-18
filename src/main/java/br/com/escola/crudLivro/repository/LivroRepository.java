package br.com.escola.crudLivro.repository;

import br.com.escola.crudLivro.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    Optional<Livro> findById(int id);
}
