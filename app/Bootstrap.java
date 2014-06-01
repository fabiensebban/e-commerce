import play.*;
import play.jobs.*;
import play.test.*;
 
import models.*;
 
@OnApplicationStart
public class Bootstrap extends Job {
 
    public void doJob() {
        // Check if the database is empty
        if(Articulo.count() == 0 || Categoria.count() == 0) {

            //***********************************************
            //Creacion de algunos objetos para hacer pruebas.
            //***********************************************

            Fixtures.load("initial-data.yml");
            long IdPadre = 1;



            //Creacion de 2 categorias que descienden de Root.
            Categoria categoriaRoot = Categoria.findById(IdPadre);
            categoriaRoot.anadirCategoria("Uni").save();
            categoriaRoot.anadirCategoria("Topos").save();

            //Sub categoria de Uni.
            Categoria categoriaUni = Categoria.find("byNombre", "Uni").first();
            categoriaUni.anadirCategoria("Acrilique").save();


            /*
            //Creamos la referencia
            Referencia referencia = new Referencia("nombre_ref", "dibujo_ref", "composicion_ref");
            referencia.largo = "largo";
            referencia.PesoPorTamaño = "peso";
            referencia.save();

            //Creamos el articulo y le asociamos la referencia
            Articulo articulo = new Articulo(referencia);
            articulo.notas = "notas";

            //Creamos la categoria
            Long id;
            id=Long.parseLong("1");
            Categoria categoria = new Categoria("Uni", id);
            categoria.save();

            //Asociamos categoria con el articulo
            articulo.categoria = categoria;

            //Creamos dos colores
            Color color1 = new Color("nombre1", 123, null);
            Color color2 = new Color("nombre2", 9887, null);

            //Asociamos el color al articulo
            articulo.colores.add(color1);
            articulo.colores.add(color2);
            articulo.save();

            */

        }
    }
}