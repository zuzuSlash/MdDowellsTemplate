package ies.accesodatos.empleados.services;


import ies.accesodatos.commons.services.ACommandService;
import ies.accesodatos.commons.services.Event;
import ies.accesodatos.empleados.model.Empleado;
import ies.accesodatos.empleados.repository.EmpleadoRepository;


public class EmpleadoCommandService extends ACommandService {
    private EmpleadoRepository repository;

public EmpleadoCommandService(EmpleadoRepository empleadoRepository) {
    this.repository = empleadoRepository;
}
public void add(Empleado item) {
    this.repository.add(item);
    this.post("Alta empleado", Event.ACTION.ADD, item);
}
public void add(String nombre, String clave, String correo,
                boolean activo) {
    Empleado empleado = new Empleado();
    empleado.setNombre(nombre);
    empleado.setClave(clave);
    empleado.setCorreo(correo);
    empleado.setActivo(activo);
    this.repository.save(empleado);
    this.post("Alta empleado", Event.ACTION.ADD, empleado);
}
public void delete(Empleado empleado) {
    this.repository.delete(empleado);
    this.post("Alta empleado", Event.ACTION.ADD, empleado);
}
public void remove(int id) {
    Empleado empleado = this.repository.getById(id);
    this.repository.deleteById(id);
    this.post("Borrado empleado", Event.ACTION.DELETE,
            empleado);
}
public void changeCorreo(int id, String value) {
    Empleado c = repository.getById(id);
    if (c != null) {
        c.setCorreo(value);
        this.repository.save(c);
        this.post("Modificar empleado(correo)",
                Event.ACTION.UPDATE, c);
    }
}
public void changeName(int id, String name) {
    Empleado c = repository.getById(id);
    if (c != null) {
        c.setNombre(name);
        this.repository.save(c);
        this.post("Modificar empleado(nombre)",
                Event.ACTION.UPDATE, c);
    }
}
public void changeClave(int id, String value) {
    Empleado c = repository.getById(id);
    if (c != null) {
        c.setClave(value);
        this.repository.save(c);
        this.post("Modificar empleado(clave)",
                Event.ACTION.UPDATE, c);
    }
}
public void changeState(int id, boolean state) {
    Empleado c = repository.getById(id);
    if (c != null) {
        c.setActivo(state);
        this.repository.save(c);
        this.post("Modificar empleado (estado)",
                Event.ACTION.UPDATE, c);
    }
}
public void changeAll(int id, String name, String correo, String
        clave, Boolean state) {
    Empleado c = repository.getById(id);
    if (c != null) {
        c.setNombre(name);
        c.setCorreo(correo);
        c.setClave(clave);
        c.setActivo(state);
        this.repository.save(c);
        this.post("Modificar empleado (total)",
                Event.ACTION.UPDATE, c);
    }
}
public void update(Empleado empleado) {
    this.repository.save(empleado);
    this.post("Modificar empleado (total)", Event.ACTION.UPDATE,
            empleado);
}
}