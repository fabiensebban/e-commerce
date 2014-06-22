package controllers;

import com.sun.mail.handlers.message_rfc822;
import play.*;
import play.data.validation.Required;
import play.data.validation.Valid;
import play.data.validation.Error;
import play.data.validation.Validation;
import play.mvc.*;
import play.db.jpa.Blob;

import java.util.*;

import models.*;

public class AnadirProductos extends Controller {


    public static void index() {

        if(session.get("user") == null){
        render();
        }

        else {
            AnadirProductos.anadir();
        }
    }

    public static void anadir() {
        if (session.get("user") == null) AnadirProductos.index();

        List<Categoria> categorias = new ArrayList<Categoria>();
        List<Categoria> renderedCategorias = new ArrayList<Categoria>();

        categorias = Categoria.findAll();
        int i = 0;

        while(i < categorias.size()){

            try{
                categorias.get(i).IdHijos.get(0);
                System.out.println(""+categorias.get(i).IdHijos.get(0));
            }
            catch(IndexOutOfBoundsException e){
                renderedCategorias.add(categorias.get(i));
                System.out.println("paso por aqui");
            }
            System.out.println("valor de i: "+i);

            i++;
        }
        render(renderedCategorias, params);
    }

     public static void guardarProducto(
            @Required String nombre_referencia,
            @Required String dibujo,
            @Required String composicion,
            @Required String idCategoria,
            String largo,
            String pesoportamano,
            String numeroColores
    ) {

        if (session.get("user") == null) {
            //The user is not identify.
            AnadirProductos.index();
        }

        else {

            int i=1;
            Boolean errorEnElColor = false;
            Boolean continu = true;
            List<String> errores = new ArrayList<String>();

            /*

            Esta funcion no es útil de momento debido a que hay una variable (input de tipo hidden) que nos da el número de
            colores que se están introduciendo. Dicha variable se va actualizando cuando le damos a Añadir o Quitar color.

            //Cuenta cuantos colores se quieren introducir
            while(continu) {
                try{

                    if((params.get("color"+numeroColores) != null) || (params.get("foto_color"+numeroColores) != null) || (params.get("cantidad"+numeroColores) != null)){
                        numeroColores++;
                    }
                    else{
                        continu = false;
                    }
                }
                catch (NullPointerException e){
                    continu = false;
                }
            }
            */

            //Verificamos que nadie haya cambiado el valor de numeroColores a otra cosa que no sea int.
            try{Integer.parseInt(numeroColores);}
            catch (NumberFormatException e){ AnadirProductos.anadir();}

            //Verifica para cada color si es han introducido todos los datos.
            while(i<=Integer.parseInt(numeroColores)){

            /*
                if (params.get("color"+i) == " "){
                    //Message de error a transmitir en el caso de que se produzca.
                    errorColor = errorColor + "Falta rellenar los datos del color " + i;
                    errorEnElColor = true;
                }

                if (params.get("foto_color"+i) == " "){
                    //Message de error a transmitir en el caso de que se produzca.
                    errorColor = errorColor + "Falta elegir la foto del color " + i;
                    errorEnElColor = true;
                }

                if (params.get("cantidad"+i) == " "){
                    //Message de error a transmitir en el caso de que se produzca.
                    errorColor = errorColor + "Falta rellenar los datos de la cantidad " + i;
                    errorEnElColor = true;
                }
            */

                validation.required(params.get("color"+i));
                validation.required(params.get("cantidad"+i));
                validation.required(params.get("foto_color" + i));

                //Testeamos el formato de la cantidad
                try{Integer.parseInt(params.get("cantidad"+i));}
                catch (NumberFormatException e){
                    errores.add("El formato de cantidad"+ i +" no es el correcto");
                }
                i++;

            }

            //Accion a realizar en caso de error
            if(validation.hasErrors() || !errores.isEmpty()){
                for(Error error : validation.errors()) {
                }
                List categorias = Categoria.findAll();
                render("@anadir", categorias, errores);
            }

            else {
            //Everything OK, empezamos a guardar los datos

                //Creamos la referencia y la guardamos
                Referencia referencia = new Referencia(nombre_referencia, dibujo, composicion);

                if(largo != null){
                    referencia.largo = largo;
                }
                if(pesoportamano != null){
                    referencia.PesoPorTamaño = pesoportamano;
                }

                referencia.save();

                //Recuperamos el objeto categoria
                Categoria categoria = Categoria.findById(Long.parseLong(params.get("idCategoria")));

                //Creamos el articulo

                Articulo articulo = new Articulo(referencia);

                //Asociamos los colores, las notas y la catgoria al articulo

                articulo.notas = params.get("notas");
                articulo.categoria = categoria;
                articulo.save();
                //Creamos la lista de colores

                i=1;
                List<Color> colores = new ArrayList<Color>();

                while (i<=Integer.parseInt(numeroColores)){

                    Blob foto_color = params.get("foto_color"+i, Blob.class);

                    Color color = new Color(params.get("color"+i),Integer.parseInt(params.get("cantidad"+i)),foto_color).save();
                    color.articulo = articulo;
                    colores.add(color);
                    color.save();

                    i++;
                }

                articulo.colores = colores;
                articulo.save();
                Application.index();
            }
        }
    }

    public static void logeo(String username, String password) {

        if (session.get("user") != null) {
            AnadirProductos.anadir();
        }
        if (username.equals("distribsud") && password.equals("rony")) {
            session.put("user", username);
            AnadirProductos.anadir();
        } else {
            AnadirProductos.index();
        }
    }

    public static void test(){
        List<Articulo> novedades = new ArrayList<Articulo>();
        novedades = Articulo.find("order by fecha_creacion desc").fetch();

        int i=0;
        boolean continu = true;

        while(continu){
            try {
                System.out.println("valor de notas: " + novedades.get(i).notas);
                i++;
            }
            catch (IndexOutOfBoundsException e){
                continu = false;
            }

        }

        System.out.println("Novedades: " + novedades);
        List<Color> colores = Color.findAll();
        render(colores);
    }

    public static void fotoColor(long id) {
        final Color color = Color.findById(id);
        notFoundIfNull(color);
        response.setContentTypeIfNotSet(color.foto.type());
        renderBinary(color.foto.get());
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
        }


        renderText(message);
    } */
}
