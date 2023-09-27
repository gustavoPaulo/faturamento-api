package br.com.faturamento.resources;

import br.com.faturamento.model.LancamentoModel;
import br.com.faturamento.services.LancamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {

    @Autowired
    private LancamentoService lancamentoService;

    @GetMapping
    public List<LancamentoModel> listar() {
        return lancamentoService.listar();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<LancamentoModel> pesquisarPorCodigo(@PathVariable Integer codigo) {
        LancamentoModel lancamentoRecuperado = lancamentoService.pesquisarPorCodigo(codigo);
        return ResponseEntity.ok(lancamentoRecuperado);
    }

    @PostMapping
    public ResponseEntity<LancamentoModel> salvar(@Valid @RequestBody LancamentoModel lancamento) {
        LancamentoModel lancamentoSalvo = lancamentoService.salvar(lancamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSalvo);
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Integer codigo) {
        lancamentoService.remover(codigo);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<LancamentoModel> atualizar(@PathVariable Integer codigo,
            @Valid @RequestBody LancamentoModel lancamento) {

        LancamentoModel lancamentoAtualizado = lancamentoService.atualizar(codigo, lancamento);
        return ResponseEntity.ok(lancamentoAtualizado);
    }
}
