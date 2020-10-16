package com.polytech.ludolearn;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.polytech.ludolearn.database.Resultat;

import java.util.List;

public class ResultatAdapter extends ArrayAdapter<Resultat> {

    public ResultatAdapter(Context context, List<Resultat> details){
        super(context,0,details);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_resultat,parent, false);
        }

        DetailViewHolder viewHolder = (DetailViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new DetailViewHolder();
            viewHolder.detailAdresseMail = (TextView) convertView.findViewById(R.id.textViewAdresseMail);
            viewHolder.detailJeu = (TextView) convertView.findViewById(R.id.textViewJeu);
            viewHolder.detailCategorie = (TextView) convertView.findViewById(R.id.textViewCategorie);
            viewHolder.detailNote = (TextView) convertView.findViewById(R.id.textViewNote);
            convertView.setTag(viewHolder);
        }

        Resultat resultat = getItem(position);

        viewHolder.detailAdresseMail.setText(resultat.getAdresseMail());
        viewHolder.detailJeu.setText(String.valueOf(resultat.getJeu()));
        viewHolder.detailCategorie.setText(resultat.getCategorie());
        viewHolder.detailNote.setText(String.valueOf(resultat.getNote()));

        return convertView;
    }

    private class DetailViewHolder{
        public TextView detailAdresseMail;
        public TextView detailJeu;
        public TextView detailCategorie;
        public TextView detailNote;
    }

}
