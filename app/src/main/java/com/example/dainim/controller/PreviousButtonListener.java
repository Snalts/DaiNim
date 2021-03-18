package com.example.dainim.controller;

import android.view.View;

import com.example.dainim.model.EnumWeek;
import com.example.dainim.view.PlanningActivity;

/**
 * Class implementing the button's listener that retrieve the previous day's animes in the planning page
 */
public class PreviousButtonListener implements View.OnClickListener
{
    /**
     * Planning page activity
     */
    private PlanningActivity planning_activity;

    /**
     * PreviousButtonListener constructor
     * @param planning_activity The planning page activity the listener is listening to
     */
    public PreviousButtonListener(PlanningActivity planning_activity)
    {
        this.planning_activity = planning_activity;
    }

    /**
     * The method executed when the listening button is clicked
     * @param v The clicked view
     */
    @Override
    public void onClick(View v)
    {
        this.planning_activity.displayPlanning(this.planning_activity.getAnimeWeek().getPreviousDay(), EnumWeek.previousDay(this.planning_activity.getEnumWeek()));
    }
}
