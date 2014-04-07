package models;

import java.util.*;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import  play.db.jpa.Blob;
import play.data.validation.Required;
import play.db.jpa.*;

@Entity
public class Categoria extends Model {

    public String nombre;
    @Required
    public Long IdPadre;
    @Column
    @ElementCollection(targetClass=Long.class)
    public List<Long> IdHijos;
    @OneToMany(mappedBy="categoria", cascade=CascadeType.ALL)
    public List<Articulo> articulos;

    public Categoria (String nombre, Long IdPadre){

        this.nombre = nombre;
        this.IdPadre = IdPadre;
        this.IdHijos = new ArrayList<Long>();
        this.articulos = new ArrayList<Articulo>();
    }

    public Categoria anadirCategoria (String nombre) {

        Categoria categoriaNueva = new Categoria(nombre, this.id).save();
        this.IdHijos.add(categoriaNueva.id);
        this.save();
        return this;

    }
}
