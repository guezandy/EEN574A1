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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.een574.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class VacuumEnvironmentController extends Fragment {
    public String TAG = VacuumEnvironment.class.getSimpleName();

    private LinearLayout mView;
    public VacuumEnvironment environment;
    public Vacuum vacuum;


    public int n = 5;


    public Boolean insertingObstacles = false;
    public Button insertObstacles;
    public Button automate;
    public Button reset;



    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //android inflate layout
        mView = (LinearLayout) inflater.inflate(R.layout.fragment_nxn, container, false);

        initializeEnvironment(mView);

        return mView;
    }

    public void initializeEnvironment(View v) {
        insertObstacles = (Button) v.findViewById(R.id.obstaclesnxn);
        automate = (Button) v.findViewById(R.id.automatenxn);
        reset = (Button) v.findViewById(R.id.resetnxn);
        environment = new VacuumEnvironment(n);
        vacuum = new Vacuum(environment);

        for(int i = 0; i < n; i++) {
            //create n linear layout for buttons
            final String layoutName = "row"+i;
            environment.LayoutMap.put(layoutName, new LinearLayout(getActivity()));
            LinearLayout row = environment.LayoutMap.get(layoutName);
            row.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            for(int j = 0; j < n; j++) {
                final String locationName = "l"+i+j;
                environment.LocationMap.put(locationName, new VacuumLocation(new Button(getActivity()), i, j));
                Button currentButton = environment.LocationMap.get(locationName).getView();
                currentButton.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
                currentButton.setText("(" + i + "," + j + ")");
                currentButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        VacuumLocation loc = environment.LocationMap.get(locationName);
                        if(insertingObstacles) {//if were not currently inserting obstacles
                            loc.isObstacle = true;
                            loc.checked = true;
                            updateView(v,environment, vacuum);
                        } else if(!loc.isObstacle){ //if the location is not already an obstacle
                            loc.hasDirt = true;
                            updateView(v, environment, vacuum);
                        }
                    }
                });
                row.addView(currentButton);
            }
            mView.addView(row);
        }

        insertObstacles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(insertingObstacles) {
                    insertingObstacles = false;
                    Toast.makeText(getActivity().getBaseContext(), "You cannot insert obstacles", Toast.LENGTH_SHORT).show();
                } else {
                    insertingObstacles = true;
                    Toast.makeText(getActivity().getBaseContext(), "You can now insert obstacles", Toast.LENGTH_SHORT).show();
                }
            }
        });

        automate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(vacuum.start()) {
                    updateView(v, environment, vacuum);
                    vacuum.solve();
                    updateView(v,environment,vacuum);
                } else {
                    Toast.makeText(getActivity().getBaseContext(), "All locations have obstacles", Toast.LENGTH_SHORT).show();
                }
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //restart the fragment
                ((MainActivity) getActivity()).replaceFragment(new VacuumEnvironmentController(), true, FragmentTransaction.TRANSIT_FRAGMENT_FADE, getString(R.string.title_section1));

            }
        });
    }


    public void updateView(View v, VacuumEnvironment e, Vacuum vm) {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                String lName = "l"+i+j;
                VacuumLocation loc = environment.LocationMap.get(lName);
                Button locButton = loc.getView();
                //robot is on location
                if(vm.x == i && vm.y == j) {
                    locButton.setBackgroundResource(R.drawable.vacuum);
                }
                else if(loc.isObstacle) {
                    locButton.setBackgroundResource(R.drawable.brick);
                } else if (loc.hasDirt) {
                    locButton.setBackgroundResource(R.drawable.dirty);
                } else {
                    locButton.setBackgroundResource(0);
                    locButton.setBackgroundResource(R.color.grey_light);
                    locButton.setText("(" + i + "," + j + ")");
                }
            }
        }
    }
}