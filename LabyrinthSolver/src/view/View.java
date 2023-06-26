
package view;

import java.util.Scanner;
import main.Event;
import main.EventListener;
import main.Main;
import model.ModelEvent;

/**
 *
 * @author josep
 */
public class View implements EventListener {
    private Main main;  
    
    private int[] sizes = new int[2];
    private final Scanner sc = new Scanner(System.in);
    
    public View (Main main) {
        this.main = main;
        
        sizeInput();
        laberynthInput();
        calculateResult();
    }
    
    private void sizeInput () {
        System.out.print("Insert first dimension size (n. of rows i): ");
        sizes[0] = sc.nextInt();
        System.out.print("Insert second dimension size (n. of columns j): ");
        sizes[1] = sc.nextInt();
    }
    
    private void laberynthInput () {
        // Input for the laberynth
        System.out.println("[Insert laberynth layout] You must insert line by line putting the elements together:");
        char[][] lab = new char[sizes[0]][sizes[1]];
        String line = "";
        for (int i = 0; i < sizes[0]; i++) {
            line = sc.next();
            for (int j = 0; j < sizes[1]; j++) {
                lab[i][j] = line.charAt(j);
            }
        }
        // Show the created laberynth
        System.out.println("The created laberynth is the followed: ");
        for (int i = 0; i < sizes[0]; i++) {
            for (int j = 0; j < sizes[1]; j++) {
                System.out.print(lab[i][j]+" ");
            }
            System.out.println("");
        }
        // Sen lab. to model
        main.notify(new ModelEvent(lab));
    }
    
    private void calculateResult () {
        // Activate the controller algorithm execution
        // Show result
    }

    @Override
    public void notify(Event e) {
        
    }
}
