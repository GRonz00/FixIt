package view;

import model.domain.*;
import utils.Input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
public class SegreteriaView {
    public static int show_menu(){
        System.out.print("Cosa vuoi fare?\n");
        System.out.print("1) aggiungi utente\n");
        System.out.print("2) aggiungi fornitore\n");
        System.out.print("3) registra intervento\n");
        System.out.print("4) calcola costo intervento\n");
        System.out.print("5) registra conclusione intervento\n");
        System.out.print("6) visualizza intervento concluso\n");
        System.out.print("7) elimina intervento aperto\n");
        System.out.print("8) visualizza interventi cliente\n");
        System.out.print("9) elimina fornitore\n");
        System.out.print("10) visualizza ordine\n");
        System.out.print("11) Visualizza intervento aperto\n");
        System.out.print("12) uscire\n");


        int choice;
        choice= Input.prendi_int(1,12) ;

        return choice;

    }

    public static Credentials nuovo_utente() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("username: ");
        String username = reader.readLine();
        System.out.print("password: ");
        String password = reader.readLine();
        System.out.print("Che ruolo ha?\n");
        System.out.print("1) Tecnico 2)Segreteria 3)Magazino\n");
        int rol = utils.Input.prendi_int(1,3);


        return new Credentials(username, password, Role.fromInt(rol));
    }

    public static Fornitore nuovo_fornitore() {


        System.out.print("partita IVA: ");
        String iva = Input.checkString();
        System.out.print("Inserire indirizzo\ncittà: ");
        String citta = Input.checkString();
        System.out.print("via:");
        String via = Input.checkString();
        System.out.print("numero:");
        int num = Input.checkIfInt();
        System.out.print("Nome fornitore:");
        String nome = Input.checkString();
        return new Fornitore(iva,citta,via,num,nome);
    }

    public static int aggiungi_contatto() {
        System.out.print("Si vuole aggiungere?\n1) Numero di telefono 2)Email 3)Fine\n");
        return utils.Input.prendi_int(1,3);
    }

    public static List<String> aggiungi_numero() {
        List<String> numeri = new ArrayList<>();
        int c = 1;
        while (c==1){
        System.out.print("Inserire numero di telefono:\n");
        numeri.add(Input.checkIfTelefono());
        System.out.print("1)Aggiungere altro numero 2)Finito\n");
        c = utils.Input.prendi_int(1,2);}
        return numeri;
    }

    public static List<String> aggiungi_email() throws IOException {

        System.out.print("Inserire email:\n");
        List<String> email = new ArrayList<>();
        int c = 1;
        while (c==1){
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            email.add(reader.readLine());
            System.out.print("1)Aggiungere email 2)Finito\n");
            c = utils.Input.prendi_int(1,2);}
        return email;

    }

    public static InterventoAperto nuovo_intervento(){

        System.out.print("Inserire descrizione problema: ");
        String descrizione = Input.checkTesto();
        System.out.print("Modello: ");
        String modello = Input.checkString();
        System.out.print("Dati cliente\nNome: ");
        String nome = Input.checkString();
        System.out.print("Cognome: ");
        String cognome = Input.checkString();
        long millis=System.currentTimeMillis();
        java.sql.Date date=new java.sql.Date(millis);
        return new InterventoAperto(date,descrizione,nome,cognome,modello);

    }

    public static int scegli_intervento() {
        System.out.print("Inserire codice dispositivo:\n");
        return Input.checkIfInt();
    }

    public static void mostraCosto(float costo) {

        System.out.print("Costo componenti= ");
        System.out.printf("%.2f",costo/(1.1));
        System.out.print(" Totale= "+costo +"\n");
    }

    public static void mostaInterventoConcluso(InterventoConcluso intervento) {
        System.out.print("Intervento: "+intervento.dispositivo());
        System.out.print(" Data inizio: "+intervento.data()+" finito :"+intervento.conclusione()+"\n");
        System.out.print("Descrizione: "+intervento.descrizione()+"\n");
        System.out.print("Nome: "+intervento.nome());
        System.out.print(" Cognome: "+intervento.cognome()+"\n");
        System.out.print("Modello: "+intervento.modello());
        System.out.print(" Costo:"+intervento.costo()+"\n");

    }





    public static void interventoEliminato() {
        System.out.print("Intervento eliminato\n");
    }

    public static String prendiNome() {
        System.out.print("Nome: ");
        return Input.checkString();
    }

    public static String prendiCognome() {
        System.out.print("Cognome: ");
        return Input.checkString();
    }

    public static void mostaTuttiInterventi(InterventiTotali i) {
        for(Intervento intervento: i.interventiAperti())SegreteriaView.mostraIntervento(intervento);
        for (InterventoConcluso interventoConcluso: i.interventiConclusi())SegreteriaView.mostaInterventoConcluso(interventoConcluso);
    }

    private static void mostraIntervento(Intervento intervento) {
        System.out.print("Intervento: "+intervento.dispositivo());
        System.out.print(" Data: "+intervento.data()+"\n");
        System.out.print("Descrizione: "+intervento.descrizione()+"\n");
        System.out.print("Nome: "+intervento.nome());
        System.out.print(" Cognome: "+intervento.cognome()+"\n");
        System.out.print("Modello: "+intervento.modello());
        System.out.print(" Costo:"+intervento.costo()+"\n");
    }

    public static String scegli_fornitore() {
        System.out.print("Inserisci P.IVA: ");
        return Input.checkString();
    }

    public static void fornitoreEliminato() {
        System.out.print("Fornitore eliminato \n");
    }

    public static int scegli_ordine() {
        System.out.print("Codice ordine: ");
        return Input.checkIfInt();
    }

    public static void mostraOrdine(Ordine ordine) {
        System.out.print("Codice: "+ordine.codice());
        System.out.print(" Data: "+ordine.data());
        System.out.print(" Costo: "+ordine.costo());
        System.out.print("\nComponenti: ");
        for(ComponenteQuantita c: ordine.componenti())
            System.out.print(c.componente()+" "+c.quantita()+" ");
        System.out.print("\n");
    }

    public static void errore() {
        System.out.print("Errore\n");
    }

    public static void registra_conclusione() {
        System.out.print("Conclusione registrata\n");
    }

    public static void mostra_intervento_aperto(InterventoEComponenti intervento) {
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

    public static void numero_dispositivo(int disp) {
        System.out.print("Intervento registrato con numero " + disp +"\n");
    }

    public static void mostaInterventoConclusoEComponenti(InterventoConclusoEComponenti inter) {
     if(inter.interventoConcluso()!=null)SegreteriaView.mostaInterventoConcluso(inter.interventoConcluso());
     else SegreteriaView.errore();

     for (ComponenteTipo componente: inter.componentes())
            System.out.print("Componente: "+componente.codice()+" "+componente.tipo()+"\n");
    }
}
