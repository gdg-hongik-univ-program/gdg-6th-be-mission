package gdg.hongik.mission.common;

import gdg.hongik.mission.common.DTO.ErrorResponse;
import gdg.hongik.mission.common.exception.BadRequestException;
import gdg.hongik.mission.common.exception.NotFoundException;
import gdg.hongik.mission.common.message.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    //400
    @ExceptionHandler(MethodArgumentNotValidException.class) // 유효성 검사할 때 발생하는 에러를 처리해준다.
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        // 첫번째 에러메세지를 추출: get(0)
        String message = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        ErrorResponse errorResponse = new ErrorResponse(message);
        return ResponseEntity.badRequest().body(errorResponse);
    }

    //400
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        return ResponseEntity.badRequest().body(errorResponse); //json형태로 바디에 담아서 내보낸다.

    }

    //404
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        //notFound는 json형태로 보낼 수 없다.
        //return ResponseEntity.notFound().build(); 404 NOT_FOUND만
    }

    //500
    //위의 에러처리 외의 것들은 500으로 처리한다. (가독성)
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> handleUnknownException(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse(ErrorMessage.SERVER_ERROR);
        return ResponseEntity.internalServerError().body(errorResponse);
        //500 서버에러 내면서 message를 json형태로 바디에 담아서 넘김
    }
}
