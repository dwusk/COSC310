package asg1;

import java.util.ArrayList;

public class ParsingPerformance_sol {

    // Instance variables the two data structures we will be comparing in this assignment
    // the first data structures is a 2D array where rows are different records and column[0] is front chainring teeth, column[1] is rear cog teeth
    // the second is an ArrayList of int arrays where each int array has two elements: front chainring teeth and rear cog teeth
    protected int[][] geardata;
    protected ArrayList<int[]> gearDataList;

    public static void main(String[] args) {
       //Download the contents of this URL into a String: "https://raw.githubusercontent.com/kartoone/cosc310/refs/heads/main/data/recorddata.txt"
       // Parse the String into an array of Strings by splitting the String using "\n" as the regular expression (e.g., testData.split("\n") ) 
       ParsingPerformance_sol parsingPerformance_sol = new ParsingPerformance_sol();
       String data = URLContentDownloader.downloadUrlContent("https://raw.githubusercontent.com/kartoone/cosc310/refs/heads/main/data/recorddata.txt");
       parsingPerformance_sol.parseData(data);
    }
    
    public void parseData(String data) {
        String[] records = data.split("\n");
        geardata = new int[records.length][2];
        gearDataList = new ArrayList<int[]>();
        for (int i = 0; i < records.length; i++) {
            String[] fields = records[i].split(" ");
            String[] gearfield = fields[10].split(",");
            String[] subgearfields = gearfield[0].split("x");
            geardata[i][0] = Integer.parseInt(subgearfields[0]);
            geardata[i][1] = Integer.parseInt(subgearfields[1]);
            gearDataList.add(new int[] {Integer.parseInt(subgearfields[0]), Integer.parseInt(subgearfields[1])});
        }
        for (int[] gears : geardata)
            System.out.println(java.util.Arrays.toString(gears));
        System.out.println("Press any key to continue...");
        try {
            System.in.read();
        } catch (Exception e) {
            // Ignored
        }
        for (int[] gears : gearDataList)
            System.out.println(java.util.Arrays.toString(gears));
    }
}
