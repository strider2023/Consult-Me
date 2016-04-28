package com.ndtv.consult.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.ndtv.consult.ChatActivity;
import com.ndtv.consult.R;
import com.ndtv.consult.adapter.ConsultListAdapter;
import com.ndtv.consult.dao.ConsultListData;
import com.ndtv.consult.threads.LoaderConsultations;
import com.ndtv.consult.util.AppPreferences;

import java.util.List;

/**
 * Created by arindamnath on 26/04/16.
 */
public class ConsultFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<ConsultListData>> {

    private View mViewHolder;
    private ListView detailsList;
    private FloatingActionMenu menuButton;
    private FloatingActionButton newChat, sosChat;
    private ConsultListAdapter consultListAdapter;
    private Bundle queryData;
    private AppPreferences appPreferences;

    public static ConsultFragment newInstance() {
        ConsultFragment fragment = new ConsultFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        consultListAdapter = new ConsultListAdapter(getActivity());
        appPreferences = new AppPreferences(getContext());

        mViewHolder = inflater.inflate(R.layout.fragment_consult, container, false);
        detailsList = (ListView) mViewHolder.findViewById(R.id.consult_list);
        menuButton = (FloatingActionMenu) mViewHolder.findViewById(R.id.consult_menu);
        newChat = (FloatingActionButton) mViewHolder.findViewById(R.id.menu_item_new);
        sosChat = (FloatingActionButton) mViewHolder.findViewById(R.id.menu_item_sos);
        detailsList.setAdapter(consultListAdapter);

        detailsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ConsultListData consultListData = consultListAdapter.getItem(position);
                Intent intent = new Intent(getActivity(), ChatActivity.class);
                intent.putExtra("name", consultListData.getName());
                intent.putExtra("lastChat", consultListData.getLastChatText());
                startActivity(intent);
            }
        });

        newChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuButton.close(true);
                startActivity(new Intent(getActivity(), ChatActivity.class));
            }
        });

        sosChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuButton.close(true);
                Snackbar.make(v, "SOS sent. Doctor will reach you shortly.", Snackbar.LENGTH_LONG).show();
            }
        });

        queryData = new Bundle();
        queryData.putString("query", "");
        getActivity().getSupportLoaderManager().initLoader(1, queryData, this).forceLoad();

        if(appPreferences.isDoctorLoggedIn()) {
            menuButton.setVisibility(View.GONE);
        }

        return mViewHolder;
    }

    @Override
    public Loader<List<ConsultListData>> onCreateLoader(int id, Bundle args) {
        return new LoaderConsultations(getActivity(), args);
    }

    @Override
    public void onLoadFinished(Loader<List<ConsultListData>> loader, List<ConsultListData> data) {
        if(data != null) {
            consultListAdapter.setData(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<ConsultListData>> loader) {

    }
}
