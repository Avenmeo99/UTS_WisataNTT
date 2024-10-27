package com.example.wisatantt.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NavigateBefore
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.CircleShape // Tambahkan import untuk CircleShape
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.wisatantt.R


// Data class untuk menyimpan informasi kabupaten
data class Kabupaten(val nama: String, val gambar: Int)

// data kabupaten
val daftarKabupaten = listOf(
    Kabupaten("Kabupaten Manggari Labuan Bajo", R.drawable.kotalabuanbajo),
    Kabupaten("Kabupaten Ngada", R.drawable.kotabajawa),
    Kabupaten("Kabupaten Sumba", R.drawable.kotasumba),
    Kabupaten("Kabupaten Ende", R.drawable.kotaende),
    // ... tambahkan data kabupaten lainnya
)

// Data class untuk menyimpan informasi wisata terbaik
data class WisataTerbaik(val nama: String, val gambar: Int, val kabupaten: String)

// data wisata terbaik
val daftarWisataTerbaik = listOf(
    WisataTerbaik("Pulau Komodo", R.drawable.bajo5, "Kabupaten Manggari Labuan Bajo"),
    WisataTerbaik("Pink Beach", R.drawable.bajo4, "Kabupaten Manggari Labuan Bajo"),
    WisataTerbaik("Taman Bung Karno", R.drawable.ende1, "Kabupaten Ende"),
    WisataTerbaik("17 Pulau Riung", R.drawable.bajawa1, "Kabupaten Ngada"),
    WisataTerbaik("Air Terjun wolobolo", R.drawable.bajawa2, "Kabupaten Ngada"),
    WisataTerbaik("Rumah Adat Bena", R.drawable.bajawa6, "Kabupaten Ngada"),
    WisataTerbaik("Sawa Kali", R.drawable.sumba1, "Kabupaten Sumba"),
    WisataTerbaik("Waikelo Sawah", R.drawable.sumba2, "Kabupaten Sumba"),
    WisataTerbaik("Pantai Bawana", R.drawable.sumba3, "Kabupaten Sumba"),
    WisataTerbaik("Pantai Watu Parunu", R.drawable.sumba4, "Kabupaten Sumba"),
    WisataTerbaik("Bukit Wairinding", R.drawable.sumba5, "Kabupaten Sumba"),
    WisataTerbaik("Danau Tiga Warna", R.drawable.gambar2, "Kabupaten Ende"),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "WISATA NUSA TENGGARA TIMUR",
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color(0xFF008000) // Warna hijau
                )
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            // Background image dan tulisan ucapan selamat
            Image(
                painter = painterResource(id = R.drawable.ntt_bg),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )

            // Tulisan ucapan selamat di dalam background
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Color.Black.copy(alpha = 0.5f)) // Background transparan
                    .padding(16.dp),
                contentAlignment = Alignment.Center // Pusatkan teks
            ) {
                Text(
                    text = "Selamat Datang di APP Kami",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 16.sp // Ubah ukuran font
                    ),
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }

            // Content di bawah background
            Column(
                modifier = Modifier
                    .padding(top = 200.dp) // Memberi ruang di atas untuk background
                    .fillMaxSize()
            ) {
                // Section Wisata Terbaik
                WisataTerbaikSection()

                // Section Kabupaten
                KabupatenSection()


            }
        }
    }
}

// Composable function untuk section Kabupaten
@Composable
fun KabupatenSection() {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .padding(top = 16.dp)
    ) {
        Text(
            text = "Daftar Kabupaten",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2)
        ) {
            items(daftarKabupaten) { kabupaten ->
                KabupatenItem(kabupaten) { clickedKabupaten ->
                    // Navigasi ke halaman kabupaten saat gambar diklik
                    // (Anda perlu mengimplementasikan navigasi ini)
                    println("Kabupaten yang diklik: ${clickedKabupaten.nama}")
                }
            }
        }
    }
}

// Composable function untuk section Wisata Terbaik
@Composable
fun WisataTerbaikSection() {
    var currentWisataIndex by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Pilihan Wisata Terbaik",
            style = MaterialTheme.typography.titleMedium, // Menggunakan titleMedium
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        val currentWisata = daftarWisataTerbaik[currentWisataIndex % daftarWisataTerbaik.size]

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(16.dp)) // Melengkungkan sudut gambar
                .clickable {
                    // Mencari kabupaten yang sesuai dengan wisata yang diklik
                    val kabupatenTerkait = daftarKabupaten.find { it.nama == currentWisata.kabupaten }

                    // Memanggil onKabupatenClick jika kabupaten ditemukan
                    kabupatenTerkait?.let {  }
                }
        ) {
            Image(
                painter = painterResource(id = currentWisata.gambar),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // Icon preview dan next di dalam gambar
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { currentWisataIndex = (currentWisataIndex - 1 + daftarWisataTerbaik.size) % daftarWisataTerbaik.size },
                    modifier = Modifier
                        .background(Color.White.copy(alpha = 0.5f), shape = CircleShape)
                        .size(32.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.NavigateBefore,
                        contentDescription = "Previous",
                        tint = Color.Black
                    )
                }

                IconButton(
                    onClick = { currentWisataIndex = (currentWisataIndex + 1) % daftarWisataTerbaik.size },
                    modifier = Modifier
                        .background(Color.White.copy(alpha = 0.5f), shape = CircleShape)
                        .size(32.dp)
                ) {
                    Icon(imageVector = Icons.Filled.NavigateNext, contentDescription = "Next", tint = Color.Black)
                }
            }
        }

        Text(
            text = currentWisata.nama,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 8.dp)
        )

        Text(
            text = currentWisata.kabupaten,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

// Composable function untuk menampilkan satu item kabupaten
@Composable
fun KabupatenItem(kabupaten: Kabupaten, onKabupatenClick: (Kabupaten) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = kabupaten.gambar),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .clip(RoundedCornerShape(16.dp)) // Melengkungkan sudut gambar
                .clickable { onKabupatenClick(kabupaten) },
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = kabupaten.nama,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold
        )
    }
}

// (fungsi lainnya) akan di lanjutkan

/*Aplikasi Wisata NTT Aplikasi Anda adalah aplikasi sederhana yang menampilkan informasi tentang wisata di Nusa Tenggara Timur (NTT). Aplikasi ini memiliki dua bagian utama:
Daftar Kabupaten: Menampilkan daftar kabupaten di NTT dengan gambar dan nama kabupaten.
Pilihan Wisata Terbaik: Menampilkan pilihan wisata terbaik di NTT dengan gambar, nama, dan kabupaten wisata tersebut. Cara Kerja Aplikasi
Saat aplikasi dijalankan, akan ditampilkan layar utama (HomeScreen) dengan:
Background gambar NTT dan tulisan "Selamat Datang di APP Kami".
Section "Daftar Kabupaten" yang menampilkan daftar kabupaten dalam grid dua kolom.
Section "Pilihan Wisata Terbaik" yang menampilkan satu wisata terbaik dengan navigasi "next" dan "previous".
Saat pengguna mengklik gambar kabupaten di section "Daftar Kabupaten", aplikasi akan menavigasi ke halaman detail kabupaten (Anda perlu mengimplementasikan navigasi ini).
Saat pengguna mengklik gambar wisata terbaik di section "Pilihan Wisata Terbaik", aplikasi akan menavigasi ke halaman detail wisata terbaik (Anda perlu mengimplementasikan navigasi ini).
Pengguna dapat mengganti wisata terbaik yang ditampilkan di section "Pilihan Wisata Terbaik" dengan menggunakan tombol "next" dan "previous". Fitur Utama
Menampilkan daftar kabupaten di NTT.
Menampilkan pilihan wisata terbaik di NTT.
Navigasi ke halaman detail kabupaten dan wisata terbaik (perlu diimplementasikan).
Navigasi "next" dan "previous" untuk pilihan wisata terbaik. Kelebihan Aplikasi
Sederhana dan mudah digunakan.
Menampilkan informasi penting tentang wisata di NTT.
Tampilan yang menarik dengan gambar dan judul. Kekurangan Aplikasi
Navigasi ke halaman detail kabupaten dan wisata terbaik belum diimplementasikan.
Fitur masih terbatas, dapat dikembangkan dengan menambahkan informasi lebih detail tentang kabupaten dan wisata terbaik, seperti deskripsi, lokasi, dan fasilitas. Kesimpulan Aplikasi Anda adalah aplikasi sederhana yang dapat menjadi dasar untuk mengembangkan aplikasi wisata NTT yang lebih lengkap dan informatif.*/


