package o.exampl;

import o.exampl.Controller.ProductController;
import o.exampl.DAO.SellerDAO;
import o.exampl.DAO.ProductDAO;
import o.exampl.Service.SellerService;
import o.exampl.Service.ProductService;
import Util.ConnectionSingleton;
import io.javalin.Javalin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;

public class Main {
    public static Logger log = LoggerFactory.getLogger(o.exampl.Main.class);
    public static void main(String[] args) {

        Connection conn = ConnectionSingleton.getConnection();
        SellerDAO sellerDAO = new SellerDAO(conn);
        ProductDAO productDAO = new ProductDAO(conn);
        SellerService sellerService = new SellerService(sellerDAO);
        ProductService productService = new ProductService(sellerService, productDAO);
        ProductController productcontroller = new ProductController(sellerService, productService);

        Javalin api = productcontroller.getAPI();

        api.start(9001);


    }
}