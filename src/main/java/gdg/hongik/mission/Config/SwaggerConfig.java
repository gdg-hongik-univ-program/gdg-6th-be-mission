package gdg.hongik.mission.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("쇼핑몰 API")
                        .description("상품 등록, 조회, 구매 및 삭제 기능을 제공하는 API 문서입니다.")
                        .version("1.0.0"));
    }
}