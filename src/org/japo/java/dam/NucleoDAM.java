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
package org.japo.java.dam;

/**
 *
 * @author JAPO Labs - japolabs@gmail.com
 */
final class NucleoDAM {

    // Valores Tipo de Acceso
    static final String TYPE_FO = "TYPE_FORWARD_ONLY";
    static final String TYPE_SI = "TYPE_SCROLL_INSENSITIVE";
    static final String TYPE_SS = "TYPE_SCROLL_SENSITIVE";

    // Valores Concurrencia
    static final String CONC_RO = "CONCUR_READ_ONLY";
    static final String CONC_UP = "CONCUR_UPDATABLE";

    // Valores Predeterminados BD
    static final String DEF_PROT = "jdbc:mariadb";
//    static final String DEF_PROT = "jdbc:mysql";
    static final String DEF_HOST = "localhost";
    static final String DEF_PORT = "3306";
    static final String DEF_DBAM = "test";
    static final String DEF_USER = "admin";
    static final String DEF_PASS = "12345";

    // Propiedades BD
    static final String PRP_PROT = "db.conn.prot";
    static final String PRP_HOST = "db.conn.host";
    static final String PRP_PORT = "db.conn.port";
    static final String PRP_DBNM = "db.conn.name";
    static final String PRP_USER = "db.conn.user";
    static final String PRP_PASS = "db.conn.pass";
    static final String PRP_TYPE = "db.stmt.access_type";
    static final String PRP_CONC = "db.stmt.concurrency";

    // Formato Cadena Conexión
    // prot://host:port/name?user=user&password=pass
    static final String CONN_STR_FOR = "%s://%s:%s/%s?user=%s&password=%s";

    // Constructor Inaccesible
    private NucleoDAM() {
    }
}
