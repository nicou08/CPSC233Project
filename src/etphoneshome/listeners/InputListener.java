package etphoneshome.listeners;

import etphoneshome.entities.characters.Character;
import etphoneshome.objects.Location;
import etphoneshome.objects.Velocity;
import etphoneshome.graphics.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

/**
 * This class gets input from the user and updates the character based on that input
 */

public class InputListener {

    /**
     * character associated with {@code InputLIstener}
     */
    private Character character;

    /**
     * location associated with the character
     */
    private Location location;

    private Velocity velocity;

    private GraphicsRepainter graphicsRepainter;

    //private int x = location.getXcord();

    //private int y = location.getYcord();

    //private double velx = velocity.getHorizontalVelocity();

    //private double vely = velocity.getVerticalVelocity();


    /**
     * Constructor for the class
     *
     * @param character gives InputListener the character associated with InputListener
     */
    public InputListener(Character character) {
        this.character = character;
        location = character.getLocation();
    }

    public void setCharacter(Character character) {
        this.character = character;
    }


    /**
     * Uses method nextMove to get input from the user and processes the input and updates the character based on it
     */
    /**

     char move = nextMove();

     // movement to the left
     if (move == 'a') {
     int initialX1 = location.getXcord();
     location.setXcord(initialX1 - 1);
     character.setLocation(location);

     // movement up
     } else if (move == 'w') {
     int initialY1 = location.getYcord();
     location.setYcord(initialY1 + 1);
     character.setLocation(location);

     // movement down
     } else if (move == 's') {
     int initialY2 = location.getYcord();
     location.setYcord(initialY2 - 1);
     character.setLocation(location);

     // movement to the right
     } else {
     int initialX2 = location.getXcord();
     location.setXcord(initialX2 + 1);
     character.setLocation(location);
     }


     }

     /**
     * Gets the direction the user wants to move. If user enters invalid entry it will prompt them to try again
     *
     * @return direction the user wants to move
     */

    /**private char nextMove() {

     //sets basic values
     char move = 'q';
     boolean validmove = false;
     int yCord = location.getYcord();
     int xCord = location.getXcord();

     //while the move is invalid it will loop until it is
     while (validmove != true) {
     Scanner reader = new Scanner(System.in);
     try {
     System.out.print("Which direction would you like to move?: ");
     String choice = reader.next();
     if (choice.length() > 1) {
     System.out.println("Please select a valid direction ('w','a','s','d') ");
     continue;
     }
     move = choice.charAt(0);
     ;
     if (move == 'a' || move == 'w' || move == 'd' || move == 's') {
     if (move != 's' && move != 'a') {
     validmove = true;

     //making sure user doesn't go below ground
     } else if (move == 's' && yCord != 0) {
     validmove = true;

     //making sure user doesn't exit the leave game field on the left
     } else if (move == 'a' && xCord != 0){
     validmove = true;

     } else {
     System.out.println("Cannot move down. Try another direction.");
     }
     } else {
     System.out.println("Please select a valid direction ('w','a','s','d') ");
     }
     } catch (InputMismatchException e) {
     System.out.println("Please enter 'a' for left, 'w' for up, 's' for down and 'd' for right ");
     reader.next();
     }
     }
     return move;

     }
     */
    /**
     * public static void main(String[] args) {
     * <p>
     * Character tester = new ET();
     * Location locate = tester.getLocation();
     * boolean tryagain = true;
     * Scanner reader = new Scanner(System.in);
     * <p>
     * while (tryagain) {
     * int locationX = locate.getXcord();
     * int locationY = locate.getYcord();
     * InputListener input = new InputListener(tester);
     * input.processInput();
     * if (locate.getXcord() == locationX + 1) {
     * System.out.println("If you moved right then test was successful");
     * } else if (locate.getYcord() == locationY + 1) {
     * System.out.println("if you moved up then test was successful");
     * } else if (locate.getXcord() == locationX - 1) {
     * System.out.println("if you moved left then test successful");
     * } else {
     * System.out.println("if you moved right then test sucessful");
     * }
     * <p>
     * System.out.print("would you like to try again? Enter y if you would like to try again.: ");
     * String decision = reader.next();
     * if (decision != "y") {
     * tryagain = false;
     * }
     * <p>
     * <p>
     * }
     * <p>
     * }
     */

    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        //x = location.setXcord(x+velx);
        //y = location.setYcord(y+vely);
        graphicsRepainter.repaint();
    }

    public EventHandler<KeyEvent> getKeyPressedEvent() {
        // TODO Auto-generated method stub
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                String input = e.getText().toLowerCase();
                System.out.println("key event: " + input);
                if (input.equals("w") || input.equals("up")) {
                    // update character.getVelocity()
                    character.getVelocity().changeVerticalVelocity(-10);
                }
                if (input.equals("a") || input.equals("left")) {
                    // update character.getVelocity()
                    character.getVelocity().changeHorizontalVelocity(-10);
                }
                if (input.equals("d") || input.equals("right")) {
                    // update character.getVelocity()
                    character.getVelocity().changeHorizontalVelocity(10);
                }
            }
        };
    }


    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        // update character.getVelocity()
        // NOTE: You cant just set the vertical velocity to 0, what if they press the W key and then release, your old code
        // (if working) would've set the velocity to 0 instantly leaving the character stuck in the air without coming back down,
        // gravity is handled in the GraphicsRepainter so you dont have to worry about that, but dont touch the verticalVelocity in this method
        character.getVelocity().changeHorizontalVelocity(0);
    }


    public void keyTyped(KeyEvent arg0) {
        // TODO Auto-generated method stub

    }


}


