package ies.accesodatos.categorias.services;



import ies.accesodatos.categorias.model.dto.CategoriaAgregateDTO;
import ies.accesodatos.categorias.repository.CategoriaQueryRepository;
import ies.accesodatos.empleados.repository.EmpleadoQueryRepository;

import java.util.ArrayList;
import java.util.List;

public class CategoriaQueryService   {
    private CategoriaQueryRepository categoriaRepository;
    public CategoriaQueryService(CategoriaQueryRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }
    public List<CategoriaAgregateDTO> getAll() {
        var categorias = this.categoriaRepository.getAll();
        return categorias;
    }
    public  CategoriaAgregateDTO getById(int id) {
        var items=new CategoriaAgregateDTO();
        return items;
    }
}
