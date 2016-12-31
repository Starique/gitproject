package com.tarique.test;

public class Main {

    public static void main(String[] args) {
        String email = "syed.tarique@gmail.com";
        String hash = MD5Util.md5Hex(email);

        System.out.println(hash);
    }
}
