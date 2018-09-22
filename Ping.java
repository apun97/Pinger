import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Ping {
	
	private int pinged;		//Keep track of how many times this has been pinged
	private String ip;
	private char os;	//m for mac, w for windows
	
	public Ping(String ip) {
		this.pinged = 0;
		this.ip = ip;
		os = System.getProperty("os.name").toLowerCase().charAt(0);	
		
	}
	
	public Ping() {
		this.pinged = 0;
		this.ip = "google.com";		//google.com as default	
		os = System.getProperty("os.name").toLowerCase().charAt(0);
	}
	
	/**
	 * Sends a ping request to given ip address and records the time taken
	 * @param ip Given ip address
	 * @return int Time taken for ping request, returns -1 if address is unreachable
	 * 
	 */
	public int pingIpAddress() {
		Process p;
		int ping = 0;
		
		try {
			if(os == 'm')
				p = Runtime.getRuntime().exec("ping -c 1 " + ip);
			else
				p = Runtime.getRuntime().exec("ping -n 1 " + ip);
			p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = reader.readLine(); //Disregard the first line
			if(os == 'w')
				reader.readLine();
			line=reader.readLine();
			
			if(line.equals("ping: sendto: No route to host")) {
				reader.readLine();		//Get rid of the "Request timeout for icmp_seq #"
			}
			else if(line.contains("Request timeout for icmp_seq")) {
				//Do nothing
			}
			else {
				ping = this.parsePing(line);
			}
			pinged++;

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ping;
	}
	
	/**
	 * Parses the line sent from ping command
	 * @param line	
	 * @return int Ping time
	 */
	private int parsePing(String line) {
		int indexOfTime = line.indexOf("time");
		int indexOfNum = indexOfTime + 5;
		String pingString = "";
		
		if(os == 'w') {
			int indexOfS = line.indexOf('s',indexOfTime);
			pingString = line.substring(indexOfNum,indexOfS-1);
		}
		else {
			int indexOfDecimal = line.indexOf('.', indexOfTime);
			pingString = line.substring(indexOfNum, indexOfDecimal);
		}
		
		return Integer.parseInt(pingString);
	}
	
	public int getPingCount() {
		return pinged;
	}
	
	public String getIp() {
		return ip;
	}
}
