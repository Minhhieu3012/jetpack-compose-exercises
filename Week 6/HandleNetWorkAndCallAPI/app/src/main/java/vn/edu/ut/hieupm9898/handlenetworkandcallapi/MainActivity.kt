package vn.edu.ut.hieupm9898.handlenetworkandcallapi

import android.content.pm.LauncherApps
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import vn.edu.ut.hieupm9898.handlenetworkandcallapi.ui.theme.HandleNetWorkAndCallAPITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HandleNetWorkAndCallAPITheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HandleNetWorkAndCallAPIApp()
                }
            }
        }
    }
}

// Data Model để lưu trữ thông tin sản phẩm
data class Product(
    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("price")
    val price: Double,

    // Mapping "desc" tu JSON vao property "description"
    @SerializedName("desc")
    val description: String,

    // Mapping "imgURL" tu JSON vao property "image"
    @SerializedName("imgURL")
    val image: String
)

// API Service de lay danh sach san pham
interface ProductApi {
    @GET("product")
    suspend fun getProduct(): Product // Trả về danh sách sản phẩm
}

// Ham tao doi tuong Retrofit de thuc hien cac yeu cau API
fun createApi(): ProductApi {
    return Retrofit.Builder()
        // URL co so phai ket thuc bang dau "/"
        .baseURL("")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ProductApi::class.java)
}


// UI Composable để hiển thị danh sách sản phẩm
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HandleNetWorkAndCallAPIApp() {

    // Khởi tạo đối tượng API bằng cách gọi hàm createApi()
    val api = remember { createApi() }
    var products by remember { mutableStateOf<Product?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf<String?>(null) }

    // Tu dong load du lieu san pham khi man hinh khoi tao
    LaunchedEffect(Unit) {
        try {
            product = api.getProduct()
        } catch (e: Exception) {
            error = "Loi khi tai du lieu: ${e.message}"
        } finally {
            isLoading = true
        }
    }


    Scaffold (
        topBar = {
            TopAppBar(
                // Chúng ta sẽ không dùng navigationIcon và actions ở đây nữa
                // mà sẽ tự định nghĩa toàn bộ cấu trúc trong title
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            // Thêm padding ngang cho Row trong TopAppBar để nó không dính sát lề
                            .padding(horizontal = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // 1. Navigation Icon ở bên trái
                        IconButton(
                            onClick = { /* TODO */},
                            modifier = Modifier
                                .background(
                                    color = Color(0xFF2196F3),
                                    shape = RoundedCornerShape(16.dp)
                                )
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = Color.White,
                                modifier = Modifier.size(36.dp)
                            )
                        }

                        // 2. Title ở giữa, chiếm hết không gian còn lại
                        Text(
                            text = "Product detail",
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF2196F3),
                            modifier = Modifier.weight(1f), // ĐÂY LÀ ĐIỀU KỲ DIỆU!
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center // Căn chữ vào giữa không gian nó chiếm
                        )

                        // 3. Một Spacer trống bên phải để cân bằng
                        // Kích thước của nó phải bằng IconButton bên trái
                        // IconButton có Icon 36dp và padding bên trong, thường có kích thước chạm tối thiểu là 48x48dp
                        Spacer(modifier = Modifier.size(48.dp))
                    }
                },
                // Bỏ trống navigationIcon và actions vì đã xử lý ở trên
                navigationIcon = {},
                actions = {},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues) // Chỉ giữ lại paddingValues từ Scaffold (cho TopAppBar)
        ) {
            Image(
                painter = painterResource(R.drawable.product),
                contentDescription = "Product image",
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .size(size = 400.dp)
            )

            Text(
                text = "Giày Nike Nam Nữ Chính Hãng - Nike Air Force 1\n'07 LV8 - Màu Trắng | JapanSport HF2898-100",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 24.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Giá: 4.000.000₫",
                fontSize = 28.sp,
                modifier = Modifier.padding(horizontal = 24.dp),
                fontWeight = FontWeight.Bold,
                color = Color(0xFFD60A0A)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Box(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .background(
                        color = Color(0xFFD5D1D1),
                        shape = RoundedCornerShape(16.dp)
                    )
            ) {
                Text(
                    text = "Với giày chạy bộ, từng gram đều quan trọng. Đó là lý do tại\n sao đế giữa LIGHTSTRIKE PRO mới nhẹ hơn so với phiên\n bản trước. Mút foam đế giữa siêu nhẹ và thoải mái này có\n lớp đệm đàn hồi được thiết kế để hạn chế tiêu hao năng\n lượng. Trong các mẫu giày tập luyện, công nghệ này được\n thiết kế nhằm hỗ trợ cơ bắp của vận động viên để họ có thể phục hồi nhanh hơn giữa các cuộc đua.",
                    fontSize = 10.sp,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HandleNetWorkAndCallAPIAppPreview() {
    HandleNetWorkAndCallAPITheme {
        HandleNetWorkAndCallAPIApp()
    }
}