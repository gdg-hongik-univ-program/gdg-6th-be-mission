package gdg.hongik.mission.common.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    private String message;
    //다른정보 주고 싶으면 필드에 추가
}
