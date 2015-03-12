package com.example.mobilewastewizard;

public class Pair<F extends Comparable<F>,S extends Comparable<S>>{
	F first;
	S second;
	public Pair(F first, S second){
		this.first = first;
		this.second = second;
	}
}
