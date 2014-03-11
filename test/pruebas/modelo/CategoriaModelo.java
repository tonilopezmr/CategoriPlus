package pruebas.modelo;

/*
 * Copyright 2014 alumno.
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

import categoriplus.controlador.Controlador;
import categoriplus.dataccesobject.gestor.DBConfig;
import categoriplus.modelo.Categoria;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openide.util.Exceptions;

/**
 *
 * @author alumno
 */
public class CategoriaModelo {

    Controlador controller;

    public CategoriaModelo() throws Exception {
        controller = new Controlador(null, new DBConfig("admin", "admin", "192.168.2.134", "openerp"));
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void getAllCategorias() {
        try {
            List<Categoria> cats = controller.listarCategorias();
            for (Categoria categoria : cats) {
                System.out.println(categoria);
            }
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }

    }

    @Test
    public void getCategoria() {
        try {
            Categoria cat = controller.getCategoria(1);
            System.out.println(cat.getId()+" - "+cat.getName()+" - "+cat.getParent());
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
        
    }
    
    @Test
    public void insertarCategoria(){
        try {
            Categoria categori = new Categoria();
//            categori.setId(10);
            categori.setName("Nombre Categoria");
            categori.setParent(controller.getCategoria(1));
            controller.crearCategoria(categori);
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
    }
    
    @Test
    public void updateCategoria(){
        try {
            Categoria cat = controller.getCategoria(3);
            cat.setName(cat.getName()+" Modificado");
            controller.modificarCategoria(cat);
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
    }
}
