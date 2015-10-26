/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jstacksets;

import java.util.Stack;

/**
 *
 * @author SPM
 */
public class JStack<T> {
    
    Stack<T> innerStack;
    
    public JStack(){
	  innerStack = new Stack<>();
    }
    public void push (T obj){
	  innerStack.push(obj);
    }
    public T pop (){
	  return innerStack.pop();
    }
    public T top(){
	  return innerStack.firstElement();
    }
    public int size(){
	  return innerStack.size();
    }
    
}
