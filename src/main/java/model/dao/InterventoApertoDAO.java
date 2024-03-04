package model.dao;

import exception.DAOException;
import model.domain.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InterventoApertoDAO {
    public static int registra_intervento(InterventoAperto intervento) throws DAOException {
        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call registra_intervento(?,?,?,?,?,?)}");
            cs.setDate(1,intervento.data() );
            cs.setString(2, intervento.descrizione());
            cs.setString(3, intervento.nome());
            cs.setString(4, intervento.cognome());
            cs.setString(5, intervento.modello());
            cs.registerOutParameter(6, Types.INTEGER);

            cs.executeQuery();
            return cs.getInt(6);
        } catch (
                SQLException e) {
            System.out.print("Error: " + e.getMessage());
        }
        return -1;
    }

    public static float calcola_costo(int cod_intervento) {


        try{
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call calcola_costo(?,?)}");
            cs.setInt(1,cod_intervento);
            cs.registerOutParameter(2,Types.FLOAT);
            cs.execute();
            return cs.getFloat(2);

        } catch (SQLException e) {
            System.out.print("Errore: "+e);
        }
        return -1;

    }



    public static InterventoEComponenti visualizza(int cod_intervento) {
        try{
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call visualizza_intervento_aperto(?)}");
            cs.setInt(1,cod_intervento);
            if(cs.execute()){
                ResultSet rs = cs.getResultSet();
                List<ComponenteTipo> componentes = new ArrayList<>();
                if(rs.next()){
                    Intervento intervento =new Intervento(
                            rs.getInt(1),
                            rs.getDate(2),
                            rs.getString(3),
                            rs.getFloat(4),
                            rs.getString(5),
                            rs.getString(6),
                            rs.getString(7));
                    if(cs.getMoreResults()){
                        rs=cs.getResultSet();
                        while (rs.next()) {
                            componentes.add(new ComponenteTipo(rs.getString(1),Tipo.valueOf(rs.getString(2).toUpperCase())));
                        }
                        return new InterventoEComponenti(intervento,componentes);
                        }
                    }

                }

        }
        catch (SQLException e) {
            System.out.print("Errore: "+e);
        }
        return null;

    }

    public static List<Intervento> visualizza_tutti() {
        List<Intervento> interventoList = new ArrayList<>();
        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call visualizza_interventi_aperti()}");
            cs.execute();
            ResultSet rs = cs.getResultSet();
            while (rs.next()){
                interventoList.add(new Intervento(
                        rs.getInt(1),
                        rs.getDate(2),
                        rs.getString(3),
                        rs.getFloat(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7)));

            } return interventoList;
        }catch (SQLException e){
            System.out.print("Errore: "+e+"\n");
        }
        return  null;
    }

    public static boolean elimina(int codice) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call elimina_intervento_aperto(?)}");
            cs.setInt(1,codice);
            cs.execute();
            ResultSet rs = cs.getResultSet();
            rs.next();
            if(rs.getInt(1)>0)return true;}
        catch (SQLException e){
            System.out.print("Errore: "+e+"\n");
        }
        return false;
    }

    public static InterventiTotali visualizza_cliente(String nome,String cognome) {

        List<Intervento> interventoList = new ArrayList<>();
        List<InterventoConcluso> interventoConclusoList = new ArrayList<>();
        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call visualizza_interventi_cliente(?,?)}");
            cs.setString(1,nome);
            cs.setString(2,cognome);
            cs.execute();
            ResultSet rs = cs.getResultSet();
            while (rs.next()){
                interventoList.add(new Intervento(
                        rs.getInt(1),
                        rs.getDate(2),
                        rs.getString(3),
                        rs.getFloat(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7)));

            }
            cs.getMoreResults();
            rs=cs.getResultSet();
            while (rs.next()){
                interventoConclusoList.add(new InterventoConcluso(
                        rs.getInt(1),
                        rs.getDate(2),
                        rs.getString(3),
                        rs.getFloat(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getDate(8)));

            } return new InterventiTotali(interventoList,interventoConclusoList);
        }catch (SQLException e){
            System.out.print("Errore: "+e+"\n");
        }
        return  null;
    }
}
