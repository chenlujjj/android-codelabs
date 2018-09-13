package com.example.android.barcodedetect;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

public class MainActivity extends AppCompatActivity {

    // TODO: 1. 从相册读取图片
    // 2. 拍照再detect
    // 3. 拍照预览就detect


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ImageView imageView = findViewById(R.id.imgview);
        // 这个是干啥的？
        Bitmap bitmap = BitmapFactory.decodeResource(
                getApplicationContext().getResources(), R.drawable.puppy
        );
        imageView.setImageBitmap(bitmap);

        TextView textView = findViewById(R.id.txtContent);

        // setup barcode detector
        BarcodeDetector detector = new BarcodeDetector.Builder(getApplicationContext())
                .setBarcodeFormats(Barcode.DATA_MATRIX | Barcode.QR_CODE)
                .build();

        if (!detector.isOperational()) {
            textView.setText("Could not set up the detector!");
        }

        // detect barcode
        Frame frame = new Frame.Builder().setBitmap(bitmap).build();
        SparseArray<Barcode> barcodes = detector.detect(frame);

        // decode barcode
        Barcode theCode = barcodes.valueAt(0);
        textView.setText(theCode.rawValue);

    }
}
