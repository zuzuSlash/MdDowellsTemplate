package ies.accesodatos.categorias.services;



import ies.accesodatos.categorias.model.dto.CategoriaAgregateDTO;

import java.util.ArrayList;
import java.util.List;

public class CategoriaQueryService   {

    public CategoriaQueryService() {

    }
    public List<CategoriaAgregateDTO> getAll() {

        return new ArrayList<CategoriaAgregateDTO>();
    }
    public  CategoriaAgregateDTO getById(int id) {
        var items=new CategoriaAgregateDTO();
        return items;
    }
}
