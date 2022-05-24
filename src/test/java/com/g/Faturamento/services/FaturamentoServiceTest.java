package com.g.Faturamento.services;

import com.g.Faturamento.dto.FaturamentoDTO;
import com.g.Faturamento.model.enums.TipoFaturamento;
import com.g.Faturamento.repositories.FaturamentoRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;

@SpringBootTest
public class FaturamentoServiceTest {

    private static final Integer codigoFaturamento = 1;

    private final FaturamentoDTO faturamentoDTO = new FaturamentoDTO(codigoFaturamento,
            TipoFaturamento.RECEITA.toString(), "3665.81", "01/01/2022",
            "05/01/2022 15:35:01", "Pagamento salárial - Mês 5");

    @Autowired
    private FaturamentoService faturamentoService;

    @Autowired
    private FaturamentoRepository faturamentoRepository;

    @Test
    void save() throws ParseException {
        restauraBase();

        FaturamentoDTO faturamentoSalvo = faturamentoService.save(faturamentoDTO);

        Assert.assertEquals(faturamentoSalvo.getCodigo(), faturamentoDTO.getCodigo());
        Assert.assertEquals(faturamentoSalvo.getData(), faturamentoDTO.getData());
    }

    @Test
    void findById() throws ParseException {
        restauraBase();

        faturamentoService.save(faturamentoDTO);

        FaturamentoDTO faturamentoSalvo = faturamentoService.findById(codigoFaturamento);

        Assert.assertTrue(faturamentoSalvo.getCodigo() == codigoFaturamento);
    }

    private void restauraBase() {
        faturamentoRepository.deleteAll();
        faturamentoRepository.restauraSequence();
    }
}
