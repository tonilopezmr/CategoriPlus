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
package categoriplus.vista.validators;

import categoriplus.vista.paneles.Formulario;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComponent;
import javax.swing.text.JTextComponent;

/**
 * Clase StringVidator que implementa el FocusListener y de distinta manera
 * tambien implementa el KeyListener {@link KeyAdapter#keyTyped(java.awt.event.KeyEvent)}
 * 
 * Si quiere una validacion completa un componente debe añadir a los dos listeners.
 * 
 * @author Antonio López Marín
 */
public class StringValidator extends KeyAdapter implements FocusListener{

    Formulario formulario;
    
    public StringValidator(Formulario formaulario) {
        this.formulario = formaulario;
    }        
    
    /**
     * Determino si la cadena reune las condiciones necesarias.
     *
     * Estatico para que pueda ser utilizado sin instanciar la clase.
     * 
     * @param cadena
     * @return
     */
    public static boolean verifyString(String cadena) {
        try {
            int verify = 0;
            if ("".equals(cadena)) {
                return false;
            }
            for (int i = 0; i < cadena.length(); i++) {
                String test = cadena.substring(i, i + 1);
                if (" ".equals(test)) {
                    verify++;
                }
            }

            if (verify * 100 / cadena.length() > 20) {
                return false;
            }
            return true;
        } catch (NumberFormatException nfe) {
            return true;
        }
    }

    /**
     * Tambien implemento el keyTyped para que a cada puslacion de teclado
     * que reciba el componente a validar, se pueda validar dinamicamente, 
     * y no tener que esperar un enter, o que cambie de foco, sino que tambien
     * conforme vaya escribiendo se vaya validando o no.
     * 
     * @param e 
     */
    @Override
    public void keyTyped(KeyEvent e) {
        JComponent component = (JComponent)e.getSource();
        
        if (component instanceof JTextComponent) {
            String valid = ((JTextComponent)component).getText();
            
            if (verifyString(valid)) {
                formulario.valid(component);
            }else{
                formulario.invalid(component);
            }
        }
    }        

    @Override
    public void focusGained(FocusEvent e) {
        //Do nothing
    }

    @Override
    public void focusLost(FocusEvent e) {        
       JComponent component = (JComponent) e.getSource();
        
        if (component instanceof JTextComponent) {
            String valid = ((JTextComponent)component).getText();
            
            if (verifyString(valid)) {
                formulario.valid(component);
            }else{
                formulario.invalid(component);
            }
        }
    }
}
