/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jstacksets;

import java.io.Serializable;

/**
 *
 * @author ClearingPath
 */
public class JCommand implements Serializable {
    public String command;
    public Object data;
    
    public JCommand(String command, Object Obj){
        this.command = command;
        this.data = Obj;
    }
}
