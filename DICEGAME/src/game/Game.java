package game;

import player.Player;
import exception.DiceCalculationException;
import exception.InvalidInputException;
import java.util.Random;
import java.util.Scanner;
import Util.DiceArt;
public class Game {
	private  Player player1 = new Player("플레이어1");
	private  Player player2 = new Player("플레이어2");
	private final Random random = new Random();
	private final char [ ] OPS = {'+', '-', '*', '/'};
	private final Scanner sc = new Scanner(System.in);
	
	private boolean isPlayer1Computer = false;
	private boolean isPlayer2Computer = false;
	
	//주사위 연산자 랜덤방셀
	private int rollDice() {return random.nextInt(6) + 1;}
	private char randomOperator() {return OPS[random.nextInt(4)];}
	
	//모드선택
	private void selectMode() {
		System.out.println("=======게임 모드 선택======");
		System.out.println("1. 플레이어1 vs 플레이어2");
		System.out.println("2. 플레이어1 vs 컴퓨터");
		System.out.println("3. 컴퓨터 vs 컴퓨터 (관전)");
		System.out.println("선택 : ");
		
		int choice = sc.nextInt();
		sc.nextLine(); //버퍼 비우기
		
		switch (choice) {
		case 1 -> {
			isPlayer1Computer = false;
			isPlayer2Computer = false;
			System.out.println("\n✅플레이어1 vs 플레이어2 모드 시작!\n");
		}
		case 2 -> {
			isPlayer1Computer = false;
			isPlayer2Computer = true;			
			player2 = new Player("컴퓨터");
			System.out.println("\n✅플레이어1 vs 컴퓨터 모드 시작!\n");
		}
		case 3 -> {
			isPlayer1Computer = true;
			isPlayer2Computer = true;
			player1 = new Player("컴퓨터1");
			player2 = new Player("컴퓨터2");
			System.out.println("\n✅컴퓨터 vs 컴퓨터 관전모드 시작\n");
		}
	default -> {
		System.out.println("잘못된 입력 -> 기본 1번 모드로 시작\n");
			}
		}
}
	
	//주사위 굴림연출--------------
	private void waitForRoll(String playerName) {
		System.out.println("\n" + playerName + "주사위를 굴리면 Enter입력...");
		sc.nextLine();
	}
	
	private void simulateRoll (String playerName) throws InterruptedException {
		System.out.println("\n" + playerName + "주사위 굴리는 중");
		for(int i = 0; i<3; i++) {
			Thread.sleep(500);
			System.out.println(" .");
		}
	}
	
	//플레이어 세팅(모드별 분기)-------
	
	private void setupPlayer (Player player, boolean isComputer) throws InterruptedException {
		if(!isComputer) {
			waitForRoll(player.getName());
		} else {
			simulateRoll(player.getName());
		}
		player.setDice(rollDice(), rollDice());
		player.setOperator(randomOperator());
	}
	
	//메인게임
	public void play() throws InterruptedException {
		selectMode() ;
		System.out.println("======2인 주사위 게임 시작=======\n");
		
		while (true) {
			//1.주사위 연산자 세팅
			setupPlayer(player1, isPlayer1Computer);
			setupPlayer(player2, isPlayer2Computer);
			
			printDiceResult();
			
		
			
			//2. 특수조건확인
			int p1s = player1.checkSpecial();
			int p2s = player2.checkSpecial();
			
			//6.6 크리티컬
			if (p1s ==1) {announceWinner(player1, "⭐크리티컬! 6,6 즉시 승리!"); break;}
			if (p2s ==1) {announceWinner(player2, "⭐크리티컬! 6,6 즉시 승리!"); break;}
			//1,1즉시 패배
			if (p1s ==-1) {announceLoser(player1, "☠️1,1즉시패배! -> 상대방승리"); break;}
			if (p2s ==-1) {announceLoser(player2, "☠️1,1 즉시패배! -> 상대방승리"); break;}
			
			//3연산(try-catch)
			if (!tryCompute(player1)) continue; //계산실패시 현재 계산 건너뛰고 다시 게임진행
			if (!tryCompute(player2)) continue;
			
			//4. 보너스적용
			applyBonusIfNeeded(player1, p1s);
			applyBonusIfNeeded(player2, p2s);
			
			
			
			
			printCalcResult();
			
			if(isPlayer1Computer && isPlayer2Computer) {
				continue;
			}
				while(true) {
					try {
						System.out.println("\n계속진행하려면 Enter");
						System.out.println("종료하려면 end입력");
						
						String input = sc.nextLine();
						validateInput(input);
						
						if(input.equalsIgnoreCase("end")) {
							System.out.println("\n게임 종료");
							return;
						}
						System.out.println("\n==================");
						break;
					} catch (InvalidInputException e) {
						System.out.println("\n[ERROR] : " + e.getMessage());
					}
				}				

		} 
			}		
			
		
	
	
	private void validateInput(String input) throws InvalidInputException {
				 if(input.isEmpty()) {
					 return;
				 }
					 
					 if(input.equalsIgnoreCase("end")) {
						 return;
					 }
						 					 
					 throw new InvalidInputException("잘못된 입력입니다, 다시 입력하세요.");
					 
				 }
			 
					
			
		
		
		//컴퓨터끼리의 승부는 무한반복End입력전까지
	 

	
//헬퍼메서드
private boolean tryCompute (Player p) {
	try {
		p.computeResult();
		return true;
	}catch (DiceCalculationException e) {
		System.out.println("[" + p.getName()+ "]"+e.getMessage() + " -> 라운드재시작\n");
		return false;
	}
}
			private void applyBonusIfNeeded(Player p, int special) {
				if(special ==0) {
					p.applyBonus();
					System.out.printf("★잭팟![%s] %d,%d 동일숫자 -> 결과에 +10 보너스! %n",
							p.getName(), p.getDice1(),p.getDice2());
				}
			}
			
			private void printDiceResult() {
				System.out.println("[주사위 결과]"); //플레이어의 주사위값과 연산자값을 출력하는 것 s는 문자열(string), d는 정수(int), c는 문자(char), n은 줄바꿈이다. 
			
			printPlayerBoxes();
			System.out.println();
			}
			private String[] createPlayerBox(Player p) {
				String[] d1 = DiceArt.getDiceArt(p.getDice1());
				String[] d2 = DiceArt.getDiceArt(p.getDice2());
				
				return new String[] {
						"┌──────────────┐",
		String.format(" | %-10s |", p.getName()),		
						d1[0] + " " + d2[0],
						d1[1] + " " + d2[1],
						d1[2] + " " + d2[2],
						d1[3] + " " + d2[3],
						d1[4] + " " + d2[4],
		String.format(" | 연산자 : (%c) |", 
								p.getOperator()),
						"└──────────────┘"
				
			};
			}
			
			private void printPlayerBoxes() {
				String[] p1Box = createPlayerBox(player1);
				String[] p2Box = createPlayerBox(player2);
				
				for (int i = 0;i<p1Box.length; i++) {
					System.out.println(p1Box[i] + "  " + p2Box[i]);
				}
			}
			
			private void printDiceSideBySide(int dice1, int dice2) {
				String[] d1 = DiceArt.getDiceArt(dice1);
				String[] d2 = DiceArt.getDiceArt(dice2);
				
				for (int i = 0; i < d1.length; i++) {
					System.out.println(d1[i] + " " + d2[i]);
				}
			}

			private void printCalcResult() {
				System.out.println("\n[계산결과]");
				System.out.printf(" %s : %s%n", player1.getName(), formatExpression(player1));
				System.out.printf(" %s : %s%n", player2.getName(), formatExpression(player2));
			}
			
			private String formatExpression (Player p) {
				int d1 = p.getDice1();
				int d2 = p.getDice2();
				char op = p.getOperator();
				int result = p.getResult();
				
				int big = Math.max(d1, d2);
				int small = Math.min(d1, d2);
				String opStr = String.valueOf(op);
				
				return switch (op) {
				case '-', '/' -> big + " " + op + " " + small + " = " + result;
				default-> d1 + " " + op + " " + d2 + " = " + result;
				};
				
			}
			
			private void announceWinner (Player winner, String reason) {
				if(!reason.isEmpty()) System.out.println(reason);
				
				
				System.out.println("===========================");
				System.out.println("🏆 최종승자 : " + winner.getName());
				System.out.println("===========================");
			}
			
			private void announceLoser (Player loser, String reason) {
				System.out.println(reason);
				Player winner = (loser ==player1) ? player2 : player1;
				announceWinner(winner, "");
			}
}
			
			//연산, 승패, 결과비교, 반복조건
			
	


