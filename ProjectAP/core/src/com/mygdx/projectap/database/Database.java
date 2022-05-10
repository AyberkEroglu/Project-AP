package com.mygdx.projectap.database;
import java.sql.*;

public class Database {


    Statement s = null;
    ResultSet r = null;
    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root", "ooaek");
            Statement st = conn.createStatement();
            ResultSet myRs = st.executeQuery("");
        }
        catch (Exception ex) {
            ex.printStackTrace();

        }

    }




//    public void main( ) {
//        try {
//            // The newInstance() call is a work around for some
//            // broken Java implementations
//
//            Class.forName("com.mysql.jdbc.Driver").newInstance();
//        } catch (Exception ex) {
//            // handle the error
//        }
//
//        try {
//            conn =
//                    DriverManager.getConnection("jdbc:mysql://localhost/test?" +
//                            "user=minty&password=greatsqldb");
//
//            // Do something with the Connection
//
//
//        } catch (SQLException ex) {
//            // handle any errors
//            System.out.println("SQLException: " + ex.getMessage());
//            System.out.println("SQLState: " + ex.getSQLState());
//            System.out.println("VendorError: " + ex.getErrorCode());
//        }
//
//
//        try {
//            s = conn.createStatement();
//            r = s.executeQuery("SELECT foo FROM bar");
//
//            // or alternatively, if you don't know ahead of time that
//            // the query will be a SELECT...
//
//            if (s.execute("SELECT foo FROM bar")) {
//                r = s.getResultSet();
//            }
//
//            // Now do something with the ResultSet ....
//        } catch (SQLException ex) {
//            // handle any errors
//            System.out.println("SQLException: " + ex.getMessage());
//            System.out.println("SQLState: " + ex.getSQLState());
//            System.out.println("VendorError: " + ex.getErrorCode());
//        } finally {
//            // it is a good idea to release
//            // resources in a finally{} block
//            // in reverse-order of their creation
//            // if they are no-longer needed
//
//            if (r != null) {
//                try {
//                    r.close();
//                } catch (SQLException sqlEx) {
//                } // ignore
//
//                r = null;
//            }
//
//            if (s != null) {
//                try {
//                    s.close();
//                } catch (SQLException sqlEx) {
//                } // ignore
//
//                s = null;
//            }
//        }
    }





