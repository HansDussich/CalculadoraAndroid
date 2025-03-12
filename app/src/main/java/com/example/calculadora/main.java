package com.example.calculadora;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class main extends AppCompatActivity {
    private calculadora calculadoraActivity = new calculadora();

    //Metodo para crear la actividad
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Se llama al metodo onCreate de la clase padre
        super.onCreate(savedInstanceState);
        //Inicializa la actividad de calculadora
        calculadoraActivity.initialize(this, savedInstanceState);
    }
}