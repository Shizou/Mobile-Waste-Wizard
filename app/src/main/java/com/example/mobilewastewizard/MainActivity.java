package com.example.mobilewastewizard;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.PopupWindow;

import java.util.ArrayList;

/**
 * Main activity class that will delegate tasks  given from the user to their
 * proper modules.
 *  
 * @author William Granados, Justin Li, Burhan Quadri
 *
 */
public class MainActivity extends AppCompatActivity{
  
  /** fragment stuff manager*/
  private BottomNavigationView bottomNavigationView;
  /** */
  private RecentFragment recentFragment;
  /** */
  private SearchItemFragment searchItemFragment;


  /**
   * Initializes the main activity, fills auto-complete suggestions with database items.
   */
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    this.bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);
    this.recentFragment = recentFragment.newInstance("1", "2");
    this.searchItemFragment = SearchItemFragment.newInstance(1);
    this.bottomNavigationView.setOnNavigationItemSelectedListener(
            new BottomNavigationView.OnNavigationItemSelectedListener() {
              @Override
              public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                switch (item.getItemId()) {
                  case R.id.bottombaritem_recents:
                    transaction.replace(R.id.fragment_container, recentFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                    return true;
                  case R.id.bottombaritem_searches:
                    transaction.replace(R.id.fragment_container, searchItemFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                    return true;
                  case R.id.bottombaritem_settings:
                    // TODO
                    return true;
                }
                return false;
              }
            });
  }
}
