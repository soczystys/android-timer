package com.myprojects.android_timer.main.actions;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputLayout;
import com.myprojects.android_timer.R;
import com.myprojects.android_timer.main.data.newdata.entity.ActionEntity;
import java.util.List;

/**
 * {@link android.app.Activity} where u can save
 * {@link com.myprojects.android_timer.main.data.newdata.entity.ActionEntity} in the database.
 */
public class ActionAddActivity extends AppCompatActivity {
    private TextInputLayout textInputTitle;
    private TextInputLayout textInputDescription;
    private List<String> list;
    private ActionEntity actionEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_add);
        textInputTitle = findViewById(R.id.activity_action_add_name_layout);
        textInputDescription = findViewById(R.id.activity_action_add_description_layout);
    }

    /**
     * Checks for a proper length of fields and shows errors in case of wrong data.
     * @return logical value if data can be saved.
     * @see ActionAddActivity#click(View) 
     */
    public boolean validate() {
        list = getIntent().getStringArrayListExtra("LIST_OF_TITLES");
        String title = textInputTitle.getEditText().getText().toString();
        String description = textInputDescription.getEditText().getText().toString();
        if (title.isEmpty()) {
            textInputTitle.setError("Field can't be empty");
            return false;
        } else if (list.contains(title)) {
            textInputTitle.setError("theres already an activity with given name");
            return false;
        } else if (title.length() > 12) {
            textInputTitle.setError("name cant have more than 12 characters");
            return false;
        } else {
            textInputTitle.setError(null);
            actionEntity = new ActionEntity(title, description);
            return true;
//            textInputTitle.setErrorEnabled(false);
        }
    }

    /**
     * Saves new {@link ActionEntity} as a parcelable.
     * @param view
     */
    public void click(View view) {
        if (validate()) {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("RESULT_NEW_ACTION", actionEntity);
            setResult(RESULT_OK, resultIntent);
            finish();
        }
    }
}
