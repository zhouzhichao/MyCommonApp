package com.example.chou.mycommonapp.utils;

/**
 * Created by txj on 16/7/23.
 */
public class TokenUtil {
    private static final char[] ss = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'g', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    public static String token() {
        String token = "";
        for (int i = 0; i < 6; i++) {
            token += ss[Random.uniform(0, 36)];
        }
        token += System.currentTimeMillis();
        return token;
    }

    public static String token2() {
        String token = new String();
        token += System.identityHashCode(token);
        token += ss[Random.uniform(11, 36)];
        token += ss[Random.uniform(11, 36)];
        token += System.currentTimeMillis();
        return token;
    }

}
