package com.example.ss_c11_e1;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class CodeChallengeGenerator
{
    public static void main(String[] args) throws NoSuchAlgorithmException
    {
        SecureRandom sr=  new SecureRandom();
        byte[] code = new byte[32];
        sr.nextBytes(code);

        String code_verifier = Base64.getUrlEncoder()
                .withoutPadding()
                .encodeToString(code);

        System.out.println("code verifier = " + code_verifier);

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] digest = md.digest("jRs-QHpu1hsvoa1ic1PSTh4CkJPlnMS0bzNTH1H8v2k".getBytes());
        String code_challenge = Base64.getUrlEncoder()
                .withoutPadding()
                .encodeToString(digest);

        System.out.println("code_challenge = " + code_challenge);

    }
}
