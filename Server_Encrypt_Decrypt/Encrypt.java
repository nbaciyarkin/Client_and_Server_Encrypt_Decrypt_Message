package server_18070006043;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author STUDENT
 */
public class Encrypt {

    static Scanner sc = new Scanner(System.in);
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


    public static String encrypt(String str, int key) {
        String encrypted = "";
        for (int i = 0; i < str.length(); i++) {
            int c = str.charAt(i);
            if (Character.isUpperCase(c)) {
                c = c + (key % 26);
                if (c > 'Z') {
                    c = c - 26;
                }
            } else if (Character.isLowerCase(c)) {
                c = c + (key % 26);
                if (c > 'z') {
                    c = c - 26;
                }
            }
            encrypted += (char) c;
        }
        return encrypted;
    }

    public static String decrypt(String str, int key) {
        String decrypted = "";
        for (int i = 0; i < str.length(); i++) {
            int c = str.charAt(i);
            if (Character.isUpperCase(c)) {
                c = c - (key % 26);
                if (c < 'A') {
                    c = c + 26;
                }
            } else if (Character.isLowerCase(c)) {
                c = c - (key % 26);
                if (c < 'a') {
                    c = c + 26;
                }
            }
            decrypted += (char) c;
        }
        return decrypted;
    }
    
            public static int findKey(String findKey) {

        char[] ch = new char[findKey.length()];

        // Copy character by character into array
        for (int i = 0; i < findKey.length(); i++) {
            ch[i] = findKey.charAt(i);
        }

        int spaceCounter = 0;

        for (int i = 0; i < ch.length; i++) {
            if (ch[i] == ' ') {
                spaceCounter++;
            }
        }
        return 3 * spaceCounter;

    }
}
