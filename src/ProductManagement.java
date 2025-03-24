import java.text.SimpleDateFormat;
import java.util.*;

public class ProductManagement {

  static Map<String, Product> productManagement = new HashMap<>();

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    Product product = null;
    String productNum = "";
    String productName = "";
    String lastDate = "";
    int dallyCount = 0;
    int price = 0;
    String information = "";
    int quantity = 0;


    String productMenu = """
        -------------------------------------------------------------
             1: 상품등록   2: 상품수정   3: 상품삭제   4: 나가기
        -------------------------------------------------------------
        """;
    while (true) {
      System.out.println(productMenu);
      System.out.print("원하는 업무를 선택하세요 : ");
      String menu = in.nextLine();
      if (menu.equals("1")) {
        System.out.println("\n----------------상품등록----------------");

        // 상품명 적는 부분
        while (true) {
          System.out.print("상품명을 입력하세요 : ");
          productName = in.nextLine();

          // 중복된 상품이 있는지 확인작업
          String cleanedProductName = productName.replaceAll("\\s+", "").toLowerCase();
          boolean isDuplicate = false;
          for (Product pList : ProductList.productList) {
            String compareProductName = pList.productName.replaceAll("\\s+", "").toLowerCase();
            if (compareProductName.equals(cleanedProductName)) {
              System.out.println("이미 동일한 상품이 존재합니다. 다시 입력하세요.");
              isDuplicate = true;
              break;
            }
          }
          if (!isDuplicate) {
            break;
          }
        }

        // 상품 가격 적는 부분
        while (true) {
          System.out.print("상품 가격을 입력하세요 : ");
          // 가격에 한글 또는 음수, 큰 금액이 들어오면 다시입력하게 설정함.
          if (in.hasNextInt()) {
            price = in.nextInt();
            if (price <= 0 || price > 100_000_000) {
              System.out.println("상품 가격을 잘못 입력되었습니다.");
              System.out.println("상품 가격을 다시 입력해 주세요.");
              continue;
            }
            break;
          } else {
            System.out.println("잘못된 입력입니다. 숫자만 입력해주세요.");
            in.nextLine();
          }
        }

        // in.nextInt() -> in.nextLine() 하면 그냥 넘어가므로 빈칸 엔터 처리
        in.nextLine();
        System.out.print("상품 설명을 입력하세요 : ");
        information = in.nextLine();

        // 상품 초기 수량 입력 받기
        while (true) {
          System.out.print("상품 수량을 입력하세요 : ");
          if (in.hasNextInt()) {
            quantity = in.nextInt();
            if (quantity < 0) {
              System.out.println("상품 수량은 음수가 될 수 없습니다.");
              continue;
            }
            break;
          } else {
            System.out.println("잘못된 입력입니다. 숫자만 입력해주세요.");
            in.nextLine();
          }
        }

        // productNum 생성 추가할때 년도-달-저장순서로 저장된다
        SimpleDateFormat sdf = new SimpleDateFormat("yyMM");
        String currentDate = sdf.format(new Date());
        if (!currentDate.equals(lastDate)) {
          lastDate = currentDate;
          dallyCount = 0;
        }
        dallyCount++;
        productNum = currentDate + String.format("%03d", dallyCount);

        in.nextLine();
        product = new Product(productName, price, information, productNum, quantity);
        productManagement.put(productNum, product);
        System.out.println();
        System.out.println("상품 등록이 완료되었습니다.");
        System.out.println("상품 번호 : " + productNum);
        System.out.println("상 품 명 : " + productName);
        System.out.println("상품 가격 : " + price);
        System.out.println("상품 설명 : " + information);
        System.out.println("상품 수량 : " + quantity);
      }

      else if (menu.equals("2")) {
        System.out.println("\n----------------상품수정----------------");

        // 상품 목록 출력
        System.out.println("현재 등록된 상품 목록:");

        // ProductList의 등록된 상품들 + productManagement에 있는 상품들 합쳐서 출력
        List<Product> allProduct = new ArrayList<>();
        allProduct.addAll(ProductList.productList);
        allProduct.addAll(productManagement.values());

        // 해당 상품이 존재하는지 확인
        Product productToModify = null;

        while (true) {
          // 합친 목록 출력
          for (Product productInList : allProduct) {
            System.out.println("상품 번호: " + productInList.productNo + ", 상품명: " + productInList.productName);
          }

          // 수정할 상품의 고유번호를 입력하세요
          System.out.print("수정할 상품의 상품 번호를 입력하세요 : ");
          String productNumModify = in.nextLine();

          // 먼저 ProductList에서 찾기
          for (Product productInList : ProductList.productList) {
            if (productInList.productNo.equals(productNumModify)) {
              productToModify = productInList;
              break;
            }
          }

          // 그 다음 productManagement에서 찾기
          if (productToModify == null) {
            productToModify = productManagement.get(productNumModify);
          }

          if (productToModify == null) {
            System.out.println("입력하신 상품 번호에 해당하는 상품이 없습니다.");
            System.out.println("상품 목록을 다시 확인해주세요.\n");
          } else {
            break;
          }
        }

        while (true) {
          // 상품 정보 출력
          System.out.println("\n수정할 상품 정보");
          System.out.println("상품 번호: " + productToModify.productNo);
          System.out.println("상품명: " + productToModify.productName);
          System.out.println("상품 가격: " + productToModify.price);
          System.out.println("상품 설명: " + productToModify.information);
          System.out.println("상품 수량: " + productToModify.quantity);

          // 수정할 항목 선택
          System.out.println("수정할 항목을 선택하세요.");
          System.out.println("1: 상품명 2: 상품 가격 3: 상품 설명 4: 메인메뉴");
          System.out.print("원하는 항목의 번호를 입력하세요: ");
          String modifyOption = in.nextLine();

          // 항목에 따라 수정 작업
          if (modifyOption.equals("1")) {
            System.out.print("새로운 상품명을 입력하세요: ");
            String newProductName = in.nextLine();
            productToModify.productName = newProductName;
            System.out.println("상품명이 수정되었습니다.");
          } else if (modifyOption.equals("2")) {
            while (true) {
              System.out.print("새로운 상품 가격을 입력하세요: ");
              if (in.hasNextInt()) {
                int newPrice = in.nextInt();
                if (newPrice <= 0 || newPrice > 100_000_000) {
                  System.out.println("상품 가격을 잘못 입력되었습니다.");
                  System.out.println("상품 가격을 다시 입력해 주세요.");
                  continue;
                }
                productToModify.price = newPrice;
                break;
              } else {
                System.out.println("잘못된 입력입니다. 숫자만 입력해주세요.");
                in.nextLine();  // 버퍼 비우기
              }
            }
            in.nextLine();
            System.out.println("상품 가격이 수정되었습니다.");
          } else if (modifyOption.equals("3")) {
            System.out.print("새로운 상품 설명을 입력하세요: ");
            productToModify.information = in.nextLine();
            System.out.println("상품 설명이 수정되었습니다.");
          } else if (modifyOption.equals("4")) {
            // 처음으로 돌아가기 (메인 메뉴로 돌아가기)
            System.out.println("메인 메뉴로 돌아갑니다.");
            break;  // 메인 메뉴로 돌아가기
          } else {
            System.out.println("잘못된 선택입니다.");
          }
        }
      }

      else if (menu.equals("3")) {
        System.out.println("\n----------------상품삭제----------------");

        while (true) {
          // 상품 목록 출력 (ProductList.productList와 productManagement 통합)
          System.out.println("현재 등록된 상품 목록:");

          // ProductList의 미리 등록된 상품들 + productManagement에 있는 상품들 합쳐서 출력
          List<Product> allProducts = new ArrayList<>();
          allProducts.addAll(ProductList.productList);
          allProducts.addAll(productManagement.values());

          // 다 지웠을때 항목으로 돌아가기
          if (allProducts.isEmpty()){
            System.out.println("등록된 상품이 없습니다.");
            break;
          }

          // 합친 목록 출력
          for (Product productInList : allProducts) {
            System.out.println("상품 번호: " + productInList.productNo + ", 상품명: " + productInList.productName);
          }
          System.out.println("상품 목록으로 돌아갈려면 \"c\"를 입력해주세요.");

          // 삭제할 상품 번호 입력받기
          System.out.print("삭제할 상품의 번호를 입력하세요: ");
          String productNumToDelete = in.nextLine();

          // c를 입력했을때 상품 목록으로 가게
          if (productNumToDelete.equals("c")) {
            System.out.println("상품 목록으로 돌아갑니다.");
            System.out.println();
            break;  // 목록으로 돌아가며 반복문 종료
          }

          // 해당 상품이 존재하는지 확인 (productList와 productManagement 모두에서 확인)
          Product productToDelete = null;

          // 먼저 ProductList에서 찾기
          for (Product productInList : ProductList.productList) {
            if (productInList.productNo.equals(productNumToDelete)) {
              productToDelete = productInList;
              break;
            }
          }

          // 그 다음 productManagement에서 찾기
          if (productToDelete == null) {
            productToDelete = productManagement.get(productNumToDelete);
          }

          if (productToDelete == null) {
            System.out.println("입력하신 상품 번호에 해당하는 상품이 없습니다.");
            System.out.println("메인 메뉴로 돌아갈려면 \"c\"를 입력해주세요.");
            continue;
          } else {
            // 상품 삭제
            boolean isProductDeleted = false;
            // ProductList에서 삭제하려면, 새로운 리스트로 덮어씌워야 하므로 수정해야 합니다.
            if (ProductList.productList.contains(productToDelete)) {
              ProductList.productList.remove(productToDelete);
              isProductDeleted = true;
            } else if (productManagement.containsKey(productNumToDelete)) {
              productManagement.remove(productNumToDelete);
              isProductDeleted = true;
            }

            if (isProductDeleted) {
              System.out.println("상품이 삭제되었습니다.");
              System.out.println();
            } else {
              System.out.println("상품 삭제에 실패했습니다.");
              System.out.println();
            }
          }
        }
      }

      else if (menu.equals("4")) {
        // 나가기
        System.out.println("좋은 하루 되세요.");
        break;
      }

    }
  }
}