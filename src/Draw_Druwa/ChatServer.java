package Draw_Druwa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class ChatServer {
	private static final int PORT = 9001;
    private static HashMap writers;
    private static ArrayList lt = new ArrayList();
    public static void main(String[] args) throws Exception {
        System.out.println("The chat server is running.");
        ServerSocket listener = new ServerSocket(PORT);
        
        
        try {
            while (true) {
                new Handler(listener.accept()).start();
            }
        } 
        finally {
            listener.close();
        }
    }

    private static class Handler extends Thread {
        private String name;
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;

        public Handler(Socket socket) {
            this.socket = socket;
        }

   
        public void run() {
        	int compare =0;
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                while (true) {
                    out.println("SUBMITNAME");
                    name = in.readLine();
                    if (name == null) {
                        return;
                    }
                    synchronized (lt) {
                    	HashMap gHm = new HashMap();
                    	String name1;
                    	for ( int c=0; c<lt.size(); c++ ){
                    		gHm = (HashMap)lt.get(c);
                    		name1 = (String)gHm.get("name");
                    		if (name1.equals(name)) { 
                    			compare =1;
                    			break;
                    		}
                    	}
                        if(compare ==1){
                        	compare =0;
                        	out.println("Error");
                        	continue;
                        }
                        else{
                        	out.println("true");
                        	writers = new HashMap();
                    		writers.put("name", name);
                    		writers.put("Client", out);
                    		lt.add(writers);
                    		break;
                        }
                    }
                }
                out.println("NAMEACCEPTED");
                PrintWriter e;
                HashMap gHm = new HashMap();
            	String tempName,temp;
                for ( int c=0; c<lt.size(); c++ ){ 
                	 gHm = (HashMap)lt.get(c);
                     e = (PrintWriter)gHm.get("Client");  
                     e.println("A new user " + name+ " entered the chat room !!! ***");
                }
              
                while (true) {
                    String input = in.readLine();
                    if (input == null) {
                        return;
                    }
                    else if(input.startsWith("point")){
                    	String[] str = input.split(" ");
                    	for ( int c=0; c<lt.size(); c++ ){
                    		gHm = (HashMap)lt.get(c);
                            e = (PrintWriter)gHm.get("Client"); 
                            tempName = (String)gHm.get("name");
                            if(!name.equals(tempName))
                            	e.println("point " + str[1].toString() + " " + str[2].toString());
                    	}
                    }
                    else if(input.startsWith("released")){
                    	for ( int c=0; c<lt.size(); c++ ){
                    		gHm = (HashMap)lt.get(c);
                            e = (PrintWriter)gHm.get("Client"); 
                            tempName = (String)gHm.get("name");
                            if(!name.equals(tempName))
                            	e.println("released ");
                    	}
                    }
                    else if(input.startsWith("clearpanel")){
                    	for ( int c=0; c<lt.size(); c++ ){
                    		gHm = (HashMap)lt.get(c);
                            e = (PrintWriter)gHm.get("Client"); 
                            tempName = (String)gHm.get("name");
                            if(!name.equals(tempName))
                            	e.println("clearpanel ");
                    	}
                    }
                    else if(input.startsWith("mode")){
                    	String[] str = input.split(" ");
                    	for ( int c=0; c<lt.size(); c++ ){
                    		gHm = (HashMap)lt.get(c);
                            e = (PrintWriter)gHm.get("Client"); 
                            tempName = (String)gHm.get("name");
                            if(!name.equals(tempName))
                            	e.println("mode " + str[1].toString());
                    	}
                    }
                }
            }
            catch (IOException e) {
                System.out.println(e);
            }
            finally {
            	HashMap gHm = new HashMap();
                PrintWriter a;
                String name;
            	for ( int c=0; c<lt.size(); c++ ){
            		gHm = (HashMap)lt.get(c);
            		name = (String)gHm.get("name");
                   a = (PrintWriter)gHm.get("Client");  
                    if(name.equals(this.name)){
                    	lt.remove(c);
                    	break;
                    }
            	
            	}
                try {
                    socket.close();
                } catch (IOException e) {
                }
            }
        }
    }
}
