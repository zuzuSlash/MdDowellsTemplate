package ies.accesodatos.productos.services;



import ies.accesodatos.productos.model.dto.ProductoAgregateDTO;


import java.util.ArrayList;
import java.util.List;

public class ProductoQueryService   {

    public ProductoQueryService() {

    }
    public List<ProductoAgregateDTO> getAll() {

        return new ArrayList<>();
    }
    public ProductoAgregateDTO getById(int id) {

        return new ProductoAgregateDTO();
    }
}
