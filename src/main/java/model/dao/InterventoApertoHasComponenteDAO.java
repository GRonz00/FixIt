package model.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class InterventoApertoHasComponenteDAO {
    public static void aggiungi_componente(int intervento, List<String> componente) {
        int i=0;
        try {
            Connection conn = ConnectionFactory.getConnection();
            while (i<componente.size()){
            CallableStatement cs = conn.prepareCall("{call registra_componenti_intervento(?,?)}");
            cs.setInt(1, intervento);
            cs.setString(2, componente.get(i));
            try {
                cs.executeQuery();
            }catch (SQLException e){
                System.out.print("Non è stato inserito il componente "+ componente.get(i)+"\n"+e+"\n");
            }
            i++;
    }
        } catch (SQLException e) {
            System.out.print("Errore: "+e);
        }
    }

    public static boolean elimina_componente(int intervento,String componente) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call elimina_componente_intervento(?,?)}");
            cs.setInt(1,intervento);
            cs.setString(2,componente);
            cs.execute();
            ResultSet rs = cs.getResultSet();
            rs.next();
            if(rs.getInt(1)>0)return true;
        }catch (SQLException e){
            System.out.print("Non è stato eliminato il componente: "+e+"\n");
        }
        return false;
    }
}
