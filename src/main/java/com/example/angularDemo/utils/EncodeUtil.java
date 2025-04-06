package com.example.angularDemo.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncodeUtil {

    public static byte[] makeHashString(String input, String algorithm) throws NoSuchAlgorithmException {

        // TODO getInstanceでNoSuchが発生する
        MessageDigest md = MessageDigest.getInstance(algorithm);

        return md.digest(input.getBytes());
    }
}
