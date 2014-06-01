package controllers;

//import com.intellij.packaging.impl.artifacts.ArtifactUtil;
import play.*;
import play.mvc.*;
import  play.db.jpa.Blob;

import java.util.*;

import models.*;

public class Application extends Controller {

    public static void index() {
        List<Articulo> novedades = Articulo.find("order by fecha_creacion asc").fetch();

        System.out.println("Novedad 1: " + novedades.get(0).referencia.nombre);
        render(novedades);
    }
    public static void testIndex() {
        List<Articulo> novedades = Articulo.find("order by fecha_creacion asc").fetch();
        //System.out.println("Novedad 1: " + novedades.get(0).referencia.nombre);
        render(novedades);
    }

    public static void addArticulo(int cantidad, Referencia referencia){

        Articulo articulo = new Articulo(referencia);
        articulo.save();
    }

    public static void fotoColor(long id) {
        final Color color = Color.findById(id);
        notFoundIfNull(color);
        response.setContentTypeIfNotSet(color.foto.type());
        renderBinary(color.foto.get());
    }

    //Comprueba si el articulo tiene un segundo color asignado
    public boolean existeOtroColor(Long id){
        Articulo articulo = Articulo.findById(id);
        Color tmpColor;
        try{
            tmpColor = articulo.colores.get(1);
            return true;
        }
        catch (NullPointerException e){
            return false;
        }

    }

}