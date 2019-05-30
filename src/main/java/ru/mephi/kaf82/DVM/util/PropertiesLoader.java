package ru.mephi.kaf82.DVM.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Загрузчик свойств из property файлов.
 */
public class PropertiesLoader {

    /**
     * Возвращает свойства.
     */
    public static Properties getProperties(String filename) {
        try {
            InputStream stream = PropertiesLoader.class.getClassLoader().getResourceAsStream(filename);
            Properties properties = new Properties();
            properties.load(stream);
            return properties;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}