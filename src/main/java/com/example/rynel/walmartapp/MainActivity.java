package com.example.rynel.walmartapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.rynel.walmartapp.data.RemoteDataSource;
import com.example.rynel.walmartapp.model.Item;
import com.example.rynel.walmartapp.model.WalmartLookup;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    //declaring objects as per RecyclerView (layout/item animator), DatabaseHelper, and adapter
    RecyclerView recyclerViewQuery;
    List<Item> walmartLookups = new ArrayList<>();
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.ItemAnimator itemAnimator;
    private WalmartListAdapter walmartListAdapter;
    Button searchBtn;
    EditText queryView;
    TextView state;

    //starting position for search
    int start = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //bind/initialize the views, button, and query
        recyclerViewQuery = findViewById(R.id.rvWalmartItemList);
        queryView = findViewById(R.id.etQuery);
        searchBtn = findViewById(R.id.btnSearch);
        layoutManager = new LinearLayoutManager(this);
        itemAnimator = new DefaultItemAnimator();
        recyclerViewQuery.setLayoutManager(layoutManager);
        recyclerViewQuery.setItemAnimator(itemAnimator);
        state = findViewById(R.id.tvState);

    }




    public void search(View view) {

        closeKeyboard();

        String query = queryView.getText().toString();

        //reset everything
        walmartLookups = new ArrayList<>();
        recyclerViewQuery.setAdapter(null);
        start = 1;


        //populating screen by calling api
        RemoteDataSource.getWalmartLookup(query, start)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<WalmartLookup>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.d(TAG, "onSubscribe: ");
                        state.setText("Searching...");
                    }

                    @Override
                    public void onNext(@NonNull WalmartLookup walmartLookup) {
                        Log.d(TAG, "onNext: ");
                        Log.d(TAG, "onNext: query: " + walmartLookup.getQuery());
                        Log.d(TAG, "onNext: total results: " + walmartLookup.getTotalResults());
                        Log.d(TAG, "onNext: get start: " + walmartLookup.getStart());

                        if (walmartLookup.getTotalResults() != 0) {
                            Log.d(TAG, "onNext: items count: " + walmartLookup.getItems().size());
                            walmartLookups = walmartLookup.getItems();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "onError: " + e.toString());
                        state.setText("No Results.");
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");

                        walmartListAdapter = new WalmartListAdapter(walmartLookups);
                        recyclerViewQuery.setAdapter(walmartListAdapter);

                        String s = (walmartLookups.size() > 0) ? "" : "No Results";
                        state.setText(s);
                    }
                });
    }


    public void closeKeyboard() {
        View v = this.getCurrentFocus();
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }
}

