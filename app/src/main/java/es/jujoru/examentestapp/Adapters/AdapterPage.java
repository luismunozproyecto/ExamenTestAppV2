package es.jujoru.examentestapp.Adapters;

import android.content.Context;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import Clases.Examen;
import es.jujoru.examentestapp.Fragments.FragmentConfiguracionExamen;
import es.jujoru.examentestapp.Fragments.FragmentInformacionExamen;
import es.jujoru.examentestapp.Fragments.FragmentTestExamen;


public class AdapterPage extends FragmentPagerAdapter {

    private final String EXTRA_EXAMEN = "EXAMEN";

    Context context;
    Examen examen;

    public AdapterPage(Context context, FragmentManager fm, Examen examen) {
        super(fm);
        this.examen = examen;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        Bundle args;
        switch (position) {
            case 0:
                fragment = new FragmentInformacionExamen();

                break;
            case 1:
                fragment = new FragmentConfiguracionExamen();

                break;
            case 2:
                fragment = new FragmentTestExamen();
                break;
            default:
                fragment = null;
                break;
        }
        args = new Bundle();
        args.putParcelable(EXTRA_EXAMEN, examen);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {
            case 0:
                return "Informacion";
            case 1:
                return "Configuracion";
            case 2:
                return "Test";
            default:
                return super.getPageTitle(position);
        }

    }
}
