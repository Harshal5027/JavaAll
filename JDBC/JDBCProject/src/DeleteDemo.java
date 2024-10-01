import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class DeleteDemo {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter mail for Delete");
        String email = sc.next();

        Class.forName("com.mysql.cj.jdbc.Driver");
        
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_db", "root", "Harshu5027@");
        
        PreparedStatement ps = con.prepareStatement("delete from register where email = ?");

        ps.setString(1, email);

        int count = ps.executeUpdate();

        if( count > 0){
            System.out.println("Deletion Sucessfully");
        }else{
            System.out.println("Deletion Fail");
        }

        con.close();
    }
}