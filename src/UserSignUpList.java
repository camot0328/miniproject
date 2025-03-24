import java.util.HashMap;
import java.util.LinkedHashMap;

public class UserSighUpList {
  private static HashMap<String, User> userDatabase = new HashMap<>();

  static {  // 직접 userDatabase 사용하여 초기 데이터 추가
    userDatabase.put("asdf1234", new User("asdf1234", "4321fdsa", "A"));
    userDatabase.put("zxcv9876", new User("zxcv9876", "1234zxcv", "B", UserType.운영자));
    userDatabase.put("qwer1234", new User("qwer1234", "5678qwer", "C"));
  }

  public static void signUPList() {
    System.out.println("\n-----------------------------저장된 회원 목록-----------------------------");
    for (String key : UserSignUp.userDatabase.keySet()){
      System.out.println("Num: " + key + " | " + userDatabase.get(key));
    }
    System.out.println("------------------------------------------------------------------------\n");
  }

  // 미리 저장된 사용자 리스트를 반환 (중복 검사용)
  public static HashMap<String, User> getUserDatabase() {
    return userDatabase;
  }

}