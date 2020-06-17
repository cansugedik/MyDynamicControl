package com.example.mydynamiccontrol.box;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mydynamiccontrol.R;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by CANSU on 4.08.2018.
 */

public class DetailActivity extends AppCompatActivity {

    private View rootView;
    private RelativeLayout rowRelative;
    private LinearLayout kutuArkaplan;
    private TextView lblKutu;
    private Button btnKutu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.detail_page);

        initControls();
        initControlEvents();
    }

    private void initControls() {
        rootView = findViewById(R.id.box);

        rowRelative = rootView.findViewById(R.id.rowRelative);
        kutuArkaplan = rootView.findViewById(R.id.kutuArkaplan);
        lblKutu = rootView.findViewById(R.id.lblKutu);
        btnKutu = rootView.findViewById(R.id.btnKutu);
    }

    private void initControlEvents() {
        rootView.setBackgroundResource(R.color.colorBlue1);
        kutuArkaplan.setBackgroundResource(R.color.colorBlue1);

        btnKutu.setVisibility(View.GONE);
        lblKutu.setText("Kutunun Detay Sayfasındasınız");
        lblKutu.setTextSize(15);
    }

    // bu iki metot ile diğer sayfadaki kontrole giden bilgileri burada harekete geçirdik
    @Override
    public void onStart() {
        super.onStart();
        //EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
       // EventBus.getDefault().unregister(this);
    }

}
