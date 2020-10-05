import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class EscritorDeArquivo {

	public void escreve(List<String> linhas, String path) {

		try (FileWriter fw = new FileWriter(path); BufferedWriter bw = new BufferedWriter(fw);) {

			for (String linha : linhas) {			
				bw.write(linha);
				bw.newLine();
			}

		} catch (IOException e) {
			System.out.println("Erro ao gravar em arquivo");
			e.printStackTrace();
		}

	}

}
