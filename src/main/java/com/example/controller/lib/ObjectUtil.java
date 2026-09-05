package com.example.controller.lib;

import java.lang.reflect.Field;

public class ObjectUtil {

    public static Object[] ClasstToObjects(Object obj) {

        Field[] fields = obj.getClass().getDeclaredFields();
        Object[] objects = new Object[fields.length];

        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            try {
                objects[i] = fields[i].get(obj);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return objects;
    }

    public static <T> T ObjectsToClass(Object[] values, Class<T> clazz) {
        try {
            T obj = clazz.getDeclaredConstructor().newInstance();
            Field[] fields = clazz.getDeclaredFields();
            for (int i = 0; i < fields.length && i < values.length; i++) {
                fields[i].setAccessible(true);
                fields[i].set(obj, values[i]);
            }
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;    
        }
    }

}
