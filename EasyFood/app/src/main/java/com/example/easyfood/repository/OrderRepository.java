package com.example.easyfood.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.easyfood.model.Order;
import com.example.easyfood.model.OrderPaymentMethodEnums;
import com.example.easyfood.model.OrderStatusEnums;
import com.example.easyfood.model.Product;
import com.example.easyfood.model.ProductDocument;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Represents a OrderRepository Singleton
 */
public class OrderRepository {
    private String TAG = "OrderRepository";
    private static OrderRepository instance;
    private FirebaseFirestore database = FirebaseFirestore.getInstance(); // TODO Move down queries to Firebase class

    // For the restaurant/manager to be able to observe all incoming orders.
    private MutableLiveData<List<Order>> orders = new MutableLiveData<>();

    private ArrayList<Order> orderList;

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
        orderList = new ArrayList<>();

        getOrdersFromDatabase(eateryId, new IOrdersCallback() {
            @Override
            public void send(ArrayList<Order> list) {
                orderList.addAll(list);
                orders.setValue(orderList);
            }
        });

        orders.setValue(orderList);
        return orders;
    }

    private void getOrdersFromDatabase(final String eateryId, final IOrdersCallback callback) {
        database.collection("orders").whereEqualTo("restaurantId", eateryId)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<Order> orders = new ArrayList<>();

                            for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                                Order order = new Order(eateryId);
                                order.setId(document.getString("id"));
                                order.setOrderNumber(document.getLong("orderNumber"));
                                order.setMessage(document.getString("message"));
                                String payment = document.getString("paymentMethod");
                                switch (payment) {
                                    case "CASH":
                                        order.setPaymentMethod(OrderPaymentMethodEnums.CASH);
                                        break;
                                    case "CARD":
                                        order.setPaymentMethod(OrderPaymentMethodEnums.CARD);
                                }

                                Boolean paid = document.getBoolean("paid");
                                if (paid.equals(true)) {
                                    order.setPaid(true);
                                } else {
                                    order.setPaid(false);
                                }

                                String status = document.getString("orderStatus");
                                switch (status) {
                                    case "CREATED":
                                        order.setOrderStatus(OrderStatusEnums.CREATED);
                                        break;
                                    case "SENT":
                                        order.setOrderStatus(OrderStatusEnums.SENT);
                                        break;
                                    case "CONFIRMED":
                                        order.setOrderStatus(OrderStatusEnums.CONFIRMED);
                                        break;
                                    case "READY":
                                        order.setOrderStatus(OrderStatusEnums.READY);
                                        break;
                                    case "DELIVERED":
                                        order.setOrderStatus(OrderStatusEnums.DELIVERED);
                                        break;
                                }

                                order.setSum((Double) document.get("sum"));
                                order.setCustomerId(document.getString("customerId"));
                                ArrayList<Product> products = document.toObject(ProductDocument.class).products;
                                order.setProducts(products);

                                orders.add(order);
                            }

                            callback.send(orders);

                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
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
