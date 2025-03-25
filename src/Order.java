import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Order {
  static Map<String, List<OrderItem>> userOrders = new HashMap<>();
  static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); // ✅ 날짜 형식 변경

  public static class OrderItem {
    Product product;
    int quantity;
    int totalPrice;
    LocalDateTime purchaseDate;

    public OrderItem(Product product, int quantity, int totalPrice, LocalDateTime purchaseDate) {
      this.product = product;
      this.quantity = quantity;
      this.totalPrice = totalPrice;
      this.purchaseDate = purchaseDate;
    }

    @Override
    public String toString() {
      return "상품명: " + product.productName + ", 수량: " + quantity + ", 가격: " + totalPrice + "원, 구매일: " + purchaseDate.format(formatter);
    }
  }

  // 주문 생성
  public static void orderCreate(User user, Product product, int quantity) {
    LocalDateTime purchaseDate = LocalDateTime.now();
    int totalPrice = product.price * quantity;
    OrderItem orderItem = new OrderItem(product, quantity, totalPrice, purchaseDate);
    userOrders.computeIfAbsent(user.userId, k -> new ArrayList<>()).add(orderItem);
  }

  // 고객: 자신의 주문 목록 조회
  public static void showOrders(String userId) {
    List<OrderItem> orders = userOrders.get(userId);
    if (orders == null || orders.isEmpty()) {
      System.out.println("주문 내역이 없습니다.");
      return;
    }
    System.out.println(userId + "님의 주문 내역:");
    for (int i = 0; i < orders.size(); i++) {
      System.out.println((i + 1) + ". " + orders.get(i));
    }
    Scanner scanner = new Scanner(System.in);
    while (true) {
      System.out.print("\n취소할 주문이 있습니까? (예/아니요): ");
      String cancelChoice = scanner.nextLine().trim().toLowerCase();

      if (cancelChoice.equals("예")) {
        System.out.print("취소할 상품명을 입력하세요: ");
        String productName = scanner.nextLine();

        System.out.print("취소할 수량을 입력하세요: ");
        int cancelQuantity = scanner.nextInt();
        scanner.nextLine(); // 버퍼 비우기

        cancelOrder(userId, productName, cancelQuantity);
        break;
      } else if (cancelChoice.equals("아니요")) {
        break;  // ✅ "아니요" 입력 시 루프 탈출
      } else {
        System.out.println("⚠️ 잘못된 입력입니다. '예' 또는 '아니요'로 입력해주세요.");
      }
    }
  }

  // 고객: 주문 취소 (상품명으로 입력 + 수량 선택 + 재고 반영)
  public static void cancelOrder(String userId, String productName, int cancelQuantity) {
    List<OrderItem> orders = userOrders.get(userId);
    if (orders == null || orders.isEmpty()) {
      System.out.println("주문 내역이 없습니다.");
      return;
    }

    // 해당 상품 찾기
    for (OrderItem order : orders) {
      if (order.product.productName.equalsIgnoreCase(productName)) {
        if (Duration.between(order.purchaseDate, LocalDateTime.now()).toHours() > 24) {
          System.out.println("이 주문은 24시간이 지나 취소할 수 없습니다.");
          return;
        }

        if (cancelQuantity <= 0 || cancelQuantity > order.quantity) {
          System.out.println("⚠️ 잘못된 수량 입력입니다. 주문한 개수 이내로 입력해주세요.");
          return;
        }

        // 취소된 수량만큼 원래 상품 재고에 추가
        order.product.quantity += cancelQuantity;
        order.quantity -= cancelQuantity;

        // 만약 주문 수량이 0이면 리스트에서 삭제
        if (order.quantity == 0) {
          orders.remove(order);
        }

        System.out.println("주문 취소 완료: " + productName + " " + cancelQuantity + "개 취소됨.");
        return;
      }
    }
    System.out.println("⚠️ 해당 상품이 주문 목록에 없습니다.");
  }
}