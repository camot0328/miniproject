import java.util.HashMap;
import java.util.Scanner;

public class UserLogIn {
  public static User UserLogIn() {
    Scanner in = new Scanner(System.in);
    System.out.println("\n--------------- 로그인 시스템 ---------------");

    System.out.print("id를 입력하세요: ");
    String userId = in.nextLine();
    System.out.print("password를 입력하세요: ");
    String password = in.nextLine();

    // 사용자 검증
    if (UserManagement.userDatabase.containsKey(userId)) {
      User user = UserManagement.userDatabase.get(userId);
      if (user.password.equals(password)) {
        System.out.println("\n로그인 성공! 환영합니다, " + user.nickname + "님!\n");
        Main.showUserMenu(user);
      } else {
        System.out.println("비밀번호가 일치하지 않습니다.");
      }
    } else {
      System.out.println("존재하지 않는 ID입니다.");
    }
    return null;
  }

  // 사용자 타입에 따라 다른 메뉴 표시
//  private static void showMenu(User user) {
//    Scanner in = new Scanner(System.in);
//    if (user.userType == UserType.운영자) {
//      String productMenu = """
//          -------------------------------------------------------------------
//               1: 상품리스트   2: 주문확인   3: 상품관리   4: 로그아웃
//          -------------------------------------------------------------------
//          """;
//      System.out.println(productMenu);
//    } else { // 일반 고객
//      String productMenu = """
//          -----------------------------------------------------
//               1: 상품리스트   2: 주문확인   3: 로그아웃
//          -----------------------------------------------------
//          """;
//      System.out.println(productMenu);
//    }
//    System.out.print("원하는 메뉴를 선택하세요: ");
//    String choice = in.nextLine();
//
//    if (choice.equals("1")) {
//      System.out.println("📦 상품리스트를 불러옵니다...");
//    } else if (choice.equals("2")) {
//      System.out.println("📝 주문확인을 진행합니다...");
//    } else if (choice.equals("3") && user.userType == UserType.고객) {
//      System.out.println("👋 로그아웃되었습니다.\n");
//      return; // 로그인 화면으로 돌아감
//    } else if (choice.equals("3") && user.userType == UserType.운영자) {
//      System.out.println("⚙️ 상품관리 메뉴로 이동합니다...");
//    } else if (choice.equals("4") && user.userType == UserType.운영자) {
//      System.out.println("👋 로그아웃되었습니다.\n");
//      return; // 로그인 화면으로 돌아감
//    } else {
//      System.out.println("⚠️ 잘못된 입력입니다. 다시 선택해주세요.");
//    }
//  }
}