package DAO;

import java.sql.*;


public class DAO {
    private static final String url ="jdbc:mysql://localhost:3306/db_daimain";
    private static final String user="root";
    private static final String password="";
    private static Connection conn1 = null;

    public static void registerDriver(){
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            System.out.println("Driver correttamente registrato");
        } catch (SQLException e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }

    public static void connectionDB(){
        try{
            conn1 = DriverManager.getConnection(url, user, password);
            if (conn1 != null) {
                System.out.println("Connected to the database test");
            }
        }catch (SQLException ex){
            System.out.println("Errore: " + ex.getMessage());
        }

    }

    public static boolean RegisterFormQuery(String nom,String prenom,String email,String mdp,String nomEntreprise,String pays,String adresse,String numtel, Date date){
        boolean done;
        try{
            connectionDB();
            Statement st = conn1.createStatement();
           String query = "INSERT INTO Users(Email,MotDePasse,Nom,Prenom,Adresse,PaysDeNaissance,Type,Telephone, DateDeNaissance)" +
                    "VALUES ('"+email+"', '"+mdp+"', '"+nom+"', '"+prenom+"' , '"+adresse+"', '"+pays+"', '"+1+"', '"+numtel+"', '"+date+"');";
            st.executeUpdate(query);
            done = true;

        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            done = false;
        }finally {
            if(conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return done;

    }

    public static boolean LoginFormQuery(String email, String mdp){
        conn1 = null;
        boolean found = false;
        try {
            connectionDB();
            Statement st = conn1.createStatement();
            String query = "SELECT * FROM users WHERE Email='"+ email +"' AND MotDePasse='"+ mdp +"';";
            System.out.println(query);
            ResultSet rs = st.executeQuery(query);
            if(rs.next()){
                found = true;
            }
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        } finally {
            if(conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return found;
    }

}
