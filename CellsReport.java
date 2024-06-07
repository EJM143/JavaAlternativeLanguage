import java.util.*;

public class CellsReport {

    private Set<Cell> cells = new HashSet<>();
    private int duplicate = 0;

    /**
     * Adds a cell phone to the report
     * @param c     the cellphone to add
     */
    public void addCell(Cell c) {
        if (!cells.add(c)) {
            duplicate++;
        }
    }

    /**
     * Deletes a cell phone from the report
     * @param c     the cellphone to delete
     */
    public void deleteCell(Cell c) {

        cells.remove(c);
    }

    /**
     * Retrieves the count of duplicate cellphone entries
     * @return the count of duplicate cellphone entries
     */
    public int duplicateRow() {

        return duplicate;
    }

    /**
     * Prints the count of cellphones launched yearly
     */
    public void printYearlyModel() {
        Map<Integer, Integer> yearlyList = new HashMap<>();

        for (Cell cell : cells) {
            yearlyList.put(cell.getLaunchAnnounced(), yearlyList.getOrDefault(cell.getLaunchAnnounced(), 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : yearlyList.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    /**
     * Prints the counts of cellphones by OEM
     */
    public void printOem() {
        Map<String, Integer> oemList = new HashMap<>();

        for (Cell cell : cells) {
            oemList.put(cell.getOem(), oemList.getOrDefault(cell.getOem(), 0) + 1);
        }

        for (Map.Entry<String, Integer> entry : oemList.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    /**
     * Prints the counts of cellphones by body sim
     */
    public void printBodySim() {
        Map<String, Integer> bodyList = new HashMap<>();

        for (Cell cell : cells) {
            bodyList.put(cell.getBodySim(), bodyList.getOrDefault(cell.getBodySim(), 0) + 1);
        }

        for (Map.Entry<String, Integer> entry : bodyList.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    /**
     * Prints the counts of cellphones by display type
     */
    public void printDisplayType() {
        Map<String, Integer> displayList = new HashMap<>();

        for (Cell cell : cells) {
            displayList.put(cell.getDisplayType(), displayList.getOrDefault(cell.getDisplayType(), 0) + 1);
        }

        for (Map.Entry<String, Integer> entry : displayList.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    /**
     * Prints the counts of cellphones by platdorm OS
     */
    public void printPlatformOs() {
        Map<String, Integer> platformOsList = new HashMap<>();

        for (Cell cell : cells) {
            platformOsList.put(cell.getPlatformOs(), platformOsList.getOrDefault(cell.getPlatformOs(), 0) + 1);
        }

        for (Map.Entry<String, Integer> entry : platformOsList.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    /**
     * Prints the OEM with the highest average weight
     */
    public void printHighestAveWt() {
        Map<String, OemWeightData> oemWtData = new HashMap<>();

        for (Cell cell : cells) {
            String oem = cell.getOem();
            float weight = cell.getBodyWeight();
            if (!oemWtData.containsKey(oem)) {
                oemWtData.put(oem, new OemWeightData(weight, 1));
            } else {
                OemWeightData data = oemWtData.get(oem);
                data.totalWeight += weight;
                data.count++;
            }
        }

        String highestOem = null;
        float highestAve = 0;

        for (Map.Entry<String, OemWeightData> entry : oemWtData.entrySet()) {
            OemWeightData data = entry.getValue();
            float aveWt = data.totalWeight / data.count;
            if (aveWt > highestAve) {
                highestAve = aveWt;
                highestOem = entry.getKey();
            }
        }

        if (highestOem != null) {
            System.out.println("The OEM with the highest average weight is " + highestOem + " with an average weight of " + highestAve + " grams.");
        } else {
            System.out.println("No cellphones found.");
        }
    }

    // Inner class to hold total weight and count for each OEM
    private static class OemWeightData {
        private float totalWeight;
        private int count;

        public OemWeightData(float totalWeight, int count) {
            this.totalWeight = totalWeight;
            this.count = count;
        }
    }

    /**
     * prints the count of phones announced in one year and realeased in another
     */
    public void printPhonesInDiffYrs() {
        List<Cell> phonesInDiffYrs = new ArrayList<>();

        for (Cell cell : cells) {
            int announcedYear = cell.getLaunchAnnounced();
            int releasedYear = getReleaseYr(cell.getLaunchStatus());

            if (announcedYear != 0 && releasedYear != 0 && announcedYear != releasedYear) {
                phonesInDiffYrs.add(cell);
            }
        }

        if (!phonesInDiffYrs.isEmpty()) {
            System.out.println("Phones announced in one year and released in another:");
            for (Cell cell : phonesInDiffYrs) {
                System.out.println("OEM: " + cell.getOem() + ", Model: " + cell.getModel() + ", Announced: " + cell.getLaunchAnnounced() + ", Released: " + getReleaseYr(cell.getLaunchStatus()));
            }
        } else {
            System.out.println("There are no phones that were announced in one year and released in another.");
        }
    }

    /**
     * Retrieves the release year from the launch status string
     * @param launchStatus      the launch status string
     * @return      the release year
     */
    private int getReleaseYr(String launchStatus) {
        if (launchStatus == null || launchStatus.isEmpty()) {
            return 0;
        }

        String[] words = launchStatus.split(" ");
        for (String word : words) {
            try {
                int year = Integer.parseInt(word);
                if (year >= 2000 && year <= 2024) {
                    return year;
                }
            } catch (NumberFormatException ignored) {}
        }

        return 0;
    }

    /**
     * Prints the count of phones with only one feature sensor
     */
    public void printPhonesWOneFSensor() {
        int count = 0;

        for (Cell cell : cells) {
            if (cell.getFeaturesSensors() != null) {
                String[] sensors = cell.getFeaturesSensors().split(",");
                if (sensors.length == 1) {
                    count++;
                }
            }
        }

        System.out.println("The number of phones with only one feature sensor is " + count + ".");
    }

    /**
     * Prints the year with most cellphones launched in the year 2000s
     */
    public void printYrMostLaunched() {
        Map<Integer, Integer> yearlyLaunchCount = new HashMap<>();

        for (Cell cell : cells) {
            int launchYear = cell.getLaunchAnnounced();
            if (launchYear >= 2000 && launchYear <= 2009) {
                yearlyLaunchCount.put(launchYear, yearlyLaunchCount.getOrDefault(launchYear, 0) + 1);
            }
        }

        int yearMostPhones = 0;
        int maxCount = 0;

        for (Map.Entry<Integer, Integer> entry : yearlyLaunchCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                yearMostPhones = entry.getKey();
            }
        }

        if (yearMostPhones > 0) {
            System.out.println("The year with the most phones launched in the 2000's is " + yearMostPhones + " with " + maxCount + " phones.");
        } else {
            System.out.println("There are no phones launched in the 2000s.");
        }
    }

    /**
     * Generates a string representation of the cellphones in the report
     * @return   A string representation of the cellphones in the report
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Cell cell : cells) {
            result.append(cell.getOem()).append(", ").append(cell.getModel()).append(", ").append(cell.getLaunchAnnounced()).append(", ").append(cell.getLaunchStatus()).append(", ").append(cell.getBodyDimensions()).append(", ").append(cell.getBodyWeight()).append(", ").append(cell.getBodySim()).append(", ").append(cell.getDisplayType()).append(", ").append(cell.getDisplaySize()).append(", ").append(cell.getDisplayResolution()).append(", ").append(cell.getFeaturesSensors()).append(", ").append(cell.getPlatformOs()).append("\n");
        }
        return result.toString();
    }
}
