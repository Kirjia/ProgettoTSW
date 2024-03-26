package com.yoru.model.DAO;




import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import javax.sql.DataSource;

import com.yoru.DBServices.GenericDBOp;
import com.yoru.model.Entity.Role;
import com.yoru.model.Entity.User;

public class UserDAO implements GenericDBOp<User> {


    private static int passCode = 256;
    private static final String LOGIN = "bool";
    public static final String TABLE_NAME = "user";
    private DataSource ds;
    
    public UserDAO(DataSource ds) {
    	this.ds = ds;
    	
    }

    @Override
    public Collection<User> getAll(){
        return null;
    }

    @Override
    public User getById(int id) {
        return null;
    }


    public User getById(String email)throws SQLException{
        Connection connection =null;
        PreparedStatement ps = null;
        User user = new User();
        ResultSet rs = null;


        try {
            connection = ds.getConnection();
            connection.setAutoCommit(false);
            String sql = "SELECT nome, cognome FROM "+ UserDAO.TABLE_NAME + " WHERE email = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, email);



            rs = ps.executeQuery();

            if (rs.next()) {
                user.setNome(rs.getString(User.COLUMNLABEL2));
                user.setCognome(rs.getString(User.COLUMNLABEL3));
                user.setEmail(email);
            }
            else
                System.out.println("Impossibile inserire il record \n");

            connection.commit();

        } finally {

            if (rs != null)
                rs.close();

            if (ps != null)
                ps.close();
            connection.close();
             
        }
        return user;
    }

    @Override
    public synchronized boolean insert(User user) throws SQLException{
        Connection connection =null;
        PreparedStatement ps = null;
        boolean statement = false;


        try {
            connection = ds.getConnection();
            connection.setAutoCommit(false);

            String sql = "INSERT INTO "  + UserDAO.TABLE_NAME + " (email, password, nome, cognome, telefono)" +
                    "VALUE (?,SHA2(?, ?),?,?,?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.setInt(3, passCode);
            ps.setString(4, user.getNome());
            ps.setString(5, user.getCognome());
            ps.setString(6, user.getTelefono());



            int result = ps.executeUpdate();

            if (result > 0) {
                System.out.println("Inserimento effettuato con successo\n");
                statement = true;
            }
            else
                System.out.println("Impossibile inserire il record \n");

            connection.commit();

        } finally {

            if (ps != null)
                ps.close();
            connection.close();
             
        }
        return statement;
    }

    @Override
    public synchronized boolean update(User user)throws SQLException{
        Connection connection =null;
        PreparedStatement ps = null;
        boolean statement = false;


        try {
            connection = ds.getConnection();
            connection.setAutoCommit(false);
            String sql = "UPDATE "+ UserDAO.TABLE_NAME + " SET password = sha2(?, ?), telefono=?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, user.getPassword());
            ps.setInt(2, passCode);
            ps.setString(3, user.getTelefono());


            int result = ps.executeUpdate();

            if (result > 0) {
                System.out.println("Inserimento effettuato con successo\n");
                statement = true;
            }
            else
                System.out.println("Impossibile inserire il record \n");

            connection.commit();

        } finally {

            if (ps != null)
                ps.close();
            connection.close();
             
        }
        return statement;
    }

    @Override
    public synchronized boolean remove(User user)throws SQLException {
        Connection connection =null;
        PreparedStatement ps = null;
        boolean statement = false;


        try {
            connection = ds.getConnection();
            connection.setAutoCommit(false);
            String sql = "DELETE FROM "+ UserDAO.TABLE_NAME + " WHERE email = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, user.getEmail());


            int result = ps.executeUpdate();

            if (result > 0) {
                System.out.println("Cancellazione effettuata con successo\n");
                statement = true;
            }
            else
                System.out.println("Impossibile Cancellare il record \n");

            connection.commit();

        } finally {

            if (ps != null)
                ps.close();
            connection.close();
             
        }
        return statement;
    }

    public User login(String email, String  password)throws SQLException{
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = new User();

        try {
            connection = ds.getConnection();
            connection.setAutoCommit(false);

            String sql = "SELECT id, nome, cognome, role FROM "+ UserDAO.TABLE_NAME + " WHERE email = ? AND password = sha2(?, ?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            ps.setInt(3, passCode);
            


            rs = ps.executeQuery();
            
            if (rs.next()){

               user.setId(rs.getInt("id"));
               user.setNome(rs.getString(User.COLUMNLABEL2));
               user.setCognome(rs.getString(User.COLUMNLABEL3));
               String roleString = rs.getString("role");
               if (roleString.equals(Role.ADMIN)) {
				user.setRole(Role.ADMIN);
               }else {
				user.setRole(Role.USER);
               }
            }

            connection.commit();

        
        } finally {

            if (rs != null)
                rs.close();

            if (ps != null)
                ps.close();
            connection.close();
             
        }
        return user;
    }

}
