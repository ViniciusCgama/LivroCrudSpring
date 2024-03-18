package br.com.escola.crudLivro.controller;

import br.com.escola.crudLivro.LivroRegistroRequest;
import br.com.escola.crudLivro.model.Livro;
import br.com.escola.crudLivro.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api/v1/livros")
public class LivroController {

    @Autowired
    private LivroRepository repository;

    @GetMapping
    public ResponseEntity<List<Livro>> listAllLivros() {
        List<Livro> livros = repository.findAll();
        return ResponseEntity.ok(livros);
    }

    @PostMapping("/registrarLivro")
    @Transactional
    public ResponseEntity<String> registrarLivro(@RequestBody LivroRegistroRequest livroRequest) {
        Livro livro = new Livro();

        livro.setTitulo(livroRequest.getTitulo());
        livro.setAutor(livroRequest.getAutor());
        livro.setDescricao(livroRequest.getDescricao());
        livro.setGenero(livroRequest.getGenero());
        livro.setEditora(livroRequest.getEditora());
        livro.setAnoLancamento(livroRequest.getAnoLancamento());

        repository.save(livro);

        return ResponseEntity.ok("Livro registrado com sucesso");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLivro(@PathVariable Long id) {
        Optional<Livro> livroOptional = repository.findById(id);
        if (livroOptional.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.ok("Livro excluído com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livro não encontrado");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editLivro(@PathVariable int id, @RequestBody LivroRegistroRequest livroRequest) {
        Optional<Livro> livroOptional = repository.findById(id);
        if (livroOptional.isPresent()) {
            Livro livro = livroOptional.get();

            if (livroRequest.getTitulo() != null) {
                livro.setTitulo(livroRequest.getTitulo());
            }
            if (livroRequest.getAutor() != null) {
                livro.setAutor(livroRequest.getAutor());
            }
            if (livroRequest.getDescricao() != null) {
                livro.setDescricao(livroRequest.getDescricao());
            }
            if (livroRequest.getGenero() != null) {
                livro.setGenero(livroRequest.getGenero());
            }
            if (livroRequest.getEditora() != null) {
                livro.setEditora(livroRequest.getEditora());
            }
            if (livroRequest.getAnoLancamento() != 0) {
                livro.setAnoLancamento(livroRequest.getAnoLancamento());
            }

            repository.save(livro);
            return ResponseEntity.ok("Livro atualizado com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livro não encontrado");
        }
    }
}
