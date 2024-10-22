import java.util.Scanner;
import java.util.regex.Pattern;
import Enum.Stage;

public class InputHandler {
  Scanner scanner;
  private Stage stage;

  public InputHandler() {
    this.scanner = new Scanner(System.in);
  }

  public void checkInput(){
    /**
     * stage값 또는 enum에 맞게 핸들러를 호출
     *
     * 이때 오류가 나면
     * InputHandler -> BaseballPlay -> InpuHandler?
     */
    switch (this.stage) {
      case StartGame:
        break;
      case SelectNumberSize:
        break;
      case InputNumber:
        break;
    }
  }

  public int inputGameType() throws InputError{
    System.out.println("환영합니다! 원하시는 번호를 입력해주세요");
    System.out.println("1. 게임시작하기 2. 게임 기록 보기 3. 종료하기");
    String input = scanner.nextLine();
    if(!input.equals("1") && !input.equals("2") && !input.equals("3")) {
      throw new InputError();
    }
    System.out.println();
    return Integer.parseInt(input);
  }

  public int inputNumberSize() throws InputError{
    int len = 0;
//    this.stage = Stage.SelectNumberSize;
    System.out.print("설정하고자 하는 자리수를 입력하세요 : ");
      try {
//        String tmpLen = scanner.nextLine();
        len = Integer.parseInt(scanner.nextLine());
        if (len < 3 || len > 5) {
          throw new InputError();
        }
      } catch (Exception e) {
        throw new InputError();
      }
    System.out.println(len + "자리수 난이도로 설정되었습니다.");
    return len;
  }

  public int[] inputNumber(int len) throws InputError {
    int[] input_Arr = new int[len];
    System.out.print("숫자를 입력하세요 : ");
    String str = scanner.nextLine();
    if(!Pattern.matches("^[0-9]*$", str)) {
      throw new InputError();
    }
    try {
      int input_Num = Integer.parseInt(str);
      checkSize(input_Num,len);
      for (int i = len - 1; i >= 0; i--) {
        input_Arr[i] = input_Num % 10;
        input_Num /= 10;
      }
    }catch (Exception e) {
      throw new InputError();
    }
    checkDuplicate(input_Arr);
    return input_Arr;
  }

  public void checkDuplicate(int[] input_Arr) throws InputError {
    int[] duplicate = new int[10];
    for(int i = 0; i < input_Arr.length; i++) {
      if(duplicate[input_Arr[i]] == 1) {
        throw new InputError();
      }
      duplicate[input_Arr[i]] = 1;
    }
  }
  public void checkSize(int input_Num, int len) throws InputError {
    if((int) Math.log10(input_Num)+1 != len) {
      throw new InputError();
    }
  }
  public void closeScanner() {
    scanner.close();
  }
}
