
import o.exampl.DAO.ProductDAO;
import o.exampl.Exceptions.ProductException;
import o.exampl.Exceptions.SellerException;
import o.exampl.Model.Product;
import o.exampl.Model.Seller;
import o.exampl.Service.ProductService;
import o.exampl.Service.SellerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class ProductServiceTest {
    ProductService productService;
    ProductDAO productDAO;
    SellerService sellerService;
    Seller testSeller;


    @Before
    public void setup() {

        productDAO = mock(ProductDAO.class);
        sellerService = mock(SellerService.class);
        productService = new ProductService(sellerService, productDAO);

    }
    @Test
    public void getAllProducts() {
        List<Product> existingProducts = new ArrayList<>();
        existingProducts.add(new Product(1, "A", 10, 1));
        existingProducts.add(new Product(1, "A", 10, 1));
        when(productDAO.getAllProducts()).thenReturn(existingProducts);

        List<Product> result = productService.getAllProducts();
        assertFalse(result.isEmpty());
        assertEquals(2, result.size());
    }

    @Test
    public void getEmptyProductsList() {
        when(productDAO.getAllProducts()).thenReturn(new ArrayList<>());
        List<Product> result = productService.getAllProducts();
        assertTrue(result.isEmpty());
    }

    /*
    @Test
    public void getProductById() throws ProductException {
        int validProductId = 1;
        Product expectedProduct = new Product(validProductId,"product a",10,1);
        when(productDAO.getProductById(validProductId)).thenReturn(expectedProduct);

        Product result = productService.getProductById(validProductId);

        assertNotNull(result);
        assertEquals(expectedProduct,result);
    }
    */
    @Test
    public void addProduct() throws ProductException, SellerException {
        Product newProduct = new Product(1,"Product A", 10, 1);
//        when(sellerService.isValidSeller(newProduct.getSellerID())).thenReturn(true);
        Product result = productService.addProduct(newProduct);
        assertNotNull(result);
        assertEquals(newProduct.getProductDesc(), result.getProductDesc());
    }

    /*@Test (expected = ProductException.class)
    public void testAddProductWithInvalidSeller() throws ProductException, SellerException {
        Product invalidProduct = new Product(1,"Product A", 10, 0);
        when(sellerService.isValidSeller(invalidProduct.getSoldBy())).thenReturn(false);
        productService.addProduct(invalidProduct);
    }*/

    @Test
    public void getByProductId() throws ProductException {
        int validProductId = 1;
        Product expectedProduct = new Product(validProductId,"product a",10,1);
        when(productDAO.getProductById(validProductId)).thenReturn(expectedProduct);

        Product result = productService.getProductById(validProductId);

        assertNotNull(result);
        assertEquals(expectedProduct,result);
    }

    @Test(expected = ProductException.class)
    public void getByInvalidProductId() throws ProductException {
        int inValidProductId = 2;
        when(productDAO.getProductById(inValidProductId)).thenReturn(null);

        productService.getProductById(inValidProductId);
    }

/*    @Test
    public void deleteByProductId() throws ProductException{
        long validProductId = 1;
        Product expectedProduct = new Product(validProductId,"product a",10.0,1);
        when(productDAO.getProductById(validProductId)).thenReturn(expectedProduct);
        Product result = productService.deletebyProductId(expectedProduct);
        assertNotNull(result);
        assertEquals(expectedProduct,result);
        verify(productDAO).deleteProductById(validProductId);
    }


    @Test
    public void updateProduct() throws  ProductException, SellerException{
        int id = 123;
        Product product = new Product(id,"Updated Product", 10,456);

 //       when(sellerService.isValidSeller(product.getSoldBy())).thenReturn(true);
        when(productDAO.updateProductById(product,id)).thenReturn(product);
        productService.updateProduct(id, product);
        verify(sellerService).isValidSeller(product.getSoldBy());
        verify(productDAO).updateProductById(product,id);

    }
*/
    /*
    @Test
    public void addProduct() throws ProductException, SellerException {
        Product newProduct = new Product(1,"Product A", 10, 1);
 //       when(sellerService.isValidSeller(newProduct.getSellerID())).thenReturn(true);
        Product result = productService.addProduct(newProduct);
        assertNotNull(result);
        assertEquals(newProduct.getProductDesc(), result.getProductDesc());
    }

    @Test
    public void updateProduct()throws  ProductException, SellerException{
        int id = 1;
        Product product = new Product(id,"Updated Product", 10,456);

        when(sellerService.isValidSeller(product.getSoldBy())).thenReturn(true);
        when(productDAO.updateProductById(product,id)).thenReturn(product);
        productService.updateProduct(id, product);
        verify(sellerService).isValidSeller(product.getSoldBy());
        verify(productDAO).updateProductById(product,id);

    }
     /*

        Product newProduct = new Product(1,"Product A", 10, 1);

        addProduct();

        List<Product> productList = productService.getAllProducts();
        int id = (int) productList.get(0).getProductId();

        try{
            sellerService.addSeller(new Seller());
            productService.updateProduct(id,testProduct);

        } catch (ProductException e) {
            e.printStackTrace();
            Assert.fail();
        }
        catch (SellerException s) {
            s.printStackTrace();
            Assert.fail("Exception should not be thrown");
        }


        Product actual = productList.get(0);
        Assert.assertTrue(actual.getProductId() > 0);
        Assert.assertEquals(name, actual.getProductDesc());
        Assert.assertEquals(price, actual.getPrice());
        Assert.assertEquals(soldBy, actual.getSoldBy());
    }
    */

   /* @Test
    public void updateProductEmptyName()
         throws ProductException, SellerException {
        Product newProduct = new Product(1,"Product A", 10, 1);
        Product result = productService.addProduct(newProduct);


        List<Product> productList = productService.getAllProducts();
        int id = (int) productList.get(0).getProductId();

        try{
            productService.updateProduct(id, result);
            Assert.fail();

        } catch (ProductException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateProductPriceLessThanZero()throws ProductException, SellerException {
        Product newProduct = new Product(1,"Product A", 10, 1);
        Product result = productService.addProduct(newProduct);

        List<Product> productList = productService.getAllProducts();
        int id = (int) productList.get(0).getProductId();

        try{
            productService.updateProduct(id,result);
            Assert.fail();

        } catch (ProductException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateProductSellerWrong()
            throws ProductException, SellerException {
        Product newProduct = new Product(1,"Product A", 10, 1);
        Product result = productService.addProduct(newProduct);

        List<Product> productList = productService.getAllProducts();
        int id = (int) productList.get(0).getProductId();

        try{
            productService.updateProduct(id,result);
            Assert.fail();

        } catch (ProductException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteProduct()throws ProductException, SellerException {
        Product newProduct = new Product(1,"Product A", 10, 1);
        Product result = productService.addProduct(newProduct);

        List<Product> productList = productService.getAllProducts();
        int id = (int) productList.get(0).getProductId();

        productService.deletebyProductId(id);

        Assert.assertTrue(productList.isEmpty());
    }

    */
   @Test
   public void testUpdateProduct() throws  ProductException, SellerException{
       int id = 1;
       Product product = new Product(id,"Updated Product", 10,456);

       when(sellerService.isValidSeller(product.getSoldBy())).thenReturn(true);
       when(productDAO.updateProductById(product,id)).thenReturn(product);
       productService.updateProduct(id, product);
       verify(sellerService).isValidSeller(product.getSoldBy());
       verify(productDAO).updateProductById(product,id);

   }
    @Test
   public void testDeleteByProductId() throws ProductException{
       int validProductId = 1;
       Product expectedProduct = new Product(validProductId,"product a",10,1);
       when(productDAO.getProductById(validProductId)).thenReturn(expectedProduct);
       Product result = productService.deleteProduct(expectedProduct);
       assertNotNull(result);
       assertEquals(expectedProduct,result);
// verify is used to verify that certain interactiosn with mck objects have occurred during the test execution
       verify(productDAO).deleteProductById(validProductId);
   }}
