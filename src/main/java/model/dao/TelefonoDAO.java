package model.dao;

import model.domain.Fornitore;
import model.domain.Role;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TelefonoDAO {
    public static void aggiungi_telefono(String fornitore, List<String> num) {
        int i=0;
        try {
            Connection conn = ConnectionFactory.getConnection();
            int l = num.size();
            while (i<l){
            CallableStatement cs = conn.prepareCall("{call aggiungi_telefono(?,?)}");
            cs.setString(1, fornitore);;
            cs.setString(2,num.get(i));
            try {
                cs.executeQuery();
            }catch (SQLException e){System.out.print("Non è stato inserito il numero " + num.get(i)+e);}
            i++;}
        } catch (
                SQLException e) {
            System.out.print("Error: " + e.getMessage());
        }
    }
}
