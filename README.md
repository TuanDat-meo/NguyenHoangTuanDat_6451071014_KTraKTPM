# UTC Login Selenium Tests

Dự án tự động hóa kiểm thử giao diện đăng nhập của [Hệ thống Văn phòng điện tử UTC](https://vanphongdientu.utc.edu.vn/Login) bằng Java, Selenium WebDriver và JUnit 5. Các kiểm thử truy cập website thật, vì vậy cần kết nối Internet và website phải hoạt động.

## Yêu cầu

- JDK 21 hoặc tương thích với Gradle toolchain
- Google Chrome đã cài đặt
- Kết nối Internet tới website UTC và Maven Central ở lần build đầu tiên

Không cần cài Gradle riêng; hãy sử dụng Gradle Wrapper trong repository.

## Chạy kiểm thử

Trên Windows PowerShell:

```powershell
.\gradlew.bat :app:test
```

Trên Linux hoặc macOS:

```bash
./gradlew :app:test
```

Để chạy riêng một test, thêm `--tests` cùng tên lớp và phương thức. Ví dụ:

```powershell
.\gradlew.bat :app:test --tests "nguyenhoangtuandat_6451071014_ktra1.LoginPageTest.loginPageLoads"
```

```bash
./gradlew :app:test --tests 'nguyenhoangtuandat_6451071014_ktra1.LoginPageTest.loginPageLoads'
```

Trong CI, Chrome chạy ở chế độ headless khi biến môi trường `CI=true`.

## Test case đăng nhập

Danh sách chi tiết được lưu tại [`docs/test-cases/login-test-cases.csv`](docs/test-cases/login-test-cases.csv).

| ID | Nội dung kiểm tra |
| --- | --- |
| TC_LOGIN_01 | Trang đăng nhập mở đúng và có tiêu đề mong đợi |
| TC_LOGIN_02 | Ô tên đăng nhập hiển thị và bật |
| TC_LOGIN_03 | Ô mật khẩu hiển thị và được che (`type=password`) |
| TC_LOGIN_04 | Nút Đăng nhập hiển thị và bật |
| TC_LOGIN_05 | Liên kết quên mật khẩu điều hướng tới `/Login/GetPass` |
| TC_LOGIN_06 | Liên kết e-mail UTC trỏ tới Google OAuth |
| TC_LOGIN_07 | Label của checkbox ghi nhớ đăng nhập hiển thị và checkbox bật |
| TC_LOGIN_08 | Có thể chọn checkbox ghi nhớ đăng nhập bằng label tùy chỉnh |

`AppTest` là test mẫu của Gradle, kiểm tra ứng dụng trả về lời chào khác `null`; nó không kiểm thử website UTC.

## Báo cáo Allure

Sau khi chạy test, JUnit/Allure lưu kết quả thô trong `app/build/allure-results`. Tạo lại báo cáo mỗi khi muốn xem kết quả mới.

### Windows PowerShell

```powershell
.\gradlew.bat :app:test
.\gradlew.bat :app:allureReport --clean
.\app\build\allure\commandline\bin\allure.bat open .\app\build\reports\allure-report\allureReport
```

### Linux hoặc macOS

```bash
./gradlew :app:test
./gradlew :app:allureReport --clean
./app/build/allure/commandline/bin/allure open ./app/build/reports/allure-report/allureReport
```

Lệnh `allure open` chạy máy chủ cục bộ và mở báo cáo trong trình duyệt. Giữ cửa sổ terminal đó mở trong lúc xem báo cáo; nhấn `Ctrl+C` để dừng máy chủ. Không mở trực tiếp `index.html`, vì trình duyệt có thể chặn tải các file dữ liệu của báo cáo.

Báo cáo JUnit HTML nằm tại `app/build/reports/tests/test/index.html`; báo cáo Allure được tạo tại `app/build/reports/allure-report/allureReport/`.

## Cấu trúc chính

```text
app/src/main/java/       Ứng dụng Java mẫu
app/src/test/java/       Selenium page object và các test JUnit
app/build.gradle         Dependencies và cấu hình test/Allure
.github/workflows/ci.yml GitHub Actions CI
.github/scripts/         Quy tắc nhánh và commit message

docs/test-cases/         Danh sách test case CSV
```

## CI và Git workflow

GitHub Actions chạy khi push lên `main` hoặc `develop`, khi tạo Pull Request vào hai nhánh này, hoặc khi chạy workflow thủ công. CI quét secret trong lịch sử Git, kiểm tra tên nhánh và commit message, chạy `:app:test`, tạo báo cáo Allure và tải báo cáo lên dưới dạng artifact.

Quy tắc nhánh:

- Các nhánh được phép: `main`, `develop`, `feature/<name>`, `defect/<name>`, `chore/<name>`.
- Pull Request từ `feature/*`, `defect/*` hoặc `chore/*` phải vào `develop`.
- Chỉ `develop` được tạo Pull Request vào `main`.

Commit message phải dùng một trong các loại `feat`, `fix`, `docs`, `refactor`, `test`, `chore`, theo mẫu dưới đây:

```text
test: verify login page load
```

Phần mô tả phải bằng tiếng Anh dùng ký tự ASCII, không quá 100 ký tự, không để trống, không kết thúc bằng dấu chấm và cần nêu rõ thay đổi.

