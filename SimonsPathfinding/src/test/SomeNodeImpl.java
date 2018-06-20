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
public class SomeNodeImpl implements Node {
    
    private double difficulty;
    
    private int x, y;
    
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
    public double getCost() {
        return difficulty;
    }

    @Override
    public boolean isBlocked() {
        return difficulty >= 0.8;
    }
    
    
    
    @Override
    public String toString () {
        return String.format("[%2d,%2d] "+ (isBlocked() ? "blkd" : "%.2f"), x, y, difficulty);
    }
    
}
