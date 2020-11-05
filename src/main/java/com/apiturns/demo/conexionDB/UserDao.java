package com.apiturns.demo.conexionDB;

import com.apiturns.demo.entity.User;

import java.sql.*;
import java.util.ArrayList;

public class UserDao {
    String DB;
    String cadenaDeConexion;

    public UserDao() {
        DB = "dbappturns";
        cadenaDeConexion = "jdbc:mysql://localhost:3306/" + DB + "?user=root&password=root&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    }
    public User getUser(int id) {
        Connection con;
        ResultSet rs;
        User user = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(cadenaDeConexion);
            Statement cmd = con.createStatement();
            rs = cmd.executeQuery("SELECT * FROM users WHERE user_id='"+id+"'");
            if (rs.next()) {
                user = new User();
                user.setUser_id(rs.getInt("user_id"));
                user.setDni(rs.getInt("dni"));
                user.setName(rs.getString("name"));
                user.setLastname(rs.getString("lastname"));
                user.setEmail(rs.getString("email"));
                user.setPass(rs.getString("pass"));
                user.setType_user_id(rs.getInt("type_user_id"));
                //agregar el resto de las columnas no obligatorias
            }
            rs.close();
            con.close();

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }

    public User getUser(String email) {
        Connection con;
        ResultSet rs;
        User user = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(cadenaDeConexion);
            Statement cmd = con.createStatement();
            rs = cmd.executeQuery("SELECT * FROM users WHERE email='"+email+"'");
            if (rs.next()) {
                user = new User();
                user.setUser_id(rs.getInt("user_id"));
                user.setDni(rs.getInt("dni"));
                user.setName(rs.getString("name"));
                user.setLastname(rs.getString("lastname"));
                user.setEmail(email);
                user.setPass(rs.getString("pass"));
                user.setType_user_id(rs.getInt("type_user_id"));
                //agregar el resto de las columnas no obligatorias
            }
            rs.close();
            con.close();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }

    public ArrayList<User> getUsers(){
        Connection con;
        ResultSet rs;
        User user = null;
        ArrayList<User> listaUsers = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(cadenaDeConexion);
            Statement cmd = con.createStatement();
            rs = cmd.executeQuery("SELECT * FROM users ");
            while (rs.next()) {
                user = new User();
                user.setUser_id(rs.getInt("user_id"));
                user.setDni(rs.getInt("dni"));
                user.setName(rs.getString("name"));
                user.setLastname(rs.getString("lastname"));
                user.setEmail(rs.getString("email"));
                user.setPass(rs.getString("pass"));
                user.setType_user_id(rs.getInt("type_user_id"));
                listaUsers.add(user);
                //agregar el resto de las columnas no obligatorias
            }
            rs.close();
            con.close();

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            ex.printStackTrace();
        }
        return listaUsers;
    }

    public boolean insertUser(User user) {
        try {
            Connection con;
            con = DriverManager.getConnection(cadenaDeConexion);
            try (Statement cmd = con.createStatement()) {

                cmd.executeUpdate("INSERT INTO users (name,lastname,pass,email,dni, type_user_id) VALUES"
                        + "(' " + user.getName()
                        + "','" + user.getLastname()
                        + "','" + user.getPass()
                        + "','" + user.getEmail()
                        + "'," + user.getDni()
                        + "," + user.getType_user_id()+")");
                try{
                    String phone= user.getPhone();
                    cmd.executeUpdate("UPDATE users SET phone='"+phone+"' WHERE user_id='"+user.getUser_id()+"'");
                }catch(java.lang.NullPointerException ex){ }
                try{
                    String googleid= user.getGoogleid();
                    cmd.executeUpdate("UPDATE users SET googleid='"+googleid+"' WHERE user_id='"+user.getUser_id()+"'");
                }catch(java.lang.NullPointerException ex){

                }
            }
            con.close();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }

    }

    public boolean deleteUser(int id){
        try {
            Connection con;
            con = DriverManager.getConnection(cadenaDeConexion);
        try (Statement cmd = con.createStatement()) {
            cmd.executeUpdate("DELETE FROM users WHERE user_id="+id);
        }
            con.close();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public boolean updateUser (@org.jetbrains.annotations.NotNull User user){
        try {
            Connection con;
            con = DriverManager.getConnection(cadenaDeConexion);
            try (Statement cmd = con.createStatement()) {

                cmd.executeUpdate("UPDATE users SET "
                        + "name=' " + user.getName()
                        + "',lastname='" + user.getLastname()
                        + "',pass='" + user.getPass()
                        + "',dni=" + user.getDni()
                        + ",type_user_id=" + user.getType_user_id()+" WHERE user_id="+user.getUser_id());

            }
            con.close();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

}
