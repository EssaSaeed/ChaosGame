
package chaosgame;

import java.awt.*;

public class City extends ChaosGame {
    
    double xPos, yPos, radius;      //Fields specific to the City class
    Color color;
    
    public City( int i ) {      //Constructor
        
        this.xPos = cityPositions[i][0];
        this.yPos = cityPositions[i][1];
        this.radius = 10;
        this.color = Color.white;
    }
    
    public void drawCity( Graphics g ) {        //Draws the city/vertex
        
        g.setColor( this.color );
        g.fillOval( (int) ( this.xPos - this.radius/2 ), (int) ( this.yPos - this.radius/2 ), (int) this.radius, (int) this.radius );
    }
}