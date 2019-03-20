public class Game {

	private Team winner;

	/*
		Modify this constructor to simulate a game and determine a winner
	 */
	public Game(Team team1, Team team2) throws Exception{

		if(team1.getStat() > team2.getStat()) {
			this.winner = team1;
		} else {
			this.winner = team2;
		}
	}
	
	public Team getWinner(){
		return winner;
	}
}
