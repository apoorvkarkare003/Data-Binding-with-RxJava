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

    private final Counter counter = new Counter();
    private final Presenter presenter = new Presenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setCounter(counter);
    }

    public void callApi(View view) {
        presenter.getData(RetrofitProvider.getInstance(), counter);
    }

}

