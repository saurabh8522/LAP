package com.example.navigationactivity.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.example.navigationactivity.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.basgeekball.awesomevalidation.ValidationStyle.BASIC;

public class RegistrationActivity extends AppCompatActivity{

    EditText userPassword, userEmail,userName,userPhone,userAddr,userAge;
    private Button regButton;
    private ProgressDialog progressDialog;
    private TextView userLogin;
    private FirebaseAuth firebaseAuth;
    RadioGroup radioGroup;
    RadioButton radioButton;
    //private ImageView userProfilePic;
    String email, name, age, password,phone,gen,addr;
    AwesomeValidation awesomeValidation;

    //private FirebaseStorage firebaseStorage;
    // private static int PICK_IMAGE = 123;
    // Uri imagePath;
    //private StorageReference storageReference;

    //    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if(requestCode == PICK_IMAGE && resultCode == RESULT_OK && data.getData() != null){
//            imagePath = data.getData();
//            try {
//                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imagePath);
//                userProfilePic.setImageBitmap(bitmap);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//    }
    private void setupUIViews(){
        userName = (EditText)findViewById(R.id.etUserName);
        userPassword = (EditText)findViewById(R.id.etUserPassword);
        userEmail = (EditText)findViewById(R.id.etUserEmail);
        regButton = (Button)findViewById(R.id.btnRegister);
        userLogin = (TextView)findViewById(R.id.tvUserLogin);
        userAge = (EditText)findViewById(R.id.etAge);
        userPhone= (EditText) findViewById(R.id.etPhone);
        userAddr=(EditText) findViewById(R.id.etaddress);
        //UName = (EditText) findViewById(R.id.etUName);
        radioGroup= (RadioGroup) findViewById(R.id.radioGroup);
        radioButton = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
        phone = userPhone.getText().toString();
        addr = userAddr.getText().toString();
        gen = radioButton.getText().toString();
        age=userAge.getText().toString();
        email=userEmail.getText().toString();
        name=userName.getText().toString();
        String regexPassword = "(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])(?=.*[~`!@#\\$%\\^&\\*\\(\\)\\-_\\+=\\{\\}\\[\\]\\|\\;:\"<>,./\\?]).{8,}";
        String TELEPHONE = "((\\+*)((0[ -]+)*|(91 )*)(\\d{12}+|\\d{10}+))|\\d{5}([- ]*)\\d{6}";
        awesomeValidation.addValidation(RegistrationActivity.this,R.id.etUserName,"[a-zA-Z\\s]+", R.string.error_field_required);
        awesomeValidation.addValidation(RegistrationActivity.this,R.id.etUserEmail,android.util.Patterns.EMAIL_ADDRESS, R.string.error_invalid_email);
        awesomeValidation.addValidation(RegistrationActivity.this,R.id.etPhone,TELEPHONE, R.string.phonerr);
        awesomeValidation.addValidation(RegistrationActivity.this,R.id.etUserPassword,regexPassword, R.string.passerr);
        awesomeValidation.addValidation(RegistrationActivity.this,R.id.confpass,R.id.etUserPassword,R.string.confpasserr);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        awesomeValidation = new AwesomeValidation(BASIC);
        setupUIViews();
        FirebaseApp.initializeApp(this);
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Creating...");
//        firebaseStorage = FirebaseStorage.getInstance();
//
//        storageReference = firebaseStorage.getReference();
//
//        userProfilePic.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.setType("images/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE);
//            }
//        });



        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(awesomeValidation.validate()){
                    //Upload data to the database
                    progressDialog.show();
                    String user_email = userEmail.getText().toString().trim();
                    //Toast.makeText(getApplicationContext(),user_email,Toast.LENGTH_SHORT).show();
                    String user_password = userPassword.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                progressDialog.dismiss();
                                //  progressDialog = new ProgressDialog(getApplicationContext());
                                //  progressDialog.setMessage("wait ...");
                                //  progressDialog.show();
                                //sendEmailVerification();
                                sendUserData();
                                //firebaseAuth.signOut();
                                Toast.makeText(getApplicationContext(), "Successfully Registered, Upload complete!", Toast.LENGTH_SHORT).show();
                                // finish();
                                startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
                            }else{
                                Toast.makeText(getApplicationContext(), "Registration Failed", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }
        });

        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
            }
        });

    }



//    private Boolean validate(){
//        Boolean result = false;
//
//        name = userName.getText().toString();
//        password = userPassword.getText().toString();
//        email = userEmail.getText().toString();
//        age = userAge.getText().toString();
//
//
//        if(name.isEmpty() || password.isEmpty() || email.isEmpty() || age.isEmpty() ){
//            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show();
//        }else{
//            result = true;
//        }
//
//        return result;
//    }


//    private void sendEmailVerification(){
//        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
//        if(firebaseUser!=null){
//            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
//                @Override
//                public void onComplete(@NonNull Task<Void> task) {
//                   if(task.isSuccessful()){
//                       //sendUserData();
//                       Toast.makeText(RegistrationActivity.this, "Successfully Registered, Verification mail sent!", Toast.LENGTH_SHORT).show();
//                       firebaseAuth.signOut();
//                       finish();
//                       startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
//                   }else{
//                       Toast.makeText(RegistrationActivity.this, "Verification mail has'nt been sent!", Toast.LENGTH_SHORT).show();
//                   }
//                }
//            });
//        }
//    }

    private void sendUserData(){
        //Toast.makeText(getApplicationContext(),"I am in user data",Toast.LENGTH_LONG).show();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference("profiles");

       /* StorageReference imageReference = storageReference.child("/abc/ce0XKHe0VfRk7S7cPJoz").child("Images").child("Profile Pic");  //User id/Images/Profile Pic.jpg
        UploadTask uploadTask = imageReference.putFile(imagePath);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RegistrationActivity.this, "Upload failed!", Toast.LENGTH_SHORT).show();
            }
        }).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                Toast.makeText(RegistrationActivity.this, "Upload successful!", Toast.LENGTH_SHORT).show();
            }
        });
        */

        //userID = UName.getText().toString();

        age = userAge.getText().toString().trim();
        email= userEmail.getText().toString().trim();
        name = userName.getText().toString().trim();
        addr = userAddr.getText().toString().trim();
        gen = radioButton.getText().toString();
        phone = userPhone.getText().toString().trim();
        //rate="".trim();
        UserProfile userProfile = new UserProfile(phone, email, name,addr,gen,age,"","0");
        //Toast.makeText(getApplicationContext(),"age: "+age + "email: "+ email, Toast.LENGTH_LONG).show();
        //Log.d("Age:",age);
        //Log.d("Email:",email);
        myRef.child(firebaseAuth.getUid()).setValue(userProfile);

        // progressDialog.dismiss();
    }
}
