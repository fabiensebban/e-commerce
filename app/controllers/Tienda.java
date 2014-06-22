package controllers;

//import com.intellij.openapi.vcs.history.VcsRevisionNumber;
import play.*;
import play.mvc.*;
import  play.db.jpa.Blob;

import java.util.*;

import models.*;

/*
class pagina {

    String numeroPagina;
    boolean isCurrent;


    public static ArrayList<pagina> calcularNumeroPaginas(List<Articulo> articulos, int selectedPage){
        //Calculamos el numero de paginas
        int numeroPaginas = 1;

        if(articulos.size()%12 == 0 && articulos.size() != 0){
            numeroPaginas = articulos.size()/12;
        }
        else if(articulos.size() == 0){
            numeroPaginas = 1;
        }
        else{
            numeroPaginas = articulos.size()/12 +1;
        }

        int i = 1;
        ArrayList<pagina> paginas = new ArrayList<pagina>();
        pagina pagina;

        while(i <= numeroPaginas){

            pagina = new pagina();

            pagina.numeroPagina = ""+i+"";

            if(selectedPage == i)
                pagina.isCurrent = true;
            else
                pagina.isCurrent = false;

            paginas.add(pagina);
            i++;
        }

        return paginas;
    }
}*/

public class Tienda extends Controller {


    public static void catalogo(Long idCategoria) {

        int selectedPage = 0;

        if(params.get("selectedpage") != null){
            selectedPage =  Integer.parseInt(params.get("selectedpage"));
        }

        long IdPadre = 1;
        List<Categoria> categorias = Categoria.find("byIdPadre", IdPadre).fetch();

        //Si se ha seleccionado una categoria
        if(idCategoria != null) {
            Categoria categoriaConcreta = Categoria.findById(idCategoria);

            if(categoriaConcreta == null) Tienda.catalogo(null);

            String categoriaSeleccionada = categoriaConcreta.nombre;

            boolean categoriaConHijos;
            try {
                categoriaConcreta.IdHijos.get(0);
                categoriaConHijos = true;
            }
            catch(IndexOutOfBoundsException e){
                categoriaConHijos = false;
            }
            //Si la categoria NO tiene categorias hijas.
            if(!categoriaConHijos) {
                List<Articulo> articulos = categoriaConcreta.articulos;
                ArrayList<Pagina> paginas;
                paginas = Pagina.calcularNumeroPaginas(categoriaConcreta.articulos,selectedPage);

                String nextPage= "";

                if(selectedPage != 0 && selectedPage != paginas.get(0).ultimaPagina)
                     nextPage = ""+(selectedPage+1)+"";
                else if(selectedPage == paginas.get(0).ultimaPagina)
                    nextPage = ""+selectedPage+"";
                else
                    nextPage = "2";

                render(categorias, articulos, paginas, nextPage, categoriaConcreta, categoriaSeleccionada);
            }
            else{
                List<Categoria> categoriaHijas = new ArrayList<Categoria>();

                for(int i=0; i < categoriaConcreta.IdHijos.size(); i++ ){

                    Categoria categoriaHija = Categoria.findById(categoriaConcreta.IdHijos.get(i));
                    categoriaHijas.add(categoriaHija);
                }

                render(categorias, categoriaHijas, categoriaConcreta);
            }
        }

        //Si no se ha seleccionado ninguna categoria
        else{
            boolean novedades = true;

            List<Articulo> articulos = new ArrayList<Articulo>();
            articulos = Articulo.find("order by fecha_creacion asc").fetch(12);

            ArrayList<Pagina> paginas = new ArrayList<Pagina>();
            Pagina pagina = new Pagina();
            paginas = Pagina.calcularNumeroPaginas(articulos, selectedPage);

            render(categorias, articulos, paginas, novedades);
        }

    }


    public static void producto(Long idProducto) {
        List<Articulo> novedades = new ArrayList<Articulo>();
        novedades = Articulo.find("order by fecha_creacion desc").fetch(12);
        render(novedades);
    }

    public static void fotoColor(long id) {
        final Color color = Color.findById(id);
        notFoundIfNull(color);
        response.setContentTypeIfNotSet(color.foto.type());
        renderBinary(color.foto.get());
    }

}

