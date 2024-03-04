package view;
import model.domain.*;
import utils.Input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class TecnicoView {

    public static int show_menu(){
        System.out.print("Cosa vuoi fare?\n");
        System.out.print("1) Registra componenti per intervento\n");
        System.out.print("2) Visualizza intervento\n");
        System.out.print("3) Mosta componenti modello\n");
        System.out.print("4) Visualizza interventi aperti\n");
        System.out.print("5) Elimina componente da intervento\n");
        System.out.print("6) Fine\n");
        return Input.prendi_int(1,6);
    }
    public static int secgli_intervento(){
        System.out.print("Selezionare a quale intervento è riferito: ");
        return utils.Input.checkIfInt();
    }

    public static String scegli_componente()  {

        System.out.print("Inserire codice componente:\n");
        return Input.checkString();
    }

    public static List<String> scegli_componenti(){
        System.out.print("Inserire codice componente:");
        return Input.listaStringhe("componente");
    }

    public static void mostra_intervento(InterventoEComponenti intervento) {
        System.out.print("Intervento: "+intervento.intervento().dispositivo()+"\n");
        System.out.print("Data: "+intervento.intervento().data()+"\n");
        System.out.print("Descrizione: "+intervento.intervento().descrizione()+"\n");
        System.out.print("Costo:"+intervento.intervento().costo()+"\n");
        System.out.print("Nome: "+intervento.intervento().nome()+"\n");
        System.out.print("Cognome: "+intervento.intervento().cognome()+"\n");
        System.out.print("Modello: "+intervento.intervento().modello()+"\n");
        for (ComponenteTipo componente: intervento.componentes())
        System.out.print("Componente: "+componente.codice()+" "+componente.tipo()+"\n");
    }

    public static String scegli_modello() {
        System.out.print("Nome modello: ");
        return Input.checkString();
    }

    public static void mostra_modello(ModelloComponenti modelloComponenti) {
        Modello modello = modelloComponenti.getModello();
        System.out.print("Nome modello: " + modello.nome());
        System.out.print("\nMarca: "+ modello.marca());
        System.out.print("\nCategoria: "+ modello.categoria().toString()+"\n");
        List<Componente> componentes = modelloComponenti.getComponenti();
        for(Componente componente : componentes){
            TecnicoView.mostraComponente(componente);
        }
    }

    public static void mostraComponente(Componente componente) {
        System.out.print("Codice componente: " + componente.codice());
        System.out.print("\nGiacenza: " + componente.giacenza());
        System.out.print("\nTipo: "+componente.tipo().toString());
        System.out.print("\nCategoria: "+componente.categoria().toString());
        System.out.print("\nDescrizione: "+componente.descrizione()+"\n");
    }

    public static void mostra_interventi(List<Intervento> interventoList) {
        for(Intervento intervento: interventoList){
            System.out.print("Intervento: "+intervento.dispositivo());
            System.out.print(" Data: "+intervento.data()+"\n");
            System.out.print("Descrizione: "+intervento.descrizione()+"\n");
            System.out.print("Nome: "+intervento.nome());
            System.out.print(" Cognome: "+intervento.cognome()+"\n");
            System.out.print("Modello: "+intervento.modello());
            System.out.print(" Costo:"+intervento.costo()+"\n");
        }
    }

    public static void componente_eliminato() {
        System.out.print("Componente eliminato\n");
    }

    public static void errore() {
        System.out.print("Errore\n");
    }
}
