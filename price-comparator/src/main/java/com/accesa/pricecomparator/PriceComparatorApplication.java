package com.accesa.pricecomparator;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.accesa.pricecomparator.service.dataloader.DataLoaderService;

import jakarta.persistence.EntityManager;

@SpringBootApplication
public class PriceComparatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(PriceComparatorApplication.class, args);
	}
	
	
	@Bean
    public CommandLineRunner loadData(DataLoaderService dataLoaderService) {
        return args -> {
            System.out.println("Loading CSV data into H2...");
            dataLoaderService.loadAllProductCSVs();
            System.out.println("CSV data loaded.");
        };
    }
	
	@Bean
	CommandLineRunner printTables(EntityManager entityManager) {
	    return args -> {
	        var metadata = entityManager.getMetamodel();
	        System.out.println("Entities found: " + metadata.getEntities());
	    };
	}

}
