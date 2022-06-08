package ua.mike.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by mike on 08.06.2022 16:53
 */
@Configuration
public class BeerServicesRoutesConfig {

    @Value("${ms.beer.service.host}")
    private String beerService;
    @Value("${ms.inventory.service.host}")
    private String inventoryService;
    @Value("${ms.order.service.host}")
    private String orderService;

    @Bean
    public RouteLocator beerServiceRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(route -> route.path("/api/beer*", "/api/beer/**").uri(beerService))
                .route(route -> route.path("/api/inventory*", "/api/inventory/**").uri(inventoryService))
                .route(route -> route.path("/api/order*", "/api/order/**").uri(orderService))
                .build();
    }
}
