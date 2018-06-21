/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package github.simon.pathfinding.algorithms;

import github.simon.pathfinding.Node;
import github.simon.pathfinding.NodeMap;
import github.simon.pathfinding.Path;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author btb
 * @param <T>
 */
public abstract class Pathfinder <T extends Node> {
    
    public abstract Path<T> findPath (NodeMap<T> nodemap, int start_x, int start_y, int end_x, int end_y, long max_time);
    
    
    
    public Set<T> getNeighbours (NodeMap<T> nodemap, Node n) {
        Set<T> neighbours = new HashSet<> ();
        boolean diagonal = nodemap.allowDiagonal();
        int n_x = n.getX();
        int n_y = n.getY();
        
        for (Direction dir : Direction.values()) {
            if ( (!diagonal && !dir.DIAGONAL) || diagonal ) {
                T neighbour = nodemap.getNode(n_x+dir.X, n_y+dir.Y);
                neighbours.add(neighbour);
            }
        }
        
        return neighbours;
    }
    
    public boolean sameCoords (T t1, T t2) {
        if (t1==null || t2==null) return false;
        return t1.getX()==t2.getX() && t1.getY()==t2.getY();
    }
    
    protected Path<T> reconstructPath (final Path<T> path, T last) {
        path.addStep(last);
        
        T current;
        T predecessor = (T) last.getPredecessor();
        while (predecessor != null) {
            path.addStep(predecessor);
            current = predecessor;
            predecessor = (T) current.getPredecessor();
            if (sameCoords(predecessor, path.getStart()))
                break;
        }
        
        // path is reversed!!!
        return path;
    }
    
}
