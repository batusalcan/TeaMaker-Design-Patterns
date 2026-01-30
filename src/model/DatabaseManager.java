package model;

import java.sql.*;
import java.time.LocalDate;

public class DatabaseManager {

    private static DatabaseManager instance;

    private static final String URL = "jdbc:mysql://localhost:3306/teamaker_db";
    private static final String USER = "root";
    private static final String PASSWORD = "112batushan";

    private DatabaseManager() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DatabaseManager getInstance() {
        if (instance == null) instance = new DatabaseManager();
        return instance;
    }


    public void insertLog(int numberOfCups) {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            CallableStatement stmt = conn.prepareCall("{ call sp_insert_log(?) }");
            stmt.setInt(1, numberOfCups);
            stmt.execute();
            conn.close();
            System.out.println("Log added to database.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getTotalCupsDaily() {
        int total = 0;
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

            CallableStatement stmt = conn.prepareCall("{ call sp_get_daily_total(?) }");


            LocalDate today = LocalDate.now();
            stmt.setDate(1, java.sql.Date.valueOf(today));

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);
            }
            conn.close();
        } catch (Exception e) {
            System.out.println("Günlük toplam çekilirken hata oluştu!");
            e.printStackTrace();
        }
        return total;
    }

    public int getTotalCupsMonthly() {
        int total = 0;
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            CallableStatement stmt = conn.prepareCall("{ call sp_get_monthly_total(?, ?) }");

            LocalDate today = LocalDate.now();
            stmt.setInt(1, today.getMonthValue());
            stmt.setInt(2, today.getYear());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {

                total = rs.getInt(1);
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return total;
    }
}