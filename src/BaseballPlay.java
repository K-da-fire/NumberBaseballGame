import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import Enum.*;

/**
 * 메서드는 하나에 한가지 일만
 * -> 쉬운함수 여러개
 *
 */

public class BaseballPlay {
  private ArrayList<Integer> answer;  //정답 배열
  private final Random rand;                //랜덤값에 저장
  private final InputHandler inputHandler;
  private final ArrayList<Integer> playNum;//게임을 실행한 횟수
  private Set<Integer> set;
  private int countTry;                    //도전 횟수
  private int questionLen;
  private Stage stage;
  private PlayerStatus playerStatus;

  public BaseballPlay() {
    answer = new ArrayList<>();
    rand = new Random();
    inputHandler = new InputHandler();
    countTry = 0;
    playNum = new ArrayList<>();
    set = new HashSet<>();
  }

  public void gameHandler(){
    /**
     * 만약에 this.stage가 0이면 initGame startGame
     * stage != 0 이면 loadGame
     */
//    loadGame();
  }

  public void loadGame() throws InputError{
    boolean flag = false;
    initGame();
    while(!flag) {
      try {
        switch (this.stage) {
          case StartGame:
            startGame();
            makeQuestion(this.questionLen);
            break;
          case SelectNumberSize:
            nowGaming();
            break;
          case InputNumber:
            flag = true;
            break;
        }
      }catch (InputError e) {
        System.out.println(e.getMessage());
      }
    }
  }

  public void initGame()  {
    this.countTry = 0;
    this.questionLen = -1;
    this.answer.clear();
    this.stage = Stage.StartGame;
  }
  public void startGame() throws InputError{
    System.out.println("< 게임을 시작합니다 >");
    this.questionLen = inputHandler.inputNumberSize();
    this.stage = Stage.SelectNumberSize;    //사이즈 입력 성공적
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

  public void showTry(){
    System.out.println("< 게임 기록 보기 >");
    for(int i = 0;i< playNum.size();i++){
      System.out.println(i+1 + "번째 게임 : 시도 횟수 - " + playNum.get(i));
    }
    System.out.println();
  }

  public void nowGaming() throws InputError {
//    System.out.println("noGame");
    int[] inputArr;
    boolean flag = false;
    while(!flag) {
      this.stage = Stage.SelectNumberSize;
      inputArr = inputHandler.inputNumber(questionLen);
      this.stage = Stage.InputNumber;   //답안 입력 성공적
      int strike = checkStrike(inputArr);
      int ball = checkBall(inputArr);
      countTry++;
      flag = updateStatus(strike, ball, inputArr.length);
      showResult(strike, ball);
      System.out.println();
    }
  }

  /**
   * showResult
   * 결과에 따라서 결과를 출력해주는 함수
   * stage내에서 input -> checkAnswer -> showResult
   */
  /**
   * 둘로 나눔
   * 1. 업데이트 스테이터스
   * 2. showResult
   */
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
