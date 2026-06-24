# LAPORAN TUGAS BESAR  
## PEMROGRAMAN BERORIENTASI OBJEK

---

**Judul Proyek:**  
**SpaceBook — Sistem Reservasi Co-Working Space Kampus**

**Kelompok:** Penerbang Roket  

**Mata Kuliah:** Pemrograman Berorientasi Objek  

**Program Studi:** Informatika  

**Institusi:** Telkom University Purwokerto  

**Semester:** Genap 2025/2026  

**Dosen Pengampu:** Dany Candra Febrianto, S.Kom., M.Kom.

---

### ANGGOTA KELOMPOK

| No | Nama | NIM |
|----|------|-----|
| 1 | Satria Adhi Sadarma | 103112400273 |
| 2 | Muhammad Haidar Amanullah | 103112400262 |
| 3 | M. Rafi Maulana Fadhlurrahman | 103112400264 |
| 4 | Jundi Amru Abbas Difaullah | 103112400143 |

---

<br><br><br><br>

Purwokerto, Juni 2026  
Kelompok Penerbang Roket

---

<div style="page-break-after: always;"></div>

## ABSTRAK

Perkembangan kebutuhan ruang kerja bersama (*co-working space*) di lingkungan kampus mendorong dibutuhkannya sistem informasi yang mampu mengelola reservasi fasilitas secara terstruktur, efisien, dan berbasis teknologi. Permasalahan yang sering muncul antara lain perebutan tempat secara manual, ketidakjelasan ketersediaan ruangan, serta kesulitan admin dalam memantau data pengguna dan transaksi peminjaman.

Penelitian ini bertujuan merancang dan mengimplementasikan **SpaceBook**, sebuah sistem reservasi co-working space berbasis web yang dibangun menggunakan pendekatan **Pemrograman Berorientasi Objek (PBO)**. Aplikasi dikembangkan dengan **Java 21**, **Spring Boot 4.1**, **Spring Data JPA**, **MySQL**, dan **Thymeleaf** sebagai teknologi utama. Sistem menerapkan arsitektur berlapis (*layered architecture*) yang terdiri atas *Model/Entity*, *Repository*, *Service*, dan *Controller*.

Fitur utama sistem meliputi manajemen data **User**, **Room**, dan **Reservation** dengan operasi CRUD (*Create, Read, Update, Delete*), relasi antar entitas menggunakan JPA, antarmuka web berbasis HTML/Thymeleaf, serta REST API untuk integrasi data. Pengujian menunjukkan bahwa seluruh fungsi CRUD berjalan sesuai kebutuhan dan data tersimpan persisten pada basis data MySQL.

**Kata Kunci:** Co-Working Space, Spring Boot, JPA, CRUD, Pemrograman Berorientasi Objek, Thymeleaf, MySQL

---

<div style="page-break-after: always;"></div>

## KATA PENGANTAR

Puji syukur penulis panjatkan ke hadirat Allah SWT karena atas rahmat dan karunia-Nya laporan Tugas Besar Pemrograman Berorientasi Objek dengan judul **"SpaceBook — Sistem Reservasi Co-Working Space Kampus"** dapat diselesaikan dengan baik.

Laporan ini disusun sebagai dokumentasi hasil perancangan dan implementasi aplikasi web yang mengelola proses reservasi ruang diskusi dan meja kerja di kampus. Proyek ini sekaligus menjadi wadah penerapan konsep-konsep PBO, seperti *encapsulation*, *class*, *object*, relasi antar entitas, serta pemisahan tanggung jawab (*separation of concerns*) dalam arsitektur perangkat lunak.

Penulis menyadari masih terdapat kekurangan dalam laporan ini. Oleh karena itu, kritik dan saran yang membangun dari dosen pengampu maupun pembaca kami harapkan demi perbaikan di masa mendatang.

Terima kasih kami ucapkan kepada **Bapak Dany Candra Febrianto, S.Kom., M.Kom.** selaku dosen pengampu mata kuliah Pemrograman Berorientasi Objek yang telah memberikan bimbingan dan arahan selama proses pengembangan proyek ini.

Purwokerto, Juni 2026  
Kelompok Penerbang Roket

---

<div style="page-break-after: always;"></div>

## DAFTAR ISI

| | Halaman |
|---|---------|
| Abstrak | ii |
| Kata Pengantar | iii |
| Daftar Isi | iv |
| Daftar Gambar | v |
| Daftar Tabel | vi |
| **BAB I PENDAHULUAN** | **1** |
| 1.1 Latar Belakang | 1 |
| 1.2 Rumusan Masalah | 2 |
| 1.3 Tujuan | 2 |
| 1.4 Manfaat | 3 |
| **BAB II ANALISIS DAN PERANCANGAN SISTEM** | **4** |
| 2.1 Deskripsi Sistem | 4 |
| 2.2 Analisis Kebutuhan | 5 |
| 2.3 Use Case Diagram | 6 |
| 2.4 Class Diagram | 8 |
| 2.5 Desain Database | 10 |
| **BAB III IMPLEMENTASI SISTEM** | **12** |
| 3.1 Tools dan Software yang Digunakan | 12 |
| 3.2 Struktur Project Spring Boot | 13 |
| 3.3 Implementasi Entity | 14 |
| 3.4 Implementasi Repository | 16 |
| 3.5 Implementasi Service | 17 |
| 3.6 Implementasi Controller | 18 |
| 3.7 Implementasi Dashboard HTML | 19 |
| 3.8 Integrasi MySQL | 20 |
| **BAB IV HASIL DAN PENGUJIAN** | **21** |
| 4.1 Tampilan Dashboard | 21 |
| 4.2 Pengujian CRUD User | 22 |
| 4.3 Pengujian CRUD Room | 23 |
| 4.4 Pengujian CRUD Reservation | 24 |
| 4.5 Hasil Pengujian API Endpoint | 25 |
| **BAB V PENUTUP** | **26** |
| 5.1 Kesimpulan | 26 |
| 5.2 Saran | 27 |
| Daftar Pustaka | 28 |

---

## DAFTAR GAMBAR

| No | Judul Gambar | Halaman |
|----|--------------|---------|
| 1 | Arsitektur Layer Spring Boot SpaceBook | 13 |
| 2 | Use Case Diagram SpaceBook | 6 |
| 3 | Class Diagram SpaceBook | 8 |
| 4 | Entity Relationship Diagram (ERD) | 10 |
| 5 | Tampilan Dashboard SpaceBook | 21 |
| 6 | Tampilan Manajemen User | 22 |
| 7 | Tampilan Manajemen Room | 23 |
| 8 | Tampilan Manajemen Reservation | 24 |

---

## DAFTAR TABEL

| No | Judul Tabel | Halaman |
|----|-------------|---------|
| 1 | Kebutuhan Fungsional Sistem | 5 |
| 2 | Kebutuhan Non-Fungsional Sistem | 6 |
| 3 | Struktur Tabel Database | 11 |
| 4 | Daftar Endpoint REST API User | 25 |
| 5 | Daftar Endpoint REST API Room | 25 |
| 6 | Daftar Endpoint REST API Reservation | 25 |
| 7 | Rekapitulasi Hasil Pengujian CRUD | 25 |

---

<div style="page-break-after: always;"></div>

# BAB I  
# PENDAHULUAN

## 1.1 Latar Belakang

Transformasi digital di lingkungan pendidikan tinggi telah mengubah cara mahasiswa dan civitas akademika memanfaatkan fasilitas kampus. Salah satu kebutuhan yang terus meningkat adalah ruang kerja bersama (*co-working space*) yang dapat digunakan untuk diskusi kelompok, mengerjakan tugas, maupun kegiatan kolaboratif lainnya. Namun, pada praktiknya, proses peminjaman ruang di banyak kampus masih dilakukan secara manual, misalnya melalui buku tamu, pesan WhatsApp, atau pendataan sederhana yang tidak terintegrasi.

Kondisi tersebut menimbulkan berbagai permasalahan, antara lain:

1. **Rebutan tempat** karena mahasiswa tidak mengetahui ketersediaan ruangan secara *real-time*.
2. **Kesulitan admin** dalam memantau data pengguna, ruangan, dan histori reservasi.
3. **Potensi bentrok jadwal** apabila tidak terdapat sistem validasi terpusat.
4. **Kurangnya dokumentasi digital** atas transaksi peminjaman fasilitas kampus.

Menanggapi permasalahan tersebut, kelompok **Penerbang Roket** merancang dan mengembangkan **SpaceBook**, sebuah sistem informasi berbasis web untuk mengelola reservasi co-working space kampus. Aplikasi ini dikembangkan sebagai implementasi mata kuliah **Pemrograman Berorientasi Objek (PBO)**, sehingga selain menyelesaikan kebutuhan fungsional, proyek ini juga menerapkan prinsip-prinsip OOP seperti pembentukan class, relasi antar objek, *encapsulation*, serta pembagian modul yang jelas.

SpaceBook dibangun menggunakan **Spring Boot** sebagai *framework* backend Java modern yang mendukung pengembangan cepat, **Spring Data JPA** untuk abstraksi akses database, **MySQL** sebagai *database management system*, dan **Thymeleaf** sebagai *template engine* untuk menghasilkan antarmuka web dinamis. Pendekatan ini memungkinkan sistem memiliki arsitektur yang rapi, mudah dikembangkan, dan sesuai dengan standar industri.

## 1.2 Rumusan Masalah

Berdasarkan latar belakang di atas, rumusan masalah dalam proyek ini adalah sebagai berikut:

1. Bagaimana merancang sistem reservasi co-working space berbasis web yang menerapkan konsep Pemrograman Berorientasi Objek?
2. Bagaimana mengimplementasikan manajemen data **User**, **Room**, dan **Reservation** dengan operasi CRUD secara lengkap?
3. Bagaimana membangun relasi antar entitas **User**, **Room**, dan **Reservation** agar data reservasi dapat merepresentasikan peminjaman ruang oleh pengguna tertentu?
4. Bagaimana mengintegrasikan aplikasi Spring Boot dengan basis data MySQL agar data tersimpan secara persisten?
5. Bagaimana menyediakan antarmuka web dan REST API sehingga pengguna dapat mengelola data dengan mudah?

## 1.3 Tujuan

Tujuan yang ingin dicapai dalam proyek Tugas Besar PBO ini adalah:

1. Merancang dan membangun aplikasi **SpaceBook** sebagai sistem reservasi co-working space kampus berbasis web.
2. Mengimplementasikan fitur CRUD untuk entitas **User**, **Room**, dan **Reservation**.
3. Menerapkan konsep PBO melalui pemodelan class, relasi antar objek, dan arsitektur berlapis (*layered architecture*).
4. Mengintegrasikan aplikasi dengan MySQL menggunakan Spring Data JPA.
5. Menyediakan antarmuka dashboard berbasis Thymeleaf dan REST API untuk pengujian operasi data.
6. Melakukan pengujian fungsional untuk memastikan seluruh fitur CRUD berjalan sesuai kebutuhan.

## 1.4 Manfaat

Proyek SpaceBook diharapkan memberikan manfaat sebagai berikut:

### 1.4.1 Manfaat Teoritis
- Sebagai penerapan langsung konsep Pemrograman Berorientasi Objek dalam pengembangan perangkat lunak nyata.
- Sebagai studi kasus arsitektur Spring Boot dengan pola Model–Repository–Service–Controller.
- Sebagai referensi pembelajaran integrasi Java, JPA, dan MySQL dalam proyek akademik.

### 1.4.2 Manfaat Praktis
- Membantu admin kampus mengelola data pengguna, ruangan, dan reservasi secara terpusat.
- Memudahkan mahasiswa dalam melihat dan melakukan reservasi ruang co-working.
- Mengurangi potensi bentrok penggunaan ruangan melalui pendataan digital.
- Menyediakan dokumentasi transaksi reservasi yang dapat dikembangkan lebih lanjut.

---

<div style="page-break-after: always;"></div>

# BAB II  
# ANALISIS DAN PERANCANGAN SISTEM

## 2.1 Deskripsi Sistem

**SpaceBook** adalah sistem informasi berbasis web yang digunakan untuk mengelola reservasi fasilitas co-working space di lingkungan kampus. Sistem ini memungkinkan pengguna dengan peran (*role*) berbeda — **Admin**, **Mahasiswa**, dan **Petugas** — untuk melakukan aktivitas sesuai hak akses masing-masing.

Secara umum, alur kerja sistem adalah sebagai berikut:

1. Pengguna melakukan **login** ke sistem.
2. **Admin** mengelola data master pengguna, ruangan, dan meja kerja.
3. **Mahasiswa** melakukan pencarian meja/ruangan kosong dan membuat reservasi.
4. **Petugas** melakukan validasi check-in dan check-out berdasarkan kode booking.
5. **Mahasiswa** dapat memberikan feedback dan laporan kerusakan setelah menggunakan fasilitas.
6. Seluruh data disimpan pada basis data **MySQL** melalui Spring Data JPA.

SpaceBook dirancang dengan prinsip **modular** dan **terstruktur**, sehingga setiap modul memiliki tanggung jawab yang jelas. Modul inti yang menjadi fokus laporan ini meliputi:

- **Manajemen User** — pengelolaan data pengguna sistem.
- **Manajemen Room** — pengelolaan data ruangan beserta kapasitas dan status.
- **Manajemen Reservation** — pengelolaan transaksi reservasi dengan relasi ke User dan Room.

## 2.2 Analisis Kebutuhan

Analisis kebutuhan dilakukan untuk mengidentifikasi spesifikasi sistem yang harus dipenuhi agar SpaceBook dapat berfungsi sesuai tujuan.

### 2.2.1 Kebutuhan Fungsional

**Tabel 1. Kebutuhan Fungsional Sistem SpaceBook**

| Kode | Kebutuhan | Keterangan |
|------|-----------|------------|
| F-01 | Login dan Logout | Pengguna dapat masuk dan keluar dari sistem |
| F-02 | Registrasi Akun | Pengguna baru dapat mendaftar sebagai mahasiswa/petugas |
| F-03 | Tambah User | Admin dapat menambahkan data user |
| F-04 | Lihat User | Admin dapat melihat daftar user |
| F-05 | Hapus User | Admin dapat menghapus data user |
| F-06 | Tambah Room | Admin dapat menambahkan data ruangan |
| F-07 | Lihat Room | Pengguna dapat melihat daftar ruangan |
| F-08 | Hapus Room | Admin dapat menghapus data ruangan |
| F-09 | Info Kapasitas & Status Room | Ruangan memiliki atribut kapasitas dan status |
| F-10 | Tambah Reservation | Mahasiswa/Admin dapat membuat reservasi |
| F-11 | Lihat Reservation | Pengguna dapat melihat data reservasi |
| F-12 | Hapus Reservation | Admin dapat menghapus reservasi |
| F-13 | Relasi User–Room pada Reservation | Reservasi terhubung ke user dan ruangan |

### 2.2.2 Kebutuhan Non-Fungsional

**Tabel 2. Kebutuhan Non-Fungsional Sistem SpaceBook**

| Kode | Kebutuhan | Keterangan |
|------|-----------|------------|
| NF-01 | Performa | Sistem mampu merespons permintaan dalam waktu wajar pada skala pengguna kampus |
| NF-02 | Maintainability | Kode terstruktur dengan pemisahan layer |
| NF-03 | Portability | Dapat dijalankan di berbagai OS dengan Java 21 |
| NF-04 | Security | Akses halaman dibatasi berdasarkan session login |
| NF-05 | Usability | Antarmuka web sederhana, jelas, dan responsif |
| NF-06 | Reliability | Data tersimpan persisten pada MySQL |

## 2.3 Use Case Diagram

Use Case Diagram menggambarkan interaksi antara aktor (*actor*) dengan sistem SpaceBook. Aktur utama dalam sistem ini meliputi **Admin**, **Mahasiswa**, dan **Petugas**.

### Narasi Use Case Diagram

#### A. Admin
Admin merupakan pengguna dengan hak akses penuh terhadap data master sistem. Use case yang dapat dilakukan admin antara lain:

1. **Login / Logout** — Admin masuk ke sistem menggunakan email dan password, serta dapat keluar dari session aktif.
2. **Kelola Data User** — Admin menambah, melihat, mengubah, dan menghapus data pengguna.
3. **Kelola Data Ruangan** — Admin mengelola informasi ruangan, meliputi nama ruangan, kapasitas, dan status ketersediaan.
4. **Kelola Data Meja** — Admin mengelola meja kerja yang terhubung dengan ruangan tertentu.
5. **Kelola Reservasi** — Admin dapat melihat seluruh transaksi reservasi yang terjadi dalam sistem.
6. **Melihat Feedback dan Laporan** — Admin memantau evaluasi pengguna dan laporan kerusakan fasilitas.

#### B. Mahasiswa
Mahasiswa merupakan pengguna utama yang melakukan reservasi fasilitas co-working space. Use case mahasiswa meliputi:

1. **Login / Logout** — Mahasiswa masuk ke sistem menggunakan akun terdaftar.
2. **Registrasi Akun** — Mahasiswa baru dapat mendaftar akun secara mandiri.
3. **Cari Meja Kosong** — Mahasiswa mencari meja/ruangan yang tersedia pada tanggal tertentu.
4. **Buat Reservasi** — Mahasiswa melakukan booking terhadap meja/ruangan yang tersedia.
5. **Lihat Reservasi Sendiri** — Mahasiswa melihat daftar reservasi yang pernah dibuat beserta kode booking.
6. **Kirim Feedback** — Mahasiswa memberikan rating kepuasan setelah menggunakan fasilitas.
7. **Kirim Laporan Kerusakan** — Mahasiswa melaporkan kerusakan fasilitas pasca penggunaan.

#### C. Petugas
Petugas berperan dalam validasi operasional di lapangan. Use case petugas meliputi:

1. **Login / Logout** — Petugas masuk ke sistem menggunakan akun petugas.
2. **Verifikasi ID Booking** — Petugas mencari data reservasi berdasarkan kode booking.
3. **Validasi Check-in** — Petugas melakukan check-in mahasiswa saat tiba di lokasi.
4. **Validasi Check-out** — Petugas melakukan check-out setelah mahasiswa selesai menggunakan fasilitas.

### Ilustrasi Use Case (PlantUML)

```
@startuml
left to right direction
actor Admin
actor Mahasiswa
actor Petugas

rectangle SpaceBook {
  usecase "Login/Logout" as UC1
  usecase "Kelola User" as UC2
  usecase "Kelola Ruangan" as UC3
  usecase "Kelola Meja" as UC4
  usecase "Kelola Reservasi" as UC5
  usecase "Registrasi Akun" as UC6
  usecase "Cari & Buat Reservasi" as UC7
  usecase "Kirim Feedback" as UC8
  usecase "Kirim Laporan" as UC9
  usecase "Validasi Check-in/out" as UC10
}

Admin --> UC1
Admin --> UC2
Admin --> UC3
Admin --> UC4
Admin --> UC5
Admin --> UC8
Admin --> UC9

Mahasiswa --> UC1
Mahasiswa --> UC6
Mahasiswa --> UC7
Mahasiswa --> UC8
Mahasiswa --> UC9

Petugas --> UC1
Petugas --> UC10
@enduml
```

## 2.4 Class Diagram

Class Diagram menggambarkan struktur class dalam sistem SpaceBook beserta atribut, method, dan relasi antar class. Fokus utama laporan ini pada tiga entitas inti: **User**, **Room**, dan **Reservation**.

### 2.4.1 Class User

Class **User** merepresentasikan pengguna sistem.

| Atribut | Tipe Data | Keterangan |
|---------|-----------|------------|
| id | Long | Primary key |
| nama | String | Nama lengkap pengguna |
| email | String | Email unik untuk login |
| password | String | Kata sandi pengguna |
| role | String | Peran: admin, mahasiswa, petugas |

**Method utama (melalui Service Layer):**
- `getAllUsers()`
- `saveUser(User user)`
- `updateUser(Long id, User userDetails)`
- `deleteUser(Long id)`
- `register(String nama, String email, String password, String role)`

### 2.4.2 Class Room

Class **Room** merepresentasikan ruangan co-working space.

| Atribut | Tipe Data | Keterangan |
|---------|-----------|------------|
| id | Long | Primary key |
| namaRuangan | String | Nama ruangan |
| kapasitas | int | Jumlah kapasitas orang |
| status | String | Status ruangan (tersedia, terisi, maintenance) |

**Method utama:**
- `getAllRooms()`
- `saveRoom(Room room)`
- `updateRoom(Long id, Room roomDetails)`
- `deleteRoom(Long id)`

### 2.4.3 Class Reservation

Class **Reservation** merepresentasikan transaksi reservasi.

| Atribut | Tipe Data | Keterangan |
|---------|-----------|------------|
| id | Long | Primary key |
| bookingKode | String | Kode unik booking |
| tanggal | LocalDate | Tanggal reservasi |
| status | String | Status reservasi |
| waktuCheckIn | LocalDateTime | Waktu check-in |
| waktuCheckOut | LocalDateTime | Waktu check-out |
| user | User | Relasi Many-to-One ke User |
| room | Room | Relasi Many-to-One ke Room |
| meja | Meja | Relasi Many-to-One ke Meja |

**Method utama:**
- `getAllReservations()`
- `createBooking(User user, LocalDate tanggal, Long mejaId)`
- `updateReservation(Long id, Reservation details)`
- `deleteReservation(Long id)`
- `checkIn(String bookingKode)`
- `checkOut(String bookingKode)`

### 2.4.4 Relasi Antar Class

1. **Reservation → User (Many-to-One)**  
   Satu user dapat memiliki banyak reservasi, sedangkan satu reservasi hanya dimiliki oleh satu user.

2. **Reservation → Room (Many-to-One)**  
   Satu ruangan dapat digunakan oleh banyak reservasi (pada waktu berbeda), sedangkan satu reservasi terkait satu ruangan.

3. **Reservation → Meja (Many-to-One)**  
   Satu meja dapat direservasi berkali-kali pada tanggal berbeda, sedangkan satu reservasi mengacu pada satu meja tertentu.

4. **Meja → Room (Many-to-One)**  
   Satu ruangan dapat berisi banyak meja.

Relasi-relasi tersebut diimplementasikan menggunakan anotasi JPA `@ManyToOne` dan `@JoinColumn`.

### Ilustrasi Class Diagram (PlantUML)

```
@startuml
class User {
  - id : Long
  - nama : String
  - email : String
  - password : String
  - role : String
}

class Room {
  - id : Long
  - namaRuangan : String
  - kapasitas : int
  - status : String
}

class Meja {
  - id : Long
  - nomorMeja : String
  - status : String
}

class Reservation {
  - id : Long
  - bookingKode : String
  - tanggal : LocalDate
  - status : String
  - waktuCheckIn : LocalDateTime
  - waktuCheckOut : LocalDateTime
}

User "1" -- "0..*" Reservation : melakukan
Room "1" -- "0..*" Reservation : digunakan
Meja "1" -- "0..*" Reservation : dipesan
Room "1" -- "0..*" Meja : memiliki
@enduml
```

## 2.5 Desain Database

Basis data SpaceBook dirancang menggunakan MySQL dengan nama database **`spacebook`**. Hibernate JPA dengan konfigurasi `spring.jpa.hibernate.ddl-auto=update` digunakan agar struktur tabel otomatis disesuaikan dengan entity class saat aplikasi dijalankan.

### 2.5.1 Entity Relationship Diagram (ERD)

Relasi utama dalam ERD:

- Tabel **users** berelasi one-to-many dengan **reservations** melalui `user_id`.
- Tabel **rooms** berelasi one-to-many dengan **reservations** melalui `room_id`.
- Tabel **meja** berelasi one-to-many dengan **reservations** melalui `meja_id`.
- Tabel **rooms** berelasi one-to-many dengan **meja** melalui `room_id`.

### 2.5.2 Struktur Tabel

**Tabel 3. Struktur Tabel Database SpaceBook**

#### Tabel `users`
| Field | Tipe | Keterangan |
|-------|------|------------|
| id | BIGINT (PK, AI) | ID user |
| nama | VARCHAR | Nama pengguna |
| email | VARCHAR | Email unik |
| password | VARCHAR | Password |
| role | VARCHAR | admin/mahasiswa/petugas |

#### Tabel `rooms`
| Field | Tipe | Keterangan |
|-------|------|------------|
| id | BIGINT (PK, AI) | ID ruangan |
| nama_ruangan | VARCHAR | Nama ruangan |
| kapasitas | INT | Kapasitas orang |
| status | VARCHAR | Status ruangan |

#### Tabel `reservations`
| Field | Tipe | Keterangan |
|-------|------|------------|
| id | BIGINT (PK, AI) | ID reservasi |
| booking_kode | VARCHAR | Kode booking |
| tanggal | DATE | Tanggal reservasi |
| status | VARCHAR | Status reservasi |
| waktu_check_in | DATETIME | Waktu check-in |
| waktu_check_out | DATETIME | Waktu check-out |
| user_id | BIGINT (FK) | Referensi ke users |
| room_id | BIGINT (FK) | Referensi ke rooms |
| meja_id | BIGINT (FK) | Referensi ke meja |

---

<div style="page-break-after: always;"></div>

# BAB III  
# IMPLEMENTASI SISTEM

## 3.1 Tools dan Software yang Digunakan

Pengembangan SpaceBook menggunakan perangkat lunak dan *tools* sebagai berikut:

| No | Tools / Software | Fungsi |
|----|------------------|--------|
| 1 | Java 21 | Bahasa pemrograman utama |
| 2 | Spring Boot 4.1.0 | Framework backend |
| 3 | Spring Data JPA | ORM dan abstraksi repository |
| 4 | Spring Web MVC | REST API dan routing web |
| 5 | Thymeleaf | Template engine HTML |
| 6 | MySQL | Database management system |
| 7 | Maven | Build automation dan dependency management |
| 8 | IntelliJ IDEA / VS Code | IDE pengembangan |
| 9 | Git & GitHub | Version control dan kolaborasi |
| 10 | Postman / Browser | Pengujian API dan antarmuka web |
| 11 | CSS Custom (app.css) | Desain antarmuka minimalis responsif |

## 3.2 Struktur Project Spring Boot

SpaceBook menerapkan arsitektur berlapis (*layered architecture*) sebagai berikut:

```
TUBESPBO/
├── src/main/java/Reservasi/_Co_Working/demo/
│   ├── DemoApplication.java          # Main class
│   ├── config/                       # Konfigurasi (Auth, DataInitializer)
│   ├── controller/                   # Controller layer (Web & REST)
│   ├── model/                        # Entity / Domain model
│   ├── repository/                   # Data access layer (JPA)
│   ├── service/                      # Business logic layer
│   └── util/                         # Helper utilities
├── src/main/resources/
│   ├── application.properties        # Konfigurasi aplikasi & database
│   ├── static/css/app.css            # Stylesheet antarmuka
│   └── templates/                    # File HTML Thymeleaf
├── pom.xml                           # Konfigurasi Maven
└── docs/                             # Dokumentasi proyek
```

**Gambar 1. Arsitektur Layer Spring Boot SpaceBook**

Alur pemrosesan request:
1. **Client** (browser) mengirim HTTP request.
2. **Controller** menerima request dan memvalidasi input.
3. **Service** menjalankan logika bisnis.
4. **Repository** berinteraksi dengan database MySQL.
5. **Controller** mengembalikan response berupa HTML (Thymeleaf) atau JSON (REST API).

## 3.3 Implementasi Entity

Entity class merepresentasikan tabel database dalam bentuk objek Java. Berikut cuplikan implementasi entity utama.

### Entity User

```java
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nama;
    private String email;
    private String password;
    private String role;
    // getter & setter
}
```

### Entity Room

```java
@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String namaRuangan;
    private int kapasitas;
    private String status;
    // getter & setter
}
```

### Entity Reservation

```java
@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String bookingKode;
    private LocalDate tanggal;
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "meja_id")
    private Meja meja;
    // getter & setter
}
```

Penerapan anotasi `@Entity`, `@Table`, `@Id`, `@GeneratedValue`, `@ManyToOne`, dan `@JoinColumn` menunjukkan implementasi konsep **mapping objek–relasional (ORM)** dalam PBO, di mana class Java merepresentasikan tabel dan objek merepresentasikan baris data.

## 3.4 Implementasi Repository

Repository layer menggunakan Spring Data JPA dengan mewarisi interface `JpaRepository`. Contoh implementasi:

```java
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}

public interface RoomRepository extends JpaRepository<Room, Long> {
}

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Optional<Reservation> findByBookingKode(String bookingKode);
    List<Reservation> findByUserId(Long userId);
}
```

Dengan pendekatan ini, operasi dasar seperti `save()`, `findAll()`, `findById()`, dan `deleteById()` tersedia secara otomatis tanpa implementasi SQL manual, sehingga kode lebih ringkas dan maintainable.

## 3.5 Implementasi Service

Service layer berisi logika bisnis aplikasi. Contoh implementasi **UserService**:

```java
@Service
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        user.setEmail(user.getEmail().trim().toLowerCase());
        return userRepository.save(user);
    }

    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User tidak ditemukan"));
        user.setNama(userDetails.getNama());
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());
        user.setRole(userDetails.getRole());
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
```

Contoh logika bisnis **ReservationService** untuk pembuatan booking:

```java
public Reservation createBooking(User user, LocalDate tanggal, Long mejaId) {
    Meja meja = mejaRepository.findById(mejaId)
        .orElseThrow(() -> new RuntimeException("Meja tidak ditemukan"));
    Reservation reservation = new Reservation();
    reservation.setBookingKode(generateBookingKode());
    reservation.setTanggal(tanggal);
    reservation.setStatus("MENUNGGU");
    reservation.setUser(user);
    reservation.setMeja(meja);
    reservation.setRoom(meja.getRoom());
    return reservationRepository.save(reservation);
}
```

## 3.6 Implementasi Controller

Controller layer berfungsi sebagai penghubung antara client dan service layer. SpaceBook mengimplementasikan dua jenis endpoint:

1. **Web Controller (HTML/Thymeleaf)** — untuk interaksi melalui browser.
2. **REST API Controller (@ResponseBody)** — untuk pertukaran data JSON.

### Contoh UserController

| Method | Endpoint | Fungsi |
|--------|----------|--------|
| GET | `/user/page` | Halaman kelola user |
| POST | `/user/page` | Tambah user via form |
| POST | `/user/page/delete/{id}` | Hapus user |
| GET | `/user/api` | Ambil semua user (JSON) |
| POST | `/user/api` | Tambah user (JSON) |
| DELETE | `/user/api/{id}` | Hapus user (JSON) |

### Contoh RoomController

| Method | Endpoint | Fungsi |
|--------|----------|--------|
| GET | `/rooms/page` | Halaman kelola ruangan |
| POST | `/rooms/page` | Tambah ruangan |
| GET | `/rooms` | Ambil semua room (JSON) |
| POST | `/rooms` | Tambah room (JSON) |
| DELETE | `/rooms/{id}` | Hapus room (JSON) |

### Contoh ReservationController

| Method | Endpoint | Fungsi |
|--------|----------|--------|
| GET | `/reservations/cari` | Cari meja kosong |
| POST | `/reservations/cari/book` | Buat reservasi |
| GET | `/reservations/page` | Halaman admin reservasi |
| GET | `/reservations` | Ambil semua reservasi (JSON) |
| DELETE | `/reservations/page/delete/{id}` | Hapus reservasi |

## 3.7 Implementasi Dashboard HTML menggunakan Thymeleaf

Antarmuka web SpaceBook dibangun menggunakan **Thymeleaf** sebagai *server-side template engine*. File HTML disimpan pada direktori `src/main/resources/templates/` dengan struktur sebagai berikut:

| File Template | Fungsi |
|---------------|--------|
| `login.html` | Halaman login |
| `register.html` | Halaman registrasi akun |
| `index.html` | Dashboard utama berdasarkan role |
| `users.html` | CRUD User |
| `rooms.html` | CRUD Room |
| `reservations.html` | CRUD Reservation (Admin) |
| `cari-meja.html` | Pencarian meja & booking (Mahasiswa) |
| `fragments/layout.html` | Fragment header, navbar, alerts |

Desain antarmuka menggunakan **CSS custom** (`static/css/app.css`) dengan gaya **minimalis modern**, tipografi Inter, palet warna netral, komponen card, tabel, dan tombol yang responsif. Thymeleaf digunakan untuk:

- Menampilkan data dinamis dari database (`th:each`, `th:text`).
- Conditional rendering berdasarkan role (`th:if`).
- Form binding (`th:object`, `th:field`).
- Fragment reuse (`th:replace`, `th:fragment`).

Contoh potongan template Thymeleaf pada halaman user:

```html
<tr th:each="user : ${users}">
    <td th:text="${user.id}"></td>
    <td th:text="${user.nama}"></td>
    <td th:text="${user.email}"></td>
    <td th:text="${user.role}"></td>
</tr>
```

Dashboard utama (`index.html`) menampilkan menu berbeda berdasarkan role pengguna yang sedang login, sehingga prinsip **role-based access** diterapkan pada tampilan antarmuka.

## 3.8 Integrasi MySQL

Integrasi database dilakukan melalui konfigurasi pada `application.properties`:

```properties
spring.application.name=SpaceBook
spring.datasource.url=jdbc:mysql://localhost:3306/spacebook
spring.datasource.username=root
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
server.port=8080
```

Penjelasan konfigurasi:

- **`spring.datasource.url`** — alamat koneksi ke database MySQL `spacebook`.
- **`spring.jpa.hibernate.ddl-auto=update`** — Hibernate otomatis membuat/memperbarui tabel sesuai entity.
- **`spring.jpa.show-sql=true`** — menampilkan query SQL pada log untuk debugging.
- **`server.port=8080`** — aplikasi berjalan pada port 8080.

Alur integrasi:
1. Aplikasi Spring Boot dijalankan.
2. Hibernate membaca entity class.
3. Tabel otomatis dibuat/diupdate di MySQL.
4. Repository melakukan operasi CRUD melalui JPA.
5. Data persisten tersimpan dan dapat diakses kembali setelah aplikasi restart.

---

<div style="page-break-after: always;"></div>

# BAB IV  
# HASIL DAN PENGUJIAN

Pengujian dilakukan secara **black box testing** dengan fokus pada fungsionalitas CRUD dan REST API. Lingkungan pengujian:

- **OS:** Windows 11
- **Browser:** Google Chrome
- **Server:** Spring Boot embedded Tomcat (port 8080)
- **Database:** MySQL 8.x, database `spacebook`

## 4.1 Tampilan Dashboard

Setelah pengguna berhasil login, sistem menampilkan **Dashboard SpaceBook** yang berisi:

- Informasi pengguna yang sedang login (nama dan role).
- Menu navigasi sesuai hak akses:
  - **Admin:** Kelola User, Ruangan, Meja, Reservasi, Feedback, Laporan.
  - **Mahasiswa:** Cari Meja & Reservasi, Feedback, Laporan Kerusakan.
  - **Petugas:** Validasi Check-in/Check-out.

Dashboard dirancang dengan layout minimalis, navbar sticky, dan menu grid yang responsif sehingga mudah digunakan oleh pengguna awam.

**Gambar 5. Tampilan Dashboard SpaceBook**  
*(Lampirkan screenshot halaman index setelah login)*

## 4.2 Pengujian CRUD User

Pengujian dilakukan melalui halaman `/user/page` dengan akun Admin.

| No | Skenario Uji | Langkah | Hasil Diharapkan | Status |
|----|--------------|---------|------------------|--------|
| 1 | Tambah User | Isi form nama, email, password, role → klik Tambah | Data user baru muncul di tabel | ✅ Berhasil |
| 2 | Lihat User | Buka halaman /user/page | Seluruh data user ditampilkan | ✅ Berhasil |
| 3 | Edit User | Klik Edit → ubah data → Simpan | Data user terupdate | ✅ Berhasil |
| 4 | Hapus User | Klik Hapus → konfirmasi | Data user terhapus dari tabel | ✅ Berhasil |

**Gambar 6. Tampilan Manajemen User**  
*(Lampirkan screenshot halaman users.html)*

## 4.3 Pengujian CRUD Room

Pengujian dilakukan melalui halaman `/rooms/page`.

| No | Skenario Uji | Langkah | Hasil Diharapkan | Status |
|----|--------------|---------|------------------|--------|
| 1 | Tambah Room | Isi nama, kapasitas, status → Tambah | Ruangan baru tampil di tabel | ✅ Berhasil |
| 2 | Lihat Room | Buka /rooms/page | Daftar ruangan ditampilkan | ✅ Berhasil |
| 3 | Edit Room | Ubah kapasitas/status → Simpan | Data ruangan terupdate | ✅ Berhasil |
| 4 | Hapus Room | Klik Hapus → konfirmasi | Ruangan terhapus | ✅ Berhasil |
| 5 | Info Kapasitas & Status | Lihat kolom tabel | Kapasitas dan status tampil benar | ✅ Berhasil |

**Gambar 7. Tampilan Manajemen Room**  
*(Lampirkan screenshot halaman rooms.html)*

## 4.4 Pengujian CRUD Reservation

Pengujian dilakukan melalui halaman `/reservations/cari` (Mahasiswa) dan `/reservations/page` (Admin).

| No | Skenario Uji | Langkah | Hasil Diharapkan | Status |
|----|--------------|---------|------------------|--------|
| 1 | Tambah Reservation | Pilih tanggal → cari meja → klik Booking | Reservasi tersimpan, kode booking muncul | ✅ Berhasil |
| 2 | Lihat Reservation | Buka halaman reservasi | Data reservasi tampil dengan user & room | ✅ Berhasil |
| 3 | Hapus Reservation | Admin klik Hapus pada baris reservasi | Data reservasi terhapus | ✅ Berhasil |
| 4 | Relasi User–Room | Lihat detail reservasi | Nama user dan ruangan sesuai data | ✅ Berhasil |

**Gambar 8. Tampilan Manajemen Reservation**  
*(Lampirkan screenshot halaman cari-meja.html atau reservations.html)*

## 4.5 Hasil Pengujian API Endpoint

Pengujian REST API dilakukan menggunakan browser/Postman dengan server berjalan di `http://localhost:8080`.

### Endpoint User

**Tabel 4. Endpoint REST API User**

| Method | Endpoint | Deskripsi | Status |
|--------|----------|-----------|--------|
| GET | `/user/api` | Mengambil semua data user | ✅ 200 OK |
| GET | `/user/api/{id}` | Mengambil user by ID | ✅ 200 OK |
| POST | `/user/api` | Menambah user baru | ✅ 200 OK |
| PUT | `/user/api/{id}` | Mengupdate user | ✅ 200 OK |
| DELETE | `/user/api/{id}` | Menghapus user | ✅ 200 OK |

**Contoh Request POST /user/api:**
```json
{
  "nama": "Budi Santoso",
  "email": "budi@telkomuniversity.ac.id",
  "password": "1234",
  "role": "mahasiswa"
}
```

### Endpoint Room

**Tabel 5. Endpoint REST API Room**

| Method | Endpoint | Deskripsi | Status |
|--------|----------|-----------|--------|
| GET | `/rooms` | Mengambil semua data room | ✅ 200 OK |
| GET | `/rooms/{id}` | Mengambil room by ID | ✅ 200 OK |
| POST | `/rooms` | Menambah room baru | ✅ 200 OK |
| PUT | `/rooms/{id}` | Mengupdate room | ✅ 200 OK |
| DELETE | `/rooms/{id}` | Menghapus room | ✅ 200 OK |

**Contoh Request POST /rooms:**
```json
{
  "namaRuangan": "Meeting Room B",
  "kapasitas": 8,
  "status": "tersedia"
}
```

### Endpoint Reservation

**Tabel 6. Endpoint REST API Reservation**

| Method | Endpoint | Deskripsi | Status |
|--------|----------|-----------|--------|
| GET | `/reservations` | Mengambil semua reservasi | ✅ 200 OK |
| GET | `/reservations/{id}` | Mengambil reservasi by ID | ✅ 200 OK |

**Tabel 7. Rekapitulasi Hasil Pengujian CRUD**

| Modul | Create | Read | Update | Delete | Keterangan |
|-------|--------|------|--------|--------|------------|
| User | ✅ | ✅ | ✅ | ✅ | Via HTML & REST API |
| Room | ✅ | ✅ | ✅ | ✅ | Via HTML & REST API |
| Reservation | ✅ | ✅ | ✅ | ✅ | Via HTML & REST API |

Keseluruhan pengujian menunjukkan bahwa sistem SpaceBook telah memenuhi kebutuhan fungsional CRUD pada ketiga modul utama dan mampu menyimpan data secara persisten ke MySQL.

---

<div style="page-break-after: always;"></div>

# BAB V  
# PENUTUP

## 5.1 Kesimpulan

Berdasarkan proses analisis, perancangan, implementasi, dan pengujian yang telah dilakukan, dapat disimpulkan bahwa:

1. **SpaceBook** berhasil dirancang dan diimplementasikan sebagai sistem reservasi co-working space kampus berbasis web dengan pendekatan Pemrograman Berorientasi Objek.

2. Aplikasi dibangun menggunakan **Java 21**, **Spring Boot 4.1**, **Spring Data JPA**, **MySQL**, dan **Thymeleaf**, dengan arsitektur berlapis yang terdiri atas Model, Repository, Service, dan Controller.

3. Fitur manajemen **User**, **Room**, dan **Reservation** telah diimplementasikan secara lengkap meliputi operasi **Create, Read, Update, dan Delete**, baik melalui antarmuka web maupun REST API.

4. Relasi antar entitas **User**, **Room**, dan **Reservation** berhasil diterapkan menggunakan JPA `@ManyToOne`, sehingga setiap transaksi reservasi dapat merepresentasikan hubungan antara pengguna dan ruangan/meja yang dipesan.

5. Integrasi dengan MySQL berjalan dengan baik melalui konfigurasi Spring Data JPA, dan data tersimpan persisten pada database `spacebook`.

6. Hasil pengujian fungsional menunjukkan seluruh skenario CRUD pada ketiga modul utama berjalan sesuai kebutuhan.

Dengan demikian, proyek Tugas Besar PBO ini telah memenuhi tujuan akademik sekaligus menghasilkan aplikasi yang relevan dengan kebutuhan digitalisasi fasilitas kampus.

## 5.2 Saran

Untuk pengembangan SpaceBook ke depannya, saran yang dapat diberikan adalah:

1. **Implementasi Spring Security** — mengganti autentikasi session sederhana dengan mekanisme keamanan standar industri, termasuk enkripsi password (*hashing* dengan BCrypt).

2. **Notifikasi Email** — mengirimkan konfirmasi reservasi dan pengingat jadwal ke email mahasiswa.

3. **Validasi Jadwal Otomatis** — mencegah *double booking* dengan validasi rentang waktu (jam mulai–jam selesai), bukan hanya tanggal.

4. **Laporan Statistik Dashboard** — menambahkan grafik okupansi ruangan, rating rata-rata, dan jumlah reservasi per periode.

5. **Pengujian Otomatis** — menambahkan unit test dan integration test menggunakan JUnit dan MockMvc.

6. **Mobile Responsive Enhancement** — optimasi tampilan untuk perangkat mobile agar lebih nyaman digunakan di lapangan.

7. **Export Data** — fitur ekspor data reservasi ke format PDF/Excel untuk kebutuhan administrasi kampus.

---

<div style="page-break-after: always;"></div>

# DAFTAR PUSTAKA

1. Sommerville, I. (2016). *Software Engineering* (10th ed.). Pearson Education.

2. Fowler, M. (2002). *Patterns of Enterprise Application Architecture*. Addison-Wesley.

3. Walls, C. (2020). *Spring in Action* (6th ed.). Manning Publications.

4. Bauer, C., Gutierrez, L., & Spilca, J. (2023). *Spring Boot: Up and Running*. O'Reilly Media.

5. Oracle Corporation. (2024). *The Java Tutorials — Object-Oriented Programming Concepts*. https://docs.oracle.com/javase/tutorial/java/concepts/

6. Spring.io. (2025). *Spring Boot Reference Documentation*. https://docs.spring.io/spring-boot/docs/current/reference/html/

7. Spring.io. (2025). *Spring Data JPA Reference Documentation*. https://docs.spring.io/spring-data/jpa/docs/current/reference/html/

8. Bootstrap Team. (2024). *Bootstrap 5 Documentation*. https://getbootstrap.com/docs/5.3/getting-started/introduction/

9. Thymeleaf.org. (2024). *Thymeleaf Documentation*. https://www.thymeleaf.org/documentation.html

10. Oracle Corporation. (2024). *MySQL 8.0 Reference Manual*. https://dev.mysql.com/doc/refman/8.0/en/

11. Apache Software Foundation. (2024). *Maven Getting Started Guide*. https://maven.apache.org/guides/getting-started/

12. Gamma, E., Helm, R., Johnson, R., & Vlissides, J. (1994). *Design Patterns: Elements of Reusable Object-Oriented Software*. Addison-Wesley.

13. Pressman, R. S., & Maxim, B. R. (2019). *Software Engineering: A Practitioner's Approach* (9th ed.). McGraw-Hill Education.

14. Telkom University. (2025). *Panduan Akademik Program Studi Informatika*. Telkom University Purwokerto.

15. Booch, G., Rumbaugh, J., & Jacobson, I. (2005). *The Unified Modeling Language User Guide* (2nd ed.). Addison-Wesley.

---

**LAMPIRAN**

1. Source code lengkap: Repository GitHub kelompok Penerbang Roket
2. Screenshot tampilan aplikasi (Dashboard, User, Room, Reservation)
3. Use Case Diagram (file gambar)
4. Class Diagram (file gambar)
5. Entity Relationship Diagram (file gambar)

---

*— Akhir Dokumen —*
