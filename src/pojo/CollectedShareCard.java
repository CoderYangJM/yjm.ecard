package pojo;

public class CollectedShareCard {
    int cardNum;
    String username;
    int collectionNum;

    public CollectedShareCard(int cardNum, String username, int collectionNum) {
        this.cardNum = cardNum;
        this.username = username;
        this.collectionNum = collectionNum;
    }

    public int getCardNum() {
        return cardNum;
    }

    public void setCardNum(int cardNum) {
        this.cardNum = cardNum;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getCollectionNum() {
        return collectionNum;
    }

    public void setCollectionNum(int collectionNum) {
        this.collectionNum = collectionNum;
    }
}
