package com.g.Faturamento.services;

import com.g.Faturamento.dto.FaturamentoDTO;
import com.g.Faturamento.model.Faturamento;
import com.g.Faturamento.model.enums.TipoFaturamento;
import com.g.Faturamento.repositories.FaturamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class FaturamentoService {

    private static final SimpleDateFormat FORMATO_DATA = new SimpleDateFormat("dd/MM/yyyy");
    private static final SimpleDateFormat FORMATO_DATAHORA = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private static final DecimalFormat FORMATO_DINHEIRO = (DecimalFormat) NumberFormat.getCurrencyInstance();

    @Autowired
    private FaturamentoRepository faturamentoRepository;

    public FaturamentoDTO save(@Valid FaturamentoDTO faturamentoDTO) throws ParseException {

        Faturamento faturamentoSalvo = new Faturamento();

        faturamentoSalvo.setTipo(TipoFaturamento.valueOf(faturamentoDTO.getTipo()));
        faturamentoSalvo.setValor(Double.parseDouble(faturamentoDTO.getValor()));
        faturamentoSalvo.setData(FORMATO_DATA.parse(faturamentoDTO.getData()));
        faturamentoSalvo.setLancamento(new Date());
        faturamentoSalvo.setDescricao(faturamentoDTO.getDescricao());

        faturamentoRepository.save(faturamentoSalvo);

        return findById(faturamentoSalvo.getCodigo());
    }

    public FaturamentoDTO findById(Integer codigo) {
        Optional<Faturamento> faturamentoSalvo = faturamentoRepository.findById(codigo);

        faturamentoSalvo.orElseThrow(() -> new NoSuchElementException("Objeto " +
                Faturamento.class.getSimpleName() + " com o ID " + codigo + ", não encontrado!"));

        return forDTO(faturamentoSalvo);
    }

    private FaturamentoDTO forDTO(Optional<Faturamento> faturamentos) {

        FaturamentoDTO faturamentoDTO = new FaturamentoDTO(faturamentos.get().getCodigo(),
                faturamentos.get().getTipo().getDescricao(),
                FORMATO_DINHEIRO.format(faturamentos.get().getValor()),
                FORMATO_DATA.format(faturamentos.get().getData()),
                FORMATO_DATAHORA.format(faturamentos.get().getLancamento()),
                faturamentos.get().getDescricao());

        return faturamentoDTO;
    }
}
