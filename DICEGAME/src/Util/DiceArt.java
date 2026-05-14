package Util;

public class DiceArt {

    public static String[] getDiceArt(int dice) {

        return switch (dice) {

            case 1 -> new String[] {

                    "┌─────┐",
                    "│     │",
                    "│  ●  │",
                    "│     │",
                    "└─────┘"
            };

            case 2 -> new String[] {

                    "┌─────┐",
                    "│●    │",
                    "│     │",
                    "│    ●│",
                    "└─────┘"
            };

            case 3 -> new String[] {

                    "┌─────┐",
                    "│  ●  │",
                    "│  ●  │",
                    "│  ●  │",
                    "└─────┘"
            };

            case 4 -> new String[] {

                    "┌─────┐",
                    "│●   ●│",
                    "│     │",
                    "│●   ●│",
                    "└─────┘"
            };

            case 5 -> new String[] {

                    "┌─────┐",
                    "│●   ●│",
                    "│  ●  │",
                    "│●   ●│",
                    "└─────┘"
            };

            case 6 -> new String[] {

                    "┌─────┐",
                    "│●   ●│",
                    "│●   ●│",
                    "│●   ●│",
                    "└─────┘"
            };

            default -> new String[] {"ERROR"};
        };
    }
}