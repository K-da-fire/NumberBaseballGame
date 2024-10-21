import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    InputHandler inputHandler = new InputHandler();
    BaseballPlay baseballGame = new BaseballPlay();
    int game_type;
    int len;

    while(true) { //계속 게임을 위한 반복
      try {
        game_type = inputHandler.inputGameType();
        if(game_type == 1) {    //게임실행
          len = inputHandler.InputNumberSize();   //몇자리 수로 문제를 만들것인가
          baseballGame.initGame(len);             //len자리수로 문제생성
          baseballGame.playGame(len);             //정답확인
        } else if (game_type == 2) {
          baseballGame.show_Try();
        }else if(game_type == 3) {
          inputHandler.closeScanner();
          System.out.println("<숫자 야구 게임을 종료합니다>");
          break;
        }
      }catch (InputError e) {
        System.out.println(e.getMessage());
      }
    }
  }
}
