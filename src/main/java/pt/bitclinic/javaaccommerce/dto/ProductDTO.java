package pt.bitclinic.javaaccommerce.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import pt.bitclinic.javaaccommerce.entities.Product;

public class ProductDTO {
	
	private Long id;
	
	@Size(min=3, max=80, message = "Campo precisa de ter de 3 a 80 caracteres")
	@NotBlank(message = "Campo necessário")
	private String name;
	
	@NotBlank(message = "Campo necessário")
	@Size(min = 10,  message = "Campo precisa de ter no minimo 10 caracteres")
	private String description;
	
	@NotNull(message = "Campo necessário")
	@Positive(message = "Preço tem de ser um valor positivo")
	private Double price;
	private String imgUrl;
	
	@NotEmpty(message = "Deve ter pelo menos uma categoria")
	private List<CategoryDTO> categories = new ArrayList<>();
	
	public ProductDTO(Long id, String name, String description, Double price, String imgUrl) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgUrl = imgUrl;		
	}
	
	public ProductDTO(Product entity) {
		id = entity.getId();
		name = entity.getName();
		description = entity.getDescription();
		price = entity.getPrice();
		imgUrl = entity.getImgUrl();
		categories = entity.getCategories().stream().map(x->new CategoryDTO(x)).collect(Collectors.toList());
		
		//alternative
		/*for(Category cat: entity.getCategories()) {
			categories.add(new CategoryDTO(cat));
		}*/
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Double getPrice() {
		return price;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public List<CategoryDTO> getCategories() {
		return categories;
	}

}
