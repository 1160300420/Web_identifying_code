import java.net.URLEncoder;

import Utils.MD5Util;

public class test {
  public static void main(String[] args) {
    for(int i=10101010;i<=10101015;i++)
    {
      try {
        System.out.println(MD5Util.getMD5Code(URLEncoder.encode(""+i, "utf-8")));
      } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }
}
