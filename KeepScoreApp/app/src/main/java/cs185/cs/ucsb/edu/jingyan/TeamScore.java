package cs185.cs.ucsb.edu.jingyan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;


public class TeamScore extends Fragment {

    AutoCompleteTextView actv;
    TextView texv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_team_score, container, false);

        // set an AutoCompleteTextView for Team 1
        actv = (AutoCompleteTextView) view.findViewById(R.id.autoCompleteTextView);
        String[] teams = getResources().getStringArray(R.array.list_of_teams);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (getActivity(),android.R.layout.simple_list_item_1,teams);
        actv.setAdapter(adapter);
        texv = (TextView) view.findViewById(R.id.editText);

        return view;
    }

    public void setHint(String auto_hint,String hint){
        actv.setHint(auto_hint);
        texv.setHint(hint);
    }

    public void clearText(){
        actv.setText("");
        texv.setText("");
    }

}
