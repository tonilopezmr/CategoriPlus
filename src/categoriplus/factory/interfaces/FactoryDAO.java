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
package categoriplus.factory.interfaces;

import categoriplus.dataccesobject.interfaces.CategoriaDAO;
import categoriplus.dataccesobject.interfaces.ProductoDAO;

/**
 * Factoria de DAO, que recoge los DAO's que hay.
 * 
 * @author Antonio López Marín
 */
public interface FactoryDAO {
    public CategoriaDAO getCategoriaDAO() throws Exception;
    public ProductoDAO getProductoDAO() throws Exception;
}
