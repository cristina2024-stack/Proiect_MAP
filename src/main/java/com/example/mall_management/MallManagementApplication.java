package com.example.mall_management;

import com.example.mall_management.model.Shop;
import com.example.mall_management.service.ShopService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "com.example.mall_management")
public class MallManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallManagementApplication.class, args);
    }

    @Bean
    CommandLineRunner init(ShopService shopService) {
        return args -> {
            shopService.addShop(new Shop("S1", "Zara", "Ana Popescu", 250.0));
            shopService.addShop(new Shop("S2", "Apple Store", "Mihai Ionescu", 180.5));
            shopService.addShop(new Shop("S3", "Starbucks", "Elena Georgescu", 90.0));
        };
    }
}
