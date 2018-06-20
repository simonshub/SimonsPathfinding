/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package github.simon.pathfinding.algorithms.greedy;

import github.simon.pathfinding.Node;
import github.simon.pathfinding.NodeMap;
import github.simon.pathfinding.Path;
import github.simon.pathfinding.algorithms.Direction;
import github.simon.pathfinding.algorithms.Pathfinder;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author btb
 * @param <T>
 */
public class GreedyPathfinder<T extends Node> extends Pathfinder<T> {

    @Override
    public Path<T> findPath (NodeMap<T> nodemap, int start_x, int start_y, int end_x, int end_y, int max_attempts) {
        T start = nodemap.getNode(start_x, start_y);
        T end = nodemap.getNode(end_x, end_y);
        
        int attempt_counter = 0;
        boolean diagonal = nodemap.allowDiagonal();
        List<T> frontiers = new ArrayList<> ();
        
        frontiers.add(start);
        
        Path result = new Path (start, end);
        if (sameCoords(start, end))
            return result;
        
        T current = start;
        while (!sameCoords(current,end) && attempt_counter<max_attempts) {
            List<Direction> directions = Direction.getSuggestedDirections(current, end, diagonal);
            
            for (Direction dir : directions) {
                T newnode = nodemap.getNode(current.getX()+dir.X, current.getY()+dir.Y);
                if (newnode!=null && !newnode.isBlocked()) {
                    frontiers.add(newnode);
                }
            }
            
            frontiers = frontiers.stream().sorted( (t1,t2) -> (int)(t2.getCost() - t1.getCost()) ).collect(Collectors.toList());
            
            attempt_counter++;
        }
        
        return result;
    }
    
}
