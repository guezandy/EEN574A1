package com.een574;


import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Robot {
    public String TAG = Robot.class.getSimpleName();

    public final int NORTH = 0;
    public final int EAST = 1;
    public final int SOUTH = 2;
    public final int WEST = 3;
    public final int THIS_LOCATION = 5;

    public final int NO_OBSTACLE = 0;
    public final int OBSTACLE = 1;
    public final int BOUNDARY = 2;

    //0->8 depending on location
    public int currentLocation = 0;

    //0 = north  1 = east 2 = south 3 = west
    public int directionFacing = 0;

    //this is a variable that will hold the possible directions the robot can move when hit an obstacle
    //possibleDir[n] = 0 if no obstacle
    //possibleDir[n] = 1 if obstacle
    //possibleDir[n] = 2 if hit boundary
    //updated in the getPossibleDirections() method
    public int[] possibleDir = {NO_OBSTACLE, NO_OBSTACLE, NO_OBSTACLE, NO_OBSTACLE};

    //AUtomated robot checking each square:
    //list to verify that the robots checked all available positions
    public int[] checkedLocations = {0,0,0,0,0,0,0,0,0};

    public Robot() {
        //empty initializer is required
    }

    Environment e;
    public Robot(Environment en) {
        this.e = en;
    }

    //finds first open (no obstacle) position to start robot
    public Boolean start(Environment e) {
        //if obstacle in zero
        if(!e.getObstacles().get(0)) {
            currentLocation = 0;
            directionFacing = 0;
            return true;
        } else if(!e.getObstacles().get(1)) {
            currentLocation = 1;
            directionFacing = 0;
            return true;
        } else if(!e.getObstacles().get(2)) {
            currentLocation = 2;
            directionFacing = 0;
            return true;
        } else if(!e.getObstacles().get(3)) {
            currentLocation = 3;
            directionFacing = 0;
            return true;
        } else if(!e.getObstacles().get(4)) {
            currentLocation = 4;
            directionFacing = 0;
            return true;
        } else if(!e.getObstacles().get(5)) {
            currentLocation = 5;
            directionFacing = 0;
            return true;
        } else if(!e.getObstacles().get(6)) {
            currentLocation = 6;
            directionFacing = 0;
            return true;
        } else if(!e.getObstacles().get(7)) {
            currentLocation = 7;
            directionFacing = 0;
            return true;
        } else if(!e.getObstacles().get(8)) {
            currentLocation = 8;
            directionFacing = 0;
            return true;
        }
        return false;
    }

    /**
     * 3x3
     * Trying to make a somewhat intelligent robot predefining the possible movements
     */
    public Boolean moveForward() {
        switch (currentLocation) {
            case 0: //Location(0,0)
                if (directionFacing == NORTH) {
                    currentLocation = 1;
                    return true;
                } else if (directionFacing == EAST) {
                    currentLocation = 5;
                    return true;
                } else if (directionFacing == SOUTH) {
                    return false;
                } else {
                    //west
                    return false;
                }
            case 1: //Location (0,1)
                if (directionFacing == NORTH) {
                    currentLocation = 2;
                    return true;
                } else if (directionFacing == EAST) {
                    currentLocation = 4;
                    return true;
                } else if (directionFacing == SOUTH) {
                    currentLocation = 0;
                    return true;
                } else {
                    //west
                    return false;
                }
            case 2: //Location (0,2)
                if (directionFacing == NORTH) {
                    return false;
                } else if (directionFacing == EAST) {
                    currentLocation = 3;
                    return true;
                } else if (directionFacing == SOUTH) {
                    currentLocation = 1;
                    return true;
                } else {
                    //west
                    return false;
                }
            case 3: //Location (1,2)
                if (directionFacing == NORTH) {
                    return false;
                } else if (directionFacing == EAST) {
                    currentLocation = 8;
                    return true;
                } else if (directionFacing == SOUTH) {
                    currentLocation = 4;
                    return true;
                } else {
                    //west
                    currentLocation = 2;
                    return true;
                }
            case 4: //Location (1,1)
                if (directionFacing == NORTH) {
                    currentLocation = 3;
                    return true;
                } else if (directionFacing == EAST) {
                    currentLocation = 7;
                    return true;
                } else if (directionFacing == SOUTH) {
                    currentLocation = 5;
                    return true;
                } else {
                    //west
                    currentLocation = 1;
                    return true;
                }
            case 5:
                if (directionFacing == NORTH) {
                    currentLocation = 4;
                    return true;
                } else if (directionFacing == EAST) {
                    currentLocation = 6;
                    return true;
                } else if (directionFacing == SOUTH) {
                    return false;
                } else {
                    //west
                    currentLocation = 0;
                    return true;
                }
            case 6:
                if (directionFacing == NORTH) {
                    currentLocation = 7;
                    return true;
                } else if (directionFacing == EAST) {
                    return false;
                } else if (directionFacing == SOUTH) {
                    return false;
                } else {
                    //west
                    currentLocation = 5;
                    return true;
                }
            case 7:
                if (directionFacing == NORTH) {
                    currentLocation = 8;
                    return true;
                } else if (directionFacing == EAST) {
                    return false;
                } else if (directionFacing == SOUTH) {
                    currentLocation = 6;
                    return true;
                } else {
                    //west
                    currentLocation = 4;
                    return true;
                }
            case 8:
                if (directionFacing == NORTH) {
                    return false;
                } else if (directionFacing == EAST) {
                    return false;
                } else if (directionFacing == SOUTH) {
                    currentLocation = 7;
                    return true;
                } else {
                    //west
                    currentLocation = 3;
                    return true;
                }
        }
        return false;
    }

    //0 = north  1 = east 2 = south 3 = west
    public Boolean turn(int dir) {
        if (dir == 0 || dir == 1 || dir == 2 || dir == 3) {
            directionFacing = dir;
            return true;
        }
        return false;
    }

    //checking for dirt involves checking the environment. This is defined here by interclass interactions
    public Boolean checkForDirt(Environment e) {
        //true if theres dirt, false if no dirt
        return (e.getThreebythree().get(currentLocation));
    }

    public void clean(Environment e) {
        //make the current location clean;
        e.getThreebythree().set(currentLocation, false);
    }

    //returns true if there is an obstacle, false if no obstacle
    public Boolean senseObstacles(Environment e, int checkThisDirection) {
        switch (currentLocation) {
            case 0: //Location(0,0)
                if(directionFacing == THIS_LOCATION) {
                    Log.i(TAG, "sense obstacle is returning a value" + e.getObstacles().get(currentLocation));
                    return e.getObstacles().get(currentLocation);
                } else if(checkThisDirection == NORTH) {
                    return e.getObstacles().get(1);
                } else if(checkThisDirection == EAST) {
                    return e.getObstacles().get(5);
                }
            case 1: //Location (0,1)
                if(directionFacing == THIS_LOCATION) {
                    return e.getObstacles().get(currentLocation);
                } else if(checkThisDirection == NORTH) {
                    return e.getObstacles().get(2);
                } else if(checkThisDirection == EAST) {
                    return e.getObstacles().get(4);
                } else if(checkThisDirection == SOUTH) {
                    return e.getObstacles().get(0);
                }
            case 2: //Location (0,2)
                if(directionFacing == THIS_LOCATION) {
                    return e.getObstacles().get(currentLocation);
                } else if(checkThisDirection == EAST) {
                    return e.getObstacles().get(3);
                } else if(checkThisDirection == SOUTH) {
                    return e.getObstacles().get(1);
                }
            case 3: //Location (1,2)
                if(directionFacing == THIS_LOCATION) {
                    return e.getObstacles().get(currentLocation);
                } else if(checkThisDirection == EAST) {
                    return e.getObstacles().get(8);
                } else if(checkThisDirection == SOUTH) {
                    return e.getObstacles().get(4);
                } else if(checkThisDirection == WEST) {
                    return e.getObstacles().get(2);
                }
            case 4: //Location (1,1)
                if(directionFacing == THIS_LOCATION) {
                    return e.getObstacles().get(currentLocation);
                } else if(checkThisDirection == NORTH) {
                    return e.getObstacles().get(3);
                } else if(checkThisDirection == EAST) {
                    return e.getObstacles().get(7);
                } else if(checkThisDirection == SOUTH) {
                    return e.getObstacles().get(5);
                } else if(checkThisDirection == WEST) {
                    return e.getObstacles().get(1);
                }
            case 5:
                if(directionFacing == THIS_LOCATION) {
                    return e.getObstacles().get(currentLocation);
                } else if(checkThisDirection == NORTH) {
                    return e.getObstacles().get(4);
                } else if(checkThisDirection == EAST) {
                    return e.getObstacles().get(6);
                } else if(checkThisDirection == WEST) {
                    return e.getObstacles().get(0);
                }
            case 6:
                if(directionFacing == THIS_LOCATION) {
                    return e.getObstacles().get(currentLocation);
                } else if(checkThisDirection == NORTH) {
                    return e.getObstacles().get(7);
                } else if(checkThisDirection == WEST) {
                    return e.getObstacles().get(5);
                }
            case 7:
                if(directionFacing == THIS_LOCATION) {
                    return e.getObstacles().get(currentLocation);
                } else if(checkThisDirection == NORTH) {
                    return e.getObstacles().get(8);
                } else if(checkThisDirection == SOUTH) {
                    return e.getObstacles().get(6);
                } else if(checkThisDirection == WEST) {
                    return e.getObstacles().get(4);
                }
            case 8:
                if(directionFacing == THIS_LOCATION) {
                    return e.getObstacles().get(currentLocation);
                } else if(checkThisDirection == SOUTH) {
                    return e.getObstacles().get(7);
                } else if(checkThisDirection == WEST) {
                    return e.getObstacles().get(3);
                }

        }
        return false;
    }

    //possibleDir[n] = 0 if no obstacle
    //possibleDir[n] = 1 if obstacle
    //possibleDir[n] = 2 if hit boundary
    //updated in the getPossibleDirection
    public int[] getPossibleDirections(Environment e) {
        switch(currentLocation) {
            //if we're at location 0 lets see if we can go left or north
            case 0:
                if(senseObstacles(e, NORTH)) {
                    //theres an obstacle can't go north
                    possibleDir[NORTH] = OBSTACLE;
                    checkedLocations[currentLocation]++;
                } else {
                    //can go north
                    possibleDir[NORTH] = NO_OBSTACLE;
                }
                if(senseObstacles(e, EAST)) {
                    possibleDir[EAST] = OBSTACLE;
                    checkedLocations[currentLocation]++;
                } else {
                    possibleDir[EAST] = NO_OBSTACLE;
                }
                possibleDir[SOUTH] = BOUNDARY;
                possibleDir[WEST] = BOUNDARY;
                break;
            case 1:
                if(senseObstacles(e, NORTH)) {
                    possibleDir[NORTH] = OBSTACLE;
                    checkedLocations[currentLocation]++;
                } else {
                    possibleDir[NORTH] = NO_OBSTACLE;
                }
                if(senseObstacles(e, EAST)) {
                    checkedLocations[currentLocation]++;
                    possibleDir[EAST] = OBSTACLE;
                } else {
                    possibleDir[EAST] = NO_OBSTACLE;
                }
                if(senseObstacles(e, SOUTH)) {
                    checkedLocations[currentLocation]++;
                    possibleDir[SOUTH] = OBSTACLE;
                } else {
                    possibleDir[SOUTH] = NO_OBSTACLE;
                }
                possibleDir[WEST] = BOUNDARY;
                break;
            case 2:
                possibleDir[NORTH] = BOUNDARY;
                if(senseObstacles(e, EAST)) {
                    checkedLocations[currentLocation]++;
                    possibleDir[EAST] = OBSTACLE;
                } else {
                    possibleDir[EAST] = NO_OBSTACLE;
                }
                if(senseObstacles(e, SOUTH)) {
                    checkedLocations[currentLocation]++;
                    possibleDir[SOUTH] = OBSTACLE;
                } else {
                    possibleDir[SOUTH] = NO_OBSTACLE;
                }
                possibleDir[WEST] = BOUNDARY;
                break;
            case 3:
                possibleDir[NORTH] = BOUNDARY;
                if(senseObstacles(e, EAST)) {
                    checkedLocations[currentLocation]++;
                    possibleDir[EAST] = OBSTACLE;
                } else {
                    possibleDir[EAST] = NO_OBSTACLE;
                }
                if(senseObstacles(e, SOUTH)) {
                    possibleDir[SOUTH] = OBSTACLE;
                    checkedLocations[currentLocation]++;
                } else {
                    possibleDir[SOUTH] = NO_OBSTACLE;
                }
                if(senseObstacles(e, WEST)) {
                    possibleDir[WEST] = OBSTACLE;
                    checkedLocations[currentLocation]++;
                } else {
                    possibleDir[WEST] = NO_OBSTACLE;
                }
                break;
            case 4:
                if(senseObstacles(e, NORTH)) {
                    possibleDir[NORTH] = OBSTACLE;
                    checkedLocations[currentLocation]++;
                } else {
                    possibleDir[NORTH] = NO_OBSTACLE;
                }
                if(senseObstacles(e, EAST)) {
                    possibleDir[EAST] = OBSTACLE;
                    checkedLocations[currentLocation]++;
                } else {
                    possibleDir[EAST] = NO_OBSTACLE;
                }
                if(senseObstacles(e, SOUTH)) {
                    possibleDir[SOUTH] = OBSTACLE;
                    checkedLocations[currentLocation]++;
                } else {
                    possibleDir[SOUTH] = NO_OBSTACLE;
                }
                if(senseObstacles(e, WEST)) {
                    possibleDir[WEST] = OBSTACLE;
                    checkedLocations[currentLocation]++;
                } else {
                    possibleDir[WEST] = NO_OBSTACLE;
                }
                break;
            case 5:
                if(senseObstacles(e, NORTH)) {
                    possibleDir[NORTH] = OBSTACLE;
                    checkedLocations[currentLocation]++;
                } else {
                    possibleDir[NORTH] = NO_OBSTACLE;
                }
                if(senseObstacles(e, EAST)) {
                    possibleDir[EAST] = OBSTACLE;
                    checkedLocations[currentLocation]++;
                } else {
                    possibleDir[EAST] = NO_OBSTACLE;
                }
                possibleDir[SOUTH] = BOUNDARY;
                if(senseObstacles(e, WEST)) {
                    possibleDir[WEST] = OBSTACLE;
                    checkedLocations[currentLocation]++;
                } else {
                    possibleDir[WEST] = NO_OBSTACLE;
                }
                break;
            case 6:
                if(senseObstacles(e, NORTH)) {
                    possibleDir[NORTH] = OBSTACLE;
                    checkedLocations[currentLocation]++;
                } else {
                    possibleDir[NORTH] = NO_OBSTACLE;
                }
                possibleDir[EAST] = BOUNDARY;
                possibleDir[SOUTH] = BOUNDARY;
                if(senseObstacles(e, WEST)) {
                    possibleDir[WEST] = OBSTACLE;
                    checkedLocations[currentLocation]++;
                } else {
                    possibleDir[WEST] = NO_OBSTACLE;
                }
                break;
            case 7:
                if(senseObstacles(e, NORTH)) {
                    possibleDir[NORTH] = OBSTACLE;
                    checkedLocations[currentLocation]++;
                } else {
                    possibleDir[NORTH] = NO_OBSTACLE;
                }
                possibleDir[EAST] = BOUNDARY;
                if(senseObstacles(e, SOUTH)) {
                    possibleDir[SOUTH] = OBSTACLE;
                    checkedLocations[currentLocation]++;
                } else {
                    possibleDir[SOUTH] = NO_OBSTACLE;
                }
                if(senseObstacles(e, WEST)) {
                    possibleDir[WEST] = OBSTACLE;
                    checkedLocations[currentLocation]++;
                } else {
                    possibleDir[WEST] = NO_OBSTACLE;
                }
                break;
            case 8:
                possibleDir[NORTH] = BOUNDARY;
                possibleDir[EAST] = BOUNDARY;
                if(senseObstacles(e, SOUTH)) {
                    possibleDir[SOUTH] = OBSTACLE;
                    checkedLocations[currentLocation]++;
                } else {
                    possibleDir[SOUTH] = NO_OBSTACLE;
                }
                if(senseObstacles(e, WEST)) {
                    possibleDir[WEST] = OBSTACLE;
                    checkedLocations[currentLocation]++;
                } else {
                    possibleDir[WEST] = NO_OBSTACLE;
                }
                break;
        }
        return possibleDir;
    }

    //possibleDir[n] = 0 if no obstacle
    //possibleDir[n] = 1 if obstacle
    //possibleDir[n] = 2 if hit boundary
    //possibleDir[n] = 3 we hit the same spot too much prob a infinite loop
    public int INFINITE = 3;
    //updated in the getPossibleDirection
    public int MAX_TRIES_AT_LOCATION = 50;
    public int[] getPossibleAutoDirections(Environment e) {
        switch(currentLocation) {
            //if we're at location 0 lets see if we can go left or north
            case 0:
                if(senseObstacles(e, NORTH)) {
                    //theres an obstacle can't go north
                    possibleDir[NORTH] = OBSTACLE;
                    checkedLocations[currentLocation]++;
                } else {
                    //can go north
                    if(checkedLocations[1] > MAX_TRIES_AT_LOCATION) {
                        possibleDir[NORTH] = INFINITE;
                    } else {
                        possibleDir[NORTH] = NO_OBSTACLE;
                        checkedLocations[currentLocation]++;
                    }
                }
                if(senseObstacles(e, EAST)) {
                    possibleDir[EAST] = OBSTACLE;
                    checkedLocations[currentLocation]++;
                } else {
                    if(checkedLocations[5] > MAX_TRIES_AT_LOCATION) {
                        possibleDir[EAST] = INFINITE;
                    } else {
                        possibleDir[EAST] = NO_OBSTACLE;
                        checkedLocations[currentLocation]++;
                    }
                }
                possibleDir[SOUTH] = BOUNDARY;
                possibleDir[WEST] = BOUNDARY;
                break;
            case 1:
                if(senseObstacles(e, NORTH)) {
                    possibleDir[NORTH] = OBSTACLE;
                    checkedLocations[currentLocation]++;
                } else {
                    if(checkedLocations[2] > MAX_TRIES_AT_LOCATION) {
                        possibleDir[NORTH] = INFINITE;
                    } else {
                        possibleDir[NORTH] = NO_OBSTACLE;
                        checkedLocations[currentLocation]++;
                    }
                }
                if(senseObstacles(e, EAST)) {
                    checkedLocations[currentLocation]++;
                    possibleDir[EAST] = OBSTACLE;
                } else {
                    if(checkedLocations[4] > MAX_TRIES_AT_LOCATION) {
                        possibleDir[EAST] = INFINITE;
                    } else {
                        possibleDir[EAST] = NO_OBSTACLE;
                        checkedLocations[currentLocation]++;
                    }
                }
                if(senseObstacles(e, SOUTH)) {
                    checkedLocations[currentLocation]++;
                    possibleDir[SOUTH] = OBSTACLE;
                } else {
                    if(checkedLocations[0] > MAX_TRIES_AT_LOCATION) {
                        possibleDir[SOUTH] = INFINITE;
                    } else {
                        possibleDir[SOUTH] = NO_OBSTACLE;
                        checkedLocations[currentLocation]++;
                    }
                }
                possibleDir[WEST] = BOUNDARY;
                break;
            case 2:
                possibleDir[NORTH] = BOUNDARY;
                if(senseObstacles(e, EAST)) {
                    checkedLocations[currentLocation]++;
                    possibleDir[EAST] = OBSTACLE;
                } else {
                    if(checkedLocations[3] > MAX_TRIES_AT_LOCATION) {
                        possibleDir[EAST] = INFINITE;
                    } else {
                        possibleDir[EAST] = NO_OBSTACLE;
                        checkedLocations[currentLocation]++;
                    }
                }
                if(senseObstacles(e, SOUTH)) {
                    checkedLocations[currentLocation]++;
                    possibleDir[SOUTH] = OBSTACLE;
                } else {
                    if(checkedLocations[1] > MAX_TRIES_AT_LOCATION) {
                        possibleDir[SOUTH] = INFINITE;
                    } else {
                        possibleDir[SOUTH] = NO_OBSTACLE;
                        checkedLocations[currentLocation]++;
                    }
                }
                possibleDir[WEST] = BOUNDARY;
                break;
            case 3:
                possibleDir[NORTH] = BOUNDARY;
                if(senseObstacles(e, EAST)) {
                    checkedLocations[currentLocation]++;
                    possibleDir[EAST] = OBSTACLE;
                } else {
                    if(checkedLocations[8] > MAX_TRIES_AT_LOCATION) {
                        possibleDir[EAST] = INFINITE;
                    } else {
                        possibleDir[EAST] = NO_OBSTACLE;
                        checkedLocations[currentLocation]++;
                    }
                }
                if(senseObstacles(e, SOUTH)) {
                    possibleDir[SOUTH] = OBSTACLE;
                    checkedLocations[currentLocation]++;
                } else {
                    if(checkedLocations[4] > MAX_TRIES_AT_LOCATION) {
                        possibleDir[SOUTH] = INFINITE;
                    } else {
                        possibleDir[SOUTH] = NO_OBSTACLE;
                        checkedLocations[currentLocation]++;
                    }
                }
                if(senseObstacles(e, WEST)) {
                    possibleDir[WEST] = OBSTACLE;
                    checkedLocations[currentLocation]++;
                } else {
                    if(checkedLocations[2] > MAX_TRIES_AT_LOCATION) {
                        possibleDir[WEST] = INFINITE;
                    } else {
                        possibleDir[WEST] = NO_OBSTACLE;
                        checkedLocations[currentLocation]++;
                    }
                }
                break;
            case 4:
                if(senseObstacles(e, NORTH)) {
                    possibleDir[NORTH] = OBSTACLE;
                    checkedLocations[currentLocation]++;
                } else {
                    if(checkedLocations[3] > MAX_TRIES_AT_LOCATION) {
                        possibleDir[NORTH] = INFINITE;
                    } else {
                        possibleDir[NORTH] = NO_OBSTACLE;
                        checkedLocations[currentLocation]++;
                    }
                }
                if(senseObstacles(e, EAST)) {
                    possibleDir[EAST] = OBSTACLE;
                    checkedLocations[currentLocation]++;
                } else {
                    if(checkedLocations[7] > MAX_TRIES_AT_LOCATION) {
                        possibleDir[EAST] = INFINITE;
                    } else {
                        possibleDir[EAST] = NO_OBSTACLE;
                        checkedLocations[currentLocation]++;
                    }
                }
                if(senseObstacles(e, SOUTH)) {
                    possibleDir[SOUTH] = OBSTACLE;
                    checkedLocations[currentLocation]++;
                } else {
                    if(checkedLocations[5] > MAX_TRIES_AT_LOCATION) {
                        possibleDir[SOUTH] = INFINITE;
                    } else {
                        possibleDir[SOUTH] = NO_OBSTACLE;
                        checkedLocations[currentLocation]++;
                    }
                }
                if(senseObstacles(e, WEST)) {
                    possibleDir[WEST] = OBSTACLE;
                    checkedLocations[currentLocation]++;
                } else {
                    if(checkedLocations[1] > MAX_TRIES_AT_LOCATION) {
                        possibleDir[WEST] = INFINITE;
                    } else {
                        possibleDir[WEST] = NO_OBSTACLE;
                        checkedLocations[currentLocation]++;
                    }
                }
                break;
            case 5:
                if(senseObstacles(e, NORTH)) {
                    possibleDir[NORTH] = OBSTACLE;
                    checkedLocations[currentLocation]++;
                } else {
                    if(checkedLocations[4] > MAX_TRIES_AT_LOCATION) {
                        possibleDir[NORTH] = INFINITE;
                    } else {
                        possibleDir[NORTH] = NO_OBSTACLE;
                        checkedLocations[currentLocation]++;
                    }
                }
                if(senseObstacles(e, EAST)) {
                    possibleDir[EAST] = OBSTACLE;
                    checkedLocations[currentLocation]++;
                } else {
                    if(checkedLocations[6] > MAX_TRIES_AT_LOCATION) {
                        possibleDir[EAST] = INFINITE;
                    } else {
                        possibleDir[EAST] = NO_OBSTACLE;
                        checkedLocations[currentLocation]++;
                    }
                }
                possibleDir[SOUTH] = BOUNDARY;
                if(senseObstacles(e, WEST)) {
                    possibleDir[WEST] = OBSTACLE;
                    checkedLocations[currentLocation]++;
                } else {
                    if(checkedLocations[0] > MAX_TRIES_AT_LOCATION) {
                        possibleDir[WEST] = INFINITE;
                    } else {
                        possibleDir[WEST] = NO_OBSTACLE;
                        checkedLocations[currentLocation]++;
                    }
                }
                break;
            case 6:
                if(senseObstacles(e, NORTH)) {
                    possibleDir[NORTH] = OBSTACLE;
                    checkedLocations[currentLocation]++;
                } else {
                    if(checkedLocations[7] > MAX_TRIES_AT_LOCATION) {
                        possibleDir[NORTH] = INFINITE;
                    } else {
                        possibleDir[NORTH] = NO_OBSTACLE;
                        checkedLocations[currentLocation]++;
                    }
                }
                possibleDir[EAST] = BOUNDARY;
                possibleDir[SOUTH] = BOUNDARY;
                if(senseObstacles(e, WEST)) {
                    possibleDir[WEST] = OBSTACLE;
                    checkedLocations[currentLocation]++;
                } else {
                    if(checkedLocations[5] > MAX_TRIES_AT_LOCATION) {
                        possibleDir[WEST] = INFINITE;
                    } else {
                        possibleDir[WEST] = NO_OBSTACLE;
                        checkedLocations[currentLocation]++;
                    }
                }
                break;
            case 7:
                if(senseObstacles(e, NORTH)) {
                    possibleDir[NORTH] = OBSTACLE;
                    checkedLocations[currentLocation]++;
                } else {
                    if(checkedLocations[8] > MAX_TRIES_AT_LOCATION) {
                        possibleDir[NORTH] = INFINITE;
                    } else {
                        possibleDir[NORTH] = NO_OBSTACLE;
                        checkedLocations[currentLocation]++;
                    }
                }
                possibleDir[EAST] = BOUNDARY;
                if(senseObstacles(e, SOUTH)) {
                    possibleDir[SOUTH] = OBSTACLE;
                    checkedLocations[currentLocation]++;
                } else {
                    if(checkedLocations[6] > MAX_TRIES_AT_LOCATION) {
                        possibleDir[SOUTH] = INFINITE;
                    } else {
                        possibleDir[SOUTH] = NO_OBSTACLE;
                        checkedLocations[currentLocation]++;
                    }
                }
                if(senseObstacles(e, WEST)) {
                    possibleDir[WEST] = OBSTACLE;
                    checkedLocations[currentLocation]++;
                } else {
                    if(checkedLocations[4] > MAX_TRIES_AT_LOCATION) {
                        possibleDir[WEST] = INFINITE;
                    } else {
                        possibleDir[WEST] = NO_OBSTACLE;
                        checkedLocations[currentLocation]++;
                    }
                }
                break;
            case 8:
                possibleDir[NORTH] = BOUNDARY;
                possibleDir[EAST] = BOUNDARY;
                if(senseObstacles(e, SOUTH)) {
                    possibleDir[SOUTH] = OBSTACLE;
                    checkedLocations[currentLocation]++;
                } else {
                    if(checkedLocations[7] > MAX_TRIES_AT_LOCATION) {
                        possibleDir[SOUTH] = INFINITE;
                    } else {
                        possibleDir[SOUTH] = NO_OBSTACLE;
                        checkedLocations[currentLocation]++;
                    }
                }
                if(senseObstacles(e, WEST)) {
                    possibleDir[WEST] = OBSTACLE;
                    checkedLocations[currentLocation]++;
                } else {
                    if(checkedLocations[3] > MAX_TRIES_AT_LOCATION) {
                        possibleDir[WEST] = INFINITE;
                    } else {
                        possibleDir[WEST] = NO_OBSTACLE;
                        checkedLocations[currentLocation]++;
                    }
                }
                break;
        }
        return possibleDir;
    }
}