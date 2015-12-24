# Gomoku-Multiplayer

November 2015

------------------------------------------

## Dibuat oleh :

Muhammad Ridwan (13513008)

Mochammad Try Yulyanto (13513067)

Ahmad Darmawan (13513096)

------------------------------------------

## Cara Pemakaian

Server:
- Buka ServerGomoku.jar
- Masukkan nomor port yang akan digunakan

Client :
- Buka ClientGomoku.jar
- Masukkan "Username", "Alamat Server", dan "Port Server"

Aplikasi ini merupakan variasi dari game Gomoku dengan spesifikasi berikut :
- Game dimainkan oleh 3 atau lebih orang
- Menggunakan arsitektur Client-Server
- Menggunakan TCP sebagai koneksinya
- Memiliki antarmuka

Pada dasarnya game ini menjejerkan 5 batu secara melintang, membujur, maupun menyilang untuk menang.

## Spesifikasi

Rule game:
- Game dapat dimainkan oleh 3 atau lebih orang dengan aturan yang sama dengan aturan asli yaitu turn based.
- Aturan yang digunakan adalah “Free-style gomoku” pada Wikipedia, abaikan variasi lainnya pada Wikipedia selain “Free-style gomoku”

Board:
- Board berukuran grid 20x20
- Setiap player memiliki symbol yang berbeda satu sama lain (boleh X, O, △, atau yang lainnya)
- Tampilan menampilkan board, player yang sedang bermain direpresentasikan oleh nickname nya, nama dari room tempat bermain

GUI:
- Dibebaskan sesuai kreasi sendiri untuk membuat tampilan GUI yang menarik
- Untuk tampilan board, mengacu pada spesifikasi board untuk benda yang harus ditampilkan

Program client harus memiliki GUI, sedangkan program server tidak perlu memiliki GUI (console diperbolehkan).
Semua lojik pemrosesan game dilakukan pada sisi server.

Alur umum program adalah sebagai berikut:
- Memasukkan “nickname” player
- Player dapat membuat room baru atau join ke dalam room yang sudah ada
- Game dimulai ketika 3 player atau lebih bergabung ke dalam satu room (mekanisme untuk memulai game diserahkan kepada anda)
- Ketika ada yang memenangkan game, tampilan pada pemenang menyatakan “Anda menang” dan tampilan pada player lain yang kalah adalah “<Nickname pemenang> adalah pemenangnya!” Tampilkan juga kelima biji yang berurutan dengan tanda tertentu.
- Setelah game selesai user dikembalikan ke antarmuka sebelumnya

Program dapat dijalankan pada sistem operasi Windows atau Linux.
