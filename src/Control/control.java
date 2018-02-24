/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import cl.servers.ClServers;
import cl.servers.Reader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Chocolate
 */
public class control {
    public static String login_check(String pw){
        String[] function=pw.split("=");
        switch(function[0]){
            case "user":
        String[] users=Reader.fileReader(ClServers.UserPath).split("\n");
        for(int l=0;l<users.length;l++){
        if(pw.equals(users[l])){
        return "Access";
            }
        }
        return "Failed";
        case "reg":
            
        return null;
        }
        return null;
    }
    public static String Getargu(String arg,String key){
        Properties reader=new Properties();
        try{
            reader.load(new FileInputStream(arg));
        }catch(IOException e){
            ClServers.log("错误", "Exception出现！位于java.io:control.Getargu()");
        }
        return reader.getProperty(key);
    }
    void event(){
    
    }
}
