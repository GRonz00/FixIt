package model.dao;

import model.domain.Categoria;
import model.domain.Componente;
import model.domain.Tipo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class ComponenteDAO {
    public static void aggiungi_componente(Componente componente) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call aggiungi_componente(?,?,?,?,?,?)}");
            cs.setString(1, componente.codice());
            cs.setInt(2, componente.giacenza());
            int c;
            c =  componente.tipo().getId();
            String tipo = Objects.requireNonNull(Tipo.fromInt(c)).toString();
            cs.setString(3, tipo);
            c =  componente.categoria().getId();
            String categoria = Objects.requireNonNull(Categoria.fromInt(c)).toString();
            cs.setString(4, categoria);
            String descrizione = componente.descrizione();
            cs.setString(5,descrizione);
            float prezzo = componente.prezzo();
            cs.setFloat(6,prezzo);
            cs.executeQuery();
        } catch (SQLException e) {
            System.out.print("Error: " + e.getMessage());
        }


    }

    public static boolean mod_giacenza(String comp, int giacenza) {
        Connection conn;
        try {
            conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call modifica_quantita(?,?)}");
            cs.setString(1, comp);
            cs.setInt(2, giacenza);
            cs.execute();
            ResultSet rs = cs.getResultSet();
            rs.next();
            if(rs.getInt(1)>0)return true;
        }
        catch (SQLException e) {
            System.out.print("Non è stata aggiornata la giacenza "+ e+ "\n");
        }
        return false;
    }

    public static Componente visualizza(String comp) {
        Connection conn;
        try {
            conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call visualizza_componente(?)}");
            cs.setString(1,comp);
            cs.execute();

            ResultSet rs = cs.getResultSet();
            rs.next();
            return new Componente(rs.getString(1),rs.getInt(2), Tipo.valueOf(rs.getString(3).toUpperCase()),Categoria.valueOf(rs.getString(4).toUpperCase()),rs.getString(5), rs.getFloat(6));
        } catch (SQLException e) {
            System.out.print("Non è stato possibile visualizzare il componente "+e+"\n");
        }
        return null;
    }

    public static boolean aggiorna_prezzo(String comp, float prezzo) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call aggiorna_prezzo_componente(?,?)}");
            cs.setString(1,comp);
            cs.setFloat(2,prezzo);

            cs.executeQuery();
            ResultSet rs = cs.getResultSet();
            rs.next();
            if(rs.getInt(1)>0)return true;
        } catch (SQLException e) {
            System.out.print("Error: " + e.getMessage()+"\n");
        }
        return false;
    }

    public static boolean elimina(String comp) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call elimina_componente(?)}");
            cs.setString(1,comp);
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
