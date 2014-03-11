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
import eu.bartolom.openerpclient.OpenERPClient;

/**
 *
 * @author Juan Vicente Carrillo
 */
public class OpenErpXmlRpcUtil {
    public static CustomOpenERPObject getOpenERPObject(DBConfig dbconfig) throws Exception {
        
        final OpenERPClient client = new OpenERPClient(
                            "http://" + dbconfig.getDbhost() + ":8069/xmlrpc/", 
                            dbconfig.getDbname(), 
                            dbconfig.getDbuser(), 
                            dbconfig.getDbpass());
        
//        // CÓDIGO PARA LOGGER DE XML-RPC
//        final XmlRpcTransportFactory transportFactory = new XmlRpcTransportFactory()
//        {
//            @Override
//            public XmlRpcTransport getTransport()
//            {
//                return new MessageLoggingTransport(client);
//            }
//        };           
//        client.setTransportFactory(transportFactory);
//        // CÓDIGO PARA LOGGER DE XML-RPC
//        
        return new CustomOpenERPObject(client);
    }
}
