/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package guerigeri;

import java.util.Scanner;

/**
 *
 * @author ERICK
 */
public class Aplicacion {

    private static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println(Ficheros.mostrarOpciones());

        String respuesta = teclado.nextLine();

        int[][] matriz = null;

        switch (respuesta) {
            case "1":
                matriz = Ficheros.leerM("matrizP.txt", ",");
                Ficheros.mostrarMatriz(matriz);
                System.out.println();
                break;
            case "2":
                System.out.println("Orden original: ");
                matriz = Ficheros.leerM("matrizP.txt", ",");
                Ficheros.mostrarMatriz(matriz);
                System.out.println();

                System.out.println("Orden inverso: ");
                matriz = Ficheros.leerM("matrizP.txt", ",");
                Ficheros.mostrarMatriz(Ficheros.ordenInvertido(matriz));
                break;
            case "3":
                matriz = Ficheros.leerM("matrizP.txt", ",");
                int[][] resultado = Ficheros.ordenInvertido(matriz);
                Ficheros.escribirMatriz(resultado, "prueba.txt");
                break;
            case "4":
                matriz = Ficheros.leerM("matrizP.txt", ",");
                
                Ficheros.mostrarMatriz(Ficheros.ordenarMatriz(matriz));
                
        }

    }

}
