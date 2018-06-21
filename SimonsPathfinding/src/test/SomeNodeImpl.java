/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import github.simon.pathfinding.Node;

/**
 *
 * @author btb
 */
public class SomeNodeImpl extends Node {
    
    private final int x, y;
    
    private final double difficulty;
    
    public SomeNodeImpl (int x, int y) {
        this.x = x;
        this.y = y;
        this.difficulty = Math.random();
    }
    
    

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public double getCost(Node previous) {
        return difficulty;
    }

    @Override
    public boolean isBlocked() {
        return difficulty >= 0.6;
    }
    
    @Override
    public String toString () {
        return String.format("[%2d,%2d] "+ (isBlocked() ? "--" : "%2d"), x, y, (int)(difficulty*100f));
    }
    
}
