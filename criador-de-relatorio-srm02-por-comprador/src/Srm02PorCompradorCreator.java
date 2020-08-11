import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Srm02PorCompradorCreator {
	
	private String repositorio;
	private String diretorioEnviar;
	private String relatorioBase;	
	private String nomeRelatorio;
	
	public Srm02PorCompradorCreator() {
		this.repositorio = "c:\\home\\usuario\\projetos\\relatoriosdiarios\\repositorio\\";
		this.diretorioEnviar = "c:\\home\\usuario\\projetos\\relatoriosdiarios\\enviar\\";
		this.relatorioBase = "srm02.f111";	
		this.nomeRelatorio = "srm02_comprador";
	}

	public void create() {

		LeitorDeArquivo leitor = new LeitorDeArquivo();
		List<String> texto = leitor.le(Paths.get(repositorio + relatorioBase));

		for (Integer n : verificaNumeroDeCompradores(texto)) {
			EscritorDeArquivo escritor = new EscritorDeArquivo();
			escritor.escreve(separadorDeConteudo(n, texto), geraNomeDoArquivo(n));
		}

	}

	private String geraNomeDoArquivo(Integer n) {

		String nomeDoArquivo = nomeRelatorio + n + ".f111";
		return diretorioEnviar + nomeDoArquivo;
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
