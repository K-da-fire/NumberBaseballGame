package exception;

public class GameTypeInputException extends Exception {
  public GameTypeInputException() {
    super("게임타입은 1, 2, 3중에 입력해주세요\n");
  }
}
