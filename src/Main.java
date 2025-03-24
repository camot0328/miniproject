import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    while (true) {
      System.out.println("------------------------------------------");
      System.out.println("  1: 로그인   2: 회원가입   3: 나가기");
      System.out.println("------------------------------------------");
      System.out.print("원하는 메뉴를 선택하세요: ");

      String choice = scanner.nextLine();

      if (choice.equals("1")) {
        User user = UserLogIn.UserLogIn();
        if (user != null) {
          showUserMenu(user);
        }
      } else if (choice.equals("2")) {
        UserSignUp.signUp();
      } else if (choice.equals("3")) {
        System.out.println("프로그램을 종료합니다. 좋은 하루 되세요!");
        break;
      } else {
        System.out.println("⚠️ 올바른 숫자를 입력하세요.");
      }
    }
    scanner.close();
  }

  public static void showUserMenu(User user) {
    Scanner scanner = new Scanner(System.in);

    if (user.userType == UserType.운영자) {
      while (true) {
        System.out.println("-----------------------------------------------------");
        System.out.println("  1: 상품 관리   2: 주문 확인   3: 로그아웃");
        System.out.println("-----------------------------------------------------");
        System.out.print("원하는 메뉴를 선택하세요: ");
        String choice = scanner.nextLine();

        if (choice.equals("1")) {
          ProductManagement.productManagementMenu();
        } else if (choice.equals("2")) {
          System.out.print("조회할 고객의 ID를 입력하세요: ");
          String targetUserId = scanner.nextLine();
          Order.showOrders(targetUserId); // 모든 주문 확인 기능 추가 필요
        } else if (choice.equals("3")) {
          System.out.println("로그아웃되었습니다.");
          break;
        } else {
          System.out.println("⚠️ 올바른 숫자를 입력하세요.");
        }
      }
    } else {
      while (true) {
        String menu1 = """
        -----------------------------------------------
           1: 상품 목록 보기   2: 주문 조회   3: 로그아웃
        -----------------------------------------------
        """;
        System.out.println(menu1);
        System.out.print("원하는 메뉴를 선택하세요: ");
        String choice = scanner.nextLine();

        if (choice.equals("1")) {
          ClientProduct.customerMenu(user, choice);  // ✅ 입력값을 전달
        } else if (choice.equals("2")) {
          System.out.println("\n주문 내역을 조회합니다...");
          Order.showOrders(user.userId);

        } else if (choice.equals("3")) {
          System.out.println("로그아웃되었습니다.");
          break;
        } else {
          System.out.println("⚠️ 올바른 숫자를 입력하세요.");
        }
      }
    }
  }
}
