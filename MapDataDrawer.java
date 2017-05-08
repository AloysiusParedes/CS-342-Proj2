/**
 * File that holds the map drawing functions and path calculation functions
 */


import java.util.*;
import java.io.*;
import java.awt.*;

public class MapDataDrawer
{
    private int[][] grid;
    public int min;
    public int max;
    public int rows;
    public int cols;

    public MapDataDrawer(String filename) throws Exception{
        // initialize grid
        //read the data from the file into the grid
        FileReader file = new FileReader(filename);
        Scanner scanner = new Scanner (file);

        rows = scanner.nextInt();
        cols = scanner.nextInt();


        max = 0;
        min = Integer.MAX_VALUE;
        grid = new int[rows][cols];

        for(int i = 0; i < rows; i++)
            for(int j = 0; j < cols; j++) {
                grid[i][j] = scanner.nextInt();

                if (max < grid[i][j])
                    max = grid[i][j];

                if (min > grid[i][j])
                    min = grid[i][j];
            }

  }

    /**
    * @return the min value in the entire grid
    */
    public int findMinValue(){
        return min;
    }
    /**
    * @return the max value in the entire grid
    */
    public int findMaxValue(){
        return max;
    }

    /**
    * @param col the column of the grid to check
    * @return the index of the row with the lowest value in the given col for the grid
    */
    public  int indexOfMinInCol(int col){
        int index = 0;
        int smallest = Integer.MAX_VALUE;
        for(int i = 0; i < rows; i++){
            if(grid[i][col] < smallest) {
                smallest = grid[i][col];
                index = i;
            }
        }

        return index;
    }

    /**
    * Draws the grid using the given Graphics object.
    * Colors should be grayscale values 0-255, scaled based on min/max values in grid
    */
    public void drawMap(Graphics g){
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                int c = ((255 * grid[i][j]) - min) / (max - min);

                g.setColor(new Color(c, c, c));

                g.fillRect(j, i, 1, 1);
            }
        }
    }

    /**
    * Find a path from West-to-East starting at given row.
    * Choose a foward step out of 3 possible forward locations, using greedy method described in assignment.
    * @return the total change in elevation traveled from West-to-East
    */
    public int drawLowestElevPath(Graphics g, int row){
        int change = 0;

        //current position: grid[row][0]

        //loop to go through all columns starting from 0
        for(int j = 0; j < cols - 1; j++) {
            g.fillRect(j, row, 1, 1);

            int upRight, right, downRight;

            right = Math.abs(grid[row][j + 1] - grid[row][j]);

            //check if you've reached upper bound
            if(row == 0)
                upRight = Integer.MAX_VALUE;
            else
                upRight = Math.abs(grid[row - 1][j + 1] - grid[row][j]);

            //check if you've reached lower bound
            if(row == rows - 1)
                downRight = Integer.MAX_VALUE;
            else
                downRight = Math.abs(grid[row + 1][j + 1] - grid[row][j]);

            //set the next path to take
            int next = nextStep(upRight, right, downRight);
            row += next;

            if(next == -1) {
                change += upRight;
                continue;
            }
            if(next == 0){
                change += right;
                continue;
            }
            change += downRight;
        }

        return change;
    }


    /**
     * Find a path from East-to-West starting at given row.
     * Choose a forward step out of 3 possible forward locations, using a greedy method
     * @return the total change in elevation traveled from East-to-West
     */
    public int reverseGreedy(Graphics g, int row){
        int change = 0;

        for(int j = cols - 1; j > 0; j--) {
            g.fillRect(j, row, 1, 1);

            int upRight, right, downRight;

            right = Math.abs(grid[row][j - 1] - grid[row][j]);

            if(row == 0)
                upRight = Integer.MAX_VALUE;
            else
                upRight = Math.abs(grid[row - 1][j - 1] - grid[row][j]);

            if(row == rows - 1)
                downRight = Integer.MAX_VALUE;
            else
                downRight = Math.abs(grid[row + 1][j - 1] - grid[row][j]);

            int next = nextStep(upRight, right, downRight);
            row += next;

            if(next == -1) {
                change += upRight;
                continue;
            }
            if(next == 0){
                change += right;
                continue;
            }
            change += downRight;
        }

        return change;
    }

    /**
     *
     * @return the index of the starting row for the lowest-elevation-change path in the entire grid going from right to left using a reverse greedy algorithm
     */
    public int indexOfLowestElevPathRightToLeft(Graphics g){
        int lowestChange = Integer.MAX_VALUE;
        int index = 0;

        for(int i = 0; i < rows; i++){
            int currentChange = reverseGreedy(g, i);
            if(currentChange < lowestChange){
                lowestChange = currentChange;
                index = i;
            }
        }

        return index;
    }

    /**
    * @return the index of the starting row for the lowest-elevation-change path in the entire grid.
    */
    public int indexOfLowestElevPath(Graphics g){
        int lowestChange = Integer.MAX_VALUE;
        int index = 0;

        for(int i = 0; i < rows; i++){
            int currentChange = drawLowestElevPath(g, i);
            if(currentChange < lowestChange){
                lowestChange = currentChange;
                index = i;
            }
        }

        return index;
    }

    /**
     * @return an integer for Greedy algorithm to determine whether to move up-right, right, or down-right
     */
    public int nextStep(int upright, int right, int downright){
        int min = upright;

        if(right < min)
            min = right;

        if(downright < min)
            min = downright;

        if(min == upright)
            return -1;
        if(min == right)
            return 0;
        return 1;
    }


}