package ies.accesodatos.empleados.services;




import ies.accesodatos.empleados.model.Empleado;
import ies.accesodatos.empleados.repository.EmpleadoQueryRepository;

import java.util.ArrayList;
import java.util.List;

public class EmpleadoQueryService {
    private EmpleadoQueryRepository empleadoQueryRepository;
    public EmpleadoQueryService(EmpleadoQueryRepository empleadoRepository) {
        this.empleadoQueryRepository = empleadoRepository;
    }

    public EmpleadoQueryService() {

    }

    public List<Empleado> getAll() {
        var items=this.empleadoQueryRepository.getAll();
        return items;
    }
    public Empleado getById(int id) {
        var items=this.empleadoQueryRepository.getById(id);
        return items;
    }
}
