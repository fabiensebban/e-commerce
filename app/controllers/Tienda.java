package controllers;

import play.*;
import play.mvc.*;
import  play.db.jpa.Blob;

import java.util.*;

import models.*;


public class Tienda extends Controller {

    public static void catalogo() {
        render();
    }

    public static void producto(Long idProducto) {
        List<Articulo> novedades = new ArrayList<Articulo>();
        novedades = Articulo.find("order by fecha_creacion desc").fetch(4);
        render(novedades);
    }
}

