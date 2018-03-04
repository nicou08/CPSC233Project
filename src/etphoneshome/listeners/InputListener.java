package etphoneshome.listeners;

import etphoneshome.UILauncher;
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
     * Constructor for the class
     *
     * @param character gives InputListener the character associated with InputListener
     */
    public InputListener(Character character) {
        this.character = character;
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


    /**
     * Generate {@code EventHandler} for the key pressed down event
     *
     * @return {@code EventHandler} for the key pressed down event
     */
    public EventHandler<KeyEvent> getKeyPressedEvent() {

        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                String input = e.getText().toLowerCase();
                if (input.equals("w") || input.equals("up")) {
                    character.setHoldingUp(true);
                }
                if (input.equals("a") || input.equals("left")) {
                    character.setHoldingLeft(true);
                }
                if (input.equals("d") || input.equals("right")) {
                    character.setHoldingRight(true);
                }
            }
        };
    }

    /**
     * Generate {@code EventHandler} for the key released event
     *
     * @return {@code EventHandler} for the key released event
     */
    public EventHandler<KeyEvent> getKeyReleasedEvent() {

        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                String input = e.getText().toLowerCase();
                if (input.equals("w") || input.equals("up")) {
                    character.setHoldingUp(false);
                }
                if (input.equals("a") || input.equals("left")) {
                    character.setHoldingLeft(false);
                }
                if (input.equals("d") || input.equals("right")) {
                    character.setHoldingRight(false);

                }
            }
        };
    }

    /**
     * Update thecharacter's velocity based on the tick of the game
     *
     * @param tick Current given tick of the animation
     */
    public void updateVelocities() {
        Velocity velocity = character.getVelocity();
        if (character.isHoldingRight() && velocity.getHorizontalVelocity() <= 10) {
            velocity.changeHorizontalVelocity(1);
        } else if (!character.isHoldingRight() && velocity.getHorizontalVelocity() > 0) {
            int newVelocity = velocity.getHorizontalVelocity() - 1 < 0 ? 0 : velocity.getHorizontalVelocity() - 1;
            velocity.setHorizontalVelocity(newVelocity);
        }

        if (character.isHoldingLeft() && velocity.getHorizontalVelocity() >= -10) {
            velocity.changeHorizontalVelocity(-1);
        } else if (!character.isHoldingLeft() && velocity.getHorizontalVelocity() < 0) {
            int newVelocity = velocity.getHorizontalVelocity() + 1 > 0 ? 0 : velocity.getHorizontalVelocity() + 1;
            velocity.setHorizontalVelocity(newVelocity);
        }

        // gravity
        if (character.isJumping()) {
            velocity.changeVerticalVelocity(1);
        }

        if (character.isHoldingUp() && !character.isJumping()) {
            character.setJumping(true);
            velocity.setVerticalVelocity(-20);
        }

    }


    /**
     * Checks if player is on the ground or not
     *
     * @return whether the player is on the ground or not
     */
    public boolean onGround() {
        return character.getLocation().getYcord() > UILauncher.getGraphicsRepainter().HEIGHT - 100 - character.getEntitySprite().getHeight();
    }
}


