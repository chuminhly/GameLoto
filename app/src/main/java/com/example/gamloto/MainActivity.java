package com.example.gamloto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText mEdtSoMin, mEdtSoMax,mEdtFindNumber;
    Button mBtnRandom,mBtnReset,mbtnAddRang, mBtnFindNumber;
    TextView mTvResult,mTvResultRevert,mTvResultFindNumber;
    String mKetQua="";

    List<Integer> arrNum = new ArrayList<>();
    List<Integer> arrNumOut = new ArrayList<>();
    int _SoMin = 0;
    int _SoMax = 0;
    int mIndex = -1;
    int Result =-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEdtSoMin = findViewById(R.id.edtSoMin);
        mEdtSoMax = findViewById(R.id.edtSoMax);
        mBtnRandom = findViewById(R.id.btnRandom);

        mBtnFindNumber = findViewById(R.id.btnFindNumber);
        mEdtFindNumber = findViewById(R.id.editFindPostion);

        //mTvResult = findViewById(R.id.tvResult);
        mTvResult= (TextView) findViewById(R.id.tvResult);
        mTvResult.setMovementMethod(new ScrollingMovementMethod());

        mTvResultRevert = findViewById(R.id.tvResultRever);

        mTvResultFindNumber = findViewById(R.id.tvResultFindNumber);

        mbtnAddRang = findViewById(R.id.btnAddrang);
        mBtnReset = findViewById(R.id.btnReset);
        disableView(mBtnRandom);

        mbtnAddRang.setOnClickListener(new View.OnClickListener()
           {
               @Override
               public void onClick(View view) {
                   String tSoMin = mEdtSoMin.getText().toString();
                   String tSoMax = mEdtSoMax.getText().toString();

                   if (tSoMin.isEmpty()||tSoMax.isEmpty())
                   {
                       //Log.d("BBB2","Thong Bao :" + tSoMin +" , "+tSoMax);
                       Toast.makeText(MainActivity.this,"Ban Chua Nhap Du Thong Tin", Toast.LENGTH_LONG).show();
                       return; // nếu không đúng thì ngưng các câu lệnh phía sau.
                   }

                   // Chuyển dữ liệu thành số
                    _SoMin = Integer.parseInt(tSoMin);
                    _SoMax = Integer.parseInt(tSoMax);

                   if (_SoMin>_SoMax)
                   {
                       _SoMax = _SoMin+1;
                   }
                   mEdtSoMin.setText(String.valueOf(_SoMin));
                   mEdtSoMax.setText(String.valueOf(_SoMax));
                   arrNum.clear();
                   for (int i = _SoMin; i <=_SoMax ; i++)
                   {
                        arrNum.add(i);
                   }
                   disableView(mbtnAddRang);
                   disableView(mEdtSoMax);
                   disableView(mEdtSoMin);
                   enableView(mBtnRandom);
               }
           }
        );
        // thực hiện nút reset
        // mbtnRandom dissable
        // mtbnAddrange Enable
        mBtnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTvResult.setText("");
                mTvResultRevert.setText("");
                disableView(mBtnRandom);
                enableView(mbtnAddRang);
                enableView(mEdtSoMax);
                enableView(mEdtSoMin);
                arrNum.clear();
                arrNumOut.clear();
                mKetQua ="";
            }
        });

        mBtnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if (arrNum.size()>0)
                {
                //    mTvResult.setText(arrNum.toString());
                    Random random = new Random();
                    mIndex = random.nextInt(arrNum.size());
                    Result = arrNum.get(mIndex);
                    // Sử dụng mảng hiện tại còn những số nào chưa được lấy ra và Số lấy ra chạy từng bước
                  //  mKetQua += arrNum.toString()+":"+ String.valueOf(Result) + "\n";
                    // Sử dụng nguyên thủy theo từng giá trị trong mảng và có dấu - từng số.
                   // mKetQua += arrNum.size()!=1? Result + "-":Result;
                    mKetQua = ((arrNum.size()!=1)? "-" + Result: Result)+ mKetQua;
                    // || arrNum.size()!=_SoMax-_SoMin
                    // sử dụng phương thức so sánh nếu chỉ còn 1 phần tử thì bỏ đi dấu - ở cuối
                    //arrNum.toString()+":"+ String.valueOf(Result) + "\n";
                    arrNumOut.add(0,Result);

                    arrNum.remove(mIndex);
                    mTvResult.setText(mKetQua);
                   // mTvResult.setText(arrNumOut.toString());
                  //  Collections.reverse(arrNumOut);
                    mTvResultRevert.setText(arrNumOut.toString());
                }
                else
                    {
                    Toast.makeText(MainActivity.this,"Hết Giá trị Random",Toast.LENGTH_SHORT).show();
                }

            }
        });

        mBtnFindNumber.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
               String _tFN =  mEdtFindNumber.getText().toString();
                if (_tFN.equals(""))
                {
                    Toast.makeText(MainActivity.this,"Vui lòng nhập số cần tìm ",Toast.LENGTH_SHORT).show();
                    return;
                }

                if (arrNumOut.contains(Integer.parseInt(_tFN)))
                {
                    //Toast.makeText(MainActivity.this,"Có giá Key",Toast.LENGTH_SHORT).show();
                    int _postion = arrNumOut.indexOf(Integer.parseInt(_tFN));

                    mTvResultFindNumber.setText(String.valueOf(_postion));
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Not value",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    //
    private void disableView(View v)
    {
        v.setEnabled(false);
    }
    private void enableView(View v)
    {
        v.setEnabled(true);
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