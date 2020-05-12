Player: Initially have 5 energies, 0 coin, 0 points. 

Maze: Every grid corresponds to a little game. There are a total of three little games: button, guess number, puzzle. 
Yellow circles are coins, player gets a free coin when they pass by.
Every time the player encounters a gird, 1 energy is deducted.

Button (Easy): 
	Win:	+5 points
	Lose:	-5 points

Button (Hard):
	Win:	+10 points
	Lose:	-10 points

Guess number:
	Win:	+10 points
	Lose: 	-10 points

Puzzle (Easy):
	Win: 	+5 points
	Lose:	-5 points

Puzzle (Medium):
	Win:	+10 points
	Lose:	-10 points

Puzzle (Hard):
	Win:	+20 points
	Lose:	-20 points

Store: Player can exchange energy with two coins.

*****************************************************
Note: Change after deadline:
Problem: Wrong route in package guessNumber causes unexpected warning after we re-package the program.
Solution: Change the route of package guessNumber.