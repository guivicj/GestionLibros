/*
 * Copyright 2024 Guillem Vicente - guillem.vicente.juan@gmail.com
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
package org.japo.java.entities.libro;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.japo.java.libraries.UtilesEntrada;
import org.japo.java.libraries.UtilesValidacion;

/**
 *
 * @author Guillem Vicente - guillem.vicente.juan@gmail.com
 */
class NucleoLibro {

    // Valores Predeterminados
    static final String DEF_NOM = "Undefined";
    static final String DEF_AUT = "Undefined";

    //Expresiones Regulares
    static final String REG_NOM = "[\\wÁÉÍÓÚÜÑáéíóúüñ _-]{2,50}";
    static final String REG_AUT = "[\\wÁÉÍÓÚÜÑáéíóúüñ _-]{2,50}";

    // Nombres Campos
    static final String FLD_NOM = "nombre";
    static final String FLD_AUT = "autor";

    private static final String TBL_AUT = "Autumn";
    private static final String TBL_ART = "Arte";
    private static final String TBL_CLS = "Clásico";
    private static final String TBL_FNT = "Fantasía";
    private static final String TBL_GTC = "Gótico";
    private static final String TBL_ROM = "Romance";
    private static final String TBL_SUM = "Summer";
    private static final String TBL_SUS = "Suspense";
    private static final String TBL_WNT = "Winter";

    // Opciones Usuario
    private static final String OPC_OPR = "1230";
    private static final String OPC_INS = "1";
    private static final String OPC_DEL = "2";
    private static final String OPC_LST = "3";
    private static final String OPC_EXT = "0";

    private static final String OPC_INS_LBR = "1234567890";

    // Cabeceras
    static final String CAB_OPC
            = """
            LISTA DE OPCIONES
            =================
            [ 1 ] Añadir Libro
            [ 2 ] Eliminar Libro
            [ 3 ] Ver tabla
            [ 0 ] Salir del Programa
            Selecciona la Operación ...: """;

    private static final String CAB_TBL
            = """
            LISTAS DISPONIBLES
            ==================
            [ 1 ] Autumn
            [ 2 ] Arte
            [ 3 ] Clásico
            [ 4 ] Fantasía
            [ 5 ] Gótico
            [ 6 ] Romance
            [ 7 ] Summer 
            [ 8 ] Suspense
            [ 9 ] Winter
            [ 0 ] Atrás
            ------------------
            Lista a modificar ...: """;

    private static final String CAB_LST
            = """
            LIBRO                                              AUTOR                                             
            -------------------------------------------------- --------------------------------------------------
            """;
    private static final String CAB_EXT
            = """
            Sesión Cerrada
            --------------
            Gracias Por Utilizar Nuestra Aplicación
            ---------------------------------------
            """;

    // Mensajes Usuario
    static final String MSG_ERR = "ERROR: Opción Incorrecta";
    static final String MSG_INS_NOM = "Nombre del libro ...:";
    static final String MSG_INS_AUT = "Nombre del autor ...:";

    // Sentencias SQL
    private static final String SQL_INS
            = """
            INSERT INTO %s\s\
            (nombre, autor)\s\
            VALUES\s\
            ('%s', '%s')""";

    private static final String SQL_DEL
            = """
            DELETE from %s\s\
            where nombre = '%s'\s\
              and autor = '%s'""";

    private static final String SQL_LST
            = "SELECT * from %s";

    private NucleoLibro() {
    }

    static final boolean validar(String data, String er) {
        return UtilesValidacion.validar(data, er);
    }

    static final void lanzar(Statement stmt) throws SQLException {
        String input = UtilesEntrada.obtenerTexto(CAB_OPC, MSG_ERR, OPC_OPR);
        switch (input) {
            case OPC_INS ->
                insertar(stmt);

            case OPC_DEL ->
                eliminar(stmt);

            case OPC_LST ->
                listar(stmt);

            default ->
                salir();

        }
    }

    private static void insertar(Statement stmt) throws SQLException {
        insertar(stmt, SQL_INS);
    }

    private static void insertar(Statement stmt, String sql) throws SQLException {
        // Usuario Elige Tabla
        String input = elegirInput();
        if (!input.equals("0")) {
            String tabla = elegirTabla(input);
            insertar(stmt, sql, tabla);
        } else {
            UtilesEntrada.hacerPausa("Volviendo al menú anterior");
        }

    }

    private static void insertar(Statement stmt, String sql, String tbl) throws SQLException {
        // Crear Objeto
        Libro l = crearObjeto();

        // Sentencia SQL 
        sql = String.format(sql, tbl, l.getNom(), l.getAut());

        // Insertado
        int filas = stmt.executeUpdate(sql);

        // Feedback
        System.out.printf("%d libro/s insertado/s correctamente%n", filas);
    }

    private static void eliminar(Statement stmt) throws SQLException {
        eliminar(stmt, SQL_DEL);
    }

    private static void eliminar(Statement stmt, String sql) throws SQLException {
        String input = elegirInput();
        if (!input.equals("0")) {
            String tabla = elegirTabla(input);
            eliminar(stmt, sql, tabla);
        } else {
            UtilesEntrada.hacerPausa("Volviendo al menú anterior");
        }
    }

    private static void eliminar(Statement stmt, String sql, String tbl) throws SQLException {
        Libro l = crearObjeto();

        // Sentencia SQL
        sql = String.format(sql, tbl, l.getNom(), l.getAut());

        // Eliminado
        int filas = stmt.executeUpdate(sql);

        // Feedback
        System.out.printf("%d libro/s eliminado/s correctamente%n", filas);
    }

    private static void listar(Statement stmt) throws SQLException {
        listar(stmt, SQL_LST);
    }

    private static void listar(Statement stmt, String sql) throws SQLException {
        String input = elegirInput();
        if (!input.equals("0")) {
            String tabla = elegirTabla(input);
            listar(stmt, sql, tabla);
        } else {
            UtilesEntrada.hacerPausa("Volviendo al menú anterior");
        }
    }

    private static void listar(Statement stmt, String sql, String tbl) throws SQLException {
        // Cabecera
        System.out.println(CAB_LST);

        sql = String.format(sql, tbl);
        try (ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                do {
                    // ResultSet - Fila Actual > Entidad
                    Libro l = importar(rs);
                    System.out.println(l.toString());
                    // Entidad > Consola
                } while (rs.next());
            } else {
                System.out.println("AVISO: No hay datos");
            }
        }
        UtilesEntrada.hacerPausa();
    }

    private static void salir() {
        System.out.println(CAB_EXT);
    }

    private static String elegirInput() {
        return UtilesEntrada.obtenerTexto(
                CAB_TBL, MSG_ERR, OPC_INS_LBR);
    }

    private static Libro crearObjeto() {
        Libro l = new Libro(
                UtilesEntrada.obtenerTexto(MSG_INS_NOM),
                UtilesEntrada.obtenerTexto(MSG_INS_AUT)
        );
        return l;
    }

    private static String elegirTabla(String input) {
        String tabla;
        switch (input) {
            case "1" ->
                tabla = TBL_AUT;
            case "2" ->
                tabla = TBL_ART;
            case "3" ->
                tabla = TBL_CLS;
            case "4" ->
                tabla = TBL_FNT;
            case "5" ->
                tabla = TBL_GTC;
            case "6" ->
                tabla = TBL_ROM;
            case "7" ->
                tabla = TBL_SUM;
            case "8" ->
                tabla = TBL_SUS;
            default ->
                tabla = TBL_WNT;
        }
        return tabla;
    }

    private static Libro importar(ResultSet rs) throws SQLException {
        String nombre = rs.getString(FLD_NOM);
        String autor = rs.getString(FLD_AUT);
        Libro l = new Libro(nombre, autor);
        return l;
    }

}
