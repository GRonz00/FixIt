package model.domain;

public record Componente(String codice, int giacenza, Tipo tipo, Categoria categoria, String descrizione,
                         float prezzo) {
}
