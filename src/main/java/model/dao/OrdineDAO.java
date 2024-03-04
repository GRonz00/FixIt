package model.dao;

import model.domain.ComponenteQuantita;
import model.domain.Ordine;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdineDAO {
    public static int aggiungi_ordine(String fornitore, Date date){
        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call registra_ordine(?,?,?)}");
            cs.setString(1, fornitore);
            cs.setDate(2, date);
            cs.registerOutParameter(3,Types.INTEGER);
            cs.executeQuery();
            return cs.getInt(3);
        } catch (SQLException e) {
            System.out.print("Errore:" + e);

        }
        return -1;
    }

    public static float inserisci_costo(int ordine) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call costo_ordine(?,?)}");
            cs.setInt(1,ordine);
            cs.registerOutParameter(2,Types.FLOAT);
            cs.executeQuery();
            return cs.getFloat(2);
        } catch (SQLException e) {
            System.out.print("Errore:" + e);
        }
        return -1;
    }

    public static Ordine visualizza(int codice) {
        Date data;
        float costo;
        List<ComponenteQuantita> componenti = new ArrayList<>();
        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call visualizza_ordine(?)}");
            cs.setInt(1,codice);
            cs.execute();
            ResultSet rs=cs.getResultSet();
            if(rs.next()){
            data = rs.getDate(2);
            costo= rs.getFloat(3);
            cs.getMoreResults();
            rs = cs.getResultSet();
            while (rs.next())
                componenti.add(new ComponenteQuantita(rs.getString(1), rs.getInt(2)));
            return new Ordine(codice,data,costo,componenti);}
        } catch (SQLException e) {
            System.out.print("Errore: "+e+"\n");
        }
        return null;
    }

    public static boolean elimina(int codice) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call elimina_ordine(?)}");
            cs.setInt(1,codice);
            cs.executeQuery();
            ResultSet rs = cs.getResultSet();
            rs.next();
            if(rs.getInt(1)>0)return true;
        } catch (SQLException e) {
            System.out.print("Error: " + e.getMessage()+"\n");
        }
        return false;
    }
}
