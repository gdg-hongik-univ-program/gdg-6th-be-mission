package gdg.hongik.mission.dto;

public record ErrorResponse(
        int status,
        String error,
        String message
) {}
