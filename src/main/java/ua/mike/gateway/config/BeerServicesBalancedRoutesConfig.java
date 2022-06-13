package ua.mike.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by mike on 13.06.2022 14:06
 */
@Configuration
@Profile("local-discovery")
public class BeerServicesBalancedRoutesConfig {

    @Value("${ms.beer.service.name}")
    private String beerService;
    @Value("${ms.inventory.service.name}")
    private String inventoryService;
    @Value("${ms.order.service.name}")
    private String orderService;

    @Bean
    public RouteLocator beerServiceRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(route -> route.path("/api/beer*", "/api/beer/**").uri(beerService))
                .route(route -> route.path("/api/inventory*", "/api/inventory/**").uri(inventoryService))
                .route(route -> route.path("/api/orders*", "/api/orders/**").uri(orderService))
                .build();
    }
}
