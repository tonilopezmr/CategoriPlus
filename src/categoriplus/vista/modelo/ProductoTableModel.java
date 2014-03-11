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
import categoriplus.modelo.Producto;
import java.util.List;

/**
 * Implementacion de GeneralTableModel para la clase Producto en el modelo de
 * tabla.
 *
 * @author Antonio López Marín
 */
public class ProductoTableModel extends GeneralTableModel<Producto> {
    //Columnas
    private static String columNames[] = {java.util.ResourceBundle.getBundle("lang/lenguajes").getString("NOMBRE"), java.util.ResourceBundle.getBundle("lang/lenguajes").getString("TIPO"), java.util.ResourceBundle.getBundle("lang/lenguajes").getString("CATEGORIA")};

    /**
     * Constructor con una lista por defecto y se le pasa al super las columnas.
     *
     * @param lista
     */
    public ProductoTableModel(List<Producto> lista) {
        super(columNames, lista);
    }

    /**
     * Devuelve la columna de un producto de la tabla, se utiliza si deseas
     * saber que hay en cada columna de una fila.
     *
     *
     * @param producto
     * @param column
     * @return
     */
    @Override
    public Object getColumFromRow(Producto producto, int column) {
        Object result = null;

        if (producto != null) {
            //Segun la columna devuelvo un valor de el objeto
            switch (column) {
                case 0:
                    result = producto.getName();
                    break;
                case 1:
                    result = producto.getType();
                    break;
                case 2:
                    Categoria categoria = producto.getCategoria();
                    if (categoria != null) {
                        result = categoria.getName();
                    }
                    break;
            }
        }

        return result;
    }

    /**
     * Metodo que devuelve el elemento que esta en una columna de una fila de la
     * tabla.
     *
     * @param rowIndex
     * @param columnIndex
     * @return
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Producto prod = getValueAtRow(rowIndex);
        return getColumFromRow(prod, columnIndex);
    }

    /**
     * Metodo que hace update sobre un objeto de la tabla.
     * 
     * @param object 
     */
    @Override
    public void updateRow(Producto object) {
        for (Producto pro : lista) {
            if (pro.getId() == object.getId()) {
                lista.set(lista.indexOf(pro), object);
            }
        }
    }
}
