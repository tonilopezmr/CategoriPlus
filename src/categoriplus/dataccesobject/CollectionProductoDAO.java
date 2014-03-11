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
package categoriplus.dataccesobject;

import categoriplus.dataccesobject.interfaces.ProductoDAO;
import categoriplus.modelo.Producto;
import categoriplus.modelo.Producto;
import categoriplus.modelo.Producto.Type;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Antonio López Marín
 */
public class CollectionProductoDAO implements ProductoDAO {
    
    public ArrayList<Producto> productos;

    public CollectionProductoDAO() {
        productos = new ArrayList<>();
        
        //Datos predefinidos
        Producto p1 = new Producto(1, "Product1", Type.CONSUMIBLE, null,  
                234.2, 234.2, "Una descripcion muy divertida y emocionante", true);
        Producto p2 = new Producto(2, "Producto2", Type.SERVICIO, null,  
                234.2, 234.2,"Este producto es muy divertido, Gracias",  true);
        Producto p3 = new Producto(3, "Producto3", Type.CONSUMIBLE, null,  
                234.2, 234.2, "Un super producto en comparacion con la competencia", false);
        
        productos.add(p1);
        productos.add(p2);
        productos.add(p3);
    }

   @Override
    public Producto insert(Producto producto) {
        producto.setId(productos.size() + 1);
        productos.add(producto);
        return null;
    }
    
    @Override
    public boolean update(Producto producto) {
        for (Producto pro : productos) {
            if (producto.getId() == pro.getId()) {
                productos.set(productos.indexOf(pro), producto);
            }
        }
        return true;
    }

    @Override
    public boolean delete(Producto producto) {
        productos.remove(producto);
        return true;
    }

    @Override
    public List<Producto> getAll() {
        return new ArrayList<>(productos);
    }

    @Override
    public Producto read(int id) {
        Producto producto= new Producto();
        
        for (Producto pro : productos) {
            if (pro.getId() == id) {
                producto = pro;
                break;
            }
        }
        
        return producto;
    }
}
