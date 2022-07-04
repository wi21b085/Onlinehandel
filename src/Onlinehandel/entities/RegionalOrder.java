package Onlinehandel.entities;

import Onlinehandel.provided.Costumer;

public class RegionalOrder extends Order{

    private boolean express = false;

    public RegionalOrder(long id, Costumer c, Iterable<Item> items){
        super(id, c, items);
    }

    public RegionalOrder(long id, Costumer c, Iterable<Item> items, boolean express){
        super(id, c, items);
        this.express = express;
    }

    @Override
    public int getTotal(){
        float euro = 0;
        for(Item i: getItems())
            euro += i.totalValue();
        if(express && !getCostumer().isPremium())
            return (int) (euro*1.2);
        return (int) euro;
    }
}
