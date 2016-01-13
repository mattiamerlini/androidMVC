package merlini.Main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import merlini.Main.Base.Console;
import merlini.Main.ButtonPanel.ButtonPanel;
import merlini.androidmvc_github.R;

public class MainActivity extends AppCompatActivity
{
    // Istanza del ButtonPanel (View e Controller).
    private ButtonPanel buttonPanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Collego l'istanza con l'elemento dichiarato nell'XML.
        this.buttonPanel = (ButtonPanel) findViewById(R.id.view);

        // Faccio partire il debug ogni 3 secondi
        this.buttonPanel.debug();

        // Aggiungo due bottoni che premuti mandano a console del testo.
        this.buttonPanel.addButton("One", new View.OnClickListener() { // Primo.
            @Override
            public void onClick(View v)
            {
                Button b = (Button) v;
                Console.log("E' stato premuto: " + b.getText());
            }
        });
        this.buttonPanel.addButton("Two", new View.OnClickListener() { // Secondo.
            @Override
            public void onClick(View v)
            {
                Button b = (Button) v;
                Console.log("E' stato premuto: " + b.getText());
            }
        });

        // Aggiungo un bottone che se premuto ne aggiunge altri.
        this.buttonPanel.addButton("Three", new View.OnClickListener() { // Terzo.
            @Override
            public void onClick(View v)
            {
                buttonPanel.addButton("Generato", new View.OnClickListener() { // Generato.
                    @Override
                    public void onClick(View v)
                    {
                        Button b = (Button) v;
                        Console.log("E' stato premuto: " + b.getText() + " con id: " + b.getId());
                    }
                });

                Button b = (Button) v;
                Console.log("E' stato premuto: " + b.getText());
            }
        });


    }
}
