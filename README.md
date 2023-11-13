## Risk Game project written in Java

[![Wikipedia article on Risk](https://upload.wikimedia.org/wikipedia/en/8/87/Risk_%28board_game%29.jpg)](https://en.wikipedia.org/wiki/Risk_(board_game))

### Overview

This is a Java implementation of the classic board game Risk with one AI opponent with different strategies. The AI opponent was implemented using the following algorithms:

* Greed
* A* search
* Real-time A* search
* Minimax search

### How to play

To play the game, simply run the `RiskGame.jar` file. You will be prompted to select the AI opponent that you want to play against. The game will then begin.

On your turn, you will be able to do the following:

* Reinforce your armies on the territories that you control.
* Attack adjacent territories that are controlled by your opponent.
* Fortify your armies on the territories that you control.

The goal of the game is to be the last player remaining with at least one army.

### AI opponent strategies

The AI opponent in this game can be configured with different strategies, which will affect its decision-making process. The following strategies are available:

* **Greed:** The greedy algorithm always chooses the move that gives it the most immediate reward. In the case of Risk, this means attacking the weakest territory that is adjacent to one of its own territories.
* **A* search:** A* search is a more sophisticated algorithm that takes into account both the immediate reward and the long-term cost of a move. In the case of Risk, this means considering the number of armies that will be lost in an attack, as well as the potential consequences of leaving a territory undefended.
* **Real-time A* search:** Real-time A* search is a variation of A* search that is designed to be used in games where the game state is constantly changing. In the case of Risk, this means that the AI opponent will be able to adjust its strategy as the game progresses.
* **Minimax search:** Minimax search is a powerful algorithm that can be used to find the best move in a game tree. In the case of Risk, this means that the AI opponent will be able to consider all of the possible moves that it could make, as well as all of the possible moves that its opponent could make.

### Non-AI opponents

The following non-AI opponents are also available:

* **Human agent:** A human agent that can make actions using the GUI.
* **Completely passive agent:** An agent that places all of its bonus armies to the territory with the fewest armies, and doesn’t make any attacks.
* **Aggressive agent:** An agent that always places all its bonus armies on the territory with the most armies, and greedily attempts to attack territories with most armies that he can attack.
* **Nearly pacifist agent:** An agent that places its armies like the completely passive agent, then conquers only the one territory with fewest armies (if it can).

### Tips for defeating the AI opponent

Here are a few tips for defeating the AI opponent:

* **Expand early:** The more territories you control, the more armies you will be able to reinforce each turn.
* **Concentrate your forces:** It is better to have a few strong armies than many weak armies.
* **Defend your key territories:** Do not let your opponent capture your most important territories.
* **Use your artillery wisely:** Artillery can be very effective at defending territories.
* **Be flexible:** Adapt your strategy to the AI opponent's strategy.

### Screenshots

[Screenshot of the Risk game](https://github.com/bard/risk-game/blob/main/screenshots/risk-game.png)

I hope these tips help you to defeat the AI opponent and become a Risk champion!
