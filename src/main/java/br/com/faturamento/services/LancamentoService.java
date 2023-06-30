package br.com.faturamento.services;

import br.com.faturamento.model.LancamentoModel;
import br.com.faturamento.repositories.LancamentoRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public LancamentoModel pesquisarPorCodigo(Integer codigo) {
        return lancamentoRepository.findById(codigo).orElseThrow(() ->
                new ObjectNotFoundException("Lançamento com código" + codigo + ", não encontrado!"
                        , LancamentoModel.class));
    }

    public void remover(Integer codigo) {
        pesquisarPorCodigo(codigo);
        lancamentoRepository.deleteById(codigo);
    }

    public LancamentoModel atualizar(Integer codigo, LancamentoModel lancamento) {
        LancamentoModel lancamentoRecuperado = pesquisarPorCodigo(codigo);

        BeanUtils.copyProperties(lancamento, lancamentoRecuperado, "codigo");
        return lancamentoRepository.save(lancamentoRecuperado);
    }
}
