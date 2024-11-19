package ies.accesodatos.categorias.repository;

import ies.accesodatos.categorias.dao.CategoriaDao;
import ies.accesodatos.categorias.model.Categoria;
import ies.accesodatos.categorias.model.dto.CategoriaAgregateDTO;
import ies.accesodatos.commons.repositories.AQueryRepository;

import java.util.List;

public class CategoriaQueryRepository extends AQueryRepository<CategoriaAgregateDTO, Integer> {
    private CategoriaDao itemDao;

    public CategoriaQueryRepository() {
        super();
    }

    public CategoriaQueryRepository(CategoriaDao categoriaDao) {
        super();
        this.itemDao = categoriaDao;
    }

    public void setDao(CategoriaDao itemDao) {
        this.itemDao = itemDao;
    }

    public CategoriaDao getCategoriaDao() {
        return itemDao;
    }

    @Override
    public CategoriaAgregateDTO getById(Integer id) {
        var categoria = itemDao.getById(id);
        CategoriaAgregateDTO categoriaDTO = new CategoriaAgregateDTO();
        categoriaDTO.setId(categoria.getId());
        categoriaDTO.setNombre(categoria.getNombre());
        categoriaDTO.setDescripcion(categoria.getDescripcion());
        categoriaDTO.setActivo(categoria.isActivo());
        return categoriaDTO;
    }

    @Override
    public List<CategoriaAgregateDTO> getAll() {
        var categorias = this.itemDao.getAll();
        return categorias.stream().map(c -> {
            CategoriaAgregateDTO dto = new CategoriaAgregateDTO(c.getId(), c.getNombre(), c.getDescripcion(), "C:\\Users\\Zzz\\Desktop\\Trabajos\\MdDowellsTemplate\\src\\main\\resources\\images\\foodAdd.png", c.isActivo());
            return dto;
        }).toList();
    }
}

