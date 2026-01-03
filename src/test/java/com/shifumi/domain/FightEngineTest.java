package com.shifumi.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FightEngineTest {

  private FightEngine fightEngine;

  @BeforeEach
  void setUp() {
    fightEngine = new FightEngine();
  }

  // --------- SINGLE ROUND TESTS ---------
  @Test
  void rockBeatsScissors() {
    RoundResult result = fightEngine.resolveRound(Move.ROCK, Move.SCISSORS, new Score(0, 0));

    assertEquals(Winner.PLAYER1, result.winner());
    assertEquals(Move.ROCK, result.player1Move());
    assertEquals(Move.SCISSORS, result.player2Move());
    assertEquals(1, result.scoreAfterRound().player1Score());
    assertEquals(0, result.scoreAfterRound().player2Score());
  }

  @Test
  void paperBeatsRock() {
    RoundResult result = fightEngine.resolveRound(Move.PAPER, Move.ROCK, new Score(0, 0));

    assertEquals(Winner.PLAYER1, result.winner());
    assertEquals(Move.PAPER, result.player1Move());
    assertEquals(Move.ROCK, result.player2Move());
    assertEquals(1, result.scoreAfterRound().player1Score());
    assertEquals(0, result.scoreAfterRound().player2Score());
  }

  @Test
  void scissorsBeatsPaper() {
    RoundResult result = fightEngine.resolveRound(Move.SCISSORS, Move.PAPER, new Score(0, 0));

    assertEquals(Winner.PLAYER1, result.winner());
    assertEquals(Move.SCISSORS, result.player1Move());
    assertEquals(Move.PAPER, result.player2Move());
    assertEquals(1, result.scoreAfterRound().player1Score());
    assertEquals(0, result.scoreAfterRound().player2Score());
  }

  @Test
  void sameMovesIsDraw() {
    RoundResult result1 = fightEngine.resolveRound(Move.ROCK, Move.ROCK, new Score(0, 0));
    RoundResult result2 = fightEngine.resolveRound(Move.PAPER, Move.PAPER, new Score(0, 0));
    RoundResult result3 = fightEngine.resolveRound(Move.SCISSORS, Move.SCISSORS, new Score(0, 0));

    assertEquals(Winner.DRAW, result1.winner());
    assertEquals(Winner.DRAW, result2.winner());
    assertEquals(Winner.DRAW, result3.winner());
  }

  // --------- FULL FIGHT TESTS ---------
  @Test
  void fullFightWithoutRandom() {
    Player player1 =
        new Player(
            "Player 1",
            "",
            List.of(
                Move.ROCK,
                Move.PAPER,
                Move.SCISSORS,
                Move.ROCK,
                Move.ROCK,
                Move.PAPER,
                Move.ROCK,
                Move.ROCK));
    Player player2 =
        new Player(
            "Player 2",
            "",
            List.of(Move.SCISSORS, Move.ROCK, Move.SCISSORS, Move.PAPER, Move.PAPER, Move.ROCK));

    FightResult fightResult = fightEngine.resolveFight(player1, player2);

    assertEquals(Winner.PLAYER1, fightResult.winner());
    assertEquals(3, fightResult.finalScore().player1Score());
    assertEquals(2, fightResult.finalScore().player2Score());
    assertEquals(6, fightResult.roundResults().size());
  }

  @Test
  void fullFightWithRandom() {
    Player player1 = new Player("Player 1", "", List.of(Move.ROCK));
    Player player2 = new Player("Player 2", "", List.of(Move.SCISSORS));

    FightResult fightResult = fightEngine.resolveFight(player1, player2);

    assertNotNull(fightResult.winner());
    assertTrue(
        fightResult.finalScore().player1Score() == 3
            || fightResult.finalScore().player2Score() == 3);
  }
}
