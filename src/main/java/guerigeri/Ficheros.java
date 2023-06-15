/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package guerigeri;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author ERICK
 */
public class Ficheros {

    // NO FUNCIONA
    public static String[][] leer(String nombre) {
        List<String> lineas;
        String[][] matriz = null;

        try {
            lineas = Files.readAllLines(Paths.get(nombre));

            // Le damos el número de filas que tiene el fichero
            matriz = new String[lineas.size()][];

            for (int i = 0; i < lineas.size(); i++) {

                // Cogemos el largo y se lo damos a las columnas de la fila
                matriz[i] = new String[lineas.get(i).length()];

                for (int j = 0; j < matriz[i].length; j++) {
                    matriz[i][j] = String.valueOf(lineas.get(i).charAt(j));
//                    System.out.print(matriz[i][j].replace(",", "") + " ");

                }

//                System.out.println();
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return matriz;
    }

    public static int[][] leerM(String nombre, String separador) {

        // Variables para guardar los datos que se van leyendo
        String[] tokens;
        String linea;
        int[][] matriz = null;
        int contador = 0;

        // Inicialización del flujo "datosFichero" en función del archivo llamado "idFichero"
        // Estructura try-with-resources. Permite cerrar los recursos una vez finalizadas
        // las operaciones con el archivo
        try ( Scanner datosFichero = new Scanner(new File(nombre), "UTF-8")) {

            while (datosFichero.hasNextLine()) {
                datosFichero.nextLine(); // Para pasar a la siguiente linea

                contador++; // Nos servirá para definir el largo de las filas
            }

            matriz = new int[contador][]; // Establecemos el número de filas que tendrá
            datosFichero.close(); // Cerramos el scanner

            // Abrimos otro scanner y no hace falta try ya que va dentro del que ya tenemos 
            Scanner datosFichero2 = new Scanner(new File(nombre), "UTF-8");

            // hasNextLine devuelve true mientras haya líneas por leer
            int fila = 0;
            while (datosFichero2.hasNextLine()) {
                // Guarda la línea completa en un String
                linea = datosFichero2.nextLine();

                // Separamos cada dato
                tokens = linea.split(separador);

                // NO SE PUEDE COLOCAR UN BUCLE YA QUE TODAVÍA NO TENEMOS LA SIGUIENTE LINEA DE datosFichero2
//                for (int i = 0; i < tokens.length; i++) {
                // Cogemos el largo del token y se lo damos a las columnas de la fila
                matriz[fila] = new int[tokens.length];

                for (int j = 0; j < tokens.length; j++) {
                    matriz[fila][j] = Integer.parseInt(tokens[j]);

                }
                fila++;

//                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return matriz;
    }

    public static String mostrarOpciones() {
        String texto = """
                       1. Leer archivo el archivo adjunto en la tarea, cargar el contenido en una matriz y mostrar la estructura de datos por consola
                       
                       2. Leer archivo el archivo adjunto en la tarea, cargar el contenido en una matriz y realizar la matriz invertida, mostrando el resultado por consola. Entendemos por matriz invertida aquella que va cambiando las filas en orden inverso. Veamos un ejemplo de matriz y su invertida
                       
                       3. Realizar la función anterior pero volcando la matriz invertida en un fichero de texto en la raíz del proyecto. El nombre del fichero hay que solicitarlo al usuario
                       
                       4. Leer archivo el archivo adjunto en la tarea, cargar el contenido en una matriz y ordenar cada fila de la matriz de mayor a menor, mostrando la matriz resultante por consola.
                       
                       5. Salir del programa.
                       
                       """;

        return texto;
    }

    public static void mostrarMatriz(int[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            System.out.println();
            for (int j = 0; j < matriz[i].length; j++) { //Así obtenemos lo que ocupa cada fila, para las columnas
                System.out.print(matriz[i][j] + " ");
            }

        }
    }

    public static int[][] ordenInvertido(int[][] m) {

        int fila = m.length;
        int[] largoFilas = new int[fila];

        for (int i = 0; i < largoFilas.length; i++) {
            largoFilas[i] = m[i].length;
        }

        int[][] matrizInvertida = new int[fila][];

        for (int i = 0; i < fila; i++) {
            int largoFilaOriginal = largoFilas[i];
            matrizInvertida[i] = new int[largoFilaOriginal];

            for (int j = 0; j < largoFilaOriginal; j++) {
                matrizInvertida[i][j] = m[fila - i - 1][j];
            }
        }
        return matrizInvertida;
    }

    public static void escribirMatriz(int[][] m, String nombre) {
        // Fichero a crear. Ruta relativa a la carpeta raíz del proyecto
        String tmp;

        // Si se utiliza el constructor FileWriter(idFichero, true) entonces se anexa información
        // al final del fichero idFichero
        // Estructura try-with-resources. Instancia el objeto con el fichero a escribir
        // y se encarga de cerrar el recurso "flujo" una vez finalizadas las operaciones
        try ( BufferedWriter flujo = new BufferedWriter(new FileWriter(nombre))) {
            for (int i = 0; i < m.length; i++) {
                for (int j = 0; j < m[i].length; j++) {
                    // Obtengo en un String el elemento int de la matriz
                    tmp = String.valueOf(m[i][j]);
                    // Si es el último de la fila no pone la coma
                    if (j == m[i].length - 1) {
                        // Usamos metodo write() para escribir en el buffer
                        flujo.write(tmp);
                    } else {
                        flujo.write(tmp + ",");
                    }
                }

                // Metodo newLine() añade salto de línea después de cada fila
                flujo.newLine();
            }
            // Metodo fluh() guarda cambios en disco 
            flujo.flush();
            System.out.println("Fichero " + nombre + " creado correctamente.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public static int[][] ordenarMatriz(int[][] m) {

        // Accedemos a la fila
        for (int i = 0; i < m.length; i++) {
            Arrays.sort(m[i]); // Ordenamos cada fila
            for (int j = 0; j < m[i].length; j++) {
                // Accedemos a cada columna
                for (int k = m[i].length - 1; k > 0; k--) {
                    m[i][j] = m[i][k];
                }
            }

        }
        return m;
    }

}
