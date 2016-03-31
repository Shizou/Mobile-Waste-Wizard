/** Name: Burhan, Justin, William
 *  Date: 24/03/15
 *  Purpose: Creates a generic pair
 * */
package com.example.mobilewastewizard;

public class Pair<F extends Comparable<F>,S extends Comparable<S>> implements
Comparable<Pair<F,S>>{
	public F first;
	public S second;

	/**Creates a pair */
	public Pair(F first, S second){
		this.first = first;
		this.second = second;
	}

	@Override
	/**Compares this item to another pair.
	 * Compares first item before second item.
	 * @param arg0 pair to be compared too*/
	public int compareTo(Pair<F,S> arg0) {
		int fCompare = this.first.compareTo(arg0.first);
		int sCompare = this.second.compareTo(arg0.second);
		return fCompare == 0 ? sCompare:fCompare;
	}
	@SuppressWarnings("unchecked")
	/**Compares this item to another item*/
	public int equalsTo(Object o){
		if(o instanceof Pair)
			return compareTo((Pair<F,S>)o);
		else
			return -1;
	}
}
