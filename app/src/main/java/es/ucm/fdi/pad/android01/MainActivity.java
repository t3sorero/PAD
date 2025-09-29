package es.ucm.fdi.pad.android01;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText sumando1;
    private EditText sumando2;

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
        sumando1 = findViewById(R.id.editTextNumberDecimal3);
        sumando2 = findViewById(R.id.editTextNumberDecimal4);
        sumar.setOnClickListener(View -> executeOperation());

    }

    private void executeOperation() {
        double res = 0;
        String s1 = sumando1.getText().toString().trim();
        String s2 = sumando2.getText().toString().trim();
        if (!s1.isEmpty() && !s2.isEmpty()) {
            try {
                res = CalculatorClass.addNumbers(Double.parseDouble(s1), Double.parseDouble(s2));
                Intent intent = new Intent(MainActivity.this, CalculatorAddResultActivity.class);
                intent.putExtra("Resultado", res);
                startActivity(intent);
            } catch (NumberFormatException e) {
                Toast.makeText(this, R.string.invalid_number, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, R.string.fill_fields, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        sumando1.setText("");
        sumando2.setText("");
        sumando1.requestFocus();
    }

}