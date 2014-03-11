/*
 * Copyright 2014 Antonio López Márin.
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
package informes;

import categoriplus.vista.Principal;
import categoriplus.vista.VistaInforme;
import java.io.File;
import java.sql.Connection;
import javax.swing.SwingWorker;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.swing.JRViewer;

/**
 * Clase que carga un reporte, y lo envia a una vista para que se vea.
 * 
 * @author Antonio López Marín
 */
public class CargarReporte {
    //Reportes
//    final private URL PRODUCTOS = ClassLoader.getSystemResource("informes/fichaProductos.jasper");
//    final private URL CATEGORIAS = ClassLoader.getSystemResource("informes/listadoEjercicioA.jasper");
//    final private URL FINAL = ClassLoader.getSystemResource("informes/reporteEjercicioC.jasper");
    final private String PRODUCTOS = "informes/fichaProductos.jasper";
    final private String CATEGORIAS = "informes/listadoEjercicioA.jasper";
    final private String FINAL = "informes/reporteEjercicioC.jasper";    
//    
    //Vistas
    private final VistaInforme vistaInforme;
    private final Principal vistaPrincipal;
    
    /**
     * Consturctor que recibe la vista donde se mostrara el informe, y 
     * la vista principal de la cual tiene que notificar cambios.
     * 
     * @param vistaPrincipal
     * @param vistaInforme 
     */
    public CargarReporte(Principal vistaPrincipal, VistaInforme vistaInforme) {
        this.vistaPrincipal = vistaPrincipal;
        this.vistaInforme = vistaInforme;
    }
    
    /**
     * Carga el reporte de categorias recibiendo una conexion.
     * 
     * @param conexion Car
     */
    public void cargarReporteCategorias(Connection conexion) {
        vistaPrincipal.progresVisible(true);
        WorkerTask worker = new WorkerTask(conexion, CATEGORIAS);
        worker.execute();
    }
    
    /**
     * Carga el reporte de Productos recibiendo una conexion.
     * 
     * @param conexion 
     */
    public void cargarReporeProductos(Connection conexion) {
        vistaPrincipal.progresVisible(true);
        WorkerTask worker = new WorkerTask(conexion, PRODUCTOS);
        worker.execute();
    }
    
    /**
     * Carga el reporte final con toda la informacion de producto por categoria,
     * recibiendo una conexion.
     * 
     * @param conexion 
     */
    public void cargarReporteFinal(Connection conexion) {
        vistaPrincipal.progresVisible(true);
        WorkerTask worker = new WorkerTask(conexion, FINAL);
        worker.execute();
    }

    /**
     * Worker que carga el informe y cuando lo recupera termina el
     * doInbackground, y en done llama a la vista del informe.
     *
     */
    class WorkerTask extends SwingWorker<Void, Void> {
        
        //Variables
        private final String informe;
        private JasperPrint jasperPrint;
        private final Connection conexion;
        
        /**
         * Constructor que recibe la ruta del informe, y la conexion.
         * 
         * @param conexion
         * @param informe 
         */
        public WorkerTask(Connection conexion, final String informe) {
            this.informe = informe;
            this.conexion = conexion;
        }
        
        /**
         * Cuando el Worker termina de trabajar, paso el informe cargado.
         * Y le digo a la vista que termine el progressBar
         */
        @Override
        protected void done() {
            vistaInforme.mostrarInforme(new JRViewer(jasperPrint));       
            vistaPrincipal.progresVisible(false);
        }
        
        /**
         * Metodo que carga el Reporte.
         * 
         * @return
         * @throws Exception 
         */
        @Override
        protected Void doInBackground() throws Exception {
            JasperReport report = (JasperReport) JRLoader.loadObject(new File(informe));
            jasperPrint = JasperFillManager.fillReport(report, null, conexion);
            return null;
        }
    }
}
