package com.example.mobilewastewizard;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.MenuItem;

import com.example.mobilewastewizard.backend.Constants;
import com.example.mobilewastewizard.backend.Database;

import java.io.IOException;

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
  /** Fragment containing all of the recernt searches done by the user. */
  private RecentFragment recentFragment;
  /** Fragment containing all of the available search items.*/
  private SearchItemFragment searchItemFragment;
  /** Fragment containing all language preferences for the user.*/
  private PrefsFragment prefsFragment;

  /**
   * Initializes the main activity, fills auto-complete suggestions with database items.
   */
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    this.initiateDatabase();
    Database.getInstance().sortTotalList();
    this.bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);
    this.recentFragment = recentFragment.newInstance("1", "2");
    this.searchItemFragment = SearchItemFragment.newInstance(1);
    this.prefsFragment = new PrefsFragment();
    this.bottomNavigationView.setOnNavigationItemSelectedListener(
            new BottomNavigationView.OnNavigationItemSelectedListener() {
              @Override
              public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager fragmentManager = getFragmentManager();
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
                    transaction.replace(R.id.fragment_container, prefsFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                    return true;
                }
                return false;
              }
            });
  }
  public void initiateDatabase() {
    String pathInAssetsFolder = null;
    for (int i = 0; i < Constants.supportedCategoriesLanguages.length; i++) {
      // For every supported language we should have a translation of each object which goes into
      // the specified categories in Constants.Categories. Also since we're storing all of this information
      // locally, we'll be storing collecting this from informatino from the android assets folder.
      pathInAssetsFolder = String.format("categories/%s/Blue.txt", Constants.supportedCategoriesLanguages[i]);
      this.initiateFileInDatabase(pathInAssetsFolder, Constants.Categories.BLUE_BIN);

      pathInAssetsFolder = String.format("categories/%s/BTTSWD.txt", Constants.supportedCategoriesLanguages[i]);
      this.initiateFileInDatabase(pathInAssetsFolder, Constants.Categories.BRING_TO_TRANSFER_STATION_OR_WASTE_DEPOT);

      pathInAssetsFolder = String.format("categories/%s/EWaste.txt", Constants.supportedCategoriesLanguages[i]);
      this.initiateFileInDatabase(pathInAssetsFolder, Constants.Categories.E_WASTE);

      pathInAssetsFolder = String.format("categories/%s/Green.txt", Constants.supportedCategoriesLanguages[i]);
      this.initiateFileInDatabase(pathInAssetsFolder, Constants.Categories.GREEN_BIN);

      pathInAssetsFolder = String.format("categories/%s/Grey.txt", Constants.supportedCategoriesLanguages[i]);
      this.initiateFileInDatabase(pathInAssetsFolder, Constants.Categories.GREY_BIN);

      pathInAssetsFolder = String.format("categories/%s/HHW.txt", Constants.supportedCategoriesLanguages[i]);
      this.initiateFileInDatabase(pathInAssetsFolder, Constants.Categories.HOUSEHOLD_HAZARDOUS_WASTE);

      pathInAssetsFolder = String.format("categories/%s/OW.txt", Constants.supportedCategoriesLanguages[i]);
      this.initiateFileInDatabase(pathInAssetsFolder, Constants.Categories.OVERSIZED_WASTE);

      pathInAssetsFolder = String.format("categories/%s/SM.txt", Constants.supportedCategoriesLanguages[i]);
      this.initiateFileInDatabase(pathInAssetsFolder, Constants.Categories.SCRAP_METAL);

      pathInAssetsFolder = String.format("categories/%s/YW.txt", Constants.supportedCategoriesLanguages[i]);
      this.initiateFileInDatabase(pathInAssetsFolder, Constants.Categories.YARD_WASTE);
    }
    Database.getInstance().sortTotalList();
  }

  /**
   * Queries the backend of our application and instantiates these items in our text file to our database.
   *
   * @param filePath path to the corresponding file in our assets folder
   * @param itemType the type of the item that we'll be using.
   */
  public void initiateFileInDatabase(String filePath, Constants.Categories itemType) {
    try {
      Database.getInstance().retrieveInformationFromCategoryFile(getApplicationContext().getAssets().open(filePath), itemType);
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }
}
