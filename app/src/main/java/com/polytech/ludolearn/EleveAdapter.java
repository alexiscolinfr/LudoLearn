package com.polytech.ludolearn;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.polytech.ludolearn.database.Profil;

import java.util.List;

public class EleveAdapter extends ArrayAdapter<Profil> {

    public EleveAdapter(Context context, List<Profil> details){
        super(context,0,details);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_liste_eleve,parent, false);
        }

        DetailViewHolder viewHolder = (DetailViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new DetailViewHolder();
            viewHolder.detailPrenom = convertView.findViewById(R.id.textViewPrenom);
            viewHolder.detailNom = convertView.findViewById(R.id.textViewNom);
            convertView.setTag(viewHolder);
        }

        Profil profil = getItem(position);

        assert profil != null;
        viewHolder.detailPrenom.setText(profil.getPrenom());
        viewHolder.detailNom.setText(profil.getNom());

        return convertView;
    }

    @Override
    public Profil getItem(int position) {
        return super.getItem(position);
    }

    private static class DetailViewHolder{
        public TextView detailPrenom;
        public TextView detailNom;
    }
}
