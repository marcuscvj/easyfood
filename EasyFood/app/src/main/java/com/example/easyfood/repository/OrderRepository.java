package com.example.easyfood.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.easyfood.model.Order;
import com.example.easyfood.model.OrderStatusEnums;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a OrderRepository Singleton
 */
public class OrderRepository {
    private String TAG = "OrderRepository";
    private static OrderRepository instance;
    private FirebaseFirestore database = FirebaseFirestore.getInstance();;

    // For the restaurant/manager to be able to observe all incoming orders.
    private MutableLiveData<List<Order>> orders = new MutableLiveData<>();

    /**
     * Returns an instance of the OrderRepository
     *
     * @return instance : OrderRepository - The instance of the OrderRepository
     */
    public static OrderRepository getInstance() {
        if (instance == null) {
            instance = new OrderRepository();
        }

        return instance;
    }

    /**
     * Returns a LiveData list of all orders
     *
     * @param eateryId : String - The eatery
     * @return orders : MutableLiveData<List<Order>> - The list of orders
     */
    public MutableLiveData<List<Order>> getAllOrders(String eateryId) {
        // TODO Database query
        return orders;
    }

    /**
     * Sends the order to the database
     *
     * @param order : Order - The order
     */
    public void sendOrder(final Order order) {
        final String orderId = getGeneratedOrderIdFromDatabase();
        order.setId(orderId);

        database.collection("orders").document(orderId)
                .set(order)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        setOrderStatus(orderId, OrderStatusEnums.SENT);
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
     * Updates the orderStatus of the order in the database
     *
     * @param orderId : String - The id of the order
     * @param newStatus : OrderStatusEnum - The order status
     */
    private void setOrderStatus(String orderId, OrderStatusEnums newStatus) {
        Map<String, Object> status = new HashMap<>();
        status.put("orderStatus", newStatus);

        database.collection("orders").document(orderId).update(status)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
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
     * Returns a new generated order id from the database
     *
     * @return String: id - New id from the database
     */
    private String getGeneratedOrderIdFromDatabase() {
        return database.collection("orders").document().getId();
    }

}
