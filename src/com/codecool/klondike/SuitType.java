package com.codecool.klondike;

public enum SuitType {
    SPADES (3, "spades"),
    CLUBS (4, "clubs"),
    HEARTS (1, "hearts"),
    DIAMONDS (2, "diamonds");

    public final int suitNumber;
    public final String suitName;
    SuitType(int suitNumber, String suitName) {
        this.suitNumber = suitNumber;
        this.suitName = suitName;
    }
}