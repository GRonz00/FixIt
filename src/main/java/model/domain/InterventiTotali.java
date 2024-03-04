package model.domain;

import java.util.List;

public record InterventiTotali(List<Intervento> interventiAperti, List<InterventoConcluso> interventiConclusi) {
}
