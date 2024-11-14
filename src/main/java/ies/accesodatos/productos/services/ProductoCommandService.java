package ies.accesodatos.productos.services;


import ies.accesodatos.commons.services.ACommandService;
import ies.accesodatos.commons.services.Event;
import ies.accesodatos.productos.model.Producto;

public class ProductoCommandService extends ACommandService {

    public ProductoCommandService() {
        super();

    }
    public void add(Producto item) {

            this.getEventBus().post(new Event("Alta producto", Event.ACTION.ADD, item));
    }
    public void add(String nombre,String descripcion,String imagepath,boolean activo,int categoriaId,float precio){

        this.post("Alta producto", Event.ACTION.ADD, "nuevo");
    }
    public void delete(Producto item) {

        this.post("Borrado producto", Event.ACTION.DELETE, item);
    }
    public void delete(int id){

        this.post("Borrado producto", Event.ACTION.DELETE, "nuevo");
    }

    public void update(Producto item){
        this.post("Modificado producto", Event.ACTION.DELETE, item);
    }

}
