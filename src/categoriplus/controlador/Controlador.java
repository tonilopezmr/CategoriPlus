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
package categoriplus.controlador;

import categoriplus.dataccesobject.gestor.DBConfig;
import categoriplus.dataccesobject.gestor.GestorDAOSingleton;
import categoriplus.dataccesobject.interfaces.CategoriaDAO;
import categoriplus.dataccesobject.interfaces.ProductoDAO;
import categoriplus.factory.interfaces.FactoryDAO;
import categoriplus.modelo.Categoria;
import categoriplus.modelo.Producto;
import categoriplus.vista.Principal;
import categoriplus.vista.modelo.CategoriaTableModel;
import categoriplus.vista.modelo.ProductoTableModel;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Observable;

/**
 * Clase controlador, que controla la vista y las conexiones 
 * con la base de datos.
 * 
 * @author Antonio López Marín
 */
public class Controlador extends Observable{
    
    //Modelo del JTable
    public CategoriaTableModel catTableModel;
    public ProductoTableModel proTableModel;
    
    //Vista principal
    private final Principal vista;
    
    //Modelo de la base de datos
    private final CategoriaDAO categorias;
    private final ProductoDAO productos;
    
    /**
     * Constructor del controlador donde se le pasa la vista y inicia las
     * variables.
     * 
     * @param vista 
     * @param config 
     * @throws java.lang.Exception 
     */
    public Controlador(Principal vista, DBConfig config) throws Exception {
        this.vista = vista;
        
        //Recupero los modelos
        FactoryDAO factory = GestorDAOSingleton.getInstance().confGestor(config)
                .getFactoriaDAO();
        categorias = factory.getCategoriaDAO();
        productos = factory.getProductoDAO();
        
        //Intancio el modelo de tabla
        catTableModel = new CategoriaTableModel(categorias.getAll());
        proTableModel = new ProductoTableModel(productos.getAll());
    }
    
    /**
     * Clase que devuelve true o false dependiendo de si el objeto 
     * es de tipo producto o no.
     * 
     * @param obj
     * @return 
     */
    public boolean isProductObject(Object obj){
        return obj instanceof Producto;
    }
    
    /**
     * Metodo que devuelve si un objeto es de tipo categoria o no.
     * 
     * @param obj
     * @return 
     */
    public boolean isCategoriaObject(Object obj){
        return obj instanceof Categoria;
    }
    
    /**
     * Metodo que devuelve el modelo de tabla de categoria.
     * 
     * @return 
     * @throws java.lang.Exception 
     */
    public CategoriaTableModel getCategoriaTableModel() throws Exception{   
        return catTableModel;
    }
    
    /**
     * Metodo que devuelve el modelo de tabla de producto.
     * 
     * @return 
     * @throws java.lang.Exception 
     */
    public ProductoTableModel getProductoTableModel() throws Exception{
        return proTableModel;
    }
        
    /**
     * Crea una categoria pasandole una categoria por parametros.
     * 
     * @param categoria 
     * @throws java.lang.Exception 
     */
    public void crearCategoria(Categoria categoria) throws Exception{
        categorias.insert(categoria);
        nuevaModificacion();
    }
    
    /**
     * Modifica una categoria, pasandole la categoria modificada por parametros.
     * 
     * @param categoria 
     * @throws java.lang.Exception 
     */
    public void modificarCategoria(Categoria categoria) throws Exception{
        categorias.update(categoria);   
        nuevaModificacion();
    }
    
    /**
     * Metodo que devuelve una lista de todas las categorias.
     * 
     * @return 
     * @throws java.lang.Exception 
     */
    public List<Categoria> listarCategorias() throws Exception{        
        return categorias.getAll();
    }
    
    /**
     * Devuelve la categoria con ese id.
     * 
     * @param id
     * @return 
     * @throws java.lang.Exception 
     */
    public Categoria getCategoria(int id) throws Exception{
        return categorias.read(id);
    }
    
    /**
     * Crea un producto pasandoselo por parametros.
     * 
     * @param producto 
     * @throws java.lang.Exception 
     */
    public void crearProducto(Producto producto) throws Exception{
        productos.insert(producto);
        nuevaModificacion();
    }
    
    /**
     * Modifica un producto.
     * 
     * @param producto 
     * @throws java.lang.Exception 
     */
    public void modificarProducto(Producto producto) throws Exception{
        productos.update(producto);
        nuevaModificacion();
    }
    
    /**
     * Devuelve una lista de todos los productos.
     * 
     * @return 
     * @throws java.lang.Exception 
     */
    public List<Producto> listarProductos() throws Exception{
        return productos.getAll();
    }
    
    /**
     * Devuelve el producto con ese id.
     * 
     * @param id
     * @return 
     * @throws java.lang.Exception 
     */
    public Producto getProducto(int id) throws Exception{
        return productos.read(id);
    }
    
    /**
     * Metodo que comprueba si hay conexion o no.
     * 
     * @param config
     * @return 
     */
    public static boolean hasConnexion(DBConfig config){
        boolean result;
        try {
            CategoriaDAO factory = GestorDAOSingleton.getInstance().confGestor(config)
                    .getFactoriaDAO().getCategoriaDAO();
            factory.getAll();
            result = true;
        } catch (Exception e) {
            result = false;
        }
        return result;
    }
    
    /**
     * Metodo que devuelve una conexion por jdbc para el jasperReport.
     * 
     * @param config
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public static Connection getReportConexion(DBConfig config) throws ClassNotFoundException,
            SQLException{
        return GestorDAOSingleton.getInstance().getConexionInformes(config);
    }
    
    /**
     * Para que la vista sepa que se dio de alta correctamente.
     *
     */
    public void nuevaModificacion() throws Exception{
        catTableModel.setLista(categorias.getAll());
        proTableModel.setLista(productos.getAll());
        //Notifico que hay que actualizar la lista de Consultas
        this.setChanged();
        this.notifyObservers();
    }  
}