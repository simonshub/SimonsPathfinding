/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import github.simon.pathfinding.Path;
import github.simon.pathfinding.algorithms.Pathfinder;
import github.simon.pathfinding.algorithms.greedy.GreedyPathfinder;

/**
 *
 * @author btb
 */
public class TestCLI {
    
    public static void main (String... vargs) {
        
        SomeNodeMapImpl map = new SomeNodeMapImpl(800, 400);
        
//        System.out.println("Test Map:\n\n"+map.toString());
        
        Pathfinder<SomeNodeImpl> pf = new GreedyPathfinder ();
        long start = System.currentTimeMillis();
        Path<SomeNodeImpl> path = pf.findPath(map, 0, 0, 200, 200, 5000);
        long end = System.currentTimeMillis();
        
        System.out.println(String.format("Pathfinding finished in %.6f sec", ( (end-start) / 1000f )));
        
        if (path==null) {
            System.err.println("No path found!");
            return;
        } else {
            System.out.println("Path found");
        }
        
        for (SomeNodeImpl node : path.getAllSteps()) {
            System.out.println("\tNode; "+node.toString());
        }
    }
    
}
