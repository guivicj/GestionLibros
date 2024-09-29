/*
 * Copyright 2019 José A. Pacheco Ondoño - joanpaon@gmail.com.
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
package org.japo.java.dam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 *
 * @author José A. Pacheco Ondoño - joanpaon@gmail.com
 */
public final class UtilesDAM {

    // Constructor Inaccesible
    public UtilesDAM() {
    }

    // Obtiene Conexión con BD - Properties
    public static final Connection conectar(Properties prp) throws SQLException {
        // Definir cadena de conexión
        String cadenaConexion = String.format(NucleoDAM.CONN_STR_FOR,
                prp.getProperty(NucleoDAM.PRP_PROT, NucleoDAM.DEF_PROT),
                prp.getProperty(NucleoDAM.PRP_HOST, NucleoDAM.DEF_HOST),
                prp.getProperty(NucleoDAM.PRP_PORT, NucleoDAM.DEF_PORT),
                prp.getProperty(NucleoDAM.PRP_DBNM, NucleoDAM.DEF_DBAM),
                prp.getProperty(NucleoDAM.PRP_USER, NucleoDAM.DEF_USER),
                prp.getProperty(NucleoDAM.PRP_PASS, NucleoDAM.DEF_PASS));

        // Realizar la conexión
        Connection conn = DriverManager.getConnection(cadenaConexion);

        // Mensaje Informativo
//        System.out.println("---");
        System.out.println("Conexión establecida con la Base de Datos");
        System.out.println("---");

        // Retorno
        return conn;
    }

    // Conexión + Access + Concurrency > Statement
    public static final Statement vincular(Connection conn, Properties prp) throws SQLException {
        // ---- TIPO DE ACCESO ----
        // ResultSet.TYPE_FORWARD_ONLY (*) - Indica que el objeto ResultSet se
        //      puede recorrer unicamente hacia adelante.
        // ResultSet.TYPE_SCROLL_INSENSITIVE - Indica que el objeto ResultSet se
        //      puede recorrer, pero en general no es sensible a los cambios en
        //      los datos que subyacen en él.
        // ResultSet.TYPE_SCROLL_SENSITIVE - Indica que el objeto ResultSet se
        //      puede  recorrer, y además, los cambios en él repercuten
        //      en la base de datos subyacente.
        //
        int tipoAcceso = switch (prp.getProperty(NucleoDAM.PRP_TYPE)) {
            case NucleoDAM.TYPE_FO ->
                ResultSet.TYPE_FORWARD_ONLY;
            case NucleoDAM.TYPE_SI ->
                ResultSet.TYPE_SCROLL_INSENSITIVE;
            case NucleoDAM.TYPE_SS ->
                ResultSet.TYPE_SCROLL_SENSITIVE;
            default ->
                throw new SQLDataException("ERROR: Statement - Tipo de Acceso INDEFINIDO");
        };

        // ---- CONCURRENCIA ----
        // ResultSet.CONCUR_READ_ONLY (*) - Indica que en el modo de concurrencia
        //      para el objeto ResultSet éste no puede ser actualizado.
        // ResultSet.CONCUR_UPDATABLE - Indica que en el modo de concurrencia
        //      para el objeto ResultSet éste podria ser actualizado.
        //
        int concurrencia = switch (prp.getProperty(NucleoDAM.PRP_CONC)) {
            case NucleoDAM.CONC_RO ->
                ResultSet.CONCUR_READ_ONLY;
            case NucleoDAM.CONC_UP ->
                ResultSet.CONCUR_UPDATABLE;
            default ->
                throw new SQLDataException("ERROR: Statement - Concurrencia INDEFINIDA");
        };

        // Connection + Access Type + Concurrency > Statement
        return conn.createStatement(tipoAcceso, concurrencia);
    }

    // Cierre JDBC Connection
    public static final void cerrar(Connection conn) throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }

        // Mensaje Informativo
//        System.out.println("---");
        System.out.println("Conexión finalizada con la Base de Datos");
        System.out.println("---");
    }

    // Cierre JDBC Statement
    public static final void cerrar(Statement stmt) throws SQLException {
        if (stmt != null && !stmt.isClosed()) {
            stmt.close();
        }
    }

    // Cierre JDBC ResultSet
    public static final void cerrar(ResultSet rs) throws SQLException {
        if (rs != null && !rs.isClosed()) {
            rs.close();
        }
    }

    // Cierre JDBC Completo
    public static final void cerrar(Connection conn, Statement stmt) throws SQLException {
        // Cierre Sentencia
        cerrar(stmt);

        // Cierre Conexión
        cerrar(conn);
    }
}
