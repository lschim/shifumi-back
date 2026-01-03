package com.shifumi.domain;

public enum Move {
  ROCK {
    @Override
    public boolean beats(Move other) {
      return SCISSORS == other;
    }
  },
  PAPER {
    @Override
    public boolean beats(Move other) {
      return ROCK == other;
    }
  },
  SCISSORS {
    @Override
    public boolean beats(Move other) {
      return PAPER == other;
    }
  };

  public abstract boolean beats(Move other);
}
