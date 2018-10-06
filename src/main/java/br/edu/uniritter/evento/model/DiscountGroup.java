package br.edu.uniritter.evento.model;

public enum DiscountGroup {
    GOLD(0.75F),
    SILVER(0.6F),
    IDOSO(0.5F),
    ESTUDANTE(0.5F),
    NORMAL(1F);

    Float discount;

    DiscountGroup(Float discount) {
        this.discount = discount;
    }
}
