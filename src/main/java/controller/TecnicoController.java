package controller;

import model.dao.ConnectionFactory;
import model.dao.InterventoApertoDAO;
import model.dao.InterventoApertoHasComponenteDAO;
import model.dao.ModelloDAO;
import model.domain.Intervento;
import model.domain.InterventoEComponenti;
import model.domain.ModelloComponenti;
import model.domain.Role;
import view.TecnicoView;

import java.sql.SQLException;
import java.util.List;

public class TecnicoController implements Controller {

    @Override
    public void start() {
        try {
            ConnectionFactory.changeRole(Role.TECNICO);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        while (true){
            int choice = TecnicoView.show_menu();
            switch (choice){
                case 1->registra_componenti_intervento();
                case 2 -> visualizza_intervento();
                case 3 -> visualizza_modello();
                case 4 -> visualizza_interventi_aperti();
                case 5 -> elimina_componente_intervento();
                case 6 -> System.exit(0);
                default -> throw new RuntimeException("Invalid choice");
            }
        }
    }

    private void elimina_componente_intervento() {
        int intervento = TecnicoView.secgli_intervento();
        String componente = TecnicoView.scegli_componente();

        if(InterventoApertoHasComponenteDAO.elimina_componente(intervento,componente))
            TecnicoView.componente_eliminato();
        else TecnicoView.errore();

    }

    private void visualizza_interventi_aperti() {
        List<Intervento> interventoList = InterventoApertoDAO.visualizza_tutti();
        if(interventoList!=null)TecnicoView.mostra_interventi(interventoList);
    }

    private void visualizza_modello() {
        String mod = TecnicoView.scegli_modello();
        ModelloComponenti modelloComponenti = ModelloDAO.visualizza(mod);
        if(modelloComponenti!=null)TecnicoView.mostra_modello(modelloComponenti);
        else TecnicoView.errore();
    }

    private void visualizza_intervento() {
        int cod = TecnicoView.secgli_intervento();
        InterventoEComponenti intervento = InterventoApertoDAO.visualizza(cod);
        if(intervento!=null)TecnicoView.mostra_intervento(intervento);
        else TecnicoView.errore();
    }

    public void registra_componenti_intervento()  {

        int intervento = TecnicoView.secgli_intervento();
        List<String> componente = TecnicoView.scegli_componenti();
        InterventoApertoHasComponenteDAO.aggiungi_componente(intervento,componente);


    }
}
