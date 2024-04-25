package br.com.cairu.projeto.integrador.brecho.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.cairu.projeto.integrador.brecho.dtos.generic.GenericResponseDTO;
import br.com.cairu.projeto.integrador.brecho.dtos.product.ProductResponseDTO;
import br.com.cairu.projeto.integrador.brecho.models.File;
import br.com.cairu.projeto.integrador.brecho.models.Product;
import br.com.cairu.projeto.integrador.brecho.repositories.FileRepository;
import br.com.cairu.projeto.integrador.brecho.repositories.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private FileRepository fileRepository;

    private final String pathImage = "\\src\\main\\resources\\static\\public\\images\\";

    public ResponseEntity<Object> register(String data, ArrayList<MultipartFile> images) {
        try {

            ObjectMapper mapper = new ObjectMapper();

            Product product = new Product();

            product = mapper.readValue(data, product.getClass());

            ArrayList<String> urlImages = this.uploadImage(images);

            productRepository.save(product);

            for (String url : urlImages) {
                fileRepository.save(new File(url, product));
            }

            return ResponseEntity.status(201).body(new GenericResponseDTO("Produto cadastrado com sucesso!"));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new GenericResponseDTO(e.getMessage()));
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
            String extension = com.google.common.io.Files.getFileExtension(image.getOriginalFilename());

            Timestamp nameFile = new Timestamp(System.currentTimeMillis() + 100);

            Files.write(Paths.get(path + this.pathImage + nameFile.getTime() + "." +
                    extension), image.getBytes());

            nameImage.add("public/image/" + nameFile.getTime() + "." + extension);
        }

        return nameImage;
    }

    public ResponseEntity<Object> all() {
        List<Product> products = productRepository.findAll();
        // List<ProductResponseDTO> products = productRepository.findAllByOrderByIdDesc();

        return ResponseEntity.status(200).body(products);
    }
}
