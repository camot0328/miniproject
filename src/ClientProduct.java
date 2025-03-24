import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClientProduct {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    List<Product> productListC = new ArrayList<>();
    productListC.addAll(ProductList.productList);
    productListC.addAll(ProductManagement.productManagement.values());
    User user ;


    String productMenu = """
        -------------------------------------------------------------
             1: 상품목록   2: 주문조회   3: 로그아웃
        -------------------------------------------------------------
        """;

    while (true) {
      System.out.println(productMenu);
      System.out.print("원하는 메뉴를 선택하세요 : ");
      String menu = in.nextLine();
      if (menu.equals("1")) {

        System.out.println("\n----------------상품목록----------------");

        while (true) {
          if (productListC.isEmpty()) {
            System.out.println("현재 등록된 상품이 없습니다.");
            break;
          }

          // 상품 목록 출력
          for (Product productInList : productListC) {
            System.out.println("상품명: " + productInList.productName + ", 상품가격: " + productInList.price
                              + ", 상품수량: " + productInList.quantity);
          }

          // 고객에게 상품명 선택을 요청
          System.out.print("상품명을 입력하여 선택해주세요: ");
          String selectedProductName = in.nextLine().replaceAll("\\s+", "").toLowerCase();

          // 선택한 상품을 찾아서 출력
          Product selectedProduct = null;
          for (Product product : productListC) {
            // 상품명에서 공백을 제거하고 소문자로 변환한 후 비교
            String cleanedProductName = product.productName.replaceAll("\\s+", "").toLowerCase();
            if (cleanedProductName.equalsIgnoreCase(selectedProductName)) {
              selectedProduct = product;
              break;
            }
          }

          // 상품이 없으면 종료
          if (selectedProduct == null) {
            System.out.println("선택한 상품명에 해당하는 상품이 없습니다.\n");
            continue;
          }

          // 선택한 상품의 정보 출력 (가격과 설명만)
          System.out.println("\n선택한 상품 정보:");
          System.out.println("상품명: " + selectedProduct.productName);
          System.out.println("가격: " + selectedProduct.price + "원");
          System.out.println("상품 설명: " + selectedProduct.information);
          System.out.println("상품 갯수: " + selectedProduct.quantity);

          // 구매 여부 물어보기
          System.out.print("\n이 상품을 구매하시겠습니까? (예/아니요): ");
          String purchaseChoice = in.nextLine();

          if (purchaseChoice.equalsIgnoreCase("예")) {
//            Order.orderCreate(user.userId, selectedProduct, selectedProduct.quantity);

            // quantityToBuy 의 초기값을 -1로 잡는다 0으로 잡을시 밑에 while(조건)에 맞아 종료될수도 있다
            int quantityToBuy = -1;
            // 수량 입력 받기
            while (quantityToBuy <= 0 || quantityToBuy > selectedProduct.quantity) {
              System.out.print("몇 개를 구매하시겠습니까? ");
              // 수량 입력 받기
              String input = in.nextLine().trim();

              try {
                quantityToBuy = Integer.parseInt(input);
                // 수량이 음수거나 0일 경우
                if (quantityToBuy <= 0) {
                  System.out.println("구매할 수량은 1개 이상이어야 합니다.");
                } else if (quantityToBuy > selectedProduct.quantity) {
                  System.out.println("재고 수량이 부족합니다. 최대 구매 가능한 수량은 " + selectedProduct.quantity + "개입니다.");
                }
              } catch (NumberFormatException e) {
                // 숫자가 아닌 경우 처리
                System.out.println("유효하지 않은 수량입니다. 숫자로만 입력해 주세요.");
              }
            }
            // 구매가 완료되면 주문 생성
            if (quantityToBuy >= 1) {
//              Order.orderCreate(user.userId, selectedProduct, quantityToBuy);  // 주문 추가
              System.out.println("구매가 완료되었습니다.");
              System.out.println("구매하신 상품: " + selectedProduct.productName + ", 수량: " + quantityToBuy + "개, 총 금액: " + selectedProduct.price * quantityToBuy + "원");

              // 상품 구매 완료 후 반복문 종료
              break;
            } else if (purchaseChoice.equalsIgnoreCase("아니오")) {
              // 구매 취소 후 목록으로 돌아가기
              System.out.println("구매가 취소되었습니다.");
              break;  // 목록으로 돌아가도록 반복문 종료
            } else {
              System.out.println("구매가 취소되었습니다.");
              break;
            }
          }
        }
      }
    }
  }
}