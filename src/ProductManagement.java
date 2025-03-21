import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ProductManagement {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    Product product = null;
    Map<String, Product> productManagement = new HashMap<>();
    String productNum = "";
    String productName = "";
    String lastDate = "";
    int dallyCount = 0;
    int price = 0;
    String information = "";



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
        System.out.println("----------------상품등록----------------");

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

        // productNum 생성 추가할때 년도-달-저장순서로 저장된다
        SimpleDateFormat sdf = new SimpleDateFormat("yyMM");
        String currentDate = sdf.format(new Date());
        if (!currentDate.equals(lastDate)) {
          lastDate = currentDate;
          dallyCount = 0;
        }
        dallyCount++;
        productNum = currentDate + String.format("%04d", dallyCount);

        product = new Product(productName, price, information, productNum);
        productManagement.put(productNum, product);
        System.out.println("상품 등록이 완료되었습니다.");
        System.out.println("상품 번호 : " + productNum);
        System.out.println("상 품 명 : " + productName);
        System.out.println("상품 가격 : " + price);
        System.out.println("상품 설명 : " + information);
      }

      else if (menu.equals("2")) {
        System.out.println("----------------상품등록----------------");
        // 상품코드를 입력하세요
        Product pd1 = productManagement.get(productNum);
        //
//        pd1.price =

      }

      else if (menu.equals("4")) {
        System.out.println("좋은 하루 되세요.");
        break;
      } else {
        System.out.println("메뉴를 다시 선택해주세요.");
      }

    }
  }
}
