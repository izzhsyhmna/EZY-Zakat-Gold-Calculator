package com.example.zakatgoldcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText etWeight, etValue;
    Spinner spinnerType;

    Button btnCalculate, btnReset;

    ImageButton btnAbout, btnShare;

    TextView tvTotalValue, tvZakatPayable, tvTotalZakat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // INPUTS
        etWeight = findViewById(R.id.etWeight);
        etValue = findViewById(R.id.etValue);
        spinnerType = findViewById(R.id.spinnerType);

        // BUTTONS
        btnCalculate = findViewById(R.id.btnCalculate);
        btnReset = findViewById(R.id.btnReset);

        // HEADER BUTTONS
        btnAbout = findViewById(R.id.btnAbout);
        btnShare = findViewById(R.id.btnShare);

        // RESULTS
        tvTotalValue = findViewById(R.id.tvTotalValue);
        tvZakatPayable = findViewById(R.id.tvZakatPayable);
        tvTotalZakat = findViewById(R.id.tvTotalZakat);

        // SPINNER DATA
        String[] goldType = {"Keep", "Wear"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                goldType
        );

        spinnerType.setAdapter(adapter);

        // ABOUT BUTTON

        btnAbout.setOnClickListener(v -> {
            try {
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(MainActivity.this,
                        "Unable to open About page",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // SHARE BUTTON

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent shareIntent =
                        new Intent(Intent.ACTION_SEND);

                shareIntent.setType("text/plain");

                shareIntent.putExtra(
                        Intent.EXTRA_TEXT,
                        "Try my Gold Zakat Calculator App!\n\n" +
                                "https://github.com/yourusername/zakatgoldcalculator"
                );

                startActivity(
                        Intent.createChooser(
                                shareIntent,
                                "Share App"
                        )
                );
            }
        });

        // CALCULATE BUTTON

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (etWeight.getText().toString().trim().isEmpty()) {

                    etWeight.setError("Enter gold weight");
                    etWeight.requestFocus();
                    return;
                }

                if (etValue.getText().toString().trim().isEmpty()) {

                    etValue.setError("Enter gold value per gram");
                    etValue.requestFocus();
                    return;
                }

                double weight =
                        Double.parseDouble(
                                etWeight.getText().toString()
                        );

                double value =
                        Double.parseDouble(
                                etValue.getText().toString()
                        );

                String type =
                        spinnerType.getSelectedItem().toString();

                double uruf;

                if (type.equals("Keep")) {
                    uruf = 85;
                } else {
                    uruf = 200;
                }

                double totalValue = weight * value;

                double zakatWeight = weight - uruf;

                if (zakatWeight < 0) {
                    zakatWeight = 0;
                }

                double zakatPayable =
                        zakatWeight * value;

                double totalZakat =
                        zakatPayable * 0.025;

                tvTotalValue.setText(
                        String.format(
                                "Total Gold Value: RM %.2f",
                                totalValue
                        )
                );

                tvZakatPayable.setText(
                        String.format(
                                "Zakat Payable: RM %.2f",
                                zakatPayable
                        )
                );

                tvTotalZakat.setText(
                        String.format(
                                "Total Zakat: RM %.2f",
                                totalZakat
                        )
                );
            }
        });

        // RESET BUTTON

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                etWeight.setText("");
                etValue.setText("");

                spinnerType.setSelection(0);

                tvTotalValue.setText(
                        "Total Gold Value: RM 0.00"
                );

                tvZakatPayable.setText(
                        "Zakat Payable: RM 0.00"
                );

                tvTotalZakat.setText(
                        "Total Zakat: RM 0.00"
                );
            }
        });
    }
}