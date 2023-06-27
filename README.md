# Rod labyrinth solver

I created the solver by implementing an A* greedy algorithm that uses heuristic to choose the most efficient path to move the rod through the labyrinth.

## Source code

I coded in Java using the NetBeans Apache IDE, so the src code is in the next path: *LabyrinthSolver/src*

## Play with the program

The console will ask to the user how many dimensions the labyrinth has and then asks for the labyrinth layout. That layout must be introduced line by line and its elements must be writen without any separator between them. An example:

    .........
    #...#....
    ....#....
    .#.....#.
    .#.....#.

However, in the *View* class you can modify the code manually to set the labyrinth. It is commented inside *laberynthInput* method.

## MVC patron

I implemented the MVC patron:

- **Model**: Contains the labyrinth representation.
- **Controller**: Contains the business logic that solves the problem.
- **View**: Allows the user to interact with the program.

The code of each component is divided in different packages called by its name.

Communication between components is done through an EventListener interface that implements the *notify* method. Is a centralized MVC patron design, so the *Main* class has the function of redirecting the events to its right destination.

## A* algorithm

It uses a priority queue that is ordered by the f(x) = g(x) + h(x) function where g(x) is the number of steps taken to get to this state and h(x) its heuristic value, that way is possible to take the optimal state at each iteration. It also plays with a hash-map to store the optimal number of steps to get to each observed state.

Is worth notice that the heuristic is calculated by obtaining the Manhattan distance from the bottom right corner of the labyrinth to the closest rod corner.

I used that method because, using the heuristic, it can find the optimal solution in a short time if we compare to other methods like Depth First Search.

