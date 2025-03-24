import java.util.HashMap;
import java.util.Scanner;

public class UserSignUp {
  public static User newUser = new User("", "", "");
  private static Scanner scanner = new Scanner(System.in);
  public static HashMap<String, User> userDatabase = UserSighUpList.getUserDatabase();

  public static void signUp() {
    System.out.println("\n=== 회원가입 시스템 ===");

    // ID 입력 및 검증
    while (true) {
      System.out.print("id를 입력하세요. (영소문자, 숫자 조합 4~12자) : ");
      newUser.userId = scanner.nextLine();

      if (!isValidInput(newUser.userId)) {
        System.out.println("⚠️ ID는 영소문자와 숫자로 이루어진 4~12자여야 합니다.");
        continue;
      }

      if (userDatabase.containsKey(newUser.userId)) {
        System.out.println("⚠️ 이미 존재하는 ID입니다. 다른 ID를 입력하세요.");
      } else {
        break;
      }
    }

    // 비밀번호 입력 및 검증
    while (true) {
      System.out.print("password를 입력하세요. (영소문자, 숫자 조합 4~12자) : ");
      newUser.password = scanner.nextLine();

      if (!isValidInput(newUser.password)) {
        System.out.println("⚠️ 비밀번호는 영소문자와 숫자로 이루어진 4~12자여야 합니다.");
      } else {
        break;
      }
    }

    // 닉네임 입력 및 중복 확인
    while (true) {
      System.out.print("닉네임을 입력하세요. (영소문자, 숫자 조합 4~12자) : ");
      newUser.nickname = scanner.nextLine();

      if (!isValidInput(newUser.nickname)) {
        System.out.println("⚠️ 닉네임은 영소문자와 숫자로 이루어진 4~12자여야 합니다.");
        continue;
      }

      if (isNicknameDuplicate(newUser.nickname)) {
        System.out.println("⚠️ 이미 사용 중인 닉네임입니다. 다른 닉네임을 입력하세요.");
      } else {
        break;
      }
    }

    // 새로운 회원 정보를 데이터베이스에 저장
    userDatabase.put(newUser.userId, newUser);

    System.out.println("\n✅ 회원가입 완료!");
    System.out.println(newUser);
    System.out.println("======================\n");
  }

  // 입력값이 유효한지 검사하는 메서드 (영소문자 + 숫자 조합 & 4~12자)
  private static boolean isValidInput(String input) {
    return input.matches("^[a-z0-9]{4,12}$");
  }

  // 닉네임 중복 검사
  private static boolean isNicknameDuplicate(String nickname) {
    return userDatabase.values().stream().anyMatch(user -> user.nickname.equals(nickname));
  }
}