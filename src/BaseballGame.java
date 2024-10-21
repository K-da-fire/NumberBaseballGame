import java.util.ArrayList;
import java.util.Random;

public abstract class BaseballGame {
  private final ArrayList<Integer> answer;  //정답 배열
  private final Random rand;                //랜덤값에 저장
  private int count_Try;                    //도전 횟수

  public BaseballGame() {
    answer = new ArrayList<>();
    rand = new Random();
    count_Try = 0;
  }

  protected void make_Question(int len) {
    answer.clear();
    int[] arr = new int[10];
    for (int i = 0; i < len; i++) {
      int tmp = rand.nextInt(1, 10);
      if(arr[tmp] == 1) i--;
      else answer.add(tmp);
      arr[tmp] = 1;
    }
  }

  public void getanswer() {
    System.out.println(answer);
  }
  public int getAnswer(int idx) {
    return answer.get(idx);
  }
  public void setCount_Try(int count_try) {
    count_Try = count_try;
  }
  public int getCountTry() {
    return count_Try;
  }

  public abstract boolean playGame(int[] input_Arr);
  protected abstract int checkStick(int[] input_Arr);
  protected abstract int checkBall(int[] input_Arr);
}
