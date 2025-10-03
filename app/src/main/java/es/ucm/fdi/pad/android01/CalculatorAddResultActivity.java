package es.ucm.fdi.pad.android01;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;
import android.util.Log;

public class CalculatorAddResultActivity extends AppCompatActivity {

    private static final String TAG = CalculatorAddResultActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Log.v(TAG, "Vista resultado cargada correctamente");
        TextView txresult = findViewById(R.id.textView);
        Button btnBack = findViewById(R.id.button2);
        Intent intent = getIntent();
        double res = intent.getDoubleExtra("Resultado", 0);
        String formatted = String.format(Locale.getDefault(), "%.4f", res);
        double parsed = Double.parseDouble(formatted);
        txresult.setText(String.valueOf(parsed).replaceFirst("\\.0$", ""));
        Log.v(TAG, "Volver a la actividad anterior");
        btnBack.setOnClickListener(view -> finish());

    }
}