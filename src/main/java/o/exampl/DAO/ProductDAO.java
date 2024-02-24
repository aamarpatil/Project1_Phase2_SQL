package o.exampl.DAO;

import o.exampl.Model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    Connection conn;
    public ProductDAO(Connection conn){
        this.conn = conn;
    }
    public void insertProduct(Product p){
        try{
            PreparedStatement ps = conn.prepareStatement("insert into Product" +
                    " (product_id, product_desc, price, sold_by) " +
                    "values (?, ?, ?, ?)");
            ps.setInt(1, p.getProductId());
            ps.setString(2, p.getProductDesc());
            ps.setInt(3, p.getPrice());
            ps.setInt(4, p.getSoldBy());
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public List<Product> getAllProducts(){
        List<Product> resultProducts = new ArrayList<>();
        try{
            PreparedStatement ps = conn.prepareStatement("select * from product");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int productId = rs.getInt("product_id");
                String productDesc = rs.getString("product_desc");
                int price = rs.getInt("price");
                int soldBy = rs.getInt("sold_by");
                Product p = new Product(productId, productDesc, price, soldBy);
                resultProducts.add(p);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return resultProducts;
    }
    public Product getProductById(int id){
        try{
            PreparedStatement ps = conn.prepareStatement("select * from Product where product_id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                int productId = rs.getInt("product_id");
                String productDesc = rs.getString("product_desc");
                int price = rs.getInt("price");
                int soldBy = rs.getInt("sold_by");
                Product p = new Product(productId, productDesc, price, soldBy);
                return p;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public Product updateProductById(Product p, int id){
        try{
            PreparedStatement ps = conn.prepareStatement(
                    "UPDATE product SET product_name = ?, price = ?, seller_id = ? where product_id = ?");
            ps.setString(1, p.getProductDesc());
            ps.setInt(2, p.getPrice());
            ps.setInt(3, p.getSoldBy());
            ps.setInt(4, id);
            ps.executeUpdate();
            return p;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public List<Product> getProductsOrderedByYear(){
        List<Product> resultProducts = new ArrayList<>();
        try{
            PreparedStatement ps = conn.prepareStatement("select * from product order by year_made desc limit 1 offset 1");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int productId = rs.getInt("product_id");
                String productDesc = rs.getString("product_desc");
                int price = rs.getInt("price");
                int soldBy = rs.getInt("sold_by");
                Product p = new Product(productId, productDesc, price, soldBy);
                resultProducts.add(p);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return resultProducts;
    }

    public Product deleteProductById(long x){
        try{
            PreparedStatement ps = conn.prepareStatement(
                    "DELETE FROM Product where product_id = ?");
            ps.setLong(1, x);
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}