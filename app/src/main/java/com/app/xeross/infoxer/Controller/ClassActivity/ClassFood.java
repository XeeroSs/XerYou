package com.app.xeross.infoxer.Controller.ClassActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.app.xeross.infoxer.Controller.MainActivity;
import com.app.xeross.infoxer.Model.ClassementAdapter;
import com.app.xeross.infoxer.R;
import com.app.xeross.infoxer.View.ItemClassement;

import java.util.ArrayList;
import java.util.List;

public class ClassFood extends AppCompatActivity {

    public ClassementAdapter adapter;
    RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView recyclerView;
    private Button mButton;
    private List<ItemClassement> listclassment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_food);

        recyclerView = findViewById(R.id.recycler_view);
        mButton = findViewById(R.id.button);

        listclassment = new ArrayList<>();

        mLayoutManager = new LinearLayoutManager(this);
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(mLayoutManager);
        adapter = new ClassementAdapter(this, listclassment);
        recyclerView.setAdapter(adapter);

        prepareAlbums();

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ClassFood.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

    private void prepareAlbums() {
        int[] covers = new int[]{
                R.drawable.hunterxhunter,
                R.drawable.myheroacademia,
                R.drawable.onepiece,
                R.drawable.naruto,
                R.drawable.attackontitan,
                R.drawable.fairytails,
                R.drawable.deathnote,
                R.drawable.onepunchman,};

        int[] color = new int[]{
                R.color.gold,
                R.color.silver,
                R.color.copper,
                R.color.blanc_gris};

        ItemClassement a = new ItemClassement("1", covers[0], "Hershey's", "", color[0]);
        listclassment.add(a);

        a = new ItemClassement("2", covers[1], "Twix", "", color[1]);
        listclassment.add(a);

        a = new ItemClassement("3", covers[2], "Kit kat", "", color[2]);
        listclassment.add(a);

        a = new ItemClassement("4", covers[3], "", "", color[3]);
        listclassment.add(a);

        a = new ItemClassement("5", covers[4], "", "", color[3]);
        listclassment.add(a);

        a = new ItemClassement("6", covers[5], "Justin bridou", "", color[3]);
        listclassment.add(a);

        a = new ItemClassement("7", covers[6], "", "", color[3]);
        listclassment.add(a);

        a = new ItemClassement("8", covers[7], "", "", color[3]);
        listclassment.add(a);

        adapter.notifyDataSetChanged();
    }
}
