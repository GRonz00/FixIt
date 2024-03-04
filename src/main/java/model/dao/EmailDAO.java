package model.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class EmailDAO {
    public static void aggiungi_email(String fornitore, List<String> email) {
        int i=0;
        try {
            Connection conn = ConnectionFactory.getConnection();
            while (i<email.size()){
            CallableStatement cs = conn.prepareCall("{call aggiungi_email(?,?)}");
            cs.setString(1, fornitore);
                cs.setString(2,email.get(i));

            try {
                cs.executeQuery();
            }catch (SQLException e){System.out.print("Non è stata aggiunta l'email: "+ email.get(i)+"\n" );}
            i++;}
        } catch (
                SQLException e) {
            System.out.print("Error: " + e.getMessage());
        }
    }
}
