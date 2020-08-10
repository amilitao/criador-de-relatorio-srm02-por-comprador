import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class LeitorDeArquivo {
	
	public List<String> le(Path arquivo) {

		List<String> texto = new ArrayList<String>();

		try {
			
			texto = Files.readAllLines(arquivo);
			
		} catch (IOException e) {
			System.out.println("Erro ao ler arquivo!");
			e.printStackTrace();
		}

		return texto;
	}

}
