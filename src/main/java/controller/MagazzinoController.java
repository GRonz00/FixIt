package controller;

import exception.DAOException;
import model.dao.*;
import model.domain.*;
import view.MagazzinoView;

import java.sql.SQLException;
import java.util.List;

public class MagazzinoController implements Controller{
    @Override
    public void start() {
        try {
            ConnectionFactory.changeRole(Role.MAGAZINO);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        while (true) {
            int choice;
            choice = MagazzinoView.show_menu();
            switch (choice) {
                case 1 -> aggiungiModello();
                case 2 -> aggiungiComponente();
                case 3 -> modificaGiacenza();
                case 4 -> visualizzaComponente();
                case 5 -> visualizzaModello();
                case 6 -> aggiungiOrdine();
                case 7 -> aggiungiComponenteFornitore();
                case 8 -> aggiornaPrezzoComponenteFornitore();
                case 9 -> aggiornaPrezzoComponente();
                case 10 -> visualizzaOrdine();
                case 11 -> eliminaComponente();
                case 12 -> eliminaModello();
                case 13 -> eliminaOrdine();
                case 14 -> visualizzaFornitore();

                case 15 -> System.exit(0);
                default -> throw new RuntimeException("Invalid choice");
            }
        }

    }

    private void visualizzaFornitore() {
        String fornitore = MagazzinoView.scegli_fornitore();
        FornitoreContatti fornitoreContatti = FornitoreProcedureDAO.visualizza(fornitore);
        if(fornitoreContatti!=null) MagazzinoView.mostra_fornitore(fornitoreContatti);
        else MagazzinoView.errore();
    }

    private void eliminaOrdine() {
        int codice = MagazzinoView.scegli_ordine();
        if(OrdineDAO.elimina(codice)) MagazzinoView.eliminato();
        else MagazzinoView.errore();

    }

    private void eliminaModello() {
        String mod = MagazzinoView.scegliModello();
        if(ModelloDAO.elimina(mod)) MagazzinoView.eliminato();
        else MagazzinoView.errore();
    }

    private void eliminaComponente() {
        String comp = MagazzinoView.scegli_componente();
        if(ComponenteDAO.elimina(comp)) MagazzinoView.eliminato();
        else MagazzinoView.errore();
    }

    private void visualizzaOrdine() {
        int codice = MagazzinoView.scegli_ordine();
        Ordine ordine = OrdineDAO.visualizza(codice);
        if(ordine!=null) MagazzinoView.mostraOrdine(ordine);
        else MagazzinoView.errore();
    }

    private void aggiornaPrezzoComponente() {
        String comp = MagazzinoView.scegli_componente();
        float prezzo = MagazzinoView.prendiPrezzo();
        if(ComponenteDAO.aggiorna_prezzo(comp,prezzo)) MagazzinoView.prezzo_aggiornato();
        else MagazzinoView.errore();
    }

    private void aggiornaPrezzoComponenteFornitore() {

        String fornitore = MagazzinoView.scegli_fornitore();
        String componente = MagazzinoView.scegli_componente();
        float prezzo = MagazzinoView.prendiPrezzo();
        if(FornitoreProcedureDAO.aggiorna_prezzo(fornitore,componente,prezzo)) MagazzinoView.prezzo_aggiornato();
        else MagazzinoView.errore();
    }

    private void aggiungiComponenteFornitore() {
        String fornitore = MagazzinoView.scegli_fornitore();
        String componente = MagazzinoView.scegli_componente();
        float prezzo = MagazzinoView.prendiPrezzo();
        if(FornitoreProcedureDAO.aggiungi_componente(fornitore,componente,prezzo)) MagazzinoView.c_fornitore();
        else MagazzinoView.errore();


    }

    private void aggiungiOrdine() {
        String fornitore = MagazzinoView.scegli_fornitore();
        long millis=System.currentTimeMillis();
        java.sql.Date date=new java.sql.Date(millis);
        int cod_ordine;
        float costo;
        cod_ordine = OrdineDAO.aggiungi_ordine(fornitore,date);
        if(cod_ordine!=-1){
            MagazzinoView.codice_ordine(cod_ordine);
            List<ComponenteQuantita> componenti = MagazzinoView.lista_comp_quantita();
            OrdineComponentiDAO.aggiungi_componente(cod_ordine,fornitore,componenti);

        }
        costo = OrdineDAO.inserisci_costo(cod_ordine);
        if(costo!=-1) MagazzinoView.costo_ordine(costo);
        }



    public void visualizzaModello() {
        String mod = MagazzinoView.scegliModello();
        ModelloComponenti modello = ModelloDAO.visualizza(mod);
        if(modello!=null) MagazzinoView.mostraModello(modello);
        else MagazzinoView.errore();
    }

    public void visualizzaComponente() {
        String comp = MagazzinoView.scegli_componente();
        Componente componente = ComponenteDAO.visualizza(comp);
        if(componente!=null) MagazzinoView.mostraComponente(componente);
    }

    public void modificaGiacenza() {
        String comp = MagazzinoView.scegli_componente();
        int giacenza = MagazzinoView.quantita();
        if(ComponenteDAO.mod_giacenza(comp,giacenza)) MagazzinoView.giacenza_aggiornata();
        else MagazzinoView.errore();
    }

    private void aggiungiComponente() {
        Componente componente = MagazzinoView.aggungi_componente();
        ComponenteDAO.aggiungi_componente(componente);
    }

    private void aggiungiModello() {
        Modello modello = MagazzinoView.aggiungi_modello();
        boolean x = false;
        try {
            x = ModelloDAO.aggiungi_modello(modello);
        }
        catch (DAOException e){
            System.out.print("Errore modello non aggiunto "+ e);
        }

            if (x){
                List<String> componenti = MagazzinoView.aggiungi_compAmodello();

                try {
                      ModelloHasComponenteDAO.aggiungi_componente(modello.nome(), componenti);
                } catch (DAOException e) {
                    System.out.print("Componenti non aggiunte");
                }

            }



    }
}
