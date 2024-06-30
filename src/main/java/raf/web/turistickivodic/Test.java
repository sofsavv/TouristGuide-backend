package raf.web.turistickivodic;

import org.apache.commons.codec.digest.DigestUtils;

public class Test {

    public static void main(String[] args) {
        System.out.println("123: " + DigestUtils.sha256Hex("123"));
    }
}
