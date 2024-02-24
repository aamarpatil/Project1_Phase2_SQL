package o.exampl.Model;

import java.util.Objects;

public class Product {
    public int productId;
    public String productDesc;
    public int price;
    public int soldBy;
    public Product(){

    }

    public Product(int productId, String productDesc, int price, int soldBy) {
        this.productId = productId;
        this.productDesc = productDesc;
        this.price = price;
        this.soldBy = soldBy;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSoldBy() {
        return soldBy;
    }

    public void setSoldBy(int soldBy) {
        this.soldBy = soldBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return (productId == (product.productId) &&
                (price == product.price) &&
                (soldBy == product.soldBy) &&
                (Objects.equals(productDesc, product.productDesc)));
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productDesc, price, soldBy);
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productDesc='" + productDesc + '\'' +
                ", price=" + price +
                ", soldBy=" + soldBy +
                '}';
    }
}