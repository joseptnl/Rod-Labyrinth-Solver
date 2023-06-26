
package view;

import controller.ControllerEvent;
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
    }
    
    public void start () {
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
        /*
        char[][] lab = {
            {'.','.','.','.','.','.','.','.','.','.'},
            {'.','#','.','.','.','.','#','.','.','.'},
            {'.','#','.','.','.','.','.','.','.','.'},
            {'.','.','.','.','.','.','.','.','.','.'},
            {'.','.','.','.','.','.','.','.','.','.'},
            {'.','#','.','.','.','.','.','.','.','.'},
            {'.','#','.','.','.','#','.','.','.','.'},
            {'.','.','.','.','.','.','#','.','.','.'},
            {'.','.','.','.','.','.','.','.','.','.'},
            {'.','.','.','.','.','.','.','.','.','.'}               
        };
        */
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
        main.notify(new ControllerEvent());
    }
    
    private void showResult (int result) {
        System.out.println("The solution is: " + result);
    }

    @Override
    public void notify(Event e) {
        ViewEvent event = (ViewEvent) e;
        switch(event.viewEventType) {
            case SOLUTION:
                showResult(event.result);
                break;
        }
    }
}
