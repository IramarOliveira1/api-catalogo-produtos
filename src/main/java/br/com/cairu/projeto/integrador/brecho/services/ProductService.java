package br.com.cairu.projeto.integrador.brecho.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.cairu.projeto.integrador.brecho.dtos.generic.GenericResponseDTO;
import br.com.cairu.projeto.integrador.brecho.dtos.product.HomeResponseDTO;
import br.com.cairu.projeto.integrador.brecho.dtos.product.ProductRequestDTO;
import br.com.cairu.projeto.integrador.brecho.models.Category;
import br.com.cairu.projeto.integrador.brecho.models.File;
import br.com.cairu.projeto.integrador.brecho.models.Product;
import br.com.cairu.projeto.integrador.brecho.repositories.CategoryRepository;
import br.com.cairu.projeto.integrador.brecho.repositories.FileRepository;
import br.com.cairu.projeto.integrador.brecho.repositories.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private final String pathImage = "\\src\\main\\resources\\static\\public\\images\\";

    public ResponseEntity<Object> register(String data, ArrayList<MultipartFile> images) {
        try {

            ObjectMapper mapper = new ObjectMapper();

            Product product = new Product();

            product = mapper.readValue(data, product.getClass());

            String formatPrice = product.getPrice().substring(0, product.getPrice().length() - 2) + "."
                    + product.getPrice().substring(product.getPrice().length() - 2);

            product.setPrice(formatPrice);
            product.setCountClick(0);
            productRepository.save(product);

            ArrayList<String> urlImages = this.uploadImage(images);

            for (String url : urlImages) {
                fileRepository.save(new File(url, product));
            }

            return ResponseEntity.status(201).body(new GenericResponseDTO("Produto cadastrado com sucesso!"));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new GenericResponseDTO(e.getMessage()));
        }
    }

    public ResponseEntity<Object> all() {
        HashMap<String, Object> objects = new HashMap<>();

        List<Product> products = productRepository.findAll();
        List<Category> categories = categoryRepository.findAll();

        objects.put("products", products);
        objects.put("categories", categories);

        return ResponseEntity.status(200).body(objects);
    }

    public ResponseEntity<Object> index(Long id, String detail) {
        Product product = productRepository.findById(id).get();

        if (detail.equals("detail")) {

            product.setCountClick(product.getCountClick() + 1);

            productRepository.save(product);
        }

        return ResponseEntity.status(200).body(product);
    }

    public ResponseEntity<Object> update(Long id, ProductRequestDTO data, ArrayList<MultipartFile> images) {
        try {

            System.out.println("cair aqui " + images);

            // List<File> files = fileRepository.findByProductId(id);

            // Category category =
            // categoryRepository.findById(data.category().getId()).get();

            // Product product = productRepository.findById(id).get();

            // String formatPrice = data.price().substring(0, data.price().length() - 2) +
            // "."
            // + data.price().substring(product.getPrice().length() - 2);

            // this.deleteImage(files);

            // product.setName(data.name());
            // product.setDescription(data.description());
            // product.setPrice(formatPrice);
            // product.setIsActive(data.isActive());
            // product.setCategory(category);

            // productRepository.save(product);

            // ArrayList<String> urlImages = this.uploadImage(images);

            // for (File file : files) {
            // fileRepository.deleteById(file.getId());
            // }

            // for (String url : urlImages) {
            // fileRepository.save(new File(url, product));
            // }

            return ResponseEntity.status(200).body(new GenericResponseDTO("Produto atualizado com sucesso!"));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new GenericResponseDTO(e.getMessage()));
        }
    }

    public ResponseEntity<Object> delete(Long id) {
        try {
            List<File> files = fileRepository.findByProductId(id);

            this.deleteImage(files);

            fileRepository.deleteAll(files);

            productRepository.deleteById(id);

            return ResponseEntity.status(200).body(new GenericResponseDTO("Produto excluído com sucesso!"));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new GenericResponseDTO(e.getMessage()));
        }
    }

    public void deleteImage(List<File> files) {
        try {

            java.io.File currentPath = new java.io.File("");
            String path = currentPath.getAbsolutePath();

            for (File file : files) {
                if (Files.exists(Paths.get(path + "\\src\\main\\resources\\static\\" + file.getUrl()))) {
                    Files.delete(Paths.get(path + "\\src\\main\\resources\\static\\" + file.getUrl()));
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<String> uploadImage(ArrayList<MultipartFile> images) throws IOException {

        java.io.File currentPath = new java.io.File("");

        String path = currentPath.getAbsolutePath();

        ArrayList<String> nameImage = new ArrayList<>();

        if (!Files.exists(Paths.get(path + this.pathImage))) {
            Files.createDirectories(Paths.get(path + this.pathImage));
        }

        for (MultipartFile image : images) {

            System.out.println(image.getName());
            if (image != null) {
                String extension = com.google.common.io.Files.getFileExtension(image.getOriginalFilename());

                Timestamp nameFile = new Timestamp(System.currentTimeMillis() + 100);

                Files.write(Paths.get(path + this.pathImage + nameFile.getTime() + "." +
                        extension), image.getBytes());

                nameImage.add("public/images/" + nameFile.getTime() + "." + extension);
            }
        }

        return nameImage;
    }

    public ResponseEntity<Object> getByCategory(Long category_id) {
        List<Product> products = productRepository.findByCategoryId(category_id);

        return ResponseEntity.status(200).body(products);
    }

    public ResponseEntity<Object> filter(Product product) {
        List<Product> products = productRepository.findByNameLike("%" + product.getName() + "%");

        return ResponseEntity.status(200).body(products);
    }

    public ResponseEntity<Object> home() {
        List<HomeResponseDTO> products = productRepository.countByProductAndCategory();

        return ResponseEntity.status(200).body(products);
    }
}
