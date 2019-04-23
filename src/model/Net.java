package model;

import java.io.Serializable;
import java.util.HashSet;
/*
"stores" the fish that ScubaSam collects
the Net has a given size that can only collect a certain amount of fish
fish are stored in a Collection
 */
public class Net implements Serializable {

	//Attributes
	private int weight = 0;
	private int maxWeight;
	private HashSet<Fish> fishlist;
	/////////////////////////////////////////////////////////////////////////
	//constructor

	public Net(int maxWeight){
		this.maxWeight = maxWeight;
		this.fishlist = new HashSet<>();
	}
	/////////////////////////////////////////////////////////////////////////
	//Getters

	/**
	 * Getter for fishlist
	 * @return
	 */
	public HashSet<Fish> getFishlist() {
		return fishlist;
	}

	/**
	 * Setter for maxWeight
	 * @param newMaxWeight
	 */
	public void setMaxWeight(int newMaxWeight){
		this.maxWeight = newMaxWeight;
	}

	/**
	 * Getter for maxWeight
	 * @return
	 */
	public int getMaxWeight(){
		return maxWeight;
	}

	/**
	 * Getter for weight
	 * @return
	 */
	public int getWeight(){
		return weight;
	}

	/////////////////////////////////////////////////////////////////////////
	//Methods
	//adds fish to fishlist

	/**
	 * Add fish to net if caught
	 * @param f
	 * @return
	 */
	public boolean add(Fish f){
		if(!isFull()){
			fishlist.add(f);
			weight += f.getWeight();
			return true;
		}
		return false;
	}

	/**
	 * Check if the net is full
	 * @return
	 */
	public boolean isFull(){
		return weight >= maxWeight;
	}
	// empties net

	/**
	 * Empty the net
	 */
	public void empty(){
		weight = 0;
		fishlist.clear();
	}
}