package com.app.xeross.infoxer.Controller.ClassActivity;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.app.xeross.infoxer.Controller.MainActivity;
import com.app.xeross.infoxer.Model.ClassementAdapter;
import com.app.xeross.infoxer.R;
import com.app.xeross.infoxer.View.ItemClassement;

import java.util.ArrayList;
import java.util.List;

public class ClassMusic extends AppCompatActivity {

    public ClassementAdapter adapter;
    RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView recyclerView;
    private Button mButton;
    private List<ItemClassement> listclassment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_music);

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
                Intent i = new Intent(ClassMusic.this, MainActivity.class);
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

        ItemClassement a = new ItemClassement("1", covers[0], "Hunter x Hunter", "Terminé", color[0]);
        listclassment.add(a);

        a = new ItemClassement("2", covers[1], "My Hero academia", "En attente", color[1]);
        listclassment.add(a);

        a = new ItemClassement("3", covers[2], "One piece", "En attente", color[2]);
        listclassment.add(a);

        a = new ItemClassement("4", covers[3], "Naturo", "En cours", color[3]);
        listclassment.add(a);

        a = new ItemClassement("5", covers[4], "L’attaque des titans", "En attente", color[3]);
        listclassment.add(a);

        a = new ItemClassement("6", covers[5], "Fairy tails", "En cours", color[3]);
        listclassment.add(a);

        a = new ItemClassement("7", covers[6], "Death Note", "En cours", color[3]);
        listclassment.add(a);

        a = new ItemClassement("8", covers[7], "One punch man", "En cours", color[3]);
        listclassment.add(a);

        adapter.notifyDataSetChanged();
    }
}
