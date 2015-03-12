package com.example.mobilewastewizard;

import java.util.*;
import java.lang.*;
import java.io.*;

import android.content.res.AssetManager;
// BURHANS CODE

public class WhichBin
{
	public static void main (String[] args) throws java.lang.Exception
	{
/*	//MOVE TEXT FILES TO ASSETS FOLDER
	AssetManager manager;
	    //long seconds = (System.currentTimeMillis() / 1000L) - 14400;
	   
	        String bin = "";
	        String[] total = new String[1500];
	        String[] bttswd = new String[1500];
	        String[] blue = new String[1500];
	        String[] ewaste = new String[1500];
	        String[] green = new String[1500];
	        String[] grey = new String[1500];
	        String[] hhw = new String[1500];
	        String[] ow = new String[1500];
	        String[] pw = new String[1500];
	        String[] sm = new String[1500];
	        String[] yw = new String[1500];
	        
	        //bttswd****************************************************************
	        try {        	
	        manager = getAssets();
	        InputStream is = manager.open("BTTSWD.txt");
	        InputStreamReader isr = new InputStreamReader(is); 
	        BufferedReader in = new BufferedReader(isr);
	        String line = in.readLine();
	            int cnt1 = 0;            
	            while(line!=""){
	               bttswd[cnt1++] = line; 
	               line = in.readLine();
	            }            
	        } catch (Exception e) {
	        System.out.println("error bruh at bttswd");  
	        bin = "NONE"; //replace with alternate suggestions
	        }
	        //blue******************************************************************
	        try {        	
	        manager = getAssets();
	        InputStream is = manager.open("Blue.txt");
	        InputStreamReader isr = new InputStreamReader(is); 
	        BufferedReader in = new BufferedReader(isr);
	        String line = in.readLine();
	            int cnt2 = 0;            
	            while(line!=""){
	               blue[cnt2++] = line; 
	               line = in.readLine();
	            }            
	        } catch (Exception e) {
	        System.out.println("error bruh at blue");  
	        bin = "NONE"; //replace with alternate suggestions
	        }
	        //ewaste******************************************************************
	        try {        	
	        manager = getAssets();
	        InputStream is = manager.open("EWaste.txt");
	        InputStreamReader isr = new InputStreamReader(is); 
	        BufferedReader in = new BufferedReader(isr);
	        String line = in.readLine();
	            int cnt3 = 0;            
	            while(line!=""){
	               ewaste[cnt3++] = line; 
	               line = in.readLine();
	            }            
	        } catch (Exception e) {
	        System.out.println("error bruh at ewaste");  
	        bin = "NONE"; //replace with alternate suggestions
	        }
	      //green******************************************************************
	        try {        	
	        manager = getAssets();
	        InputStream is = manager.open("Green.txt");
	        InputStreamReader isr = new InputStreamReader(is); 
	        BufferedReader in = new BufferedReader(isr);
	        String line = in.readLine();
	            int cnt4 = 0;            
	            while(line!=""){
	               green[cnt4++] = line; 
	               line = in.readLine();
	            }            
	        } catch (Exception e) {
	        System.out.println("error bruh at green");  
	        bin = "NONE"; //replace with alternate suggestions
	        }
	      //grey*******************************************************************
	        try {        	
	        manager = getAssets();
	        InputStream is = manager.open("Grey.txt");
	        InputStreamReader isr = new InputStreamReader(is); 
	        BufferedReader in = new BufferedReader(isr);
	        String line = in.readLine();
	            int cnt5 = 0;            
	            while(line!=""){
	               grey[cnt5++] = line; 
	               line = in.readLine();
	            }            
	        } catch (Exception e) {
	        System.out.println("error bruh at grey");  
	        bin = "NONE"; //replace with alternate suggestions
	        }
	      //hhw*******************************************************************
	        try {        	
	        manager = getAssets();
	        InputStream is = manager.open("HHW.txt");
	        InputStreamReader isr = new InputStreamReader(is); 
	        BufferedReader in = new BufferedReader(isr);
	        String line = in.readLine();
	            int cnt6 = 0;            
	            while(line!=""){
	               hhw[cnt6++] = line; 
	               line = in.readLine();
	            }            
	        } catch (Exception e) {
	        System.out.println("error bruh at hhw");  
	        bin = "NONE"; //replace with alternate suggestions
	        }
	      //ow*******************************************************************
	        try {        	
	        manager = getAssets();
	        InputStream is = manager.open("OW.txt");
	        InputStreamReader isr = new InputStreamReader(is); 
	        BufferedReader in = new BufferedReader(isr);
	        String line = in.readLine();
	            int cnt7 = 0;            
	            while(line!=""){
	               ow[cnt7++] = line; 
	               line = in.readLine();
	            }            
	        } catch (Exception e) {
	        System.out.println("error bruh at ow");  
	        bin = "NONE"; //replace with alternate suggestions
	        }
	      //pw*******************************************************************
	        try {        	
	        manager = getAssets();
	        InputStream is = manager.open("PW.txt");
	        InputStreamReader isr = new InputStreamReader(is); 
	        BufferedReader in = new BufferedReader(isr);
	        String line = in.readLine();
	            int cnt8 = 0;            
	            while(line!=""){
	               pw[cnt8++] = line; 
	               line = in.readLine();
	            }            
	        } catch (Exception e) {
	        System.out.println("error bruh at pw");  
	        bin = "NONE"; //replace with alternate suggestions
	        }
	      //sm*******************************************************************
	        try {        	
	        manager = getAssets();
	        InputStream is = manager.open("SM.txt");
	        InputStreamReader isr = new InputStreamReader(is); 
	        BufferedReader in = new BufferedReader(isr);
	        String line = in.readLine();
	            int cnt9 = 0;            
	            while(line!=""){
	               sm[cnt9++] = line; 
	               line = in.readLine();
	            }            
	        } catch (Exception e) {
	        System.out.println("error bruh at sm");  
	        bin = "NONE"; //replace with alternate suggestions
	        }
	      //yw*******************************************************************
	        try {        	
	        manager = getAssets();
	        InputStream is = manager.open("YW.txt");
	        InputStreamReader isr = new InputStreamReader(is); 
	        BufferedReader in = new BufferedReader(isr);
	        String line = in.readLine();
	            int cnt10 = 0;            
	            while(line!=""){
	               yw[cnt10++] = line; 
	               line = in.readLine();
	            }            
	        } catch (Exception e) {
	        System.out.println("error bruh at yw");  
	        bin = "NONE"; //replace with alternate suggestions
	        }
	      //total*******************************************************************
	        try {        	
	        manager = getAssets();
	        InputStream is = manager.open("YW.txt");
	        InputStreamReader isr = new InputStreamReader(is); 
	        BufferedReader in = new BufferedReader(isr);
	        String line = in.readLine();
	            int cnt10 = 0;            
	            while(line!=""){
	               yw[cnt10++] = line; 
	               line = in.readLine();
	            }            
	        } catch (Exception e) {
	        System.out.println("error bruh at yw");  
	        bin = "NONE"; //replace with alternate suggestions
	        }
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++        
	        String whichbin = "NONE";
	        for(int i = 0; i < bttswd.length; i++){
		        if(bttswd[i] == input){
		        whichbin = "Bring to Transfer Station/Waste Depot";
		        }
	        }
	        for(int i = 0; i < blue.length; i++){
		        if(blue[i] == input){
		        whichbin = "Blue Bin";
		        }
	        }
	        for(int i = 0; i < ewaste.length; i++){
		        if(ewaste[i] == input){
		        whichbin = "E-Waste";
		        }
	        }
	        for(int i = 0; i < green.length; i++){
		        if(green[i] == input){
		        whichbin = "Green Bin";
		        }
	        }
	        for(int i = 0; i < grey.length; i++){
		        if(grey[i] == input){
		        whichbin = "Grey Bin - Garbage";
		        }
	        }
	        for(int i = 0; i < hhw.length; i++){
		        if(hhw[i] == input){
		        whichbin = "Household Hazardous Waste";
		        }
	        }
	        for(int i = 0; i < ow.length; i++){
		        if(ow[i] == input){
		        whichbin = "Oversized Waste";
		        }
	        }
	        for(int i = 0; i < pw.length; i++){
		        if(pw[i] == input){
		        whichbin = "Prohibited Waste";
		        }
	        }
	        for(int i = 0; i < sm.length; i++){
		        if(sm[i] == input){
		        whichbin = "Scrap Metal";
		        }
	        }
	        for(int i = 0; i < yw.length; i++){
		        if(yw[i] == input){
		        whichbin = "Yard Waste";
		        }
	        }
	        
	        if(whichbin != "NONE") System.out.println(whichbin);
	        
	        
	        */
	        
	}
}
