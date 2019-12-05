package com.example.shan.menudemo;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText mEditText, mEditText2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText = findViewById(R.id.editTextEmail);
        mEditText2 = findViewById(R.id.editTextConfirmEmail);


        registerForContextMenu( findViewById(R.id.textViewUserName) );
        registerForContextMenu( findViewById(R.id.textViewPassword) );

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.itemCopy:
                copyToClipboard();
                return true;
            case R.id.itemPaste:
                pasteFromClipboard();
                return true;
            case R.id.itemAbout:
                        Toast.makeText(this, "This is demo App",
                                Toast.LENGTH_LONG).show();
                        return true;

            case R.id.itemNew:
                Toast.makeText(this, "New file selected",
                        Toast.LENGTH_LONG).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void copyToClipboard(){
        ClipboardManager clipboard =
                (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("email",
                mEditText.getText().toString());
        clipboard.setPrimaryClip(clip);
    }

    public void pasteFromClipboard(){
        ClipboardManager clipboard =
                (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData.Item item = clipboard.getPrimaryClip().getItemAt(0);
        if (item.getText() != null) {
            mEditText2.setText(item.getText());
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        switch (v.getId()){
            case R.id.textViewUserName:
                menuInflater.inflate(R.menu.main_context_menu, menu);
                return;
            case R.id.textViewPassword:
                menuInflater.inflate(R.menu.password_context_menu, menu);
                return;
            default:
                return;
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.copy:
                Toast.makeText(this, "Copy context item selected",
                        Toast.LENGTH_LONG).show();
                return true;
            case R.id.remember:
                Toast.makeText(this, "Remember context item selected",
                        Toast.LENGTH_LONG).show();
                return true;
            case R.id.forget:
                Toast.makeText(this, "Forget context item selected",
                        Toast.LENGTH_LONG).show();
                return true;

            default:
                return super.onContextItemSelected(item);

        }


    }
}
