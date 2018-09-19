import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Ping {
	
	private int pinged;		//Keep track of how many times this has been pinged
	private String ip;
	
	public Ping(String ip) {
		this.pinged = 0;
		this.ip = ip;
		
	}
	
	public Ping() {
		this.pinged = 0;
		this.ip = "google.com";		//google.com as default	
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
			p = Runtime.getRuntime().exec("ping -c 1 " + ip);
			p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = reader.readLine(); //Disregard the first line
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
		int indexOfDecimal = line.indexOf('.', indexOfTime);
		
		String pingString = line.substring(indexOfTime + 5, indexOfDecimal);
		
		return Integer.parseInt(pingString);
	}
	
	public int getPingCount() {
		return pinged;
	}
	
	public String getIp() {
		return ip;
	}
}
