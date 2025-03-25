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
}
