
import o.exampl.Exceptions.ProductException;
import o.exampl.Exceptions.SellerException;
import o.exampl.Model.Product;
import o.exampl.Model.Seller;
import o.exampl.Service.SellerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import o.exampl.DAO.SellerDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
public class SellerServiceTest {
    SellerService sellerService;
    SellerDAO sellerDAO;


    @Before
    public void setup() {
         sellerDAO = mock(SellerDAO.class);
        sellerService = new SellerService(sellerDAO);
    }
    @Test
    public void getAllEmpty(){
        List<Seller> sellerList = sellerService.getAllSeller();
        Assert.assertTrue(sellerList.isEmpty());
    }

    @Test
    public void getAllSellers() {
        List<Seller> existingSellers = new ArrayList<>();
        existingSellers.add(new Seller(1, "A"));
        existingSellers.add(new Seller(1, "A"));
        when(sellerDAO.getAllSeller()).thenReturn(existingSellers);

        List<Seller> result = sellerService.getAllSeller();
        assertFalse(result.isEmpty());
        assertEquals(2, result.size());
    }

    @Test
    public void insertSeller() throws SellerException {
            Seller sellerA = new Seller(1, "Seller A");
            when(sellerDAO.getAllSeller()).thenReturn(new ArrayList<>());

            sellerService.addSeller(sellerA);
            verify(sellerDAO, times(1)).insertSeller(sellerA);
        }
   /*     Seller testSeller = new Seller();
        testSeller.setSellerId(1);
        testSeller.setSellerName("Ashwini");

        try{
            sellerService.addSeller(testSeller);
        } catch (SellerException e) {
            e.printStackTrace();
            Assert.fail();
        }

        List<Seller> sellerList = sellerService.getAllSeller();
        Seller actual = sellerList.get(0);
        Assert.assertEquals(1, actual.getSellerId());
        Assert.assertEquals("Ashwini", actual.getSellerName());
    }
*/
   @Test
   public void testSearchBySellerId() throws SellerException {
       int validSellerId = 1;
       Seller expectedSeller = new Seller(validSellerId,"product a");
       when(sellerDAO.getSellerById(validSellerId)).thenReturn(expectedSeller);

       Seller result = sellerService.getSellerById(validSellerId);

       assertNotNull(result);
       assertEquals(expectedSeller,result);
   }



    @Test
    public void insertDuplicateSeller(){
        Seller testSeller = new Seller();
        testSeller.setSellerName("Ashwini");

        try{
            sellerService.addSeller(testSeller);
            sellerService.addSeller(testSeller);
            Assert.fail();
        } catch (SellerException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void insertEmptySeller(){
        Seller testSeller = new Seller();
        testSeller.setSellerName("");

        try{
            sellerService.addSeller(testSeller);
            Assert.fail();
        } catch (SellerException e) {
            e.printStackTrace();
        }
    }
}