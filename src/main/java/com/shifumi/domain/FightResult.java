package com.shifumi.domain;

import java.util.List;

public record FightResult(
    Player player1,
    Player player2,
    List<Move> player1Moves,
    List<Move> player2Moves,
    List<RoundResult> roundResults,
    Score finalScore,
    Winner winner) {}
