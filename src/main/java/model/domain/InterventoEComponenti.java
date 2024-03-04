package model.domain;

import java.util.List;

public record InterventoEComponenti(Intervento intervento, List<ComponenteTipo> componentes) {
}
