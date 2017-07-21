package com.example.a15017381.p09_gettingmylocations;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Created by 15017381 on 21/7/2017.
 */

public class SecondActivity extends AppCompatActivity {

    ListView lv;
    ArrayAdapter aa;
    ArrayList<String> records;
    Button btnRefresh;
    TextView tvRecords;
    String folderLocation = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Test";
    File targetFile = new File(folderLocation, "location1.txt");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        lv = (ListView)findViewById(R.id.lvRecords);
        btnRefresh = (Button)findViewById(R.id.btnRefresh);
        tvRecords = (TextView)findViewById(R.id.tvRecords);

        records = new ArrayList<>();
        aa = new ArrayAdapter(SecondActivity.this, android.R.layout.simple_list_item_1, records);
        lv.setAdapter(aa);


        if (targetFile.exists() == true){
            try {
                FileReader reader = new FileReader(targetFile);
                BufferedReader br = new BufferedReader(reader);
                String line = br.readLine();
                while (line != null){
                    records.add(line);
                    aa.notifyDataSetChanged();
                    line = br.readLine();
                }
                br.close();
                reader.close();
            } catch (Exception e) {
                Toast.makeText(this, "Failed to read!",
                        Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
            tvRecords.setText("Total records: "+records.size());
        }

        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                records.clear();
                if (targetFile.exists() == true){
                    try {
                        FileReader reader = new FileReader(targetFile);
                        BufferedReader br = new BufferedReader(reader);
                        String line = br.readLine();
                        while (line != null){
                            records.add(line);
                            aa.notifyDataSetChanged();
                            line = br.readLine();
                        }
                        br.close();
                        reader.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    tvRecords.setText("Total records: "+records.size());

                }
            }
        });

    }
}
