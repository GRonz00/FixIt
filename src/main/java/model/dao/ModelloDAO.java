package model.dao;

import exception.DAOException;
import model.domain.*;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ModelloDAO {
    public static Boolean aggiungi_modello(Modello modello) throws DAOException {
        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call aggiungi_modello(?,?,?)}");
            cs.setString(1, modello.nome());
            cs.setString(2, modello.marca());
            int c =  modello.categoria().getId();
            String categoria = Categoria.fromInt(c).toString();
            cs.setString(3, categoria);

            cs.executeQuery();
        } catch (SQLException e) {
            System.out.print("Error: " + e.getMessage());
            return false;
        }
        return true;
    }

    public static ModelloComponenti visualizza(String mod) {
        Connection conn;
        List<Componente> componentes = new ArrayList<>();
        Modello modello;
        try {
            conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call visualizza_modello(?)}");
            cs.setString(1,mod);
            cs.execute();

            ResultSet rs = cs.getResultSet();
            if(rs.next()) modello = new Modello(rs.getString(1),rs.getString(2),Categoria.valueOf(rs.getString(3).toUpperCase()));
            else return null;
            if(cs.getMoreResults()){
                rs= cs.getResultSet();
                while (rs.next()){
                        componentes.add(new Componente(rs.getString(1),rs.getInt(2), Tipo.valueOf(rs.getString(3).toUpperCase()),Categoria.valueOf(rs.getString(4).toUpperCase()),rs.getString(5), rs.getFloat(6)));
                }
            }
            return new ModelloComponenti(modello,componentes);
        } catch (SQLException e) {
            System.out.print("Errore:" + e);
        }
        return null;
    }

    public static boolean elimina(String mod) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call elimina_modello(?)}");
            cs.setString(1,mod);
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
