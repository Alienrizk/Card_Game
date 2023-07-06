import javax.swing.JFrame;
import javax.swing.JOptionPane;

import cardgames.Card;
import cardgames.Deck;
import cardgames.GUI;

public class NurkenProj7{
    public static void main(String[]args){
        int amount = 500; // in the beginning of the game the player has $500. 
        
        int wantTo = JOptionPane.YES_OPTION; // the wantTo variable is set to YES option meaning the user wants to play the game until the variable is set to NO option.
        GUI theGUI = new GUI(2, true); // Initially will two cards and a deck card.
        Deck theDeck = new Deck();
        // The while loop runs the code while the player's amount of money is more than zero and wantTo is set to YES option.
        while(amount > 0 && wantTo == JOptionPane.YES_OPTION){
            // declaring references to GUI object (using parameterized constructor) and Deck object (using default constructor).
            
            theDeck.shuffleDeck(); // Shuffling the deck  
            theGUI.showAmount(amount); // Showing the player's initial amount of money.
            
            // Declaring references to two cards that were dealed from the deck.
            Card c1 = theDeck.dealCard(); 
            Card c2 = theDeck.dealCard(); 
            
            // Creating variables with minimum and maximum integer values.
            int min; 
            int max;
            
            // If statement finds which of the dealed cards has a greater value and displays them in ascending order.
            if(c1.getValue()>c2.getValue()){ // Case 1: when c1 value is greater than c2 value
                theGUI.showCard(c2); 
                theGUI.showCard(c1);
                
                // sets min to c2 and max to c1
                max = c1.getValue();
                min = c2.getValue();
            } else{ // Case 2: when c2 value is greater than c1 value
                theGUI.showCard(c1);
                theGUI.showCard(c2);
                
                // sets min to c1 and max to c2
                min = c1.getValue();
                max = c2.getValue();
            }
            // Determines if the user wants to bet that the next card comes before, between, or after the two cards that are displayed using JOptionPane and values 0, 1, and 2.
            int response = Integer.parseInt(JOptionPane.showInputDialog("o = before\n1 = between\n2 = after"));
            
            // Prompt the user for his/her bet, storing it in a double varible, and displaying it to the user.
            double bet = Double.parseDouble(JOptionPane.showInputDialog("Your Bet: ")); 
            theGUI.showBet(bet);
            
            // Dealing third card (deck card) and declaring a reference to it to c3. Then it displays the card to the user.
            Card c3 = theDeck.dealCard();
            theGUI.showDeckCard(c3);
            
            // Creating the win variable to false for further cases.
            boolean win = false;
            
            // The if statements consider user's bet (that the next card comes before, between, or after the two cards) and determine if the user won the bet.
            if(response == 0){ 
                if(c3.getValue()<min){ // If the user bet before the two cards and the deck card's value is less than the minimum then win is true.
                    win = true;
                }
            }else if(response == 1){
                if(c3.getValue()>min && c3.getValue()<max){ // If the user bet between the two cards and the deck card's value is between the cards then win is true.
                    win = true;
                }
            }else if(response == 2){
                if(c3.getValue()> max){ // If the user bet after the two cards and the deck card's value is more than the maximum then win is true.
                    win = true;
                }
            }
            
            // If statement determine whether to add the double bet value to the amount or subtract it.
            // Displays the result to the user (whether the user won or lost)
            // Displays the bet and new amount to the user.
            if(win){  
                amount += 2*bet;
                theGUI.showBet(bet);
                theGUI.showMessage("You won!");
                theGUI.showAmount(amount);
            }else{
                amount -= bet;
                theGUI.showBet(bet);
                theGUI.showMessage("You lost :(");
                theGUI.showAmount(amount);
            }
            
            // Determines if the user wants to play the game again using YES or NO option 
            wantTo = JOptionPane.showConfirmDialog(new JFrame(), "Do you wanna play out? ;)", "Game over", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            
            // if statement determines if the user has enough money to replay the game.
            // if the user's amount of money is less than or equal to 0 then the loop breaks.
            if(amount <= 0){
                theGUI.showMessage("You don't have money");
                break;
            }
            theGUI.clearPlayerCards();
            
        }
    }
}
    

