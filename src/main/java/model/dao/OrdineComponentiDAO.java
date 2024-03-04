package model.dao;

import model.domain.ComponenteQuantita;

import java.sql.*;
import java.util.List;

public class OrdineComponentiDAO {
    public static void aggiungi_componente(int ordine,String fornitore, List<ComponenteQuantita> componenti) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            int i=0;
            while (i<componenti.size()){
            CallableStatement cs = conn.prepareCall("{call aggiungi_componente_ordine(?,?,?,?)}");
            cs.setInt(1, ordine);
                cs.setString(2,fornitore);
            cs.setString(3,componenti.get(i).componente());
            cs.setInt(4,componenti.get(i).quantita());
            try {
                cs.executeQuery();
            }catch (SQLException e)
            {System.out.print("Non è stato inserito il componente: "+componenti.get(i).componente()+"\n"+e+"\n");}
            i++;
            }
        } catch (SQLException e) {
            System.out.print("Errore:" + e);
        }
    }
}
