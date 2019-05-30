package ru.mephi.kaf82.DVM.util;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Вспомогательный класс для работы с файлами.
 */
public class FileUtil {

    /**
     * Папка для сохранения файлов.
     */
    private static final String FOLDER = PropertiesLoader.getProperties("profile.properties").getProperty("filesFolder");

    /**
     * Сохранение файла.
     *
     * @param filename имя файла.
     * @param data     данные файла.
     */
    public static void saveFile(String filename, byte[] data) {
        try {
            File file = new File(FOLDER + filename);
            FileUtils.writeByteArrayToFile(file, data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Получение полного имени файла из classpath.
     *
     * @param file имя файла.
     * @return полное имя файла.
     */
    public static String getFullClasspathFilename(String file) {
        try {
            return new ClassPathResource(file).getURL().getPath();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Получение потока файла из classpath по имени файла.
     *
     * @param filename имя файла.
     * @return поток файла.
     */
    public static InputStream getClasspathFileInputStream(String filename) {
        return FileUtil.class.getClassLoader().getResourceAsStream(filename);
    }

    /**
     * Очистка имени файла.
     *
     * @param fileName имя файла.
     * @return имя файла без несовместимых символов.
     */
    public static String validateFileName(String fileName) {
        return fileName.replaceAll("\"|/|:|\\*|\\?|<|>|\\|", "");
    }

    /**
     * Получение массива байт из файла.
     *
     * @param filename имя файла.
     * @return массив байт файла.
     */
    public static byte[] getFileBytes(String filename) {
        try (InputStream inputStream = new FileInputStream(new File(FOLDER + filename))) {
            return IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Удаление файла.
     *
     * @param filename имя файла.
     */
    public static void deleteFile(String filename) {
        File file = new File(FOLDER + filename);
        file.delete();
    }
}
