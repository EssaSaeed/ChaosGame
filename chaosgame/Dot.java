
package chaosgame;

import java.awt.*;
import java.util.Random;

public class Dot extends City {
    
    //Requires no fields as all the necessary fields are inherited from the City class
    
    public Dot( int i ) {       //Constuctor
        
        super( i );     //Calls the City constructot
        this.radius = 1;
        this.color = Color.yellow;
    }
    
    public static void sleep( int duration ) {      //Special method, courtesy of Mr. Schattman
            
        try {
            Thread.sleep( duration );
        }
        catch ( Exception e ) {
        }
    }
    
    public void drawDots( Graphics g ) {        //Draws the dots in the correct midpoints between starting positions and randomly selected vertices
        
        Random r = new Random();
        
        g.setColor( this.color );
        
        for ( int i = 0; i < numDots; i++ ) {
            
            targetCityIndex = r.nextInt( n );       //Sets target city based on a particular, randomly selected, index
        
            xPos = ( xPos + cityPositions[targetCityIndex][0] ) / 2.0;      //Midpoint of a line
            yPos = ( yPos + cityPositions[targetCityIndex][1] ) / 2.0;
       
            g.fillOval( (int) ( this.xPos - this.radius/2 ), (int) ( this.yPos - this.radius/2 ), (int) this.radius, (int) this.radius );
            sleep( delayTime );     //Small delay for a more "animated" look
        }
   }
}