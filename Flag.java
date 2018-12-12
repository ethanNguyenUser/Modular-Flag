package ethanNguyen;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JApplet;
import java.util.Scanner;

public class Flag extends JApplet {
	
    // SCALE FACTORS (A through L)
    //
    // Note: Constants in Java should always be ALL_CAPS, even
    // if we are using single letters to represent them
    //
    // NOTE 2: Do not delete or change the names of any of the
    // variables given here

    // Set the constants to exactly what is specified in the documentation

	private static final long serialVersionUID = 1L;
	private int scale = 800;                  //Default scale of flag
	private final int STRIPES = 13;
	private final double A = 1.0;             // Hoist (width) of flag
    private final double B = 1.9;             // Fly (length) of flag
    private final double C = 0.5385;          // Hoist of Union
    private final double D = 0.76;            // Fly of Union
    private final double E = 0.054;           // See flag specification
    private final double F = 0.054;           // See flag specification
    private final double G = 0.063;           // See flag specification
    private final double H = 0.063;           // See flag specification
    private final double K = 0.0616;          // Diameter of star
    private final double L = 0.0769;          // Width of stripe
    //private double scaleA;                  // Unused
    private double scaleB;
    private double scaleC;
    private double scaleD;
    private double scaleE;
    private double scaleF;
    private double scaleG;
    private double scaleH;
    private double scaleK;
    private double scaleL;
    private final Color FLAGRED = new Color(191, 10, 48);
    private final Color FLAGBLUE = new Color(0, 40, 104);
    private final int MINSCALE = 87;

 // init() will automatically be called when an applet is run
    public void init() {
        askScale();
        System.out.print("The flag scale is now " + scale);
        setSize((int) (scale * B), (int) (scale * A));
        repaint();
    }

 // paint() will be called every time a resizing of an applet occurs
    public void paint(Graphics g) {
    	
    	//Make window-resizing proportional
        if(getWidth() / getHeight() < 1.9) {
            scale = (int) (getWidth() / 1.9);
        }
        else {
            scale = getHeight();
        }
        
        //Enforce minimum scale
        if(scale < MINSCALE)
        	scale = MINSCALE;
        
        changeScales();
        drawBackground(g);
        drawStripes(g);
        drawField(g);
        drawStars(g);
    }
    
    private void changeScales() {
        //scaleA = A * scale;                 // Unused
        scaleB = B * scale;
        scaleC = C * scale;
        scaleD = D * scale;
        scaleE = E * scale;
        scaleF = F * scale;
        scaleG = G * scale;
        scaleH = H * scale;
        scaleK = K * scale;
        scaleL = L * scale;
    }
    
    private void drawBackground(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
    
    private void drawStripes(Graphics g) {
        g.setColor(FLAGRED);
        for(int k = 0; k < STRIPES; k += 2) {
            g.fillRect(0, (int) (k * scaleL), (int) scaleB, (int) scaleL);
        }
    }

    private void drawField(Graphics g) {
        g.setColor(FLAGBLUE);
        g.fillRect(0, 0, (int) scaleD, (int) scaleC);
    }

    private void drawStars(Graphics g) {
    	
    	final int ODDROWS = 5;            // # of odd row stars
        final int EVENROWS = 4;           // # of even row stars
        final int ODDCOLS = 6;            // # of odd column stars
        final int EVENCOLS = 5;           // # of odd column stars
        
    	g.setColor(Color.WHITE);
        for(int k = 0; k < ODDCOLS; k++) {
            for(int j = 0; j < ODDROWS; j++) {
                drawStar(g, (int) (scaleH * k * 2 + scaleH), (int) (scaleF * j * 2 + scaleE));
            }
        }
        
        for(int k = 0; k < EVENCOLS; k++) {
            for(int j = 0; j < EVENROWS; j++) {
                drawStar(g, (int) (scaleH * k * 2 + scaleH + scaleG), (int) (scaleF * j * 2 + scaleE + scaleF));
            }
        }
        
    }
    
    private void drawStar(Graphics g, int x, int y) {
        
    	// Draws circle as placeholder
    	// g.fillOval((int) (x - 0.5 * scaleK), (int) (y - 0.5 * scaleK), (int) scaleK, (int) scaleK);
    	
    	final int NUMPOINTS = 10;           // # of points in star
    	double radius = scaleK / 2;
    	
    	int xPoints[] = new int[NUMPOINTS];
    	int yPoints[] = new int[NUMPOINTS];
    	
    	double innerRadius = radius * Math.sin(Math.toRadians(18) / Math.sin(Math.toRadians(54)));

		for (int k = 18; k < 360; k += 72) {
			xPoints[(k - 18) / 36] = x + (int) (radius * Math.cos(Math.toRadians(k)));
			yPoints[(k - 18) / 36] = y - (int) (radius * Math.sin(Math.toRadians(k))); 
		}

		for (int k = 54; k < 360; k += 72) {
			xPoints[(k - 18) / 36] = x + (int) (innerRadius * Math.cos(Math.toRadians(k)));
			yPoints[(k - 18) / 36] = y - (int) (innerRadius * Math.sin(Math.toRadians(k))); 
		}
		
        g.fillPolygon(xPoints, yPoints, NUMPOINTS);
        
    }
    
    //asks the user for scale
    private void askScale() {
    	
    	final String SEPERATOR = "-----------------------------------------------------------------------";
    	Scanner input = new Scanner(System.in);
    	String inputString = "";
    	
    	System.out.print(SEPERATOR
    			+ "\nScalable American Flag"
    			+ "\n" + SEPERATOR
    			+ "\nEnter an integer value for the flag scale (>=" + MINSCALE + ")"
    			+ "\nEnter \"d\" for default scale (" + scale + ")"
    			+ "\nEnter \"q\" to quit the program"
    			+ "\n" + SEPERATOR
    			+ "\nUser Input: ");
    	
    	inputString = input.nextLine();
    	
    	System.out.println(SEPERATOR);
    	
    	if(inputString.equals("d")) {
    		input.close();
    		return;
    	}
    	
    	if(inputString.equals("q")) {
    		System.out.println("Program Sucessfully Terminated");
    		System.exit(0);
    	}
    	
    	try {
            scale = Integer.parseInt(inputString);
        }
    	
        catch(Exception e) {
            System.out.println("Unexpected input");
            askScale();
        }
    	
    	if(scale < MINSCALE) {
    		System.out.println("Flag scale must be greater than or equal to " + MINSCALE);
    		askScale();
    	}
        
        input.close();
        
    }
}
