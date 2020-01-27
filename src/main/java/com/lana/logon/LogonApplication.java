package com.lana.logon;

import com.lana.logon.models.product.Category;
import com.lana.logon.repositories.product.CategoryRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class LogonApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogonApplication.class, args);
    }

    @Bean
    public CommandLineRunner insertData(CategoryRepo categoryRepo) {
        return (args) -> {
            String[] Categories = {"Desktop", "Laptop", "Tablet", "Components", "Accessories"};
            for (String name : Categories) {
                Category categoryModel = new Category();
                categoryModel.setName(name);
                categoryRepo.save(categoryModel);
            }
        };
    }
}
