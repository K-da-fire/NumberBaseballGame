package enums;

import exception.GameTypeInputException;

public enum GameType {
  START_PROGRAM(0),  //게임 타입을 받기 전에 할당되는 상수, 다른 기능이 끝날 때 초기화 됩니다.
  START_GAME(1),      //게임이 시작한 것을 저장하는 상수
  SHOW_TRY(2),       //게임의 시도횟수를 출력하는 것을 저장한는 상수
  END_PROGRAM(3);       //게임이 끝나는 것을 저장하는 상수

  private final int idx;
  GameType(int idx) {
    this.idx = idx;
  }

  public GameType setGameType(int idx) throws GameTypeInputException {
    for (GameType gameType : GameType.values()) {
      if(idx == 0) {break;}
      if (gameType.idx == idx) {
        return gameType;
      }
    }
    throw new GameTypeInputException();
  }
}
