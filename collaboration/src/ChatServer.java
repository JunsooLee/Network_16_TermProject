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
	private static HashMap writers; // ø©±‚ø° ¿Ã∏ß∞˙ ¿Ø¿˙∏¶ ¿‘∑¬πﬁΩ¿¥œ¥Ÿ.
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


		@Override
		public void run() {
			int compare =0;
			try {
				in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // Clientø°º≠ ∞°¡Æø…¥œ¥Ÿ.
				out = new PrintWriter(socket.getOutputStream(), true);//Client ø°  ∫∏≥ª±‚ ¿ß«— ∫Œ∫–¿‘¥œ¥Ÿ.
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
							if (name1.equals(name)) { // ∏∏æ‡ ltø° ¿Ã∏ß¿Ã æ¯¥Ÿ∏È ¿Ã ∫Œ∫–¿ª ªı∑ŒøÓ ¿Ø¿˙∏¶ ¿‘∑¬Ω√≈∞¥¬ ∫Œ∫–¿‘¥œ¥Ÿ.
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

				out.println("NAMEACCEPTED"); // Client ¿« Textfield ∏¶ »∞º∫»≠ Ω√≈µ¥œ¥Ÿ.
				PrintWriter e;
				HashMap gHm = new HashMap();
				String tempName,temp;
				for ( int c=0; c<lt.size(); c++ ){  // ¿Ø¿˙∞° µÈæÓø¿∏È º≠πˆø°º≠  Client ∑Œ ¿¸º€«ÿ¡›¥œ¥Ÿ.
					gHm = (HashMap)lt.get(c);
					e = (PrintWriter)gHm.get("Client");
					e.println("message A new user " + name+ " entered the chat room !!! ***");
					e.println("assign " + name);
				}
				String namelist="",allname;
				for ( int c=0; c<lt.size(); c++ ){ // Client ∏¶ ≤¯Ω√  ltø° ¿˙¿Âµ«æÓ¿÷¥¯ ±‚∑œ¿ª ¡ˆøÛ¥œ¥Ÿ.
					gHm = (HashMap)lt.get(c);
					allname = (String)gHm.get("name");
					if(!allname.equals(this.name)){
						namelist +=allname+" ";
					}

				}
				out.println("alluser "+namelist);

				while (true) {
					String input = in.readLine();

					System.out.println(input);
					if (input == null) {
						return;
					}
					else if(input.startsWith("message")){
						for ( int c=0; c<lt.size(); c++ ){  // ¿Ø¿˙∞° µÈæÓø¿∏È º≠πˆø°º≠  Client ∑Œ ¿¸º€«ÿ¡›¥œ¥Ÿ.
							gHm = (HashMap)lt.get(c);
							e = (PrintWriter)gHm.get("Client");
							e.println("message "+ name+" : "+input.substring(8));
						}
					}
					else if(input.startsWith("point")){  // ±”º”∏ª¿ª ¿ß«— ∫Œ∫–¿‘¥œ¥Ÿ.
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
					else if(input.startsWith("change")){
						for ( int c=0; c<lt.size(); c++ ){
							gHm = (HashMap)lt.get(c);
							e = (PrintWriter)gHm.get("Client");
							tempName = (String)gHm.get("name");
							if(!name.equals(tempName))
								e.println("change ");
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
				for ( int c=0; c<lt.size(); c++ ){ // Client ∏¶ ≤¯Ω√  ltø° ¿˙¿Âµ«æÓ¿÷¥¯ ±‚∑œ¿ª ¡ˆøÛ¥œ¥Ÿ.
					gHm = (HashMap)lt.get(c);
					name = (String)gHm.get("name");
					a = (PrintWriter)gHm.get("Client");
					if(name.equals(this.name)){
						lt.remove(c);
						break;
					}
				}
				for ( int c=0; c<lt.size(); c++ ){ // Client ∏¶ ≤¯Ω√  ltø° ¿˙¿Âµ«æÓ¿÷¥¯ ±‚∑œ¿ª ¡ˆøÛ¥œ¥Ÿ.
					gHm = (HashMap)lt.get(c);
					a = (PrintWriter)gHm.get("Client");
					a.println("removename "+this.name);
				}
				try {
					socket.close();
				} catch (IOException e) {
				}
			}
		}
	}
}