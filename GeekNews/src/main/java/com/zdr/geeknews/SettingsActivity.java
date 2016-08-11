package com.zdr.geeknews;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import utils.Constants;

public class SettingsActivity extends AppCompatActivity {
    RelativeLayout rl2;
    RelativeLayout rl3;
    TextView tvTextSize;
    TextView tvLoadType;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        rl2 = (RelativeLayout) findViewById(R.id.settings_rl2);
        rl3 = (RelativeLayout) findViewById(R.id.settings_rl3);
        tvTextSize = (TextView) findViewById(R.id.settings_rl2_size);
        tvLoadType = (TextView) findViewById(R.id.settings_rl3_loadType);

        sp = getSharedPreferences(Constants.SP_FILE, Context.MODE_PRIVATE);
        editor = sp.edit();
        tvTextSize.setText(sp.getString("size","小"));
        tvLoadType.setText(sp.getString("type","极省流量(不下载图)"));

        rl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SettingsActivity.this);
                AlertDialog dialog = builder.setTitle("字体大小")
                        .setSingleChoiceItems(R.array.textSize, 1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                editor.putString("size", getResources().getTextArray(R.array.textSize)[which].toString());
                                editor.apply();
                                tvTextSize.setText(sp.getString("size","小"));
                                dialog.dismiss();
                            }
                        })
                        .create();
                dialog.show();
            }
        });
        rl3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SettingsActivity.this);
                AlertDialog dialog = builder.setTitle("流量控制")
                        .setSingleChoiceItems(R.array.loadType, 1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                tvLoadType.setText(getResources().getTextArray(R.array.loadType)[which]);
                                editor.putString("type", getResources().getTextArray(R.array.loadType)[which].toString());
                                editor.apply();
                                tvLoadType.setText(sp.getString("type","极省流量(不下载图)"));
                                dialog.dismiss();
                            }
                        })
                        .create();
                dialog.show();
            }
        });
    }
}
