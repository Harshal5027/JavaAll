import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.*;

public class UpdateDemo {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter City for Update");
        String city = sc.next();
        System.out.println("Enter EmailId");
        String email = sc.next();

        
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_db", "root", "Harshu5027@");

            PreparedStatement ps = con.prepareStatement("update register set city = ? where email = ?");

            ps.setString(1, city);
            ps.setString(2, email);
            
            int count = ps.executeUpdate();
            
            if( count > 0 ){
                System.out.println("Updated Sucessfully");
            }else{
                System.out.println("Updation fail");
            }

            con.close();

        } catch (Exception e) {

            e.printStackTrace();
            // TODO: handle exception
        }
       
    }
    
}
