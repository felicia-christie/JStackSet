/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jstacksets;

import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.ReceiverAdapter;
import org.jgroups.View;
import org.jgroups.util.Util;

import java.io.*;
import java.util.List;
import java.util.LinkedList;
import java.util.Scanner;

public class JStackSets {
    
    private static boolean exit = false;
    private static Scanner in = new Scanner(System.in);
    
    public static void main(String[] args) throws Exception {
        int opt = 0;
	  while (!exit) {
		System.out.println("Select data structure:");
		System.out.println("1. Integer Stack");
		System.out.println("2. Integer Set");
		System.out.print("   > ");
		opt = in.nextInt();
		switch (opt){
		    case 1:
			  ImplStack();
			  break;
		    case 2:
			  ImplSet();
			  break;
		    default: 
			  System.out.println("Wrong option!");
				
		}
	  }
    }
    
    public static void ImplStack() throws Exception{
	  JStack<Integer> integerStack = new JStack<>();
	  while (!exit){
		System.out.println("Current stack state:");
		System.out.println(integerStack.toString());
		
		System.out.println("Input operation on Integer Stack:");
		System.out.println("1. Push");
		System.out.println("2. Pop");
		System.out.println("3. Check Top Value");
		System.out.println("4. Exit");
		    
		int opt = in.nextInt();
		switch (opt){
		    case 1:
			  System.out.print("Input push value: ");
			  int temp;
			  temp = in.nextInt();
			  integerStack.push(temp);
			  break;
		    case 2:
			  System.out.println("Pop'd value: " + integerStack.pop());
			  break;
		    case 3:
			  System.out.println("Top value: " + integerStack.top());
			  break;
		    case 4:
			  exit = true;
			  System.exit(0);
			  break;
			  
		    default:
			  System.out.println("Wrong option!");
		}
	  }
    }
    public static void ImplSet() throws Exception{
	  
	  JSet<Integer> integerSet = new JSet<>();
	  while (!exit){
		System.out.println("Current stack state:");
		System.out.println(integerSet.toString());
		
		System.out.println("Input operation on Integer Set:");
		System.out.println("1. Add");
		System.out.println("2. Remove");
		System.out.println("3. Check Availability");
		System.out.println("4. Exit");
		    
		int temp;
		int opt = in.nextInt();
		switch (opt){
		    case 1:
			  System.out.print("Input add value: ");
			  temp = in.nextInt();
			  integerSet.add(temp);
			  break;
		    case 2:
			  System.out.print("Input remove value: ");
			  temp = in.nextInt();
			  integerSet.remove(temp);
			  break;
		    case 3:
			  System.out.print("Input value for checking: ");
			  temp = in.nextInt();
			  System.out.println(integerSet.contains(temp));
			  break;
		    case 4:
			  exit = true;
			  System.exit(0);
			  break;
			  
		    default:
			  System.out.println("Wrong option!");
		}
	  }
    }
}
