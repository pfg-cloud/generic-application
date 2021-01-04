package base;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;

public class BaseController {

    private static final String RESPONSE_TIME_HEADER = "X-Response-Time";

    protected <T> ResponseEntity<T> success() {
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .header(RESPONSE_TIME_HEADER, String.valueOf(System.currentTimeMillis())).build();
    }

    protected <T> ResponseEntity<T> success(@Nullable final T data) {
        return ResponseEntity.status(HttpStatus.OK)
                .header(RESPONSE_TIME_HEADER, String.valueOf(System.currentTimeMillis()))
                .body(data);
    }
}
