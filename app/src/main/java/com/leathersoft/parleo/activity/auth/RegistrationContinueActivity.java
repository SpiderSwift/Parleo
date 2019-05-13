package com.leathersoft.parleo.activity.auth;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.leathersoft.parleo.R;
import com.leathersoft.parleo.activity.InterestsWindowActivity;
import com.leathersoft.parleo.activity.LanguageWindowActivity;
import com.leathersoft.parleo.messaging.Interest;
import com.leathersoft.parleo.messaging.LanguageModel;
import com.leathersoft.parleo.network.SingletonRetrofitClient;
import com.leathersoft.parleo.network.model.Hobby;
import com.leathersoft.parleo.network.model.Lang;
import com.leathersoft.parleo.network.model.Language;
import com.leathersoft.parleo.network.model.UserUpdateModel;
import com.leathersoft.parleo.util.LanguageHolderUtil;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationContinueActivity extends AppCompatActivity {

    @BindView(R.id.te_name) EditText editName;
    @BindView(R.id.te_date) Button editDate;
    //@BindView(R.id.layout) ConstraintLayout layout;

    private static int CODE_LANG = 200;
    private static int CODE_INTERESTS = 201;

    private Boolean gender;
    private Date dateBirth;
    private List<LanguageModel> languageModels = new ArrayList<>();
    private List<Interest> interests = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_continue);
        ButterKnife.bind(this);


        SingletonRetrofitClient.getInsance().getApi().getLanguages()
                .enqueue(new Callback<List<Lang>>() {
                    @Override
                    public void onResponse(Call<List<Lang>> call, Response<List<Lang>> response) {
                        List<Lang> languages = response.body();
                        LanguageHolderUtil.getInstance().clearMap();
                        LanguageHolderUtil.getInstance().fillMap(getBaseContext(), languages);
                        for (Lang language : languages) {


                            Locale locale = new Locale(language.getId());

                            //todo спасибо беку и ребятам на пк версии за охуенный костыль
                            if (language.getId().equals("gb")) {
                                languageModels.add(new LanguageModel(language.getId(),"English", 0, 0));
                            } else if (language.getId().equals("bh")) {
                                languageModels.add(new LanguageModel(language.getId(),"Bhojpuri", 0, 0));
                            } else {
                                languageModels.add(new LanguageModel(language.getId(),locale.getDisplayName(), 0, 0));
                            }



                        }
                    }

                    @Override
                    public void onFailure(Call<List<Lang>> call, Throwable t) {

                    }
                });


        SingletonRetrofitClient.getInsance().getApi().getHobbies()
                .enqueue(new Callback<List<Hobby>>() {
                    @Override
                    public void onResponse(Call<List<Hobby>> call, Response<List<Hobby>> response) {
                        List<Hobby> hobbies = response.body();
                        for (Hobby hobby : hobbies) {
                            interests.add(new Interest(hobby.getName()));
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Hobby>> call, Throwable t) {

                    }
                });




    }



    @OnClick(R.id.te_date)
    public void onClickDate() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        Dialog d = new DatePickerDialog(RegistrationContinueActivity.this, dateSetListener, year, month, day);
        d.show();

    }



    private DatePickerDialog.OnDateSetListener dateSetListener =
            ((view, year, month, day) -> {
                editDate.setText(view.getYear() + " / " + (view.getMonth()+1) + " / " + view.getDayOfMonth());

                Calendar cal = Calendar.getInstance();
                cal.set(year, month, day);
                dateBirth = cal.getTime();
            });


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick(R.id.btn_continue)
    public void onContinue() {

        if (editName.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please write your name", Toast.LENGTH_LONG);
            return;
        }

        if (dateBirth == null) {
            Toast.makeText(getApplicationContext(), "Please pick a birthdate", Toast.LENGTH_LONG);
            return;
        }

        if (gender == null) {
            Toast.makeText(getApplicationContext(), "Please choose your gender", Toast.LENGTH_LONG);
            return;
        }

        List<Language> languages = new ArrayList<>();
        for (LanguageModel languageModel : languageModels) {
            if (languageModel.isChosen() == 1) {
                Language language = new Language();
                language.setCode(languageModel.getCode());
                language.setLevel(languageModel.getLevel() + 1); // todo тут нужно подумать, можно ли сделать нормально?
                languages.add(language);
            }
        }

        List<String> hobbies = new ArrayList<>();

        for (Interest interest : interests) {
            if (interest.isChosen() == 1) {
                hobbies.add(interest.getName());
            }
        }


        UserUpdateModel model = new UserUpdateModel();
        model.setAbout("");
        model.setBirthdate(dateBirth);
        model.setHobbies(hobbies);
        model.setLanguages(languages);
        model.setGender(gender);
        model.setName(editName.getText().toString());
        SingletonRetrofitClient.getInsance()
                .getApi()
                .updateUser(model)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(response.isSuccessful()){
                            startActivity(new Intent(getApplicationContext(), RegistrationFinishActivity.class));
                        } else {
                            try {
                                JSONObject jObjError = new JSONObject(response.errorBody().string());
                                Log.d("JSON",jObjError.toString());
                                Toast.makeText(getApplicationContext(), jObjError.getString("error"), Toast.LENGTH_LONG).show();
                            } catch (Exception e) {

                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });




    }

    @OnClick(R.id.btn_languages)
    public void showLanguages() {
        startActivityForResult(new Intent(getApplicationContext(), LanguageWindowActivity.class).putExtra("listLanguages", (Serializable) languageModels), CODE_LANG);
    }

    @OnClick(R.id.btn_interests)
    public void showInterests() {
        startActivityForResult(new Intent(getApplicationContext(), InterestsWindowActivity.class).putExtra("listInterests", (Serializable) interests), CODE_INTERESTS);
    }


    @OnClick(R.id.btn_gender)
    public void showGender() {
        String[] genders = {"Female", "Male"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Your gender");
        builder.setItems(genders, (dialog, which) -> gender = which == 1); //todo check if right?
        builder.show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //finish();
        if (data == null) {
            return;
        }
        if (requestCode == CODE_LANG) {
            languageModels = (List<LanguageModel>) data.getSerializableExtra("listLanguages");
            Log.d("TAG", languageModels.toString());
        }

        if (requestCode == CODE_INTERESTS) {
            interests = (List<Interest>) data.getSerializableExtra("listInterests");
            Log.d("TAG", interests.toString());
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
