package model.domain;

import java.util.List;

public record FornitoreContatti(Fornitore fornitore, List<String> numeri, List<String> email) {
}
