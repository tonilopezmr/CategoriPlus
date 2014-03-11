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
package categoriplus.vista.modelo;


import categoriplus.modelo.Categoria;
import java.util.List;

/**
 * Implementacion de GeneralTableModel para la clase Categoria en el 
 * modelo de tabla.
 * 
 * @author Antonio López Marín
 */
public class CategoriaTableModel extends GeneralTableModel<Categoria>{
    
    private static String[] columNames = {java.util.ResourceBundle.getBundle("lang/lenguajes").getString("NOMBRE")};

    public CategoriaTableModel(List<Categoria> lista) {
        super(columNames, lista);
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return getValueAtRow(rowIndex);
    }
    
    /**
     * Metodo que devuelve la la columna de una fila de la tabla.
     * 
     * @param categoria
     * @param column
     * @return
     */
    @Override
    public Object getColumFromRow(Categoria categoria, int column) {
        return categoria.getName();
    }
    
    /**
     * Metodo que hace update de un elemento de la tabla.
     * 
     * @param object 
     */
    @Override
    public void updateRow(Categoria object) {
        for (Categoria cat : lista) {
            if (cat.getId() == object.getId()) {
                lista.set(lista.indexOf(cat), object);
            }
        }
    }
}
