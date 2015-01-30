/*
ID: elj_4321
LANG: JAVA
TASK: milk2
*/

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import java.io.PrintStream;
//import java.net.URL;
//import java.io.BufferedReader;
//import java.io.FileReader;

public class Milk2 {

  private static boolean debug = true;
  private static final String task = "milk2";
  private static PrintStream outs = System.out;

  /**
   * @param args
   */
  public static void main(String[] args) throws IOException, FileNotFoundException
  {
    final String infile = task + ".in";
    final String outfile = task + ".out";
//    URL location = test.class.getProtectionDomain().getCodeSource().getLocation();
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(outfile)));
//    o(location.getFile());
    Scanner scanr = new Scanner(new File(infile));
   
    // Read in number of pairs
    int numPairs = scanr.nextInt();
    outd("numPairs = " + numPairs);


    int milked = 0;
    int notMilked = 0;
    // Loop over all pairs
    for (int iPair = 1; iPair <= numPairs; iPair++)
    {
      int startSecs = scanr.nextInt();
      int endSecs = scanr.nextInt();
      outd("start = " + startSecs + " end = " + endSecs);
    }

    // Output results
    outd("" + milked);
    outd("" + notMilked);
    out.println(milked);
    out.println(notMilked);
    scanr.close();
    out.close();
    System.exit(0);
  }

  static int getMax(char[] beads, char c1, int direction, int start,
                    boolean repaint)
  {
    int max = 1;
    int iNext = start;
    int beadLen = beads.length;
    for (int j = 0; j < beadLen-1; j++)
    {
      iNext = iNext + direction;
      if (iNext == 0) iNext = beadLen;
      if (iNext > beadLen) iNext = 1;
      if (beads[iNext-1] == c1 || beads[iNext-1] == 'w')
      {
        if (repaint) beads[iNext-1] = c1;
        max++;
      }
      else
      {
        break;
      }
    }
    return max;
  }
 
  static void outd(String msg)
  {
    if (debug) outs.println(msg);
  }
}
