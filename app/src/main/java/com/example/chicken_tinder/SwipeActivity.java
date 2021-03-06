package com.example.chicken_tinder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;
import java.util.List;

public class SwipeActivity extends AppCompatActivity {
    private ArrayList<String> al;
    private ArrayAdapter<String> arrayAdapter;
    private int i;
    private FindRestaurants findRestaurants;
    Handler handlerThread;
    //@InjectView(R.id.frame) SwipeFlingAdapterView flingContainer;
    SwipeFlingAdapterView flingContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handlerThread = new Handler();
        setContentView(R.layout.activity_swipe);
        //ButterKnife.inject(this);
        int mileage = getIntent().getIntExtra(ChooseSingleOrGroupActivity.MILEAGE,0);
        findRestaurants = new FindRestaurants(this, mileage, handlerThread);
        new Thread(findRestaurants).start();

        al = new ArrayList<>();
        /* //TODO: need to get learn more on async functions and returning after complete
        for (Result result : restaurantResults) {
            al.add(result.name);
        }

         */
        //al.add("Chinese");


        arrayAdapter = new ArrayAdapter<>(this, R.layout.item, R.id.helloText, al );

        flingContainer = (SwipeFlingAdapterView) findViewById(R.id.frame);

        flingContainer.setAdapter(arrayAdapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
                Log.d("LIST", "removed object!");
                al.remove(0);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                //Do something on the left!
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject
                Toast.makeText(SwipeActivity.this, "Left",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                Toast.makeText(SwipeActivity.this, "Right",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                // Ask for more data here
                //al.add("XML ".concat(String.valueOf(i)));
                //arrayAdapter.notifyDataSetChanged();
                Log.d("LIST", "notified");
                //i++;
            }

            @Override
            public void onScroll(float scrollProgressPercent) {
                /*
                View view = flingContainer.getSelectedView();
                view.findViewById(R.id.item_swipe_right_indicator).setAlpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0);
                view.findViewById(R.id.item_swipe_left_indicator).setAlpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);

                 */
            }
        });


        // Optionally add an OnItemClickListener
        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
                Toast.makeText(SwipeActivity.this, "Click",Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void addRestaurant(String name) {
        al.add(name);
    }

    public void refreshAdapter() {
        arrayAdapter.notifyDataSetChanged();
        Log.d("SwipeActivity","refreshAdapter called");
    }

    /*
    static void makeToast(Context ctx, String s){
        Toast.makeText(ctx, s, Toast.LENGTH_SHORT).show();
    }

     */



    //@OnClick(R.id.right)
    //public void right() {
     //   /**
      //   * Trigger the right event manually.
      //   */
       // flingContainer.getTopCardListener().selectRight();
    //}

    //@OnClick(R.id.left)
    //public void left() {
    //    flingContainer.getTopCardListener().selectLeft();
   // }


    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe);
    }

     */
}
