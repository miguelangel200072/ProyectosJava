package Juego_Pareja;

public class Item {

    private String name;
    private final String type;
    private int amount;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public int getAmount() {
        return amount;
    }

    public Item(String name, String type, int amount){
        this.name = name;
        this.type = type;
        this.amount = amount;
    }

    public void use(){
        if (amount > 0){
            amount --;
        }else {
            System.out.println("You do not have " + name + "left.");
        }
    }

    @Override
    public String toString() {
        return this.name + ", of type " + this.type + ", remaining amount: " + this.amount ;
    }
}
