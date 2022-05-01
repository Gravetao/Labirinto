import java.util.Arrays;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        fugirLabirinto perdido = new fugirLabirinto();
        perdido.percorrer();
    }
}

class fugirLabirinto {

    String[][] mapa = Mapa.mapa;
    ArrayStack coluna = new ArrayStack(4, 1);
    ArrayStack linha = new ArrayStack(4, 1);
    ArrayStack ultimaConferida = new ArrayStack(4, 1);

    public fugirLabirinto() {
        acharEntrada();
    }

    public void acharEntrada() {
        for (int l = 0; l < Mapa.mapa.length; l++) {
            for (int c = 0; c < Mapa.mapa[l].length; c++) {
                if (Objects.equals(Mapa.mapa[l][c], "E")) {
                    coluna.push(c);
                    linha.push(l);
                    ultimaConferida.push(0);
                }
            }
        }
    }

    public boolean acharSaida(){
        if (Objects.equals(mapa[linha.last()][coluna.last()], "S")) {
            System.out.println("Você achou a saída");
            return true;
        }
        return false;
    }

    public void marcarCaminho() {
        mapa[linha.last()][coluna.last()] = "&";
    }

    public boolean percorrerAcima() {
        System.out.println("Conferi acima");
        var position = mapa[linha.last() - 1][coluna.last()];
        return (Objects.equals(position, " ") || Objects.equals(position, "S"));
    }
    public boolean percorrerDireita() {
        System.out.println("Conferi a direita");
        var position = mapa[linha.last()][coluna.last() + 1];
        return (Objects.equals(position, " ") || Objects.equals(position, "S"));
    }
    public boolean percorrerAbaixo() {
        System.out.println("Conferi abaixo");
        var position = mapa[linha.last() + 1][coluna.last()];
        return (Objects.equals(position, " ") || Objects.equals(position, "S"));
    }
    public boolean percorrerEsquerda() {
        System.out.println("Conferi a esquerda");
        var position = mapa[linha.last()][coluna.last() - 1];
        return (Objects.equals(position, " ") || Objects.equals(position, "S"));
    }

    public void percorrer() {
        while (!acharSaida()) {

            System.out.println("Ultima posição: " + ultimaConferida.last());
            System.out.println("Posição atual: ( " + linha.last() + " , " + coluna.last() + " )");
            marcarCaminho();
            imprimirMapa();

            if (percorrerAcima()) {
                System.out.println("Andei para cima");
                linha.push(coluna.last() - 1);
                ultimaConferida.push(0);
            } else if (percorrerDireita()) {
                System.out.println("Andei para direita");
                coluna.push(coluna.last() + 1);
                ultimaConferida.push(1);
            } else if (percorrerAbaixo()) {
                System.out.println("Andei para baixo");
                linha.push(linha.last() + 1);
                ultimaConferida.push(2);
            } else if (percorrerEsquerda()) {
                System.out.println("Andei para esquerda");
                coluna.push(coluna.last() - 1);
                ultimaConferida.push(3);
            } else {
                switch (ultimaConferida.last()) {
                    case 0 :
                        System.out.println("Indo para baixo");
                        linha.pop();
                        break;
                    case 1 :
                        System.out.println("Indo para esquerda");
                        coluna.pop();
                        break;
                    case 2:
                        System.out.println("Indo para cima");
                        linha.pop();
                        break;
                    case 3:
                        System.out.println("Indo para direita");
                        coluna.pop();
                        break;
                }
                ultimaConferida.pop();
            }
        }
    }

    public void imprimirMapa() {
        for (String[] strings : mapa) {
            System.out.println(Arrays.toString(strings));
        }
    }
}


class Mapa {
    static String[][] mapa = {
            {"#","#","#","#","#","#","#","#","#","#","#","#"},
            {"#","E","#","#","#","#","#","#","#","#","S","#"},
            {"#"," ","#","#","#","#","#","#","#","#"," ","#"},
            {"#"," ","#","#","#"," "," ","#","#","#"," ","#"},
            {"#"," ","#"," "," ","#"," ","#","#","#"," ","#"},
            {"#"," "," "," ","#","#"," ","#","#","#"," ","#"},
            {"#"," ","#"," ","#","#"," "," "," "," "," ","#"},
            {"#"," ","#"," ","#","#","#","#"," ","#","#","#"},
            {"#"," ","#","#","#","#","#","#"," ","#","#","#"},
            {"#"," "," "," "," "," "," "," "," ","#","#","#"},
            {"#","#","#","#","#","#","#","#","#","#","#","#"},
            {"#","#","#","#","#","#","#","#","#","#","#","#"}
    };
}