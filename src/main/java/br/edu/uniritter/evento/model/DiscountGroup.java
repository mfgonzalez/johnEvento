package br.edu.uniritter.evento.model;

public enum DiscountGroup {
    GOLD(0.75F),
    SILVER(0.6F),
    ELDER(0.5F),
    STUDENT(0.5F),
    GENERAL(1F);

    Float discount;

    DiscountGroup(Float discount) {
        this.discount = discount;
    }
}
