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

        }
    }
 
}