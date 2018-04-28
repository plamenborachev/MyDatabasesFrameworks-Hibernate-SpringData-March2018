package softuni;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import softuni.config.ModelMapperConfig;

@SpringBootApplication
public class GameStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameStoreApplication.class, args);

	}

	@Bean
	public ModelMapper getMapper(){
		ModelMapper modelMapper = new ModelMapper();
		ModelMapperConfig config = new ModelMapperConfig(modelMapper);
		return modelMapper;
	}
}
