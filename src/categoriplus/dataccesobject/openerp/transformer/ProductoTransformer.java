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
package categoriplus.dataccesobject.openerp.transformer;

import categoriplus.modelo.Categoria;
import categoriplus.modelo.Producto;
import categoriplus.modelo.Producto.Type;
import java.util.AbstractMap;
import java.util.HashMap;

/**
 * Clase transformer, que hace las conversiones de Producto a AbstractMap y 
 * viceversa.
 * 
 * @author Antonio López Marín
 */
public class ProductoTransformer implements Transformer<Producto>{
    
    //Ids
    private String ID = "id";
    public static final String NAME             = "name";
    public static final String DESCRIPTION      = "description";
    public static final String TYPE             = "type";
    public static final String CATEGORIA        = "categ_id";
    public static final String PRECIOVENTA      = "list_price";
    public static final String PRECIOCOMPRA     = "standard_price";
    public static final String SALE_OK          = "sale_ok";
    
    //Campos del objeto Producto
    public static final String[] FIELDS = {NAME, DESCRIPTION, TYPE, 
        CATEGORIA, PRECIOVENTA, PRECIOCOMPRA, SALE_OK};
    
    //Nombre del objeto en el ERP
    public static final String OBJECT_NAME = "product.product";
    
    /**
     * Transforma un AbstractMap a Producto.
     * 
     * @param map
     * @return 
     */
    @Override
    public Producto transform(AbstractMap map) {
        Producto producto = new Producto();
        
        //Recojo la descripcion que puede ser Boolean o un String
        Object opb = map.get(DESCRIPTION);
        //Si es booleano hago la conversion a string
        if (opb instanceof Boolean) {
             producto.setDescription(String.valueOf((Boolean)opb));
        }else if(opb instanceof String){
            //Si es string simplemente lo pongo
            producto.setDescription((String)opb);
        }
        
        //Introduzco los campos de producto por su ID
        producto.setId((Integer)map.get(ID));        
        producto.setName((String)map.get(NAME));
        producto.setListPrice((Double)map.get(PRECIOVENTA));
        producto.setStandardPrice((Double)map.get(PRECIOCOMPRA));
        producto.setSaleOk((Boolean)map.get(SALE_OK));
        producto.setType(getTypeProducto((String)map.get(TYPE)));
        
        //Recojo la categoria
        Object[] categoria = (Object[])map.get(CATEGORIA);
        
        //Si es distinta de null, la pongo en el producto
        if (categoria != null) {
            producto.setCategoria(new Categoria(
                (Integer)categoria[0], 
                (String)categoria[1], 
                null));
        }
                
        return producto;
    }
    
    /**
     * Metodo que pasa el tipo de producto de String (lo que da la base de datos),
     * a de tipo Type (enum de producto).
     * 
     * @param tipo
     * @return 
     */
    private Type getTypeProducto(String tipo){
        Type type;
        
        if (tipo.startsWith("servi")) {
           type = Producto.Type.SERVICIO;
        }else{
           type = Producto.Type.CONSUMIBLE;
        }
        
        return type;
    }
    
    /**
     * Metodo que pasa de Type (enum producto) a String, para mandarlo.
     * 
     * @param tipo
     * @return 
     */
    private String getStringTypeProducto(Type tipo){
        String type;
        
        if (tipo == Type.CONSUMIBLE) {
            type = "consu";
        }else{
            type = "service";
        }
        
        return type;
    }
    
    /**
     * Transforma de Prodcuto a Abstractmap.
     * 
     * @param dto
     * @return 
     */
    @Override
    public AbstractMap transform(Producto dto) {
        AbstractMap map = new HashMap<String, Object>();
        
        //Importante no poner su id ya que el DELEGATE ya lo recojera
        map.put(DESCRIPTION, dto.getDescription());
        map.put(NAME, dto.getName());
        map.put(PRECIOVENTA, dto.getListPrice());
        map.put(PRECIOCOMPRA, dto.getStandardPrice());
        map.put(SALE_OK, dto.isSaleOk());
        map.put(TYPE, getStringTypeProducto(dto.getType()));
        map.put(CATEGORIA, dto.getCategoria().getId());
        
        return map;
    }
    
    /**
     * Metodo que devuelve la id del objeto.
     * 
     * @param dto
     * @return 
     */
    @Override
    public Object getIdObject(Producto dto) {
        return (Integer)dto.getId();
    }
    
    /**
     * Metodo recoje un producto y una id y se la cambia.
     * 
     * @param dto
     * @param id
     * @return 
     */
    @Override
    public Producto setId(Producto dto, Object id) {    
        dto.setId((Integer)id);
        return dto;
    }

    /**
     * Devuelve el array de campos de producto.
     * 
     * @return 
     */
    @Override
    public String[] getFields() {
        return FIELDS;
    }

    /**
     * Devuelve el nombre del objeto en el ERP.
     * 
     * @return 
     */
    @Override
    public String getObjectName() {
        return OBJECT_NAME;
    }
    
}
