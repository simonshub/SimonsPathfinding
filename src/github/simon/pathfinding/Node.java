/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package github.simon.pathfinding;

import github.simon.pathfinding.algorithms.directional.Direction;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author btb
 */
public abstract class Node {
    
    public abstract int getX ();
    
    public abstract int getY ();
    
    public abstract double getCost (final Node previous);
    
    public abstract boolean isBlocked ();
    
    
    
    private Node predecessor=null;
    
    public void setPredecessor (Node predecessor) {
        this.predecessor = predecessor;
    }
    
    public Node getPredecessor () {
        return predecessor;
    }
    
    public List<? extends Node> getNeighbours (final NodeMap<? extends Node> map) {
        List<Node> results = new ArrayList<> ();
        
        for (Direction dir : Direction.values()) {
            if ( map.allowDiagonal() || (!map.allowDiagonal() && !dir.DIAGONAL) ) {
                Node neighbour = neighbour(map, dir);
                if (neighbour != null)
                    results.add(neighbour);
            }
        }
        
        return results;
    }
    
    public final Node neighbour (final NodeMap<? extends Node> map, Direction direction) {
        return map.getNode(getX()+direction.X , getY()+direction.Y);
    }
    
    
    
    @Override
    public boolean equals (Object obj) {
        try {
            Node node = (Node)obj;
            return node.getX()==getX() && node.getY()==getY();
        } catch (ClassCastException ex) {
            return false;
        }
    }
    
}
