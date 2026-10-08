package br.edu.ifsp.prw3.proj_mecanica.controller;

import br.edu.ifsp.prw3.proj_mecanica.conserto.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("consertos")
public class ConsertoController {

    @Autowired
    private ConsertoRepository repository;

    // POST - Cadastro
    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosEntradaConserto dados, UriComponentsBuilder uriBuilder) {
        var conserto = new Conserto(dados);
        repository.save(conserto);

        var uri = uriBuilder.path("/consertos/{id}").buildAndExpand(conserto.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoConserto(conserto));
    }

    // GET 1: Todos
    @GetMapping
    public ResponseEntity<Page<Conserto>> listarTodos(Pageable paginacao) {
        return ResponseEntity.ok(repository.findAll(paginacao));
    }

    // GET 2: Resumo apenas dos consertos ATIVOS
    @GetMapping("resumo")
    public ResponseEntity<List<DadosListagemConserto>> listarResumo() {
        var lista = repository.findAllByAtivoTrue().stream().map(DadosListagemConserto::new).toList();
        return ResponseEntity.ok(lista);
    }

    // GET 3: Recuperar dados de um único conserto pelo ID
    @GetMapping("/{id}")
    public ResponseEntity getConsertoById(@PathVariable Long id) {
        Optional<Conserto> consertoOptional = repository.findById(id);
        if (consertoOptional.isPresent()) {
            Conserto conserto = consertoOptional.get();
            return ResponseEntity.ok(new DadosDetalhamentoConserto(conserto));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // PUT: Alterar data de saída / mecânico
    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoConserto dados) {
        Conserto conserto = repository.getReferenceById(dados.id());
        conserto.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoConserto(conserto));
    }

    // DELETE: Exclusão lógica (ativo = false)
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        Conserto conserto = repository.getReferenceById(id);
        conserto.excluir();
        return ResponseEntity.noContent().build();
    }
}
