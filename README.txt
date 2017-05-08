Author: Aloysius Paredes
Code#:	879P
File:	README.txt
Descr:	readme file for CS 342 Fall 2016 Program 2: Take a Hike

Starting point for program:	Start out with the file "Driver.java". Also make sure that "DrawingPanel.java" and "MapDataDrawer.java" is in the same project folder in order for the program to recognize the Vertex and Java public classes. Also make sure the "NevadaToCalifornia.txt", is in the same project folder so that it can read the file to properly generate the grayscale map and the 3 paths. 

Description of program completeness:	My program completes all required steps
	Completion:
		1. Read in Data File
		2. Find the min and max values
		3. Draw the grayscale map
		4. Draw the Greedy Path starting from the lowest point of elevation (RED)
		5. Draw the best Greedy path from all possible starting points of the left (GREEN)
		6. Draw a better solution than the best Greedy path (BLUE)
			Used a Greedy Algorithm traversing from Right-to-Left or East-to-West