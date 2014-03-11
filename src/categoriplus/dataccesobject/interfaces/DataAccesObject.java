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
package categoriplus.dataccesobject.interfaces;

import java.util.List;

/**
 *
 * @author Juan Vicente Carrillo
 */
public interface DataAccesObject<T> {
    // CRUD
    public T insert(T dto) throws Exception;
    public boolean update(T dto) throws Exception;
    public boolean delete(T dto) throws Exception;
    public T read(int id) throws Exception;
    // Búsquedas
    public List<T> getAll() throws Exception;
}
