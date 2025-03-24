public class User {
  String userId;
  String password;
  String nickname;
  UserType userType;

  //회원가입시 기본적으로 '고객'으로 설정
  public User(String userId, String password, String nickname) {
    this.userId = userId;
    this.password = password;
    this.nickname = nickname;
    this.userType = UserType.고객; // 기본값 : 고객
  }

  //특정 역할을 부여할 경우
  public User(String userId, String password, String nickname, UserType userType) {
    this.userId = userId;
    this.password = password;
    this.nickname = nickname;
    this.userType = userType;
  }
  @Override
  public String toString() {
    return "ID: " + userId + " | Password: " + password + " | Nickname: " + nickname;
  }
}

