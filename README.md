# 숫자야구게임 구현
## 1. 규칙

  -야구게임 문제는 1 ~ 9에서 중복되지 않는 숫자입니다.
  -3 ~ 5까지의 난이도를 사용자가 입력하여 난이도를 조절할 수 있습니다.
  -시도횟수보기는 지금까지의 게임을 진행한 횟수와 각 게임마다 몇회를 시도했는지 출력해줍니다.

## 2. 입력방법

  -모든 입력은 숫자로 진행됩니다.
  -시작할 때, 원하는 기능을 선택하는 것은 1 ~ 3까지의 정수를 입력하여야합니다.
  -자리수(난이도)는 3 ~ 5까지의 정수를 입력하여야합니다.
  -입력되는 정답에는 중복된 숫자가 없어야하고, 난이도에 맞는 숫자가 입력되어야합니다.

  ## 3. 주요구성
  ### BaseballPlay클래스
  
    -게임의 정보를 저장하고, 게임을 실행하는 클래스
    -예외처리를 하는 메서드 구현
  
  ### InputHandler클래스
  
    -입력값을 받는 클래스
    -입력값에 예외가 발생하면 발생한 예외에 맞는 메시지를 던져준다.

  ### enums패키지
  
    -Stage : 게임 진행상황을 저장합니다.
    -PlayerStatus : 게임 결과를 저장합니다.
    -GameType : 선택한 게임타입을 저장합니다.
                이때 GameType은 setGameType을 이용하여 인덱스에 맞는 enum을 반환합니다.

  ### exception패키지
  
    -DuplicateInputException : 사용자가 중복값을 입력하면, 예외메시지를 출력해준다.
    -GameTypeInpputException : 옳바르지 않는 게임타입이 입력된다면 예외메시지를 출력해준다. (1 ~ 3이외의 입력)
    -LenInputException : 문제의 길이(난이도)가 잘못 입력되었을 때, 예외메시지를 출력해준다. (3 ~ 5이외의 입력)
    -NumberInputException : 사용자가 설정한 난이도와 다른 길이의 숫자를 입력했을 때, 예외메시지를 출력해준다. (3 ~ 5자리의 수)
