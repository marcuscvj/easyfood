package com.example.easyfood.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.easyfood.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * Represents a UserRepository Singleton class
 */
public class UserRepository {
    private String TAG = "UserRepository";
    private static UserRepository instance;
    private FirebaseFirestore database = FirebaseFirestore.getInstance();
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    private MutableLiveData<User> currentUser = new MutableLiveData<>();

    /**
     * Returns an instance of the UserRepository
     *
     * @return instance : UserRepository - The instance
     */
    public static UserRepository getInstance() {
        if (instance == null){
            instance = new UserRepository();
        }
        return instance;
    }

    public MutableLiveData<User> getCurrentUser(String userId) {
        currentUser = new MutableLiveData<>();

        getUserFromDatabase(userId, new IUserCallback() {
            @Override
            public void send(User user) {
                currentUser.setValue(user);
            }
        });

        return currentUser;
    }

    /**
     * Signs in the User with Email and Password
     *
     * @param email String - Email of the user
     * @param password String - Password of the user
     * @return userLiveData MutableLiveData<User> - The user
     */
    public MutableLiveData<User> signInWithEmailAndPassword(String email, String password) {
        currentUser = new MutableLiveData<>();

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task task) {
                if (task.isSuccessful()) {
                    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                    if(firebaseUser != null) {
                        getUserFromDatabase(firebaseUser.getUid(), new IUserCallback() {
                            @Override
                            public void send(User user) {
                                currentUser.setValue(user);
                            }
                        });
                    }
                } else {
                    currentUser.setValue(new User("0", "0"));
                }
            }
        });

        return currentUser;
    }

    /**
     * Gets the user from the database
     *
     * @param userId : String - The id of the user
     * @param callback : IUserCallback - Callback
     */
    private void getUserFromDatabase(final String userId, final IUserCallback callback) {
        database.collection("users").document(userId)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        DocumentSnapshot document = task.getResult();
                        String email = document.getString("email");

                        User user = new User(userId, email);

                        String role = document.getString("role");
                        if (role.equals("MANAGER")) {
                            user.setRole(User.Role.MANAGER);
                        }

                        callback.send(user);
                    }
                });
    }

    /**
     * Registers a new user
     *
     * @param email String - Email of the user
     * @param password String - Password of the user
     */
    public MutableLiveData<User> registerWithEmailAndPassword(String email, String password) {
        currentUser = new MutableLiveData<>();

        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        if (user != null) {
                            createUser(user.getUid(), user.getEmail(), new IUserCallback() {
                                @Override
                                public void send(User user) {
                                    currentUser.setValue(user);
                                }
                            });
                        }
                    } else {
                        currentUser.setValue(new User("0", "0"));
                    }
                }
            });

        return currentUser;
    }

    /**
     * Creates a new user and adds it to the database
     *
     * @param userId : String - The id of the user
     * @param userEmail : String - The email address of the user
     * @param callback : IUserCallback - Callback
     */
    private void createUser(String userId, String userEmail, final IUserCallback callback) {
        User user = new User(userId, userEmail);

        user.setRole(User.Role.CUSTOMER);

        addUserToDatabase(user, new IUserCallback() {
            @Override
            public void send(User user) {
                callback.send(user);
            }
        });
    }

    /**
     * Adds the user to the database
     *
     * @param user : User - The user
     * @param callback : IUserCallback - Callback
     */
    private void addUserToDatabase(final User user, final IUserCallback callback) {
        database.collection("users").document(user.getId())
                .set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        callback.send(user);
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });
    }

    /**
     * Callback interface
     */
    public interface IUserCallback {
        void send(User user);
    }
}
