package ies.accesodatos.categorias.services;


import ies.accesodatos.categorias.model.Categoria;
import ies.accesodatos.commons.services.ACommandService;
import ies.accesodatos.commons.services.Event;

public class CategoriaCommandService extends ACommandService {


    public CategoriaCommandService() {
        super();

    }

    public void add(Categoria item) {

        this.post("Alta categoría", Event.ACTION.ADD,item);

    }

    public void add(String nombre, String descripcion, String imagepath, boolean activo) {
        Categoria item = new Categoria();

        this.post("Alta categoría", Event.ACTION.ADD,item);

    }

    public void remove(Categoria item) {



        this.post("Borrar categoría", Event.ACTION.DELETE,item);

      }

    public void remove(int id) {

        this.post("Borrar categoría", Event.ACTION.DELETE,"vacio");

    }





    public Categoria getById(int id) {
        return new Categoria();
    }

    public void update(Categoria item) {

        this.post("Modificar categoría", Event.ACTION.UPDATE,item);
    }

}
