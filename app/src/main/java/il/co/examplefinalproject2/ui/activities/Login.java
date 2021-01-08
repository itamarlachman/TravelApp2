package il.co.examplefinalproject2.ui.activities;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import il.co.examplefinalproject2.R;
import il.co.examplefinalproject2.interfaces.DataResult;
import il.co.examplefinalproject2.models.Company;
import il.co.examplefinalproject2.sources.FireBase;
import il.co.examplefinalproject2.ui.viewmodels.LoginViewModel;
import il.co.examplefinalproject2.utils.DateUtils;
import il.co.examplefinalproject2.utils.DialogUtils;
import il.co.examplefinalproject2.utils.Globals;

public class Login extends AppCompatActivity {

    protected LoginViewModel viewModel;
    protected EditText name,email,password;
    protected  DialogUtils dialogUtils;

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        registerViews();
        registerActions();
        registerUtils();
        test();

    }

    private void test() {
        new FireBase().lastAccess(DateUtils.getNow());
    }

    private void registerUtils() {
        dialogUtils = new DialogUtils(Login.this);
    }

    private void registerViews() {
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
    }

    private void registerActions() {
        viewModel = ViewModelProviders.of(Login.this).get(LoginViewModel.class);
        viewModel.getLiveData().observe(this, new Observer<DataResult>() {
            @Override
            public void onChanged(DataResult dataResult) {
                if ((dataResult.getEntity().equals(DataResult.Entity.Companies) && (dataResult.isSuccess() == true))
                        &&
                        (dataResult.getOperation().equals(DataResult.Operation.Login) ||
                                dataResult.getOperation().equals(DataResult.Operation.Register)))
                {
                    startMain(dataResult);
                } else {
                    if ( dataResult.getOperation().equals(DataResult.Operation.Login))
                        dialogUtils.showAlert("Login","Unable to perform login, see error " + dataResult.getException().getMessage(), "Close");
                    else if ( dataResult.getOperation().equals(DataResult.Operation.Register))
                        dialogUtils.showAlert("Register","Unable to register, see error " + dataResult.getException().getMessage(), "Close");
                }
            }
        });

        findViewById(R.id.login)
                .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Company company = new Company(email.getText().toString(),password.getText().toString());
                    viewModel.SignIn(company);

                } catch (Exception ex) {
                    dialogUtils.showError(ex);
                }
            }
        });

        findViewById(R.id.register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Company company = new Company(name.getText().toString(), email.getText().toString(), password.getText().toString());
                    viewModel.Register(company);
                } catch (Exception ex) {
                    dialogUtils.showError(ex);
                }

            }
        });
    }

    private void startMain(DataResult dataResult) {
        try {
            Intent intent = new Intent(Login.this, Main.class);
            startActivity(intent);

        } catch(Exception ex) {
            this.dialogUtils.showError(ex);
        }
    }


}