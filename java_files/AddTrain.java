import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;
import java.util.*;

public class AddTrain extends HttpServlet {
    public int addTrain(String tid, String name, String source, String destination, String max_seats, String duration) throws Exception{
        String url = "jdbc:mysql://localhost:3306/ars";
        String sqluser = "root";
        String sqlpwd = "krnb0502";

        Class.forName("com.mysql.cj.jdbc.Driver");

        System.out.println("Hey I am after forname method in addTrain() - AddTrain class");
        
        Connection con = DriverManager.getConnection(url, sqluser, sqlpwd);

        Statement st = con.createStatement();

        System.out.println("Hey I am after create method in addTrain() - AddTrain class");
        int ms=Integer.parseInt(max_seats);
        int ti=Integer.parseInt(tid);
        int du=Integer.parseInt(duration);
        try {
            String s = String.format("select count(*) from flight where tid = '%d'", ti);
            ResultSet rs = st.executeQuery(s);
            rs.next();
            int count = rs.getInt(1);
            if(count > 0) {
                return 0;
            }

            s = String.format("insert into flight(tid, name, source, destination, max_seats, duration) values('%d','%s','%s','%s','%d','%d')", ti, name, source, destination, ms, du);
            st.executeUpdate(s);
            // PrintWriter pw=new getWriter();
            // pw.println("alert('Train record inserted...')");
            System.out.println("flight record inserted...");
        }
        catch(Exception e) {
            System.out.println(e);
            return -1;
        }
        return 1;
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String id = req.getParameter("tid");
        String name = req.getParameter("tname");
        String source = req.getParameter("source");
        String destination = req.getParameter("destination");
        String max_seats = req.getParameter("maxseats");
        String duration = req.getParameter("duration");
        System.out.println(id + ", " + name + ", " + source + ", " + destination + ", " + max_seats + ", " + duration);

        try {
            int result = addTrain(id, name, source, destination, max_seats, duration);
            if(result == 0) {
                System.out.println("Flight already exists...");
               // Cookie c = new Cookie("InvalidaddTrain", "invalid");
               // c.setMaxAge(2);
               // res.addCookie(c);
                res.sendRedirect("addFlight.html");
            }
            else if(result == 1) {
                System.out.println("New Flight added");
               // Cookie c = new Cookie("InvalidaddTrain", "valid");
               // c.setMaxAge(2);
               // res.addCookie(c);
               // Cookie c1 = new Cookie("tid", id);
               // res.addCookie(c1);
                res.sendRedirect("ahome.html");
            }
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }
}