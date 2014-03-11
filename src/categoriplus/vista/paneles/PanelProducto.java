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

import categoriplus.controlador.Controlador;
import categoriplus.controlador.listeners.ButtonListener;
import categoriplus.modelo.Categoria;
import categoriplus.modelo.Producto;
import categoriplus.modelo.Producto.Type;
import categoriplus.vista.Principal;
import categoriplus.vista.validators.StringValidator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JComponent;
import javax.swing.SpinnerNumberModel;

/**
 * Panel de Producto, que puede crear o modificar una categoria.
 *
 * Implementa de Objserver porque utiliza el patron Observer.
 *
 * @author Antonio López Marín
 */
public class PanelProducto extends javax.swing.JPanel implements Formulario, Observer {

    /**
     * Constructor para usar el panel para crear productos.
     *
     * @param vista
     * @param controller
     */
    public PanelProducto(Principal vista, Controlador controller) throws Exception {
        this.vista = vista;
        this.actionCode = PanelProducto.CREAR;
        this.controller = controller;
        initComponents();
        configComponents();
    }

    /**
     * Consturctor para usar el panel para modificar un producto.
     *
     * @param vista
     * @param controller
     * @param producto
     * @throws java.lang.Exception
     */
    public PanelProducto(Principal vista, Controlador controller, Producto producto) throws Exception {
        this.vista = vista;
        this.actionCode = PanelProducto.MODIFICAR;
        this.controller = controller;
        this.productoPanel = producto;
        initComponents();
        configComponents();
        cargarProducto(producto);
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
        jTextArea1 = new javax.swing.JTextArea();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        tituloLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        nombreField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        precioVentaSp = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        precioCompraSp = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        listaCategorias = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        isVendibleCheck = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        descripcionArea = new javax.swing.JTextArea();
        guardarBtn = new botonjavabeans.PuntoBoton();
        cancelarBtn = new botonjavabeans.PuntoBoton();
        errorLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        consumibleRBtn = new javax.swing.JRadioButton();
        servicioRBtn = new javax.swing.JRadioButton();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setMinimumSize(new java.awt.Dimension(590, 426));

        tituloLabel.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        tituloLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("lang/lenguajes"); // NOI18N
        tituloLabel.setText(bundle.getString("PRODUCTO")); // NOI18N

        jLabel1.setText(bundle.getString("NOMBRE")); // NOI18N
        jLabel1.setToolTipText(bundle.getString("nom_pro_tooltip")); // NOI18N

        nombreField.setName("nombre_prod"); // NOI18N

        jLabel2.setLabelFor(precioVentaSp);
        jLabel2.setText(bundle.getString("precio_venta")); // NOI18N
        jLabel2.setToolTipText(bundle.getString("precio_venta_tooltip")); // NOI18N

        jLabel3.setLabelFor(precioCompraSp);
        jLabel3.setText(bundle.getString("precio_compra")); // NOI18N
        jLabel3.setToolTipText(bundle.getString("precio_compra_tooltip")); // NOI18N

        jLabel4.setText(bundle.getString("CATEGORIA")); // NOI18N

        listaCategorias.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        listaCategorias.setName("cat_list"); // NOI18N

        jLabel5.setText(bundle.getString("TIPO")); // NOI18N
        jLabel5.setToolTipText("Es de tipo consumible si se puede consumir de cualquier forma, \ny servicio si es un trabajo que se presta.");

        isVendibleCheck.setText(bundle.getString("SE PUEDE VENDER")); // NOI18N
        isVendibleCheck.setToolTipText("Si el producto se puede vender, de lo contrario es un producto\nque se compra para elaborar otro producto final o similar.");
        isVendibleCheck.setName("sale_check"); // NOI18N
        isVendibleCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                isVendibleCheckActionPerformed(evt);
            }
        });

        jLabel6.setText(bundle.getString("DESCRIPCION")); // NOI18N
        jLabel6.setToolTipText(bundle.getString("des_pro_tooltip")); // NOI18N

        descripcionArea.setColumns(20);
        descripcionArea.setRows(5);
        descripcionArea.setName("descripcion"); // NOI18N
        jScrollPane2.setViewportView(descripcionArea);

        guardarBtn.setName("guardar_prod"); // NOI18N
        guardarBtn.setText(bundle.getString("GUARDAR")); // NOI18N
        guardarBtn.setToolTipText("");
        guardarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarBtnActionPerformed(evt);
            }
        });

        cancelarBtn.setName(""); // NOI18N
        cancelarBtn.setText(bundle.getString("CANCELAR")); // NOI18N
        cancelarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarBtnActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        buttonGroup1.add(consumibleRBtn);
        consumibleRBtn.setSelected(true);
        consumibleRBtn.setText(bundle.getString("CONSUMIBLE")); // NOI18N
        consumibleRBtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        consumibleRBtn.setName("consu_radio"); // NOI18N
        consumibleRBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consumibleRBtnActionPerformed(evt);
            }
        });

        buttonGroup1.add(servicioRBtn);
        servicioRBtn.setText(bundle.getString("SERVICIO")); // NOI18N
        servicioRBtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        servicioRBtn.setName("servicio_radio"); // NOI18N
        servicioRBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                servicioRBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(consumibleRBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(servicioRBtn)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(consumibleRBtn)
                    .addComponent(servicioRBtn))
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(guardarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(cancelarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(137, 137, 137))
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(errorLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(listaCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane2)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(precioVentaSp, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                                .addComponent(jLabel3))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(nombreField, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(isVendibleCheck)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(precioCompraSp)
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addGap(22, 22, 22))
            .addGroup(layout.createSequentialGroup()
                .addComponent(tituloLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(tituloLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(nombreField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(precioVentaSp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(precioCompraSp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(listaCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(isVendibleCheck))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(guardarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cancelarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(errorLabel)
                        .addGap(106, 106, 106))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void consumibleRBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consumibleRBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_consumibleRBtnActionPerformed

    private void servicioRBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_servicioRBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_servicioRBtnActionPerformed

    private void isVendibleCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_isVendibleCheckActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_isVendibleCheckActionPerformed

    private void guardarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_guardarBtnActionPerformed

    private void cancelarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cancelarBtnActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private botonjavabeans.PuntoBoton cancelarBtn;
    private javax.swing.JRadioButton consumibleRBtn;
    private javax.swing.JTextArea descripcionArea;
    private javax.swing.JLabel errorLabel;
    private botonjavabeans.PuntoBoton guardarBtn;
    private javax.swing.JCheckBox isVendibleCheck;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JComboBox listaCategorias;
    private javax.swing.JTextField nombreField;
    private javax.swing.JSpinner precioCompraSp;
    private javax.swing.JSpinner precioVentaSp;
    private javax.swing.JRadioButton servicioRBtn;
    private javax.swing.JLabel tituloLabel;
    // End of variables declaration//GEN-END:variables
    //Constantes
    final public static String PANEL_NAME = java.util.ResourceBundle.getBundle("lang/lenguajes").getString("PRODUCTO");
    final public static int MODIFICAR = 100;        //Puede modificar
    final public static int CREAR = 123;            //Puede crear
    final public static int NONE_CODE = -10;
    //Vista principal
    private Principal vista;
    //Controlador
    private Controlador controller;
    private int actionCode;
    //Codigo de producto a modificar
    private Producto productoPanel;

    //Si los campos son validos
    private boolean isNombreValid = false;
    private boolean isDesValid = false;

    /**
     * Metodo que configra los componentes.
     */
    private void configComponents() throws Exception {
        //Por defecto esta deshabilitado
        guardarBtn.setEnabled(false);

        //Por defecto esta seleccionado consumible y vendible
        isVendibleCheck.setSelected(true);

        //Añado los modelos a los spinners
        precioCompraSp.setModel(new SpinnerNumberModel(0, 0.0, 999999999E2, 0.01));
        precioVentaSp.setModel(new SpinnerNumberModel(0, 0.0, 999999999E2, 0.01));

        //Verifica el nombre
        StringValidator verifier = new StringValidator(this);
        nombreField.addFocusListener(verifier);
        nombreField.addKeyListener(verifier);
        descripcionArea.addFocusListener(verifier);
        descripcionArea.addKeyListener(verifier);

        //Listener de los botones guardar y cancelar para que se cierre el tab
        ButtonListener btnListener = new ButtonListener(vista);

        //Inicio el listener
        cancelarBtn.addActionListener(btnListener);
        cancelarBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarPanel();
            }
        });

        //Creo el listener de guardado de datos
        guardarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //Si la ccion es de crear, crea un producto
                    if (actionCode == CREAR) {
                        controller.crearProducto(recogerDatosPanel());
                        limpiarPanel();
                        notifyInfo("Se creo correctamente.");
                    } else {
                        //Sino es de modificar y modifica un producto
                        modificar();
                    }
                } catch (Exception ex) {
                    vista.mostrarError(java.util.ResourceBundle.getBundle("lang/lenguajes").getString("error_guardar_producto"));
                }
            }
        });
        guardarBtn.addActionListener(btnListener);
        //Se cargan todas las categorias
        cargarCategorias();
    }

    private void modificar() throws Exception {
        Producto producto = recogerDatosPanel();
        producto.setId(productoPanel.getId());
        if (!producto.equals(this.productoPanel)) {
            controller.modificarProducto(producto);
            limpiarPanel();
            notifyInfo("Se modifico correctamente.");
        }
    }

    /**
     * Metodo que carga los datos en el panel para ser editados.
     *
     * @param producto
     */
    private void cargarProducto(Producto producto) {
        nombreField.setText(producto.getName());
        precioVentaSp.setValue(producto.getListPrice());
        precioCompraSp.setValue(producto.getStandardPrice());
        descripcionArea.setText(producto.getDescription());
        isVendibleCheck.setSelected(producto.isSaleOk());
        seleccionarTypoProducto(producto.getType());
        seleccionarCategoria(producto.getCategoria());
        guardarBtn.setEnabled(true);
    }

    /**
     * Metodo que recoje los datos del panel y devuelve un producto completo.
     *
     * @return
     */
    private Producto recogerDatosPanel() {
        Producto producto = new Producto();

        producto.setName(nombreField.getText());
        producto.setListPrice((double) precioVentaSp.getValue());
        producto.setStandardPrice((double) precioCompraSp.getValue());
        producto.setDescription(descripcionArea.getText());
        producto.setSaleOk(isVendibleCheck.isSelected());
        producto.setType(getTypeProductoPanel());
        producto.setCategoria(getCategoriaPanel());

        return producto;
    }

    /**
     * Metodo que devuelve la categoria que esta seleccionada en el panel.
     *
     * @return
     */
    public Categoria getCategoriaPanel() {
        Categoria categoria = (Categoria) listaCategorias.getSelectedItem();

        if (categoria.getId() == NONE_CODE) {
            categoria = null;
        }

        return categoria;
    }

    /**
     * Metodo que selecciona la categoria en el panel que se le pasa por
     * parametros.
     *
     * @param categoria
     */
    public void seleccionarCategoria(Categoria categoria) {
        if (categoria != null) {
            int count = listaCategorias.getItemCount();
            for (int i = 0; i < count; i++) {
                Categoria cat = (Categoria) listaCategorias.getItemAt(i);
                if (cat.getId() == categoria.getId()) {
                    listaCategorias.setSelectedIndex(i);
                }
            }
//            listaCategorias.setSelectedItem(categoria);
        } else {
            listaCategorias.setSelectedIndex(0); 
        }
    }

    /**
     * Metodo que recoje el tipo de producto del panel.
     *
     * @return
     */
    public Type getTypeProductoPanel() {
        Type tipo;

        if (consumibleRBtn.isSelected()) {
            tipo = Type.CONSUMIBLE;
        } else {
            tipo = Type.SERVICIO;
        }

        return tipo;
    }

    /**
     * Metodo que se le pasa un tipo de producto y se selecciona.
     *
     * @param tipo
     */
    public void seleccionarTypoProducto(Type tipo) {
        //Si tipo es consumible que se seleccione su boton
        if (tipo == Type.CONSUMIBLE) {
            consumibleRBtn.setSelected(true);
        } else {
            //Si no el otro boton
            servicioRBtn.setSelected(true);
        }
    }

    /**
     * Metodo que carga todas las categorias, para seleccionarla como padre.
     *
     */
    public void cargarCategorias() throws Exception {
        //Primero quito todas las categorias y las vuelvo a llenar
        listaCategorias.removeAllItems();
        //Recojo una lista de categorias
        List<Categoria> cats = controller.listarCategorias();

        //Y relleno las categorias padre si hay
        for (Categoria categoria : cats) {
            listaCategorias.addItem(categoria);
        }
    }

    /**
     * Metodo que limpia el panel
     */
    public void limpiarPanel() {
        nombreField.setText("");
        precioVentaSp.setValue(0.0);
        precioCompraSp.setValue(0.0);
        descripcionArea.setText("");
        isVendibleCheck.setSelected(true);
        seleccionarTypoProducto(Type.CONSUMIBLE);
        listaCategorias.setSelectedIndex(0);
        notifyError("");
        notifyInfo("");
    }

    /**
     * Metodo que verifica si se puede poner enable el boton de guardar o no.
     *
     */
    public void botonGuardar() {
        if (listaCategorias.getItemCount() == 0) {
            notifyError(java.util.ResourceBundle.getBundle("lang/lenguajes").getString("crear_producto_info")
                    + java.util.ResourceBundle.getBundle("lang/lenguajes").getString("ANTES."));
        } else {
            if (isNombreValid && isDesValid) {
                guardarBtn.setEnabled(true);
            }
        }
    }

    /**
     * Metodo se ejecuta cuando el modelo cambia.
     *
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        try {
            cargarCategorias();
            if (actionCode == MODIFICAR) {
                seleccionarCategoria(controller.getProducto(productoPanel.getId()).getCategoria());
            }
        } catch (Exception ex) {
            vista.mostrarError(java.util.ResourceBundle.getBundle("lang/lenguajes").getString("error_cargar_cats"));
        }
    }

    /**
     * Metodo que muestra en la vista principal un mensaje de informacion.
     *
     * @param msg
     */
    @Override
    public void notifyInfo(String msg) {
        vista.mostrarInformacion(msg);
    }

    /**
     * Metodo que pone en el panel un mensaje de error.
     *
     * @param msg
     */
    @Override
    public void notifyError(String msg) {
        errorLabel.setText("<html><font color=\"red\">" + msg + "</font></html>");
    }

    /**
     * El primer componente que tiene el foco en este formulario es el nombre.
     */
    @Override
    public void initalFocus() {
        nombreField.requestFocus();
    }

    /**
     * Metodo que pone el boton por defecto, tiene que ir alternandose, porque
     * en la misma vista puede haber varios en cada momento.
     *
     */
    @Override
    public void defaultButton() {
        //Boton por defecto el de guardar
        vista.getRootPane().setDefaultButton(guardarBtn);
    }

    @Override
    public void valid(JComponent valid) {
        if (valid.equals(nombreField)) {
            isNombreValid = true;
        } else {
            isDesValid = true;
        }
        botonGuardar();
        errorLabel.setText("");
    }

    @Override
    public void invalid(JComponent invalid) {
        if (invalid.equals(nombreField)) {
            notifyError(java.util.ResourceBundle.getBundle("lang/lenguajes").getString("nombre_sin_espacios"));
            isNombreValid = false;
        } else {
            notifyError(java.util.ResourceBundle.getBundle("lang/lenguajes").getString("escriba_descripcion"));
            isDesValid = false;
        }
        guardarBtn.setEnabled(false);
    }
}
