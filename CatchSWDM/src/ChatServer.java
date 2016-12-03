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
	private static int[] eachRoomUser = new int[6];
	private static ArrayList[] eachRoomUserInfo = new ArrayList[6];

	public static void main(String[] args) throws Exception {
		System.out.println("The chat server is running.");
		ServerSocket listener = new ServerSocket(PORT);
		for (int i = 0; i < 6; i++) {
			eachRoomUserInfo[i] = new ArrayList();
			eachRoomUser[i] = 0;
		}
		try {
			while (true) {
				new Handler(listener.accept()).start();
			}
		} finally {
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
			int compare = 0;
			try {
				in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // Client
				out = new PrintWriter(socket.getOutputStream(), true);// Client

				while (true) {
					out.println("SUBMITNAME");
					name = in.readLine();
					if (name == null) {
						return;
					}
					synchronized (lt) {
						HashMap gHm = new HashMap();
						String name1;
						for (int c = 0; c < lt.size(); c++) {
							gHm = (HashMap) lt.get(c);
							name1 = (String) gHm.get("name");
							if (name1.equals(name)) {
								compare = 1;
								break;
							}
						}
						if (compare == 1) {
							compare = 0;
							out.println("Error");
							continue;
						} else {
							out.println("true");
							writers = new HashMap();
							writers.put("name", name);
							writers.put("Client", out);
							lt.add(writers);
							break;
						}
					}
				}

				out.println("NAMEACCEPTED " + eachRoomUser[0] + " " + eachRoomUser[1] + " " + eachRoomUser[2]
						+ " " + eachRoomUser[3] + " " + eachRoomUser[4] + " " + eachRoomUser[5]);
				PrintWriter e;
				HashMap gHm = new HashMap();
				String tempName, temp;
				for (int c = 0; c < lt.size(); c++) {
					gHm = (HashMap) lt.get(c);
					e = (PrintWriter) gHm.get("Client");
					e.println("message A new user " + name + " entered the chat room !!! ***");
					e.println("assign " + name);
				}
				String namelist = "", allname;
				for (int c = 0; c < lt.size(); c++) {
					gHm = (HashMap) lt.get(c);
					allname = (String) gHm.get("name");
					if (!allname.equals(this.name)) {
						namelist += allname + " ";
					}

				}
				out.println("alluser " + namelist);

				while (true) {
					//System.out.println(eachRoomUser[0] + " " + eachRoomUser[1] + " " + eachRoomUser[2] + " "+ eachRoomUser[3] + " " + eachRoomUser[4] + " " + eachRoomUser[5]);

					String input = in.readLine();
					//System.out.println(input);

					if (input == null) {
						return;
					} else if (input.startsWith("message")) {
						for (int c = 0; c < lt.size(); c++) {

							gHm = (HashMap) lt.get(c);
							e = (PrintWriter) gHm.get("Client");
							e.println("message " + name + " : " + input.substring(8));
						}
					}
					//out.println("roommsg "+room +" "+textField.getText());
					else if (input.startsWith("roommsg")) { // each room message
						String[] str = input.split(" ");
						int tmproom = Integer.parseInt(str[1]);

						for (int c = 0; c < eachRoomUserInfo[tmproom].size(); c++) {
							gHm = (HashMap) eachRoomUserInfo[tmproom].get(c);
							e = (PrintWriter) gHm.get("Client");
							e.println("roommsg " +name + " : " + input.substring(10));
						}

					}

					//out.println("point "+ room +" " + x + " " + y);
					else if (input.startsWith("point")) {
						String[] str = input.split(" ");
						int tmproom = Integer.parseInt(str[1]);

						for (int c = 0; c < eachRoomUserInfo[tmproom].size(); c++) {
							gHm = (HashMap) eachRoomUserInfo[tmproom].get(c);
							e = (PrintWriter) gHm.get("Client");
							tempName = (String) gHm.get("name");
							//System.out.println("room: " +tmproom +" name : "+tempName);
							if (!name.equals(tempName))
								e.println("point "+ str[1].toString()  + " " + str[2].toString() + " " + str[3].toString());
						}

					}
					//out.println("released "+room+" ");
					else if (input.startsWith("released")) {
						String[] str = input.split(" ");
						int tmproom = Integer.parseInt(str[1]);

						for (int c = 0; c < eachRoomUserInfo[tmproom].size(); c++) {
							gHm = (HashMap) eachRoomUserInfo[tmproom].get(c);
							e = (PrintWriter) gHm.get("Client");
							tempName = (String) gHm.get("name");
							if (!tempName.equals(this.name))
								e.println("released "+tmproom);
						}

					}
					//out.println("clearpanel");
					else if (input.startsWith("clearpanel")) {
						String[] str = input.split(" ");
						int tmproom = Integer.parseInt(str[1]);

						for (int c = 0; c < eachRoomUserInfo[tmproom].size(); c++) {
							gHm = (HashMap) eachRoomUserInfo[tmproom].get(c);
							e = (PrintWriter) gHm.get("Client");
							tempName = (String) gHm.get("name");
							if (!name.equals(tempName))
								e.println("clearpanel "+tmproom);
						}


					}

					//out.println("mode "+ room +" 1");
					else if (input.startsWith("mode")) {
						String[] str = input.split(" ");
						int tmproom = Integer.parseInt(str[1]);

						for (int c = 0; c < eachRoomUserInfo[tmproom].size(); c++) {
							gHm = (HashMap) eachRoomUserInfo[tmproom].get(c);
							e = (PrintWriter) gHm.get("Client");
							tempName = (String) gHm.get("name");
							if (!name.equals(tempName))
								e.println("mode " +str[1].toString() + " " + str[2].toString());
						}

					}




					else if (input.startsWith("change")) {
						//System.out.println(input);
						String[] str = input.split(" ");
						int pp = Integer.parseInt(str[1]);

						// server record
						if (str[2].equals("+")) {
							if (eachRoomUser[pp] != 0) {
								String eachRoomNameList="",tmpname="";
								for (int c = 0; c < eachRoomUserInfo[pp].size(); c++) {
									gHm = (HashMap) eachRoomUserInfo[pp].get(c);
									tmpname = (String) gHm.get("name");
									eachRoomNameList +=tmpname+" ";
								}
								eachRoomNameList +=name;
								for (int c = 0; c < eachRoomUserInfo[pp].size(); c++) {
									gHm = (HashMap) eachRoomUserInfo[pp].get(c);
									e = (PrintWriter) gHm.get("Client");
									e.println("convertUserInfo "+ pp + " " + (eachRoomUser[pp] + 1) + " " + eachRoomNameList);
								}
							}

							eachRoomUser[pp]++;


							HashMap wri = new HashMap();
							for (int c = 0; c < lt.size(); c++) {

								gHm = (HashMap) lt.get(c);
								e = (PrintWriter) gHm.get("Client");
								tempName = (String) gHm.get("name");
								if(tempName.equals(str[3].toString())){
									wri.put("name", tempName);
									wri.put("Client", e);
									break;
								}
							}
							eachRoomUserInfo[pp].add(wri);

						}
						else {

							String tmpname;
							for (int i = 0; i < eachRoomUserInfo[pp].size(); i++) {
								gHm = (HashMap) eachRoomUserInfo[pp].get(i);
								tmpname = (String) gHm.get("name");
								if (tmpname.equals(this.name.toString())) {
									eachRoomUserInfo[pp].remove(i);
									break;
								}
							}
							//System.out.println("- 1 pp: " + pp + "and eachRoomUser[pp] : " + eachRoomUser[pp]);
							eachRoomUser[pp]--;
							tmpname="";
							String eachRoomNameList="";
							for (int c = 0; c < eachRoomUserInfo[pp].size(); c++) {
								gHm = (HashMap) eachRoomUserInfo[pp].get(c);
								tmpname = (String) gHm.get("name");
								eachRoomNameList +=tmpname+" ";
							}
							for (int c = 0; c < eachRoomUserInfo[pp].size(); c++) {
								gHm = (HashMap) eachRoomUserInfo[pp].get(c);
								e = (PrintWriter) gHm.get("Client");
								e.println("convertUserInfo "+ pp + " " + eachRoomUser[pp] + " " + eachRoomNameList);
							}

							//System.out.println("- 2 pp: " + pp + "and eachRoomUser[pp] : " + eachRoomUser[pp]);

						}

						for (int c = 0; c < lt.size(); c++) {
							gHm = (HashMap) lt.get(c);
							e = (PrintWriter) gHm.get("Client");
							e.println("synch " + eachRoomUser[0] + " " + eachRoomUser[1] + " " + eachRoomUser[2]
									+ " " + eachRoomUser[3] + " " + eachRoomUser[4] + " " + eachRoomUser[5]);
						}
						for (int c = 0; c < lt.size(); c++) {
							gHm = (HashMap) lt.get(c);
							e = (PrintWriter) gHm.get("Client");
							e.println(input);
						}
					}

					else if (input.startsWith("redispose")) {
						for (int i = 0; i < 10; i++) {
							out.println(input);
						}
					}
					// 방에 들어왔을때 셋
					////out.println("initialRoom " + room);
					else if (input.startsWith("initialRoom")) {
						String[] tmp = input.split(" ");
						int pp = Integer.parseInt(tmp[1].toString());

						if (eachRoomUser[pp] == 1) {
							out.println("setRoom "+ tmp[1] + " 1 " +name);
						} else {
							String eachRoomNameList="",tmpname="";
							for (int c = 0; c < eachRoomUserInfo[pp].size(); c++) {
								gHm = (HashMap) eachRoomUserInfo[pp].get(c);
								tmpname = (String) gHm.get("name");
								eachRoomNameList +=tmpname+" ";
							}
							out.println("setRoom "+ tmp[1] +" "+ eachRoomUser[Integer.parseInt(tmp[1])]+" " + eachRoomNameList );
						}
					}

					else if(input.startsWith("retrans")){
						System.out.println("retrans :" + input.substring(8));
						out.println(input.substring(8));
					}
				}
			} catch (IOException e) {
				System.out.println(e);
			} finally {
				HashMap gHm = new HashMap();
				PrintWriter a;
				String name;
				for (int c = 0; c < lt.size(); c++) { //

					gHm = (HashMap) lt.get(c);
					name = (String) gHm.get("name");
					a = (PrintWriter) gHm.get("Client");
					if (name.equals(this.name)) {
						lt.remove(c);
						break;
					}
				}
				for (int c = 0; c < lt.size(); c++) { // Client

					gHm = (HashMap) lt.get(c);
					a = (PrintWriter) gHm.get("Client");
					a.println("removename " + this.name);
				}
				try {
					socket.close();
				} catch (IOException e) {
				}
			}
		}
	}
}