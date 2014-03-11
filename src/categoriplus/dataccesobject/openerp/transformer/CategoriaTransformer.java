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
package categoriplus.dataccesobject.openerp.transformer;

import categoriplus.modelo.Categoria;
import java.util.AbstractMap;
import java.util.HashMap;

/**
 * Clase que transforma los datos de Categoria a AbstractMap y viceversa.
 * 
 * @author Antonio López Marín
 */
public class CategoriaTransformer implements Transformer<Categoria> {
    
    //Ids
    private String ID = "id";
    public static final String NAME = "name";
    public static final String PARENT = "parent_id";
    //Campos
    public static final String[] FIELDS = {NAME, PARENT};
    //Nombre del objeto en la base de datos
    public static final String OBJECT_NAME = "product.category";

    /**
     * Metodo que transforma de AbstractMap a Categoria.
     * 
     * @param map
     * @return Met
     */
    @Override
    public Categoria transform(AbstractMap map) {
        Categoria categoria = new Categoria();
        //Introduzco los campos de id y name en una nueva categoria
        categoria.setId((Integer) map.get(ID));
        categoria.setName((String) map.get(NAME));
        
        //Recojo la categoria padre en un Object, porque no siempre devuelve
        //el mismo tipo
        Object p = map.get(PARENT);

        //Si es un Array de Objetos creo la categoria padre con sus datos
        if (p instanceof Object[]) {
            Object[] padre = (Object[]) p;
            Categoria catPadre = new Categoria((Integer) padre[0],
                    (String) padre[1],
                    new Categoria());
            categoria.setParent(catPadre);
        }

        return categoria;
    }

    /**
     * Transform que pasa de Categoria a AbstractMap.
     * 
     * @param dto
     * @return 
     */
    @Override
    public AbstractMap transform(Categoria dto) {
        AbstractMap map = new HashMap<String, Object>();
        //Paso sus campos
        map.put(ID, (Integer)dto.getId());
        map.put(NAME, (String)dto.getName());
        
        //Y si tiene categoria padre le paso su id
        Categoria padre = dto.getParent();
        if (padre != null) {
            map.put(PARENT, padre.getId());
        }

        return map;
    }
    
    /**
     * Metodo que devuelve la id de una categoria.
     * 
     * @param dto
     * @return Met
     */
    @Override
    public Object getIdObject(Categoria dto) {
        return (Integer) dto.getId();
    }
    
    /**
     * Metodo que cambia la id a una categoria con una nueva.
     * 
     * @param dto
     * @param id
     * @return Met
     */
    @Override
    public Categoria setId(Categoria dto, Object id) {
        dto.setId((Integer) id);
        return dto;
    }
    
    /**
     * Metodo que devuelve los campos de Categoria.
     * 
     * @return 
     */
    @Override
    public String[] getFields() {
        return FIELDS;
    }
    
    /**
     * Metodo que devuelve el nombre del objeto en el ERP.
     * @return 
     */
    @Override
    public String getObjectName() {
        return OBJECT_NAME;
    }
}
