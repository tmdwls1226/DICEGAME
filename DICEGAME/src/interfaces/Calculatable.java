package interfaces;

import exception.DiceCalculationException;
public interface Calculatable {
	int calculate (int dice1, int dice2, char operator) throws DiceCalculationException;
	//dice1, dice2, operator의 calculate가 잘못되면 예외 diceCalculationException으로 던지기

}
