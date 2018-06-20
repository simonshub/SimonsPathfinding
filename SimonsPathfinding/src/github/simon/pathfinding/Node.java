/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package github.simon.pathfinding;

/**
 *
 * @author btb
 */
public interface Node {
    
    public int getX ();
    
    public int getY ();
    
    public double getCost ();
    
    public boolean isBlocked ();
    
}
