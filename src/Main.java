
public class Main {

  public static void main(String[] args) {
    InputHandler inputHandler = new InputHandler();
    BaseballPlay baseballGame = new BaseballPlay();
    int game_type;

    while(true) { //계속 게임을 위한 반복
      try {
        game_type = inputHandler.inputGameType();
        if(game_type == 1) {    //게임실행
          baseballGame.loadGame();
        } else if (game_type == 2) {
          baseballGame.showTry();
        }else if(game_type == 3) {
          inputHandler.closeScanner();
          System.out.println("<숫자 야구 게임을 종료합니다>");
          break;
        }
      }catch (InputError e) {
        System.out.println(e.getMessage());
      }
//      catch(Exception e) {
//        System.out.println("게임이 비정상적으로 종료되었습니다.");
//        break;
//      }
    }
  }
}
