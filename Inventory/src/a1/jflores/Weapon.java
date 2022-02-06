package a1.jflores;

public class Weapon extends Item{
    private float attack;

    public Weapon() {
        super();
        this.attack = 0;
    }

    public Weapon(String name, double price, int qty, float attack) {
        super(name, price, qty);
        this.attack = attack;
    }

    public float getAttack() {
        return attack;
    }

    public void setAttack(float attack) {
        this.attack = attack;
    }

    @Override
    public String toString() {
        return String.format("%s %7s %20s |", super.toString(), "|", this.attack);
    }
}
