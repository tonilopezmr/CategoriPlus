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
package categoriplus.vista.paneles;

import javax.swing.JComponent;

/**
 * Interfaz que implementa los metodos que debe de tener un formulario, 
 * para poder validarlo, mostrar mensajes etc..
 * 
 * @author Antonio López Marín
 */
public interface Formulario {
    public void notifyError(String msg);
    public void notifyInfo(String msg);
    public void initalFocus();
    public void defaultButton();
    public void valid(JComponent validComponent);
    public void invalid(JComponent invalidComponent);
}
