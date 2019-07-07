package com.test.tc.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.test.tc.Presenter;
import com.test.tc.R;
import com.test.tc.databinding.ActivityMainBinding;
import com.test.tc.model.Counter;
import com.test.tc.retrofit.RetrofitProvider;

public class MainActivity extends AppCompatActivity {

    private Counter counter;
    private final Presenter presenter = new Presenter();
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        if (savedInstanceState == null) {
            counter = new Counter();
            binding.setCounter(counter);
        }
    }

    public void callApi(View view) {
        presenter.getData(RetrofitProvider.getInstance(), counter);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable("savedData", counter);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        counter = savedInstanceState.getParcelable("savedData");
        binding.setCounter(counter);
    }
}

