import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Product {
  String productName;
  int price;
  String productNo;
  String information;
  String productAdd;
  String productUpdate;

  public Product(String productName, int price, String information, String productNo) {
    this.productName = productName;
    this.price = price;
    this.information = information;
    this.productNo = productNo;
//    this.productAdd = productAdd;
//    this.productUpdate = productUpdate;
  }
}