import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int[] gamedata = newGame();
        int tikudN = gamedata[0];
        int maxM = gamedata[1];
        boolean labi;
        while (tikudN > 0) {
            tikudN -= makeHumanMove(maxM, tikudN); // võtan kogu tikkude summast maha meetodi return väärtuse
            labi = check(tikudN);
            if (!labi) {
                System.out.println("Kas sa soovid veel mängida? Y or N");
                Scanner input = new Scanner(System.in);
                if (input.next().equals("Y")) {
                    gamedata = newGame(); // annan mängule uued väärtused meetodiga
                    tikudN = gamedata[0];
                    maxM = gamedata[1];
                } else {
                    System.out.println("Head aega!");
                    break; // lõpetan while loopi juhul kui ei saa inputiks Y tähte
                }
            }
            System.out.println("Mängus on " + tikudN + " tikku alles");
            tikudN -= makeComputerMove(maxM, tikudN); // võtan kogu tikkude summast maha meetodi return väärtuse
            labi = check(tikudN);
            if (!labi) {
                System.out.println("Kas sa soovid veel mängida? Y or N");
                Scanner input = new Scanner(System.in);
                if (input.next().equals("Y")) {
                    gamedata = newGame(); // annan mängule uued väärtused newGame() meetodiga
                    tikudN = gamedata[0];
                    maxM = gamedata[1];
                } else {
                    System.out.println("Head aega!");
                    break; // lõpetan while loopi juhul kui ei saa inputiks Y tähte
                }
            }
            System.out.println("Mängus on " + tikudN + " tikku alles");
        }

    }

    static boolean check(int tikudLaual) { // While loopist varasemalt välja saamise jaoks
        if (tikudLaual > 0) {
            return true;
        }
        return false;
    }

    static int[] newGame() { // Uue mängu alustamiseks on vaja mul saada kaht väärtust
        int[] start = new int[2]; // tegin massiivi, kus hoiustan kaht väärtust, sest Java ei lase kaht väärtust returnida
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("Mitu tikku on kokku mängus? (Täisarv)");
            if (input.hasNextInt()) {
                start[0] = input.nextInt();
            } else {
                input.next();
            }
        } while (start[0] < 1);
        do {
            System.out.println("Mis on maksimum tikkude arv (täisarv), mida võib ühe käigu korraga võtta laualt?");
            if (input.hasNextInt()) {
                start[1] = input.nextInt();
            } else  {
                input.next();
            }

        } while (start[1] < 1);

        return start;

    }

    static int makeHumanMove(int lubatud, int tikudLaual) {
        Scanner input = new Scanner(System.in);
        int arv = 0;
        do {
            System.out.println("Mitu tikku võtad? (Täisarv)");
            if (input.hasNextInt()) {
                arv = input.nextInt();
            } else {
                input.next();
            }
        } while ((arv < 1) || (arv > lubatud)); // küsin inimeselt väärtust nii kaua kuni saan sobiva väärtuse
        if (tikudLaual <= arv) { // kui inimese viimane arv on sama mis tikkude arv laual ta võidab
            System.out.println("Mängija võitis!");
            return arv;
        }
        return arv;
    }

    static int makeComputerMove(int lubatud, int tikudLaual) {
        int min = 1;
        int suvaline;
        if (lubatud >= tikudLaual) { // juhul kui on võimalus võtta kõik viimased
            System.out.println("Arvuti võttis laualt " + lubatud + " tikku.");
            System.out.println("Arvuti võitis");
            return lubatud;
        } else {
            // see on lahendus, mis ma leidsin internetist, kui tahan teatud vahemikust saada väärtust
            suvaline = min + (int)(Math.random() * ((lubatud - min) + 1));
            System.out.println("Arvuti võttis " + suvaline);
        }
        return suvaline;
    }
}