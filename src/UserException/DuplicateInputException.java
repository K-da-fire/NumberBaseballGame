package UserException;

public class DuplicateInputException extends Exception {
  public DuplicateInputException() {
    super("중복된 숫자는 입력할 수 없습니다\n");
  }
}
