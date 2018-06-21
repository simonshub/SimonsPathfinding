/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package github.simon.pathfinding;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author btb
 * @param <T>
 */
public class Path <T extends Node> {
    
    private final T start, goal;
    private final List<T> steps;
    
    
    
    public Path (T start, T goal) {
        this.start = start;
        this.goal = goal;
        this.steps = new ArrayList<> ();
    }
    
    
    
    public List<T> getAllSteps () {
        return steps;
    }
    
    public T getStart () {
        return start;
    }
    
    public T getGoal () {
        return goal;
    }
    
    public T getStep (int index) {
        return steps.get(index);
    }
    
    public T getLastStep () {
        if (steps.isEmpty())
            return null;
        
        return steps.get(steps.size()-1);
    }
    
    public void addStep (T step) {
        steps.add(step);
    }
    
}
