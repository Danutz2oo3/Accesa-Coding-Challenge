package com.accesa.pricecomparator.service.dataloader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.accesa.pricecomparator.entity.Discount;
import com.accesa.pricecomparator.entity.ProductPrice;
import com.accesa.pricecomparator.repository.DiscountRepository;
import com.accesa.pricecomparator.repository.ProductPriceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DataLoaderServiceImpl implements DataLoaderService {

	private final ProductPriceRepository productPriceRepository;
    private final DiscountRepository discountRepository;
	
	@Override
	public void loadAllProductCSVs() {
		File folder = new File("data");
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".csv"));

        if (files == null || files.length == 0) {
            System.out.println("No product CSVs found in /data.");
            return;
        }

        for (File file : files) {
        	System.out.println("Loading file: " + file.getName());
        	if(file.getName().toLowerCase().contains("discount")) 
        		loadDiscountFile(file);
        	else 
        		loadProductFile(file);
        }
	}
	
	private void loadProductFile(File file) {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
        	
            br.readLine();

            String[] nameParts = file.getName().replace(".csv", "").split("_");
            String store = nameParts[0];
            LocalDate date = LocalDate.parse(nameParts[1]);

            List<ProductPrice> productList = new ArrayList<>();

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length < 8) continue;

                ProductPrice p = new ProductPrice();
                p.setProductId(parts[0]);
                p.setProductName(parts[1]);
                p.setCategory(parts[2]);
                p.setBrand(parts[3]);
                p.setPackageQuantity(Double.parseDouble(parts[4]));
                p.setPackageUnit(parts[5]);
                p.setPrice(Double.parseDouble(parts[6]));
                p.setCurrency(parts[7]);
                p.setStore(store);
                p.setDate(date);

                productList.add(p);
            }

            productPriceRepository.saveAll(productList);
            System.out.println("Saved " + productList.size() + " products from " + file.getName());

        } catch (Exception e) {
            System.err.println("Error loading file: " + file.getName());
            e.printStackTrace();
        }
    }
    private void loadDiscountFile(File file) {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {

            br.readLine();

            List<Discount> discountList = new ArrayList<>();
            String store = file.getName().split("_")[0];

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length < 9) continue;

                Discount d = new Discount();
                d.setProductId(parts[0]);
                d.setProductName(parts[1]);
                d.setBrand(parts[2]);
                d.setPackageQuantity(Double.parseDouble(parts[3]));
                d.setPackageUnit(parts[4]);
                d.setCategory(parts[5]);
                d.setFromDate(LocalDate.parse(parts[6]));
                d.setToDate(LocalDate.parse(parts[7]));
                d.setPercentageOfDiscount(Double.parseDouble(parts[8]));
                d.setStore(store);

                discountList.add(d);
            }

            discountRepository.saveAll(discountList);
            System.out.println("Saved " + discountList.size() + " discounts from " + file.getName());

        } catch (Exception e) {
            System.err.println("Error loading discount file: " + file.getName());
            e.printStackTrace();
        }
    }

}
