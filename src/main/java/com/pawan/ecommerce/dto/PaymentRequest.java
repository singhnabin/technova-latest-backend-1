package com.pawan.ecommerce.dto;

import com.pawan.ecommerce.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {
    private PaymentInfo paymentInfo;
    private List<Product> productsList;
    private String orderBy;


    public @Data static class PaymentInfo {
        public Card card;
        public String client_ip;
        public int created;
        public String email;
        public String id;
        public boolean livemode;
        public String object;
        public String type;
        public boolean used;
    }


    public @Data static class Card {
        public String address_city;
        public String address_country;
        public String address_line1;
        public String address_line1_check;
        public Object address_line2;
        public String address_state;
        public String address_zip;
        public String address_zip_check;
        public String brand;
        public String country;
        public String cvc_check;
        public Object dynamic_last4;
        public int exp_month;
        public int exp_year;
        public String funding;
        public String id;
        public String last4;
        public String name;
        public String object;
        public Object tokenization_method;


    }
//    JSONObject jsonObject= new JSONObject(Card);
//    jsonObject.get('address_country');
}
