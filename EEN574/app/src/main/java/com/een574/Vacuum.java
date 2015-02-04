package com.een574;


import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Vacuum {
    public String TAG = Vacuum.class.getSimpleName();

    VacuumEnvironment e;
    VacuumLocation currentLocation;
    int x;
    int y;

    String steps = "Actions: ";

    public Vacuum(VacuumEnvironment en) {
        e = en;
    }

    //checks if all locations have been moved to
    public Boolean checkedEveryLocation() {
        for(int i = 0; i < e.n; i++) {
            for(int j = 0; j < e.n; j++) {
                VacuumLocation loc = e.LocationMap.get("l"+i+j);
                if(loc == null) {
                    return false;
                }
                if(!loc.checked) {
                    return false;
                }
            }
        }
        return true;
    }

    public Boolean start() {
        for(int i = 0; i < e.n; i++) {
            for(int j = 0; j < e.n; j++) {
                VacuumLocation loc = e.LocationMap.get("l"+i+j);
                if(!loc.isObstacle) {
                    x = i;
                    y = j;
                    currentLocation = loc;
                    return true;
                }
            }
        }
        return false;
    }

    //return a bool if you can move in that direction
    public Boolean canMove(int mX, int mY) {
        VacuumLocation loc = e.LocationMap.get("l"+mX+mY);
        if(loc == null) {
            //Log.i(TAG, "Cannot move wall");
            return false;
        } else if(loc.isObstacle) {
            //Log.i(TAG, "Cannot move obstacle");
            return false;
        }
        loc.checkedCount = loc.checkedCount+1;
        //Log.i(TAG, "Can move forward");
        return true;
    }

    public int canMoveInt(int xd, int yd) {
        if(canMove(xd,yd)) {
            return 1;
        } else {
            return 0;
        }
    }

    List<VacuumLocation> path = new ArrayList<VacuumLocation>();
    public void moveTo(int mx, int my) {
        this.x = mx;
        this.y = my;
        currentLocation = e.LocationMap.get("l"+mx+my);
    }

    public void moveToLocation(VacuumLocation loc) {
        //move to location
        path.add(loc);
        loc.hasDirt = false;
        loc.checked = true;
        moveTo(loc.x, loc.y);
        loc.moveToCount++;
        //check if junction
        if(canMoveInt(loc.x+1, loc.y) + canMoveInt(loc.x-1, loc.y)+ canMoveInt(loc.x, loc.y+1)+ canMoveInt(loc.x, loc.y-1) > 2) {
            Log.i(TAG, "Location is a junction");
            loc.junction = true;
        } else {
            loc.junction = false;
        }
    }
    public List<VacuumLocation> checkSurroundings() {
        List<VacuumLocation> possibleDirections = new ArrayList<VacuumLocation>();
        currentLocation.checkedCount++;
        if(canMove(currentLocation.x+1, currentLocation.y)) {
            Log.i(TAG, "Can move x+1");
            possibleDirections.add(e.LocationMap.get("l"+(x+1)+y));
        }
        if(canMove(currentLocation.x-1, currentLocation.y)){
            Log.i(TAG, "Can move x-1");
            possibleDirections.add(e.LocationMap.get("l"+(x-1)+y));
        }
        if(canMove(currentLocation.x, currentLocation.y+1) ) {
            Log.i(TAG, "Can move y+1");
            possibleDirections.add(e.LocationMap.get("l"+x+(y+1)));
        }
        if(canMove(currentLocation.x, currentLocation.y-1)) {
            Log.i(TAG, "Can move y-1");
            possibleDirections.add(e.LocationMap.get("l"+x+(y-1)));
        }
        return possibleDirections;
    }

    public VacuumLocation pickLocation(List<VacuumLocation> locat) {
        int where = locat.size()-1;
        VacuumLocation next = locat.get(locat.size()-1);
        if(locat == null) {
            return null;
        } else {
            for(VacuumLocation l : locat) {
                if(l.moveToCount+1 < next.moveToCount) {
                    next = l;
                }
            }
            return next;
        }
    }

    public void moveToLocationThatsNot(VacuumLocation loc) {
        if(canMove(currentLocation.x+1, currentLocation.y) && (loc != e.LocationMap.get('l'+(currentLocation.x+1)+currentLocation.y))) {
            moveToLocation(e.LocationMap.get("l"+(currentLocation.x+1)+currentLocation.y) );
        } else if(canMove(currentLocation.x-1, currentLocation.y) && (loc != e.LocationMap.get('l'+(currentLocation.x-1)+currentLocation.y))){
            moveToLocation(e.LocationMap.get("l"+(currentLocation.x-1)+currentLocation.y));
        } else if(canMove(currentLocation.x, currentLocation.y+1) && (loc != e.LocationMap.get('l'+(currentLocation.x+1)+currentLocation.y))) {
            moveToLocation(e.LocationMap.get("l"+currentLocation.x+(currentLocation.y+1)));
        } else if(canMove(currentLocation.x, currentLocation.y-1) && (loc != e.LocationMap.get('l'+(currentLocation.x+1)+currentLocation.y))) {
            moveToLocation(e.LocationMap.get("l" + currentLocation.x + (currentLocation.y - 1)));
        }
    }

    public VacuumLocation moveBackwards(List<VacuumLocation> path) {
        VacuumLocation loc = path.remove(path.size()-1);
        moveTo(loc.x, loc.y);
        return loc;
    }

    //moves back to the location of the previous junction returns the value of the location move to after the junction
    public VacuumLocation moveBackwardsToJunction(List<VacuumLocation> path) {
        VacuumLocation current = moveBackwards(path);
        VacuumLocation prev = new VacuumLocation();
        while(!current.junction) {
            prev = current;
            //removes current from the stack and moves back to it
            current = moveBackwards(path);
        }
        //add current so the current junction back to the stack
        moveToLocation(current);
        if(prev != null) {
            //make previous equal to previous
            return prev;
        } else {
            return null;
        }
    }

    public Boolean solve() {
        while(!checkedEveryLocation()) {
            Log.i(TAG, "Checking Location: "+currentLocation.x+currentLocation.y);
            VacuumLocation nextSpot = pickLocation(checkSurroundings());
            if(nextSpot!= null) {
                moveToLocation(nextSpot);
            } else {
                Log.i(TAG, "No possible movements");
                break;
            }
        }
        //we solved the maze
        return true;
    }


    public Boolean solveRandom() {
        int MAX_CHECKED = 200;
        while(!checkedEveryLocation() && !(currentLocation.checkedCount > MAX_CHECKED)) {
            List<VacuumLocation> possibleDirections = new ArrayList<VacuumLocation>();
            Log.i(TAG, "Checking Location: "+currentLocation.x+currentLocation.y);
            currentLocation.checkedCount++;
            if(canMove(currentLocation.x+1, currentLocation.y) && (e.LocationMap.get("l"+(x+1)+y).checkedCount < MAX_CHECKED)) {
                Log.i(TAG, "Can move x+1");
                possibleDirections.add(e.LocationMap.get("l"+(x+1)+y));
            } else if(canMove(currentLocation.x-1, currentLocation.y) && (e.LocationMap.get("l"+(x-1)+y).checkedCount < MAX_CHECKED)){
                Log.i(TAG, "Can move x-1");
                possibleDirections.add(e.LocationMap.get("l"+(x-1)+y));
            } else if(canMove(currentLocation.x, currentLocation.y+1) && (e.LocationMap.get("l"+x+(y+1)).checkedCount < MAX_CHECKED)) {
                Log.i(TAG, "Can move y+1");
                possibleDirections.add(e.LocationMap.get("l"+x+(y+1)));
            } else if(canMove(currentLocation.x, currentLocation.y-1) && (e.LocationMap.get("l"+x+(y-1)).checkedCount < MAX_CHECKED)) {
                Log.i(TAG, "Can move y-1");
                possibleDirections.add(e.LocationMap.get("l"+x+(y-1)));
            }
            //choose random direction:
            if(possibleDirections.size() > 0) {
                Random random = new Random();
                moveToLocation(possibleDirections.get(random.nextInt(possibleDirections.size())));
                Log.i(TAG, "RANDom movement: "+currentLocation.x+currentLocation.y);
            }
        }
        //we solved the maze
        return true;
    }
}