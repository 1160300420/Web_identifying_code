package Utils;

import java.security.MessageDigest;



public class MD5Util {
      private static final String KEY_MD5 = "MD5";
      /**
       * 全局数组
       */
      private static final String[] strDigits = {"0", "1", "2", "3", "4", "5",
              "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
      private static final MD5Util MD5 = null;

      /**
       * 返回形式为数字跟字符串
       */
      private static String byteToArrayString(byte bByte) {
          int iRet = bByte;
          if (iRet < 0) {
              iRet += 256;
          }
          int iD1 = iRet / 16;
          int iD2 = iRet % 16;
          return strDigits[iD1] + strDigits[iD2];
      }

      /**
       * 转换字节数组为16进制字串
       */
      private static String byteToString(byte[] bByte) {
          StringBuffer sBuffer = new StringBuffer();
          for (int i = 0; i < bByte.length; i++) {
              sBuffer.append(byteToArrayString(bByte[i]));
          }
          return sBuffer.toString();
      }

      /**
       * MD5加密
       *
       * @param strObj
       * @return
       * @throws Exception
       */
      public static String getMD5Code(String strObj) throws Exception {
          MessageDigest md = MessageDigest.getInstance(KEY_MD5);
          // md.digest() 该函数返回值为存放哈希值结果的byte数组
          return byteToString(md.digest(strObj.getBytes()));
      }

    /*  public static void main(String[] arge) throws Exception {
          String result = MD5.getMD5Code(URLEncoder.encode("小妹妹123", "utf-8"));
          System.out.println(result);
      }*/

  }/*
  public static String encodebyMD5(String info){
      try {
          MessageDigest md5 = MessageDigest.getInstance("MD5");
          md5.update(info.getBytes("UTF-8"));
          byte[] encryption = md5.digest();

          StringBuffer strBuf = new StringBuffer();
          for (int i = 0; i < encryption.length; i++) {
              if (Integer.toHexString(0xff & encryption[i]).length() == 1) {
                  strBuf.append("0").append(
                          Integer.toHexString(0xff & encryption[i]));
              } else {
                  strBuf.append(Integer.toHexString(0xff & encryption[i]));
              }
          }

          return strBuf.toString();
      } catch (Exception e) {
          // TODO: handle exception
          return "";
      }
  }*/
