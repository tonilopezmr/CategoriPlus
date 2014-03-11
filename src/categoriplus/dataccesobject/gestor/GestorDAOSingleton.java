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
package categoriplus.dataccesobject.gestor;

import categoriplus.factory.OpenErpXmlRpcDAOFactory;
import categoriplus.factory.interfaces.FactoryDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase Gestor del DAO que devuelve la FactoryDAO.
 * 
 * @author Antonio López Marín
 */
public class GestorDAOSingleton {
    
    //Variables
    private static GestorDAOSingleton gestor;
    private DBConfig dbconfig;
    
    /**
     * Constructor privado porque tiene el patron Singleton.
     * 
     */
    private GestorDAOSingleton() {
    }
    
    /**
     * Metodo que devuelve la instancia de la clase.
     * @return 
     */
    public static GestorDAOSingleton getInstance() {
        if (gestor == null) {
            gestor = new GestorDAOSingleton();
        }

        return gestor;
    }
    
    /**
     * Metodo que recoje la configuracion de la conexion.
     * 
     * INFO: 
     *      Es el #with(DBConfig config) que utilizabas en tu proyecto.      
     * 
     * @param config
     * @return 
     */
    public GestorDAOSingleton confGestor(DBConfig config) {
        this.dbconfig = config;
        return this;
    }
    
    /**
     * Devuelve la factoria de DAO, aqui se cambia la clase si se conecta
     * a otro tipo de base de datos.
     * 
     * @return 
     */
    public FactoryDAO getFactoriaDAO() {
        return new OpenErpXmlRpcDAOFactory(dbconfig);
    }
        
    /**
     * Metodo que devuelve la configuracion que tiene el gestor.
     * 
     * @return Me
     */
    public DBConfig getConfiguracionConexion(){
        return dbconfig;
    }
    
    /**
     * Devuelve una conexion para generar los informes, si es otra base de datos
     * solo se tiene que cambiar el driver, que eso no se ha parametrizado.
     * 
     * Se debe utilizar una configuracion distinta pese a lo que dice el ejercicio,
     * ya que para los informes no te conectas al erp, sino que te conectas
     * directamente a la base de datos.
     * 
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public Connection getConexionInformes(DBConfig dbconfig) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection("jdbc:postgresql://"+
                dbconfig.getDbhost()+"/"+dbconfig.getDbname(),
                dbconfig.getDbuser(),
                dbconfig.getDbpass());
    }
}
