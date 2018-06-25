/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import github.simon.pathfinding.NodeMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author btb
 */
public class SomeNodeMapImpl implements NodeMap<SomeNodeImpl> {
    
    private final int width, height;
    private final List<List<SomeNodeImpl>> map;
    
    public SomeNodeMapImpl (int width, int height) {
        this.width = width;
        this.height = height;
        this.map = new ArrayList<> ();
        
        for (int r=0;r<height;r++) {
            this.map.add(new ArrayList<> ());
            
            for (int c=0;c<width;c++) {
                this.map.get(r).add(new SomeNodeImpl (c,r));
            }
        }
    }
    
    

    @Override
    public int getSizeX() {
        return width;
    }

    @Override
    public int getSizeY() {
        return height;
    }

    @Override
    public SomeNodeImpl getNode(int x, int y) {
        if (y>=height || y<0 || x>=width || x<0)
            return null;
        
        return map.get(y).get(x);
    }

    @Override
    public boolean allowDiagonal() {
        return true;
    }
    
    
    
    @Override
    public String toString () {
        String contents = "";
        
        for (int r=0;r<height;r++) {
            for (int c=0;c<width;c++) {
                contents += getNode(c, r).toString();
            }
            contents += "\n";
        }
        
        return contents;
    }

    @Override
    public Comparator<SomeNodeImpl> getOpenNodeSorter() {
        return (SomeNodeImpl o1, SomeNodeImpl o2) -> (int) (o1.getCost(null) - o2.getCost(null));
    }
    
}
