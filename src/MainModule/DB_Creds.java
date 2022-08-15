/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainModule;

import java.sql.*;

/**
 *
 * @author HP
 */
public class DB_Creds {

    public Connection ConnectToDB() {
        String host = "localhost";
        String dbName = "XE";
        int port = 1521;
        String oracleURL = "jdbc:oracle:thin:@" + host + ":" + port + ":" + dbName;
        String username = "system";
        String password = "system";
        Connection connection = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
        } catch (Exception E) {
            System.err.println("Unable to load driver.");
            E.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(oracleURL, username, password); //?user=root&password=xyz");

        } catch (SQLException E) {
            System.out.println("SQLException:" + E.getMessage());
            System.out.println("SQLState:" + E.getSQLState());
            System.out.println("VendorError:" + E.getErrorCode());
        }
        return connection;
    }

    public void closeDBConnection(Connection connection) throws SQLException {
        try {
            connection.close();
        } catch (Exception E) {
            E.printStackTrace();
        }

    }

}
