package com.example.esgi.jmdoudoux_android;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements INGram{

    Button firstButton;
    Button secondButton;
    Button thirdButton;
    EditText editText;

    ArrayList<NGram> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RetrofitHelper.getNGram(this);

       //WebService ws = new WebService();

       //ws.getNGRAm();
       //Log.e("MAIN", "ngram ok");

        this.editText = (EditText) findViewById(R.id.editText);
        this.firstButton = (Button) findViewById(R.id.button);
        this.secondButton = (Button) findViewById(R.id.button2);
        this.thirdButton = (Button) findViewById(R.id.button3);

        this.firstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button) view;
                editText.append(b.getText() + " ");
            }
        });

        this.secondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button) view;
                editText.append(b.getText() + " ");
            }
        });

        this.thirdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button) view;
                editText.append(b.getText() + " ");
            }
        });

        this.editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() - 1 >= 0 && charSequence.charAt(charSequence.length() - 1) == ' ') {
                    if (charSequence.length() - 2 >= 0 && charSequence.charAt(charSequence.length() - 2) == '.') {
                        updateButtonsWith("Je", "Tu", "Salut");
                    } else {
                        String[] splittedArrayBySpace = charSequence.toString().split("\\s+");
                        int l = splittedArrayBySpace.length;

                        if (l == 1) {
                            String lw = splittedArrayBySpace[l - 1];
                            sendToTool(lw, null);
                        } else if (l > 1) {
                            String lw = splittedArrayBySpace[l - 1];
                            String lw2 = splittedArrayBySpace[l - 2];

                            if (lw2.charAt(lw2.length() - 1) == '.') {
                                sendToTool(lw, null);
                            } else {
                                sendToTool(lw, null);
                            }
                        }
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        Log.e("Main", "begining");


    }

    private void updateButtonsWith(String text1, String text2, String text3) {
        firstButton.setText(text1);
        secondButton.setText(text2);
        thirdButton.setText(text3);
    }

    private void sendToTool(String word, String word2) {
        GramUtil gu = new GramUtil(list);
        ArrayList<String> nexts = gu.getNextWords(word, word2);

        if(nexts.get(0) != null)
            firstButton.setText(nexts.get(0));
        else
            firstButton.setText("");

        if(nexts.get(1) != null)
            secondButton.setText(nexts.get(1));
        else
            secondButton.setText("");

        if(nexts.get(2) != null)
            thirdButton.setText(nexts.get(2));
        else
            thirdButton.setText("");

    }



    @Override
    protected void onPostResume() {
        super.onPostResume();

    }

    @Override
    public void onRetrofitResult(boolean okay, ArrayList<NGram> lista) {
        list = lista;

        if(okay){

            Log.e("MAIN", "ok");
        }else{
            Log.e("MAIN", "ko");
        }
    }
}
