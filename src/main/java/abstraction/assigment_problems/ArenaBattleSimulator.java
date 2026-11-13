package abstraction.assigment_problems;

public class ArenaBattleSimulator {

    public interface Attackable {
        String attack();
        String attack(String weaponName);
    }

    public interface Defendable {
        String defend();
    }

    public static abstract class GameCharacter {
        private static int counter = 0;
        private final String characterId;

        public GameCharacter() {
            counter++;
            this.characterId = "CHAR-" + counter;
        }

        public String getCharacterId() {
            return characterId;
        }

        public abstract String getSpecialMove();
    }

    public static class Warrior extends GameCharacter implements Attackable, Defendable {
        private String name;

        public Warrior(String name) {
            super();
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public String attack() {
            return name + " strikes with a blade";
        }

        @Override
        public String attack(String weaponName) {
            if (weaponName == null || weaponName.isEmpty()) {
                return attack();
            }
            char first = Character.toUpperCase(weaponName.charAt(0));
            String article = (first == 'A' || first == 'E' || first == 'I' || first == 'O' || first == 'U') ? "an" : "a";
            return name + " strikes with " + article + " " + weaponName;
        }

        @Override
        public String defend() {
            return name + " raises a shield";
        }

        @Override
        public String getSpecialMove() {
            return name + " unleashes Whirlwind Slash";
        }
    }

    public static class Trap implements Defendable {
        private String trapType;

        public Trap(String trapType) {
            this.trapType = trapType;
        }

        public String getTrapType() {
            return trapType;
        }

        @Override
        public String defend() {
            return trapType + " triggers automatically";
        }
    }

    public static void resolveDefense(Defendable[] combatants) {
        if (combatants == null) {
            return;
        }
        for (Defendable c : combatants) {
            if (c != null) {
                System.out.println(c.defend());
            }
        }
    }

    public static void main(String[] args) {
        Warrior w = new Warrior("Kael");
        System.out.println(w.attack());
        System.out.println(w.attack("Iron Sword"));
        System.out.println(w.defend());
        System.out.println(w.getSpecialMove());

        Trap t = new Trap("Spike Pit");
        System.out.println(t.defend());

        resolveDefense(new Defendable[]{w, t});
    }
}