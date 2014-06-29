package models;

import javax.persistence.Entity;
import java.util.*;
import javax.persistence.*;
import  play.db.jpa.Blob;
import play.data.validation.Required;
import play.db.jpa.*;

@Entity
public class Color extends Model{

    @Required
    public String nombre;
    public Blob foto;
    @Required
    public int cantidad;
    @ManyToOne
    @Required
    public Articulo articulo;

    public Color(String nombre, int cantidad){

        this.nombre = nombre;
        this.cantidad = cantidad;
        this.foto = null;
    }



}
