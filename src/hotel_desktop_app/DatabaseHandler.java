package hotel_desktop_app;

/**
 * @author Kotsolakos Konstantinos
 **/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseHandler {
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/hotel_database";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "110298";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }

    // Other methods for querying the database...
    
    public static int update_hotel_rooms_and_users(String textFromPriceField, String textFromReservedArea, int id, String reserved){
        String[] parts = textFromReservedArea.split(", ");
        String reserved_rooms = "" + id + ",";
        String reserved_rooms_from_query = "";
        
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE hotel_rooms SET price = ?, reserved_by = ? WHERE id = ?");
            preparedStatement.setString(1, textFromPriceField);
            preparedStatement.setString(2, textFromReservedArea);
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
            if (parts.length == 2) {
                String number = parts[0];
                int userId = Integer.parseInt(number);
                PreparedStatement preparedSecondStatement = connection.prepareStatement("UPDATE users SET reserved_rooms = reserved_rooms || ? WHERE id = ?");
                preparedSecondStatement.setString(1, reserved_rooms);
                preparedSecondStatement.setInt(2, userId);
                preparedSecondStatement.executeUpdate();
            }
            else if(parts.length == 1){
                String number = reserved.split(", ")[0];
                int userId = Integer.parseInt(number);
                PreparedStatement preparedThirdStatement = connection.prepareStatement("SELECT reserved_rooms FROM users WHERE id = ?");
                preparedThirdStatement.setInt(1, userId);
                ResultSet resultSet = preparedThirdStatement.executeQuery();
                while (resultSet.next()) {
                    reserved_rooms_from_query = resultSet.getString("reserved_rooms");
                }
                String[] reserved_rooms_parts = reserved_rooms_from_query.split(",");
                String id_string_type = id + "";
                String result = "";
                for (String room : reserved_rooms_parts) {
                    if (!room.equals(id_string_type)) {
                        result += room + ",";
                    }
                }
                PreparedStatement preparedFourthStatement = connection.prepareStatement("UPDATE users SET reserved_rooms = ? WHERE id = ?");
                preparedFourthStatement.setString(1, result);
                preparedFourthStatement.setInt(2, userId);
                preparedFourthStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return (0);
        }
        return (1);
    }
    
    public static int insert_into_hotel_rooms(byte[] imageData, String textFromDescTextArea, String textFromPriceField, String textFromReservedArea) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO hotel_rooms (image, description, price, reserved_by) VALUES (?, ?, ?, ?)");
            preparedStatement.setBytes(1, imageData);
            preparedStatement.setString(2, textFromDescTextArea);
            preparedStatement.setString(3, textFromPriceField);
            preparedStatement.setString(4, textFromReservedArea);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return (0);
        }
        return (1);
    }
                
}
