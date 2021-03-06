/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package github.simon.pathfinding;

import java.util.Comparator;

/**
 *
 * @author btb
 * @param <T>
 */
public interface NodeMap <T extends Node> {
    
    public int getSizeX ();
    
    public int getSizeY ();
    
    public T getNode (int x, int y);
    
    public Comparator<T> getOpenNodeSorter ();
    
    public boolean allowDiagonal ();
    
}
