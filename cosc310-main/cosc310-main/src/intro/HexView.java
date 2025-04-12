package intro;                                                      // PACKAGE DECLARATION: This means the class is located in a subfolder named "intro"

import java.util.Scanner;                                           // IMPORT STATEMENTS: These are used to include classes from other packages
import java.io.InputStream;
import java.io.FileInputStream;

public class HexView {                                              // CLASS DECLARATION: Must match the name of the file HexView.java
    public static void main(String[] args) {
        System.out.println("Enter a filename:");                  // Note that the x: shown in gray is NOT part of the code, it is just a visual cue from VS Code
        Scanner scanner = new Scanner(System.in);                   // Create an instance of the Scanner class
        String filename = scanner.nextLine();                       // Read the filename from the terminal window
        try (InputStream in = new FileInputStream(filename)) {      // Try-with-resources statement to open the file and automatically close it after the try block ends
            int offset = 0;                                         // Keep track of our position within the file (in bytes)
            int b;                                                  // The current byte we just read from the file
            while ((b = in.read()) != -1) {                         // Loop until we reach the end of the file at which point in.read() returns -1
                if (offset % 16 == 0) {                             // Print the offset in hexadecimal at the beginning of each line ... every 16 bytes
                    System.out.printf("%08x: ", offset);     // Remember the gray "format:" cue is NOT part of the actual code
                }
                System.out.printf("%02x ", b);               // Print the current byte in hexadecimal
                offset++;                                           // Update our "offset" variable to keep track of which byte we are displaying           
                if (offset % 16 == 0) {                             // Add a newline character to go down to the next line if this was the 16th byte, 32nd byte, 48th byte, etc...
                    System.out.println();
                }
            }
            if (offset % 16 != 0) {                                 // Print one final newline character unless we just printed one
                System.out.println();
            }
        } catch (Exception e) {                                     // This is required part of the try block, it catches any exceptions that occur in the try block, specifically if the file doesn't exist or is deleted while the program is running
            e.printStackTrace();
        }
        scanner.close();
    }
}
