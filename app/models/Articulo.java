package models;

import java.util.*;
import javax.persistence.*;
import  play.db.jpa.Blob;
import play.data.validation.Required;
import play.db.jpa.*;

@Entity
public class Articulo extends Model{

    @Required
    public int cantidad;
    public String notas;
    @OneToOne
    public Referencia referencia;

    public Articulo(int cantidad, String notas, Referencia referencia){

        this.cantidad = cantidad;
        this.notas = notas;
        this.referencia = referencia;

    }

}
