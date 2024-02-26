package o.exampl.Service;

import o.exampl.DAO.ProductDAO;
import o.exampl.Exceptions.ProductException;
import o.exampl.Exceptions.SellerException;
import o.exampl.Model.Product;
import o.exampl.Main;
import o.exampl.Service.SellerService;

import java.util.ArrayList;
import java.util.List;

public class ProductService {
    ProductDAO productDAO;
    List<Product> productList;
    SellerService sellerService;

    public ProductService(SellerService sellerService, ProductDAO productDAO) {
        this.sellerService = sellerService;
        this.productDAO = productDAO;
        productList = new ArrayList<>();

    }

   /* public void saveProduct(Product p) throws ProductException {
        int id = p.getProductId();
//        If no product with that id was found - insert the product
        if (productDAO.getProductById(id) == null) {
            productDAO.insertProduct(p);
        } else {
            throw new ProductException("product with id " + id + " already exists");
        }
    }
*/
    public List<Product> getAllProducts() {
        Main.log.info("Product List returned: " + productList);
        return productDAO.getAllProducts();
    }

    public Product addProduct(Product product) throws ProductException {
        int id = (int) (Math.random() * Integer.MAX_VALUE);
        product.setProductId(id);
        int soldby = product.getSoldBy();
        if (product.getProductDesc() == null || product.getProductDesc().isEmpty()) {
            Main.log.warn("Product name/desc is empty");
            throw new ProductException("Product name/desc is empty");
        } else if (product.getPrice() <= 0) {
            Main.log.warn("Product price is less than or equal to 0");
            throw new ProductException("Product price is less than or equal to 0");
        }
        productList.add(product);
        Main.log.info("Product added: " + product.toString());
        return product;
    }




    /*      int soldby = product.getSoldBy();
          if (product.getProductDesc() == null || product.getProductDesc().isEmpty()) {
              Main.log.warn("Product name/desc is empty");
              throw new ProductException("Product name is empty");
          }
          if (product.getPrice() <= 0) {
              Main.log.warn("Product price is less than or equal to 0");
              throw new ProductException("Product price is less than or equal to 0");
          }*/
    public Product updateProduct(int id, Product product) throws ProductException, SellerException {
        Product productToUpdate = productDAO.updateProductById(product, id);

        if (sellerService.isValidSeller(product.getSoldBy())) {
            productToUpdate.setProductDesc(product.getProductDesc());
            productToUpdate.setPrice(product.getPrice());
            productToUpdate.setSoldBy(product.getSoldBy());
            Main.log.info("Updating a product");
        } else {
            Main.log.warn("Product Exception thrown because Seller Name must exist in the Seller database");
            throw new ProductException("SellerName must exist in Seller database");
        }
        return null;
    }
    public Product getProductById(int productID) throws ProductException {
        Main.log.info("Searching by product Id " + productID);
        Product p = productDAO.getProductById(productID);
        if (p == null) {
            throw new ProductException("No product found with that ID");
        } else {
            return p;
        }
    }

    /*public Product deletebyProductId(int id) {
        for (int i = 0; i < productList.size(); i++) {
            Product currentProduct = productList.get(i);
            if (currentProduct.getProductId() == id) {
                productList.remove(i);
            }
        }
        return null;
    }*/

    public Product deleteProduct(Product p) throws ProductException {
        Product product = getProductById(p.getProductId());
        if (product != null) {
            Main.log.info("Deleting product by product id.");
            productDAO.deleteProductById(product.getProductId());
            System.out.println("Product ID " + product.getProductId() + " has been deleted successfully");
            return product;
        } else {
            Main.log.info("Product exception thrown because Product ID not found.");
            throw new ProductException("Product ID " + product.getProductId() + " not found.");
        }

    }
}