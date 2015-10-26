/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jstacksets;

import java.util.ArrayList;

/**
 *
 * @author Waxrain
 */
public class JSet<T> {
    ArrayList<T> innerSet;
    public JSet(){
	  innerSet = new ArrayList<>();
    }
    public boolean add(T obj) {
	  if (!innerSet.contains(obj)){
		innerSet.add(obj);
		return true;
	  } else {
		return false;
	  }
    }
    
    public boolean remove(T obj) {
	  if (!innerSet.contains(obj)){
		innerSet.remove(obj);
		return true;
	  } else {
		return false;
	  }
    }
    
    public boolean contains(T obj) {
	  return(innerSet.contains(obj));
    }
    
    public String toString(){
	  String ret = "";
	  for (int i = 0; i < innerSet.size(); i++){
		ret += innerSet.get(i).toString();
	  }
	  return ret;
    }
}
