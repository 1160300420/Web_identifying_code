import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Utils.DBUtil;
import Utils.MD5Util;

@WebServlet("/Login")
public class Login extends HttpServlet {
  private static final long serialVersionUID = 1L;
  public Login() {
    super();
    // TODO Auto-generated constructor stub
  }
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
    String id = request.getParameter("ID");
    String pw = request.getParameter("PW");
    String code = request.getParameter("Code");
    HttpSession session = request.getSession();
    String pw_data = "";
    boolean ishave = false;
    if (!code.equalsIgnoreCase((String) session.getAttribute("randCheckCode"))) {
      request.setAttribute("errormsg", "验证码不正确");
      response.getWriter().println(
          "<script charset='utf8' type='text/javascript'>window.alert('*identify coding error');window.location.href='index.jsp'</script>");
    } else {
      try {
        Connection con = DBUtil.getConnection();
        Statement stmt1 = con.createStatement();
        String sql1 = "select * from newserver.userlist where ID='" + id + "'";
        ResultSet rs1 = stmt1.executeQuery(sql1);
        while (rs1.next()) {
          pw_data = rs1.getString(2);
          ishave = true;
        }
        if (!ishave) {
          response.getWriter().println(
              "<script charset='utf8' type='text/javascript'>window.alert('*there is no such user or password error');window.location.href='index.jsp'</script>");
        }
      } catch (Exception e) {
        // TODO: handle exception
        e.printStackTrace();
      }
      try {
        System.out.println(MD5Util.getMD5Code(URLEncoder.encode(pw_data+code,"utf-8")));
      } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      try {
        if (ishave && pw.equals(MD5Util.getMD5Code(URLEncoder.encode(pw_data+code,"utf-8")))) {
          response.getWriter().println("<script> window.location.href='Login.jsp'</script>");
        } else {
          response.getWriter().println(
              "<script charset='utf8' type='text/javascript'>window.alert('*no such user or password incorrect');window.location.href='index.jsp'</script>");
        }
      } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }
}
