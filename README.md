# JSON formatter 라이브러리

## 프로젝트 개요

JSON 형태의 데이터를 로그에서 포맷팅하는 라이브러리입니다.
- REST API 요청 및 응답 데이터를 쉽게 확인할 수 있습니다.
- JSON 데이터를 가독성 있는 형식으로 변환합니다.
---

## ⭐주요기능
- JSON Pretty Print: JSON 데이터를 보기 좋은 들여쓰기로 포맷.
- JSON Minify: JSON 데이터를 압축된 형태로 변환.
- Key Sorting: JSON 데이터를 키값 기준으로 오름차순(알파벳 순) 정렬.
- Validation: JSON 형식이 올바른지 확인하고 문제가 있는 경우 오류메세지 제공.
- Syntax Highlighting: JSON 데이터를 로그에 출력 시 색상 지정.
---

## ✔️설치방법

### 1. Maven 

```xml
<dependency>
    <groupId>com.mimmimji</groupId>
    <artifactId>json-formatter</artifactId>
    <version>1.0.0</version>
</dependency>
 ```

### 2. Gradle 사용시

```gradle
implementation 'com.mimmimji:json-formatter:1.0.0'
```

---
# JSON Formatter 사용 방법
## 1. **JSON Formatter 사용하기**

### **기본 사용법**

```java
public class Main {

	public static void main(String[] args) {
            // 샘플 JSON 데이터
            String sampleJson = "{\"name\":\"John\",\"age\":30,\"city\":\"New York\",\"hobbies\":[\"reading\",\"traveling\"]}";
    
            // Pretty Print
            System.out.println("=== Pretty Print ===");
            String prettyJson = JsonFormatter.prettyPrint(sampleJson);
            System.out.println(prettyJson);
	}
}
```

### 결과 예시

```
    {
      "name" : "John",
      "age" : 30,
      "city" : "New York",
      "hobbies" : [ "reading", "traveling" ]
    }
```


---
## 📜 License

This project is licensed under the [MIT License](LICENSE).  
Feel free to use, modify, and distribute it as per the license terms.

© 2024 [mim-mim-ji]. All rights reserved.
