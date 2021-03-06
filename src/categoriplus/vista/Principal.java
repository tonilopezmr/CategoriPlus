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
package categoriplus.vista;

import categoriplus.controlador.Controlador;
import categoriplus.dataccesobject.gestor.DBConfig;
import categoriplus.modelo.Categoria;
import categoriplus.modelo.Producto;
import categoriplus.vista.paneles.PanelCategoria;
import categoriplus.vista.paneles.Listado;
import categoriplus.vista.paneles.PanelPrincipal;
import categoriplus.vista.paneles.PanelProducto;
import informes.CargarReporte;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import javax.help.CSH;
import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.help.HelpSetException;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

/**
 * Vista principal, que maneja las pequeñas porciones de vista, como los 
 * diferentes paneles que la componen.
 * 
 * @author Antonio López Marín
 */
public class Principal extends javax.swing.JFrame {

    static {
        Locale.setDefault(LenguajeUtils.recogerIdioma());
    }

    /**
     * Creates new form NewJFrame
     */
    public Principal() {
        inicio();
        initComponents();
        configComponents();
    }
    
    /**
     * Constructor pasandole la configuracion, para al reiniciar, no 
     * vuelva a pedir el usuario y contraseña.
     * 
     * @param config 
     */
    public Principal(DBConfig config) {
        this.config = config;
        initComponents();
        configComponents();
    }
    
    /**
     * Metodo que muestra la vista.
     * 
     */
    public static void mostrar() {
        new Principal().setVisible(true);
    }
    
    /**
     * Metodo que muestra la vista pasandole la configuracion.
     * 
     * @param config 
     */
    public static void mostrar(DBConfig config) {
        new Principal(config).setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        splitPane = new javax.swing.JSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        arbol = new javax.swing.JTree();
        jPanel1 = new javax.swing.JPanel();
        errorLabel = new javax.swing.JLabel();
        progresBar = new javax.swing.JProgressBar();
        menuBar = new javax.swing.JMenuBar();
        menuArchivo = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(781, 506));

        splitPane.setMinimumSize(new java.awt.Dimension(220, 388));

        jScrollPane1.setViewportView(arbol);

        splitPane.setLeftComponent(jScrollPane1);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        errorLabel.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        errorLabel.setBorder(null);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(errorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 525, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(progresBar, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(errorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 3, Short.MAX_VALUE))
            .addComponent(progresBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("lang/lenguajes"); // NOI18N
        menuArchivo.setText(bundle.getString("archivo")); // NOI18N
        menuBar.add(menuArchivo);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(splitPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(splitPane, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTree arbol;
    private javax.swing.JLabel errorLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenu menuArchivo;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JProgressBar progresBar;
    private javax.swing.JSplitPane splitPane;
    // End of variables declaration//GEN-END:variables
    //Variables del JTree
    private DefaultTreeModel arbolModel;
    private DefaultMutableTreeNode crearProducto;
    private DefaultMutableTreeNode listarProducto;
    private DefaultMutableTreeNode crearCategoria;
    private DefaultMutableTreeNode listarCategoria;
    private DefaultMutableTreeNode productoModel;
    private DefaultMutableTreeNode categoriaModel;
    //Paneles
    private PanelPrincipal panelPrincipal;
    private PanelCategoria panelCategoria;
    private PanelProducto panelProducto;
    private Listado panelListado;
    //JMenuItem que muestra la ayuda de usuario
    private JMenuItem ayudaContenidos;
    //Controlador
    private Controlador controller;
    //Mapa de modificaciones
    private HashMap<Object, JPanel> modificaciones;
    //Configuracion que el usuario introdujo
    private DBConfig config;
    //Clase que carga los reportes
    private CargarReporte cargarReporte;

    /**
     * Metodo que se inicia antes de cargar la interfaz.
     */
    private void inicio() {
        final LoginDialog validacion = new LoginDialog();
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validacion.comprobarConexion();
            }
        };
        //Le añado el evento de que llame al metodo de comprobar conexion
        validacion.addActionListener(listener);

        //Muestro el dialogo y espero su respuesta
        DBConfig config = validacion.show();

        //Si es distinto de null.
        if (config != null) {
            this.config = config;
        } else {
            //Sino le dieron a cancelar
            System.exit(0);
        }
    }

    /**
     * Metodo que configura los componentes de la vista.
     *
     */
    private void configComponents() {
        try {
            setTitle(java.util.ResourceBundle.getBundle("lang/lenguajes").getString("CATEGORIPLUS"));
            setResizable(false);
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            setLocation(screenSize.width / 4, screenSize.height / 5);
            
            //Inicio los componentes de la vista
            iniciarJTree();
            iniciarMenu();
            
            try {
                setupHelp();
            } catch (Exception e) {
                mostrarError(java.util.ResourceBundle.getBundle("lang/lenguajes").getString("error_cargar_ayuda"));
                e.printStackTrace();
            }

            try {
                //Inicio el controlador
                controller = new Controlador(this, config);
            } catch (Exception e) {
                mostrarError(java.util.ResourceBundle.getBundle("lang/lenguajes").getString("error_db"));
                e.printStackTrace();
            }

            //Añado el panel principal al splitPane
            panelPrincipal = new PanelPrincipal();
            splitPane.setRightComponent(panelPrincipal);

            //Inicio los demas paneles
            panelCategoria = new PanelCategoria(this, controller);
            panelProducto = new PanelProducto(this, controller);
            panelListado = new Listado(this);

            //Añado los paneles a la lista de Observers
            controller.addObserver(panelCategoria);
            controller.addObserver(panelProducto);
            controller.addObserver(panelListado);

            //Inicio el reporte
            cargarReporte = new CargarReporte(this, new VistaInforme());

            //Mapa que guardamos el objeto que maneja cada panel de modificar
            modificaciones = new HashMap<Object, JPanel>();

            //ProgressBar que no se vea, solo cuando cargue
            progresBar.setVisible(false);
            progresBar.setIndeterminate(true);
            progresBar.setStringPainted(true);
            progresBar.setString(java.util.ResourceBundle.getBundle("lang/lenguajes").getString("cargando"));
        } catch (Exception ex) {
            mostrarError(java.util.ResourceBundle.getBundle("lang/lenguajes").getString("error_general."));
            ex.printStackTrace();
        }
    }

    /**
     * Inicio el JTree
     */
    private void iniciarJTree() {
        //Crear y listar
        crearProducto = new DefaultMutableTreeNode(java.util.ResourceBundle.getBundle("lang/lenguajes").getString("CREAR"));
        listarProducto = new DefaultMutableTreeNode(java.util.ResourceBundle.getBundle("lang/lenguajes").getString("LISTAR"));
        crearCategoria = new DefaultMutableTreeNode(java.util.ResourceBundle.getBundle("lang/lenguajes").getString("CREAR"));
        listarCategoria = new DefaultMutableTreeNode(java.util.ResourceBundle.getBundle("lang/lenguajes").getString("LISTAR"));

        //Producto y categoriaModel, a los dos les añado el crear y listar
        productoModel = new DefaultMutableTreeNode(java.util.ResourceBundle.getBundle("lang/lenguajes").getString("PRODUCTO"));
        productoModel.add(crearProducto);
        productoModel.add(listarProducto);

        categoriaModel = new DefaultMutableTreeNode(java.util.ResourceBundle.getBundle("lang/lenguajes").getString("CATEGORIA"));
        categoriaModel.add(crearCategoria);
        categoriaModel.add(listarCategoria);

        //El nodo raiz es el nombre de la aplicacion
        DefaultMutableTreeNode categoriPlus = new DefaultMutableTreeNode(java.util.ResourceBundle.getBundle("lang/lenguajes").getString("CATEGORIPLUS"));
        categoriPlus.add(categoriaModel);
        categoriPlus.add(productoModel);
        
        //Creo el modelo del arbol
        arbolModel = new DefaultTreeModel(categoriPlus);
        
        //Añado el modelo al arbol
        arbol.setModel(arbolModel);
        //Solo se puede seleccionar uno
        arbol.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        arbol.setName("jtree");        
        //Añado el listener de seleccion
        arbol.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                //Cuando le pulsen ah crear o listar comprueba quien es su padre 
                TreePath parent = e.getPath().getParentPath();
                if (parent != null) {
                    //Accion listar/crear
                    DefaultMutableTreeNode accion = (DefaultMutableTreeNode) e.getPath().getLastPathComponent();
                    //Padre productoModel/categoriaModel
                    DefaultMutableTreeNode padre = (DefaultMutableTreeNode) parent.getLastPathComponent();
                    try {
                        //Cambia el panel
                        mostrarPanel(accion, padre);
                        mostrarError("");
                    } catch (Exception ex) {
                        mostrarError(java.util.ResourceBundle.getBundle("lang/lenguajes").getString("error_cargar_formulario"));
                        ex.printStackTrace();
                    }
                }
            }
        });
    }

    /**
     * Metodo que inicia el JMenuBar, añadiendole los submenus y items que se es
     * requerido.
     *
     */
    private void iniciarMenu() {

        //Submenu lñenguajes
        JMenu lenguajes = new JMenu(java.util.ResourceBundle.getBundle("lang/lenguajes").getString("LENGUAJES"));
        lenguajes.setMnemonic(KeyEvent.VK_L);

        //Item de idioma español
        JMenuItem espanish = new JMenuItem(java.util.ResourceBundle.getBundle("lang/lenguajes").getString("espanish"));
        espanish.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,
                ActionEvent.CTRL_MASK));
        espanish.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Locale.setDefault(LenguajeUtils.cambiarIdioma("es", "ES"));
                reiniciar();
            }
        });

        //Item de idioma ingles
        JMenuItem ingles = new JMenuItem(java.util.ResourceBundle.getBundle("lang/lenguajes").getString("INGLES"));
        ingles.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,
                ActionEvent.CTRL_MASK));
        ingles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Locale.setDefault(LenguajeUtils.cambiarIdioma("en", "GB"));
                reiniciar();
            }
        });

        //Añado los lenguajes
        lenguajes.add(espanish);
        lenguajes.add(ingles);

        //SubMenu de informes
        JMenu informes = new JMenu(java.util.ResourceBundle.getBundle("lang/lenguajes").getString("INFORMES"));
        informes.setMnemonic(KeyEvent.VK_I);

        //Lista de categorias
        JMenuItem listaCat = new JMenuItem(java.util.ResourceBundle.getBundle("lang/lenguajes").getString("lista_categorias"));
        listaCat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    iniciarInformeCategorias();
                } catch (SQLException | ClassNotFoundException ex) {
                    mostrarError(java.util.ResourceBundle.getBundle("lang/lenguajes").getString("error_informes"));
                    ex.printStackTrace();
                }
            }
        });

        //Lista fichas de productos
        JMenuItem fichaProductos = new JMenuItem(java.util.ResourceBundle.getBundle("lang/lenguajes").getString("ficha_productos"));
        fichaProductos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    iniciarInformeProductos();
                } catch (SQLException | ClassNotFoundException ex) {
                    mostrarError(java.util.ResourceBundle.getBundle("lang/lenguajes").getString("error_informes"));
                    ex.printStackTrace();
                }
            }
        });

        //Informe final con todo
        JMenuItem informeFinal = new JMenuItem(java.util.ResourceBundle.getBundle("lang/lenguajes").getString("informe_final"));
        ingles.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,
                ActionEvent.CTRL_MASK));
        informeFinal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    iniciarInformeFinal();
                } catch (SQLException | ClassNotFoundException ex) {
                    mostrarError(java.util.ResourceBundle.getBundle("lang/lenguajes").getString("error_informes"));
                    ex.printStackTrace();
                }
            }
        });

        //Añado los informes
        informes.add(listaCat);
        informes.add(fichaProductos);
        informes.add(informeFinal);

        //Menu para poder salir
        JMenuItem salir = new JMenuItem(java.util.ResourceBundle.getBundle("lang/lenguajes").getString("SALIR"));
        salir.setMnemonic(KeyEvent.VK_S);
        salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        //Menu de ayuda
        JMenu ayuda = new JMenu(java.util.ResourceBundle.getBundle("lang/lenguajes").getString("ayuda"));  
        ayudaContenidos = new JMenuItem(java.util.ResourceBundle.getBundle("lang/lenguajes").getString("ayuda_contenidos"));
        JMenuItem about = new JMenuItem(java.util.ResourceBundle.getBundle("lang/lenguajes").getString("acerca_de"));
         about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(getContentPane(), 
                        java.util.ResourceBundle.getBundle("lang/lenguajes").getString("creado_por_mi"), 
                        java.util.ResourceBundle.getBundle("lang/lenguajes").getString("acerca_de"),  
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
        //Añado los items de ayuda
        ayuda.add(ayudaContenidos);
        ayuda.add(about);
        
        //Añado al menubar la ayuda
        menuBar.add(ayuda);
        
        //Mnemonic
        menuArchivo.setMnemonic(KeyEvent.VK_A);

        //Añado los menus
        menuArchivo.add(informes);
        menuArchivo.add(lenguajes);
        menuArchivo.addSeparator();
        menuArchivo.add(salir);
    }
    
    /**
     * Metodo prepara la ayuda de usuario, y añado al item que abrira la ayuda
     * su action listener.
     * 
     * @throws MalformedURLException
     * @throws HelpSetException 
     */
    public void setupHelp() throws MalformedURLException, HelpSetException {
        File archivo = new File("src/javahelp/help_set.jh");        
        URL hsURL = archivo.toURI().toURL();
//        URL hsURL = ClassLoader.getSystemResource("javahelp/help_set.jh");
        
        //Leemos el HelpSet de Configuración
        HelpSet helpset = new HelpSet(getClass().getClassLoader(),hsURL);
        HelpBroker helpbroker = helpset.createHelpBroker();

        // Añadimos la ayuda a los botones
        // Al pulsar sobre el boton del menu ayuda se muestra 
        //helpbroker.enableHelpOnButton(helpMenuItem, "aplicacion", helpset);
        ayudaContenidos.addActionListener(new CSH.DisplayHelpFromSource( helpbroker ));

        //Al presionar F1 sobre la ventana se muestra la ayuda
        helpbroker.enableHelpKey(getContentPane(), "index", helpset);      
    }
    
    /**
     * Metodo que hace visible o no el progreso.
     * 
     * @param value 
     */
    public void progresVisible(boolean value){
        progresBar.setVisible(value);
    }
    
    /**
     * Metodo que pregunta la conexion de informe, y devuelve la conexion.
     * 
     * exitosa.
     * @return 
     */
    public DBConfig preguntarConexionInforme() {
        final LoginDialog validacion = new LoginDialog();
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validacion.comprobarConexionInforme();
            }
        };
        //Le añado el evento de que llame al metodo de comprobar conexion
        validacion.addActionListener(listener);

        //Muestro el dialogo y espero su respuesta
        DBConfig config = validacion.showInforme(this.config);

        //Si es distinto de null, pongo la nueva configuracion
        if (config != null) {
            return config;
        }
        return null;
    }

    /**
     * Metodo que inicia el informe de categorias.
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void iniciarInformeCategorias() throws SQLException, ClassNotFoundException {
        DBConfig conf = preguntarConexionInforme();
        if (conf != null) {
            cargarReporte.cargarReporteCategorias(Controlador.getReportConexion(conf));
        }
    }

    /**
     * Metodo que inicia el informe de productos.
     *
     * @throws SQLException
     * @throws ClassNotFoundException Metod
     */
    public void iniciarInformeProductos() throws SQLException, ClassNotFoundException {
        DBConfig conf = preguntarConexionInforme();
        if (conf != null) {
            cargarReporte.cargarReporeProductos(Controlador.getReportConexion(conf));
        }
    }

    /**
     * Metodo que inicia el informe final con todos los productos y categorias.
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void iniciarInformeFinal() throws SQLException, ClassNotFoundException {
        DBConfig conf = preguntarConexionInforme();
        if (conf != null) {
            cargarReporte.cargarReporteFinal(Controlador.getReportConexion(conf));
        }
    }

    /**
     * Metodo que reinicia la ventana.
     *
     */
    private void reiniciar() {
        this.dispose();
        Principal.mostrar(config);
    }

    /**
     * Metodo que pone un mensaje de error en la pantalla principal.
     *
     * @param mensaje
     */
    public void mostrarError(String mensaje) {
        errorLabel.setText("<html><font color=\"red\">" + mensaje + "</font></html>");
    }
    
    /**
     * Metodo que pone un mensaje de informacion en la pantalla principal.
     *
     * @param mensaje
     */
    public void mostrarInformacion(String mensaje){
        errorLabel.setText("<html><font color=\"blue\">" + mensaje + "</font></html>");
    }
    
    /**
     * Quita el tab, pasandole su panel.
     *
     * @param panel
     */
    public void quitarTab(JPanel panel) {
        quitarModificacion(panel);
        panelPrincipal.removeTab(panel);
    }

    /**
     * Metodo que quita del mapa de modificaciones, el registro, ya que el panel
     * se cerro.
     *
     * @param panel
     */
    public void quitarModificacion(JPanel panel) {
        //Recojo una lista Set<Entry<Object, JPanel>>
        Set lista = modificaciones.entrySet();
        //Recojo su iterator
        Iterator it = lista.iterator();
        //Recorro el iterator
        while (it.hasNext()) {
            //Map.Entry clave valor de modificaciones
            Map.Entry me = (Map.Entry) it.next();
            //Si el valor es igual al panel que se haca cerrado
            if (me.getValue().equals(panel)) {
                //quito el panel de modificaciones
                modificaciones.remove(me.getKey());
                break;
            }
        }
    }

    /**
     * Metodo que muestra el panel segun donde se pinche sobre el JTree
     *
     * @param accion
     * @param parent
     * @throws java.lang.Exception
     */
    public void mostrarPanel(DefaultMutableTreeNode accion,
            DefaultMutableTreeNode parent) throws Exception {
        if (parent.equals(productoModel)) {
            mostrarPanelProducto(accion);
        } else if (parent.equals(categoriaModel)) {
            mostrarPanelCategoria(accion);
        }
    }

    /**
     * Metodos que muestran un panel de producto segun la accion elegida.
     *
     * @param accion
     * @throws java.lang.Exception
     */
    public void mostrarPanelProducto(DefaultMutableTreeNode accion) throws Exception {
        //por si el primer elemento del tab es un panel de mofidicar
        quitarModificacion(panelPrincipal.getFirstTab());
        if (accion.equals(crearProducto)) {
            panelPrincipal.cambiarPanel(java.util.ResourceBundle.getBundle("lang/lenguajes").getString("crear_producto"), panelProducto);
        } else if (accion.equals(listarProducto)) {
            //Cambiar el modelo de la tabla del listado
            panelListado.setTableModel(controller.getProductoTableModel());
            panelPrincipal.cambiarPanel(java.util.ResourceBundle.getBundle("lang/lenguajes").getString("listar_producto"), panelListado);
        }
        //Cambia el boton por defecto
        panelProducto.defaultButton();
    }

    /**
     * Metodo que muestran un panel de categoria segun la accion elegida.
     *
     * @param accion
     * @throws java.lang.Exception
     */
    public void mostrarPanelCategoria(DefaultMutableTreeNode accion) throws Exception {
        //por si el primer elemento del tab es un panel de mofidicar
        quitarModificacion(panelPrincipal.getFirstTab());
        if (accion.equals(crearCategoria)) {
            panelPrincipal.cambiarPanel(java.util.ResourceBundle.getBundle("lang/lenguajes").getString("crear_categoria"), panelCategoria);
        } else if (accion.equals(listarCategoria)) {
            //Cambiar el modelo de la tabla de listado
            panelListado.setTableModel(controller.getCategoriaTableModel());
            panelPrincipal.cambiarPanel(java.util.ResourceBundle.getBundle("lang/lenguajes").getString("listar_categoria"), panelListado);
        }
        //Cambia el boton por defecto
        panelCategoria.defaultButton();
        errorLabel.setText("");
    }

    /**
     * Metodo añade un nuevo panel al tabbPane.
     *
     * @param object
     * @throws java.lang.Exception
     */
    public void nuevoPanelModificar(Object object) throws Exception {
        if (controller.isProductObject(object)) {
            modifProducto(object);
        } else {
            modifCategoria(object);
        }
        errorLabel.setText("");
    }

    /**
     * Metodo que comprueba si ya hay un panel para ese objeto y no lo añade
     * sino que lo selecciona, sino es una locura, se empiezan a abrir pestañas.
     *
     * @param object
     */
    private void modifProducto(Object object) throws Exception {
        //Si el producto no esta en uso..
        if (!modificaciones.containsKey(object)) {
            PanelProducto panel = new PanelProducto(this, controller, (Producto) object);
            controller.addObserver(panel);
            panelPrincipal.addTab(java.util.ResourceBundle.getBundle("lang/lenguajes").getString("modificar") + object.toString(), panel);
            //Pone la categoria como usada
            modificaciones.put(object, panel);
        } else {
            //Si esta en uso lo selecciona
            panelPrincipal.setSelectPane(modificaciones.get(object));
        }
    }

    /**
     * Metodo que comprueba si ya hay un panel para ese objeto y no lo añade
     * sino que lo selecciona, sino es una locura, se empiezan a abrir pestañas.
     *
     * @param object
     */
    private void modifCategoria(Object object) throws Exception {
        //Si la categoria no esta en uso..
        if (!modificaciones.containsKey(object)) {
            PanelCategoria panel = new PanelCategoria(this, controller, (Categoria) object);
            controller.addObserver(panel);
            panelPrincipal.addTab(java.util.ResourceBundle.getBundle("lang/lenguajes").getString("modificar") + object.toString(), panel);
            //Pone la categoria como usada
            modificaciones.put(object, panel);
        } else {
            //Si esta en uso lo selecciona
            panelPrincipal.setSelectPane(modificaciones.get(object));
        }
    }
}