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

import categoriplus.dataccesobject.gestor.DBConfig;
import categoriplus.dataccesobject.interfaces.ProductoDAO;
import categoriplus.dataccesobject.openerp.OpenErpXmlRpcDelegate;
import categoriplus.dataccesobject.openerp.transformer.ProductoTransformer;
import categoriplus.modelo.Producto;

/**
 * Clase que implementa el DAO de categoria para el OpernErp.
 * 
 * @author Antonio López Marín
 */
public class OpenErpXmlProductoDAO 
    extends OpenErpXmlRpcDelegate<Producto>     
    implements ProductoDAO{

    public OpenErpXmlProductoDAO(DBConfig dbconfig) throws Exception {
        super(new ProductoTransformer(), dbconfig);
    }
}
