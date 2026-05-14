package game;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			Game game = new Game();
			game.play();
			
			System.out.print("\n다시 하겠습니까? (y/n) : ");
			String input = sc.nextLine();
			
			if (input.equalsIgnoreCase("n")) {
				System.out.println("게임을 종료합니다.");
				break;
			}
			System.out.println("\n" + "=".repeat(30) + "\n");
		}
	}

}
