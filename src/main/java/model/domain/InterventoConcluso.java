package model.domain;

import java.sql.Date;

public record InterventoConcluso(int dispositivo, Date data, String descrizione, float costo, String nome, String cognome, String modello,Date conclusione) {
}
