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
	private static int[] eachRoomUser = new int[6];
	private static ArrayList[] eachRoomUserInfo = new ArrayList[6];
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
			for(int i= 0; i< 6 ; i++){
				eachRoomUserInfo[i] = new ArrayList();
				eachRoomUser[i] =0;
			}
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
					System.out.println(eachRoomUser[0] +" "+ eachRoomUser[1]+ " "+eachRoomUser[2] + " "+eachRoomUser[3]  + " "+eachRoomUser[4]  + " "+eachRoomUser[5]);
					String input = in.readLine();

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
						System.out.println(input);
						String[] str = input.split(" ");
						int pp = Integer.parseInt(str[1]);

						//server record
						if(str[2].equals("+")){
							if(eachRoomUser[pp] != 0){
								for ( int c=0; c<eachRoomUserInfo[pp].size(); c++ ){
									gHm = (HashMap)lt.get(c);
									e = (PrintWriter)gHm.get("Client");
									e.println("changeUserInfo "+ eachRoomUser[pp]+1 +" "+name);
								}
							}
							System.out.println("+ 1 pp: "+ pp  +"and eachRoomUser[pp] : "+eachRoomUser[pp]);
							eachRoomUser[pp]++;
							System.out.println("+ 2 pp: "+ pp  +"and eachRoomUser[pp] : "+eachRoomUser[pp]);
							writers = new HashMap();
							writers.put("name", name);
							writers.put("Client", out);

							eachRoomUserInfo[pp].add(writers); // 에러지


						}
						else{
							String tmpname;
							for(int i = 0 ; i < eachRoomUserInfo[pp].size() ; i++){
								gHm = (HashMap) eachRoomUserInfo[pp].get(i);
								tmpname = (String)gHm.get("name");
								if(tmpname.equals(this.name)){
									eachRoomUserInfo[pp].remove(i);
									break;
								}
							}
							System.out.println("- 1 pp: "+ pp  +"and eachRoomUser[pp] : "+eachRoomUser[pp]);
							eachRoomUser[pp]--;
							System.out.println("- 2 pp: "+ pp  +"and eachRoomUser[pp] : "+eachRoomUser[pp]);

						}
						// end serverrecord

						for ( int c=0; c<lt.size(); c++ ){
							gHm = (HashMap)lt.get(c);
							e = (PrintWriter)gHm.get("Client");
							tempName = (String)gHm.get("name");
							if(!tempName.equals(this.name))
								e.println(input);
						}
					}


					else if(input.startsWith("redispose")){
						System.out.println("tset: "+eachRoomUser[0] +" "+ eachRoomUser[1]+ " "+eachRoomUser[2] + " "+eachRoomUser[3]  + " "+eachRoomUser[4]  + " "+eachRoomUser[5]);
						for(int i = 0 ; i < 10 ; i++){
							out.println(input+" "+eachRoomUser[0] +" "+ eachRoomUser[1]+ " "+eachRoomUser[2] + " "+eachRoomUser[3]  + " "+eachRoomUser[4]  + " "+eachRoomUser[5]);
						}
					}
					//방에 들어왔을때 셋
					else if(input.startsWith("initialRoom")){
						String[] tmp = input.split(" ");
						if(eachRoomUser[Integer.parseInt(tmp[1])] ==1){
							out.println("setRoom 1");
						}
						else{
							out.println("setRoom "+ eachRoomUser[Integer.parseInt(tmp[1])]);
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