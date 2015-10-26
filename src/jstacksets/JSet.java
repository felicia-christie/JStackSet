/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jstacksets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.ReceiverAdapter;
import org.jgroups.View;
import org.jgroups.util.Util;

/**
 *
 * @author Waxrain
 */
public class JSet<T> extends ReceiverAdapter {

    JChannel channel;
    String user_name = System.getProperty("user.name", "n/a");
    ArrayList<T> innerSet;

    public JSet() throws Exception {
        innerSet = new ArrayList<>();
        channel = new JChannel();
        channel.setReceiver(this);
        channel.connect("JSet");
        channel.getState(null, 10000);
    }
    
    public void viewAccepted(View new_view) {
        System.out.println("** view: " + new_view);
    }

    public void receive(Message msg) {
        if (!msg.getSrc().toString().equals(channel.getName())){
            JCommand c = (JCommand) msg.getObject();
            if (c.command.equals("remove")){
                synchronized(innerSet){
                    innerSet.remove((T) c.data);
                }
            }
            else if (c.command.equals("add")){
                synchronized(innerSet){
                    innerSet.add((T) c.data);
                }
            }
        }
        
    }
    
    public void send(String command,T Obj){
        try {
            JCommand c = new JCommand(command, Obj);
            Message msg = new Message(null,null,c);
            channel.send(msg);
        } catch (Exception ex) {
            Logger.getLogger(JStack.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getState(OutputStream output) throws Exception {
        synchronized(innerSet) {
            Util.objectToStream(innerSet, new DataOutputStream(output));
        }
    }

    @SuppressWarnings("unchecked")
    public void setState(InputStream input) throws Exception {
        ArrayList<T> list=(ArrayList<T>)Util.objectFromStream(new DataInputStream(input));
        synchronized(innerSet) {
            innerSet.clear();
            innerSet.addAll(list);
        }
    }

    public boolean add(T obj) {
        synchronized(innerSet){
            if (!innerSet.contains(obj)) {
                innerSet.add(obj);
                send("add",obj);
                return true;
            } else {
                return false;
            }
        }
        
    }

    public boolean remove(T obj) {
        synchronized(innerSet){
            if (innerSet.contains(obj)) {
                innerSet.remove(obj);
                send("remove",obj);
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean contains(T obj) {
        return (innerSet.contains(obj));
    }

    public String toString() {
        String ret = "{ ";
        for (int i = 0; i < innerSet.size(); i++) {
            if (i == innerSet.size() - 1) {
                ret += innerSet.get(i).toString();
            } else {
                ret += innerSet.get(i).toString() + ", ";
            }
        }
        ret += " }";
        return ret;
    }
}
