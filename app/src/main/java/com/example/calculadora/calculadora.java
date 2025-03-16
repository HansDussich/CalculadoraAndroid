package com.example.calculadora;


import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class calculadora {

    //Referencia a la actividad de android
    private Activity activity;

    //TextView para mostrar el resultado
    private TextView txtRespuesta;

    //Variables para almacenar el numero actual, la operacion y el primer numero
    private String numeroActual = "";
    private String operacion = "";
    private double primerNumero = 0;
    private boolean operacionPresionada = false;

    //Configura la actividad y el layout
    public void initialize(Activity activity, Bundle savedInstanceState) {
        this.activity = activity;
        //inicializa los componentes de la interfaz
        activity.setContentView(R.layout.cal);

        txtRespuesta = activity.findViewById(R.id.txtRespuesta);
        txtRespuesta.setText("0");

        // Inicializar botones numéricos
        numeros(R.id.btnCero, "0");
        numeros(R.id.btnUno, "1");
        numeros(R.id.btnDos, "2");
        numeros(R.id.btnTres, "3");
        numeros(R.id.btnCuatro, "4");
        numeros(R.id.btnCinco, "5");
        numeros(R.id.btnSeis, "6");
        numeros(R.id.btnSiete, "7");
        numeros(R.id.btnOcho, "8");
        numeros(R.id.btnNueve, "9");

        // Inicializar botón decimal
        decimal();

        // Inicializar botones de operaciones
        operacion(R.id.btnSuma, "+");
        operacion(R.id.btnResta, "-");
        operacion(R.id.btnPor, "*");
        operacion(R.id.btnDividir, "/");

        // Inicializar botón igual
        igual();

        // Inicializar botón limpiar
        limpiar();
    }

    //Metodo para los numeros
    private void numeros(int id, final String numero) {
        Button button = activity.findViewById(id);
        button.setOnClickListener(v -> {
            //Si ya se presiono una operacion, se limpia el numero actual
            if (operacionPresionada) {
                numeroActual = "";
                operacionPresionada = false;
            }
            //Si el numero actual es 0, se reemplaza por el numero actual
            if (numeroActual.equals("0")) {
                numeroActual = numero;
            } else {
                numeroActual += numero;
            }

            txtRespuesta.setText(numeroActual);
        });
    }

    private void decimal() {
        Button btnDecimal = activity.findViewById(R.id.btnDecimal);
        btnDecimal.setOnClickListener(v -> {
            if (!numeroActual.contains(".")) {
                numeroActual += ".";
                txtRespuesta.setText(numeroActual);
            }
        });
    }

    //Metodo para las operaciones
    private void operacion(int id, final String operador) {
        //
        Button button = activity.findViewById(id);
        button.setOnClickListener(v -> {
            if (!numeroActual.isEmpty()) {
                primerNumero = Double.parseDouble(numeroActual);
                operacion = operador;
                operacionPresionada = true;
            }
        });
    }

    //metodo para el boton igual
    private void igual() {
        Button btnIgual = activity.findViewById(R.id.btnIgual);
        btnIgual.setOnClickListener(v -> {
            if (!numeroActual.isEmpty() && !operacion.isEmpty()) {
                double segundoNumero = Double.parseDouble(numeroActual);
                double resultado = calcularResultado(primerNumero, segundoNumero, operacion);

                if (Double.isInfinite(resultado) || Double.isNaN(resultado)) {
                    txtRespuesta.setText("Error");
                    // Reinicia el estado como al limpiar
                    numeroActual = "";
                    operacion = "";
                    primerNumero = 0;
                    operacionPresionada = false;
                } else {
                    // Muestra el resultado normalmente
                    if (resultado == (int) resultado) {
                        txtRespuesta.setText(String.valueOf((int) resultado));
                        numeroActual = String.valueOf((int) resultado);
                    } else {
                        txtRespuesta.setText(String.valueOf(resultado));
                        numeroActual = String.valueOf(resultado);
                    }
                    operacion = "";
                }
            }
        });
    }

    //Metodo para calcular el resultado
    private double calcularResultado(double num1, double num2, String op) {
        switch (op) {

            case "+": return num1 + num2;
            case "-": return num1 - num2;
            case "*": return num1 * num2;
            //Si el numero 2 es 0 = no se puede dividir entre 0 y se muestra 0
            case "/": return num1 / num2;
            default: return 0;
        }
    }

    //Metodo para limpiar
    private void limpiar() {
        Button btnLimpiar = activity.findViewById(R.id.btnLimpiar);
        btnLimpiar.setOnClickListener(v -> {
            numeroActual = "";
            operacion = "";
            primerNumero = 0;
            operacionPresionada = false;
            txtRespuesta.setText("0");
        });
    }
}
