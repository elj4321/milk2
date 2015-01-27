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

  private static final String task = "milk2";
  private static char white = 'w';
  private static boolean debug = false;
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
   
    // Read in number of beads and string of beads
    int beadLen = scanr.nextInt();
    String beadStr = scanr.next();
//    outd("beadLen = " + beadLen);
    outd("beadStr1 = " + beadStr);


    int maxTotal = 0;
    int maxF = 0;
    int maxR = 0;
    int iSplitSav = -1;
    int iNextF = -1;
    int iNextR = -1;
    // Loop over all possible positions for splitting the necklace
    for (int iSplit = 1; iSplit <= beadLen; iSplit++)
    {
      // Use copy of beads for each split since we may have to repaint beads
      char[] beads = beadStr.toCharArray();
      iNextF = iSplit;
      iNextR = iSplit-1;
      if (iNextR == 0) iNextR = beadLen;

      // Find max beads in forward direction
      char c1 = beads[iNextF-1];
      if (c1 != white)
      {
        maxF = getMax(beads, c1, 1, iNextF, true);
      }
      else
      {
        int maxBlue = getMax(beads, 'b', 1, iNextF, false);
        int maxRed = getMax(beads, 'r', 1, iNextF, false);
        if ( maxBlue > maxRed)
        {
          getMax(beads, 'b', 1, iNextF, true);
        }
        else
        {
          getMax(beads, 'r', 1, iNextF, true);         
        }
        maxF = Math.max(maxBlue,  maxRed);
      }
     
//      for (int j = 0; j < beadLen-1; j++)
//      {
//        iNextF++;
//        if (iNextF > beadLen) iNextF = 1;
//        if (beads[iNextF-1] == c1 || beads[iNextF-1] == white)
//        {
//          maxF++;
//        }
//        else
//        {
//          break;
//        }
//      }

      // If all beads same color then we are done
      if (maxF == beadLen)
      {
        maxTotal = maxF;
        break;
      }
     
      // Max beads in reverse direction
      c1 = beads[iNextR-1];
      if (c1 != white)
      {
        maxR = getMax(beads, c1, -1, iNextR, true);
      }
      else
      {
        int maxBlue = getMax(beads, 'b', -1, iNextR, false);
        int maxRed = getMax(beads, 'r', -1, iNextR, false);
        if ( maxBlue > maxRed)
        {
          getMax(beads, 'b', -1, iNextR, true);
        }
        else
        {
          getMax(beads, 'r', -1, iNextR, true);         
        }
        maxR = Math.max(maxBlue,  maxRed);
      }

//      maxR = 1;
//      c1 = beads[iNextR-1];
//      for (int j = 0; j < beadLen-1; j++)
//      {
//        iNextR--;
//        if (iNextR == 0) iNextR = beadLen;
//        if (beads[iNextR-1] == c1 || beads[iNextR-1] == white)
//        {
//          maxR++;
//        }
//        else
//        {
//          break;
//        }
//      }
//      out.println("iSplit: " + iSplit + " maxF: " + maxF +
//              " maxR: " + maxR + " maxTot: " + maxTotal);
      // Max total
      if ((maxF + maxR) > maxTotal)
      {
        maxTotal = maxF + maxR;
        iSplitSav = iSplit;
        outd("New max of " + maxTotal + " at position " + iSplitSav);
      }
    }
    // Output results
    outd("" + maxTotal);
    out.println(maxTotal);
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
      if (beads[iNext-1] == c1 || beads[iNext-1] == white)
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
