package com.altm.food;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.altm.food.entities.Product;
import com.altm.food.repositories.ProductRepository;

@SpringBootApplication 
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages="com.altm.food.repositories")
@EnableTransactionManagement
@EntityScan(basePackages="com.altm.food.entities")
public class Application implements CommandLineRunner{
	@Autowired
	private ProductRepository prodRepository;
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

	@Override
	public void run(String... args) throws Exception {
		prodRepository.save(new Product("Veg", "Roti", 100, "Roti.jpg", 4));
		prodRepository.save(new Product("Veg", "Rice", 200, "Rice.jpg", 1));
		prodRepository.save(new Product("Veg", "Dal", 300, "Dal.jpg", 2));
		prodRepository.save(new Product("Veg", "Mix", 400, "Mix.jpg", 3));
		prodRepository.save(new Product("Veg", "Paneer", 500, "Paneer.jpg", 5));
		prodRepository.save(new Product("Veg", "Sweets", 600, "Sweets.jpg", 3));
		prodRepository.save(new Product("Non-Veg", "Chicken", 100, "Chicken.jpg", 2));
		prodRepository.save(new Product("Non-Veg", "Fish", 200, "Fish.jpg", 1));
		prodRepository.save(new Product("Non-Veg", "Egg", 300, "Egg.jpg", 3));
		prodRepository.save(new Product("Non-Veg", "Mutton", 400, "Mutton.jpg", 5));
	
	}
    
}