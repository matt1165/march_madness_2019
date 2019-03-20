# march_madness_2019

2019 Bracket Challenge

The main method in the Tournament class will generate a bracket consisting of n rounds based on the teams.csv file. The method will also print out the teams included in each round (the winners from the previous round). The final round will contain a single unbeaten team which will be the overall champion.

The Game class contains methods to determine the winner when given two teams. This determination is made based upon the total basketball budget / # of undergraduate students / out of state tuition.  The idea here is that the greater % of tuition allocated to a team should buy success.  Based on this metric, Duke is the obvious overall winner since almost 6.5% of each student's tuition goes to the Men's basketball team.  If I were a Duke student, I would expect a National Championship if I was paying $3,185.89 each year for Men's basketball (this comes to $12,743.57 over four years, only $4,650 shy of a whole year at Texas Tech, the cheapest school in the 2019 tournament).

All budget and enrollment data is from https://ope.ed.gov/athletics/#/ while tuition data was pulled from individual institution's websites.
