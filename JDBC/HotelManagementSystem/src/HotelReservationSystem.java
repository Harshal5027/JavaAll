import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class HotelReservationSystem {

    private static final String url = "jdbc:mysql://localhost:3306/hotel_db";

    private static final String username = "root";

    private static final String  password = "Harshu5027@";

    public static void main(String[] args) throws Exception{
        
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection con = DriverManager.getConnection(url, username, password);

        while( true ){

            System.out.println();
            System.out.println("HOTEL MANAGEMENT SYSTEM");

            Scanner sc = new Scanner(System.in);
            System.out.println("1. Reserve a room");
            System.out.println("2. View Reservation");
            System.out.println("3. Get a room Number");
            System.out.println("4. Update Reservation");
            System.out.println("5. Delete Reservation");
            System.out.println("0. Exit");
            System.out.println("Choose an optin : ");

            int choise =  sc.nextInt();

            switch (choise) {
                case 1 :
                    reserveRoom(con, sc);
                    break;
                
                case 2 : 
                    viewReservation(con);
                    break;

                case 3:
                    getRoomNumber(con, sc);
                    break;

                case 4 :
                    updateReservation(con, sc);
                    break;

                case 5 : 
                    deleteReservation(con, sc);
                    break;
                
                case 0 : 
                    exit();
                    return;
                
                default:
                    System.out.println("Invalid Option");
                    break;
            }
        }

    }

    private static void reserveRoom( Connection con, Scanner sc){

        try {
            
            System.out.println("Enter a Guest Name : ");
            String guestName = sc.next();
            sc.nextLine();
            System.out.println("Enter room Number : ");
            int roomNumber = sc.nextInt();
            System.out.println("Enter a contact Number : ");
            String contactNumber = sc.next();

            String sql = "INSERT INTO reservation (guest_name, room_number, contact_number)"+"VALUES('"+ guestName +"'," + roomNumber  + ", '" + contactNumber+ "')";

            PreparedStatement ps = con.prepareStatement(sql);

            int count = ps.executeUpdate();

            if( count > 0 ){
                System.out.println("Reservation Sucessfully");
            }else{
                System.out.println("Reservation failed");
            }

        
        } catch (SQLException e) {

            e.printStackTrace();
            // TODO: handle exception
        }
    }
    
    private static void viewReservation( Connection con){

        String sql = " SELECT reservation_id, guest_name, room_number, contact_number, reservation_date FROM reservation";

        try {
            
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();

            System.out.println("Current Reservation : ");

            while( resultSet.next()){

                System.out.println("===============================================================================");


                int reservation_id1 = resultSet.getInt("reservation_id");
                System.out.println("Reservation ID : "+ reservation_id1);
                String guestname1 = resultSet.getString("guest_name");
                System.out.println("Guest Name : "+guestname1);
                int room_number1 = resultSet.getInt("room_number");
                System.out.println("Room Number : "+room_number1);
                String contact_number1 = resultSet.getString("contact_number");
                System.out.println("Contact Number : "+contact_number1);
                String reservationDate = resultSet.getTimestamp("reservation_date").toString();
                System.out.println("Reservation Date : "+reservationDate);

            }
        } catch (SQLException e) {
            e.printStackTrace();
            // TODO: handle exception
        }
    }

    private static void getRoomNumber( Connection con, Scanner sc){

        try {
            
            System.out.println("Enter reservation ID : ");
            int reservationId1 = sc.nextInt();
            System.out.println("Enter Guest Name : ");
            String guestName1 = sc.next();

            String sql = " SELECT room_number FROM reservation " + 
                        "WHERE reservation_id = "+ reservationId1 +
                        "AND guest_name = '"+ guestName1 +"'";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet resultSet = ps.executeQuery();

            if( resultSet.next() ){
                int roomNumber = resultSet.getInt("room_number");

                System.out.println("Room Number for Reservation ID "+reservationId1+" and Guest "+guestName1 + " is : "+ roomNumber);

            }else{
                System.out.println("Reservation Not found for given ID and guest name ");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // TODO: handle exception
        }
    }

    private static void updateReservation( Connection con, Scanner sc){

        try {
            
            System.out.println("Enter reservation ID to update : ");
            int reservationId = sc.nextInt();
            // sc.nextInt();

            if(!reservationExists(con, reservationId)){
                System.out.println("Reservation not found for the Given ID.");
                return;
            }

            System.out.println("Enter new guest name : ");
            String newGuestName = sc.next();
            System.out.println("Enter new room Number : ");
            int newRoomNumber = sc.nextInt();
            System.out.println("Enter new Contact Number : ");
            String newContactNumber = sc.next();

            String sql = "UPDATE reservation SET guest_name = '" + newGuestName + "',"+ 
            "room_number = "+ newRoomNumber + ", " + newContactNumber + ", " +
            "WHERE reservation_id = " + reservationId;

            PreparedStatement ps = con.prepareStatement(sql);

            int count = ps.executeUpdate();

            if( count > 0){
                System.out.println("Reservation Updated Sucessfully !");
            }else{
                System.out.println("Reservation Update fail.");
            }

        } catch (SQLException e) {

            e.printStackTrace();
            // TODO: handle exception
        }
    }

    private static void  deleteReservation( Connection con, Scanner sc ){

        try {
            System.out.println("Enter Reservation ID to delete : ");
            int reservationId = sc.nextInt();

            if( !reservationExists( con, reservationId)){
                System.out.println("Reservation not found for given ID ");
                return;
            }

            String sql = "DELETE FROM reservation WHERE reservation_id = "+ reservationId;

            PreparedStatement ps = con.prepareStatement(sql);

            int count = ps.executeUpdate();

            if( count > 0 ){
                System.out.println("Reservation deleted Sucessfully !");
            }else{
                System.out.println("Reservation deletion failed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // TODO: handle exception
        }
    }

    static boolean reservationExists( Connection con, int reservationId){

        try {
            
            String sql = "SELECT reservation_id FROM reservation WHERE reservation_id = "+reservationId;

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();

            return resultSet.next();

        }catch (SQLException e) {
            e.printStackTrace();
            return false;
            // TODO: handle exception
        }
    }
   
    public static void exit() throws InterruptedException {

        System.out.print("Existing System");
        int i = 5; 
        while( i!=0){
            System.out.print(".");
            Thread.sleep(500);
            i--;
        }
        System.out.println();
        System.out.println("ThankYou for Using  Reservation System!!!");
    }
}
