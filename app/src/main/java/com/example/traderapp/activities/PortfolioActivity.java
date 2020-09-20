package com.example.traderapp.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traderapp.portfolio.PortfolioContract;
import com.example.traderapp.portfolio.PortfolioPresenter;
import com.example.traderapp.R;
import com.example.traderapp.portfolio.RecyclerViewAdaptor;
import com.example.traderapp.models.Item;

import java.text.DecimalFormat;
import java.util.List;


public class PortfolioActivity extends AppCompatActivity implements PortfolioContract.PortfolioView {
    String defaultAccount = "";
    String id , pass = "";
    RecyclerView mRecyclerView;
    TextView totalTextView;
    ProgressDialog progress;
    private PortfolioContract.PortfolioPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.portfolio_activity2);
        presenter = new PortfolioPresenter(PortfolioActivity.this);
        mRecyclerView= findViewById(R.id.rcycler_view);

        totalTextView = findViewById(R.id.toplamTutarNumberText);
        progress = new ProgressDialog(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            defaultAccount = extras.getString("DefaultAccount");
            id   = extras.getString("id");
            pass = extras.getString("pass");
        }
        else
            Log.i("In portfolio acc","could not get the extras.");

        presenter.getData(id, pass, defaultAccount);
    }


    @Override
    public void showLoader() {
        if (progress != null) {
            progress.setTitle(getString(R.string.veri));
            progress.setMessage(getString(R.string.bekleyiniz));
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progress.show();
        }
    }

    @Override
    public void hideLoader() {
        if (progress != null)
            progress.dismiss();
    }

    @Override
    public void onSuccess(List<Item> itemList, Double total) {
        RecyclerViewAdaptor adaptor = new RecyclerViewAdaptor(this, itemList);
        mRecyclerView.setAdapter(adaptor);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        DecimalFormat formatter = new DecimalFormat("#,###.00");
        totalTextView.setText(formatter.format(total));
    }

    @Override
    public void onError(String msg) {
        Toast toast = Toast.makeText(getApplicationContext(), msg , Toast.LENGTH_LONG);
        toast.show();
    }

}

