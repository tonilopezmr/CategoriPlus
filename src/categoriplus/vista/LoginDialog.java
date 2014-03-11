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
import categoriplus.vista.paneles.PanelLogin;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.prefs.Preferences;
import javax.swing.SwingWorker;
import org.netbeans.validation.api.Problems;
import org.netbeans.validation.api.Severity;
import org.netbeans.validation.api.Validator;
import org.netbeans.validation.api.ui.swing.ValidationPanel;
import org.netbeans.validation.api.builtin.stringvalidation.StringValidators;

/**
 * Dialogo que pide los datos para poder ingresar en la aplicación.
 * 
 * Se utiliza la libreria Simple Validation API, tambien se definen nuestros
 * propios validadores.
 * 
 * @see ValidationPanel
 * @author Antonio López Marín
 */
public class LoginDialog {
    
    //Variables, panel del login y el panel de Simple Validation API
    private PanelLogin login;
    private ValidationPanel validation;
    
    //Constantes de las preferencias de usuario
    final private String HOST = "host";
    final private String NAMEDB = "name_DB";
    final private String USER = "user";
    final private String USER_INFORM = "user_inform";
    
    /*
     * Constructor de login
     */
    public LoginDialog() {
        init(); //Inicio
    }

    /**
     * Metodo que inicia el validationlogin
     */
    private void init() {
        //Instancio el login de login
        login = new PanelLogin(this);

        //Pongo en el login de validacion en el panel
        validation = new ValidationPanel();
        validation.setInnerComponent(login);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        validation.setLocation(screenSize.width / 3, screenSize.height / 3);

        //Validator para la conexion, es especifico para eso
        validation.getValidationGroup().add(login.getBtnConexion(),
                new ConexionValidator());

        //Validator para el host
        validation.getValidationGroup().add(login.getHostField(),
                StringValidators.REQUIRE_NON_EMPTY_STRING);
        //Validator para el usuario
        validation.getValidationGroup().add(login.getUsuarioField(),
                StringValidators.REQUIRE_NON_EMPTY_STRING);
        //validator para la pass
        validation.getValidationGroup().add(login.getPassField(),
                StringValidators.REQUIRE_NON_EMPTY_STRING);
        //validator para el nombre
        validation.getValidationGroup().add(login.getNomDBField(),
                StringValidators.REQUIRE_NON_EMPTY_STRING);
        
        //Establezco las preferencias de usuario del login
        establecerPreferencias(USER);
    }

    /**
     * Metodo que le pone el action listener al boton de comprobar.
     *
     * @param listener
     * @paleram listener
     */
    public void addActionListener(ActionListener listener) {
        //Le pongo el lisenter
        login.addactionListener(listener);
    }

    /**
     * Metodo que muestra el panel para el login antes de comenzar el programa.
     *
     * @return
     */
    public DBConfig show() {
        //Pongo los botones de Ok y cancel.
        if (validation.showOkCancelDialog(java.util.ResourceBundle.getBundle("lang/lenguajes").getString("categoriplus_login"))) {
            guardarPreferencias(USER);
            return recogerDatosPanel();
        }
        return null;
    }

    /**
     * Metodo que muestra el panel para que muestre
     *
     * @param config
     * @return
     */
    public DBConfig showInforme(DBConfig config) {
        establecerPreferencias(USER_INFORM);

        //Pongo los botones de Ok y cancel.
        if (validation.showOkCancelDialog(java.util.ResourceBundle.getBundle("lang/lenguajes").getString("categoriplus_informes"))) {
            guardarPreferencias(USER_INFORM);
            return recogerDatosPanel();
        }
        return null;
    }

    /**
     * Metodo que se ejecuta si le dan al boton comprobar.
     *
     */
    public void comprobarConexionInforme() {
        try {
            boolean isGood = validarConexionInforme();
            if (isGood) {
                login.notifyInfo(null);
            } else {
                login.notifyError(java.util.ResourceBundle.getBundle("lang/lenguajes").getString("conexion_erronea"));
            }
        } catch (InterruptedException | ExecutionException ex) {
            //Si se interrumpe la operacion, se sale de la ventana, 
            //cancelar etc..
        }
    }

    /**
     * Metodo que se ejecuta si le dan al boton comprobar
     */
    public void comprobarConexion() {
        try {
            boolean isGood = validarConexion();
            if (isGood) {
                login.notifyInfo(null);
            } else {
                login.notifyError(java.util.ResourceBundle.getBundle("lang/lenguajes").getString("conexion_erronea"));
            }
        } catch (InterruptedException | ExecutionException ex) {
            //Si se interrumpe la operacion, se sale de la ventana, 
            //cancelar etc..
        }
    }

    /**
     * Metodo que valida la conexion de login de la base de datos en general.
     *
     * Llama a un worker, se paraliza la interfaz hasta que no verifique
     * la conexion.
     *
     * @return 
     * @throws java.lang.InterruptedException
     * @throws java.util.concurrent.ExecutionException
     */
    public boolean validarConexion() throws InterruptedException, ExecutionException {
        //Para que en el cursor aparezca un progreso
        validation.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        WorkerConexion validConex = new WorkerConexion();
        validConex.execute();
        return validConex.get();
    }

    /**
     * Metodo que valida la conexion de reporte, utilizando un worker, 
     * se bloque la interfaz, hasta que no se valide la conexion.
     *
     * @return
     * @throws java.lang.InterruptedException
     * @throws java.util.concurrent.ExecutionException
     */
    public boolean validarConexionInforme() throws InterruptedException, ExecutionException {
        //Para que en el cursor aparezca un progreso
        validation.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        WorkerConexionReport conexInforme = new WorkerConexionReport();
        conexInforme.execute();        
        return conexInforme.get();
    }

    /**
     * Metodo que recoje los datos del panel de login.
     *
     * @return
     */
    private DBConfig recogerDatosPanel() {
        String host = login.getHost();
        String usuario = login.getUsuario();
        String nomDB = login.getNomDB();
        char[] charPass = login.getPass();
        String pass = String.valueOf(login.getPass(), 0, charPass.length);
        Arrays.fill(charPass, '0'); //Relleno de ceros la contraseña
        
        return new DBConfig(usuario, pass, host, nomDB);
    }
    
    /**
     * Metodo que recoge las preferencias de usuario, el host, nombre base de datos
     * y usuario que inserto anteriormente.
     * 
     * Por parametro le paso el usuario, que quiero que recupere, puede ser
     * de la base de datos del programa, o de los informes.
     * 
     */
    private void establecerPreferencias(final String USER) {
        Preferences preferences = Preferences.userRoot();
        
        login.setHost(preferences.get(HOST, "localhost"));        
        login.setNomDB(preferences.get(NAMEDB, "example"));
        login.setUsuario(preferences.get(USER, "admin"));
    }
    
    /**
     * Guarda el host, el nombre y el usuario, de la base de datos, en las
     * preferencias de usuario.
     * 
     * Por parametro le paso el usuario, que quiero que guarde, puede ser
     * de la base de datos del programa, o de los informes.
     * 
     */
    private void guardarPreferencias(final String USER){
        Preferences preferences = Preferences.userRoot();
        
        preferences.put(HOST, login.getHost());
        preferences.put(NAMEDB, login.getNomDB());
        preferences.put(USER, login.getUsuario());
    }
    
    /**
     * Validador para el Simple Validator Panel de la conexion, hasta que la
     * conexion no es correcta no te deja darle al ok.
     *
     */
    final class ConexionValidator implements Validator<String> {

        @Override
        public void validate(Problems problems, String cmpName, String t) {
            if (t.isEmpty()) {
                problems.add(java.util.ResourceBundle.getBundle("lang/lenguajes").getString("compruebe_conexion"),
                        Severity.FATAL);
            } else if (t.equals(PanelLogin.INCORRECTO)) {
                problems.add(java.util.ResourceBundle.getBundle("lang/lenguajes").getString("conexion_erronea"),
                        Severity.FATAL);
            } else if (t.equals(PanelLogin.CORRECTO)) {
                //Very bright colors get an information message
                problems.add(java.util.ResourceBundle.getBundle("lang/lenguajes").getString("conexion_correcta"),
                        Severity.INFO);
            }
        }

        @Override
        public Class<String> modelType() {
            return String.class;
        }
    }
    
    /**
     * Worker que lanza una peticion de conexion a la base de datos donde
     * este el Report y devuelve true o false si fue exitosa o no.
     * 
     */
    class WorkerConexionReport extends SwingWorker<Boolean, Void> {
        
        /**
         * Cuando termina el worker, termino el progress del mouse, y hace un sonidito
         */
        @Override
        protected void done() {
            validation.setCursor(null);
            Toolkit.getDefaultToolkit().beep();
        }
        
        /**
         * Intenta conectarse a la base de datos, y devuelve true si fue exitoso,
         * false si no lo fue.
         * 
         * @return
         * @throws Exception 
         */
        @Override
        protected Boolean doInBackground() throws Exception {
            boolean isGood;
            try {
                Controlador.getReportConexion(recogerDatosPanel());
                isGood = true;
            } catch (Exception e) {
                isGood = false;
            }

            return isGood;
        }
    }
    
    /**
     * Worker que lanza una peticion de conexion a la base de datos, que 
     * consultara la aplicacion, devuelve true o false si fue exitosa.
     */
    class WorkerConexion extends SwingWorker<Boolean, Void> {
        
        /**
         * Detiene el cursor de progreso y hace un sonidito
         */
        @Override
        protected void done() {
            validation.setCursor(null);
            Toolkit.getDefaultToolkit().beep();
        }
        
        /**
         * Intenta conectarse a la base de datos.
         * 
         * @return
         * @throws Exception 
         */
        @Override
        protected Boolean doInBackground() throws Exception {
            return Controlador.hasConnexion(recogerDatosPanel());
        }
    }
}