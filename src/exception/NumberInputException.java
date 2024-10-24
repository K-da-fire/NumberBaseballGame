package exception;

public class NumberInputException extends Exception {
  public NumberInputException(String message) {
    super(message + "자리의 수를 입력해주세요\n");
  }
}
