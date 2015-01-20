package by.of.servicebook.myapplication;

import android.app.Activity;

import android.app.FragmentManager;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.support.v7.widget.Toolbar;


import com.activeandroid.query.Delete;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import by.of.servicebook.myapplication.fragments.AboutFragment;
import by.of.servicebook.myapplication.fragments.GarageFragment;
import by.of.servicebook.myapplication.fragments.RecordsFragment;
import by.of.servicebook.myapplication.fragments.ResponseFragment;
import by.of.servicebook.myapplication.fragments.StatisticFragment;
import by.of.servicebook.myapplication.models.Detail;
import by.of.servicebook.myapplication.models.Job;
import by.of.servicebook.myapplication.models.Record;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    public Toolbar mToolbar;
    /**
     * Used to store the last screen title. 
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNavigationDrawerFragment =(NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer, mDrawerLayout, mToolbar);

        downloadRecords();
        downloadJobs();
        downloadDetails();
    }

    public void downloadRecords(){
        try {
            new Delete().from(Record.class).execute();
            InputStream inputStream = getAssets().open("records.txt");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String records = new String(buffer);
            JSONArray jsonArray = new JSONArray(records);
            for (int i = 0; i < jsonArray.length(); i++) {
                new Record((JSONObject) jsonArray.get(i)).save();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void downloadJobs(){
        try {
            new Delete().from(Job.class).execute();
            InputStream inputStream = getAssets().open("jobs.txt");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String jobs = new String(buffer);
            JSONArray jsonArray = new JSONArray(jobs);
            for (int i = 0; i < jsonArray.length(); i++) {
                new Job((JSONObject) jsonArray.get(i)).save();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void downloadDetails(){
        try {
            new Delete().from(Detail.class).execute();
            InputStream inputStream = getAssets().open("details.txt");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String details = new String(buffer);
            JSONArray jsonArray = new JSONArray(details);
            for (int i = 0; i < jsonArray.length(); i++) {
                new Detail((JSONObject) jsonArray.get(i)).save();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item))
            return true;

        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        switch (position) {
            case 0:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, GarageFragment.newInstance())
                        .commit();
                break;
            case 1:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, RecordsFragment.newInstance())
                        .addToBackStack(null)
                        .commit();
                break;
            case 2:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, StatisticFragment.newInstance())
                        .commit();
                break;
            case 3:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, ResponseFragment.newInstance())
                        .commit();
                break;
            case 4:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, AboutFragment.newInstance())
                        .commit();
                break;
        }

    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.fragment_title_garage);
                break;
            case 2:
                mTitle = getString(R.string.fragment_title_records);
                break;
            case 3:
                mTitle = getString(R.string.fragment_title_statistic);
                break;
            case 4:
                mTitle = getString(R.string.fragment_title_response);
                break;
            case 5:
                mTitle = getString(R.string.fragment_title_about);
                break;
        }
        mToolbar.setTitle(mTitle);

    }



}
