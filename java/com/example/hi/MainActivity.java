package com.example.hi;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Map<String, String[]> countryStatesMap;

    private TextView birthDateText;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private TextInputEditText birthTimeText;
    private Button submitButton;
    private TextInputEditText passwordText;
    private Spinner stateSpinner, genderSpinner;
    private AutoCompleteTextView countryText;
    private TextInputEditText usernameText, emailText, phoneNumberText, interestText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countryText = findViewById(R.id.countryText);
        stateSpinner = findViewById(R.id.stateSpinner);
        genderSpinner = findViewById(R.id.genderSpinner);
        usernameText = findViewById(R.id.usernameText);
        emailText = findViewById(R.id.emailText);
        phoneNumberText = findViewById(R.id.phoneNumberText);
        interestText = findViewById(R.id.interestText);
        submitButton = findViewById(R.id.submitButton);
        birthTimeText = findViewById(R.id.birthTimeText);
        birthDateText = findViewById(R.id.birthDateText);

        TextInputLayout passwordTextLayout = findViewById(R.id.passwordTextEditLayout);
        passwordText = passwordTextLayout != null ? (TextInputEditText) passwordTextLayout.getEditText() : null;

        birthTimeText.setOnClickListener(v -> showTimePicker());
        birthDateText.setOnClickListener(v -> showDatePickerDialog());

        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());

        String[] genderItems = new String[]{"", "Male", "Female"};
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, genderItems);
        genderAdapter.setDropDownViewResource(R.layout.spinner_item);
        genderSpinner.setAdapter(genderAdapter);

        initializeCountryStatesMap();

        String[] southeastAsianCountries = new String[]{
                "Brunei", "Cambodia", "East Timor", "Indonesia", "Laos",
                "Malaysia", "Myanmar", "Philippines", "Singapore", "Thailand", "Vietnam"
        };

        ArrayAdapter<String> countryAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                southeastAsianCountries
        );
        countryText.setAdapter(countryAdapter);

        countryText.setOnItemClickListener((parent, view, position, id) -> {
            String selectedCountry = (String) parent.getItemAtPosition(position);
            updateStateSpinner(stateSpinner, selectedCountry);
        });

        submitButton.setOnClickListener(v -> showConfirmationDialog());
    }

    private void initializeCountryStatesMap() {
        countryStatesMap = new HashMap<>();

        countryStatesMap.put("Brunei", new String[]{"", "Belait", "Brunei-Muara", "Temburong", "Tutong"});
        countryStatesMap.put("Cambodia", new String[]{"", "Phnom Penh", "Siem Reap", "Battambang", "Kampot", "Kampong Cham", "Kampong Speu", "Kampong Thom", "Kampong Chhnang", "Kandal", "Koh Kong", "Mondulkiri", "Oddar Meanchey", "Preah Vihear", "Prey Veng", "Pursat", "Ratanakiri", "Sihanoukville", "Svay Rieng", "Takeo"});
        countryStatesMap.put("East Timor", new String[]{"", "Aileu", "Ainaro", "Baucau", "Bobonaro", "Covalima", "Dili", "Ermera", "Lautem", "Liquica", "Oecusse", "Viqueque"});
        countryStatesMap.put("Indonesia", new String[]{"", "Aceh", "Bali", "Banten", "Bengkulu", "Central Java", "Central Kalimantan", "Central Sulawesi", "East Java", "East Kalimantan", "East Nusa Tenggara", "Gorontalo", "Jakarta", "Jambi", "Lampung", "Maluku", "North Kalimantan", "North Maluku", "North Sulawesi", "Papua", "Riau", "Riau Islands", "South Kalimantan", "South Sulawesi", "South Sumatra", "West Java", "West Kalimantan", "West Nusa Tenggara", "West Papua", "West Sulawesi", "West Sumatra", "Yogyakarta"});
        countryStatesMap.put("Laos", new String[]{"", "Attapeu", "Bokeo", "Bolikhamsai", "Champasak", "Houaphanh", "Khammouane", "Luang Namtha", "Luang Prabang", "Oudomxay", "Phongsaly", "Salavan", "Savannakhet", "Vientiane", "Vientiane Province", "Xaignabouli", "Xayaburi", "Xekong", "Xieng Khouang"});
        countryStatesMap.put("Malaysia", new String[]{"", "Johor", "Kedah", "Kelantan", "Malacca", "Negeri Sembilan", "Pahang", "Penang", "Perak", "Perlis", "Sabah", "Sarawak", "Selangor", "Kuala Lumpur", "Putrajaya", "Labuan"});
        countryStatesMap.put("Myanmar", new String[]{"", "Ayeyarwady", "Bago", "Chin", "Kachin", "Kayin", "Kayah", "Magway", "Mandalay", "Mon", "Naypyidaw", "Rakhine", "Sagaing", "Shan", "Tanintharyi", "Yangon"});
        countryStatesMap.put("Philippines", new String[]{"", "Abra", "Agusan del Norte", "Agusan del Sur", "Aklan", "Albay", "Antique", "Apayao", "Aurora", "Basilan", "Bataan", "Batanes", "Batangas", "Benguet", "Biliran", "Bohol", "Bukidnon", "Bulacan", "Cagayan", "Camarines Norte", "Camarines Sur", "Camiguin", "Capiz", "Catanduanes", "Cavite", "Cebu", "Compostela Valley", "Cotabato", "Davao Occidental", "Davao Oriental", "Davao del Norte", "Davao del Sur", "Eastern Samar", "Guimaras", "Ifugao", "Ilocos Norte", "Ilocos Sur", "Iloilo", "Isabela", "Kalinga", "La Union", "Laguna", "Lanao del Norte", "Lanao del Sur", "Leyte", "Marinduque", "Masbate", "Metro Manila", "Misamis Occidental", "Misamis Oriental", "Mountain Province", "Negros Occidental", "Negros Oriental", "Northern Samar", "Nueva Ecija", "Nueva Vizcaya", "Occidental Mindoro", "Oriental Mindoro", "Palawan", "Pampanga", "Pangasinan", "Quezon", "Quirino", "Rizal", "Romblon", "Samar", "Sarangani", "Siquijor", "Sorsogon", "South Cotabato", "Southern Leyte", "Sultan Kudarat", "Sulu", "Surigao del Norte", "Surigao del Sur", "Tarlac", "Tawi-Tawi", "Zambales", "Zamboanga del Norte", "Zamboanga del Sur", "Zamboanga Sibugay"});
        countryStatesMap.put("Singapore", new String[]{"", "Central Region", "East Region", "North Region", "North-East Region", "West Region"});
        countryStatesMap.put("Thailand", new String[]{"", "Bangkok", "Chachoengsao", "Chai Nat", "Chaiyaphum", "Chanthaburi", "Chiang Mai", "Chiang Rai", "Chonburi", "Kalasin", "Kamphaeng Phet", "Kanchanaburi", "Khon Kaen", "Lampang", "Lamphun", "Loei", "Lopburi", "Mae Hong Son", "Mahasarakham", "Mukdahan", "Nakhon Nayok", "Nakhon Pathom", "Nakhon Phanom", "Nakhon Ratchasima", "Nakhon Si Thammarat", "Nan", "Narathiwat", "Nong Bua Lamphu", "Nong Khai", "Nonthaburi", "Pattani", "Prachinburi", "Prachuap Khiri Khan", "Phang Nga", "Phatthalung", "Phayao", "Phetchabun", "Phetchaburi", "Phichit", "Phitsanulok", "Phrae", "Princess", "Rayong", "Roi Et", "Sa Kaeo", "Sakon Nakhon", "Samut Prakan", "Samut Sakhon", "Samut Songkhram", "Saraburi", "Satun", "Singburi", "Sukhothai", "Suphan Buri", "Surat Thani", "Surin", "Tak", "Trang", "Trat", "Ubon Ratchathani", "Udon Thani", "Uthai Thani", "Uttaradit", "Yala", "Yasothon"});
        countryStatesMap.put("Vietnam", new String[]{"", "Bac Giang", "Bac Kan", "Bac Lieu", "Bac Ninh", "Binh Dinh", "Binh Duong", "Binh Phuoc", "Ca Mau", "Can Tho", "Cao Bang", "Dac Lak", "Dac Nong", "Dai Loc", "Da Nang", "Dien Bien", "Dong Nai", "Dong Thap", "Ha Giang", "Ha Nam", "Ha Noi", "Ha Tay", "Hai Duong", "Hai Phong", "Hoa Binh", "Hung Yen", "Huyen", "Khanh Hoa", "Kien Giang", "Kon Tum", "Lai Chau", "Lam Dong", "Lang Son", "Lao Cai", "Long An", "Nam Dinh", "Nghe An", "Ninh Binh", "Ninh Thuan", "Phu Tho", "Phu Yen", "Quang Binh", "Quang Nam", "Quang Ngai", "Quang Tri", "Soc Trang", "Son La", "Tay Ninh", "Thai Binh", "Thai Nguyen", "Thanh Hoa", "Thua Thien Hue", "Tien Giang", "Tra Vinh", "Tuyen Quang", "Vinh Phuc", "Vinh Long", "Yen Bai"});

        stateSpinner.setSelection(0);
    }

    private void updateStateSpinner(Spinner stateSpinner, String country) {
        String[] states = countryStatesMap.getOrDefault(country, new String[]{""});
        ArrayAdapter<String> stateAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, states);
        stateAdapter.setDropDownViewResource(R.layout.spinner_item);
        stateSpinner.setAdapter(stateAdapter);
    }

    private void showTimePicker() {
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                (view, hourOfDay, minute1) -> {
                    calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    calendar.set(Calendar.MINUTE, minute1);
                    String time = String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute1);
                    birthTimeText.setText(time);
                }, hour, minute, true);
        timePickerDialog.show();
    }

    private void showDatePickerDialog() {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, year1, month1, dayOfMonth) -> {
                    calendar.set(Calendar.YEAR, year1);
                    calendar.set(Calendar.MONTH, month1);
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    String date = dateFormat.format(calendar.getTime());
                    birthDateText.setText(date);
                }, year, month, day);
        datePickerDialog.show();
    }

    private void showConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmation");
        builder.setMessage("Are you sure?");
        builder.setPositiveButton("Yes", (dialog, which) -> {
            String username = usernameText.getText().toString();
            String email = emailText.getText().toString();
            String phoneNumber = phoneNumberText.getText().toString();
            String interest = interestText.getText().toString();
            String birthTime = birthTimeText.getText().toString();
            String birthDate = birthDateText.getText().toString();
            String country = countryText.getText().toString();
            String state = stateSpinner.getSelectedItem() != null ? stateSpinner.getSelectedItem().toString() : "";
            String gender = genderSpinner.getSelectedItem() != null ? genderSpinner.getSelectedItem().toString() : "";
            String password = passwordText != null ? passwordText.getText().toString() : "";

            String message = "Username: " + username + "\n" +
                    "E-mail Address: " + email + "\n" +
                    "Phone Number: " + phoneNumber + "\n" +
                    "Interest: " + interest + "\n" +
                    "Birth Time: " + birthTime + "\n" +
                    "Birth Date: " + birthDate + "\n" +
                    "Country: " + country + "\n" +
                    "State: " + state + "\n" +
                    "Gender: " + gender + "\n" +
                    "Password: " + password;

            new AlertDialog.Builder(this)
                    .setTitle("Confirm Your Answers")
                    .setMessage(message)
                    .setPositiveButton("OK", (dialog1, which1) -> {
                        Intent intent = new Intent(MainActivity.this, Record.class);
                        startActivity(intent);
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        });
        builder.setNegativeButton("No", (dialog, which) -> {});
        builder.create().show();
    }
}
