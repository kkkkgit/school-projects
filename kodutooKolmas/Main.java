import java.util.*;

public class Main {

    public static void main(String[] args) {
        char mostFrequent = leiaKõigeSagedasem("To be or not to be... that is the question!");
        System.out.println(mostFrequent);
        String result1 = eemaldaKõigeSagedasem("To be or not to be... that is the question!", mostFrequent);
        System.out.println(result1);

        mostFrequent = leiaKõigeSagedasem("trrkrt kpsstVVrqqKKt !!!!!!!!!!!!");
        System.out.println(mostFrequent);
        String result2 = eemaldaKõigeSagedasem("trrkrt kpsstVVrqqKKt !!!!!!!!!!!!", mostFrequent);
        System.out.println(result2);

        mostFrequent = leiaKõigeSagedasem("621 arvutit on mu koige ttttt!!! 22 ?? ...");
        System.out.println(mostFrequent);
        String result3 = eemaldaKõigeSagedasem("621 arvutit on mu koige ttttt!!! 22 ?? ...", mostFrequent);
        System.out.println(result3);
    }

    static char leiaKõigeSagedasem(String tekst) {

        if (!tekst.matches(".*[a-zA-Z].*")) {
            System.out.println("Tekstis pole tähti");
            return '-';
        }
        // Loon HashMap tabeli, et talletada kaht väärtust koos: täht ja mitu korda see esineb
        HashMap<Character, Integer> tabel = new HashMap<Character, Integer>();

        String vaikseks = tekst.toLowerCase();
        // Lisan stringi array'sse, tahtede haaval ja samal ajal votan ara koik elemendid, mis ei ole tahed voi numbrid
        char[] charArray = vaikseks
                .replaceAll("[^a-zA-Z]+", "")
                .toCharArray();

        System.out.println(charArray);
        // For each loop
        for (char c : charArray) {
            // .containsKey tagastab true/false ehk on boolean
            if (tabel.containsKey(c)) {
                // lisan HashMapi .put commandiga, mis votab esimese argumendinda key ja teisena value sellele
                // HashMap .get command votab key value ja laseb sellele +1 liita
                tabel.put(c, tabel.get(c) + 1);
            } else {
                // kui votit ei ole siis ta loob selle ja annab valueks 1
                tabel.put(c, 1);
            }
        }

        int maxCount = 0;
        char maxChar = 0;
        List<Character> result = new ArrayList<>();

        System.out.println(tabel);
        // For each loop, koos Map.Entry'ga mis on loodud key:value paariga, millega saab kaia labi HashMapi keyd ja valued
        for (Map.Entry<Character, Integer> entry : tabel.entrySet()) {
            // Kui entry value peaks olema suurem kui eelmine salvestatud maxCount, siis lisan maxCount ja maxChar uued vaartused
            // eemaldan vanad vaartused ArrayListist, ning lisan ArrayListi uue key
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                maxChar = entry.getKey();
                result.clear();
                result.add(maxChar);
            } else if (entry.getValue() == maxCount) {
                // kui value on sama mis maxCount value, siis voin lisada ka uue key ArrayListi
                result.add(entry.getKey());
            }
        }
        // kui on mitu tahte, siis sortin need ja valjastan esimese ArrayListi value
        result.sort(null);
        maxChar = result.getFirst();
        return maxChar;
    }

    static String eemaldaKõigeSagedasem(String tekst, char mostFrequent) {
        // eemaldan tekstist koik tahed mis on mostFrequent'iga sisse antud. ? utleb et vahet pole kas suur voi vaike taht
        tekst = tekst.replaceAll("(?i)" + mostFrequent, "");
        tekst = tekst.replaceAll("[^a-zA-Z0-9\\s]", ".");
        return tekst;
    }
}

