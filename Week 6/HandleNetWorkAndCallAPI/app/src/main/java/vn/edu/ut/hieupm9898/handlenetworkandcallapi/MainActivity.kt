package vn.edu.ut.hieupm9898.handlenetworkandcallapi

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.gson.annotations.SerializedName
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import coil.compose.rememberAsyncImagePainter
import retrofit2.http.GET
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
        .baseUrl("https://www.google.com/url?sa=i&url=https%3A%2F%2Ftomser.shop%2F&psig=AOvVaw0OfAlvhWoMy3oiEUKn95U6&ust=1761904782715000&source=images&cd=vfe&opi=89978449&ved=0CBEQjRxqFwoTCJiT2vPUy5ADFQAAAAAdAAAAABAE/")
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
    var product by remember { mutableStateOf<Product?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf<String?>(null) }

    // Tu dong load du lieu san pham khi man hinh khoi tao
    LaunchedEffect(Unit) {
        try {
            product = api.getProduct()
        } catch (e: Exception) {
            error = "Lỗi khi tải dữ liệu: ${e.message}"
        } finally {
            isLoading = false
        }
    }

    Scaffold(
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
                            onClick = { /* TODO */ },
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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when {
                isLoading -> {
                    CircularProgressIndicator(
                        // .align() sẽ hoạt động vì 'when' nằm trong 'Box'
                        modifier = Modifier.align(Alignment.Center),
                        color = Color(0xFFFF5252)
                    )
                }

                error != null -> {
                    Text(
                        text = error!!,
                        color = Color.Red,
                        modifier = Modifier
                            .align(Alignment.Center) // .align() cũng sẽ hoạt động
                            .padding(16.dp),
                        textAlign = TextAlign.Center
                    )
                }

                product != null -> {
                    ProductDetail(product!!)
                }
            }
        }
    }
}

@Composable
fun ProductDetail(product: Product) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        // Dung painter cua Coil de load anh tu URL
        Image(
            painter = rememberAsyncImagePainter(product.image),
            contentDescription = "Product image",
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .padding(horizontal = 24.dp),
            contentScale = ContentScale.Fit // Theo kich thuoc anh
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = product.name,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 24.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Giá: ${product.price}đ",
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
                text = product.description,
                fontSize = 10.sp,
                modifier = Modifier.padding(16.dp)
            )
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