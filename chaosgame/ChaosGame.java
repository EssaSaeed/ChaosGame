
package chaosgame;

import java.awt.Graphics;           //Imports necessary imports
import java.awt.Color;
import java.io.*;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JFrame;

public class ChaosGame extends JFrame {     //extends JFrame to display graphics

    static int n, numDots;      //Fields
    static int cityPositions[][];
    static int targetCityIndex;
    static int screenWidth = 800;
    static int delayTime = 1;
    
    public static void getUserInput() throws IOException {      //Reads and stores/makes use of user inputs
        
        Scanner s = new Scanner( System.in );
        
        System.out.println( "Welcome to the ChaosGame simulation!" );
        System.out.println( "Before we start, please enter the number of dots as an integer value, try 100000+ for a crisper image: " );
        numDots = s.nextInt();
        
        System.out.println( "NEW: There are three ways of viewing this simulation, enter the corresponding number to your choice below:" );
        System.out.println( "1 - Step-by-step (a higher delay time, to allow the user to follow along the simulation - for patient people)" );
        System.out.println( "2 - All-at-once (virtually zero delay time, thereby creating all the dots on screen at once - for impatient people)" );
        System.out.println( "3 - Classic/Usual (the default setting of a 1 millisecond delay, this will be chosen if the user enters a choice that is not available)" );
        
        String userSay = s.next();
        if( userSay.equalsIgnoreCase( "1" ) ) {
            delayTime = 500;
        }
        else if ( userSay.equalsIgnoreCase( "2" ) ) {
            delayTime = 0;
        }
        else {       //Delaytime remains unchanged
        }
        
        System.out.println( "Choose one of the following patterns: Triangle (coolest), Square (lame), Pentagon (cool), Hexagon (cool), Heptagon (meh), Octagon (lame), or Custom" );
        System.out.println( "NOTE: Custom will require the user to input the number of vertices, and the coordinates of each" );
        System.out.println( "CAUTION: If the user enters an option that is not available above, Custom will be chosen" );
        
        String input = s.next();
        
        if ( input.equalsIgnoreCase( "Triangle" ) ) {       //Triangle coordinates
            
            cityPositions = new int[3][2];
            
            cityPositions[0][0] = 350;
            cityPositions[0][1] = 100;
            cityPositions[1][0] = 100;
            cityPositions[1][1] = 600;
            cityPositions[2][0] = 600;
            cityPositions[2][1] = 600;
        }
        
        else if( input.equalsIgnoreCase( "Square" ) ) {     //Square coordinates
            
            cityPositions = new int[4][2];
            
            cityPositions[0][0] = 100;
            cityPositions[0][1] = 100;
            cityPositions[1][0] = 700;
            cityPositions[1][1] = 100;
            cityPositions[2][0] = 100;
            cityPositions[2][1] = 700;
            cityPositions[3][0] = 700;
            cityPositions[3][1] = 700;
        }
        
        else if( input.equalsIgnoreCase( "Pentagon" ) ) {       //Pentagon coordinates
            
            cityPositions = new int[5][2];
            
            cityPositions[0][0] = 400;
            cityPositions[0][1] = 50;
            cityPositions[1][0] = 100;
            cityPositions[1][1] = 350;
            cityPositions[2][0] = 700;
            cityPositions[2][1] = 350;
            cityPositions[3][0] = 250;
            cityPositions[3][1] = 700;
            cityPositions[4][0] = 550;
            cityPositions[4][1] = 700;
        }
        
        else if( input.equalsIgnoreCase( "Hexagon" ) ) {        //Hexagon coordinates
            
            cityPositions = new int[6][2];
            
            cityPositions[0][0] = 250;
            cityPositions[0][1] = 100;
            cityPositions[1][0] = 100;
            cityPositions[1][1] = 400;
            cityPositions[2][0] = 700;
            cityPositions[2][1] = 400;
            cityPositions[3][0] = 250;
            cityPositions[3][1] = 700;
            cityPositions[4][0] = 550;
            cityPositions[4][1] = 700;
            cityPositions[5][0] = 550;
            cityPositions[5][1] = 100;
        }
        
        else if( input.equalsIgnoreCase( "Heptagon" ) ) {       //Heptagon coordinates
            
            cityPositions = new int[7][2];
            
            cityPositions[0][0] = 150;
            cityPositions[0][1] = 200;
            cityPositions[1][0] = 100;
            cityPositions[1][1] = 500;
            cityPositions[2][0] = 700;
            cityPositions[2][1] = 500;
            cityPositions[3][0] = 250;
            cityPositions[3][1] = 700;
            cityPositions[4][0] = 550;
            cityPositions[4][1] = 700;
            cityPositions[5][0] = 650;
            cityPositions[5][1] = 200;
            cityPositions[6][0] = 400;
            cityPositions[6][1] = 50;
        }
        
        else if( input.equalsIgnoreCase( "Octagon" ) ) {        //Octagon coordinates
            cityPositions = new int[8][2];
            
            cityPositions[0][0] = 250;
            cityPositions[0][1] = 150;
            cityPositions[1][0] = 500;
            cityPositions[1][1] = 150;
            cityPositions[2][0] = 100;
            cityPositions[2][1] = 350;
            cityPositions[3][0] = 650;
            cityPositions[3][1] = 350;
            cityPositions[4][0] = 100;
            cityPositions[4][1] = 550;
            cityPositions[5][0] = 650;
            cityPositions[5][1] = 550;
            cityPositions[6][0] = 250;
            cityPositions[6][1] = 750;
            cityPositions[7][0] = 500;
            cityPositions[7][1] = 750;
        }
        
        else {      //Custom, where the user decides the coordinates of the vertices, and the number of vertices
            System.out.println( "Enter the number of cities (vertices) as an integer value: " );
        
            n = s.nextInt();

            cityPositions = new int[n][2];
        
            for ( int i = 1; i < n+1; i++ ) {
                System.out.println( "Enter an x-value for co-ordinate #" + i );
                cityPositions[i-1][0] = s.nextInt();
                System.out.println( "Enter a y-value for co-ordinate #" + i );
                cityPositions[i-1][1] = s.nextInt();
            }
        }
    }
    
    public void paint( Graphics g ) {       //Paint method calls the correct methods to create the desired patterns
        g.setColor( Color.black );          //Sets the color to black
        g.fillRect( 0, 0, 800, 800 );       //Fills the background with colour
        
        n = cityPositions.length;       //Ensures that n is set to the correct value, especially for the presets
        
        drawCity( g );      //Calls the drawCity method

        drawDots( g );      //Calls the drawDots method
    }
    
    public void drawCity( Graphics g ) {

        for ( int i = 0; i < n; i++ ) {     //Runs for as many cities as the user enters, or the preset amount
            City c = new City( i );     //Creates a new city with index "i"
            c.drawCity( g );        //Calls the drawCity method located within the City class which draws the city/vertex that corresponds to the index it is passed as an argument
        }
    }
    
    public void drawDots( Graphics g ) {
        
        Random r = new Random();        //Creates new random object

        targetCityIndex = r.nextInt( n );       //Chooses starting city at random
        
        Dot d = new Dot( targetCityIndex );     //Creates a new dot that uses the targetCityIndex as its starting point
        d.drawDots( g );        //Calls the drawDots method located within the Dot class which creates all the dots at the correct random midpoints
    }
    
    public static void main( String[] args ) throws IOException {       //Throws possible input/output exceptions
        
        ChaosGame chaos = new ChaosGame();      //Creates a new ChaosGame object
        
        getUserInput();     //Gets the user's inputs
        
        chaos.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );     //Allows for the user to stop the program from running when they close the display screen
        chaos.setSize( screenWidth, screenWidth );      //Sets the size of the display screen
        chaos.setBackground( Color.black );     //This is supposed to make the background black
        chaos.setVisible( true );       //Calls paint
    }
}