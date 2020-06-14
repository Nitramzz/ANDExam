package flyingfuck.whatthe.and.data;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import flyingfuck.whatthe.and.data.model.LoggedInUser;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    private FirebaseAuth mAuth;

    public Result<LoggedInUser> login(String username, String password) {
        mAuth = FirebaseAuth.getInstance();
        if(mAuth.signInWithEmailAndPassword(username, password).isSuccessful()) {
            Log.d("mAuth", mAuth.getCurrentUser().toString());
               LoggedInUser fakeUser =
                       new LoggedInUser(
                               java.util.UUID.randomUUID().toString(),
                               mAuth.getCurrentUser().getDisplayName());
               return new Result.Success<LoggedInUser>(fakeUser);
        }
        else
        {
            return new Result.Error(new Exception("There was a problem with the login attempt"));
        }
    }

    public void logout() {
        mAuth.signOut();
    }
}
