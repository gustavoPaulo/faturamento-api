package com.g.Faturamento.resources;

import com.g.Faturamento.dto.FaturamentoDTO;
import com.g.Faturamento.services.FaturamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;

@RestController
@RequestMapping("/faturamentos")
public class FaturamentoResource {

    @Autowired
    private FaturamentoService faturamentoService;

    @GetMapping("/{codigo}")
    public ResponseEntity<FaturamentoDTO> findById(@PathVariable Integer codigo) {
        return ResponseEntity.ok().body(faturamentoService.findById(codigo));
    }

    @PostMapping
    public ResponseEntity<FaturamentoDTO> insert(@RequestBody @Valid FaturamentoDTO faturamentoDTO)
            throws ParseException {
        return ResponseEntity.status(HttpStatus.CREATED).body(faturamentoService.save(faturamentoDTO));
    }
}
