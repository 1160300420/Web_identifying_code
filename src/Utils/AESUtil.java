package Utils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESUtil {
  private static String iv = "0123456789ABCDEF";//ƫ�����ַ���������16λ ��ģʽ��CBC��ʱ���������ƫ���� 
  private static String Algorithm = "AES";
  private static String AlgorithmProvider = "AES/CBC/PKCS5Padding"; //�㷨/ģʽ/���뷽ʽ 
  public static byte[] generatorKey() throws NoSuchAlgorithmException {
    KeyGenerator keyGenerator = KeyGenerator.getInstance(Algorithm);
    keyGenerator.init(256);//Ĭ��128�����������Ȩ�޺��Ϊ192��256
    SecretKey secretKey = keyGenerator.generateKey();
    return secretKey.getEncoded();
  }
  public static IvParameterSpec getIv() throws UnsupportedEncodingException {
    IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes("utf-8"));
    System.out.println("ƫ������" + byteToHexString(ivParameterSpec.getIV()));
    return ivParameterSpec;
  }
  public static byte[] encrypt(String src, byte[] key)
      throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException,
      BadPaddingException, UnsupportedEncodingException, InvalidAlgorithmParameterException {
    SecretKey secretKey = new SecretKeySpec(key, Algorithm);
    IvParameterSpec ivParameterSpec = getIv();
    Cipher cipher = Cipher.getInstance(AlgorithmProvider);
    cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
    byte[] cipherBytes = cipher.doFinal(src.getBytes(Charset.forName("utf-8")));
    return cipherBytes;
  }
  public static byte[] decrypt(String src, byte[] key) throws Exception {
    SecretKey secretKey = new SecretKeySpec(key, Algorithm);
    String IV = "face0123456789ai";
    IvParameterSpec iv = new IvParameterSpec(IV.getBytes("utf-8"));
    //IvParameterSpec ivParameterSpec = getIv();
    Cipher cipher = Cipher.getInstance(AlgorithmProvider);
    cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
    byte[] hexBytes = hexStringToBytes(src);
    byte[] plainBytes = cipher.doFinal(hexBytes);
    return plainBytes;
  }
  /**
  }
  * ��byteת��Ϊ16�����ַ���
  * @param src
  * @return
  */
  public static String byteToHexString(byte[] src) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < src.length; i++) {
      int v = src[i] & 0xff;
      String hv = Integer.toHexString(v);
      if (hv.length() < 2) {
        sb.append("0");
      }
      sb.append(hv);
    }
    return sb.toString();
  }
  /**
  * ��16�����ַ���װ��Ϊbyte����
  * @param hexString
  * @return
  */
  public static byte[] hexStringToBytes(String hexString) {
    hexString = hexString.toUpperCase();
    int length = hexString.length() / 2;
    char[] hexChars = hexString.toCharArray();
    byte[] b = new byte[length];
    for (int i = 0; i < length; i++) {
      int pos = i * 2;
      b[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
    }
    return b;
  }
  private static byte charToByte(char c) {
    return (byte) "0123456789ABCDEF".indexOf(c);
  }
  
}
