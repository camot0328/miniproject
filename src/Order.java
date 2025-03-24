import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order {
  // 고객 ID를 키로 하고, 해당 고객의 주문 내역을 값으로 가지는 Map
  static Map<String, List<OrderItem>> userOrders = new HashMap<>();

  // 주문 항목을 관리하는 OrderItem 클래스
  public static class OrderItem {
    Product product;
    int quantity;
    int totalPrice;
    LocalDateTime purchaseDate;  // 날짜/시간을 LocalDateTime으로

    public OrderItem(Product product, int quantity, int totalPrice, LocalDateTime purchaseDate) {
      this.product = product;
      this.quantity = quantity;
      this.totalPrice = totalPrice;
      this.purchaseDate = purchaseDate;
    }

    @Override
    public String toString() {
      return "상품명: " + product.productName + ", 수량: " + quantity +
          ", 가격: " + totalPrice + ", 구매일: " + purchaseDate;
    }
  }

  // 고객의 주문 내역을 Map에 추가하는 메서드
  public static void orderCreate(User user, Product product, int quantity) {
    LocalDateTime purchaseDate = LocalDateTime.now();
    int totalPrice = product.price * quantity;

    // 주문 항목을 생성
    OrderItem orderItem = new OrderItem(product, quantity, totalPrice, purchaseDate);

    // 고객의 주문 내역 리스트 가져오기 (없으면 새로 생성)
    List<OrderItem> orders = userOrders.getOrDefault(user.userId, new ArrayList<>());

    // 주문 내역 리스트에 추가
    orders.add(orderItem);

    // 다시 맵에 업데이트
    userOrders.put(user.userId, orders);
  }

  // 고객의 주문 내역을 출력하는 메서드
  public static void showOrders(String userId) {
    List<OrderItem> orders = userOrders.get(userId);

    if (orders == null || orders.isEmpty()) {
      System.out.println("해당 고객의 주문 내역이 없습니다.");
    } else {
      System.out.println(userId + "님의 주문 내역:");
      for (OrderItem order : orders) {
        System.out.println(order);
      }
    }
  }
}