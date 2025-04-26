/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mypackage;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class PropertyExtractor {
    public static List<String> extractProperty(List<?> objects, String propertyName) {
        List<String> result = new ArrayList<>();

        for (Object obj : objects) {
            try {
                Field field = obj.getClass().getDeclaredField(propertyName);
                field.setAccessible(true); // Allow access to private fields
                Object value = field.get(obj);
                result.add(value != null ? value.toString() : null);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                result.add(null); 
            }
        }

        return result;
    }
}

