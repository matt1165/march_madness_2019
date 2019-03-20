import java.io.FileReader;
import java.io.Reader;
import java.util.Iterator;
import java.util.LinkedList;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class Tournament {

	/*	Location of teams.csv, this file should be an ordered list of teams competing in the tournament.
		Teams must be ordered by matched seed: 1,16,8,9,5,12,4,13,6,11,3,14,7,10,2,15
		And by bracket region: (East-West-Midwest-South)

		Example:

		Team Name
		[Name of #1 seeded team in first regional sub bracket]
		[Name of #16 seeded team in first regional sub bracket]
		[Name of #8 seeded team in first regional sub bracket]
		[Name of #9 seeded team in first regional sub bracket]
		...
		[Name of #1 seeded team in second regional sub bracket]
		[Name of #16 seeded team in second regional sub bracket]
		[Name of #8 seeded team in second regional sub bracket]
		[Name of #9 seeded team in second regional sub bracket]

	 */
	private String TEAM_FILE_PATH = ".//data//teams.csv";
	
	private LinkedList<Team> teams;
	private LinkedList<LinkedList<Team>> rounds = new LinkedList<LinkedList<Team>>();
	
	/*
	 	Simulate a tournament and print winners of each round to standard output
	 */
	public static void main(String[] args) throws Exception{
		Tournament marchMadness = new Tournament();
		marchMadness.printAllRounds();
	}
	
	/*
	 	A Tournament object is a LinedList of LinkedLists
	 	of Teams representing the rounds of a tournament
	 */
	private Tournament() throws Exception{
		
		//Generate teams
		teams = getTeams();

		//Generate first round
		rounds.add(teams);
		
		//Generate rounds, add to map
		LinkedList<Team> round = teams;
		for(int i=2; i<calcRounds(teams); i++){
			round = getNextRound(round);
			rounds.add(round);
		}
		
		//Determine Champion
		round = new LinkedList<>();
		round.add(new Game(rounds.getLast().get(0),rounds.getLast().get(1)).getWinner());
		rounds.add(round);
	}
	
	/*
	 	Determine the winners that will move to the next round
	 */
	public LinkedList<Team> getNextRound(LinkedList<Team> teams) throws Exception{
		LinkedList<Team> nextRound = new LinkedList<Team>();
		Iterator<Team> itr = teams.iterator();
		
		while(itr.hasNext()){
			nextRound.add(new Game(itr.next(),itr.next()).getWinner());
		}
		return nextRound;
	}
		
	/*
	 	Read a formatted .csv of team names to generate
	 	a LinkedList of teams in the tournament

	 	Teams must be ordered by matched seed:
	 		1,16,8,9,5,12,4,13,6,11,3,14,7,10,2,15

	 	And by bracket region: (East-West-Midwest-South)
	 */
	private LinkedList<Team> getTeams() throws Exception{
		Reader fileReader = new FileReader(TEAM_FILE_PATH);
		Iterable <CSVRecord> records = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(fileReader);

		String teamName;
		String teamStat;
		LinkedList<Team> teamList = new LinkedList<Team>();

		//Add each team to the list of teams
		for(CSVRecord row : records){
			teamName = row.get("Team");
			teamStat = row.get("expense_ratio");
			teamList.add(new Team(teamName, teamStat));
		}
		return teamList;
	}

	//Calculate the number of rounds in the tournament
	private int calcRounds(LinkedList<Team> teams){
		return (int) (Math.log(teams.size())/Math.log(2)) + 1;
	}

	private void printAllRounds(){
		int roundNumber = 1;
		for(LinkedList<Team> teams : rounds){
			System.out.println("\n***Round " + roundNumber++ + "***\n");
			for(Team t : teams){
				System.out.println(t.getName() + ",");
			}
		}
	}
}
