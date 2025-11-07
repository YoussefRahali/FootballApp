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
						.uri("lb://microBillet"))
				.route("Club",r->r.path("/clubs/**")
						.uri("lb://microClub"))
				.route("Competition",r->r.path("/competitions/**")
						.uri("lb://microCompetition"))
				.route("Joueur",r->r.path("/joueurs/**")
						.uri("lb://microJoueur"))
				.route("microLocal", r -> r.path("/microLocal/**")
						.filters(f -> f.stripPrefix(1))   // /microLocal/foo -> /foo
						.uri("lb://microLocal"))
				.route("Match",r->r.path("/matchs/**")
						.uri("lb://microMatch"))
				.route("Staff",r->r.path("/staffs/**")
						.uri("lb://microStaff"))
				.route("Transfert",r->r.path("/transferts/**")
						.uri("lb://microTransfert"))
				.route("User",r->r.path("/users/**")
						.uri("lb://microUser"))
				.build();
	}
}
