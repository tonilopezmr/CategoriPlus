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

import categoriplus.dataccesobject.interfaces.CategoriaDAO;
import categoriplus.modelo.Categoria;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Antonio López Marín
 */
public class CollectionCategoriaDAO implements CategoriaDAO {
    
    public ArrayList<Categoria> categorias;    
    public CollectionCategoriaDAO() {
        categorias = new ArrayList<>();

        //Datos por defecto
        Categoria ca1 = new Categoria(1, "Categoria1", null);
        Categoria ca2 = new Categoria(2, "Categoria2", ca1);   
        Categoria ca3 = new Categoria(3, "Categoria3", ca2);
        
        categorias.add(ca1);
        categorias.add(ca2);
        categorias.add(ca3);
    }
    
    @Override
    public Categoria insert(Categoria categoria) throws Exception{
        categoria.setId(categorias.size() + 1);
        categorias.add(categoria);
        return null;
    }
    
    @Override
    public boolean update(Categoria categoria) throws Exception{
        for (Categoria cat : categorias) {
            if (categoria.getId() == cat.getId()) {
                categorias.set(categorias.indexOf(cat), categoria);
            }
        }
        return true;
    }
    
    @Override
    public boolean delete(Categoria categoria) throws Exception{
        categorias.remove(categoria);
        return true;
    }
    
    @Override
    public List<Categoria> getAll() {
        return new ArrayList<>(categorias);
    }
    
    @Override
    public Categoria read(int id) {
        Categoria categoria = new Categoria();
        
        for (Categoria cat : categorias) {
            if (cat.getId() == id) {
                categoria = cat;
                break;
            }
        }
        
        return categoria;
    }

}
