/*
 * Copyright 2023 José A. Pacheco - japolabs@gmail.com.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.japo.java.libraries;

import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author José A. Pacheco - japolabs@gmail.com
 */
public final class UtilesEntrada {

    // Mensajes Predeterminados
    public static final String MSG_USR = "Dato .....: ";
    public static final String MSG_ERR = "ERROR: Entrada incorrecta";
    public static final String MSG_BRK = "Pulse [Intro] para continuar ...";

    // Objeto Scanner
    public static final Scanner SCN
            = new Scanner(System.in, "Windows-1252")
                    .useLocale(Locale.ENGLISH).useDelimiter("\\s+");

    // Constructor Predeterminado ( Oculto )
    private UtilesEntrada() {
    }

    // Consola > Real
    public static final double obtenerReal(String msgUsr, String msgErr) {
        // Valor a obtener
        double dato = 0;

        // Algoritmo de entrada
        boolean introOK = false;
        do {
            System.out.print(msgUsr);
            try {
                dato = SCN.nextDouble();
                introOK = true;
            } catch (Exception e) {
                System.out.println(msgErr);
                System.out.println("---");
            } finally {
                SCN.nextLine();
            }
        } while (!introOK);

        // Retorno
        return dato;
    }

    // Consola + Rango > Real
    public static final double obtenerReal(String msgUsr, String msgErr, double min, double max) {
        // Valor de retorno
        double dato = 0;

        // Algoritmo de Entrada
        boolean introOK;
        do {
            // Entrada de Valor
            dato = obtenerReal(msgUsr, msgErr);

            // Validacion de valor
            introOK = dato >= min && dato <= max;

            // Informar del Error
            if (!introOK) {
                System.out.println(msgErr);
                System.out.println("---");
            }
        } while (!introOK);

        // Retorno
        return dato;
    }

    // Consola + Expresión Regular > Real
    public static final double obtenerReal(String msgUsr, String msgErr, String er) {
        // Referencia
        double dato = 0.0;

        // Bucle Validación
        boolean introOK;
        do {
            // Consola > Dato
            dato = obtenerReal(msgUsr, msgErr);

            // Validar Dato
            introOK = UtilesValidacion.validar(dato + "", er);

            // Procesar Validación
            if (!introOK) {
                System.out.println("---");
                System.out.println(msgErr);
                System.out.println("---");
            }
        } while (!introOK);

        // Retorno
        return dato;
    }

    // Consola > Entero
    public static final int obtenerEntero(String msgUsr, String msgErr) {
        return (int) obtenerReal(msgUsr, msgErr);
    }

    // Consola + Rango > Entero
    public static final int obtenerEntero(String msgUsr, String msgErr, int min, int max) {
        return (int) obtenerReal(msgUsr, msgErr, min, max);
    }

    // Consola + Expresión Regular > Entero
    public static final int obtenerEntero(String msgUsr, String msgErr, String er) {
        // Referencia
        int dato = 0;

        // Bucle Validación
        boolean introOK;
        do {
            // Consola > Dato
            dato = obtenerEntero(msgUsr, msgErr);

            // Validar Dato
            introOK = UtilesValidacion.validar(dato + "", er);

            // Procesar Validación
            if (!introOK) {
                System.out.println("---");
                System.out.println(msgErr);
                System.out.println("---");
            }
        } while (!introOK);

        // Retorno
        return dato;
    }

    // Consola > Carácter
    public static final char obtenerCaracter(String msgUsr, String msgErr) {
        // Valor a obtener
        char dato = 0;

        // Algoritmo de entrada
        boolean introOK = false;
        do {
            System.out.print(msgUsr);
            try {
                dato = SCN.nextLine().charAt(0);
                introOK = true;
            } catch (Exception e) {
                System.out.println("---");
                System.out.println(msgErr);
                System.out.println("---");
            }
        } while (!introOK);

        // Retorno
        return dato;
    }

    // Consola + Opciones > Carácter
    public static final char obtenerCaracter(String msgUsr, String msgErr, String opc) {
        // Valor a obtener
        char dato = 0;

        // Algoritmo de entrada
        boolean introOK = false;
        do {
            dato = obtenerCaracter(msgUsr, msgErr);
            if (opc.contains(dato + "")) {
                introOK = true;
            } else {
                System.out.println("---");
                System.out.println(msgErr);
            }
            System.out.println("---");
        } while (!introOK);

        // Retorno
        return dato;
    }

    // Consola + Rango > Carácter
    public static final char obtenerCaracter(String msgUsr, String msgErr, char min, char max) {
        // Valor de retorno
        char dato = 0;

        // Algoritmo de Entrada
        boolean introOK;
        do {
            // Entrada de Valor
            dato = obtenerCaracter(msgUsr, msgErr);

            // Validacion de valor
            introOK = dato >= min && dato <= max;

            // Informar del Error
            if (!introOK) {
                System.out.println(msgErr);
                System.out.println("---");
            }
        } while (!introOK);

        // Retorno
        return dato;
    }

    // Consola > Texto
    public static final String obtenerTexto(String msgUsr) {
        System.out.print(msgUsr);
        return SCN.nextLine();
    }

    // Consola + Expresión Regular > Texto
    public static final String obtenerTexto(String msgUsr, String msgErr, String er) {
        // Valor de retorno
        String dato = "";

        // Algoritmo de Entrada
        boolean introOK;
        do {
            // Entrada de Valor
            dato = obtenerTexto(msgUsr);

            // Validacion de valor
            introOK = UtilesValidacion.validar(dato, er);

            // Informar del Error
            if (!introOK) {
                System.out.println("---");
                System.out.println(msgErr);
                System.out.println("---");
            }
        } while (!introOK);

        // Retorno
        return dato;
    }

    // Consola > Lógico
    public static final boolean obtenerLogico(String msgUsr, String msgErr) {
        // Valor de retorno
        boolean dato = false;

        // Entrada de Valor
        boolean introOK = false;
        do {
            try {
                System.out.print(msgUsr);
                dato = SCN.nextBoolean();
                introOK = true;
            } catch (Exception e) {
                System.out.println(msgErr);
            } finally {
                SCN.nextLine();
            }
        } while (!introOK);

        // Retorno
        return dato;
    }

    // Mensaje Personalizado + Pausa ( Intro )
    public static final void hacerPausa(String msg) {
        System.out.println(msg);
        hacerPausa();
    }

    // Pausa ( Intro )
    public static final void hacerPausa() {
        System.out.println("---");
        obtenerTexto(MSG_BRK);
        System.out.println("---");
    }
}
