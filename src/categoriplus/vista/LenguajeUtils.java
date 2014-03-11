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
package categoriplus.vista;

import java.util.Locale;
import java.util.prefs.Preferences;

/**
 * Clase de facil consulta y recogida del lenguaje local.
 * 
 * @author Antonio López Marín
 */
public class LenguajeUtils {
    
    /**
     * Metodo que recoje las preferencias de usuario y devuelve la 
     * localizacion en la que se encuentra.
     * 
     * @return 
     */
    public static Locale recogerIdioma(){
        //Primero recojo el idioma
        // Obtengo las preferencias del usuario
        Preferences userPreferences
                = Preferences.userRoot();
        // Obtengo un valor de preferencias, por defecto es español
        String lang = userPreferences.get("LANG", "es");
        String country = userPreferences.get("COUNTRY", "ES");
        
        return new Locale(lang, country);
    }
    
    /**
     * Metodo que cambia el lenguaje.
     *
     * @param lang
     * @param country
     * @return 
     */
    public static Locale cambiarIdioma(String lang, String country) {
        // Obtengo las preferencias del usuario
        Preferences userPreferences
                = Preferences.userRoot();
        // Guardo un valor en preferencias
        userPreferences.put("COUNTRY", country);
        userPreferences.put("LANG", lang);
        //Le cambio la localidad
        return new Locale(lang, country);
    }
}
