/*
 * Copyright 2024 guill.
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

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author guill
 */
public final class Libro implements Serializable {

    private String nom;
    private String aut;

    public Libro() {
        this.nom = NucleoLibro.DEF_NOM;
        this.aut = NucleoLibro.DEF_AUT;
    }

    public Libro(String nom, String aut) {
        if (NucleoLibro.validar(nom, NucleoLibro.REG_AUT)) {
            this.nom = nom;
        } else {
            this.nom = NucleoLibro.DEF_NOM;
        }
        if (NucleoLibro.validar(aut, NucleoLibro.REG_AUT)) {
            this.aut = aut;
        } else {
            this.aut = NucleoLibro.DEF_AUT;
        }
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        if (NucleoLibro.validar(nom, NucleoLibro.REG_AUT)) {
            this.nom = nom;
        }
    }

    public String getAut() {
        return aut;
    }

    public void setAut(String aut) {
        if (NucleoLibro.validar(aut, NucleoLibro.REG_AUT)) {
            this.aut = aut;
        }
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Libro l
                ? this.nom.equals(l.nom)
                : false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.nom);
        return hash;
    }

    @Override
    public String toString() {
        return String.format("Título: %s - Autor/a: %s",
                nom, aut);
    }   
}