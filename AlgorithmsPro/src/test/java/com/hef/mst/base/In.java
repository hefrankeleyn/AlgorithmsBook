package com.hef.mst.base;

import org.springframework.core.io.ClassPathResource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * @author lifei
 * @since 2020/10/28
 */
public class In {

    private Scanner scanner;

    public In(String filePath){
        try {
            InputStream inputStream = new FileInputStream(filePath);
            scanner = new Scanner(inputStream);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public int readInt(){
        return scanner.nextInt();
    }

    public double readDouble(){
        return scanner.nextDouble();
    }

    public boolean hasNext(){
        return scanner.hasNext();
    }

    public static void main(String[] args) {
        ClassPathResource pathResource = new ClassPathResource("mst/tinyEWG.txt");
        try {
            String filePath = pathResource.getFile().getCanonicalPath();
            System.out.println(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
