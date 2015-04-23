package kz.lookastana.lookastanaproject.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import kz.lookastana.lookastanaproject.R;
import kz.lookastana.lookastanaproject.object.Organization;

public class MainActivity extends Activity {

    EditText nameTxt, phoneTxt, emailTxt, addressTxt;

    List<Organization> organizations = new ArrayList<Organization>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        nameTxt = (EditText) findViewById(R.id.txtOrgName);
        phoneTxt = (EditText) findViewById(R.id.txtOrgPhone);
        emailTxt = (EditText) findViewById(R.id.txtOrgEmail);
        addressTxt = (EditText) findViewById(R.id.txtOrgAddress);
        TabHost tabHost = (TabHost) findViewById(R.id.tabHost);

        tabHost.setup();

        TabHost.TabSpec tabSpec = tabHost.newTabSpec("creator");
        tabSpec.setContent(R.id.tabOrgCreator);
        tabSpec.setIndicator("Добавление");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("list");
        tabSpec.setContent(R.id.tabOrgList);
        tabSpec.setIndicator("Список");
        tabHost.addTab(tabSpec);

        final Button addBtn = (Button) findViewById(R.id.btnAdd);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameTxt.getText().toString();
                String phone = phoneTxt.getText().toString();
                String email = emailTxt.getText().toString();
                String address = addressTxt.getText().toString();
                addOrganization(name, phone, email, address);
                Toast.makeText(getApplicationContext(), name + " успешно добавлена в Список организаций!", Toast.LENGTH_SHORT).show();
            }
        });


        nameTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                addBtn.setEnabled(!nameTxt.getText().toString().trim().isEmpty());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void addOrganization(String orgName, String orgPhone, String orgEmail, String orgAddress){
        organizations.add(new Organization(orgName, orgPhone, orgEmail, orgAddress));
    }

    private class OrganizationListAdapter extends ArrayAdapter<Organization> {
        public OrganizationListAdapter() {
            super(MainActivity.this, R.layout.listview_item, organizations);
        }

        @Override
        public View getView(int position, View view, ViewGroup parent){

            if(view == null) {
                view = getLayoutInflater().inflate(R.layout.listview_item, parent, false);
            }

            Organization currentOrganization = organizations.get(position);

            TextView name = (TextView) view.findViewById(R.id.orgName);
            name.setText(currentOrganization.getOrgName());
            TextView phone = (TextView) view.findViewById(R.id.orgPhone);
            phone.setText(currentOrganization.getOrgPhone());
            TextView email = (TextView) view.findViewById(R.id.orgEmail);
            email.setText(currentOrganization.getOrgEmail());
            TextView address = (TextView) view.findViewById(R.id.orgAddress);
            address.setText(currentOrganization.getOrgAddress());

            return view;

        }

    }

}