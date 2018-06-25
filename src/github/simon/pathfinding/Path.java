/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package github.simon.pathfinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author btb
 * @param <T>
 */
public class Path <T extends Node> {
    
    private final T start, goal;
    private final Map<Integer,T> steps;
    
    
    
    public Path (T start, T goal) {
        this.start = start;
        this.goal = goal;
        this.steps = new HashMap<> ();
    }
    
    
    
    public List<T> getAllSteps () {
        List<T> step_list = new ArrayList<> ();
        for (int i=0;i<steps.size();i++) {
            step_list.add(steps.get(i));
        }
        return step_list;
    }
    
    public Set<T> getAllUniqueSteps () {
        return new HashSet<> (steps.values());
    }
    
    public T getStart () {
        return start;
    }
    
    public T getGoal () {
        return goal;
    }
    
    public T getStep (int index) {
        if (index<0 || index>=steps.size()) {
            throw new IndexOutOfBoundsException ("Path step index "+index+" is incorrect; size="+steps.size());
        }
        
        return steps.get(index);
    }
    
    public T getLastStep () {
        if (steps.isEmpty())
            return null;
        
        return steps.get(steps.size()-1);
    }
    
    public boolean addStep (T step) {
        if (!steps.containsValue(step)) {
            steps.put(steps.size(), step);
            return true;
        }
        return false;
    }
    
}
