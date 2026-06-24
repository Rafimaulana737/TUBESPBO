package Reservasi._Co_Working.demo.util;

import Reservasi._Co_Working.demo.model.User;

public final class AuthHelper {

    public static final String SESSION_USER = "currentUser";

    private AuthHelper() {
    }

    public static User getCurrentUser(jakarta.servlet.http.HttpSession session) {
        Object user = session.getAttribute(SESSION_USER);
        return user instanceof User ? (User) user : null;
    }

    public static boolean hasRole(User user, String... roles) {
        if (user == null || user.getRole() == null) {
            return false;
        }
        String userRole = user.getRole().trim().toLowerCase();
        for (String role : roles) {
            if (userRole.equals(role.trim().toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public static boolean isAdmin(User user) {
        return hasRole(user, "admin");
    }

    public static boolean isMahasiswa(User user) {
        return hasRole(user, "mahasiswa");
    }

    public static boolean isPetugas(User user) {
        return hasRole(user, "petugas");
    }
}
