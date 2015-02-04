package com.een574;


import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Environment {
    public String TAG = Environment.class.getSimpleName();

    //List to handle the dirt in grid: true = dirt false = clean
    public List<Boolean> threebythree;

    //List to place obstacles in the activity
    public List<Boolean> obstacles;

    public Environment() {
        threebythree = new ArrayList<Boolean>();
        threebythree.add(0, false);
        threebythree.add(1, false);
        threebythree.add(2, false);
        threebythree.add(3, false);
        threebythree.add(4, false);
        threebythree.add(5, false);
        threebythree.add(6, false);
        threebythree.add(7, false);
        threebythree.add(8, false);

        obstacles = new ArrayList<Boolean>();
        obstacles.add(0, false);
        obstacles.add(1, false);
        obstacles.add(2, false);
        obstacles.add(3, false);
        obstacles.add(4, false);
        obstacles.add(5, false);
        obstacles.add(6, false);
        obstacles.add(7, false);
        obstacles.add(8, false);
    }
    public List<Boolean> getThreebythree() {
        return this.threebythree;
    }

    public List<Boolean> getObstacles() {
        return this.obstacles;
    }

    public void printEnvironment() {
        Log.i(TAG, threebythree.toString());
    }

    public void reset() {
        threebythree.set(0, false);
        threebythree.set(1, false);
        threebythree.set(2, false);
        threebythree.set(3, false);
        threebythree.set(4, false);
        threebythree.set(5, false);
        threebythree.set(6, false);
        threebythree.set(7, false);
        threebythree.set(8, false);

        obstacles.set(0, false);
        obstacles.set(1, false);
        obstacles.set(2, false);
        obstacles.set(3, false);
        obstacles.set(4, false);
        obstacles.set(5, false);
        obstacles.set(6, false);
        obstacles.set(7, false);
        obstacles.set(8, false);

    }
}