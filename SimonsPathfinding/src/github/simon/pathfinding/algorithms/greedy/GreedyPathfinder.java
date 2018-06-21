/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package github.simon.pathfinding.algorithms.greedy;

import github.simon.pathfinding.Node;
import github.simon.pathfinding.NodeMap;
import github.simon.pathfinding.Path;
import github.simon.pathfinding.algorithms.Pathfinder;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

/**
 *
 * @author btb
 * @param <T>
 */
public class GreedyPathfinder<T extends Node> extends Pathfinder<T> {

    @Override
    public Path<T> findPath (NodeMap<T> nodemap, int start_x, int start_y, int goal_x, int goal_y, int max_attempts) {
        T start = nodemap.getNode(start_x, start_y);
        T goal = nodemap.getNode(goal_x, goal_y);
        
        Path<T> result = new Path (start, goal);
        
        PriorityQueue<T> openNodes = new PriorityQueue<> ();
        Set<T> closedNodes = new HashSet<> ();
        
        openNodes.add(start);
        
        while (!openNodes.isEmpty()) {
            T current = openNodes.poll();
            
            if (current==null)
                return null;
            
            if (sameCoords(current, goal))
                return reconstructPath (result, current);
            
            closedNodes.add(current);
            
            List<T> neighbours = (List<T>) current.getNeighbours(nodemap);
            for (T neighbour : neighbours) {
                if (!closedNodes.contains(neighbour) && !openNodes.contains(neighbour) && !neighbour.isBlocked()) {
                    neighbour.setPredecessor(current);
                    openNodes.add(neighbour);
                }
            }
        }
        
        return null;
    }
    
}
