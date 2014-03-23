package models;

import java.util.*;
import javax.persistence.*;
import  play.db.jpa.Blob;
import play.data.validation.Required;
import play.db.jpa.*;

@Entity
public class Referencia extends Model{

    @Required
    public String nombre;
    @Required
    public String dibujo;
    @Required
    public String composicion;
    public String largo;
    public String PesoPorTamaño;
    @OneToOne
    public Articulo articulo;

    public Referencia (String nombre, String dibujo, String composicion){

        this.nombre = nombre;
        this.dibujo = dibujo;
        this.composicion = composicion;

    }
}
