package models;

import java.util.*;
import java.util.ArrayList;
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
    @OneToMany(mappedBy="articulo", cascade=CascadeType.ALL)
    public List<Color> colores;
    @ManyToOne
    public Categoria categoria;


    public Articulo(int cantidad, Referencia referencia){

        this.cantidad = cantidad;
        this.notas = "";
        this.referencia = referencia;
        this.colores = new ArrayList<Color>();
        this.categoria = null;
    }

}
