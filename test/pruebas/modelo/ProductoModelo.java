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
import categoriplus.modelo.Producto;
import categoriplus.modelo.Producto.Type;
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
public class ProductoModelo {

    Controlador controller;

    public ProductoModelo() throws Exception {
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
    public void getAllProductos() {
        try {
            List<Producto> cats = controller.listarProductos();
            for (Producto producto : cats) {
                System.out.println(producto);
            }
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }

    }

    @Test
    public void getProducto() {
        try {
            Producto prod = controller.getProducto(1);
            System.out.println(prod.getId() + " - " + prod.getName());
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }

    }

    @Test
    public void insertarProducto() {
        try {
            Producto produc = new Producto();
            produc.setName("Super producto");
            produc.setDescription("Pallo vaya descripcionnnn");
            produc.setListPrice(123.23);
            produc.setStandardPrice(20.22);
            produc.setSaleOk(true);
            produc.setType(Type.SERVICIO);
            produc.setCategoria(controller.getCategoria(3));
            controller.crearProducto(produc);
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    @Test
    public void updateProducto() {
        try {
            Producto cat = controller.getProducto(1);
            cat.setName(cat.getName() + " Modificado");
            controller.modificarProducto(cat);
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
    }
}
