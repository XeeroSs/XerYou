package com.app.xeross.infoxer.Controller.ClassActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.app.xeross.infoxer.Controller.MainActivity;
import com.app.xeross.infoxer.Model.ClassementAdapter;
import com.app.xeross.infoxer.R;
import com.app.xeross.infoxer.View.ItemClassement;

import java.util.ArrayList;
import java.util.List;

public class ClassAnime extends AppCompatActivity {

    public ClassementAdapter adapter;
    RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView recyclerView;
    private Button mButton, mButton2;
    private List<ItemClassement> listclassment;
    private EditText cla, titre, statut, image, color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_anime);

        mButton2 = findViewById(R.id.button2);

        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ClassAnime.this);
                View mView = getLayoutInflater().inflate(R.layout.row_layout, null);
                builder.setView(mView).setTitle("Ajouter").setNegativeButton("ANNULER", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).setPositiveButton("VALIDER", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        //ItemClassement a = new ItemClassement(cla.getText().toString(), titre, statut.getText().toString(), image.getText().toString(), color.getText().toString());
                        //listclassment.add(a);
                    }
                });
                cla = mView.findViewById(R.id.classement);
                titre = mView.findViewById(R.id.titles);
                statut = mView.findViewById(R.id.statut);
                image = mView.findViewById(R.id.imagee);
                color = mView.findViewById(R.id.color);
                final AlertDialog dialogs = builder.create();
                dialogs.show();
            }
        });

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
                Intent i = new Intent(ClassAnime.this, MainActivity.class);
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
