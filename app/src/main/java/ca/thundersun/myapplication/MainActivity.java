package ca.thundersun.myapplication;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableBoolean;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import ca.thundersun.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    MyViewModel myViewModel;
    MyHandlers handlers;
    PojoDomainModel domain;

    // Observables for the UI
    public final ObservableBoolean actCheck = new ObservableBoolean(true);

    public MainActivity() {
        domain = new PojoDomainModel();
        myViewModel = new MyViewModel(domain);
        myViewModel.setBucks(2.34f);
        handlers = new MyHandlers(myViewModel, domain);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setModel(myViewModel);
        binding.setDomain(domain);
        binding.setHandlers(handlers);
        binding.setActivity(this);

        myViewModel.buckWatcher = new ValidNumberWatcher(binding.editTextBucks, myViewModel, ca.thundersun.myapplication.BR.bucks);
    }

    public void onClickInvalidate(View v) {
        binding.invalidateAll();
    }
}
