package com.rooztr.rooztr_android;
import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.HashMap;

import com.rooztr.model.Contact;

public class MainActivity extends Activity {


    Cursor cur;
    ArrayList<String> names = new ArrayList<>();
    public static HashMap<String,Contact> contactMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getContacts();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void getContacts() {

        ContentResolver cr = getContentResolver();
        cur = cr.query(ContactsContract.Contacts.CONTENT_URI,null,null,null,null);
        if(cur.getCount() > 0) {
            while(cur.moveToNext()) {
                String name  = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                if(name != null) {
                    Contact contact = new Contact();
                    String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                    contact.setName(name);
                    contact.setId(id);
                    if(Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {

                        Cursor pcur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                null,
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID +"= ?",
                                new String[] {id},null
                        );
                        while (pcur.moveToNext()) {
                            String phoneNo = pcur.getString(pcur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                            contact.getNumbers().add(phoneNo);
                        }
                        pcur.close();
                    }
                    contactMap.put(id, contact);
                }
            }
        }
        cur.close();
    }
}
