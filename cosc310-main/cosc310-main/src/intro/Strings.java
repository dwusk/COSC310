package intro;

public class Strings {
    public static void main(String[] args) {
        // encodingExperiment();
        stringParsingManual();
        stringParsingWithSplit();
    }

    @SuppressWarnings("unused")
    private static void encodingExperiment() {
        // Let's start with just a plain old string with four regular characters (non-unicode)
        String name = "John";
        byte[] nameBytes = name.getBytes();
        System.out.println("Length of the bytes array: " + nameBytes.length); // ASCII and UTF-8 would have the same length when non-unicode characters are being used with one byte per character
                                                                              // UTF-16, on the other hand, always uses 2 or 4 bytes for characters so the length would be 8 since there are no unicode characters in John
        
        // Let's print out the bytes in the array one by one on the same line
        for (int i =0; i < nameBytes.length; i++) {
            byte b = nameBytes[i];
            System.out.print(b + " ");
        }
        System.out.println();
        System.out.println("name: " + name); // Note that there are no special characters in this name
        System.out.println("Length of the string: " + name.length()); // AND the length of the string is simply 4 b/c there are four characters
        System.out.println();

        // Now let's create a name with an emoji at the end
        byte[] name2Bytes = {74,111,104,110,(byte)0xf0, (byte)0x9f, (byte)0x98, (byte)0x96};
        String name2 = new String(name2Bytes);
        System.out.println("Length of the new bytes array: " + name2Bytes.length); // ASCII and UTF-8 would still have the same length but when displayed it would display ???? for the unicode bytes. This is what happens on Windows!
        System.out.println("name2: " + name2); // Note that there is now a single unicode character (counts as 2 in Java) at the end of the name in the terminal 
        System.out.println("Length of the string: " + name2.length()); // The length of the string is 6 b/c unicode characters count as double characters ... four regular and one unicode "codepoint" which is seen as 2 characters by Java
        System.out.println();
        
        // Now let's create a name with an emoji at the end using the actual emoji character copy/pasted from online
        String name3 = "John😆";
        System.out.println("name3: " + name3);
        byte[] name3Bytes = name3.getBytes();
        System.out.println("Length of the new bytes array: " + name3Bytes.length); // ASCII and UTF-8 both have length of 8 since the emoji is represented by 4 bytes in UTF-8
        System.out.println("Length of the string: " + name3.length()); // BUT the length of the string is 6 b/c unicode characters count as double characters ... four regular and one unicode "codepoint" which is seen as 2 characters by Java
        System.out.println();
    }

    private static void stringParsingWithSplit() {
        System.out.println("PARSING USING SPLIT");
        String routeIds = "35,76,89,90,91,95";
        long startTime = System.nanoTime();        
        String[] strRouteIds = routeIds.split(",");
        int[] intRouteIds = new int[strRouteIds.length];
        int i = 0;
        for (String s : strRouteIds) {
            intRouteIds[i++] = Integer.parseInt(s.trim());
        }
        long finishTime = System.nanoTime();        
        long elapsed = finishTime - startTime;
        System.out.println("Elapsed time: " + elapsed + " ns");
        System.out.println("Count: " + intRouteIds.length);
        System.out.println("IDs:" + java.util.Arrays.toString(intRouteIds));
        System.out.println();
    }

    private static void stringParsingManual() {
        System.out.println("MANUAL PARSING");
        String routeIds = "35,76,89,90,91,95";
        long startTime = System.nanoTime();        
        int[] intRouteIds = new int[10]; // over allocate
        String currentId = "";
        // manually parse the string to count how many "ids" are present
        int count = 1;
        for (int i = 0; i < routeIds.length(); i++) {
            char c = routeIds.charAt(i);
            if (c == ',') {
                intRouteIds[count-1] = Integer.parseInt(currentId.trim());
                currentId = "";
                count++;
            } else {
                currentId += c;
            }
        }
        long finishTime = System.nanoTime();        
        long elapsed = finishTime - startTime;
        System.out.println("Elapsed time: " + elapsed + " ns");
        System.out.println("Count: " + count);
        System.out.println("IDs:" + java.util.Arrays.toString(intRouteIds));
        System.out.println();
    }
}
