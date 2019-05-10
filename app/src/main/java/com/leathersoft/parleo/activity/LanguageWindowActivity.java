package com.leathersoft.parleo.activity;


import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.leathersoft.parleo.R;
import com.leathersoft.parleo.adapter.LanguageAdapter;
import com.leathersoft.parleo.messaging.LanguageModel;


import java.io.Serializable;
import java.util.List;



public class LanguageWindowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.window_list);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);


        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        LanguageAdapter adapter = new LanguageAdapter();

        Intent i = getIntent();

        EditText editText = findViewById(R.id.te_search);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        List<LanguageModel> listLanguages = (List<LanguageModel>) i.getSerializableExtra("listLanguages");
        adapter.setInterests(listLanguages);
        recyclerView.setAdapter(adapter);
        Button b = findViewById(R.id.btn_done);
        b.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.putExtra("listLanguages", (Serializable) listLanguages);
            setResult(RESULT_OK, intent);
            finish();
        });
    }


}
