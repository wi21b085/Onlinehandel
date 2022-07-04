package Onlinehandel.util;

import Onlinehandel.entities.*;
import Onlinehandel.provided.Matcher;

public class OnRouteMatcher implements Matcher<Order> {

    public OnRouteMatcher(){}

    public boolean matches(Order t){
        if(t.isCollected() && !t.isDelivered())
            return true;
        return false;
        //simpler: return t.isCollected() && !t.isDelivered();
    }
}
