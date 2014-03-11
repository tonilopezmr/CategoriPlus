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

import eu.bartolom.openerpclient.OpenERPClient;
import eu.bartolom.openerpclient.OpenERPObject;
import java.util.AbstractMap;

/**
 * Implementación del OpenERPObject que implementa un
 * método update para un único objeto, y el método delete
 * 
 * @author Juan Vicente Carrillo
 */
public class CustomOpenERPObject extends OpenERPObject  {
    
    public CustomOpenERPObject(OpenERPClient client) throws Exception {
        super(client);
    }
    
    public Object update(Object id, AbstractMap map, String resource) throws Exception {
        return update(new Object[]{id}, map, resource);
    }
    
    /**
     * Elimina un registro de la base de datos
     * @param id Id del registro a eliminar
     * @param resource Nombre de la tabla
     * @throws Exception 
     */
    public Object delete(Object id, String resource) throws Exception {
        Object[] params = new Object[]{resource, "unlink", new Object[]{id}};
        return execute(params);        
    }
}
