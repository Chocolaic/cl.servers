/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.servers;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;

/**
 *
 * @author Chocolate
 */
public class ClServers {
    public static String ConfigPath="Config.prop";
    public static String UserPath ="user.txt";

    public static void main(String[] args) {
        Control.web control=new Control.web();
        control.HttpProtocol();
            try {
                File file=new File("logs/log.txt");
                file.getParentFile().mkdirs();
                file.createNewFile();
                Thread.sleep(100000000);                
            } catch (InterruptedException ex) {
                Logger.getLogger(ClServers.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
            Logger.getLogger(ClServers.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public static void log(String type,String msg){
    Date time=new Date();    
    System.out.println("["+time+"]"+"["+type+"]"+" "+msg);
    }
}
