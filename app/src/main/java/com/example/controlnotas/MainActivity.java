package com.example.controlnotas;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    // Porcentajes de cada nota
    private static final double PORC_NOTA1 = 0.20;
    private static final double PORC_NOTA2 = 0.30;
    private static final double PORC_NOTA3 = 0.15;
    private static final double PORC_NOTA4 = 0.35;

    // Nota mínima para aprobar
    private static final double NOTA_APROBATORIA = 3.0;

    // Objetos de la interfaz
    private EditText etNombre, etNota1, etNota2, etNota3, etNota4;
    private RadioButton rbPantalla;
    private CheckBox cbLimpiar;
    private TextView tvResultado, tvResumen;

    // Contadores del grupo
    private int totalEstudiantes = 0;
    private int estudiantesPerdieron = 0;

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

        // Enlazar los objetos del XML con Java
        etNombre = findViewById(R.id.etNombre);
        etNota1 = findViewById(R.id.etNota1);
        etNota2 = findViewById(R.id.etNota2);
        etNota3 = findViewById(R.id.etNota3);
        etNota4 = findViewById(R.id.etNota4);
        rbPantalla = findViewById(R.id.rbPantalla);
        cbLimpiar = findViewById(R.id.cbLimpiar);
        tvResultado = findViewById(R.id.tvResultado);
        tvResumen = findViewById(R.id.tvResumen);

        Button btnCalcular = findViewById(R.id.btnCalcular);
        Button btnFinalizar = findViewById(R.id.btnFinalizar);
        Button btnReiniciar = findViewById(R.id.btnReiniciar);

        btnCalcular.setOnClickListener(v -> calcularNota());
        btnFinalizar.setOnClickListener(v -> finalizarGrupo());
        btnReiniciar.setOnClickListener(v -> reiniciar());
    }

    // Calcula la nota definitiva de un estudiante
    private void calcularNota() {
        String nombre = etNombre.getText().toString().trim();
        if (nombre.isEmpty()) {
            etNombre.setError("Ingrese el nombre");
            return;
        }

        // Leer y validar cada nota (entre 1 y 5)
        Double n1 = leerNota(etNota1);
        Double n2 = leerNota(etNota2);
        Double n3 = leerNota(etNota3);
        Double n4 = leerNota(etNota4);
        if (n1 == null || n2 == null || n3 == null || n4 == null) {
            return;
        }

        // Multiplicar cada nota por su porcentaje y acumular
        double definitiva = 0;
        definitiva += n1 * PORC_NOTA1;
        definitiva += n2 * PORC_NOTA2;
        definitiva += n3 * PORC_NOTA3;
        definitiva += n4 * PORC_NOTA4;

        totalEstudiantes++;
        String estado;
        if (definitiva < NOTA_APROBATORIA) {
            estudiantesPerdieron++;
            estado = "PERDIÓ";
        } else {
            estado = "APROBÓ";
        }

        String mensaje = String.format(Locale.getDefault(),
                "%s: nota definitiva %.2f — %s", nombre, definitiva, estado);

        // Mostrar según el RadioButton seleccionado
        if (rbPantalla.isChecked()) {
            tvResultado.setText(mensaje);
        } else {
            Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
        }

        actualizarResumen();

        // Limpiar campos si el CheckBox está marcado
        if (cbLimpiar.isChecked()) {
            limpiarCampos();
        }
    }

    // Devuelve la nota si es válida, o null si hay error
    private Double leerNota(EditText campo) {
        String texto = campo.getText().toString().trim().replace(",", ".");
        if (texto.isEmpty()) {
            campo.setError("Ingrese la nota");
            return null;
        }
        double nota;
        try {
            nota = Double.parseDouble(texto);
        } catch (NumberFormatException e) {
            campo.setError("Valor no válido");
            return null;
        }
        if (nota < 1 || nota > 5) {
            campo.setError("La nota debe estar entre 1 y 5");
            return null;
        }
        return nota;
    }

    // Muestra cuántos estudiantes perdieron la materia
    private void finalizarGrupo() {
        if (totalEstudiantes == 0) {
            Toast.makeText(this, "No se ha registrado ningún estudiante", Toast.LENGTH_SHORT).show();
            return;
        }
        new AlertDialog.Builder(this)
                .setTitle("Resultado del grupo")
                .setMessage("Estudiantes registrados: " + totalEstudiantes
                        + "\nAprobaron: " + (totalEstudiantes - estudiantesPerdieron)
                        + "\nPerdieron la materia: " + estudiantesPerdieron)
                .setPositiveButton("Aceptar", null)
                .show();
    }

    // Reinicia los contadores para un nuevo grupo
    private void reiniciar() {
        totalEstudiantes = 0;
        estudiantesPerdieron = 0;
        tvResultado.setText("");
        limpiarCampos();
        actualizarResumen();
    }

    private void actualizarResumen() {
        tvResumen.setText("Estudiantes: " + totalEstudiantes + " | Perdieron: " + estudiantesPerdieron);
    }

    private void limpiarCampos() {
        etNombre.setText("");
        etNota1.setText("");
        etNota2.setText("");
        etNota3.setText("");
        etNota4.setText("");
        etNombre.requestFocus();
    }
}