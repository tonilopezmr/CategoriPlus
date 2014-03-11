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
public class TestCategoria {

    private FrameFixture window;

    public TestCategoria() {
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
     * Una accion de usuario comun, que te vas a crear categoria, y 
     * pones el nombre, debe de estar enable el boton y se debe de mostrar
     * un tooltip, y si reculas y quitas el texto debe estar el boton disable.
     */
    @Test
    public void accionCategoria() {
        window.tree("jtree").clickPath("CategoriPlus/Categoria/Crear");
        window.textBox("nombre_cat").enterText("Nueva categoria");
        window.button("guardar").requireEnabled();
        window.button("guardar").requireToolTip("Guarda la categoria que hay en pantalla.");
        window.textBox("nombre_cat").deleteText();
        window.button("guardar").requireDisabled();
    }

}