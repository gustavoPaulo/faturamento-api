package br.com.faturamento.services;

import br.com.faturamento.model.LancamentoModel;
import br.com.faturamento.repositories.LancamentoRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LancamentoService {

    @Autowired
    private LancamentoRepository lancamentoRepository;

    public List<LancamentoModel> listar() {
        return lancamentoRepository.findAll();
    }

    public LancamentoModel salvar(LancamentoModel lacamento) {
        return lancamentoRepository.save(lacamento);
    }

    public Optional<LancamentoModel> pesquisarPorCodigo(Integer codigo) {
        Optional<LancamentoModel> lancamentoRecuperado = lancamentoRepository.findById(codigo);
        return Optional.ofNullable(lancamentoRecuperado.orElseThrow(() ->
                new ObjectNotFoundException("Lançamento com código " + codigo
                        + ", não encontrado!", LancamentoModel.class)));
    }
}
