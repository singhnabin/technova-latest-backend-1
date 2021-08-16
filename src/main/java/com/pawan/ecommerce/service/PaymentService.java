package com.pawan.ecommerce.service;

import com.pawan.ecommerce.dto.PaymentRequest;
import com.pawan.ecommerce.model.Order;
import com.pawan.ecommerce.model.Product;
import com.pawan.ecommerce.repo.OrderRepo;
import com.pawan.ecommerce.repo.ProductRepo;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaymentService {

    private  final String secretKey="";

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepo productRepo;
//    @PostConstruct
////    public void init(){
////        Stripe.apiKey=secretKey;
////    }
////    public void makePayment(PaymentRequest paymentRequest) throws StripeException {
////        Map<String,Object> stripeObj=new HashMap<>();
////        stripeObj.put("amount", getAmmount(paymentRequest.getProductsList()));
////        stripeObj.put("currency","USD");
////        stripeObj.put("source",paymentRequest.getPaymentInfo().getId());
////        Charge charge= Charge.create(stripeObj);
////        System.out.println(charge.toString());
////
////
////
////    }
////
////    private Integer getAmmount(List<Product> productsList) {
////        return productsList.stream().mapToInt(product ->  (int)(product.getPrice() * product.getQuantity())).sum()*100;
////
////    }






    @PostConstruct
    public void init(){
        Stripe.apiKey=secretKey;
    }

    public JSONObject makePayment(PaymentRequest paymentRequest) throws StripeException {
        JSONObject jsonObject= new JSONObject();
        Map<String, Object> customerMap = new HashMap<String, Object>();
        customerMap.put("amount", getTotalAmount(paymentRequest.getProductsList()));
        customerMap.put("currency", "USD");
        customerMap.put("source",paymentRequest.getPaymentInfo().getId());
        Charge paymentCharge=Charge.create(customerMap);
        if(paymentCharge.getPaid()){
            for(Product product:paymentRequest.getProductsList()){
                handleProductQuantity(product);
                Order order= new Order();
                order.setCategory(product.getCategory());
                order.setName(product.getName());
                order.setCreated_by(paymentRequest.getOrderBy());
                order.setProduct(product.getName());
                order.setQuantity(product.getQuantity());
                order.setPrice(product.getQuantity()*product.getPrice());
                order.setDeleveryStatus("Order getting ready");
                order.setOrderProgress(5);
                order.setTransactionId(paymentCharge.getId());
                order.setOrderReceipt(paymentCharge.getReceiptUrl());
                orderRepo.save(order);
            }
            jsonObject.put("status",200);
            jsonObject.put("message","Payment successfull. $"+paymentCharge.getAmount()+"+ is charged from account");
            return jsonObject;
        }
        jsonObject.put("status",400);
        jsonObject.put("error","Payment processing failed");
        return jsonObject;

    }

    private void handleProductQuantity(Product product) {
        Product availableProduct=productService.getproductbyID(product.getId());
        if(product.getQuantity()<=availableProduct.getQuantity()){
            availableProduct.setQuantity(availableProduct.getQuantity()-product.getQuantity());
            productRepo.save(availableProduct);
        }

    }

    private Integer getTotalAmount(List<Product> productsList) {
        return productsList.stream().mapToInt(product -> (int) (product.getQuantity() * product.getPrice())).sum();
    }


}
