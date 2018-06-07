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

public class MainActivity extends AppCompatActivity {
public class MainActivity extends AppCompatActivity implements INGram{

    Button firstButton;
    Button secondButton;
    Button thirdButton;
    EditText editText;

    private void updateButtonsWith(String text1, String text2, String text3) {
        firstButton.setText(text1);
        secondButton.setText(text2);
        thirdButton.setText(text3);
    }

    private void sendToTool(String word, String word2) {
        GramUtil gu = new GramUtil(new ArrayList<NGram>() {{
            add(new NGram());
            add(new NGram());
        }});
        ArrayList<String> nexts = gu.getNextWords(word, word2);

        firstButton.setText(nexts.get(0));
        secondButton.setText(nexts.get(1));
        thirdButton.setText(nexts.get(2));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.editText = (EditText) findViewById(R.id.editText);
        this.firstButton = (Button) findViewById(R.id.button);
        this.secondButton = (Button) findViewById(R.id.button2);
        this.thirdButton = (Button) findViewById(R.id.button3);

        this.firstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TAG", "Button 1");
                Button b = (Button) view;
                editText.append(b.getText() + " ");
            }
        });

        this.secondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TAG", "Button 2");
                Button b = (Button) view;
                editText.append(b.getText() + " ");
            }
        });

        this.thirdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TAG", "Button 3");
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
                                sendToTool(lw, lw2);
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



    @Override
    protected void onPostResume() {
        super.onPostResume();
        RetrofitHelper.getNGram("Paris", this);
    }

    @Override
    public void onRetrofitResult(boolean okay) {
        if(okay){
            Log.e("MAIN", "ok");
        }else{
            Log.e("MAIN", "ko");
        }
    }
}
