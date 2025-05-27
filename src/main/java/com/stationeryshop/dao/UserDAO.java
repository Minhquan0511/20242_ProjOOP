package com.stationeryshop.dao;

import com.stationeryshop.utils.DBConnection;
import com.stationeryshop.utils.PwdHash;
import com.stationeryshop.utils.RandomUserId;

import javax.swing.*;
import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class UserDAO {
    private static DBConnection db;
    public UserDAO(){
        Properties props = new Properties();
        try{
            FileInputStream fis = new FileInputStream("src/main/resources/db.properties");
            props.load(fis);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        String useradmin = props.getProperty("db.loginuser");
        String pwdadmin = props.getProperty("db.loginpwd");
        this.db = new DBConnection(useradmin, pwdadmin);
    }
    public UserDAO(String useradmin, String pwdadmin){
        this.db = new DBConnection(useradmin, pwdadmin);
    }
    void createUser(String username, String password, String role){
        //Lưu thông tin người dùng vào database
        Connection conn = null;
        PreparedStatement stmt = null;
        String query = "insert into user_view(user_id,user_name,pwd_hash,role_name) values(?,?,?,?)";
        String pwd = new PwdHash(password).getHash();
        String user_id = new RandomUserId().getRandomUserId();
        try{
            conn = db.connect();
            if(conn == null) JOptionPane.showMessageDialog(null, "The password is incorrect", "Warning", JOptionPane.WARNING_MESSAGE);
            stmt = conn.prepareStatement(query);
            stmt.setString(1,user_id);
            stmt.setString(2,username);
            stmt.setString(3,pwd);
            stmt.setString(4,role);
            stmt.executeUpdate();
            System.out.println("Create user success");
        }catch(SQLException e){
            e.printStackTrace();
        }
        db.closeConnect();
    }
    public boolean findUserByUsername(String username) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet result = null;

        String query = "SELECT user_name FROM users WHERE user_name LIKE ?";

        try {
            conn = db.connect();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, username); // Sử dụng tham số an toàn
            result = pstmt.executeQuery();

            while (result.next()) {
                System.out.println(result.getString("user_name"));
            }

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng kết nối an toàn
            try {
                if (result != null) result.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }
    public boolean verifyPassword(String username, String password){
        Connection conn = null;
        PreparedStatement stmt = null;
        String query = "select pwd_hash from users where user_name like ?";
        ResultSet result = null;
        try{
            conn = db.connect();
            stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            result = stmt.executeQuery();
            while(result.next()){
                String pwd_hash = result.getString("pwd_hash");
                PwdHash pwd = new PwdHash(password);
                if(pwd.verify(pwd_hash)) return true;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        finally{
            try {
                if (result != null) result.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    public boolean updateUser(String username, String password){
        Connection conn = null;
        PreparedStatement stmt = null;
        String query = "update users set pwd_hash=? where user_name=?";
        String pwd = new PwdHash(password).getHash();
        try{
            conn = db.connect();
            stmt = conn.prepareStatement(query);
            stmt.setString(1,pwd);
            stmt.setString(2, username);
            return true;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public boolean deleteUser(String username){
        Connection conn = null;
        PreparedStatement stmt = null;
        String query = "delete from users where user_name=?";
        try{
            conn = db.connect();
            stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            return true;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
