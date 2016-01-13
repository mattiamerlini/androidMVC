package merlini.Main.ButtonPanel;

import android.content.Context;
import android.view.View.OnClickListener;
import android.widget.Button;

import java.util.ArrayList;

import merlini.Main.Base.Console;
import merlini.Main.Base.Model;


/**
 * Created by mattia on 26/11/15.
 */
public class ButtonModel extends Model
{
    private ArrayList<Button> buttons;
    private int ids = 0;

    /**
     * Costruttore
     */
    public ButtonModel()
    {
        this.buttons = new ArrayList<>();
    }

    /**
     * Aggiunge un bottone al modello e avverte gli observers registrati che c'è stato un cambiamento.
     * @param context Context Il contesto
     * @param label String L'etichetta del bottone
     * @param listener OnClickListener Un'azione da eseguire quando il pulsante viene cliccato
     */
    public void addButton(Context context, String label, OnClickListener listener)
    {
        Button b = new Button(context);
        b.setText(label);
        b.setOnClickListener(listener);
        b.setId(this.ids ++);

        try
        {
            this.buttons.add(b);

            // Avverto gli Observers di un cambiamento.
            this.fireValuesChange(String.format("Aggiunta bottone [%s]", b.toString()));
        }
        catch (Exception e)
        {
            Console.log(e);
        }
    }

    public ArrayList<Button> getButtons()
    {
        return this.buttons;
    }
}
