package com.example.easyfood.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.easyfood.db.Firebase;
import com.example.easyfood.db.IEateriesCallback;
import com.example.easyfood.model.Eatery;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * EateryRepository - Singleton
 */
public class EateryRepository {
    private String TAG = "EateryRepository";
    private static EateryRepository instance;
    private static Firebase fb;
    private FirebaseFirestore database = FirebaseFirestore.getInstance();
    private ArrayList<Eatery> eateryList;
    private MutableLiveData<List<Eatery>> eateries = new MutableLiveData<>();
    private MutableLiveData<Eatery> eatery = new MutableLiveData<>();

    /**
     * Returns an instance of the EateryRepository
     *
     * @return EateryRepository: instance - The instance of the EateryRepository.
     */
    public static EateryRepository getInstance() {
        if (instance == null){
            instance = new EateryRepository();
            fb = new Firebase();
        }
        return instance;
    }

    /**
     * Returns the eateries.
     *
     * @return MutableLiveData<List<Eatery>>: eateries - The eateries.
     */
    public MutableLiveData<List<Eatery>> getEateries() {
        // Empty the list so we don't get duplicates
        eateryList = new ArrayList<>();

        fb.getAllEateries(new IEateriesCallback() {
            @Override
            public void send(ArrayList<Eatery> list) {
                eateryList.addAll(list);
                eateries.setValue(eateryList);
            }
        });

        eateries.setValue(eateryList);
        return eateries;
    }


    /**
     * Returns eatery by manager id
     *
     * @param managerId : String - The id of the manager
     * @return eatery : LiveData<Eatery> - The eatery
     */
    public MutableLiveData<Eatery> getSpecificEatery(String managerId) {
        eatery = new MutableLiveData<>();

        getEateryFromDatabase(managerId, new IEateryCallback() {
            @Override
            public void send(Eatery newEatery) {
                eatery.setValue(newEatery);
            }
        });

        return eatery;
    }

    /**
     * Gets a specific eatery from the database by its manager id
     * @param managerId : String - Id of the manager
     * @param callback : IEateryCallback - Callback
     */
    private void getEateryFromDatabase(String managerId, final IEateryCallback callback) {
        database.collection("eateries").whereEqualTo("managerId", managerId)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            Eatery eatery = new Eatery();

                            for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                                eatery = document.toObject(Eatery.class);
                            }

                            callback.send(eatery);

                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    /**
     * Interface
     */
    private interface IEateryCallback {
        void send(Eatery eatery);
    }
}
