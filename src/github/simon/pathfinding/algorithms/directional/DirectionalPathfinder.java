/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package github.simon.pathfinding.algorithms.directional;

import github.simon.pathfinding.Node;
import github.simon.pathfinding.NodeMap;
import github.simon.pathfinding.Path;
import github.simon.pathfinding.algorithms.Pathfinder;
import java.util.List;

/**
 *
 * @author XyRoN
 * @param <T>
 */
public class DirectionalPathfinder<T extends Node> extends Pathfinder<T> {

    @Override
    public Path findPath(NodeMap<T> nodemap, int start_x, int start_y, int goal_x, int goal_y, long max_duration) {
        return findPath (nodemap,start_x,start_y,goal_x,goal_y,max_duration,0);
    }
    
    
    public Path findPath(NodeMap<T> nodemap, int start_x, int start_y, int goal_x, int goal_y, long max_duration, int recursive_iter) {
        T start = nodemap.getNode(start_x, start_y);
        T goal = nodemap.getNode(goal_x, goal_y);
        
        Path<T> result = new Path (start, goal);
        
        T next = start;
        
        long start_time = System.currentTimeMillis();
        while (next!=null) {
            if (System.currentTimeMillis() - start_time > max_duration)
                return null;
            
            T current = next;
            
            if (sameCoords(current, goal))
                return reconstructPath (result, current);
            
            next = null;
            List<Direction> directions = Direction.getSuggestedDirections(current, goal, nodemap.allowDiagonal());
            for (Direction dir : directions) {
                T to_add = (T) current.neighbour(nodemap, dir);
                if (to_add==null || to_add.isBlocked())
                    continue;
                
                if (sameCoords(to_add, goal)) {
                    next = to_add;
                    next.setPredecessor(current);
                    break;
                }
                
                if (next!=null && next.getCost(current) == to_add.getCost(current) && Math.random()>=0.5 ) {
                    next = to_add;
                } else if (next==null || next.getCost(current) > to_add.getCost(current)) {
                    next = to_add;
                }
            }
            
            if (next!=null)
                next.setPredecessor(current);
            else if (recursive_iter<5)
                return findPath( nodemap, start_x, start_y, goal_x, goal_y, max_duration - (System.currentTimeMillis() - start_time), recursive_iter+1 );
        }
        
        return null;
    }
    
}
