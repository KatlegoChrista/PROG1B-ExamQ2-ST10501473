package com.mycompany.iproductcalculations;


public class ProductsCalculations implements IProductCalculations {
    
    private double price;

    public ProductsCalculations(double price) {
        this.price = price;
    }

    // to calculate15% VAT
    @Override
    public double GetVat() {
        return price * 0.15;
    }

    //to calculate 5% comission 
    @Override
    public double GetCommission() {
        return price * 0.05;
    }

    //to calculate total sale 
    @Override
    public double GetTotal() {
        return price + GetVat() + GetCommission();
    }
}