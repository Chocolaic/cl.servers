/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.servers;

import java.util.Properties;
import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;

public class Reader {
    public static String configReader(String path,String key){
    Properties reader=new Properties();
        try{
            File file=new File(path);
            if(file.exists()){
            reader.load(new FileInputStream(new File(path)));
            }else{
            file.createNewFile();
            }
        }catch(IOException e){
            ClServers.log("错误", "Exception出现！位于java.io:Reader.configreader()");
        }
        return reader.getProperty(key);
    }
    public static String fileReader(String path){
        File file=new File(path);
        if(file.exists()){
            Long length=file.length();
            byte[] byt=new byte[length.intValue()];
            try{
            FileInputStream input=new FileInputStream(file);
            input.read(byt);
            input.close();
            return new String(byt);
            }catch(IOException e){
            e.printStackTrace();
            return null;
            }
        }else{
            ClServers.log("错误",path+ "不存在");
            return null;
        }
    }
        public static byte[] byteReader(String path){
        File file=new File(path);
        if(file.exists()){
            Long length=file.length();
            byte[] byt=new byte[length.intValue()];
            try{
            FileInputStream input=new FileInputStream(file);
            input.read(byt);
            input.close();
            return byt;
            }catch(IOException e){
                ClServers.log("错误", "Exception出现！位于java.io:Reader.bytereader()");
            return null;
            }
        }else{
            return null;
        }
    }
}
