import java.util.Scanner;

public class UserManagement {
  private static Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    boolean run = true;
    while (run) {
      String mainMenu = """
          --------------------------------------------------------------
               1:로그인      2: 회원가입      3:회원목록      4: 나가기
          --------------------------------------------------------------
          """;
      while (true) {
        System.out.println(mainMenu);
        System.out.print("원하는 업무를 선택하세요 : ");
        String menu = scanner.nextLine();
        ;
        if (menu.equals("1")) {
          UserLogIn.UserLogIn();
          run = false;

        } else if (menu.equals("2")) {
          UserSignUp.signUp();
          run = false;

        } else if (menu.equals("3")) {
          UserSignUpList.signUPList();
          run = false;
        } else if (menu.equals("4")) {
          System.out.println("좋은 하루 되세요.");
          return;
        } else {
          System.out.println("메뉴를 다시 선택해주세요.");
          break;
        }
      }
    }
    System.out.println("프로그램 종료");
  }


  public static void logIn(String userId, String password) {
    return;
  }




}