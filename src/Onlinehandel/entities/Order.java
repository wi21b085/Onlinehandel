package Onlinehandel.entities;

import java.util.HashSet;
import java.util.Set;

import Onlinehandel.provided.*;

/**
 * An order within the Onlinehandel.<br>
 * <br>
 * 
 * An order holds information on the costumer placing the order, the items
 * ordered and the status of the order which captures collection and delivery
 * date and time.<br>
 * <br>
 * 
 * The usual life cycle is
 * <ul>
 * <li>create an order with id, costumer and at least one item</li>
 * <li>add items</li>
 * <li>collect</li>
 * <li>deliver</li>
 * </ul>
 *
 */
public abstract class Order implements Comparable<Order>{

	private DateTime collected;
	private Costumer costumer;
	private DateTime delivered;
	private java.util.Set<Item> goods;
	private long id;

	public Order(long id,
				  Costumer c,
				  Iterable<Item> items){
		if(id <= 0){
			throw new IllegalArgumentException("id must be greater than 0 ( given: " + id + ")");
		} else if(c == null){
			throw new IllegalArgumentException("c must be non-null");
		} else if(items == null  || !items.iterator().hasNext()){
			throw new IllegalArgumentException("at least one item must be passed");
		}
		this.id = id;
		this.costumer = c;
		this.goods = new HashSet<>();
		for(Item i: items){
			goods.add(i);
		}
	}

	public Order(Order orig){
		this.id = orig.id;
		this.costumer = new Costumer(orig.costumer);
		this.goods = new HashSet<>(orig.goods);
		this.collected = new DateTime(orig.collected);
		this.delivered = new DateTime(orig.delivered);
	}

	public abstract int getTotal();

	public boolean isCollected(){
		if(collected != null)
			return true;
		return false;
		// oder simpler: return collected != null;
	}

	public boolean isDelivered(){
		return delivered != null;
	}

	public final boolean addItems(Item item){
		if(!isCollected() && !isDelivered()){
			return goods.add(item);
		}
		return false;
	}

	public final boolean addItems(Iterable<Item> items){
		boolean added = false;
		if(!isCollected() && !isDelivered()){
			for(Item i: items) {
				added |= goods.add(i);
				//added = added || goods.add(i);
			}
		}
		return added;
	}

	public java.util.Set<Item> getItems(){
		return new HashSet<Item>(goods);
	}

	public final boolean collect(DateTime toc){
		if(!isCollected() && toc != null){
			collected = new DateTime(toc);
			return true;
		}
		return false;
	}

	public final boolean deliver(DateTime tod){
		if(isCollected() && !isDelivered() && tod != null){
			delivered = new DateTime(tod);
			return true;
		}
		return false;
	}

	public Costumer getCostumer(){
		return new Costumer(costumer);
	}

	@Override
	public int compareTo(Order arg0){
		return Long.compare(this.id, arg0.id);
	}

	/**
	 * creates a string representation of this delivery.<br>
	 * 
	 * @ProgrammingProblem.Hint provided
	 * 
	 */
	@Override
	public String toString() {
		return String.format("%d " + "[%scollected, %sdelivered] (EUR %.2f)\n" + "%s", //
				id, isCollected() ? "" : "not ", isDelivered() ? "" : "not ", getTotal() / 100.,
				goods == null ? "no stock" : goods);
	}

}
