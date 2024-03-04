package view;

import model.domain.*;
import utils.Input;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MagazzinoView {
    public static int show_menu(){
        System.out.print("Cosa vuoi fare?\n");
        System.out.print("1) aggiungi modello\n");
        System.out.print("2) aggiungi componente\n");
        System.out.print("3) modifica giacenza componente\n");
        System.out.print("4) visualizza componente\n");
        System.out.print("5) visualizza modello\n");
        System.out.print("6) inserisci ordine\n");
        System.out.print("7) aggiungi componente venduto da un fornitore\n");
        System.out.print("8) aggiorna prezzo d'acquisto componente venduto da fornitore\n");
        System.out.print("9) aggiorna prezzo vendita componente\n");
        System.out.print("10) visualizza ordine\n");
        System.out.print("11) elimina componente\n");
        System.out.print("12) elimina modello\n");
        System.out.print("13) elimina ordine\n");
        System.out.print("14) visualizza fornitore\n");
        System.out.print("15) uscire\n");
        return Input.prendi_int(1,15) ;


    }

    public static Modello aggiungi_modello() {

        System.out.print("Nome modello:");
        String nome = Input.checkString();
        System.out.print("Marca:");
        String marca = Input.checkString();
        System.out.print("Selezionare categoria\n 1)Telefono 2)portatili\n");
        int cat = utils.Input.prendi_int(1,2);
         Categoria categoria = Categoria.fromInt(cat);
         return new Modello(nome,marca,categoria);
    }

    public static List<String> aggiungi_compAmodello() {
        List<String> componenti = new ArrayList<>();
        int c = 1;
        while (c==1){
        componenti.add(MagazzinoView.scegli_componente());
        System.out.print("1)Aggiungere componente a modello 2)Finito\n");
        c = utils.Input.prendi_int(1,2);}
        return componenti;
    }

    public static String scegli_componente(){
        System.out.print("Codice componente:");
        return Input.checkString();

    }


    public static Componente aggungi_componente() {

        System.out.print("Codice:");
        String codice = Input.checkString();
        System.out.print("Giacenza:");
        int giacenza = utils.Input.checkIfInt();
        System.out.print("Selezionare tipo\n 1)Schermo 2)Connettore USB 3)Batteria 4)Scheda madre\n");
        System.out.print("5)Tastiera 6)Trackpad 7)Ventola 8)Modulo RAM\n");
        int t = utils.Input.prendi_int(1,8);
        Tipo tipo = Tipo.fromInt(t);
        System.out.print("Selezionare categoria\n 1)Telefono 2)portatili\n");
        int cat = utils.Input.prendi_int(1,2);
        Categoria categoria = Categoria.fromInt(cat);
        System.out.print("Breve descrizione:");
        String descrizione = Input.checkTesto();
        System.out.print("Prezzo:");
        float prezzo = utils.Input.checkIfFloat();

        return new Componente(codice,giacenza,tipo,categoria,descrizione,prezzo);
    }

    public static int quantita() {
        System.out.print("Di qunte unita? (mettere - davanti per diminuire o niente per aumentare\n)");
        int num;
        while (true) {
            Scanner input = new Scanner(System.in);
            try {
                num = input.nextInt();
                return num;
            } catch (InputMismatchException ignored) {}
            System.out.print("Bisogna inserire un intero\n");
        }
    }

    public static void mostraComponente(Componente componente) {
        System.out.print("Codice componente: " + componente.codice());
        System.out.print("\nGiacenza: " + componente.giacenza());
        System.out.print("\nTipo: "+componente.tipo().toString());
        System.out.print("\nCategoria: "+componente.categoria().toString());
        System.out.print("\nDescrizione: "+componente.descrizione()+"\n");
    }

    public static String scegliModello() {

        System.out.print("Nome modello: ");
        return Input.checkString();
    }

    public static void mostraModello(ModelloComponenti modelloComponenti) {
        Modello modello = modelloComponenti.getModello();
        System.out.print("Nome modello: " + modello.nome());
        System.out.print("\nMarca: "+ modello.marca());
        System.out.print("\nCategoria: "+ modello.categoria().toString()+"\n");
        List<Componente> componentes = modelloComponenti.getComponenti();
        for(Componente componenteList : componentes){
            MagazzinoView.mostraComponente(componenteList);
        }
    }

    public static String scegli_fornitore()  {
        System.out.print("P.IVA fornitore:");
        return Input.checkString();

    }

    public static int altri_componenti() {
        System.out.print("1)Aggiungi un altro componente\n2)Fine\n");
        return utils.Input.prendi_int(1,2);
    }

    public static List<ComponenteQuantita> lista_comp_quantita() {
        List<ComponenteQuantita> l = new ArrayList<>();
        int c=1;
        String comp;
        int q;
        while (c==1){
            System.out.print("Inserisci codice componente: ");
            comp = Input.checkString();
            System.out.print("Inserisci quantita: ");
            q = Input.checkIfInt();
            l.add(new ComponenteQuantita(comp,q));
            c = altri_componenti();
        }
        return l;
    }

    public static void costo_ordine(float costo) {
        System.out.print("Il costo totale del ordine è "+costo+"\n");
    }

    public static float prendiPrezzo() {
        System.out.print("Inserisci prezzo: ");
        return Input.checkIfFloat();
    }

    public static void c_fornitore() {
        System.out.print("Componente aggiunto\n");
    }

    public static void prezzo_aggiornato() {
        System.out.print("Prezzo aggiornato\n");
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

    public static void eliminato() {
        System.out.print("Eliminato con successo\n");
    }

    public static void errore() {
        System.out.print("Errore\n");
    }

    public static void giacenza_aggiornata() {
        System.out.print("Giacenza aggiornata\n");
    }

    public static void codice_ordine(int cod_ordine) {
        System.out.print("Codice ordine: "+cod_ordine+"\n");
    }



    public static void mostra_fornitore(FornitoreContatti fornitoreContatti) {
        System.out.print("Nome: "+ fornitoreContatti.fornitore().nome());
        System.out.print(" P.IVA: "+ fornitoreContatti.fornitore().p_iva());
        System.out.print("\nIndirizzo: "+ fornitoreContatti.fornitore().via()+" "+fornitoreContatti.fornitore().città()+" "+fornitoreContatti.fornitore().num());
        System.out.print("\nEmail: ");
        for(String email: fornitoreContatti.email())System.out.print(email+" ");
        System.out.print("\nNumeri: ");
        for (String numero: fornitoreContatti.numeri())System.out.print(numero+" ");
        System.out.print("\n");
    }
}
