package com.shifumi.domain;

import java.util.ArrayList;

public class FightEngine {
  public static final int ROUNDS_TO_WIN = 3;
  public static final int MOVES_PER_PLAYER = 10;
  public static final int MAX_ROUNDS = 100;

  public FightResult resolveFight(Player player1, Player player2) {
    var roundResults = new ArrayList<RoundResult>();
    int currentRound = 0;
    Move player1Move;
    Move player2Move;
    Score currentScore = new Score(0, 0);
    RoundResult roundResult;
    while (currentScore.player1Score() < ROUNDS_TO_WIN
        && currentScore.player2Score() < ROUNDS_TO_WIN
        && currentRound < MAX_ROUNDS) {

      player1Move = player1.getMove(currentRound);
      player2Move = player2.getMove(currentRound);
      roundResult = resolveRound(player1Move, player2Move, currentScore);
      currentScore = roundResult.scoreAfterRound();
      roundResults.add(roundResult);
      currentRound++;
    }
    return new FightResult(
        player1,
        player2,
        player1.moves(),
        player2.moves(),
        roundResults,
        currentScore,
        currentScore.computeWinner());
  }

  public RoundResult resolveRound(Move player1Move, Move player2Move, Score currentScore) {
    if (player1Move == player2Move) {
      return new RoundResult(Winner.DRAW, player1Move, player2Move, currentScore);
    }
    Winner winner = player1Move.beats(player2Move) ? Winner.PLAYER1 : Winner.PLAYER2;
    Score newScore = currentScore.increaseFor(winner);
    return new RoundResult(winner, player1Move, player2Move, newScore);
  }
}
