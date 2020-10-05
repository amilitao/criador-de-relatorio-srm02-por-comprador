import java.nio.file.Path;
import java.nio.file.Paths;

public class TestSrm02PorCompradorCreator {

	public static void main(String[] args) {

		Path relatorioBase = Paths.get("c:\\home\\usuario\\projetos\\relatoriosdiarios\\repositorio\\srm02.f111");	
		Path diretorioDestino = Paths.get("c:\\home\\usuario\\projetos\\relatoriosdiarios\\enviar\\");
		Srm02PorCompradorCreator srm02PorComprador = new Srm02PorCompradorCreator(relatorioBase, diretorioDestino);
		
		srm02PorComprador.create();

	}

}
