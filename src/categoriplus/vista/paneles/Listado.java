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
package categoriplus.vista.paneles;

import categoriplus.vista.LenguajeUtils;
import categoriplus.vista.Principal;
import categoriplus.vista.modelo.GeneralTableModel;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;
import javax.swing.DefaultListSelectionModel;
import javax.swing.event.TableModelEvent;

/**
 * Panel de Listado de Categorias y Productos.
 *
 * Implementa de Objserver porque utiliza el patron Observer.
 *
 * @author Antonio López Marín
 */
public class Listado extends javax.swing.JPanel implements Observer {
    
    static {
        Locale.setDefault(LenguajeUtils.recogerIdioma());
    }
    
    /**
     * Creates new form Listado
     */
    public Listado(Principal vista) {
        this.vista = vista;
        initComponents();
        configcomponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabla.setName("tabla_prod"); // NOI18N
        jScrollPane2.setViewportView(tabla);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables

    private GeneralTableModel tableModel;
    private Principal vista;
    
    /**
     * Metodo que configura los componentes del panel.
     *
     */
    private void configcomponents() {
        //Que no se puedan mover los campos de sitio
        tabla.getTableHeader().setReorderingAllowed(false);
        //Para que no se pueda seleccionar mas de una fila
        tabla.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        //Añado el MouseListener para los toques de raton
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                //Si hacen doble click
                if (e.getClickCount() >= 2) {
                    //Recojo en que fila esta, el click
                    int row = tabla.rowAtPoint(e.getPoint());
                    //Si es mayor o igual a 0, pulso una fila correcta
                    if (row >= 0) {
                        try {
                            //Obtengo el objeto del modelo de la tabla
                            Object object = tableModel.getValueAtRow(row);
                            //Y creo un nuevo panel para ese objeto
                            vista.nuevoPanelModificar(object);
                        } catch (Exception ex) {
                            vista.mostrarError(java.util.ResourceBundle.getBundle("lang/lenguajes").getString("error_formulario_modificar"));
                        }
                    }
                }
            }
        });
    }

    /**
     * Metodo que cambia el modelo de la tabla de forma dinamica.
     *
     * @param tableModel
     */
    public void setTableModel(GeneralTableModel tableModel) {
        this.tableModel = tableModel;
        tabla.setModel(tableModel);
    }

    /**
     * Cuando cambia el modelo este metodo se ejecuta y actualiza la tabla.
     *
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        if (tableModel != null) {
            tabla.tableChanged(new TableModelEvent(tableModel));
        }
    }
}
