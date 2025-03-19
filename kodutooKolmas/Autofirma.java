import java.io.*;
import java.util.Scanner;

public class Autofirma {

    public static void main(String[] args) {
        eemaldaRida("src/andmed.txt", "Volvo");
        convert("src/andmed.txt", "src/oigesti.txt", "src/valesti.txt");
    }

    static void convert(String sisendfail, String valjund1, String valjund2) {
        // Määran valuedeks null, sest plaanin neid kasutada, aga praegu ei ole neid vaja
        BufferedReader fileReader = null;
        BufferedWriter fileWriterR = null;
        BufferedWriter fileWriterW = null;

        try {
            File inputFile = new File(sisendfail);
            if (!inputFile.exists()) {
                throw new FileNotFoundException("Sisendfaili ei leitud: " + sisendfail);
            }

            // nüüd on tarvis neid kasutada
            // faili lugeja
            fileReader = new BufferedReader(new FileReader(sisendfail));
            // fail, mis on õigesti kirjutatud
            fileWriterR = new BufferedWriter(new FileWriter(valjund1, false));
            // fail, mis on valesti kirjutatud
            fileWriterW = new BufferedWriter(new FileWriter(valjund2, false));

            String line;
            while ((line = fileReader.readLine()) != null) {
                // kontrollin kas vastav rida on oigesti kirjutatud
                boolean valid = isValid(line);

                if (valid) {
                    String processedLine = isProcessedLine(line);
                    fileWriterR.write(processedLine);
                    fileWriterR.newLine();
                } else {
                    fileWriterW.write(line);
                    fileWriterW.newLine();
                }

            }

        } catch (FileNotFoundException e) {
            System.err.println("Viga faili avamisel: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Viga failide töötlemisel: " + e.getMessage());
        } finally {
            // lopetan nende kasutamise ja kontrollin kas moni neist tootab veel, kui jah siis panen kinni
            try {
                if (fileReader != null) fileReader.close();
                if (fileWriterR != null) fileWriterR.close();
                if (fileWriterW != null) fileWriterW.close();
            } catch (IOException e) {
                System.err.println("Viga faili sulgemisel: " + e.getMessage());
            }
        }
    }

    public static boolean isValid(String line) {
        // kontrollin et sisestatud oleks 4 osaliselt
        String[] stringParts = line.split("\\|");
        if (stringParts.length != 4) {
            return false;
        }

        try {
            // kontrollin kas esimesed kolm valuet on numbrid
            String autoNR = stringParts[0].substring(0,3);
            boolean autoNROige = autoNR.matches("[0-9]+");
            if (!autoNROige) {
                return false;
            }
            // kontrollin kas kolm viimast valuet on tahed
            String autoT =  stringParts[0].substring(3);
            boolean autoTOige = autoT.matches("[a-zA-Z]+");
            if (!autoTOige) {
                return false;
            }
            // kontrollin kas aasta on uuem kui 1900 aga mitte uuem kui 2025
            // hind ei saa olla vaiksem kui null lisaks kontrollin kas need on numbrid ka
            int year = Integer.parseInt(stringParts[2]);
            double price = Double.parseDouble(stringParts[3]);

            if (year < 1900 || year >= 2025 || price < 0) {
                return false;
            }

            return true;

        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            return false;
        } catch (Exception e) {
            System.out.println("Vale sisestus: " + e.getMessage());
            return false;
        }
    }

    public static String isProcessedLine(String line) {
        String[] stringParts = line.split("\\|");

        return "Auto nr: " +
                stringParts[0] +
                "\n" +
                "Mark: " +
                stringParts[1] +
                "\n" +
                "Väljalaskeaasta: " +
                stringParts[2] +
                "\n" +
                "Hind: " +
                stringParts[3];
    }

    public static void eemaldaRida(String sisendfail, String otsitav) {
        File inputFile = new File(sisendfail);
        File tempFile = new File(sisendfail + ".tmp");

        BufferedReader fileReader = null;
        BufferedWriter fileWriter = null;
        boolean lineRemoved = false;

        try {
            fileReader = new BufferedReader(new FileReader(inputFile));
            fileWriter = new BufferedWriter(new FileWriter(tempFile));

            String currentLine;
            
            while ((currentLine = fileReader.readLine()) != null) {
                if (currentLine.contains(otsitav)) {
                    lineRemoved = true;
                    System.out.println("Eemaldatud rida: " + otsitav);
                    continue; // jatab vahele selle rea, et ei kirjuta seda uude faili
                }

                fileWriter.write(currentLine);
                fileWriter.newLine();
            }
            if (!lineRemoved) {
                System.out.println("Eemaldatud rida ei leitud");
            }

        } catch (FileNotFoundException e) {
            System.err.println("Faili ei leitud " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Viga faili tootlemisel: " + e.getMessage());
        } finally {
                try {
                    if (fileWriter != null) fileWriter.close();
                    if (fileReader != null) fileReader.close();
                } catch (IOException e) {
                    System.err.println("Faili sulgemisel: " + e.getMessage());
                }
        }
        // kui onnestus uus fail teha
        if (tempFile.exists()) {
            // kustutame algse faili
            if (inputFile.delete()) {
                // kui ei onnestunud uut faili umber nimetada algseks
                if (!tempFile.renameTo(inputFile)) {
                    System.err.println("Ei onnestunud ajutist faili umber nimetada");
                }
            } else {
              System.err.println("Ei onnestunud algset faili kustutada.");
            }
        }
    }
}