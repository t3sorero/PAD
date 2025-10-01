package es.ucm.fdi.pad.android01;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

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

        Log.v(TAG, "Aplicacion iniciada");
        Log.i(TAG, "MainActivity cargada correctamente");

        Button sumar = findViewById(R.id.button);
        sumando1 = findViewById(R.id.editTextNumberDecimal3);
        sumando2 = findViewById(R.id.editTextNumberDecimal4);
        sumando1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) clearFieldError(sumando1);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        sumando2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) clearFieldError(sumando2);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        sumar.setOnClickListener(View -> executeOperation());

    }

    private void executeOperation() {
        double res = 0;
        String s1 = sumando1.getText().toString().trim();
        String s2 = sumando2.getText().toString().trim();
        boolean ok = true;
        if (s1.isEmpty()) {
            showFieldError(sumando1, R.string.fill_field);
            Log.w(TAG, "Rellena el primer campo");
            ok = false;
        }
        if (s2.isEmpty()) {
            showFieldError(sumando2, R.string.fill_field);
            Log.w(TAG, "Rellena el segundo campo");
            ok = false;
        }
        if (ok) {
            try {
                res = CalculatorClass.addNumbers(Double.parseDouble(s1), Double.parseDouble(s2));
                Log.i(TAG, "Numeros parseados correctamente");
                Log.v(TAG, "Suma realizada correctamente");
                Intent intent = new Intent(MainActivity.this, CalculatorAddResultActivity.class);
                intent.putExtra("Resultado", res);
                Log.v(TAG, "Pasamos a la siguiente actividad");
                startActivity(intent);
            } catch (NumberFormatException e) {
                Log.e(TAG, "Error en el parseo del numero");
            }
        }
    }

    private void showFieldError(EditText field, @StringRes int msg) {
        field.setError(getString(msg));
        field.setHintTextColor(ContextCompat.getColor(this, R.color.error));
        field.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.error)));
        field.requestFocus();
    }

    private void clearFieldError(EditText field) {
        field.setError(null);
        field.setHintTextColor(ContextCompat.getColor(this, R.color.neutral_gray));
        field.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.gray_400)));
    }


    @Override
    protected void onResume() {
        super.onResume();
        sumando1.setText("");
        sumando2.setText("");
        sumando1.requestFocus();
    }

}