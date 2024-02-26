package o.exampl.Service;

import o.exampl.DAO.SellerDAO;
import o.exampl.Exceptions.SellerException;
import o.exampl.Model.Seller;
import o.exampl.Main;
import java.util.ArrayList;
import java.util.List;

public class SellerService {
    SellerDAO sellerDAO;
    public SellerService(SellerDAO sellerDAO){
        this.sellerDAO = sellerDAO;
    }

    public List<Seller> getAllSeller(){
        List<Seller> sellerList = sellerDAO.getAllSeller();
        Main.log.info("Seller List returned: " + sellerList);
        return sellerList;
    }

 /*   public void saveSeller(Seller a){
        sellerDAO.insertSeller(a);
    }*/

    List<Seller> sellerList;
    List<String> sellerName;
    public SellerService() {
        this.sellerList = new ArrayList<>();
        this.sellerName = new ArrayList<>();
    }
    public Seller getSellerById(int id) throws SellerException {
        Seller a = sellerDAO.getSellerById(id);
        if(a == null){
            throw new SellerException("no seller with such id found");
        }else{
            return a;
        }
    }

     public void addSeller(Seller a) throws SellerException {
         List<Seller> sellerList = sellerDAO.getAllSeller();
        if (a.getSellerName().isEmpty()) {
            Main.log.warn("Seller name is empty");
            throw new SellerException("Seller name is empty");
        }
        if (a.getSellerId() == 0 ) {
             Main.log.warn("Seller's name cannot be zero");
             throw new SellerException("Seller's name cannot be zero.");
         }
         if (sellerList.contains(a)){
             Main.log.warn("Duplicate seller's name.");
             throw new SellerException("Duplicate seller's name: " + a);
         }
 /*       sellerList.add(a);
        sellerName.add(a.getSellerName());
        */

        Main.log.info("Seller added: " + a);
        sellerDAO.insertSeller(a);
    }
    public boolean isValidSeller(int sellerId) throws SellerException {
        List<Seller> sellerList = sellerDAO.getAllSeller();
        return sellerList.stream().anyMatch(seller -> seller.getSellerId() == sellerId);
    }
    /*public boolean isDuplicate(Seller p) throws SellerException {
        int id = p.getSellerId();
//        If no product with that id was found - insert the product
        if (sellerDAO.getSellerById(p.sellerId) == null) {
            sellerDAO.insertSeller(p);
        } else {
            throw new SellerException("seller with id " + id + " already exists");
        }
        return false;

    }
    */

}