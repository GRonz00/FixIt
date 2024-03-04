package model.dao;

import exception.DAOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ModelloHasComponenteDAO {
    public static void aggiungi_componente(String modello, List<String> componente) throws DAOException {
        int i=0;
        try {
            Connection conn = ConnectionFactory.getConnection();
            int l = componente.size();

            while (i<l){
            CallableStatement cs = conn.prepareCall("{call aggiungi_componenteAmodello(?,?)}");
            cs.setString(1, modello);
            cs.setString(2, componente.get(i));
            try{cs.executeQuery();}
            catch (SQLException e)
            {System.out.print("Errore non è stato inserito il componente "+ componente.get(i)+"\n"+e+"\n");}
            i++;}
        } catch (SQLException e) {
            System.out.print("Error: " + e.getMessage());
        }
    }
}
