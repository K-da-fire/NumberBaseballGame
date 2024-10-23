package Enum;

import UserException.GameTypeInputException;

public enum GameType {
  StartProgram(0),
  PlayGame(1),
  ShowTry(2),
  EndGame(3);

  private final int idx;
  GameType(int idx) {
    this.idx = idx;
  }

  public static GameType setGameType(int idx) throws GameTypeInputException {
    for (GameType gameType : GameType.values()) {
      if (gameType.idx == idx) {
        return gameType;
      }
    }
    throw new GameTypeInputException();
  }
}
