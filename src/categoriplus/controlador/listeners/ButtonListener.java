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
package categoriplus.controlador.listeners;

import categoriplus.vista.Principal;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Listener de los botones, cuando pulsan sobre un boton, la pestaña 
 * donde se encuentra ese boton se cierra.
 * 
 * @author Antonio López Marín
 */
public class ButtonListener implements ActionListener {

    private Principal vista;

    public ButtonListener(Principal vista) {
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton cancel = (JButton) e.getSource();
        Container panel = cancel.getParent();
        if (panel != null) {
            if (panel instanceof JPanel) {
                vista.quitarTab((JPanel) panel);
            }
        }
    }
}
