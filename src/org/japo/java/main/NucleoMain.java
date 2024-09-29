/*
 * Copyright 2024 JAPO Labs - japolabs@gmail.com.
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
package org.japo.java.main;

import java.sql.SQLException;

/**
 *
 * @author JAPO Labs - japolabs@gmail.com
 */
final class NucleoMain {

    // Mensajes de Error
    static final String MSG_ERR_ACCESO
            = """
        ERROR: Acceso Denegado
        Contacte con el Servicio Técnico
        Gracias por utilizar esta Aplicación
        """;

    // Clave de Acceso
    private static final String ACCESS_UID = "JAPO-Omicron-0";

    // Constructor Privado
    private NucleoMain() {
    }

    // Validación Argumentos Ejecución
    static final boolean validarArgumentos(String[] args) {
        return args != null && args.length == 1 && args[0].equals(ACCESS_UID);
    }

    // SQLException Info > Consola
    static final void depurar(SQLException e) {
        if (e != null) {
            System.out.println("ATENCIÓN: Base de Datos INACCESIBLE");
            System.out.printf("Código de error .: %d%n", e.getErrorCode());
            System.out.printf("Estado SQL ......: %s%n", e.getSQLState());
            System.out.printf("Descripción .....: %s%n", e.getLocalizedMessage());
        }
    }

    // Exception Info > Consola
    static final void depurar(Exception e) {
        if (e != null) {
            System.out.printf("Descripción .....: %s%n", e.getLocalizedMessage());
        }
    }

    // Argumentos Erróneos > Consola
    static final void depurar() {
        System.out.println(MSG_ERR_ACCESO);
    }
}
