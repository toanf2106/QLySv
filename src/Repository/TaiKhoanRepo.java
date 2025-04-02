package Repository;

import DBConnect.DBConnect;
import java.sql.*;

public class TaiKhoanRepo {

    private static Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;

    
    public String getRole(String username, String password) {
    String role = null; // Sử dụng null để xác định khi không có kết quả
    String sql = "SELECT LoaiTK FROM TaiKhoan WHERE Username = ? AND Password = ?";
    
    try {
        con = DBConnect.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, username);
        ps.setString(2, password);
        rs = ps.executeQuery();

        if (rs.next()) { // Nếu có kết quả thì lấy giá trị LoaiTK
            role = rs.getString("LoaiTK");
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    return role; // Nếu không có kết quả, sẽ trả về null
}

}
