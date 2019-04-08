import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javax.servlet.http.HttpServlet;
import Utils.AESUtil;
import Utils.DBUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
@WebServlet("/Modify")
public class Modify extends HttpServlet {

  private static final long serialVersionUID = 1L;
  public Modify() {
    super();
  }
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
    String id = request.getParameter("ID");
    String pw = request.getParameter("newPW");
    String pw_data = "";
    boolean ishave=false;
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
        }else {
          byte key[] =pw_data.getBytes("utf-8");
          String newpw1 =new String(AESUtil.decrypt(pw, key), "utf-8");
          String sql="update newserver.userlist set PW='"+newpw1+"' where ID='"+id+"'";
          stmt1.execute(sql);
        }
      } catch (Exception e) {
        // TODO: handle exception
        e.printStackTrace();
      }
  }
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
    doPost(request, response);
  }
}
