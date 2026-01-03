package com.shifumi.domain;

public record Score(int player1Score, int player2Score) {

  public Score increaseFor(Winner winner) {
    return switch (winner) {
      case PLAYER1 -> new Score(this.player1Score + 1, this.player2Score);
      case PLAYER2 -> new Score(this.player1Score, this.player2Score + 1);
      default -> this;
    };
  }

  public Winner computeWinner() {
    if (player1Score > player2Score) return Winner.PLAYER1;
    if (player2Score > player1Score) return Winner.PLAYER2;
    return Winner.DRAW;
  }
}
