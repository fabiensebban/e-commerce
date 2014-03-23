package controllers;

import play.*;
import play.mvc.*;
import  play.db.jpa.Blob;

import java.util.*;

import models.*;

public class Application extends Controller {

    public static void index() {
        render();
    }

    public static void addArticulo(int cantidad, String notas, Blob photo ){

        Articulo articulo = new Articulo(cantidad, notas, photo);
        articulo.save();


    }

}