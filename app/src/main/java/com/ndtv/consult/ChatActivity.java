package com.ndtv.consult;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ndtv.consult.adapter.ChatListAdapter;
import com.ndtv.consult.dao.ChatData;
import com.ndtv.consult.threads.LoaderConversation;

import java.util.List;

public class ChatActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<ChatData>>{

    private FloatingActionButton sendChat;
    private EditText chatBody;
    private ChatListAdapter chatListAdapter;
    private ListView chatView;
    private TextView name, status;
    private Bundle queryData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        chatListAdapter = new ChatListAdapter(this);

        chatView = (ListView) findViewById(R.id.chat_timeline);
        sendChat = (FloatingActionButton) findViewById(R.id.chat_enter);
        chatBody = (EditText) findViewById(R.id.chat_text_box);
        name = (TextView) findViewById(R.id.chat_name);
        status = (TextView) findViewById(R.id.chat_last_seen);

        chatView.setAdapter(chatListAdapter);

        if(getIntent().getStringExtra("name") != null) {
            name.setText(getIntent().getStringExtra("name"));
            //pre populate if name is present
            queryData = new Bundle();
            queryData.putString("query", "");
            getSupportLoaderManager().initLoader(2, queryData, this).forceLoad();
        }

        sendChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChatData chatData = new ChatData(v.getContext());
                chatData.setChatText(chatBody.getText().toString());
                chatData.setDate(System.currentTimeMillis());
                chatData.setIsSameUser(true);
                chatListAdapter.addData(chatData);
                chatBody.getEditableText().clear();

                ChatData systemReply = new ChatData(v.getContext());
                systemReply.setChatText("Automated Reply");
                systemReply.setDate(System.currentTimeMillis());
                systemReply.setIsSameUser(false);
                chatListAdapter.addData(systemReply);
            }
        });

        findViewById(R.id.chat_user_container)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ChatActivity.this, UserProfileActivity.class);
                        if (getIntent().getStringExtra("name") != null) {
                            intent.putExtra("name", getIntent().getStringExtra("name"));
                        } else {
                            intent.putExtra("name", "Arindam Nath");
                        }
                        startActivity(intent);
                    }
                });

        Toast.makeText(this, "Click on the user name on the action bar to view details", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<List<ChatData>> onCreateLoader(int id, Bundle args) {
        return new LoaderConversation(this, args);
    }

    @Override
    public void onLoadFinished(Loader<List<ChatData>> loader, List<ChatData> data) {
        if(data != null) {
            chatListAdapter.setData(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<ChatData>> loader) {

    }
}
