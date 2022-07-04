package Onlinehandel.entities;

import Onlinehandel.provided.Costumer;

public class InterationalOrder extends Order{
    private float custom;

    public InterationalOrder(long id,
                              Costumer c,
                              Iterable<Item> items){
        super(id, c, items);
    }

    public InterationalOrder(long id,
                             Costumer c,
                             Iterable<Item> items,
                             float custom){
        super(id, c, items);
        if(custom >= 1)
            this.custom = custom;
        else
            this.custom = 1;
    }

    @Override
    public int getTotal(){
        float euro = 0;
        if(getItems().size() == 0)
            return -1;
        for(Item i: getItems())
            euro += i.totalValue();
        return (int) (euro*custom);
    }
}
