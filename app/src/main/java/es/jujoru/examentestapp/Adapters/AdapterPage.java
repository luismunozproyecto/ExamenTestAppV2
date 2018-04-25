package es.jujoru.examentestapp.Adapters;

import android.content.Context;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import es.jujoru.examentestapp.Fragments.FragmentConfiguracionExamen;
import es.jujoru.examentestapp.Fragments.FragmentInformacionExamen;
import es.jujoru.examentestapp.Fragments.FragmentTestExamen;


public class AdapterPage extends FragmentPagerAdapter {


    Context context;

    public AdapterPage(Context context, FragmentManager fm) {
        super(fm);
        this.context=context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;

        switch (position){
            case 0: fragment = new FragmentInformacionExamen(); break;
            case 1: fragment =  new FragmentConfiguracionExamen(); break;
            case 2: fragment =  new FragmentTestExamen(); break;
            default: fragment = null; break;
        }


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
