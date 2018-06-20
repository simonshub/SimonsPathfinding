/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package github.simon.pathfinding.algorithms;

import github.simon.pathfinding.Node;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author btb
 */
public enum Direction {
    UP          (false,0,-1),
    DOWN        (false,0,1),
    LEFT        (false,-1,0),
    RIGHT       (false,1,0),
    
    UP_LEFT     (true,-1,1),
    UP_RIGHT    (true,1,1),
    DOWN_LEFT   (true,-1,-1),
    DOWN_RIGHT  (true,1,-1),
    
    ;
    
    public final boolean DIAGONAL;
    public final int X,Y;
    
    Direction (boolean diagonal, int x, int y) {
        DIAGONAL=diagonal;
        X=x; Y=y;
    }
    
    public static Direction combine (Direction d1, Direction d2) {
        switch (d1) {
            case UP :
                if (LEFT.equals(d2))
                    return UP_LEFT;
                else if (RIGHT.equals(d2))
                    return UP_RIGHT;
                break;
            case DOWN :
                if (LEFT.equals(d2))
                    return DOWN_LEFT;
                else if (RIGHT.equals(d2))
                    return DOWN_RIGHT;
                break;
            case LEFT :
                if (UP.equals(d2))
                    return UP_LEFT;
                else if (DOWN.equals(d2))
                    return DOWN_LEFT;
                break;
            case RIGHT :
                if (UP.equals(d2))
                    return UP_RIGHT;
                else if (DOWN.equals(d2))
                    return DOWN_RIGHT;
                break;
        }
        
        return null;
    }
    
    public static List<Direction> getSuggestedDirections (Node from, Node to, boolean diagonal) {
        int dx = from.getX() - to.getX();
        int dy = from.getY() - to.getY();
        
        List<Direction> result = new ArrayList<> ();
        
        if (dx < 0)
            result.add(RIGHT);
        else if (dx > 0)
            result.add(LEFT);
        
        if (dy < 0)
            result.add(DOWN);
        else if (dy > 0)
            result.add(UP);
        
        // if diagonal movement is allowed, and a diagonal pattern can be established,
        // then add it to the list of possible directions
        if (diagonal && result.size()==2) {
            Direction diagonal_direction = combine(result.get(0), result.get(1));
            
            if (diagonal_direction!=null)
                result.add(diagonal_direction);
        }
        
        return result;
    }
}
