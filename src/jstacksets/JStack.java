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
import java.util.List;
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
 * @param <T>
 */
public class JStack<T> extends ReceiverAdapter {

    JChannel channel;
    String user_name = System.getProperty("user.name", "n/a");
    Stack<T> innerStack;

    public JStack() throws Exception {
        innerStack = new Stack<>();
        channel = new JChannel();
        channel.setReceiver(this);
        channel.connect("JStack");
        channel.getState(null, 10000);
    }
    
    public void viewAccepted(View new_view) {
        System.out.println("** view: " + new_view);
    }

    public void receive(Message msg) {
//        String line=msg.getSrc() + ": " + msg.getObject();
        JCommand c = (JCommand) msg.getObject();
        if (c.command.equals("pop")){
            synchronized(innerStack){
                innerStack.pop();
            }
        }
        else if (c.command.equals("push")){
            synchronized(innerStack){
                innerStack.push((T) c.data);
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
        synchronized(innerStack) {
            Util.objectToStream(innerStack, new DataOutputStream(output));
        }
    }

    @SuppressWarnings("unchecked")
    public void setState(InputStream input) throws Exception {
        Stack<T> list=(Stack<T>)Util.objectFromStream(new DataInputStream(input));
        synchronized(innerStack) {
            innerStack.clear();
            innerStack.addAll(list);
        }
//        System.out.println("received innerStack (" + list.size() + " messages in chat history):");
    }
    
    public void push(T obj) {
        synchronized(innerStack){
            innerStack.push(obj);
        }
        send("push",obj);
    }

    public T pop() {
        T ret;
        synchronized(innerStack){
            ret = (T) innerStack.pop();
        }
        send("pop", null);
        return ret;
    }

    public T top() {
        return innerStack.firstElement();
    }

    public int size() {
        return innerStack.size();
    }

    @Override
    public String toString() {
        String ret = "";
        for (int i = innerStack.size()-1 ; i >= 0; i--) {
            ret += "[" + innerStack.elementAt(i).toString() + "]\n";
        }
        return ret;
    }

}
