package JavaFactories;


import JavaDataframes.DataFrame;

import java.io.File;
import java.io.IOException;

/**
 * Cargaremos los datos leídos de las diferentes factories y los cargaremos una estructura de datos
 * llamada <b>DataFrame</b>.
 */
public interface DataFrameFactory {

    /**
     * Leeremos los datos del fichero recibido y los cargaremos en la estructura del DataFrame según el tipo de fichero
     * que hemos tratado.
     * @param input: Fichero a leer.
     * @return Devuelve una estructura DataFrame {HashMap,ArrayList}
     * @throws IOException
     */
    DataFrame frame(File input, String... delim) throws IOException;


}
