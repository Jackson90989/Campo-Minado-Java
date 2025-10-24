import org.example.excecao.Explosao;
import org.example.modelo.Campo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CampoTeste {
    private Campo campo = new Campo(3, 3);

    @Test
    void testeVizinhoRealDistancia1() {
        Campo vizinho = new Campo(3, 2);

        boolean resultado = campo.adicionarVizinho(vizinho);

        Assertions.assertTrue(resultado);
        assertTrue(resultado);
    }
    @Test
    void testeValorPadraoAtributoMarcado() {
        assertFalse(campo.isMarcado());
    }
    @Test
    void testeAlternarMarcacaoDuasChamadas(){
        campo.alternarMarcacao();
        campo.minar();
    }

    @Test
    void testeAbrirMinadoNaoMarcado(){
        campo.minar();
        assertThrows(Explosao.class, () -> {

        });
    }

    void testeAbrirComVizinhos(){
        Campo campo11 = new Campo(2, 2);
        Campo campo12 = new Campo(2,2);



        campo.adicionarVizinho(campo11);

        campo.adicionarVizinho(campo12);
        assertFalse(campo.abrir());

        campo12.adicionarVizinho(campo12);

        assertTrue(campo12.isAberto() && !campo11.isAberto());


    }
}
