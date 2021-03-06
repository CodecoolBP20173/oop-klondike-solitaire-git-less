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

    static Image backFace;
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

    public static void changeBackFace(Image back) {
        backFace = back;
    }

    public RankType getRank() {
        return rank;
    }

    public RankType getPreviousRank() {
        RankType actualRank = getRank();
        RankType previousRank = RankType.ACE;
        for (RankType rank : RankType.values()) {
            if (rank.equals(actualRank)) {
                return previousRank;
            }
            previousRank = rank;
        }
        return previousRank;
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
            if (card1.getSuit() == SuitType.SPADES && card2.getSuit() != SuitType.CLUBS) {
                return true;
            } else if (card1.getSuit() == SuitType.CLUBS && card2.getSuit() != SuitType.SPADES) {
                return true;
            } else if (card1.getSuit() == SuitType.DIAMONDS && card2.getSuit() != SuitType.HEARTS) {
                return true;
            } else if (card1.getSuit() == SuitType.HEARTS && card2.getSuit() != SuitType.DIAMONDS){
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
    public static void loadCardImagesCc() {
        cardBackImage = new Image("card_images/card_back_cc.png");
        for (SuitType suit : SuitType.values()) {
            for (RankType rank : RankType.values()) {
                String cardName = suit.suitName + rank.rankNumber;
                String cardId = "S" + suit.suitNumber + "R" + rank.rankNumber;
                String imageFileName = "card_images2/" + cardName + ".png";
                cardFaceImages.put(cardId, new Image(imageFileName));
            }
        }
    }
}
