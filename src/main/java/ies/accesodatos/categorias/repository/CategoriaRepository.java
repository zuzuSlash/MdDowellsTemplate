package ies.accesodatos.categorias.repository;

import ies.accesodatos.categorias.dao.CategoriaDao;
import ies.accesodatos.categorias.model.Categoria;
import ies.accesodatos.commons.repositories.AWriteRepository;

public class CategoriaRepository extends AWriteRepository<Categoria, Integer> {
    private CategoriaDao itemDao;

    public CategoriaRepository() {
        super();
    }

    public CategoriaRepository(CategoriaDao categoriaDao) {
        super();
        this.setDao(categoriaDao);
    }

    public void setDao(CategoriaDao itemDao) {
        this.itemDao = itemDao;
    }

    public CategoriaDao getCategoriaDao() {
        return itemDao;
    }

    @Override
    public void save(Categoria item) {
        if (item.getId() == -1) {
            this.add(item);
        } else {
            this.update(item);
        }
    }

    @Override
    public void add(Categoria item) {
        this.itemDao.insert(item);
    }

    @Override
    public void update(Categoria item) {
        this.itemDao.update(item);
    }

    @Override
    public void delete(Categoria item) {
        this.itemDao.delete(item);
    }

    @Override
    public void deleteById(Integer id) {
        this.itemDao.deleteById(id);
    }

    @Override
    public Categoria getById(Integer id) {
        return this.itemDao.getById(id);
    }
}
