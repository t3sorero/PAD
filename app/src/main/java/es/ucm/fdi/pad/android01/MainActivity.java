package es.ucm.fdi.pad.android01;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button sumar = findViewById(R.id.button);
        sumar.setOnClickListener(View ->{
            EditText sumando1 = findViewById(R.id.editTextNumberDecimal3);
            EditText sumando2 = findViewById(R.id.editTextNumberDecimal4);
            float res;
            if (!sumando1.getText().toString().isEmpty() && !sumando2.getText().toString().isEmpty())
                res = Float.parseFloat(sumando1.getText().toString()) + Float.parseFloat(sumando2.getText().toString());
        });
    }
}