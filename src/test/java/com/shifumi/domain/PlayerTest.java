package com.shifumi.domain;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

class PlayerTest {

  @Test
  void getMoveShouldAlwaysReturnAMove() {
    Player player = new Player("player 1", "", List.of(Move.ROCK));
    Assert.notNull(
        player.getMove(2),
        "getMove() should always return a value even when index is bigger than the number of moves for that player");
  }
}
