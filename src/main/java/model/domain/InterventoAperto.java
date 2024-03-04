package model.domain;

import java.sql.Date;

public record InterventoAperto(Date data, String descrizione, String nome, String cognome, String modello) {
}

