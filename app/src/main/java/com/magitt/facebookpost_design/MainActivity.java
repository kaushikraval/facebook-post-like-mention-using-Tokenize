package com.magitt.facebookpost_design;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String[] list={"kaushik","mehul","mohit","harsh","jeet","komal","mayank","ketul","harshil","hardik"};

    private MultiAutoCompleteTextView multiAutoCompleteTextView;
    private Button btnpost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        multiAutoCompleteTextView = findViewById(R.id.MultilineTextView);
        btnpost  = findViewById(R.id.btnPost);


        Tokenizzer();
        //autoComplete();

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_dropdown_item_1line,list);
        multiAutoCompleteTextView.setThreshold(1);
        multiAutoCompleteTextView.setAdapter(adapter);

        btnpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String srt = multiAutoCompleteTextView.getText().toString();
                Intent intent = new Intent(MainActivity.this,DisplayData.class);
                intent.putExtra("kaushik",srt);
                startActivity(intent);
            }
        });
    }

    private void Tokenizzer() {
        multiAutoCompleteTextView.setTokenizer(new MultiAutoCompleteTextView.Tokenizer()
        {

            @Override
            public CharSequence terminateToken(CharSequence text) {
//                int i = text.length();
//
//                while (i > 0 && text.charAt(i - 1) == ' ') {
//                    i--;
//                }
//
//                if (i > 0 && text.charAt(i - 1) == ' ') {
//                    return text;
//                } else {
//                    if (text instanceof Spanned) {
//                        SpannableString sp = new SpannableString(text + " ");
//                        TextUtils.copySpansFrom((Spanned) text, 0, text.length(), Object.class, sp, 0);
//                        return sp;
//                    } else {
//                        return text + " ";
//                    }
//                }
                return text;
            }

            @Override
            public int findTokenStart(CharSequence text, int cursor) {
                int i = cursor;

                while (i > 0 && text.charAt(i - 1) != '@' && text.charAt(i-1) != '#') {
                    i--;
                }

                //Check if token really started with @, else we don't have a valid token
                if (i < 1 || text.charAt(i - 1) != '@' && text.charAt(i-1) != '#') {
                    return cursor;
                }

                return i;
            }

            @Override
            public int findTokenEnd(CharSequence text, int cursor) {
                int i = cursor;
                int len = text.length();

                while (i < len) {
                    if (text.charAt(i) == ' ') {
                        return i;
                    } else {
                        i++;
                    }
                }

                return len;
            }
        });
    }

//    private void autoComplete() {
//
////        autoCompleteTextView.addTextChangedListener(new TextWatcher() {
////            @Override
////            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
////
////            }
////
////            @Override
////            public void onTextChanged(CharSequence s, int start, int before, int count) {
////
////            }
////
////            @Override
////            public void afterTextChanged(Editable s) {
////                if(s.length() > 0 && s.toString().charAt(s.length()-1) == '@' && s.toString().charAt(s.length()-1) == '#' )
////                {
////                    final String newText = s.toString().substring(0, s.length()-1) + "";
////                    autoCompleteTextView.setText(newText);
////                }
////            }
////        });
//    }

}