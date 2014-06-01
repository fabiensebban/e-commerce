import org.junit.*;
import java.util.*;
import play.test.*;
import models.*;

import play.db.jpa.Blob;



public class BasicTest extends UnitTest {

    @Test
    public void aVeryImportantThingToTest() {
        assertEquals(2, 1 + 1);
    }

    @Test
    public void addAndRetriveArticulo(){
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

        //Testeamos

        //Artiuclo - Notas
        assertEquals(articulo.notas,"notas");

        //Referencia
        assertEquals(articulo.referencia.nombre,"nombre_ref");
        assertEquals(articulo.referencia.dibujo,"dibujo_ref");
        assertEquals(articulo.referencia.composicion,"composicion_ref");
        assertEquals(articulo.referencia.largo,"largo");
        assertEquals(articulo.referencia.PesoPorTamaño,"peso");

        //Categoria
        assertEquals(articulo.categoria.nombre,"Uni");
        assertEquals(articulo.categoria.IdPadre,id);

        //Colores
        assertEquals(articulo.colores.get(0).nombre,"nombre1");
        assertEquals(articulo.colores.get(0).cantidad,123);

        assertEquals(articulo.colores.get(1).nombre,"nombre2");
        assertEquals(articulo.colores.get(1).cantidad,9887);
    }

    public void CreateAndDeleteArticulo(){

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

        //Testeamos

    }
}
