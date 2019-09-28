package com.rewedigital.examples.msintegration.productstock.stock;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Stock {

    @Id
    @Column(name = "id")
    private String productId;

    private int amount;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public static class Builder {

        private String productId;
        private int amount = 0;

        public Builder withProductId(String productId) {
            this.productId = productId;
            return this;
        }

        public Builder withAmount(int amount) {
            this.amount = amount;
            return this;
        }

        public Stock build() {
            Stock stock = new Stock();
            stock.productId = productId;
            stock.amount = amount;
            return stock;
        }
    }
}