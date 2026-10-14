package com.frosthaven.config;

import com.frosthaven.entity.Admin;
import com.frosthaven.entity.Category;
import com.frosthaven.entity.Product;
import com.frosthaven.repository.AdminRepository;
import com.frosthaven.repository.CategoryRepository;
import com.frosthaven.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(
            ProductRepository productRepository,
            CategoryRepository categoryRepository,
            AdminRepository adminRepository,
            PasswordEncoder passwordEncoder) {
        return args -> {
            // Seed Categories if empty
            if (categoryRepository.count() == 0) {
                Category c1 = new Category();
                c1.setCategoryName("Fruity Delights");
                Category c2 = new Category();
                c2.setCategoryName("Chocolate Heaven");
                Category c3 = new Category();
                c3.setCategoryName("Premium Specials");
                categoryRepository.saveAll(List.of(c1, c2, c3));
            }

            // Seed Products if empty
            if (productRepository.count() == 0) {
                Product p1 = new Product();
                p1.setProductName("Classic Strawberry Swirl");
                p1.setDescription("Handcrafted with fresh farm-picked strawberries, blended into velvety rich cream with strawberry ribbons.");
                p1.setPrice(149.0);
                p1.setImage("/images/strawberry.jpg");
                p1.setCategory("Fruity Delights");
                p1.setStock(25);

                Product p2 = new Product();
                p2.setProductName("Belgian Dark Chocolate");
                p2.setDescription("Intense Belgian dark chocolate infused with cocoa chips for the ultimate chocolate indulgence.");
                p2.setPrice(189.0);
                p2.setImage("/images/chocolate.jpg");
                p2.setCategory("Chocolate Heaven");
                p2.setStock(30);

                Product p3 = new Product();
                p3.setProductName("Alphonso Mango Delight");
                p3.setDescription("Sun-ripened Alphonso mango pulp churned into fresh, sweet, and tropical sunshine in every scoop.");
                p3.setPrice(169.0);
                p3.setImage("/images/mango.jpg");
                p3.setCategory("Fruity Delights");
                p3.setStock(20);

                Product p4 = new Product();
                p4.setProductName("Wild Blackcurrant Bliss");
                p4.setDescription("Tangy wild blackcurrants swirled with smooth Madagascar vanilla cream for an exquisite flavor contrast.");
                p4.setPrice(179.0);
                p4.setImage("/images/blackcurrant.jpg");
                p4.setCategory("Premium Specials");
                p4.setStock(18);

                productRepository.saveAll(List.of(p1, p2, p3, p4));
            }

            // Seed Default Admin if none exists
            if (adminRepository.count() == 0) {
                Admin admin = new Admin();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123"));
                adminRepository.save(admin);
            }
        };
    }
}
