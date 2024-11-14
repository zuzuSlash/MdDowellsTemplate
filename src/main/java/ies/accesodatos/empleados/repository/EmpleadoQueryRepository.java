package ies.accesodatos.empleados.repository;

import ies.accesodatos.commons.repositories.AQueryRepository;
import ies.accesodatos.empleados.DAO.EmpleadoDAO;
import ies.accesodatos.empleados.model.Empleado;

import java.util.List;

public class EmpleadoQueryRepository extends AQueryRepository<Empleado,Integer> {
    private EmpleadoDAO itemDao;

    public EmpleadoQueryRepository() {
        super();
    }

    public EmpleadoQueryRepository(EmpleadoDAO empleadoDao) {
        super();
        //this.setDao(itemDao);
        this.itemDao = empleadoDao;
    }

    public void setDao(EmpleadoDAO itemDao) {
        this.itemDao = itemDao;
    }

    public EmpleadoDAO getEmpleadoDao() {
        return itemDao;
    }

    public void setEmpleadoDao(EmpleadoDAO empleadoDao) {
        this.itemDao = empleadoDao;
    }

    public EmpleadoDAO getitemDao() {
        return itemDao;
    }

    @Override
    public Empleado getById(Integer id) {
        var empleado = itemDao.getById(id);
        Empleado empleadoDTO = new Empleado();
        empleadoDTO.setId(empleado.getId());
        empleadoDTO.setNombre(empleado.getNombre());
        empleadoDTO.setActivo(empleado.isActivo());
        empleadoDTO.setCorreo(empleado.getCorreo());
        return empleadoDTO;
    }

    @Override
    public List<Empleado> getAll() {
        var categorias = this.itemDao.getAll();
        var items = categorias.stream().map(c -> {
            Empleado dto = new
                    Empleado(c.getId(), c.getNombre(), c.isActivo(), c.getCorreo(), c.getClave());
            return dto;
        }).toList();
        return items;
    }
}