package com.xuanvu.simplecontact;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class EditContact extends AppCompatActivity {

    Contact contact;
    EditText name, mobile, email;
    ImageButton btnEdit, btnClose;
    MyDatabase myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.edit_contact );
        name = findViewById( R.id.edName );
        mobile = findViewById( R.id.edMobile );
        email = findViewById( R.id.edEmail );
        btnEdit = findViewById( R.id.btnEdit );
        btnClose = findViewById( R.id.btnClose );
        myDatabase = new MyDatabase( this );


        Intent intent = getIntent();
        contact = (Contact) intent.getSerializableExtra( "contact" );
        name.setText( contact.getmFullname() );
        mobile.setText( contact.getmMobile() );
        email.setText( contact.getmEmail() );


        btnEdit.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                contact.setmFullname( name.getText().toString() );
                contact.setmMobile( mobile.getText().toString() );
                contact.setmEmail( email.getText().toString() );
                if (myDatabase.updateContact( contact )) {
                    Intent intentEdit = new Intent( EditContact.this, MainActivity.class );
                    setResult( MainActivity.RESULT_CODE_EDIT, intentEdit );
                    Toast.makeText( EditContact.this, "EDIT SUCCESS", Toast.LENGTH_SHORT ).show();
                    finish();
                } else {
                    Toast.makeText( EditContact.this, "EDIT FAILE", Toast.LENGTH_SHORT ).show();

                }

            }
        } );

        btnClose.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentClose = new Intent( EditContact.this, MainActivity.class );
                setResult( MainActivity.REQUEST_CODE, intentClose );
                Toast.makeText( EditContact.this, "CANCEL", Toast.LENGTH_SHORT ).show();
                finish();

            }
        } );

    }

}
