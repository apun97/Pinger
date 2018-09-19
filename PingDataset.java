import org.jfree.data.category.DefaultCategoryDataset;

public class PingDataset {
	
	private DefaultCategoryDataset dataset;
	
	public PingDataset() {
		dataset = new DefaultCategoryDataset( );
	}

	/**
	 * Adds a value to the Dataset
	 * @param time	Time increments
	 * @param ping	Response time
	 */
	public void addToDataset(int ping, String time) {
		dataset.addValue( ping , "Ping" , time );
	}
	
	public DefaultCategoryDataset getDataset() {
		return dataset;
	}
}
