/*
	This class can be extended to add any team statistics to be used when simulating a game
 */

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.Reader;

public class Team {

	private String name;
	private double stat;

	public Team(String name, String stat) throws Exception{
		this.name = name;

		if(stat!=null && !stat.isEmpty()) {
			this.stat = Double.parseDouble(stat.replace("%",""));
		} else {
			this.stat = 0;
		}


	}

	public String getName() {
		return name;
	}
	public double getStat() { return stat;}

}
