package gdg.hongik.mission.exception;

import gdg.hongik.mission.dto.ErrorResponse;
import gdg.hongik.mission.entity.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    //커스텀 비즈니스 예외 처리
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException ex) {
        // 상품을 찾지 못한 경우는 404, 그 외 중복이나 재고 부족은 400 에러를 반환.
        HttpStatus status = (ex instanceof ProductNotFoundException) ?
                HttpStatus.NOT_FOUND : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(ErrorResponse.from(ex.getMessage()));
    }

    // DTO 유효성 검사 실패 예외 처리
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        String defaultMessage = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse.from(defaultMessage));
    }

}