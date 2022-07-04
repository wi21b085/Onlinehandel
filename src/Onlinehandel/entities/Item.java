package Onlinehandel.entities;

public class Item {

    private int amount = 1;
    private String description;
    private int value;

    public Item(String description, int amount, int value){
        if(amount <= 0)
            throw new IllegalArgumentException("amount must be greater than 0 ( given: " + amount + ")");
        else if(value <= 0)
            throw new IllegalArgumentException("value must be greater than 0 ( given: " + value + ")");
        this.amount = amount;
        this.description = description;
        this.value = value;
    }

    public int totalValue(){
        return value*amount;
    }

    @Override
    public String toString(){
        return String.format("Item [description: " + description +
                ",amount: " + amount +
                ",value: " + value + "]");
    }
}
