import java.util.Scanner;

public class Main {

  public static void main(String[] args) throws Exception {
    Scanner scanner = new Scanner(System.in);
    InputHandler inputHandler = new InputHandler(scanner);
    int len = 0;
    int[] input_Arr = new int[0];
    int game_type = 0;
    BaseballPlay baseballGame = new BaseballPlay();


    while(true) { //계속 게임을 위한 반복
      try {
        game_type = inputHandler.inputGameType();
        if(game_type == 1) {    //게임실행
          boolean flag = false;
          len = inputHandler.InputNumberSize();   //몇자리 수로 문제를 만들것인가
          baseballGame.initGame(len);             //len자리수로 문제생성
          while(!flag) { //정답을 맞출 때 까지 반복
            try {
              input_Arr = inputHandler.InputNumber(len);    //정답 인풋
            }catch (Exception e) {
              System.out.println(e.getMessage());
              continue;
            }
            flag = baseballGame.playGame(input_Arr);      //정답확인
          }

        } else if (game_type == 2) {
          baseballGame.show_Try();
        }else if(game_type == 3) {
          scanner.close();
          System.out.println("<숫자 야구 게임을 종료합니다>");
          break;
        }
      }catch (InputError e) {
        System.out.println(e.getMessage());
      }

    }
  }
}
