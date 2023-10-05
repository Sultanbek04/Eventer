package com.example.eventer;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eventer.entity.Event;
import com.example.eventer.implementation.EventImplementation;
import com.example.eventer.implementation.SectionImplementation;
import com.google.android.material.textfield.TextInputEditText;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostEventActivity extends AppCompatActivity {

    private String eventDate;
    private String eventTime;

    private Uri selectedImageUri;

    private ImageView ivEvent;
    MultipartBody.Part body;
    private String eventName;
    private String eventDescription;
    private ImageView imageDate;
    private ImageView imageTime;
    private TextView tvDate;
    private TextView tvTime;
    private TextInputEditText etEventName;
    private TextInputEditText etDescription;
    private int sectionId = 1;
    private SectionImplementation sectionImplementation = new SectionImplementation();
    private EventImplementation eventImplementation = new EventImplementation();
    private AutoCompleteTextView autoCompleteTextView;

    private Button btnPost;
    private int cityId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_event);

        imageDate = findViewById(R.id.imageDate);
        imageTime = findViewById(R.id.imageTime);
        Intent intent = getIntent();
        cityId = intent.getIntExtra("cityId", -1);
        tvDate = findViewById(R.id.tvDate);
        tvTime = findViewById(R.id.tvTime);
        btnPost = findViewById(R.id.btnPost);
        etEventName = findViewById(R.id.etEventName);
        etDescription = findViewById(R.id.etDescription);

        ivEvent = findViewById(R.id.ivEvent);
        ivEvent.setOnClickListener(this::imageChooser);
        btnPost.setOnClickListener(this::sendToCheck);
        imageTime.setOnClickListener(this::eventTime);
        imageDate.setOnClickListener(this::eventDate);

        autoCompleteTextView = findViewById(R.id.sectionItems);

        autoCompleteTextView.setOnItemClickListener((adapterView, view, i, l) -> {
            sectionId = i + 1;
        });

        sectionImplementation.getAllSections(autoCompleteTextView, this);


    }

    private void sendToCheck(View view) {
        eventName = etEventName.getText().toString();
        eventDescription = etDescription.getText().toString();
        String path = RealPathUtil.getRealPath(this, selectedImageUri);
        File file = new File(path);
        RequestBody requestBody =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);
        String resultFileName = UUID.randomUUID().toString() + "." + file.getName();
        body =
                MultipartBody.Part.createFormData("file", resultFileName, requestBody);


        NetworkService
                .getInstance()
                .getEventApi()
                .uploadImage(body)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Toast.makeText(PostEventActivity.this, response.body(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(PostEventActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        Event event = new Event(eventName, eventDescription, sectionId, MainActivity.sharedPreferences.getInt("userId", -1),
                cityId, eventDate, eventTime, false, resultFileName, 0);
        eventImplementation.saveEvent(event, this);

    }

    private void imageChooser(View view) {
        int accessStorage = ContextCompat.checkSelfPermission(
                getApplicationContext(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (accessStorage == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            launchImageActivity.launch(intent);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }

    ActivityResultLauncher<Intent> launchImageActivity = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    selectedImageUri = data.getData();
                    ivEvent.setImageURI(selectedImageUri);
                }
            });

    private void sendToServer() {
        String path = RealPathUtil.getRealPath(this, selectedImageUri);
        File file = new File(path);
        RequestBody requestBody =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);

        body =
                MultipartBody.Part.createFormData("file", file.getName(), requestBody);

        NetworkService
                .getInstance()
                .getEventApi()
                .uploadImage(body)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Toast.makeText(PostEventActivity.this, response.body(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(PostEventActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }


//    public void eventIsChecked(int eventId) {
//
//        Event event = new Event(eventId, eventName, eventDescription, sectionId, MainActivity.sharedPreferences.getInt("userId", -1),
//                cityId, eventDate, eventTime, true);
//        eventImplementation.updateEvent(event, this);
//    }

    private void eventTime(View view) {
        Calendar calendar = Calendar.getInstance();
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                (timePicker, hours, minutes) -> {
                    calendar.set(Calendar.HOUR_OF_DAY, hours);
                    calendar.set(Calendar.MINUTE, minutes);
                    Date d = calendar.getTime();
                    eventTime = new SimpleDateFormat("HH:mm").format(d);
                    tvTime.setText(eventTime);

                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true
        );
        timePickerDialog.show();
    }

    private void eventDate(View view) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (datePicker, year, month, dayOfMonth) -> {
                    Date d = new GregorianCalendar(year, month, dayOfMonth).getTime();
                    eventDate = new SimpleDateFormat("dd/MM/yyyy").format(d);
                    tvDate.setText(eventDate);
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }
}