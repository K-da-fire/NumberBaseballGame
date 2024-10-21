import java.util.ArrayList;
import java.util.Random;

public class BaseballPlay {
  private final ArrayList<Integer> answer;  //정답 배열
  private final Random rand;                //랜덤값에 저장
  private final InputHandler inputHandler;
  private int count_Try;                    //도전 횟수
  ArrayList<Integer> play_Num;   //게임을 실행한 횟수

  public BaseballPlay() {
    answer = new ArrayList<>();
    rand = new Random();
    inputHandler = new InputHandler();
    count_Try = 0;
    play_Num = new ArrayList<>();
  }

  public void initGame(int len){
    count_Try = 0;
    make_Question(len);
    //////////////////////////////////////////////////////////
    answer.stream()
            .forEach(System.out::print);
    /////////////////////////////////////////////////////////
    System.out.println();
    System.out.println("< 게임을 시작합니다 >");
  }

  private void make_Question(int len) {
    answer.clear();
    int[] arr = new int[10];
    for (int i = 0; i < len; i++) {
      int tmp = rand.nextInt(1, 10);
      if(arr[tmp] == 1) i--;
      else answer.add(tmp);
      arr[tmp] = 1;
    }
  }

  public void show_Try(){
    System.out.println("< 게임 기록 보기 >");
    for(int i = 0;i<play_Num.size();i++){
      System.out.println(i+1 + "번째 게임 : 시도 횟수 - " + play_Num.get(i));
    }
  }

  public void playGame(int len) throws InputError {
    int[] input_Arr;
    boolean flag = false;
    while(!flag) {
      try {
        input_Arr = inputHandler.InputNumber(len);
        int strick = checkStick(input_Arr);
        int ball = checkBall(input_Arr);
        count_Try++;
        flag = checkAnswer(strick, ball, input_Arr.length);
        System.out.println("\n");
      }catch (Exception e){
        System.out.println(e.getMessage());
      }
    }
  }
  private boolean checkAnswer(int strick, int ball, int inputLen){
    if (strick == 0 && ball == 0) {
      System.out.print("아웃");
    } else if (strick == inputLen) {
      System.out.println(count_Try + "회 시도");
      System.out.println("정답입니다!");
      System.out.println();
      play_Num.add(count_Try);
      return true;
    }
    return false;
  }
  private int checkStick(int[] input_Arr){
    int count = 0;
    for(int i = 0; i < input_Arr.length; i++){
      if(input_Arr[i] == answer.get(i)) count++;
    }
    if(count != 0) {
      System.out.print(count + "스트라이크 ");
    }
    return count;
  }
  private int checkBall(int[] input_Arr){
    int count = 0;
    for(int i = 0; i < input_Arr.length; i++){
      for(int j = 0; j < input_Arr.length; j++){
        if(i == j) continue;
        if(input_Arr[i] == answer.get(j)) count++;
      }
    }
    if(count != 0) {
      System.out.print(count + "볼 ");
    }

    return count;
  }
}
