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
package categoriplus.dataccesobject.openerp;

import categoriplus.dataccesobject.gestor.DBConfig;
import categoriplus.dataccesobject.interfaces.DataAccesObject;
import categoriplus.dataccesobject.openerp.transformer.Transformer;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase delegada encargada de implementar los métodos CRUD
 * para cualquier objeto de la base de datos
 * 
 * @author Juan Vicente Carrillo
 */
public class OpenErpXmlRpcDelegate<T> implements DataAccesObject<T> {
    CustomOpenERPObject object;
    Transformer<T> transformer;
    
    public OpenErpXmlRpcDelegate(Transformer<T> transformer, 
                                 DBConfig dbconfig) 
            throws Exception{
        this.transformer = transformer;
        this.object = OpenErpXmlRpcUtil.getOpenERPObject(dbconfig);
    }

    @Override
    public T insert(T dto) throws Exception {
        Object id = 
            object.create(transformer.transform(dto), 
                          transformer.getObjectName());
        
        return transformer.setId(dto, id);
    }

    @Override
    public boolean update(T dto) throws Exception {
        return (Boolean) object.update(transformer.getIdObject(dto), 
                            transformer.transform(dto), 
                            transformer.getObjectName());
    }

    @Override
    public boolean delete(T dto) throws Exception {
        return (Boolean)
            object.delete(transformer.getIdObject(dto), transformer.getObjectName());
    }

    @Override
    public T read(int id) throws Exception {
        final Object[] fields = transformer.getFields();
        // 1. Busco por id
        AbstractMap res = object.read(id, fields, transformer.getObjectName());
        // 2. Transformo el resultado de la consulta a objetos de tipo T
        return transformer.transform(res);
    }

    @Override
    public List<T> getAll() throws Exception {
        final Object empty[][] = new Object[0][0];
        return getAllWhere(empty);
    }
    
    public List<T> getAllWhere(Object[][] where) throws Exception {
        // 1. Leo todos los objetos por su id
        List<T> list = new ArrayList();
       // El primer parámetro (where) tiene que ser de tipo Object[][]
        List<AbstractMap> rres = object.searchRead(where, 
                        transformer.getFields(), transformer.getObjectName());        
        // 2. Transformo el resultado de la consulta a objetos de tipo Libro
        for(AbstractMap map : rres) list.add(transformer.transform(map));

        return list;           
    }
    
}

