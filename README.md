# PokerGame
Simulates a Poker Game
The code works by using the PokerTest class. There are two options when run: 

1. You may give the program a hand of 5 cards, and it will tell you the highest hand you have. This is done using command line arguments. To imput a hand, you can use the format (s,h,d,c)(2-14), with s = spade, h = heart, d = diamond, c = club, and 2 = 2, 3 = 3, ect. and 11 = jack, 12 = queen, 13 = king, and 14 = ace. One example would be to run the program and type the following as a command line argument "s1 s2 s3 s4 s5", in which case the program will return that the hand is a straight flush.

2. You may play an individual game in which by not entering a command line, the computer will generate a deck and give the player a hand of five cards using the same printing scheme as above. The player then has the oportunity to switch cards by responing yes or no, until the final card has either been kept or swapped. At the end, the program will return the highest hand you have.
