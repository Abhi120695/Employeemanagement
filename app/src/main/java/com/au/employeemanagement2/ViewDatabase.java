package com.au.employeemanagement2;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Abhishek-Pc on 10-01-2016.
 */
public class ViewDatabase extends Fragment {


    public ListView List;
  //  private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    //private RecyclerViewAdapter recyclerViewAdapter;
    private EditText addTaskBox;
    private DatabaseReference databaseReference;
    private DatabaseReference databaseReference2;
    public ArrayAdapter<user> Users;

    private Integer percentvalue;
    private List<user> alluser;

    public SearchView Search;

    public static EditText newslots;

    private Button select2;

    private Button Update;
    String detail;






    public static ViewDatabase newInstance() {
        ViewDatabase fragment = new ViewDatabase();
        return fragment;
    }
    public ViewDatabase() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

         View rootView = inflater.inflate(R.layout.viewdatabase, container, false);
        super.onCreate(savedInstanceState);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference2=databaseReference.child("users");
        alluser=new ArrayList<>();

       List= (ListView)rootView.findViewById(R.id.listView);
      //  recyclerView = (RecyclerView) rootView.findViewById(R.id.listView);
        newslots=(EditText)rootView.findViewById(R.id.editText4);

        //select2=(Button)rootView.findViewById(R.id.button60);
        //Update=(Button)rootView.findViewById(R.id.button6);
        // setSupportActionBar(toolbar);
        //Bundle args = getArguments();




        //setListAdapter(Students);

        List.setTextFilterEnabled(true);
        Search = (SearchView)rootView.findViewById(R.id.searchView);
        Search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                Users.getFilter().filter(s);

                return true;
            }
        });


        databaseReference2.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                getAllUser(dataSnapshot);
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                getAllUser(dataSnapshot);
            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
               // taskDeletion(dataSnapshot);
            }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });




/*
        List.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //  Retriveddata mRetriveddata=retriveddatas.get(i);




                Free =name+"\n"+Regno+"\n"+Takenslots+Taken+"\n"+ Club2+Club+"\n"+FreeTimings;
                Free2=mon + "\n" + tue + "\n" + wed + "\n" + thur + "\n" + fri;
                displaydetails(Free,Free2);

            }


        });
        */
        //registerForContextMenu(List);
        return rootView;
        //List.setOnItemLongClickListener();

    }
    private void getAllUser(DataSnapshot dataSnapshot){
        //for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
           user muser = dataSnapshot.getValue(user.class);
            alluser.add(muser);
          //  Students = new ArrayAdapter<Retriveddata>(getActivity(), android.R.layout.simple_list_item_1, retriveddatas);
           // List.setAdapter(Students);

            //  recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, allTask);
            // recyclerView.setAdapter(recyclerViewAdapter);

        Users=new ArrayAdapter<user>(getActivity(),android.R.layout.simple_list_item_1,alluser);
        List.setAdapter(Users);
        List.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> mAdapterView, View mView, int mI, long mL) {
                user mUser1=alluser.get(mI);
                Integer value = Integer.valueOf(mUser1.mgetDay_1()) + Integer.valueOf(mUser1.mgetDay_2()) + Integer.valueOf(mUser1.mgetDay_3()) + Integer.valueOf(mUser1.mgetDay_4()) + Integer.valueOf(mUser1.mgetDay_5()) + Integer.valueOf(mUser1.mgetDay_6()) + Integer.valueOf(mUser1.mgetDay_7()) + Integer.valueOf(mUser1.mgetDay_8()) + Integer.valueOf(mUser1.mgetDay_9()) + Integer.valueOf(mUser1.mgetDay_10()) +
                        +Integer.valueOf(mUser1.mgetDay_11()) + Integer.valueOf(mUser1.mgetDay_12()) + Integer.valueOf(mUser1.mgetDay_13()) + Integer.valueOf(mUser1.mgetDay_14()) + Integer.valueOf(mUser1.mgetDay_15()) + Integer.valueOf(mUser1.mgetDay_16()) + Integer.valueOf(mUser1.mgetDay_17())
                        + Integer.valueOf(mUser1.mgetDay_18()) + Integer.valueOf(mUser1.mgetDay_19()) + Integer.valueOf(mUser1.mgetDay_20()) + Integer.valueOf(mUser1.mgetDay_21()) + Integer.valueOf(mUser1.mgetDay_22()) + Integer.valueOf(mUser1.mgetDay_23())
                        + Integer.valueOf(mUser1.mgetDay_24()) + Integer.valueOf(mUser1.mgetDay_25()) + Integer.valueOf(mUser1.mgetDay_26()) + Integer.valueOf(mUser1.mgetDay_27()) + Integer.valueOf(mUser1.mgetDay_28()) + Integer.valueOf(mUser1.mgetDay_29())
                        + Integer.valueOf(mUser1.mgetDay_30()) + Integer.valueOf(mUser1.mgetDay_31());
                detail = "Day_1-"  + getString(mUser1.mgetDay_1()) + "\n" + "Day_2-"  + getString(mUser1.mgetDay_2()) + "\n" + "Day_3-"  + getString(mUser1.mgetDay_3()) + "\n" + "Day_4-"  + getString(mUser1.mgetDay_4()) + "\n" + "Day_5-" + getString(mUser1.mgetDay_5()) + "\n"+
                        "Day_6-"  + getString(mUser1.mgetDay_6()) + "\n" + "Day_7-"  + getString(mUser1.mgetDay_7()) + "\n" + "Day_8-"  + getString(mUser1.mgetDay_8()) + "\n" + "Day_9-"  + getString(mUser1.mgetDay_9()) + "\n" + "Day_10-"  + getString(mUser1.mgetDay_10()) + "\n"+
                        "Day_11-"  + getString(mUser1.mgetDay_11()) + "\n" + "Day_12-"  + getString(mUser1.mgetDay_12()) + "\n" + "Day_13-"  + getString(mUser1.mgetDay_13()) + "\n" + "Day_14-"  + getString(mUser1.mgetDay_14()) + "\n" + "Day_15-"  + getString(mUser1.mgetDay_15()) + "\n"+
                        "Day_16-"  + getString(mUser1.mgetDay_17()) + "\n" + "Day_17-"  + getString(mUser1.mgetDay_17()) + "\n" + "Day_18-"  + getString(mUser1.mgetDay_18()) + "\n" + "Day_19-"  + getString(mUser1.mgetDay_18()) + "\n" + "Day_20-"  + getString(mUser1.mgetDay_20()) + "\n"+
                        "Day_21-"  + getString(mUser1.mgetDay_22()) + "\n" + "Day_22-"  + getString(mUser1.mgetDay_22()) + "\n" + "Day_23-"  + getString(mUser1.mgetDay_23()) + "\n" + "Day_24-"  + getString(mUser1.mgetDay_23()) + "\n" + "Day_25-"  + getString(mUser1.mgetDay_25()) + "\n"+
                        "Day_26-"  + getString(mUser1.mgetDay_26()) + "\n" + "Day_27-"  + getString(mUser1.mgetDay_27()) + "\n" + "Day_28-"  + getString(mUser1.mgetDay_28()) + "\n" + "Day_29-"  + getString(mUser1.mgetDay_29()) + "\n" + "Day_30-"  + getString(mUser1.mgetDay_30()) + "\n"+
                        "Day_31-"  + getString(mUser1.mgetDay_31());
                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int totaldays = countWeekendDays(year, month);
                Log.v("check2", String.valueOf(totaldays));
                float percent = getpercent(value, totaldays);
                int percentfinal = Math.round(percent);

                String percentstring = Float.toString(percent);
                Toast.makeText(getActivity(), " attendence for "+mUser1.name+" till now is " + percentstring, Toast.LENGTH_SHORT).show();
                percentvalue = percentfinal;
                Intent intent = new Intent(getActivity(), Viewattendence.class);
                intent.putExtra("percent", percentvalue);
                intent.putExtra("detail", detail);
                startActivity(intent);

            }
        });

    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.add(0, v.getId(), 0, "View");//groupId, itemId, order, title
        menu.add(0, v.getId(), 0, "Delete");
        menu.add(0, v.getId(), 0, "Update");
        menu.add(0, v.getId(), 0, "sync");

    }
    @Override
    public boolean onContextItemSelected(MenuItem item){
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final int i= info.position;
        if(item.getTitle()=="View"){


/*

            Free =name+"\n"+Regno+"\n"+Takenslots+Taken+"\n"+ Club2+Club+"\n"+FreeTimings;
            Free2=mon + "\n" + tue + "\n" + wed + "\n" + thur + "\n" + fri;
            displaydetails(Free,Free2);*/
        }
        else if(item.getTitle()=="Delete") {
            new AlertDialog.Builder(getActivity()).setTitle("Confirm Delete")
                    .setMessage("Are you sure you want delete this Data?")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {



                        }
                    })
                    .setNeutralButton("Cancel", null)
                    .create()
                    .show();
        }
        else if (item.getTitle()=="Update"){
         //   List.setVisibility(View.INVISIBLE);

            return false;
        }
        else if(item.getTitle()=="sync"){




        }

        return true;
    }
    public int countWeekendDays(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        // Note that month is 0-based in calendar, bizarrely.
        // calendar.set(year, month - 1, 1);
        //int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int daysInMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int count = 0;
        Log.v("check3", String.valueOf(daysInMonth));

        for (int day = 1; day <= daysInMonth; day++) {
            calendar.set(year, month, day);
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

            if (dayOfWeek == Calendar.SUNDAY || dayOfWeek == Calendar.SATURDAY) {
                count++;
                // Or do whatever you need to with the result.
            }
        }
        int totaldays = daysInMonth - count;
        return totaldays;
    }

    
    String  getString(String s){
        switch (s){
            case"0":return "absent";
            case "1":return "present";
        }
        return null;
    }

    private float getpercent(Integer mValue, int mTotaldays) {
        float proportionCorrect = ((float) mValue) / ((float) mTotaldays);
        return proportionCorrect * 100;
    }

}

