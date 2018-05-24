package es.jujoru.examentestapp;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.view.ViewPager;
import android.os.Bundle;

import Clases.Examen;
import es.jujoru.examentestapp.Adapters.AdapterPage;
import es.jujoru.examentestapp.Fragments.FragmentGestionExamen;

public class ActivityGestionExamen extends AppCompatActivity {


    private AdapterPage adapterPage;
    private ViewPager mViewPager;
    Toolbar toolbar;
    Examen examen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_examen);
        declararViews();

        Bundle b= getIntent().getExtras();
        if(b!=null){
            examen = b.getParcelable(FragmentGestionExamen.EXTRA_EXAMEN);
        }


        adapterPage = new AdapterPage(this,getSupportFragmentManager(),examen);
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(adapterPage);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));




    }
    private void declararViews(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }



}
