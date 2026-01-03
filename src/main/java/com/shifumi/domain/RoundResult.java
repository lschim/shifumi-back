package com.shifumi.domain;

public record RoundResult(
    Winner winner, Move player1Move, Move player2Move, Score scoreAfterRound) {}
