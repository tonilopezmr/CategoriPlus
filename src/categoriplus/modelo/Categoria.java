/*
 * Copyright 2014 Antonio López Marín.
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
package categoriplus.modelo;

import java.io.Serializable;
import java.util.Objects;

/**
 * Clase del Modelo de Categoria.
 * 
 * @author Antonio López Marín
 */
public class Categoria implements Serializable{
        
    //Atributos
    private int id;
    private String name;
    private Categoria parent;
    
    public Categoria(){
    }
    
    /**
     * Constructor.
     * 
     * @param id
     * @param name
     * @param parent Con
     */
    public Categoria(int id, String name, Categoria parent) {
        this.id = id;
        this.name = name;
        this.parent = parent;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Categoria getParent() {
        return parent;
    }

    public void setParent(Categoria parent) {
        this.parent = parent;
    }
    
    @Override
    public String toString() {
        return name;
    }    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Categoria other = (Categoria) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (this.parent != null && other.parent != null) {
            if (parent.getId() != other.parent.getId()) {
                return false;
            }
        }else if(this.parent == null){
            if (other.parent != null) {
                return false;
            }
        }else if (other.parent == null){
            if (this.parent != null) {
                return false;
            }
        }
        return true;
    }
}
