package ies.accesodatos.categorias.services;

import ies.accesodatos.categorias.model.Categoria;
import ies.accesodatos.commons.services.ACommandService;
import ies.accesodatos.commons.services.Event;
import ies.accesodatos.categorias.repository.CategoriaRepository;

public class CategoriaCommandService extends ACommandService {
    private CategoriaRepository repository;

    public CategoriaCommandService(CategoriaRepository categoriaRepository) {
        this.repository = categoriaRepository;
    }

    public void add(Categoria item) {
        this.repository.add(item);
        this.post("Alta categoría", Event.ACTION.ADD, item);
    }

    public void add(String nombre, String descripcion, String imagepath, boolean activo) {

        imagepath = "./src/main/resources/images/foodAdd.png";

        Categoria categoria = new Categoria();
        categoria.setNombre(nombre);
        categoria.setDescripcion(descripcion);
        categoria.setImg_src(imagepath);
        categoria.setActivo(activo);
        this.repository.save(categoria);
        this.post("Alta categoría", Event.ACTION.ADD, categoria);
    }

    public void delete(Categoria categoria) {
        this.repository.delete(categoria);
        this.post("Borrado categoría", Event.ACTION.DELETE, categoria);
    }

    public void remove(int id) {
        Categoria categoria = this.repository.getById(id);
        this.repository.deleteById(id);
        this.post("Borrado categoría", Event.ACTION.DELETE, categoria);
    }

    public void changeNombre(int id, String value) {
        Categoria c = repository.getById(id);
        if (c != null) {
            c.setNombre(value);
            this.repository.save(c);
            this.post("Modificar categoría (nombre)", Event.ACTION.UPDATE, c);
        }
    }

    public void changeDescripcion(int id, String value) {
        Categoria c = repository.getById(id);
        if (c != null) {
            c.setDescripcion(value);
            this.repository.save(c);
            this.post("Modificar categoría (descripción)", Event.ACTION.UPDATE, c);
        }
    }

    public void changeImagePath(int id, String value) {
        Categoria c = repository.getById(id);
        if (c != null) {
            c.setImg_src(value);
            this.repository.save(c);
            this.post("Modificar categoría (imagen)", Event.ACTION.UPDATE, c);
        }
    }

    public void changeState(int id, boolean state) {
        Categoria c = repository.getById(id);
        if (c != null) {
            c.setActivo(state);
            this.repository.save(c);
            this.post("Modificar categoría (estado)", Event.ACTION.UPDATE, c);
        }
    }

    public void changeAll(int id, String nombre, String descripcion, String imagepath, Boolean state) {
        Categoria c = repository.getById(id);
        if (c != null) {
            c.setNombre(nombre);
            c.setDescripcion(descripcion);
            c.setImg_src(imagepath);
            c.setActivo(state);
            this.repository.save(c);
            this.post("Modificar categoría (total)", Event.ACTION.UPDATE, c);
        }
    }

    public void update(Categoria categoria) {
        this.repository.save(categoria);
        this.post("Modificar categoría (total)", Event.ACTION.UPDATE, categoria);
    }
}
