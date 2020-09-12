package com.example.gamloto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText mEdtSoMin, mEdtSoMax;
    Button mBtnRandom;
    TextView mTvResult;
    String mKetQua="";
    int _Even = 0;
    int _arrEven[] = { };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEdtSoMin = findViewById(R.id.edtSoMin);
        mEdtSoMax = findViewById(R.id.edtSoMax);
        mBtnRandom = findViewById(R.id.btnRandom);
        mTvResult = findViewById(R.id.tvResult);

        mBtnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String tSoMin = mEdtSoMin.getText().toString();
                String tSoMax = mEdtSoMax.getText().toString();

                if (tSoMin.isEmpty()||tSoMax.isEmpty())
                {
                    //Log.d("BBB2","Thong Bao :" + tSoMin +" , "+tSoMax);
                    Toast.makeText(MainActivity.this,"Ban Chua Nhap Du Thong Tin", Toast.LENGTH_LONG).show();
                    return; // nếu không đúng thì ngưng các câu lệnh phía sau.
                }

                // Chuyển dữ liệu thành số
                int _SoMin = Integer.parseInt(tSoMin);
                int _SoMax = Integer.parseInt(tSoMax);

                if (_SoMin>_SoMax)
                {
                    _SoMax = _SoMin+1;
                }
                mEdtSoMin.setText(String.valueOf(_SoMin));
                mEdtSoMax.setText(String.valueOf(_SoMax));

                Random random = new Random();
                int Value = random.nextInt(_SoMax-_SoMin+1)+_SoMin;

                if (_Even > 0)
                {
                    for (int j=0; j <_Even ; j++) {
                      if (_arrEven[j] == Value)
                        {
                            Toast.makeText(MainActivity.this, "Trung Du Lieu :" + Value, Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                }
                _arrEven = addX(_Even, _arrEven, Value);
                _Even++;
                //mKetQua += Value + "-";
           //     mKetQua = Arrays.toString(_arrEven);
                mKetQua += Value + "-";
                mTvResult.setText(mKetQua);
            }
        });
    }

    public static int[] addX(int n, int arr[], int x)
    {
        int i;
        int newarr[] = new int[n + 1];
        for (i = 0; i < n; i++)
            newarr[i] = arr[i];
        newarr[n] = x;
        return newarr;
    }
}