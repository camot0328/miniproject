import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserManagement {
  static Scanner scanner = new Scanner(System.in);
  static Map<String, User> userDatabase = new HashMap<>();

  static {
    userDatabase.put("user1", new User("user1", "1234", "박보경"));
    userDatabase.put("master", new User("master", "9876", "정대현", UserType.운영자));
    userDatabase.put("user2", new User("user2", "0000", "최상근"));
  }

  public static void main(String[] args) {

    while (true) {
      String mainMenu = """
          ------------------------------------------
               1: 로그인   2: 회원가입   3: 나가기
          ------------------------------------------
          """;
      while (true) {
        System.out.println(mainMenu);
        System.out.print("원하는 업무를 선택하세요: ");
        String menu = scanner.nextLine();

        if (menu.equals("1")) {
          UserLogIn.UserLogIn();
          break;

        } else if (menu.equals("2")) {
          UserSignUp.signUp();
          break;

        } else if (menu.equals("3")) {
          System.out.println("좋은 하루 되세요.");
          return;
        } else {
          System.out.println("메뉴를 다시 선택해주세요.");
        }
      }
    }
  }
}
