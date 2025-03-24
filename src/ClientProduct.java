import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClientProduct {
  public static void customerMenu(User user, String menu) {
    Scanner in = new Scanner(System.in);

    while (true) {
      if (menu.equals("1")) {
        showProductList(user);
      } else if (menu.equals("2")) {
        System.out.println("\n주문 내역을 조회합니다...");
        Order.showOrders(user.userId);
      } else if (menu.equals("3")) {
        System.out.println("로그아웃되었습니다.");
        return;
      } else {
        System.out.println("⚠️ 올바른 숫자를 입력하세요.");
      }

      // ✅ 새로운 입력을 받아서 menu 값을 업데이트
      System.out.print("원하는 메뉴를 선택하세요: ");
      menu = in.nextLine();
    }
  }


  // ✅ 상품 목록 보기 기능을 별도 메서드로 분리 (customerMenu 내에서 중복 호출 방지)
  private static void showProductList(User user) {
    Scanner in = new Scanner(System.in);
    List<Product> productListC = new ArrayList<>();
    productListC.addAll(ProductList.productList);
    productListC.addAll(ProductManagement.productManagement.values());
    Product product;

    String menu1 = """
        -----------------------------------------------
           1: 상품 목록 보기   2: 주문 조회   3: 로그아웃
        -----------------------------------------------
        """;

    while (true) {
      System.out.println("\n----------------상품 목록 보기----------------");

      if (productListC.isEmpty()) {
        System.out.println("현재 등록된 상품이 없습니다.");
        return;
      }

      for (Product productInList : productListC) {
        System.out.println("상품명: " + productInList.productName + ", 가격: " + productInList.price + "원, 수량: " + productInList.quantity);
      }

      System.out.print("상품명을 입력하여 선택해주세요 ('c' 입력 시 취소): ");
      String selectedProductName = in.nextLine().replaceAll("\\s+", "").toLowerCase();

      if (selectedProductName.equals("c")) {
        System.out.println("상품 목록 보기를 종료합니다.");
        System.out.println(menu1);
        return;  // ✅ 목록에서 나가기 (메뉴로 돌아감)
      }

      Product selectedProduct = null;
      for (Product product1 : productListC) {
        if (product1.productName.replaceAll("\\s+", "").equalsIgnoreCase(selectedProductName)) {
          selectedProduct = product1;
          break;
        }
      }

      if (selectedProduct == null) {
        System.out.println("⚠️ 선택한 상품이 존재하지 않습니다. 다시 입력해주세요.");
        continue;
      }

      System.out.println("\n선택한 상품 정보:");
      System.out.println("상품명: " + selectedProduct.productName);
      System.out.println("가격: " + selectedProduct.price + "원");
      System.out.println("상품 설명: " + selectedProduct.information);
      System.out.println("상품 갯수: " + selectedProduct.quantity);

      System.out.print("\n이 상품을 구매하시겠습니까? (예/아니요): ");
      String purchaseChoice = in.nextLine();

      if (purchaseChoice.equalsIgnoreCase("예")) {
        int quantityToBuy = -1;
        while (quantityToBuy <= 0 || quantityToBuy > selectedProduct.quantity) {
          System.out.print("몇 개를 구매하시겠습니까? ");
          String input = in.nextLine().trim();
          try {
            quantityToBuy = Integer.parseInt(input);
            if (quantityToBuy <= 0) {
              System.out.println("⚠️ 구매할 수량은 1개 이상이어야 합니다.");
            } else if (quantityToBuy > selectedProduct.quantity) {
              System.out.println("⚠️ 재고 부족! 최대 구매 가능 수량: " + selectedProduct.quantity + "개");
            }
          } catch (NumberFormatException e) {
            System.out.println("⚠️ 유효하지 않은 수량입니다. 숫자로 입력해주세요.");
          }
        }

        if (quantityToBuy >= 1) {
          Order.orderCreate(user, selectedProduct, quantityToBuy);
          selectedProduct.quantity -= quantityToBuy; // ✅ 구매 후 상품 수량 감소
          System.out.println("구매 완료! " + selectedProduct.productName + " " + quantityToBuy + "개 구매.");
          System.out.println("총 금액: " + (selectedProduct.price * quantityToBuy) + "원");
        }
      } else {
        System.out.println("구매가 취소되었습니다.");
      }

      System.out.print("다른 상품을 보시겠습니까? (예/아니요): ");
      String continueChoice = in.nextLine();
      if (!continueChoice.equalsIgnoreCase("예")) {
        System.out.println("상품 목록 보기를 종료합니다.");
        System.out.println(menu1);
        return;  // ✅ 상품 목록 종료 후, 메뉴로 돌아감
      }
    }
  }
}