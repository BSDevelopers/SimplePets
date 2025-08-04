package org.bsdevelopment.simplepets;

import java.util.Arrays;

public class TestClass {
    public static void main(String[] args) {
        String string = "org.bsdevelopment.simplepets.nms";

        System.out.println("Bytes: "+ Arrays.toString(string.getBytes()));

        System.out.println(new String(new byte[]{111, 114, 103, 46, 98, 115, 100, 101, 118, 101, 108, 111, 112, 109, 101, 110, 116, 46, 115, 105, 109, 112, 108, 101, 112, 101, 116, 115, 46, 110, 109, 115}));
    }
}
