# There are some labs needed to complete in a university.

# JAVALAB - Flappy Bird bằng Java Swing

Bài thực hành gồm 4 phần, viết và chạy trên **VSCode trong Linux**.

## Môi trường
Kiểm tra phiên bản Java bằng lệnh:

```bash
java --version
javac --version
```

Máy đang dùng:

* `java` = 11
* `javac` = 21

Do `java` và `javac` khác phiên bản, khi biên dịch nên dùng:

```bash
javac --release 11 TenFile.java
java TenClass
```

Ví dụ:

```bash
javac --release 11 bai1.java
java bai1
```

## Cấu trúc thư mục

```text
JAVALAB/
├── bai1.java
├── bai2.java
├── bai3.java
├── bai4.java
├── flappybirdbg.png
├── flappybird.png
├── toppipe.png
└── bottompipe.png
```

## Nội dung các bài

### Bài 1

* Tạo cửa sổ game kích thước `360 x 640`
* Đặt tiêu đề `Flappy Bird`
* Không cho resize
* Hiển thị ảnh nền `flappybirdbg.png`

Chạy:

```bash
rm -f bai1.class
javac --release 11 bai1.java
java bai1
```

### Bài 2

* Thêm chim bằng ảnh `flappybird.png`
* Chim rơi xuống theo phương thẳng đứng
* Nhấn `Space` hoặc `Enter` để chim bay lên

Chạy:

```bash
rm -f bai2.class
javac --release 11 bai2.java
java bai2
```

### Bài 3

* Thêm 2 ống bằng `toppipe.png` và `bottompipe.png`
* Ống chạy từ phải sang trái
* Giữ lại cơ chế rơi và nhảy của chim

Chạy:

```bash
rm -f bai3.class
javac --release 11 bai3.java
java bai3
```

### Bài 4

* Thêm cơ chế tính điểm
* Thêm `Game Over` khi chim chạm ống hoặc ra ngoài màn hình
* Thêm chức năng chơi lại (`restart`)

Chạy:

```bash
rm -f bai4.class
javac --release 11 bai4.java
java bai4
```

## Lưu ý

* Tên file và tên class phải giống nhau
  Ví dụ: `bai1.java` tương ứng với `public class bai1`
* Các file ảnh phải nằm cùng thư mục với file `.java`
* Nếu sửa code trong VSCode, nhớ lưu file trước khi chạy lại
* Nếu gặp lỗi do lệch phiên bản Java, luôn ưu tiên biên dịch với (áp dụng với máy ảo của mình thôi):

```bash
javac --release 11 TenFile.java
```
