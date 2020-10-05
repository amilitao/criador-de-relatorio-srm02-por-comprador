import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Srm02PorCompradorCreator {	
	
	private Path arquivoBase;
	private Path destino;
	private final String nomeRelatorio = "srm02_comprador";
	
	public Srm02PorCompradorCreator(Path arquivoBase, Path destino) {		
		this.arquivoBase = arquivoBase;
		this.destino = destino;
	}
	
	
	public void create() {

		LeitorDeArquivo leitor = new LeitorDeArquivo();
		List<String> texto = leitor.le(arquivoBase);

		for (Integer numero : verificaNumeroDeCompradores(texto)) {	
			EscritorDeArquivo escritor = new EscritorDeArquivo();
			escritor.escreve(separadorDeConteudo(numero, texto), geraNomeDoArquivo(numero));
		}

	}

	private String geraNomeDoArquivo(Integer n) {

		String nomeDoArquivo = nomeRelatorio + n + ".f111";
		return destino + File.separator + nomeDoArquivo;
	}

	private Set<Integer> verificaNumeroDeCompradores(List<String> texto) {

		Set<Integer> compradores = new HashSet<Integer>();

		for (String linha : texto) {

			if (linha.contains("COMPRADOR.:")) {
				compradores.add(getNumeroDeComprador(linha));
			}
		}
		return compradores;
	}

	private int getNumeroDeComprador(String linha) {
		return Integer.parseInt(linha.substring(13, 18).trim());
	}

	private List<String> separadorDeConteudo(int comprador, List<String> texto) {

		List<String> conteudo = new ArrayList<String>();
		boolean exec = true;

		for (String line : texto) {
			if (line.contains("COMPRADOR.:")) {
				if (getNumeroDeComprador(line) == comprador) {
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
