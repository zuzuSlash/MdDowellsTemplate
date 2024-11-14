package ies.accesodatos.empleados.services;

import ies.accesodatos.commons.repositories.AWriteRepository;
import ies.accesodatos.empleados.DAO.EmpleadoDAO;
import ies.accesodatos.empleados.model.Empleado;

public class EmpleadoRepository extends AWriteRepository<Empleado,Integer> {
    private EmpleadoDAO itemDao;

    public EmpleadoRepository() {
        super();
    }

    public EmpleadoRepository(EmpleadoDAO itemDao) {
        super();
        this.setDao(itemDao);
    }

    public void setDao(EmpleadoDAO itemDao) {
        this.itemDao = itemDao;
    }

    public EmpleadoDAO getiDao() {
        return itemDao;
    }

    @Override
    public void save(Empleado item) {
        if (item.getId() == -1) {
            this.add(item);
        } else {
            this.update(item);
        }
    }

    @Override
    public void add(Empleado item) {
        this.itemDao.insert(item);
    }

    @Override
    public void update(Empleado item) {
        this.itemDao.update(item);
    }

    @Override
    public void delete(Empleado item) {
        this.itemDao.delete(item);
    }

    @Override
    public void deleteById(Integer id) {
        this.itemDao.deleteById(id);
    }

    @Override
    public Empleado getById(Integer id) {
        return this.itemDao.getById(id);
    }
}