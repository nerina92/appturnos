package com.apiturns.demo.conexionDB;

import com.apiturns.demo.entity.Court;
import com.apiturns.demo.entity.User;

import java.sql.*;
import java.util.ArrayList;

public class CourtDao {
    String DB;
    String cadenaDeConexion;

    public CourtDao() {
        DB = "dbappturns";
        cadenaDeConexion = "jdbc:mysql://localhost:3306/" + DB + "?user=root&password=root&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    }
    public Court getCourt(int id) {
        Connection con;
        ResultSet rs;
        Court court = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(cadenaDeConexion);
            Statement cmd = con.createStatement();
            rs = cmd.executeQuery("SELECT * FROM courts WHERE court_id='"+id+"'");
            if (rs.next()) {
                court = new Court();
                court.setCourt_id(id);
                court.setName(rs.getString("name"));
                court.setAddress(rs.getString("lastname"));
                court.setDetails(rs.getString("details"));
            }
            rs.close();
            con.close();

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            ex.printStackTrace();
        }
        return court;
    }

    public ArrayList<Court> getCourts(){
        Connection con;
        ResultSet rs;
        Court court = null;
        ArrayList<Court> listaCourts = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(cadenaDeConexion);
            Statement cmd = con.createStatement();
            rs = cmd.executeQuery("SELECT * FROM courts ");
            while (rs.next()) {
                court = new Court();
                court.setCourt_id(rs.getInt("court_id"));
                court.setName(rs.getString("name"));
                court.setAddress(rs.getString("address"));
                court.setDetails(rs.getString("details"));
                listaCourts.add(court);
            }
            rs.close();
            con.close();

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            ex.printStackTrace();
        }
        return listaCourts;
    }

    public boolean insertCourt(Court court) {
        try {
            Connection con;
            con = DriverManager.getConnection(cadenaDeConexion);
            try (Statement cmd = con.createStatement()) {
                cmd.executeUpdate("INSERT INTO courts (name,address,details) VALUES"
                        + "(' " + court.getName()
                        + "','" + court.getAddress()
                        + "','" + court.getDetails()+"')");
            }
            con.close();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }

    }

    public boolean deleteCourt(int id){
        try {
            Connection con;
            con = DriverManager.getConnection(cadenaDeConexion);
        try (Statement cmd = con.createStatement()) {
            cmd.executeUpdate("DELETE FROM courts WHERE court_id="+id);
        }
            con.close();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

}
