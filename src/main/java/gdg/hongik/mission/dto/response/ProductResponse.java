package gdg.hongik.mission.dto.response;

public record ProductResponse(
        Long id,
        String name,
        int price,
        int stock
) {
}

