package com.een574;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.een574.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class EnvironmentController extends Fragment {
    public String TAG = EnvironmentController.class.getSimpleName();
    public final int NORTH = 0;
    public final int EAST = 1;
    public final int SOUTH = 2;
    public final int WEST = 3;

    private View mView;
    private Button but00;
    private Button but01;
    private Button but02;
    private Button but10;
    private Button but11;
    private Button but12;
    private Button but20;
    private Button but21;
    private Button but22;
    private Button automate;
    private Button start;
    private Button forward;
    private Button turn;
    private Button clean;
    private Button obstacles;
    private Button reset;

    public Environment environment;
    public Robot robot;
    public TextView steps;

    //are we currently allowing the user to set obstacles
    public Boolean setObstacles = false;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //android inflate layout
        mView = inflater.inflate(R.layout.fragment_main, container, false);

        //initialize environment
        environment = new Environment();

        //initialize robot
        robot = new Robot(environment);

        //get everything started
        initializeEnvironment(mView, environment, robot);

        return mView;
    }

    public void initializeEnvironment(View mView, final Environment environment, final Robot robot) {
        //button to automate the robots path
        automate = (Button) mView.findViewById(R.id.auto);
        //button to start manually controlling robots path
        start = (Button) mView.findViewById(R.id.start);
        //button to move robot forward
        forward = (Button) mView.findViewById(R.id.forward);
        forward.setClickable(false);
        //button to turn robot
        turn = (Button) mView.findViewById(R.id.turn);
        turn.setClickable(false);
        //button to check and clean
        clean = (Button) mView.findViewById(R.id.clean);
        clean.setClickable(false);
        //text view to show user what actions they've taken
        steps = (TextView) mView.findViewById(R.id.action);
        //obstacles to be added to the field
        obstacles = (Button) mView.findViewById(R.id.obstacle);
        //reset the environment
        reset = (Button) mView.findViewById(R.id.reset);
        //each square in the 3x3 is it's own button
        but00 = (Button) mView.findViewById(R.id.but0_0);
        but01 = (Button) mView.findViewById(R.id.but0_1);
        but02 = (Button) mView.findViewById(R.id.but0_2);
        but10 = (Button) mView.findViewById(R.id.but1_0);
        but11 = (Button) mView.findViewById(R.id.but1_1);
        but12 = (Button) mView.findViewById(R.id.but1_2);
        but20 = (Button) mView.findViewById(R.id.but2_0);
        but21 = (Button) mView.findViewById(R.id.but2_1);
        but22 = (Button) mView.findViewById(R.id.but2_2);

        //OnClick listeners in case user wants to add dirt to any location
        but00.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!setObstacles) {
                    Toast.makeText(getActivity().getBaseContext(), "Adding dirt to (0,0)", Toast.LENGTH_SHORT).show();
                    but00.setBackgroundResource(R.drawable.ic_launcher);
                    environment.getThreebythree().set(0, true);
                    steps.setText(steps.getText().toString() + "\nAdding dirt to (0,0)");
                } else {
                    Toast.makeText(getActivity().getBaseContext(), "Adding obstacle to (0,0)", Toast.LENGTH_SHORT).show();
                    but00.setBackgroundResource(R.drawable.obstacle);
                    environment.getObstacles().set(0, true);
                    steps.setText(steps.getText().toString() + "\nAdding obstacle to (0,0)");
                }
            }
        });
        but01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!setObstacles) {
                    Toast.makeText(getActivity().getBaseContext(), "Adding dirt to (0,1)", Toast.LENGTH_SHORT).show();
                    but01.setBackgroundResource(R.drawable.ic_launcher);
                    environment.getThreebythree().set(1, true);
                    steps.setText(steps.getText().toString() + "\nAdding dirt to (0,1)");
                } else {
                    Toast.makeText(getActivity().getBaseContext(), "Adding obstacle to (0,1)", Toast.LENGTH_SHORT).show();
                    but01.setBackgroundResource(R.drawable.obstacle);
                    environment.getObstacles().set(1, true);
                    steps.setText(steps.getText().toString() + "\nAdding obstacle to (0,1)");
                }
            }
        });
        but02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!setObstacles) {
                    Toast.makeText(getActivity().getBaseContext(), "Adding dirt to (0,2)", Toast.LENGTH_SHORT).show();
                    but02.setBackgroundResource(R.drawable.ic_launcher);
                    environment.getThreebythree().set(2, true);
                    steps.setText(steps.getText().toString() + "\nAdding dirt to (0,2)");
                } else {
                    Toast.makeText(getActivity().getBaseContext(), "Adding obstacle to (0,2)", Toast.LENGTH_SHORT).show();
                    but02.setBackgroundResource(R.drawable.obstacle);
                    environment.getObstacles().set(2, true);
                    steps.setText(steps.getText().toString() + "\nAdding obstacle to (0,2)");
                }
            }
        });
        but10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!setObstacles) {
                    Toast.makeText(getActivity().getBaseContext(), "Adding dirt to (1,0)", Toast.LENGTH_SHORT).show();
                    but10.setBackgroundResource(R.drawable.ic_launcher);
                    environment.getThreebythree().set(5, true);
                    steps.setText(steps.getText().toString() + "\nAdding dirt to (1,0)");
                } else {
                    Toast.makeText(getActivity().getBaseContext(), "Adding obstacle to (1,0)", Toast.LENGTH_SHORT).show();
                    but10.setBackgroundResource(R.drawable.obstacle);
                    environment.getObstacles().set(5, true);
                    steps.setText(steps.getText().toString() + "\nAdding obstacle to (1,0)");
                }
            }
        });
        but11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!setObstacles) {
                    Toast.makeText(getActivity().getBaseContext(), "Adding dirt to (1,1)", Toast.LENGTH_SHORT).show();
                    but11.setBackgroundResource(R.drawable.ic_launcher);
                    environment.getThreebythree().set(4, true);
                    steps.setText(steps.getText().toString() + "\nAdding dirt to (1,1)");
                } else {
                    Toast.makeText(getActivity().getBaseContext(), "Adding obstacle to (1,1)", Toast.LENGTH_SHORT).show();
                    but11.setBackgroundResource(R.drawable.obstacle);
                    environment.getObstacles().set(4, true);
                    steps.setText(steps.getText().toString() + "\nAdding obstacle to (1,1)");
                }
            }
        });
        but12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!setObstacles) {
                    Toast.makeText(getActivity().getBaseContext(), "Adding dirt to (1,2)", Toast.LENGTH_SHORT).show();
                    but12.setBackgroundResource(R.drawable.ic_launcher);
                    environment.getThreebythree().set(3, true);
                    steps.setText(steps.getText().toString() + "\nAdding dirt to (1,2)");
                } else {
                    Toast.makeText(getActivity().getBaseContext(), "Adding obstacle to (1,2)", Toast.LENGTH_SHORT).show();
                    but12.setBackgroundResource(R.drawable.obstacle);
                    environment.getObstacles().set(3, true);
                    steps.setText(steps.getText().toString() + "\nAdding obstacle to (1,2)");
                }
            }
        });
        but20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!setObstacles) {
                    Toast.makeText(getActivity().getBaseContext(), "Adding dirt to (2,0)", Toast.LENGTH_SHORT).show();
                    but20.setBackgroundResource(R.drawable.ic_launcher);
                    environment.getThreebythree().set(6, true);
                    steps.setText(steps.getText().toString() + "\nAdding dirt to (2,0)");
                } else {
                    Toast.makeText(getActivity().getBaseContext(), "Adding obstacle to (2,0)", Toast.LENGTH_SHORT).show();
                    but20.setBackgroundResource(R.drawable.obstacle);
                    environment.getObstacles().set(6, true);
                    steps.setText(steps.getText().toString() + "\nAdding obstacle to (2,0)");
                }
            }
        });
        but21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!setObstacles) {
                    Toast.makeText(getActivity().getBaseContext(), "Adding dirt to (2,1)", Toast.LENGTH_SHORT).show();
                    but21.setBackgroundResource(R.drawable.ic_launcher);
                    environment.getThreebythree().set(7, true);
                    steps.setText(steps.getText().toString() + "\nAdding dirt to (2,1)");
                } else {
                    Toast.makeText(getActivity().getBaseContext(), "Adding obstacle to (2,1)", Toast.LENGTH_SHORT).show();
                    but21.setBackgroundResource(R.drawable.obstacle);
                    environment.getObstacles().set(7, true);
                    steps.setText(steps.getText().toString() + "\nAdding obstacle to (2,1)");
                }
            }
        });
        but22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!setObstacles) {
                    Toast.makeText(getActivity().getBaseContext(), "Adding dirt to (2,2)", Toast.LENGTH_SHORT).show();
                    but22.setBackgroundResource(R.drawable.ic_launcher);
                    environment.getThreebythree().set(8, true);
                    steps.setText(steps.getText().toString() + "\nAdding dirt to (2,2)");
                } else {
                    Toast.makeText(getActivity().getBaseContext(), "Adding obstacle to (2,2)", Toast.LENGTH_SHORT).show();
                    but22.setBackgroundResource(R.drawable.obstacle);
                    environment.getObstacles().set(8, true);
                    steps.setText(steps.getText().toString() + "\nAdding obstacle to (2,2)");
                }
            }
        });

        //set the automate cleaning button
        automate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //call automate robot method with no obstacles
                if(environment.getObstacles().contains(true)) {
                    automaticRobotWithObstacles(v, environment, robot);
                } else {
                    automaticRobotNoObstacles(v, environment, robot);
                }
            }
        });

        //start button
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //when start is clicked disable automate button
                automate.setClickable(false);

                //enable these buttons
                clean.setClickable(true);
                forward.setClickable(true);
                turn.setClickable(true);

                //set the robot to first available location
                if(robot.start(environment)) {
                    updateView(v, environment, robot);
                } else {
                    steps.setText(steps.getText() + "ALL Locations have obstacles");
                }

            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //restart the fragment
                ((MainActivity) getActivity()).replaceFragment(new EnvironmentController(), true, FragmentTransaction.TRANSIT_FRAGMENT_FADE, getString(R.string.title_section1));

            }
        });

        //move forward button
        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //return true if theres an obstacle
                if(!robot.senseObstacles(environment,robot.directionFacing)) {
                    //there is no obstacle ahead of us
                    if(robot.moveForward()) {
                        steps.setText(steps.getText() + "\nMoving Forward (No obstacle)");
                        updateView(v, environment, robot);
                    } else {
                        Toast.makeText(getActivity().getBaseContext(),"Cannot move forward, turn first", Toast.LENGTH_SHORT).show();
                        steps.setText(steps.getText() + "\nCannot move forward hit a boundary");
                    }
                } else {
                    Toast.makeText(getActivity().getBaseContext(), "Cannot move forward hit obstacle", Toast.LENGTH_SHORT).show();
                    steps.setText(steps.getText() + "\nCannot move forward hit an OBSTACLE");
                }
                int[] directions = robot.getPossibleDirections(environment);
                if(directions[0] == 0) {
                    steps.setText(steps.getText() + "\nCAN GO NORTH");
                }
                if(directions[1] == 0) {
                    steps.setText(steps.getText() + "\nCAN GO EAST");
                }
                if(directions[2] == 0) {
                    steps.setText(steps.getText() + "\nCAN GO SOUTH");
                }
                if(directions[3] == 0) {
                    steps.setText(steps.getText() + "\nCAN GO WEST");
                }
                if((directions[0] == 1 || directions[0]==2) && (directions[1] == 1 || directions[1]==2) && (directions[2] == 1 || directions[2]==2) && (directions[3] == 1 || directions[3]==2)) {
                    steps.setText(steps.getText() + "\nNo more possible movements obstacles all around");
                }
                updateView(v,environment,robot);
            }
        });

        //turn button
        turn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //map 0,1,2,3 to North, East, South, West respectiviely.
                int directionToTurn = 0;
                if(robot.directionFacing == 0) {
                    directionToTurn = 1;
                } else if(robot.directionFacing == 1) {
                    directionToTurn = 2;
                } else if(robot.directionFacing == 2) {
                    directionToTurn = 3;
                } else {
                    //west
                    directionToTurn = 0;
                }

                //set turn direction. Turning is not disabled if turning into a wall
                robot.turn(directionToTurn);

                //print log
                steps.setText(steps.getText() + "\nTurning robot");
                updateView(v, environment, robot);
            }
        });

        clean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check if theres dirt in the square
                if(robot.checkForDirt(environment)) {
                    //if there is dirt then clean it
                    steps.setText(steps.getText() + "\nCleaning Dirt");
                    robot.clean(environment);
                } else {
                    //else just print no dirt
                    steps.setText(steps.getText() + "\nCheck for dirt: NO DIRT!");
                }
            }
        });
        //button to allow the setting of obstacles
        obstacles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setObstacles = !setObstacles;
                if(setObstacles) {
                    steps.setText(steps.getText() + "\nUser can set obstacles");
                } else {
                    steps.setText(steps.getText() + "\nUser cannot set obstacles");
                }
            }
        });
    }

    /**
     * Automated the route that the robot takes to clean the dirt
     * @param v View
     * @param e Environment
     * @param r Robot
     * @return number of dirt cleaned
     */
    public int automaticRobotNoObstacles(View v, Environment e, Robot r) {
        steps.setText(steps.getText().toString() + "\nAUTOMATED ROBOT");
        int cleanedCounter = 0;
        //sets location to (0,0) and direction to north;
        r.start(environment);
        updateView(v,e,r);
        //check and clean first tile
        if(r.checkForDirt(e)) {
            cleanedCounter++;
            steps.setText(steps.getText().toString() + "\nCleaned Dirt!");
            r.clean(e);
            updateView(v,e,r);
        }
        //to(0,1)
        r.moveForward();
        updateView(v,e,r);
        //check if dirt
        if(r.checkForDirt(e)) {
            cleanedCounter++;
            steps.setText(steps.getText().toString() + "\nCleaned Dirt!");
            r.clean(e);
            updateView(v,e,r);
        }
        //to(0,2)
        r.moveForward();
        updateView(v,e,r);
        if(r.checkForDirt(e)) {
            cleanedCounter++;
            steps.setText(steps.getText().toString() + "\nCleaned Dirt!");
            r.clean(e);
            updateView(v,e,r);
        }
        //turn east towards (1,2)
        r.turn(EAST);
        updateView(v,e,r);
        //to (1,2)
        r.moveForward();
        updateView(v,e,r);
        if(r.checkForDirt(e)) {
            cleanedCounter++;
            steps.setText(steps.getText().toString() + "\nCleaned Dirt!");
            r.clean(e);
            updateView(v,e,r);
        }
        //turn towards (1,1)
        r.turn(SOUTH);
        updateView(v,e,r);
        //move to (1,1)
        r.moveForward();
        updateView(v,e,r);
        if(r.checkForDirt(e)) {
            cleanedCounter++;
            steps.setText(steps.getText().toString() + "\nCleaned Dirt!");
            r.clean(e);
            updateView(v,e,r);
        }
        //move to (1,0)
        r.moveForward();
        updateView(v,e,r);
        if(r.checkForDirt(e)) {
            cleanedCounter++;
            steps.setText(steps.getText().toString() + "\nCleaned Dirt!");
            r.clean(e);
            updateView(v,e,r);
        }

        //turn east
        r.turn(EAST);
        updateView(v,e,r);
        //move to (2,0)
        r.moveForward();
        updateView(v,e,r);
        if(r.checkForDirt(e)) {
            cleanedCounter++;
            steps.setText(steps.getText().toString() + "\nCleaned Dirt!");
            r.clean(e);
            updateView(v,e,r);
        }

        r.turn(NORTH);
        updateView(v,e,r);
        //move towards (2,1)
        r.moveForward();
        updateView(v,e,r);
        if(r.checkForDirt(e)) {
            cleanedCounter++;
            steps.setText(steps.getText().toString() + "\nCleaned Dirt!");
            r.clean(e);
            updateView(v,e,r);
        }

        //move towards (2,2);
        r.moveForward();
        updateView(v,e,r);
        if(r.checkForDirt(e)) {
            cleanedCounter++;
            steps.setText(steps.getText().toString() + "\nCleaned Dirt!");
            r.clean(e);
            updateView(v,e,r);
        }
        return cleanedCounter;
    }


    //handles all the updating of the view after each movement is made
    public void updateView(View v, Environment e, Robot r) {
        //if no dirt and not an obstacle
        if(!e.getThreebythree().get(0) && !e.getObstacles().get(0)) {
            //clear background
            but00.setBackgroundResource(0);
            but00.setBackgroundResource(R.color.grey_light);
            //if obstacle
        } else if(e.getObstacles().get(0)) {
            but00.setBackgroundResource(R.drawable.obstacle);
        } else {
            //if dirt
           but00.setBackgroundResource(R.drawable.ic_launcher);
        }
        if(!e.getThreebythree().get(1) && !e.getObstacles().get(1)) {
            but01.setBackgroundResource(0);
            but01.setBackgroundResource(R.color.grey_light);
        } else if(e.getObstacles().get(1)) {
            but01.setBackgroundResource(R.drawable.obstacle);
        } else {
            but01.setBackgroundResource(R.drawable.ic_launcher);
        }
        if(!e.getThreebythree().get(2) && !e.getObstacles().get(2)) {
            but02.setBackgroundResource(0);
            but02.setBackgroundResource(R.color.grey_light);
        } else if(e.getObstacles().get(2)) {
            but02.setBackgroundResource(R.drawable.obstacle);
        } else {
            but02.setBackgroundResource(R.drawable.ic_launcher);
        }
        if(!e.getThreebythree().get(3) && !e.getObstacles().get(3)) {
            but12.setBackgroundResource(0);
            but12.setBackgroundResource(R.color.grey_light);
        } else if(e.getObstacles().get(3)) {
            but12.setBackgroundResource(R.drawable.obstacle);
        } else {
            but12.setBackgroundResource(R.drawable.ic_launcher);
        }
        if(!e.getThreebythree().get(4) && !e.getObstacles().get(4)) {
            but11.setBackgroundResource(0);
            but11.setBackgroundResource(R.color.grey_light);
        } else if(e.getObstacles().get(4)) {
            but11.setBackgroundResource(R.drawable.obstacle);
        } else {
            but11.setBackgroundResource(R.drawable.ic_launcher);
        }
        if(!e.getThreebythree().get(5) && !e.getObstacles().get(5)) {
            but10.setBackgroundResource(0);
            but10.setBackgroundResource(R.color.grey_light);
        } else if(e.getObstacles().get(5)) {
            but10.setBackgroundResource(R.drawable.obstacle);
        } else {
            but10.setBackgroundResource(R.drawable.ic_launcher);
        }
        if(!e.getThreebythree().get(6) && !e.getObstacles().get(6)) {
            but20.setBackgroundResource(0);
            but20.setBackgroundResource(R.color.grey_light);
        } else if(e.getObstacles().get(6)) {
            but20.setBackgroundResource(R.drawable.obstacle);
        } else {
            but20.setBackgroundResource(R.drawable.ic_launcher);
        }
        if(!e.getThreebythree().get(7) && !e.getObstacles().get(7)) {
            but21.setBackgroundResource(0);
            but21.setBackgroundResource(R.color.grey_light);
        } else if(e.getObstacles().get(7)) {
            but21.setBackgroundResource(R.drawable.obstacle);
        } else {
            but21.setBackgroundResource(R.drawable.ic_launcher);
        }
        if(!e.getThreebythree().get(8) && !e.getObstacles().get(8)) {
            but22.setBackgroundResource(0);
            but22.setBackgroundResource(R.color.grey_light);
        } else if(e.getObstacles().get(8)) {
            but22.setBackgroundResource(R.drawable.obstacle);
        } else {
            but22.setBackgroundResource(R.drawable.ic_launcher);
        }

        int resourceId = 0;
        if(r.directionFacing == NORTH) {
            resourceId = R.drawable.robot_north;
        } else if(r.directionFacing == EAST) {
            resourceId = R.drawable.robot_east;
        } else if(r.directionFacing == SOUTH) {
            resourceId = R.drawable.robot_south;
        } else {
            //west
            resourceId = R.drawable.robot_west;
        }

        //updates the robots view location and string variable for printing
        String locationString = "";
        switch(r.currentLocation) {
            case 0:
                locationString = "(0,0)";
                but00.setBackgroundResource(resourceId);
                break;
            case 1:
                locationString = "(0,1)";
                but01.setBackgroundResource(resourceId);
                break;
            case 2:
                locationString = "(0,2)";
                but02.setBackgroundResource(resourceId);
                break;
            case 3:
                locationString = "(1,2)";
                but12.setBackgroundResource(resourceId);
                break;
            case 4:
                locationString = "(1,1)";
                but11.setBackgroundResource(resourceId);
                break;
            case 5:
                locationString = "(1,0)";
                but10.setBackgroundResource(resourceId);
                break;
            case 6:
                locationString = "(2,0)";
                but20.setBackgroundResource(resourceId);
                break;
            case 7:
                locationString = "(2,1)";
                but21.setBackgroundResource(resourceId);
                break;
            case 8:
                locationString = "(2,2)";
                but22.setBackgroundResource(resourceId);
                break;
        }
        String directionString = "";
        switch(r.directionFacing) {
            case 0:
                directionString = "NORTH";
                break;
            case 1:
                directionString = "EAST";
                break;
            case 2:
                directionString = "SOUTH";
                break;
            case 3:
                directionString = "WEST";
                break;
        }
        steps.setText(steps.getText().toString() + "\nView Updated Robot Facing: "+directionString+"\nIs at Location: "+locationString);
    }

    public void automaticRobotWithObstacles(View v, Environment e, Robot r) {
        steps.setText(steps.getText().toString() + "\nStarting AUTOMATED ROBOT");
        int cleanedCounter = 0;

        //sets location to first open location
        if(r.start(environment)) {
            for(int i = 0; i < robot.currentLocation; i++) {
                //checked all locations before the start
                Log.i(TAG, "Setting to true: " + i);
                r.checkedLocations[i]++;
            }
            updateView(v,e,r);
        } else {
            steps.setText(steps.getText().toString() + "\nEnding AUTOMATED ROBOT all locations have obstacles");
            return;
        }
        //til all locations have been checked
        while(checkIfAnySpotHasntBeenChecked(r.checkedLocations)) {
            Log.i(TAG, "Starting looop: " +"at location: "+r.currentLocation+"\n"+r.checkedLocations.toString());
            if(r.checkForDirt(e)) {
                cleanedCounter++;
                steps.setText(steps.getText().toString() + "\nCleaned Dirt!");
                r.clean(e);
                updateView(v,e,r);
            }

            //check for obstacles and move in the direction with no obstacles
            int[] directions = robot.getPossibleAutoDirections(environment);

            //debugging purposes
            for(int i = 0; i < directions.length; i++) {
                Log.i(TAG, "Directions is "+i);
            }

            //Movement in following order: NORTH, EAST, SOUTH, WEST
/*            if(directions[0] == 0) {
                Log.i(TAG, "GOING NORTH");
                steps.setText(steps.getText() + "\nAutomated: GOING NORTH");
                r.directionFacing = 0;
                r.moveForward();
            }else if(directions[1] == 0) {
                Log.i(TAG, "GOING EAST");
                steps.setText(steps.getText() + "\nAUTOMATED: GOING EAST");
                r.directionFacing = 1;
                r.moveForward();
            } else if(directions[2] == 0) {
                Log.i(TAG, "GOING SOUTH");
                steps.setText(steps.getText() + "\nAUTOMATED: GOING SOUTH");
                r.directionFacing = 2;
                r.moveForward();
            }else if(directions[3] == 0) {
                Log.i(TAG, "GOING WEST");
                steps.setText(steps.getText() + "\nAUTOMATED: GOING WEST");
                r.directionFacing = 3;
                r.moveForward();
            }*/

            //checks if no more possible movements
            if((directions[0] == 1 || directions[0]==2  ||directions[0]==3 )
                    && (directions[1] == 1 || directions[1]==2 ||directions[1]==3)
                    && (directions[2] == 1 || directions[2]==2 ||directions[2]==3)
                    && (directions[3] == 1 || directions[3]==2 ||directions[3]==3)) {
                steps.setText(steps.getText() + "\nNo more possible movements obstacles all around or we're avoiding an infinite loop");
                return;
            }

            //choose a random direction to move
            List<String> possibleDirections = new ArrayList<String>();
            if(directions[0] == 0) {
                possibleDirections.add("N");
            }
            if(directions[1] == 0) {
                possibleDirections.add("E");
            }
            if(directions[2] == 0) {
                possibleDirections.add("S");
            }
            if(directions[3] == 0) {
                possibleDirections.add("W");
            }

            Log.i(TAG, "Possible Directions: "+possibleDirections.toString());
            if(possibleDirections.size() > 0) {
                Random random = new Random();
                String randomDirection = possibleDirections.get(random.nextInt(possibleDirections.size()));
                switch (randomDirection) {
                    case "N":
                        Log.i(TAG, "RANDOM GOING NORTH");
                        steps.setText(steps.getText() + "\nAutomated: RANDOM GOING NORTH");
                        r.directionFacing = 0;
                        r.moveForward();
                        break;
                    case "E":
                        Log.i(TAG, "RANDOM GOING EAST");
                        steps.setText(steps.getText() + "\nAUTOMATED: RANDOM GOING EAST");
                        r.directionFacing = 1;
                        r.moveForward();
                        break;
                    case "S":
                        Log.i(TAG, "RANDOM GOING SOUTH");
                        steps.setText(steps.getText() + "\nAUTOMATED: RANDOM GOING SOUTH");
                        r.directionFacing = 2;
                        r.moveForward();
                        break;
                    case "W":
                        Log.i(TAG, "RANDOM GOING WEST");
                        steps.setText(steps.getText() + "\nAUTOMATED: RANDOM GOING WEST");
                        r.directionFacing = 3;
                        r.moveForward();
                        break;
                    default:
                        steps.setText(steps.getText() + " Invalid direction to randomly move");
                        return;
                }
            }
            updateView(v, e, r);
        }
        Log.i(TAG, "Loop ended");
    }

    public Boolean checkIfAnySpotHasntBeenChecked(int[] array) {
        for(int i : array) {
            //if all locations have been checked more than 5 times
            if(i <= 5) {
                return true;
            }
        }
        return false;
    }
}