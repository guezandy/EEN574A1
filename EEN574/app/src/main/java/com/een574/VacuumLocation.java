package com.een574;


import android.util.Log;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class VacuumLocation{
    public String TAG = VacuumLocation.class.getSimpleName();

    Button view;
    int x;
    int y;
    Boolean hasDirt;
    Boolean isObstacle;
    int moveToCount;
    int checkedCount;
    Boolean checked;
    Boolean junction;

    public VacuumLocation() {

    }
    public VacuumLocation(Button b, int x, int y) {
        view = b;
        hasDirt = false;
        checked = false;
        isObstacle = false;
        checkedCount = 0;
        this.x = x;
        this.y = y;
    }

    public void addView(Button b) {
        this.view = b;
    }

    public Button getView() {
        return this.view;
    }

}