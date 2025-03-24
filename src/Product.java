public class Product {
  String productName;
  int price;
  String productNo;
  String information;
  int quantity;
  String productAdd;
  String productUpdate;

  public Product(String productName, int price, String information, String productNo, int quantity) {
    this.productName = productName;
    this.price = price;
    this.information = information;
    this.productNo = productNo;
    this.quantity = quantity;
//    this.productAdd = productAdd;
//    this.productUpdate = productUpdate;
  }
}