/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import cl.servers.ClServers;
import cl.servers.Reader;
import java.net.Socket;
import java.net.InetSocketAddress;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.Thread;
import java.util.Iterator;  
import java.util.List;  
import java.util.Set;
import java.util.concurrent.Executors;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.Headers;  
import com.sun.net.httpserver.HttpExchange;  
import com.sun.net.httpserver.HttpHandler; 
/**
 *
 * @author Chocolate
 */
public class web {
    
    int port=Integer.parseInt(Reader.configReader(ClServers.ConfigPath, "ControlPort"));
    String IPaddress=Reader.configReader(ClServers.ConfigPath, "LocalAddress");
    public void HttpProtocol(){
        try{
        InetSocketAddress httpserver = new InetSocketAddress(IPaddress,port);
        HttpServer server = HttpServer.create(httpserver, 0);
        server.createContext("/",new Response());
        server.setExecutor(Executors.newCachedThreadPool());
        server.start();
        ClServers.log("信息","服务端开启成功("+IPaddress+":"+port+")");
        }catch(IOException e){
        e.printStackTrace();
        }
    }
    
    String LoginPath="Control/html/login.html";
    String ControlPath="Control/html/Control.html";
    class Response implements HttpHandler{
    public void handle(HttpExchange exchange) throws IOException {
    String requestTYPE = exchange.getRequestMethod();
    if(requestTYPE.equalsIgnoreCase("GET")){
        Headers responseHeaders = exchange.getResponseHeaders();
        if(null!=Reader.byteReader("Data/HTTP"+exchange.getRequestURI().getPath())){
        exchange.sendResponseHeaders(200, 0);
        OutputStream responseBody = exchange.getResponseBody();
        responseBody.write(Reader.byteReader("Data/HTTP"+exchange.getRequestURI().getPath()));
        responseBody.close(); 
    }else{
            responseHeaders.set("Content-Type", "text/html");
            exchange.sendResponseHeaders(200, 0);
            OutputStream responseBody = exchange.getResponseBody();
            InputStream input= getClass().getClassLoader().getResourceAsStream(LoginPath);
            byte[] buffer=new byte[input.available()];
            input.read(buffer);
            input.close();
            String tex=new String(buffer);
            responseBody.write(tex.replace("$ADDRESS$",IPaddress+":"+String.valueOf(port)).getBytes()); 
            responseBody.close(); 
        }
        }
    if(requestTYPE.equalsIgnoreCase("POST")){
            Headers responseHeaders = exchange.getResponseHeaders();
        responseHeaders.set("Content-Type", "text/html");
        exchange.sendResponseHeaders(200, 0);
        OutputStream responseBody = exchange.getResponseBody();  
        InputStream ClientRequest=exchange.getRequestBody();
        byte[] Request=new byte[ClientRequest.available()];
        ClientRequest.read(Request); 
            String tex=new String(Request);
            String command=control.login_check(tex);
            if(command=="Access"){
            ClServers.log("信息",exchange.getRemoteAddress().getAddress().toString()+"已登入");
            InputStream input= getClass().getClassLoader().getResourceAsStream(ControlPath);
            byte[] buffer=new byte[input.available()];
            input.read(buffer);
            input.close();
            String con=new String(buffer);
            responseBody.write(con.replace("$ADDRESS$",IPaddress+":"+String.valueOf(port)).getBytes()); 
            responseBody.close(); 
            }else{
            if(command=="Failed"){
            String logout="<h2>Wrong Password</h2><br/><a href=”#”onClick=”javascript :history.back(-1);”>Back</a>";
            responseBody.write((new String(logout.getBytes(),"utf-8")).getBytes());
            }
            }
            responseBody.close(); 
    }
    }
    }
}

