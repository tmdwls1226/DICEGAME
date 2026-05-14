package player;

import interfaces.Calculatable;
import exception.DiceCalculationException;

public class Player implements Calculatable {
	
	private String name;
	private int dice1, dice2;
	private char operator;
	private int result;
	
	public Player (String name) {
		this.name = name;
	}
	
	//------Calculatable구현-------
	@Override
	public int calculate (int dice1, int dice2, char operator) throws DiceCalculationException {
		return switch (operator) {
		case '+' -> dice1 + dice2;
		case '-' -> Math.max(dice1, dice2) - Math.min(dice1, dice2);
		case '*' -> dice1 * dice2;
		case '/' -> {
			int max = Math.max(dice1, dice2) ;
			int min = Math.min(dice1, dice2);
			if(min == 0) throw new DiceCalculationException("연산실패! 다시계산하시오");
			yield  max/min;
		}
		default -> throw new DiceCalculationException("연산실패! 다시계산하시오");
		};
	}

	//try-catch는 game에서 감싸므로 여기선 예외를 위로 던짐
	public void computeResult() throws DiceCalculationException {
		result = calculate(dice1, dice2, operator);
	}
	
	
	//특수조건 확인
	//반환값 : 	1 -> 6,6 즉시 승리
	//			-1 -> 1,1 즉시 패배
	//			0	-> 2,2 ~ 5,5 보너스
	//			-2 -> 일반
	public int checkSpecial() {
		if (dice1 ==6 && dice2 ==6) return 1;
		if(dice1 ==1 && dice2 ==1) return -1;
		if(dice1 == dice2) return 0;
		return -2;
	}
	
	public void applyBonus () {
		result += 10;
		
	}
	//Getter/Setter
	public String getName() {return name;}
	public int getDice1() {return dice1;}
	public int getDice2() {return dice2;}
	public char getOperator() {return operator;}
	public int getResult() {return result;}
	
	public void setDice(int d1, int d2) {dice1 = d1; dice2 = d2;}
	public void setOperator(char op) {operator = op;}
	
}
