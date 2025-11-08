package com.example.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Bean
	public RouteLocator getwayRoutes(RouteLocatorBuilder builder)
	{
		return builder.routes()
				.route("Billet",r->r.path("/billets/**")
						.filters(f -> f.stripPrefix(1))
						.uri("lb://microBillet"))
				.route("Club",r->r.path("/clubs/**")
						.filters(f -> f.stripPrefix(1))
						.uri("lb://microClub"))
				.route("Competition",r->r.path("/competitions/**")
						.filters(f -> f.stripPrefix(1))
						.uri("lb://microCompetition"))
				.route("Joueur",r->r.path("/joueurs/**")
						.filters(f -> f.stripPrefix(1))
						.uri("lb://microJoueur"))
				.route("microLocal", r -> r.path("/microLocal/**")
						.filters(f -> f.stripPrefix(1))   // /microLocal/foo -> /foo
						.uri("lb://microLocal"))
				.route("Match",r->r.path("/matchs/**")
						.filters(f -> f.stripPrefix(1))
						.uri("lb://microMatch"))
				.route("Staff",r->r.path("/staffs/**")
						.filters(f -> f.stripPrefix(1))
						.uri("lb://microStaff"))
				.route("Transfert",r->r.path("/transferts/**")
						.filters(f -> f.stripPrefix(1))
						.uri("lb://microTransfert"))
				.route("User",r->r.path("/users/**")
						.filters(f -> f.stripPrefix(1))
						.uri("lb://microUser"))
				.build();
	}
}
