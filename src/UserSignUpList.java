import java.util.HashMap;

//public class UserSignUpList {
//  private static HashMap<String, User> userDatabase = new HashMap<>();
//
//  static {  // 직접 userDatabase 사용하여 초기 데이터 추가
//    userDatabase.put("user1111", new User("user1111", "123456", "박보경"));
//    userDatabase.put("master", new User("master", "1234zxcv", "정대현", UserType.운영자));
//    userDatabase.put("user0000", new User("user0000", "654321", "최상근"));
//  }
//
//  public static void signUPList() {
//    System.out.println("\n-----------------------------저장된 회원 목록-----------------------------");
//    for (String key : UserSignUp.userDatabase.keySet()){
//      System.out.println("Num: " + key + " | " + userDatabase.get(key));
//    }
//    System.out.println("------------------------------------------------------------------------\n");
//  }
//
//  // 미리 저장된 사용자 리스트를 반환 (중복 검사용)
//  public static HashMap<String, User> getUserDatabase() {
//    return userDatabase;
//  }
//
//}