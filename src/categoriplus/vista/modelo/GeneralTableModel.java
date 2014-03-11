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

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Clase abstracta que implementa AbstractTableModel, y la defino para 
 * modelos de tabla que sean de alguna clase en concreto.
 * 
 * @author Antonio López Marín
 * @param <T>
 */
public abstract class GeneralTableModel<T> extends AbstractTableModel{

    private String[] columNames;
    protected List<T> lista;
    private boolean isCellEditable;
    
    /**
     * Constructor que le pasa el nombre de las columans y una lista predefinida.
     * 
     * @param columNames
     * @param lista 
     */
    public GeneralTableModel(String[] columNames, List<T> lista) {
        this.columNames = columNames;
        this.lista = lista;       
    }        
    
    public GeneralTableModel(List<T> lista) {
        this.lista = lista;       
    } 
    
    ///////////////////////////////////////
    /////// Metodos redefinidos ///////////
    ///////////////////////////////////////
    
    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return columNames.length;
    }
    
    @Override
    public String getColumnName(int column) {
        return columNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return lista.get(columnIndex).getClass();
    } 

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return this.isCellEditable;
    }
    
    ///////////////////////////////////////
    //////// Metodos añadidos /////////////
    ///////////////////////////////////////
    
    /**
     * Metodo que cambia los nombres de las columnas.
     * 
     * @param columNames 
     */
    public void setColumNames(String[] columNames){
        this.columNames = columNames;
    }
    
    /**
     * Metodo que cambia la lista de la tabla.
     * 
     * @param lista 
     */
    public void setLista(List<T> lista){
        this.lista = lista;
    }
    
    /**
     * Devuelve el objeto que esta en una fila de la tabla.
     * 
     * @param rowIndex
     * @return 
     */
    public T getValueAtRow(int rowIndex){
        return lista.get(rowIndex);
    }
        
    /**
     * Metodo que añade un registro a la tabla.
     * 
     * @param object 
     */
    public void addRow(T object){
        lista.add(object);
    }
    
    /**
     * Metodo que borra un registro de la tabla.
     * 
     * @param object 
     */
    public void removeRow(T object){
        lista.remove(object);
    }
    
    /**
     * Metodo que borra todos los registros de la tabla.
     * 
     */
    public void removeAllRows(){
        lista.removeAll(lista);
    }
    
    /**
     * Metodo que cambia se se puede editar los campos de la tabla o no.
     * 
     * @param enable 
     */
    public void isCellsEditable(boolean enable){
        this.isCellEditable = enable;
    }
    
    /**
     * Metodo abstracto que se tiene que implementar junto al metodo 
     * que no esta definido en esta clase getValueAt que es de la 
     * clase padre (AbstractTableModel).
     * 
     * @see AbstractTableModel#getValueAt(int, int) 
     * @param object
     * @param column
     * @return 
     */    
    public abstract Object getColumFromRow(T object, int column);
    public abstract void updateRow(T object);
}
