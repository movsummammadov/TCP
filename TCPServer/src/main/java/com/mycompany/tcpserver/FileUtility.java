package com.mycompany.tcpserver;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Movsum Mammadov
 *
 */
public class FileUtility {

    public static void write(String fileName, byte[] bytes) throws Exception {
        Path path = Paths.get(fileName);
        Files.write(path, bytes);
    }

    public static byte[] read(String fileName) throws Exception {
        Path path = Paths.get(fileName);
        return Files.readAllBytes(path);
    }
}