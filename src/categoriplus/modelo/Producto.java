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
 *  Clase del Modelo de Producto.
 * 
 * @author Antonio López Marín
 */
public class Producto implements Serializable {
    
    //Atributos
    private int id;
    private String name;
    private Type type;
    private Categoria categoria;
    private Double listPrice;
    private Double standardPrice;
    private String description;
    private boolean saleOk;
    
    //Enum
    public enum Type {
        CONSUMIBLE(java.util.ResourceBundle.getBundle("lang/lenguajes").getString("CONSUMIBLE")),
        SERVICIO(java.util.ResourceBundle.getBundle("lang/lenguajes").getString("SERVICIO"));
        
        final String name;
        
        private Type(String name){
            this.name = name;
        }
        
        public String get(){
            return name;
        }
    }
    
    public Producto() {
    }
    
    /**
     * Constructor.
     * 
     * @param id
     * @param name
     * @param type
     * @param categoria
     * @param listPrice
     * @param standardPrice
     * @param description
     * @param saleOk 
     */
    public Producto(int id, String name, Type type, Categoria categoria, 
            Double listPrice, Double standardPrice, String description, boolean saleOk) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.categoria = categoria;
        this.listPrice = listPrice;
        this.standardPrice = standardPrice;
        this.description = description;
        this.saleOk = saleOk;
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Double getListPrice() {
        return listPrice;
    }

    public void setListPrice(Double listPrice) {
        this.listPrice = listPrice;
    }

    public Double getStandardPrice() {
        return standardPrice;
    }

    public void setStandardPrice(Double standardPrice) {
        this.standardPrice = standardPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
   
    public boolean isSaleOk() {
        return saleOk;
    }

    public void setSaleOk(boolean saleOk) {
        this.saleOk = saleOk;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.id;
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
        final Producto other = (Producto) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (this.type != other.type) {
            return false;
        }
        if (this.categoria != null & other.categoria != null) {
            if (this.categoria.getId() != other.categoria.getId()) {
                return false;
            }
        }else if(this.categoria == null){
            if (other.categoria != null) {
                return false;
            }
        }else if(other.categoria == null){
            if(this.categoria != null){
                return false;
            }
        }
        if (!Objects.equals(this.listPrice, other.listPrice)) {
            return false;
        }
        if (!Objects.equals(this.standardPrice, other.standardPrice)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (this.saleOk != other.saleOk) {
            return false;
        }
        return true;
    }
    
    
}
