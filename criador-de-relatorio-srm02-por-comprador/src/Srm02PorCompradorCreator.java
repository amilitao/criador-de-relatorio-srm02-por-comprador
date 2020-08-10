import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Srm02PorCompradorCreator {
	
	private String relatorioBase = "srm02.f111";		

	public void create() {

		LeitorDeArquivo leitor = new LeitorDeArquivo();
		List<String> texto = leitor.le(Paths.get("c:\\home\\" + relatorioBase));

		for (Integer n : verificaNumeroDeCompradores(texto)) {
			EscritorDeArquivo escritor = new EscritorDeArquivo();
			escritor.escreve(separadorDeConteudo(n, texto), geraNomeDoArquivo(n));
		}

	}

	private String geraNomeDoArquivo(Integer n) {

		String diretorio = "c:\\home\\";
		String nomeDoArquivo = "srm02.comprador" + n + ".f111";

		return diretorio + nomeDoArquivo;
	}

	private Set<Integer> verificaNumeroDeCompradores(List<String> texto) {

		Set<Integer> compradores = new HashSet<Integer>();

		for (String linha : texto) {

			if (linha.contains("COMPRADOR.:")) {
				compradores.add(Integer.parseInt(linha.substring(13, 18).trim()));
			}
		}
		return compradores;
	}

	private List<String> separadorDeConteudo(int comprador, List<String> texto) {

		List<String> conteudo = new ArrayList<String>();
		boolean exec = true;

		for (String line : texto) {
			if (line.contains("COMPRADOR.:")) {
				if (Integer.parseInt(line.substring(13, 18).trim()) == comprador) {
					exec = true;
				} else {
					exec = false;
				}
			}

			if (exec) {
				conteudo.add(line);
			}
		}
		return conteudo;
	}

}
