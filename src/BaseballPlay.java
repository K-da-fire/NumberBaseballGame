import UserException.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import Enum.*;

public class BaseballPlay {
  private final ArrayList<Integer> answer;  //정답 배열
  private final InputHandler inputHandler;
  private final ArrayList<Integer> playNum;//게임을 실행한 횟수
  private int countTry;                    //도전 횟수
  private int questionLen;
  private Stage stage;
  private PlayerStatus playerStatus;
  private GameType gameType;

  public BaseballPlay() {
    answer = new ArrayList<>();
    inputHandler = new InputHandler();
    countTry = 0;
    playNum = new ArrayList<>();
    stage = Stage.StartGame;
    gameType = GameType.StartProgram;
  }

  public void playBaseBall(){
    boolean flag = false;
    while(!flag) {
      try {
        switch (gameType) {    //게임실행
          case GameType.StartProgram:
            gameType = GameType.setGameType(inputHandler.inputGameType()); //UserException.GameTypeInputException
            break;
          case GameType.PlayGame:
            loadGame();                               //UserException.UserInputException, NumberFormatException
            gameType = GameType.StartProgram;
            break;
          case GameType.ShowTry:
            showTry();
            gameType = GameType.StartProgram;
            break;
          case GameType.EndGame:
            inputHandler.closeScanner();
            System.out.println("<숫자 야구 게임을 종료합니다>");
            flag = true;
        }
      } catch (GameTypeInputException | DuplicateInputException | NumberInputException |
               LenInputException e){
        System.out.println(e.getMessage());
      } catch (NumberFormatException e) {
        System.out.println("숫자를 입력해주세요\n");
      } catch (Exception e) {
        System.out.println("예기치않은 오류입니다.");
      }
    }
  }
  public void loadGame() throws Exception {
    boolean flag = false;
    if(stage == Stage.EndGame)
      stage = Stage.StartGame;
    while (!flag) {
      switch (this.stage) {
        case StartGame:
          startGame();
          makeQuestion(this.questionLen);
          break;
        case NowGaming:
          nowGaming();
          break;
        case EndGame:
          flag = true;
          break;
        case EndProgram:
          break;
      }
    }
  }
  public void initGame()  {
    this.countTry = 0;
    this.questionLen = -1;
    this.answer.clear();
    this.stage = Stage.StartGame;
  }
  public void startGame() throws Exception{
    initGame();
    System.out.println("< 게임을 시작합니다 >");
    this.questionLen = inputHandler.inputNumberSize();
    this.stage = Stage.NowGaming;
  }
  private void makeQuestion(int questionLen) {
    List<Integer> arrList = Arrays.asList(1, 2, 3, 4, 5, 6, 7,8, 9);
    Collections.shuffle(arrList);
    for(int i = 0; i < questionLen; i++) {
      answer.add(arrList.get(i));
    }
    //////////////////////////////////////////////////////////
    answer.stream()
        .forEach(System.out::print);
    System.out.println();
    /////////////////////////////////////////////////////////
  }
  public void nowGaming() throws Exception {
    int[] inputArr;
    boolean flag = false;
    while(!flag) {
      this.stage = Stage.NowGaming;
      inputArr = inputHandler.inputNumber(questionLen);
      this.stage = Stage.EndGame;   //답안 입력 성공적
      int strike = checkStrike(inputArr);
      int ball = checkBall(inputArr);
      countTry++;
      flag = updateStatus(strike, ball, inputArr.length);
      showResult(strike, ball);
      System.out.println();
    }
  }
  public void showTry(){
    if(playNum.isEmpty()) {
      System.out.println("게임을 시도한 적이 없습니다\n");
      return;
    }
    System.out.println("< 게임 기록 보기 >");
    for(int i = 0;i< playNum.size();i++){
      System.out.println(i+1 + "번째 게임 : 시도 횟수 - " + playNum.get(i));
    }
    System.out.println();
  }
  private boolean updateStatus(int strike, int ball, int inputLen){
    if (strike == 0 && ball == 0) {
      this.playerStatus = PlayerStatus.Out;
    } else if (strike == inputLen) {
      this.playerStatus = PlayerStatus.Win;
      playNum.add(countTry);
      return true;
    }else {
      this.playerStatus = PlayerStatus.Playing;
    }
    return false;
  }
  private void showResult(int strike, int ball){
    switch (playerStatus) {
      case Out:
        System.out.print("아웃");
        break;
      case Win:
        System.out.println(countTry + "회 시도");
        System.out.println("정답입니다!");
        break;
      case Playing:
        if(strike != 0) System.out.print(strike + "스트라이크 ");
        if(ball != 0) System.out.print(ball + "볼");
        break;
    }
  }
  private int checkStrike(int[] inputArr){
    int count = 0;
    for(int i = 0; i < inputArr.length; i++){
      if(inputArr[i] == answer.get(i)) count++;
    }
    return count;
  }
  private int checkBall(int[] inputArr){
    int count = 0;
    for(int i = 0; i < inputArr.length; i++){
      for(int j = 0; j < inputArr.length; j++){
        if(i == j) continue;
        if(inputArr[i] == answer.get(j)) count++;
      }
    }
    return count;
  }
}
