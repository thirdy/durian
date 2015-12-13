package com.porty.swing.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

/**
 * A few utilities to dynamically access any object properties and methods.
 * 
 * @author iportyankin
 */
public class DynamicBeanUtils {

    public static <T> T getPropertyValue(Object instance, PropertyDescriptor descriptor) {
        try {
            Method m = descriptor.getReadMethod();
            Object result = m.invoke(instance);
            return (T) result;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static <T> T getPropertyValue(Object instance, String propertyName) {
        try {
            Method m = instance.getClass().getMethod(getPropertyGetterName(propertyName));
            Object result = m.invoke(instance);
            return (T) result;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static String getPropertyGetterName(String propertyName) {
        String propertyNameCap = propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
        return "get" + propertyNameCap;
    }

    public static String getPropertySetterName(String propertyName) {
        String propertyNameCap = propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
        return "set" + propertyNameCap;
    }
}
