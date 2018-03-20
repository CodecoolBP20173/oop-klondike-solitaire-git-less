package com.codecool.klondike;

import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.util.*;

public class Card extends ImageView {

    private SuitType suit;
    private RankType rank;
    private boolean faceDown;

    private Image backFace;
    private Image frontFace;
    private Pile containingPile;
    private DropShadow dropShadow;

    static Image cardBackImage;
    private static final Map<String, Image> cardFaceImages = new HashMap<>();
    public static final int WIDTH = 150;
    public static final int HEIGHT = 215;

    public Card(SuitType suit, RankType rank, boolean faceDown) {
        this.suit = suit;
        this.rank = rank;
        this.faceDown = faceDown;
        this.dropShadow = new DropShadow(2, Color.gray(0, 0.75));
        backFace = cardBackImage;
        frontFace = cardFaceImages.get(getShortName());
        setImage(faceDown ? backFace : frontFace);
        setEffect(dropShadow);
    }

    public SuitType getSuit() {
        return suit;
    }

    public RankType getRank() {
        return rank;
    }

    public boolean isFaceDown() {
        return faceDown;
    }

    public String getShortName() {
        return "S" + suit.suitNumber + "R" + rank.rankNumber;
    }

    public DropShadow getDropShadow() {
        return dropShadow;
    }

    public Pile getContainingPile() {
        return containingPile;
    }

    public void setContainingPile(Pile containingPile) {
        this.containingPile = containingPile;
    }

    public void moveToPile(Pile destPile) {
        this.getContainingPile().getCards().remove(this);
        destPile.addCard(this);
    }

    public void flip() {
        faceDown = !faceDown;
        setImage(faceDown ? backFace : frontFace);
    }

    @Override
    public String toString() {
        return "The " + "Rank" + rank.rankNumber + " of " + "Suit" + suit.suitNumber;
    }

    public static boolean isOppositeColor(Card card1, Card card2) {
        if (!isSameSuit(card1, card2)) {
            if (card1.getSuit() == 1 && card2.getSuit() != 2) {
                return true;
            } else if (card1.getSuit() == 2 && card2.getSuit() != 1) {
                return true;
            } else if (card1.getSuit() == 3 && card2.getSuit() != 4) {
                return true;
            } else if (card1.getSuit() == 4 && card2.getSuit() != 3){
                return true;
            }
        }
        return false;
    }

    public static boolean isSameSuit(Card card1, Card card2) {
        return card1.getSuit() == card2.getSuit();
    }

    public static List<Card> createNewDeck() {
        List<Card> result = new ArrayList<>();
        for (SuitType suit : SuitType.values()) {
            for (RankType rank : RankType.values()) {
                result.add(new Card(suit, rank, true));
            }
        }
        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }
        shuffleDeck(result);
        return result;
    }

    public static List<Card> shuffleDeck(List deck) {
        Collections.shuffle(deck);
        return deck;
    }

    public static void loadCardImages() {
        cardBackImage = new Image("card_images/card_back.png");
        for (SuitType suit : SuitType.values()) {
            for (RankType rank : RankType.values()) {
                String cardName = suit.suitName + rank.rankNumber;
                String cardId = "S" + suit.suitNumber + "R" + rank.rankNumber;
                String imageFileName = "card_images/" + cardName + ".png";
                cardFaceImages.put(cardId, new Image(imageFileName));
            }
        }
    }
}
