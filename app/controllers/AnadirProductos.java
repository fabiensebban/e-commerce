package controllers;

import com.sun.mail.handlers.message_rfc822;
import play.*;
import play.mvc.*;
import play.db.jpa.Blob;

import java.util.*;

import models.*;

public class AnadirProductos extends Controller {


    public static void index() {
        render();
    }

    public static void anadir() {
        if (session.get("user") == null) AnadirProductos.index();

        List categorias = Categoria.findAll();
        render(categorias);
    }

    public static void guardarProducto(
            String nombre_referencia,
            String dibujo,
            String composion,
            String largo,
            String pesoportamano
    ) {
        if (session.get("user") == null) {
            AnadirProductos.index();
        } else {


        }


    }

    public static void logeo(String username, String password) {

        System.out.println("username: " + username);
        System.out.println("password: " + password);
        if (username.equals("distribsud") && password.equals("rony")) {
            session.put("user", username);
            AnadirProductos.anadir();
        } else {
            AnadirProductos.index();
        }
    }
    /*

    ***********************************************************
    Posible implementacion para obtener las categorias via ajax
    ***********************************************************

    public static void ajaxCategoria() {

        String inicioCategoria = params.get("inicioCat");
        List<Categoria> categorias = Categoria.find("byNombreLike", inicioCategoria + "%").fetch();

        int i = 0;
        String message = "";
        boolean continu = true;

        while (continu) {
            try {
                message = message + "<option value=\"" + categorias.get(i).nombre + "\">";
                i++;
            } catch (IndexOutOfBoundsException e) {
                continu = false;
            }

            System.out.println("valor de i: " + i);

        }


        renderText(message);
    } */
}
