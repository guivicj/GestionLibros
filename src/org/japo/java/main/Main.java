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
package org.japo.java.main;

import java.sql.SQLException;
import java.util.Properties;
import org.japo.java.app.App;
import org.japo.java.dam.DAM;
import org.japo.java.libraries.UtilesPRP;

/**
 *
 * @author Guillem Vicente - guillem.vicente.juan@gmail.com
 */
public final class Main {

    // Constructor Inaccesiblew
    private Main() {
    }

    // Punto de Entrado al Programa
    public static final void main(String[] args) {
        try {
            if (NucleoMain.validarArgumentos(args)) {
                // Objeto Propiedades Vacío
                Properties prp = new Properties();

                // Fichero + Recurso > Properties
                UtilesPRP.importar(prp);

                // Properties > Conexión BD
                DAM dam = new DAM(prp);

                // Propiedades + Conexión BD > Lógica de Negocio
                App app = new App(prp, dam);

                // Lógica de Negocio
                app.launchApp();

                // Desconexión BD
                dam.cerrar();
            } else {
                NucleoMain.depurar();
            }
        } catch (SQLException e) {
            NucleoMain.depurar(e);
        } catch (Exception e) {
            NucleoMain.depurar(e);
        }
    }
}
