package com.ws.ijk.ws_ijk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FirstActivity extends AppCompatActivity {


    @BindView(R.id.XFLM)
    Button XFLM;
    @BindView(R.id.FPLM)
    Button FPLM;
    @BindView(R.id.FPLM2)
    Button FPLM2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.XFLM, R.id.FPLM, R.id.FPLM2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.XFLM:
                Intent intent = new Intent(FirstActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.FPLM:
                Intent intent2 = new Intent(FirstActivity.this, FPLMActivity.class);
                startActivity(intent2);
                break;
            case R.id.FPLM2:
                Intent intent3 = new Intent(FirstActivity.this, FPLMActivity.class);
                startActivity(intent3);
                break;


        }
    }

}
