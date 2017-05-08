/** Program 2: Take a Hike
 * Author:  Aloysius Paredes
 * Code #:  879P
 * Date:    09/21/2016
 * Class:   UIC CS 342 Fall 2016
 * System:  OS X, IntelliJ IDE
 * Program Description:
 *      Using a Greedy Algorithm, find a path from West-to-East through a mountain pass that has the lowest elevation change.
 *      Using your own implemented algorithm, find a better path.
 * Files needed:    Driver.java
 *                  MapDataDrawer.java
 *                  DrawingPanel.java
 */

import java.awt.*;

public class Driver
{
    
    public static void main(String[] args) throws Exception {
        
        //construct DrawingPanel, and get its Graphics context
        DrawingPanel panel = new DrawingPanel(844, 480);
        Graphics g = panel.getGraphics();
        
        //Test Step 1 - construct mountain map data
        MapDataDrawer map = new MapDataDrawer("NevadaToCalifornia.txt");
        
        //Test Step 2 - min, max, minRow in col
        int min = map.findMinValue();
        System.out.println("Min value in map: "+min);
        
        int max = map.findMaxValue();
        System.out.println("Max value in map: "+max);
        
        int minRow = map.indexOfMinInCol(0);
        System.out.println("Row with lowest val in col 0: "+minRow);
        
        //Test Step 3 - draw the map
        map.drawMap(g);
        
        //Test Step 4 - draw a greedy path
        g.setColor(Color.RED); //can set the color of the 'brush' before drawing, then method doesn't need to worry about it
        int totalChange = map.drawLowestElevPath(g, minRow); //use minRow from Step 2 as starting point

        System.out.println("\n\tPART 4");
        System.out.println("The Lowest-Elevation-Change Path starting at row "+minRow+" gives total change of: "+totalChange);


        //Test Step 5 - draw the best path
        g.setColor(Color.RED);
        int bestRow = map.indexOfLowestElevPath(g);

        int myBestRow = map.indexOfLowestElevPathRightToLeft(g);

        
        map.drawMap(g); //use this to get rid of all red lines
        g.setColor(Color.GREEN); //set brush to green for drawing best path
        //PART 5
        totalChange = map.drawLowestElevPath(g, bestRow);
        System.out.println("\n\tPART 5");
        System.out.println("The Lowest-Elevation-Change Path starts at row: "+bestRow+" and gives a total change of: "+totalChange);

        g.setColor(Color.RED);
        //PART 4
        totalChange = map.drawLowestElevPath(g, minRow);

        g.setColor(Color.BLUE);

        //PART 6
        totalChange = map.reverseGreedy(g, myBestRow);
        System.out.println("\n\tPART 6");
        System.out.println("The Lowest-Elevation-Change Path traveling East-to-West starts at row: " + myBestRow + " and gives a total change of: " + totalChange);

    }


}
