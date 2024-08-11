import java.sql.SQLException;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        DB db = new DB();
//        db.CreateUserTable();
//        db.InsertUser("kmin983", "Password0.!");
        String hash = db.loadUserPW("kmin983");
//        Authentication auth = new Authentication();
//        var a = new Authentication().HashPassword("Password0.!");
        var b = Authentication.VerifyPassword("Password0.!", hash);
        System.out.println(b);
//        System.out.println(b);
    }
}
