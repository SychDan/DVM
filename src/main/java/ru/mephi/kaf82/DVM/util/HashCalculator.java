package ru.mephi.kaf82.DVM.util;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Класс для вычисления хеша.
 */
public class HashCalculator {

    private static MessageDigest digest;

    static {
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /*
     * Получение SHA-256 хеша.
     *
     * @param data массив байт.
     * @return строка с SHA-256 хешем.
     */
    public static String getSHA256String(byte[] data) {
        return DatatypeConverter.printHexBinary(digest.digest(data));
    }
}
