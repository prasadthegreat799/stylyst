package com.nappdeveloper.salon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.nappdeveloper.salon.Fragments.bookingsFragment;
import com.nappdeveloper.salon.Fragments.exploreFragment;
import com.nappdeveloper.salon.Fragments.homeFragment;
import com.nappdeveloper.salon.Fragments.profileFragment;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.BottomNavigationView);
        bottomNavigationView.setBackground(null);
        Menu menuNav = bottomNavigationView.getMenu();
        getSupportFragmentManager().beginTransaction().replace(R.id.FragmentContainer, new homeFragment()).commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(bottomnavMethod);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener bottomnavMethod = new
            BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    Fragment fragment = null;
                    switch (item.getItemId()) {

                        case R.id.homeMenu:
                            fragment = new homeFragment();
                            break;

                        case R.id.exploreMenu:
                            fragment = new exploreFragment();
                            break;

                        case R.id.bookingMenu:
                            fragment = new bookingsFragment();
                            break;

                        case R.id.profileMenu:
                            fragment = new profileFragment();
                            break;

                        default:
                            Toast.makeText(getApplicationContext(), "Unknown Location", Toast.LENGTH_SHORT).show();

                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.FragmentContainer, fragment).commit();
                    return true;
                }

            };

}