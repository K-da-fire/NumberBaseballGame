import UserException.*;
import java.util.Scanner;

public class InputHandler {
  Scanner scanner;

  public InputHandler() {
    this.scanner = new Scanner(System.in);
  }
  public int inputGameType() throws NumberFormatException {
    System.out.println("환영합니다! 원하시는 번호를 입력해주세요");
    System.out.println("1. 게임시작하기 2. 게임 기록 보기 3. 종료하기");
    String input = scanner.nextLine();
    return Integer.parseInt(input);
  }
  public int inputNumberSize() throws Exception {
    int len;
    System.out.print("설정하고자 하는 자리수를 입력하세요 : ");
    len = Integer.parseInt(scanner.nextLine());
    if (len < 3 || len > 5) {
      throw new LenInputException();
    }
    System.out.println(len + "자리수 난이도로 설정되었습니다.");
    return len;
  }
  public int[] inputNumber(int len) throws Exception {
    int[] input_Arr = new int[len];
    System.out.print("숫자를 입력하세요 : ");
    int input_Num = Integer.parseInt(scanner.nextLine());
    checkSize(input_Num,len);
    for (int i = len - 1; i >= 0; i--) {
      input_Arr[i] = input_Num % 10;
      input_Num /= 10;
    }
    checkDuplicate(input_Arr);
    return input_Arr;
  }
  public void checkDuplicate(int[] input_Arr) throws DuplicateInputException {
    int[] duplicate = new int[10];
    for(int i = 0; i < input_Arr.length; i++) {
      if(duplicate[input_Arr[i]] == 1) {
        throw new DuplicateInputException();
      }
      duplicate[input_Arr[i]] = 1;
    }
  }
  public void checkSize(int input_Num, int len) throws NumberInputException {
    if((int) Math.log10(input_Num)+1 != len) {
      throw new NumberInputException(len + "");
    }
  }
  public void closeScanner() {
    scanner.close();
  }
}
