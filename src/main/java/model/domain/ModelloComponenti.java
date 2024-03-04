package model.domain;

import java.util.List;

public class ModelloComponenti {

    private Modello modello;
    private List<Componente> componenti;

    public ModelloComponenti(Modello modello, List<Componente> componenti){
        this.modello = modello;
        this.componenti = componenti;
    }

    public List<Componente> getComponenti() {
        return componenti;
    }

    public void setComponenti(List<Componente> componenti) {
        this.componenti = componenti;
    }

    public Modello getModello() {
        return modello;
    }

    public void setModello(Modello modello) {
        this.modello = modello;
    }
}
