package br.com.escola.crudLivro.repository;

import br.com.escola.crudLivro.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {
}
