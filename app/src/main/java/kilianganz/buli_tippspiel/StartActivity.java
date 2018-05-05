package kilianganz.buli_tippspiel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;


public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        ArrayList testListe = new ArrayList <String> ();
        testListe.add("Test");



        Log.d("Testausgabe", "funktioniert");
        calculateResult calc = new calculateResult();


        Log.d("Test: ", "" + calc.calculatePoints(testListe));


        //Testausgabe loggen


    }
}
