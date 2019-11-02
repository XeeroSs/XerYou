package com.app.xeross.infoxer.Controller;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;

import com.app.xeross.infoxer.Model.CategorieAdapter;
import com.app.xeross.infoxer.R;
import com.app.xeross.infoxer.View.Categorie;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "PREFS_NAME";
    public CategorieAdapter adapter;
    private RecyclerView recyclerView;
    private List<Categorie> albumList;
    private Switch mSwitch;
    private RelativeLayout mRelativeLayout;
    private Controle c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        c = new Controle();

        recyclerView = findViewById(R.id.recycler_view);
        mRelativeLayout = findViewById(R.id.background);

        albumList = new ArrayList<>();
        adapter = new CategorieAdapter(this, albumList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareAlbums();
        mSwitch = findViewById(R.id.switch1);

        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    mRelativeLayout.setBackgroundResource(R.color.backgroung_black);
                } else {
                    mRelativeLayout.setBackgroundResource(R.color.cardview_light_background);
                }

                SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putBoolean("switchkey", isChecked);
                editor.apply();

            }
        });

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        boolean silent = settings.getBoolean("switchkey", false);
        mSwitch.setChecked(silent);

    }

    private void prepareAlbums() {
        int[] covers = new int[]{
                R.drawable.music,
                R.drawable.serie,
                R.drawable.film,
                R.drawable.animerandom2,
                R.drawable.jeu,
                R.drawable.bouffe,};

        Categorie a = new Categorie("Musique", 0, covers[0]);
        albumList.add(a);

        a = new Categorie("Série", 0, covers[1]);
        albumList.add(a);

        a = new Categorie("Film", 0, covers[2]);
        albumList.add(a);

        a = new Categorie("Anime/Manga", 0, covers[3]);
        albumList.add(a);

        a = new Categorie("Jeux vidéo", 0, covers[4]);
        albumList.add(a);

        a = new Categorie("Nourriture", 0, covers[5]);
        albumList.add(a);

        adapter.notifyDataSetChanged();
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }
}
