package com.example.encoding;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;


public class MainActivity extends AppCompatActivity {

    public static final String KEY_VALUE = "SchneiderApp";
    private static SecretKeySpec key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        encodeString();
    }

    private void encodeString(){

        String SEED_VALUE = getPackageName();
        String Token = "Bearer miShz9_6kX-8psL5P8JWX4cAaomwDQji";

        Toast.makeText(this, SEED_VALUE, Toast.LENGTH_LONG).show();



        String encyptedString = encrypt(SEED_VALUE, Token);

        String decryptedString = decrypt(SEED_VALUE, encyptedString);

        System.out.println(decryptedString);





    }

    private static SecretKey getRawKeyNewCrypto(byte[] salt) {
        if (null == key) {
            try {
                KeySpec keySpec = new PBEKeySpec(KEY_VALUE.toCharArray(), salt, 65536, 128);
                SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
                byte[] keyBytes = secretKeyFactory.generateSecret(keySpec).getEncoded();

                key = new SecretKeySpec(keyBytes, "AES");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (InvalidKeySpecException e) {
                e.printStackTrace();
            }
        }
        return key;

    }

    public static String encrypt(String salt, String text) {

        Cipher cipher;
        String encryptedString = "";
        try {
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, getRawKeyNewCrypto(salt.getBytes()));
            encryptedString = toHex(cipher.doFinal(text.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        finally {
            return encryptedString;
        }

    }

    public static String decrypt(String salt, String text) {

        Cipher cipher;
        String decryptedString = "";
        try {
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, getRawKeyNewCrypto(salt.getBytes()));
            decryptedString =  new String(cipher.doFinal(toByte(text)));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        finally {
            return decryptedString;
        }

    }

    public static byte[] toByte(String hexString) {
        int len = hexString.length() / 2;
        byte[] result = new byte[len];

        for (int i = 0; i < len; ++i) {
            result[i] = Integer.valueOf(hexString.substring(2 * i, 2 * i + 2), 16).byteValue();
        }

        return result;
    }

    public static String toHex(byte[] buf) {
        if (buf == null) {
            return "";
        } else {
            StringBuffer result = new StringBuffer(2 * buf.length);

            for (int i = 0; i < buf.length; ++i) {
                appendHex(result, buf[i]);
            }

            return result.toString();
        }
    }

    private static void appendHex(StringBuffer sb, byte b) {
        sb.append("0123456789ABCDEF".charAt(b >> 4 & 15)).append("0123456789ABCDEF".charAt(b & 15));
    }

}
