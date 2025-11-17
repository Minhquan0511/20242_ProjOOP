# 📚 Stationery Shop Management System (JavaFX + PostgreSQL)

Một ứng dụng quản lý cửa hàng văn phòng phẩm được xây dựng bằng
**Java**, **JavaFX**, **Maven**, **PostgreSQL**, với các chức năng như
quản lý sản phẩm, khách hàng, giỏ hàng, hoá đơn, thống kê doanh thu,
chatbot AI hỗ trợ...

------------------------------------------------------------------------

## 📂 1. Cấu trúc dự án

    src/main
     ├── java/com/stationeryshop
     │     ├── controller
     │     ├── dao
     │     ├── model
     │     ├── utils
     │     ├── chatbot
     │     ├── App.java
     │     └── Main.java
     ├── resources
     │     ├── fxml
     │     ├── images
     │     ├── styles
     │     └── db.properties
    SQLFile/
    jar/
    lib/
    pom.xml

------------------------------------------------------------------------

## 🛠 2. Công nghệ & Thư viện sử dụng

### **Backend**

-   Java 17
-   JavaFX 20.0.2
-   PostgreSQL JDBC Driver 42.2.14
-   OkHttp 4.12.0 (HTTP API)
-   Jackson Databind (JSON)
-   jBCrypt (mã hoá mật khẩu)
-   JUnit 4.11 (Unit Test)

### **Frontend**

-   JavaFX FXML
-   CSS UI Styling

### **AI Integration**

-   OpenAI Java SDK

------------------------------------------------------------------------

## 🗃 3. Cấu hình Database (PostgreSQL)

### 🔧 **1. Tạo database**

``` sql
CREATE DATABASE stationeryshop;
```

### 🔧 **2. Import toàn bộ bảng**

``` bash
psql -U postgres -d stationeryshop -f stationeryshop_bkup.sql
```

------------------------------------------------------------------------

## 🛠 4. Cấu hình `db.properties`

``` properties
db.url=jdbc:postgresql://localhost:5432/stationeryshop
db.username=postgres
db.password=your_password_here
db.pool.size=10
```

------------------------------------------------------------------------

## 📦 5. Cách chạy project

### ✔️ **Chạy bằng Maven**

    mvn clean install
    mvn javafx:run

### ✔️ **Chạy bằng IntelliJ**

1.  File → Open → chọn thư mục project\
2.  Chờ Maven tải dependencies\
3.  Run file `Main.java`

------------------------------------------------------------------------

## 📁 6. Chức năng chính

-   Quản lý sản phẩm, danh mục, kho
-   Giỏ hàng, thanh toán
-   Quản lý hoá đơn & lịch sử mua hàng
-   Thống kê doanh thu theo ngày/tuần/tháng/năm
-   So sánh doanh thu kỳ trước
-   Đăng nhập/đăng ký với mã hoá mật khẩu
-   Chatbot AI hỗ trợ

------------------------------------------------------------------------

## 📘 7. Build file JAR

    mvn clean package

------------------------------------------------------------------------

## 🧪 8. Unit Tests

Chạy test:

    mvn test

------------------------------------------------------------------------

## 👨‍💻 9. Nhóm phát triển

  Thành viên                   Vai trò
  ------------                 ---------
  Nguyễn Minh Quân             Backend + QA  
  
  Hoàng Thị Phương Thảo        Backend
  
  Ngô Thanh Ngân               Backend
  
  Đào Bảo Trâm                 Backend
  
  Trần Minh Hiếu               Backend
  
  Dương Hạnh Nhi               Frontend

------------------------------------------------------------------------

## 📄 10. License

MIT License
