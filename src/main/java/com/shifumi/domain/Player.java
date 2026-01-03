package com.shifumi.domain;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public record Player(String name, String avatarPath, List<Move> moves) {

  public Move getMove(int index) {
    if (index >= moves.size()) {
      return Move.values()[ThreadLocalRandom.current().nextInt(Move.values().length)];
    } else {
      return moves.get(index);
    }
  }
}
