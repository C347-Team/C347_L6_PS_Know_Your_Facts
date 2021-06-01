package sg.edu.rp.c347.id19007966.knowyourfacts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ArrayList<Fragment> al;
    FragmentPagerAdapter adapter;
    ViewPager viewPager;
    Button btnReadLater;

    int reqCode = 12345;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        viewPager = findViewById(R.id.viewpager1);
        btnReadLater = findViewById(R.id.btnBack);

        FragmentManager fm = getSupportFragmentManager();

        al = new ArrayList<Fragment>();
        al.add(new Frag1());
        al.add(new Frag2());
        al.add(new Frag3());

        adapter = new FactsFragmentPagerAdapter(fm, al);

        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(sharedPreferences.getInt("pagePosition", 0), true);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("pagePosition", position);
                editor.apply();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        btnReadLater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
//                System.exit(0);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_previous) {
            if(viewPager.getCurrentItem() > 0){
                int previousPage = viewPager.getCurrentItem() - 1;
                viewPager.setCurrentItem(previousPage, true);
            }
        }
        else if (id == R.id.action_random) {
            Random rnd = new Random();
            viewPager.setCurrentItem(rnd.nextInt(3), true);


            return true;
        }
        else if (id == R.id.action_next) {
            int max = viewPager.getChildCount();
            if(viewPager.getCurrentItem() < max-1){
                int nextPage  = viewPager.getCurrentItem() + 1;
                viewPager.setCurrentItem(nextPage, true);

            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



}