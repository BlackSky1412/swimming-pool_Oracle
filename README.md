## ✨ Tính năng chính

Ứng dụng **swimming-pool_Oracle** cung cấp các tính năng sau:

* **Quản lý thành viên:** Thêm, sửa, xóa thông tin thành viên, theo dõi lịch sử bơi.
* **Quản lý lịch bơi:** Tạo, cập nhật, xem lịch các buổi bơi.
* **Đặt chỗ:** Cho phép thành viên đặt chỗ trước cho các khung giờ bơi (nếu có).
* **Quản lý nhân viên:** Thêm, sửa, xóa thông tin nhân viên.
* **Báo cáo:** Thống kê số lượng thành viên, doanh thu (nếu có tính phí).

## 🔄 Chi tiết về quá trình chuyển đổi

Quá trình chuyển đổi từ MySQL sang Oracle tập trung vào việc đảm bảo hiệu suất và tận dụng các tính năng nâng cao của Oracle Database. Một số thách thức chính bao gồm:

* **Sự khác biệt về cú pháp SQL:** Chuyển đổi các hàm và cấu trúc truy vấn đặc trưng của MySQL sang cú pháp tương đương trong Oracle (ví dụ: `LIMIT` trong MySQL sang `ROWNUM` hoặc mệnh đề `FETCH FIRST` trong Oracle).
* **Kiểu dữ liệu:** Đảm bảo sự tương thích của các kiểu dữ liệu giữa hai hệ quản trị cơ sở dữ liệu.
* **Stored Procedures:** Viết lại các stored procedure từ ngôn ngữ của MySQL sang PL/SQL của Oracle.
* **Tối ưu hóa hiệu suất:** Sử dụng các công cụ phân tích hiệu suất của Oracle để xác định và tối ưu hóa các truy vấn chậm. Chúng tôi đã tận dụng các index và partitioning của Oracle để cải thiện tốc độ truy vấn.

File `QUERY.txt` chứa các ví dụ về các truy vấn Oracle đã được chuyển đổi, bao gồm các truy vấn cơ bản như `SELECT`, `INSERT`, `UPDATE`, `DELETE` và một số stored procedure mẫu.

## ⚙️ Giải thích công nghệ

* **Java (Spring Boot):** Chúng tôi sử dụng framework Spring Boot để xây dựng ứng dụng backend một cách nhanh chóng và hiệu quả, cung cấp các tính năng như Dependency Injection, Auto-configuration và tích hợp sẵn với cơ sở dữ liệu.
* **Oracle Database 19c:** Phiên bản Oracle Database 19c được lựa chọn vì tính ổn định, hiệu suất cao và các tính năng bảo mật mạnh mẽ.
* **Docker:** Docker được sử dụng để đóng gói ứng dụng và môi trường chạy của nó vào một container, giúp dễ dàng triển khai và quản lý trên nhiều hệ thống khác nhau.
* **Docker Compose:** Docker Compose được sử dụng để định nghĩa và quản lý nhiều container Docker (ví dụ: ứng dụng Java và database Oracle) một cách dễ dàng.
* **Maven:** Maven là công cụ quản lý dự án và build mạnh mẽ cho Java, giúp quản lý các thư viện phụ thuộc và build project một cách tự động.
* **HTML/CSS/JavaScript (Thymeleaf & Vanilla JS):** Giao diện người dùng được xây dựng bằng HTML, CSS và một chút JavaScript thuần (Vanilla JS) kết hợp với template engine Thymeleaf của Spring Boot để hiển thị dữ liệu từ backend.

## 📁 Cấu trúc thư mục chi tiết
.
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/blacksky1412/swimmingpool/  # Mã nguồn Java của ứng dụng
│   │   ├── resources/
│   │   │   ├── application.properties         # Cấu hình ứng dụng Spring Boot
│   │   │   ├── templates/                   # Các file template HTML
│   │   │   └── static/                      # Các file CSS, JavaScript
│   └── test/
│       └── java/
│           └── com/blacksky1412/swimmingpool/  # Mã nguồn các unit test
├── out/artifacts/                             # Thư mục chứa các file JAR hoặc WAR sau khi build
├── Dockerfile                                 # File cấu hình để build image Docker cho ứng dụng
├── docker-compose.yaml                        # File cấu hình để quản lý các container Docker (ứng dụng và database)
├── pom.xml                                    # File cấu hình Maven project
├── QUERY.txt                                  # Các truy vấn SQL theo chuẩn Oracle
├── .gitignore                                 # Chỉ định các file và thư mục Git nên bỏ qua
└── .dockerignore                              # Chỉ định các file và thư mục Docker nên bỏ qua khi build image


## 🚀 Hướng dẫn chạy ứng dụng chi tiết

### Yêu cầu

Đảm bảo bạn đã cài đặt các công cụ sau trên hệ thống:

* **Docker:** [Hướng dẫn cài đặt Docker](<link_huong_dan_cai_dat_docker>)
* **Docker Compose:** [Hướng dẫn cài đặt Docker Compose](<link_huong_dan_cai_dat_docker_compose>)
* **JDK 8 trở lên:** [Hướng dẫn cài đặt JDK](<link_huong_dan_cai_dat_jdk>)
* **Maven:** [Hướng dẫn cài đặt Maven](<link_huong_dan_cai_dat_maven>)

### Các bước thực hiện

1.  **Clone repository (nếu bạn chưa có):**
    ```bash
    git clone <link_repository_cua_ban>
    cd swimming-pool_Oracle
    ```

2.  **Build ứng dụng bằng Maven:**
    ```bash
    mvn clean package
    ```
    Lệnh này sẽ tải xuống các dependencies và build file `.jar` của ứng dụng trong thư mục `target` (hoặc `out/artifacts/`).

3.  **Khởi chạy Docker Compose:**
    ```bash
    docker-compose up --build
    ```
    Lệnh này sẽ build các image Docker (nếu cần) và khởi chạy các container được định nghĩa trong file `docker-compose.yaml`. Trong trường hợp này, nó có thể bao gồm container cho ứng dụng Java và có thể cả container cho Oracle Database (nếu bạn cấu hình như vậy).

4.  **Truy cập ứng dụng:**
    Mở trình duyệt web của bạn và truy cập địa chỉ `http://localhost:8080`. Cổng `8080` là cổng mặc định được cấu hình trong file `docker-compose.yaml` hoặc cấu hình của ứng dụng Spring Boot. Nếu ứng dụng chạy trên một cổng khác, hãy điều chỉnh URL cho phù hợp.

5.  **Xem logs (nếu có lỗi):**
    Nếu bạn gặp bất kỳ vấn đề nào, bạn có thể xem logs của các container Docker bằng lệnh:
    ```bash
    docker logs <tên_container_ứng_dụng>
    docker logs <tên_container_database>
    ```
    Bạn có thể tìm tên container bằng lệnh `docker ps`.

📌 **Tác giả**

 – [GitHub](BlackSky1412)
