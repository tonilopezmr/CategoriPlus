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
import org.fest.swing.core.MouseButton;
import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.fixture.JTableFixture;
import org.fest.swing.fixture.JTableHeaderFixture;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Antonio López Marín
 */
public class TestListado {
     private FrameFixture window;

    public TestListado() {
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
     * Compruebo una accion de usuario de seleccionar el listado, y que le den
     * a un producto doble click, y se tiene que abrir un nuevo panel y estar
     * seleccionado.
     * 
     */
    @Test
    public void accionListado() {
        window.tree("jtree").clickPath("CategoriPlus/Producto/Listar");
        JTableFixture table = window.table("tabla_prod");
        table.tableHeader().clickColumn(1);
        table.doubleClick();
        //Tiene que estar seleccionada la nueva pestaña
        window.tabbedPane("tabbedpane").selectTab(1);  
    }
}
