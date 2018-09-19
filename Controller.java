import org.jfree.ui.RefineryUtilities;

public class Controller {
	
	private String ip;
	private int	seconds;
	private PingDataset dataset;
	private Ping pinger;
	private LineChart chart;
	
	public Controller(String ip,int seconds) {
		this.ip = ip;
		this.seconds = seconds;
		
		dataset = new PingDataset();
		pinger = new Ping(this.ip);
	
		chart = new LineChart("Pinging " + pinger.getIp(),this.ip);
		chart.pack();
		RefineryUtilities.centerFrameOnScreen( chart );
		chart.setVisible( true );	
	}
	
	/**
	 * Executes the updating of the graph
	 */
	public void run() {
		for(int i = 0;i < seconds;i++) {
			try
			{
			    Thread.sleep(1000);		//Ping every 1 second
			    dataset.addToDataset(pinger.pingIpAddress(), Integer.toString(pinger.getPingCount()));
			    chart.paintAgain(chart, dataset.getDataset(),ip);
			}
			catch(InterruptedException e)
			{
			    Thread.currentThread().interrupt();
			}
		}
	}
	
	
}
