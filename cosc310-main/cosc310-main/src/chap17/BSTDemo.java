package chap17;

import java.util.ArrayList;

import chap17.BST.BSTNode;

public class BSTDemo {
    public static void main(String[] args) {
        BST<String> airports = new BST<>("BHM");
        airports.add("ATL");
        airports.add("MSP");
        airports.add("JFK");
        airports.add("ORD");
        System.out.println(airports);
        System.out.println();
        String csvstr = "MAD,PEK,LAX,DXB,HND,ORD,LHR,HKG,PVG,CDG,DFW,CAN,AMS,FRA,IST,DEL,JFK,SIN,ICN,DEN,BKK,SFO,KUL,ATL,LAS,CTU,BCN,SEA,PHX,MIA,MUC,SYD,FCO,EWR,MCO,SHA,CLT,YYZ,LGW,MSP,BOM,SZX,MEL,MNL,IAH,KMG,ZRH,GRU,OSL,ARN,DME,DOH,VIE,BNE,CPH,HEL,BRU,SVO,MXP,MAN,LIM,YVR,TXL,NRT,AUH,STN,LGG,LIS,HOU,BOG,PRG,WAW,ATH,GIG,LGA,CAI,TPE,SGN,LED,BUD,CGK,TXL,OTP,BSB,GMP,CHC,CMN,SAN,OKA,AKL,THR,TUN,ALG,KWI,DUB,CRK,CPT,ABV,BNE,SGF,ICT,OMA,ELP,BUF,BHM,HSV,MDT,PSP,RNO,GEG,MYR,ORF,ANC,FLL,PIT,CLE,SNA,JAX,MSY,SMF,PDX";
        String airportsparsed[] = csvstr.split(",");
        BST<String> lotsofairports = new BST<>(airportsparsed[0]);
        for (String airport : airportsparsed) {
            lotsofairports.add(airport);
        }
        System.out.println(lotsofairports);                
        System.out.println();
        String airportssorted[] = java.util.Arrays.copyOf(airportsparsed, airportsparsed.length);
        java.util.Arrays.sort(airportssorted);
        BST<String> rightleaning = new BST<>(airportssorted[0]);
        for (String airport : airportssorted) {
            rightleaning.add(airport);
        }
        System.out.println(rightleaning);

        String airportssortedr[] = java.util.Arrays.copyOf(airportsparsed, airportsparsed.length);
        java.util.Arrays.sort(airportssortedr, java.util.Collections.reverseOrder());
        BST<String> leftleaning = new BST<>(airportssortedr[0]);
        for (String airport : airportssortedr) {
            leftleaning.add(airport);
        }
        System.out.println(leftleaning);

        System.out.println("Searching for ZZZ in rightleaning");
        long start = System.nanoTime();
        BSTNode hit = rightleaning.search("ZZZ");
        long end = System.nanoTime();
        System.out.println("Time taken to search for ZZZ: " + (end - start) + " ns");
        if (hit == null) {
            System.out.println("ZZZ not found");
        } else {
            System.out.println("ZZZ found: " + hit);
        }

        System.out.println("Searching for AAA in leftleaning");
        start = System.nanoTime();
        hit = leftleaning.search("AAA");
        end = System.nanoTime();
        System.out.println("Time taken to search for AAA: " + (end - start) + " ns");
        if (hit == null) {
            System.out.println("AAA not found");
        } else {
            System.out.println("AAA found: " + hit);            
        }

        System.out.println("Searching for ZZZ in lotsofairports");
        start = System.nanoTime();
        hit = lotsofairports.search("ZZZ");
        end = System.nanoTime();
        System.out.println("Time taken to search for ZZZ: " + (end - start) + " ns");
        if (hit == null) {
            System.out.println("ZZZ not found");
        } else {
            System.out.println("ZZZ found: " + hit);            
        }
    }

}
