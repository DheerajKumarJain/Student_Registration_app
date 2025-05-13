import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Set response type
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Get form data
        String name = request.getParameter("name");
        String rollno = request.getParameter("rollno");
        String studentClass = request.getParameter("class");
        String email = request.getParameter("email");
        String contact = request.getParameter("contact");
        String gender = request.getParameter("gender");
        String department = request.getParameter("department");

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to database
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb", "root", "your_password");

            // Prepare SQL query
            String query = "INSERT INTO students (name, rollno, class, email, contact, gender, department) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, name);
            pst.setString(2, rollno);
            pst.setString(3, studentClass);
            pst.setString(4, email);
            pst.setString(5, contact);
            pst.setString(6, gender);
            pst.setString(7, department);

            int count = pst.executeUpdate();

            if (count > 0) {
                out.println("<h2>Student Registered Successfully!</h2>");
                out.println("<a href='index.html'>Register Another Student</a>");
                out.println("<br><a href='view_students.html'>View Students</a>");
            } else {
                out.println("<h2>Something went wrong!</h2>");
            }

            // Close connection
            pst.close();
            con.close();
        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }

        out.close();
    }
}
