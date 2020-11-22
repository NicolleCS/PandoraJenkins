package br.com.pandora.projetopandora.service;

import br.com.pandora.projetopandora.data.model.CategoriaServico;
import br.com.pandora.projetopandora.repository.CategoriaServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServicoService {

    @Autowired
    private CategoriaServicoRepository categoriaServicoRepository;

    public Optional<CategoriaServico> obterPorId(Integer id){
        return categoriaServicoRepository.getByIdCategoria(id);
    }

    public List<CategoriaServico> obterTodos(){
        return categoriaServicoRepository.findAll();
    }
}
