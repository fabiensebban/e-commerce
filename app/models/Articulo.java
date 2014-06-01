package models;

import java.util.*;
import java.util.ArrayList;
import javax.persistence.*;
import  play.db.jpa.Blob;
import play.data.validation.Required;
import play.db.jpa.*;

@Entity
public class Articulo extends Model{

    public String notas;
    @OneToOne
    public Referencia referencia;
    @OneToMany(mappedBy="articulo", cascade=CascadeType.ALL)
    public List<Color> colores;
    @ManyToOne
    public Categoria categoria;
    public Date fecha_creacion;


    public Articulo(Referencia referencia){

        this.notas = "";
        this.referencia = referencia;
        this.colores = new ArrayList<Color>();
        this.categoria = null;
        this.fecha_creacion = new Date();
    }

}
