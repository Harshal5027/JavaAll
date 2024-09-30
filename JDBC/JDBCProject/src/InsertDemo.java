import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class InsertDemo {

    public static void main(String[] args) throws Exception {

        String name = "Manasi";
        String email = "manasi07@gmail.com";
        String password = "Harsh@";
        String gender = "Female";
        String city = "Pune";

        Class.forName("com.mysql.cj.jdbc.Driver");
        //System.out.println("Driver class loaded sucessfully");

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_db", "root", "Harshu5027@");
        //System.out.println("Sucess");

        PreparedStatement ps = con.prepareStatement("insert into register values(?,?,?,?,?)");
        ps.setString(1, name);
        ps.setString(2, email);
        ps.setString(3, password);
        ps.setString(4, gender);
        ps.setString(5, city);

        int i = ps.executeUpdate();

        if( i > 0 ){

            System.out.println("Sucess");
        }else{
            System.out.println("Fail");
        }
    }
    
}
