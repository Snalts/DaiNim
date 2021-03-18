package com.example.dainim.controller;

import android.view.View;

import com.example.dainim.model.EnumWeek;
import com.example.dainim.view.PlanningActivity;

public class PreviousButtonListener implements View.OnClickListener
{
    private PlanningActivity planning_activity;

    public PreviousButtonListener(PlanningActivity planning_activity)
    {
        this.planning_activity = planning_activity;
    }

    @Override
    public void onClick(View v)
    {
        this.planning_activity.displayPlanning(this.planning_activity.getAnimeWeek().getPreviousDay(), EnumWeek.lastDay(this.planning_activity.getEnumWeek()));
    }
}
