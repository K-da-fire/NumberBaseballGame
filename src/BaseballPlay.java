import java.lang.reflect.Array;
import java.util.ArrayList;
import javax.swing.JFrame;

public class BaseballPlay extends BaseballGame {
  ArrayList<Integer> play_Num;   //게임을 실행한 횟수

  public BaseballPlay() {
    super();
    play_Num = new ArrayList<>();
  }

  public void initGame(int len){
    setCount_Try(0);
    make_Question(len);
    getanswer();
  }

  public void show_Try(){
    for(int i = 0;i<play_Num.size();i++){
      System.out.println(i+1 + "번째 게임 : 시도 횟수 - " + play_Num.get(i));
    }
  }

  @Override
  public boolean playGame(int[] input_Arr){
    int strick = checkStick(input_Arr);
    int ball = checkBall(input_Arr);
    setCount_Try(getCountTry()+1);
    if(strick == 0 && ball == 0){
      System.out.println("아웃");
    }else if(strick == input_Arr.length){
      System.out.println(getCountTry()+"회 시도");
      System.out.println("정답입니다!");
      play_Num.add(getCountTry());
      return true;
    }
    if(strick != 0) {
      System.out.print(strick + "스트라이크 ");
    }
    if(ball != 0) {
      System.out.print(ball + "볼 ");
    }
    System.out.println();
    return false;
  }
  @Override
  protected int checkStick(int[] input_Arr){
    int count = 0;
    for(int i = 0; i < input_Arr.length; i++){
      if(input_Arr[i] == getAnswer(i)) count++;
    }
    return count;
  }
  @Override
  protected int checkBall(int[] input_Arr){
    int count = 0;
    for(int i = 0; i < input_Arr.length; i++){
      for(int j = 0; j < input_Arr.length; j++){
        if(i == j) continue;
        if(input_Arr[i] == getAnswer(j)) count++;
      }
    }
    return count;
  }
}
