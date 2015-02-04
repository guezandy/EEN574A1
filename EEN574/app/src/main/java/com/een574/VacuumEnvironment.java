package com.een574;


import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VacuumEnvironment {
    public String TAG = VacuumEnvironment.class.getSimpleName();
    public int n;

    public VacuumEnvironment() {
        n = 3;
    }

    public VacuumEnvironment(int num) {
        this.n = num;
    }

    //map to hold the Linear Layout rows
    //String  = "l"+x+y
    public Map<String, LinearLayout> LayoutMap = new HashMap<String, LinearLayout>();

    //map to hold buttons:
    //public Map<String, Button> ButtonMap = new HashMap<String, Button>();

    public Map<String, VacuumLocation> LocationMap = new HashMap<String, VacuumLocation>();

}