package a1.jflores;

public class Clothing extends Item {
    private float defense;

    public Clothing() {
        super();
        this.defense = 0;
    }

    public Clothing(String name, double price, int qty, float defense) {
        super(name, price, qty);
        this.defense = defense;
    }

    public float getDefense() {
        return defense;
    }

    public void setDefense(float defense) {
        this.defense = defense;
    }

    @Override
    public String toString() {
        return String.format("%s %7s %20s |", super.toString(), "|", this.defense);
    }
}
