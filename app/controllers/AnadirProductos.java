package controllers;

import play.*;
import play.mvc.*;
import  play.db.jpa.Blob;

import java.util.*;

import models.*;

public class AnadirProductos extends Controller {


    public static void index(){
        render();
    }

    public static void anadir(){
        if (session.get("user") == null) AnadirProductos.index();
        render();
    }

    public static void guardarProducto(){
        if (session.get("user") == null) AnadirProductos.index();

    }

    public static void logeo(String username, String password){

        System.out.println("username: " +username);
        System.out.println("password: " +password);
        if(username.equals("distribsud") || password.equals("rony")){
            session.put("user",username);
            AnadirProductos.anadir();
        }
        else{
            AnadirProductos.index();
        }
    }
}
