🎲DICEGAME


🎲게임기획
2인 주사위 연산 게임은 Java 기반으로 개발한 콘솔형 게임 프로젝트로, 
단순한 주사위 승부 방식에서 벗어나 랜덤 연산자와 특수 규칙을 결합하여 긴장감과 재미 요소를 강화한 게임이다.


🎲게임규칙
게임은 총 4개의 주사위를 생성하여 플레이어마다 2개씩 분배하며,각 주사위는 1부터 6 사이의 랜덤 값을 가진다. 
이후 각 플레이어에게 사칙연산 연산자(+,-,*,/) 중 하나가 랜덤으로 부여되며, 플레이어는 자신의 주사위 값과 연산자를 이용하여 계산 결과를 도출한다.
특수한 규칙을 도입하여 게임에 몰입감을 주었다. 
- 주사위 두 개가 모두 6인 경우 → 즉시 승리(Critical)
- 주사위 두 개가 모두 1인 경우 → 즉시 패배(Fail)
- 2,2 ~ 5,5 동일한 숫자가 나온 경우 → 결과값 +10 보너스 적용

최종적으로 두 플레이어의 계산 결과를 비교하여 높은 점수를 획득한 플레이어가 승리하며, 결과값이 동일할 경우 무승부 처리 후 승자가 나올 때까지 게임을 반복 진행하도록 구현하였다.


🛠️사용 기술
Java
Object-Oriented Programming (OOP)
Interface
Custom Exception
Random
Switch Expression
ASCII Console UI


🏢프로젝트 구조
src
├── main
│   └── Main.java
├── game
│   └── Game.java
├── player
│   └── Player.java
├── interfaces
│   └── Calculatable.java
├── exception
│   └── DiceCalculationException.java
└── util
    └── DiceArt.java



    



