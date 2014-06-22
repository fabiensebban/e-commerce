package controllers;

import models.Articulo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fabiansebban on 20/06/14.
 */
class Pagina extends Tienda{

    String numeroPagina;
    boolean isCurrent;
    int ultimaPagina = 1;

        public static ArrayList<Pagina> calcularNumeroPaginas(List<Articulo> articulos, int selectedPage){
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
            ArrayList<Pagina> paginas = new ArrayList<Pagina>();
            Pagina pagina;

            while(i <= numeroPaginas){

                pagina = new Pagina();
                pagina.numeroPagina = ""+i+"";

                if(selectedPage == i || numeroPaginas == 1)
                    pagina.isCurrent = true;
                else
                    pagina.isCurrent = false;

                pagina.ultimaPagina = numeroPaginas;

                paginas.add(pagina);
                i++;
            }

            return paginas;
        }
    }

