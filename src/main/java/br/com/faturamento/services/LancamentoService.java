package br.com.faturamento.services;

import br.com.faturamento.model.LancamentoModel;
import br.com.faturamento.repositories.LancamentoRepository;
import br.com.faturamento.services.exceptions.ObjectAlreadyExistsException;
import br.com.faturamento.useful.Utils;
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

    public LancamentoModel salvar(LancamentoModel lancamento) {
        verifyIfExist(lancamento);
        return lancamentoRepository.save(lancamento);
    }

    public LancamentoModel pesquisarPorCodigo(Integer codigo) {
        return lancamentoRepository.findById(codigo).orElseThrow(() ->
                new ObjectNotFoundException(Utils.ERROR_LANCAMENTO_NOT_FOUND
                        .replace("code", Integer.toString(codigo))
                        , LancamentoModel.class));
    }

    public void remover(Integer codigo) {
        pesquisarPorCodigo(codigo);
        lancamentoRepository.deleteById(codigo);
    }

    public LancamentoModel atualizar(Integer codigo, LancamentoModel lancamento) {
        LancamentoModel lancamentoRecuperado = pesquisarPorCodigo(codigo);

        lancamento.setCodigo(lancamentoRecuperado.getCodigo());
        lancamento.setRegistro(lancamentoRecuperado.getRegistro());

        return lancamentoRepository.save(lancamento);
    }

    private void verifyIfExist(LancamentoModel lancamento) {
        Optional<LancamentoModel> lancamentoRecuperado = lancamentoRepository.verifyIfExist(lancamento.getValor(),
                        lancamento.getDescricao(), lancamento.getTipo(), lancamento.getDataLancamento());

        if (lancamentoRecuperado != null && lancamentoRecuperado.isPresent()) {
            throw new ObjectAlreadyExistsException();
        }
    }
}
