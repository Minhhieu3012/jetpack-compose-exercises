package vn.edu.ut.hieupm9898.basicuicomponents.navigation

// Lớp này định nghĩa tất cả các route trong ứng dụng một cách nhất quán
sealed class Screen(val route: String) {
    object Welcome : Screen("welcome_screen")
    object List : Screen("list_screen")
    object TextDetail : Screen("text_detail_screen")
    object ImageDetail : Screen("image_detail_screen")
    object TextField : Screen("text_field_screen")
    object PasswordField : Screen("password_field_screen")
    object ColumnLayoutDetail : Screen("column_layout_detail_screen")
    object RowLayoutDetail : Screen("row_layout_detail_screen")
}