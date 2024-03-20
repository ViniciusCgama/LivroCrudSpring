package br.com.escola.crudLivro.service;

import br.com.escola.crudLivro.model.Livro;
import br.com.escola.crudLivro.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LivroService {

    @Autowired
    private LivroRepository repository;

    public void salvarLivro(Livro livro) {
        repository.save(livro);
    }

}