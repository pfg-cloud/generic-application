package base;

import org.springframework.http.ResponseEntity;

public class BaseController {

    protected <T> ResponseEntity<T> success(final T data) {
        return ResponseEntity.ok(data);
    }
}
