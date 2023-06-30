package br.com.faturamento.resources;

import br.com.faturamento.model.LancamentoModel;
import br.com.faturamento.services.LancamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {

    @Autowired
    private LancamentoService lancamentoService;

    @GetMapping
    public List<LancamentoModel> listar() {
        return this.lancamentoService.listar();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<LancamentoModel> pesquisarPorCodigo(@PathVariable Integer codigo) {
        Optional<LancamentoModel> lancamentoRecuperado = lancamentoService.pesquisarPorCodigo(codigo);
        return lancamentoRecuperado.isPresent() ? ResponseEntity.ok(lancamentoRecuperado.get())
                : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<LancamentoModel> salvar(@Validated @RequestBody LancamentoModel lacamento) {
        LancamentoModel lancamentoSalvo = lancamentoService.salvar(lacamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSalvo);
    }
}
