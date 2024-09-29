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
package org.japo.java.app;

import java.sql.SQLException;
import java.util.Properties;
import org.japo.java.dam.DAM;
import org.japo.java.entities.libro.LibroDAM;

/**
 *
 * @author Guillem Vicente - guillem.vicente.juan@gmail.com
 */
public final class App {

    // Propiedades Aplicación
    private final Properties prp;

    // Data Access Manager
    private final DAM dam;

    // Entidades DAM
    private final LibroDAM ldam;

    // Constructor Parametrizado
    public App(Properties prp, DAM dam) {
        // Propiedades Aplicación
        this.prp = prp;

        // Data Access Manager
        this.dam = dam;

        // Entidades DAM
        this.ldam = dam.getLibro();
    }

    // Lanzar Lógica Aplicación
    public void launchApp() throws SQLException {
        ldam.lanzar();
    }
}
