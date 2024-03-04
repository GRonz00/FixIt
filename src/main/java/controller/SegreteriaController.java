package controller;

import exception.DAOException;
import model.dao.*;
import model.domain.*;
import view.SegreteriaView;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class SegreteriaController implements Controller {
    @Override
    public void start() throws IOException {
        try {
            ConnectionFactory.changeRole(Role.SEGRETERIA);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        while (true) {
            int choice;
                choice = SegreteriaView.show_menu();



            switch (choice) {
                case 1 -> aggiungiUtente();
                case 2 -> aggiungiFornitore();
                case 3 -> registraIntervento();
                case 4 -> calcolaCosto();
                case 5 -> registraConclusione();
                case 6 -> visualizza_intervento_concluso();
                case 7 -> elimina_intervento_aperto();
                case 8 -> visualizza_interventi_cliente();
                case 9 -> elimina_fornitore();
                case 10 ->visualizza_ordine();
                case 11 -> visualizza_intervento_aperto();
                case 12 -> System.exit(0);
                default -> throw new RuntimeException("Invalid choice");
            }
        }


    }

    private void visualizza_intervento_aperto() {
        int inter = SegreteriaView.scegli_intervento();
        InterventoEComponenti interventoEComponenti = InterventoApertoDAO.visualizza(inter);
        if(interventoEComponenti!=null)SegreteriaView.mostra_intervento_aperto(interventoEComponenti);
        else SegreteriaView.errore();
    }

    private void visualizza_ordine() {
        int codice = SegreteriaView.scegli_ordine();
        Ordine ordine = OrdineDAO.visualizza(codice);
        if(ordine!=null)SegreteriaView.mostraOrdine(ordine);
        else SegreteriaView.errore();


    }

    private void elimina_fornitore() {
        String fornitore = SegreteriaView.scegli_fornitore();
        if( FornitoreProcedureDAO.elimina(fornitore))SegreteriaView.fornitoreEliminato();
        else SegreteriaView.errore();
    }

    private void visualizza_interventi_cliente() {
        String nome = SegreteriaView.prendiNome();
        String cognome = SegreteriaView.prendiCognome();
        InterventiTotali i = InterventoApertoDAO.visualizza_cliente(nome,cognome);
        if(i!=null)SegreteriaView.mostaTuttiInterventi(i);
    }

    private void elimina_intervento_aperto() {
        int codice = SegreteriaView.scegli_intervento();
        if(InterventoApertoDAO.elimina(codice))SegreteriaView.interventoEliminato();
        else SegreteriaView.errore();
    }

    private void visualizza_intervento_concluso() {
        int intervento = SegreteriaView.scegli_intervento();
        InterventoConclusoEComponenti inter = InterventoConclusoDAO.visualizza(intervento);
        if(inter!=null)SegreteriaView.mostaInterventoConclusoEComponenti(inter);
        else SegreteriaView.errore();

    }

    public void registraConclusione() {
        int intervento = SegreteriaView.scegli_intervento();
        try{
            InterventoConclusoDAO.registra_conclusione(intervento);
            SegreteriaView.registra_conclusione();
        } catch (Exception e) {
            System.out.print("Errore");
        }

    }

    public void calcolaCosto() {
        int intervento = SegreteriaView.scegli_intervento();
        InterventoEComponenti comp;
        float costo;

        comp = InterventoApertoDAO.visualizza(intervento);
        if(comp!=null){
        SegreteriaView.mostra_intervento_aperto(comp);
        costo = InterventoApertoDAO.calcola_costo(intervento);
        if(costo!=-1)SegreteriaView.mostraCosto(costo);
        else SegreteriaView.errore();
        }else SegreteriaView.errore();

    }

    public void registraIntervento() {
        InterventoAperto intervento = SegreteriaView.nuovo_intervento();
        try {int disp = InterventoApertoDAO.registra_intervento(intervento);
        if (disp!=-1)SegreteriaView.numero_dispositivo(disp);}
        catch (DAOException e){
            throw new RuntimeException(e);
        }


    }

    public void aggiungiFornitore() throws IOException {
        Fornitore fornitore = SegreteriaView.nuovo_fornitore();
        boolean c;
        try{
            c = FornitoreProcedureDAO.aggiungi_fornitore(fornitore);
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }
        String forn = fornitore.p_iva();

        while (c){
            int choice;
            choice = SegreteriaView.aggiungi_contatto();
            switch (choice){
                case 1 -> {List<String> num = SegreteriaView.aggiungi_numero();
                    TelefonoDAO.aggiungi_telefono(forn,num);
                }

                case 2 -> {List<String> email = SegreteriaView.aggiungi_email();
                    EmailDAO.aggiungi_email(forn,email);}

                case 3 -> c = false;

            }

        }



    }

    public void aggiungiUtente() throws IOException {
        //per la password del utente anziche essere inserita dalla segreteria al primo accesso la inseriva l'utente
        Credentials newUtente = SegreteriaView.nuovo_utente();
        try {UtentiProcedureDAO.execute(newUtente);}
        catch (DAOException e){
            throw new RuntimeException(e);
        }
        System.out.print("Utente aggiunto correttamente\n");


    }
}
