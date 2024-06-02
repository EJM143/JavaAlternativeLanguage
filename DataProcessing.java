import java.io.*;


public class DataProcessing {


    public static void main(String[] args) throws IOException {
        readFile("./Data/cells.csv");
    }

    static void readFile(String fileLoc) throws IOException {
        CellsReport cellsReport = new CellsReport();

        try (BufferedReader read = new BufferedReader(new FileReader(fileLoc))) {
            String line;
            read.readLine(); // Skip header
            while ((line = read.readLine()) != null) {
                Cell c = parseLine(line);
                cellsReport.addCell(c);
            }
        } catch (IOException e) {
            throw e;
        }

        System.out.println();
        System.out.println("*** Yearly Model Count *** ");
        cellsReport.printYearlyModel();
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println();
        System.out.println("*** OEM Count *** ");
        cellsReport.printOem();
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println();
        System.out.println("*** Body Sim Count *** ");
        cellsReport.printBodySim();
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println();
        System.out.println("*** Display Type Count *** ");
        cellsReport.printDisplayType();
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println();
        System.out.println("*** Platform OS Count *** ");
        cellsReport.printPlatformOs();
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println();
        System.out.println("Duplicate Report: " + cellsReport.duplicateRow());
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println();
        System.out.println("*** Highest Average Weight *** ");
        cellsReport.printHighestAveWt();
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println();
        System.out.println("*** Phones Announced in One Year and Released in Another *** ");
        cellsReport.printPhonesInDiffYrs();
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println();
        System.out.println("*** Phones with One Feature Sensor *** ");
        cellsReport.printPhonesWOneFSensor();
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println();
        System.out.println("*** Year With Most Phone Launched in the 2000s *** ");
        cellsReport.printYrMostLaunched();
        System.out.println("--------------------------------------------------------------------------------------");
    }

    public static Cell parseLine(String line) {
        String[] subs = line.split(",");
        Cell c = new Cell();

        int index = 0;
        for (int i = 0; i < 12 && index < subs.length; i++) {
            String str = subs[index++];
            if (!str.isEmpty() && str.charAt(0) == '\"') {
                while (index < subs.length) {
                    str += ",";
                    str += subs[index++];
                    if (str.charAt(str.length() - 1) == '\"') {
                        break;
                    }
                }
                str = str.substring(1, str.length() - 1);
            }

            switch (i) {
                case 0:
                    c.setOem(str);
                    break;
                case 1:
                    c.setModel(str);
                    break;
                case 2:
                    c.setLaunchAnnounced(getLaunchYear(str));
                    break;
                case 3:
                    c.setLaunchStatus(str);
                    break;
                case 4:
                    c.setBodyDimensions(str);
                    break;
                case 5:
                    c.setBodyWeight(getBodyWeight(str));
                    break;
                case 6:
                    c.setBodySim(str);
                    break;
                case 7:
                    c.setDisplayType(str);
                    break;
                case 8:
                    c.setDisplaySize(getDisplaySize(str));
                    break;
                case 9:
                    c.setDisplayResolution(str);
                    break;
                case 10:
                    c.setFeaturesSensors(str);
                    break;
                case 11:
                    c.setPlatformOs(str);
                    break;
            }
        }
        return c;
    }

    private static int getLaunchYear(String str) {
        if (str.isEmpty()) {
            return 0;
        }
        String year = "";
        for (int i = 0; i < 4; i++) {
            if (Character.isDigit(str.charAt(i))) {
                year += str.charAt(i);
            } else {
                return 0;
            }
        }
        return Integer.parseInt(year);
    }

    private static float getDisplaySize(String str) {
        if (str.isEmpty()) {
            return 0;
        }
        String size = "";
        boolean flag = false;
        for (char c : str.toCharArray()) {
            if (Character.isDigit(c) || c == '.') {
                size += c;
            } else if (Character.isWhitespace(c)) {
                continue;
            } else if (c == 'i') {
                flag = true;
                break;
            }
        }
        if (flag) {
            return Float.parseFloat(size);
        } else {
            return 0;
        }
    }

    private static float getBodyWeight(String str) {
        if (str.isEmpty()) {
            return 0;
        }
        String weight = "";
        boolean flag = false;
        for (char c : str.toCharArray()) {
            if (Character.isDigit(c)) {
                weight += c;
            } else if (Character.isWhitespace(c)) {
                continue;
            } else if (c == 'g') {
                flag = true;
                break;
            }
        }
        if (flag) {
            return Integer.parseInt(weight);
        } else {
            return 0;
        }
    }
}

