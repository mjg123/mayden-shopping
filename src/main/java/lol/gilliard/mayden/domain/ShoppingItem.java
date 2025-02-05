package lol.gilliard.mayden.domain;

public class ShoppingItem {

    private String name;
    private boolean isStruckOut;

    public ShoppingItem(String name){
        this.name = name;
        this.isStruckOut = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStruckOut() {
        return isStruckOut;
    }

    public void setStruckOut(boolean struckOut) {
        isStruckOut = struckOut;
    }
}
