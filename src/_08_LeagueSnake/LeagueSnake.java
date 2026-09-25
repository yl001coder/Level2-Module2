package _08_LeagueSnake;

import com.sun.prism.paint.Color;

import java.util.ArrayList;
import processing.core.PApplet;

public class LeagueSnake extends PApplet{
    static final int WIDTH = 800;
    static final int HEIGHT = 800;
    
    /*
     * Game variables
     * 
     * Put all the game variables here.
     */
    ArrayList<Segment> segments = new ArrayList<>();
    Segment head;
    int foodX;
    int foodY;
    int snakeDir = UP;
    int food = 0;
    int headX = 10;
    int headY = 10;
    int segment;
    Segment burp;

    
    /*
     * Setup methods
     * 
     * These methods are called at the start of the game.
     */
    @Override
    public void settings() {
    	size(500,500);
    }

    @Override
    public void setup() {
    	head = new Segment(headX,headY);
    	frameRate(20);
    	dropFood();
    }

    void dropFood() {
        // Set the food in a new random location
    	foodX = ((int)random(50)*10);
    	foodY = ((int)random(50)*10);
    }

    /*
     * Draw Methods
     * 
     * These methods are used to draw the snake and its food
     */

    @Override
    public void draw() {
    	background(0);
    	checkBoundaries();
    	move();
    	drawFood();
    	drawSnake();
    	eat();
    	
    	
    	
    }

    void drawFood() {
        // Draw the food
    	fill(209,50,50);
        rect(foodX,foodY,10,10);
    }

    void drawSnake() {
        // Draw the head of the snake followed by its tail
    	fill(133,199,109);
    	rect(headX,headY,10,10);
    	manageTail();
    }

    void drawTail() {
        // Draw each segment of the tail
    	segment = segments.size();
        for(int i = 0; i < segment; i++ ) {
        	fill(133,199,109);
        	rect(headX,headY,10,10);
        }
    }

    /*
     * Tail Management methods
     * 
     * These methods make sure the tail is the correct length.
     */

    void manageTail() {
        // After drawing the tail, add a new segment at the "start" of the tail and
        // remove the one at the "end"
        // This produces the illusion of the snake tail moving.
    	checkTailCollision();
    	drawTail();
    	burp = new Segment(headX,headY);
    	segments.add(burp);
    	segments.remove(0);
    }

    void checkTailCollision() {
        // If the snake crosses its own tail, shrink the tail back to one segment
    	if(snakeDir == UP) {
    		for (int i = 0; i < segment; i++) {
    			if(segments.get(i).getX() == headX - 10 && segments.get(i).getY() == headY) {
    				food = 1;
    				segments.clear();
    				burp = new Segment(headX, headY);
    				segments.add(burp);
    				break;
    			}
    		}
    	}
    	else if(snakeDir == DOWN) {
    		for (int i = 0; i < segment; i++) {
    			if(segments.get(i).getX() == headX + 10 && segments.get(i).getY() == headY) {
    				food = 1;
    				segments.clear();
    				burp = new Segment(headX, headY);
    				segments.add(burp);
    				break;
    			}
    		}
    	}
    	else if(snakeDir == LEFT) {
    		for(int i = 0; i < segment; i++) {
    			if(segments.get(i).getX() == headX && segments.get(i).getY() == headY - 10) {
    				food = 1;
    				segments.clear();
    				burp = new Segment(headX, headY);
    				segments.add(burp);
    				break;
    			}
    		}
    	}
    	else if(snakeDir == RIGHT) {
    		for(int i = 0; i < segment; i++) {
    			if(segments.get(i).getX() == headX && segments.get(i).getY() == headY - 10) {
    				food = 1;
    				segments.clear();
    				burp = new Segment(headX, headY);
    				segments.add(burp);
    				break;
    			}
    		}
    	}
    
    	
    }

    /*
     * Control methods
     * 
     * These methods are used to change what is happening to the snake
     */

    @Override
    public void keyPressed() {
        // Set the direction of the snake according to the arrow keys pressed
        if(keyCode == 38) {
        	snakeDir = UP;
        }
        if(keyCode == 40) {
        	snakeDir = DOWN;
        }
        if(keyCode == 37) {
        	snakeDir = LEFT;
        }
        if(keyCode == 39) {
        	snakeDir = RIGHT;
        }
        if(keyCode == 68) {
        	snakeDir = RIGHT;
        }
        if(keyCode == 87) {
        	snakeDir = UP;
        }
        if(keyCode == 83) {
        	snakeDir = DOWN;
        }
        if(keyCode == 65) {
        	snakeDir = LEFT;
        }
    }

    void move() {
        // Change the location of the Snake head based on the direction it is moving.

       
        if (snakeDir == UP) {
            // Move head up
        	headY-=10;
        } else if (snakeDir == DOWN) {
            // Move head down
            headY+=10;
        } else if (snakeDir == LEFT) {
            headX-=10;
        } else if (snakeDir == RIGHT) {
            headX+=10;
        }
        
    }

    void checkBoundaries() {
        // If the snake leaves the frame, make it reappear on the other side
        if(headY>500) {
        	headY = 0;
        }
        if(headX>500) {
        	headX=0;
        }
        if(headY<0) {
        	headY = 500;
        }
        if(headX<0) {
        	headX=500;
        }
    }

    void eat() {
    	
        // When the snake eats the food, its tail should grow and more
        // food appear
    	if((headX == foodX)&&(headY == foodY)) {
    		dropFood();
    		drawFood();
    		food++;
    		burp = new Segment(headX,headY);
    		segments.add(burp);
    		drawTail();
    	}
    	
    }

    static public void main(String[] passedArgs) {
        PApplet.main(LeagueSnake.class.getName());
    }
}
