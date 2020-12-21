package base;

import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class BaseController {

    protected <T> ResponseEntity<T> success(@Nullable final T data) {
        return ResponseEntity
                .of(Optional.ofNullable(data));
    }
}
