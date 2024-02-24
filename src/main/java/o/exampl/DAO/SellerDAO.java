package o.exampl.DAO;

import o.exampl.Model.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SellerDAO {
    Connection conn;
    public SellerDAO(Connection conn){
        this.conn = conn;
    }
    public List<Seller> getAllSeller(){
        List<Seller> sellerResults = new ArrayList<>();
        try{
            PreparedStatement ps = conn.prepareStatement("select * from Seller");
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                int sellerId = resultSet.getInt("seller_id");
                String sellerName = resultSet.getString("seller_name");
                Seller a = new Seller(sellerId, sellerName);
                sellerResults.add(a);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return sellerResults;
    }

    public void insertSeller(Seller a){
        try{
            PreparedStatement ps = conn.prepareStatement("insert into " +
                    "Seller (seller_id, seller_name) values (?, ?)");
            ps.setInt(1, a.getSellerId());
            ps.setString(2, a.getSellerName());
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public Seller getSellerById(int id){
        try{
            PreparedStatement ps = conn.prepareStatement(
                    "select * from seller where seller_id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                int sellerId = rs.getInt("seller_id");
                String sellerName = rs.getString("seller_name");
                Seller a = new Seller(sellerId, sellerName);
                return a;
            }else{
                return null;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }


}