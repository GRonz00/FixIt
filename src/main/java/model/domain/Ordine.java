package model.domain;

import java.sql.Date;
import java.util.List;

public record Ordine(int codice, Date data, Float costo, List<ComponenteQuantita> componenti) {
}
