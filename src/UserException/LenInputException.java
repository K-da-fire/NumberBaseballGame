package UserException;

public class LenInputException extends Exception {
  public LenInputException() {
    super("3~5사이의 정수를 입력해주세요\n");
  }
}
