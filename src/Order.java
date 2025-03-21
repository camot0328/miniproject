import java.time.LocalDateTime;

public class Order {
  int orderId;
  String orderList;
  LocalDateTime orderDate;
  String orderStatus;

  public Order(int orderId, String orderList, LocalDateTime orderDate, String orderStatus) {
    this.orderId = orderId;
    this.orderList = orderList;
    this.orderDate = orderDate;
    this.orderStatus = orderStatus;
  }
}
