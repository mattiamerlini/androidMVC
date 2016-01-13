package merlini.Main.ButtonPanel;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

import merlini.Main.Base.Console;


/**
 * Created by mattia on 26/11/15.
 */
public class ButtonPanel extends LinearLayout implements Observer
{
    private TextView textView;

    private ButtonModel buttonModel; // Istanza del ButtonModel.

    public ButtonPanel(Context context) {
        super(context);
        init();
    }

    public ButtonPanel(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init()
    {
        this.buttonModel = new ButtonModel(); // Inizializzo il ButtonModel.
        this.buttonModel.addObserver(this); // Registro questa view come Observer del Model.
    }

    /**
     * Funzione che viene richiamata ogni volta che nel Model viene eseguito fireValuesChange(...).
     * @param observable Observable Implicito
     * @param data Object Implicito
     */
    @Override
    public void update(Observable observable, Object data)
    {
        // Aggiorno il modello con quello restituito dal Model.
        this.buttonModel = (ButtonModel) observable;

        // Rimuovo tutti i pulsanti presenti.
        this.removeAllViewsInLayout();
        this.removeAllViews();

        // Riaggiungo i pulsanti comprendendo quelli nuovi.
        this.addButtonsToPanel();

        // Forzo il repaint del layout
        this.invalidate();
    }

    /**
     * Metodo che riaggiunge tutti i bottoni presenti nel Model al layout.
     */
    private void addButtonsToPanel()
    {
        for(Button b : this.buttonModel.getButtons())
        {
            //Rimuovo e riaggiungo per evitare problemi del tipo 'already has a parent'.
            this.removeView(b);
            this.addView(b);
        }
    }

    /**
     * Metodo che ogni 3 secondi manda a video il numero di bottoni presenti
     */
    public void debug()
    {
        TimerTask task = new TimerTask() {
            @Override
            public void run()
            {
                String out = "{\n";
                for(Button b : buttonModel.getButtons())
                {
                    out += String.format("[Button: %s --- ID: %d]\n", b.getText(), b.getId());
                }
                out += "\n}";
                Console.log("\nNumero di Button aggiunti: " + getChildCount() + "\n" + out);
            }
        };
        Timer t = new Timer();
        t.scheduleAtFixedRate(task, 0, 3000);
    }

    /**
     * Funzione wrapper che aggiunge bottoni al Model (e di conseguenza alla View).
     * @param label String L'etichetta del bottone
     * @param listener OnClickListener Un'azione da eseguire quando il pulsante viene cliccato
     */
    public void addButton(String label, OnClickListener listener)
    {
        this.buttonModel.addButton(this.getContext(), label, listener);
    }
}
