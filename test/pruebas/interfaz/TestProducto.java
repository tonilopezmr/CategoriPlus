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
package pruebas.interfaz;

import categoriplus.vista.Principal;
import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Antonio López Marín
 */
public class TestProducto {
    
        private FrameFixture window;

    public TestProducto() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        Principal frame = GuiActionRunner.execute(new GuiQuery<Principal>() {
            @Override
            protected Principal executeInEDT() {
                return new Principal();
            }
        });
        window = new FrameFixture(frame);
        window.show(); // shows the frame to test
    }

    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
   
    /**
     * Defino el comportamiento que deberia de tener un usuario, de que
     * ponga un nombre al producto compruebo si el boton esta disable, hasta
     * que lo rellena todo y el boton de guardar tiene que estar enable.
     */
    @Test
    public void pinchaEnElJTreeYintentaGuardarPeroTieneQueEstarDisableRellenaLosCamposMareaOtroPocoYleDaAGuardar() {
        window.tree("jtree").clickPath("CategoriPlus/Producto/Crear");
        window.button("guardar_prod").requireDisabled();
        window.textBox("nombre_prod").enterText("Nueva producto");
        window.button("guardar_prod").requireDisabled();            
        window.checkBox("sale_check").check();                  //Hago check sobre vendible
        window.radioButton("servicio_radio").check();           //Selecciono servicio
        window.radioButton("consu_radio").requireNotSelected(); //No debe estar seleccionado consumible     
        window.textBox("descripcion").requireEmpty();           //Descripcion ha de estar vacia
        window.textBox("descripcion").enterText("Una descripcion cualquiera.");
        window.button("guardar_prod").requireEnabled();
    }

}