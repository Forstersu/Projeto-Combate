package combate;
import java.util.InputMismatchException;
import java.util.Scanner;

class Campeao {
    private String nome;
    private int vida;
    private int ataque;
    private int armadura;

    public Campeao(String nome, int vida, int ataque, int armadura) {
        this.nome = nome;
        this.vida = vida;
        this.ataque = ataque;
        this.armadura = armadura;
    }

    public void takeDamage(int dano) {
        int danoReal = Math.max(1, dano - this.armadura); // mínimo de 1 de dano
        this.vida = Math.max(0, this.vida - danoReal); // vida não pode ser menor que zero
    }

    public String status() {
        if (this.vida == 0) {
            return this.nome + ": morreu";
        } else {
            return this.nome + ": " + this.vida + " de vida";
        }
    }

    public int getAtaque() {
        return this.ataque;
    }

    public boolean isAlive() {
        return this.vida > 0;
    }
}


public class JogoCombate {
	
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RED = "\u001B[31m";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println(ANSI_BLUE + "========================================");
        System.out.println(ANSI_YELLOW + "      BEM VINDO AO JOGO DE COMBATE      ");
        System.out.println(ANSI_BLUE + "========================================" + ANSI_RESET);

        System.out.println("Digite os dados do primeiro campeão:");
        System.out.print("Nome: ");
        String nome1 = sc.nextLine();
        int vida1 = lerInteiro("Vida inicial: ", sc);
        int ataque1 = lerInteiro("Ataque: ", sc);
        int armadura1 = lerInteiro("Armadura: ", sc);

        System.out.println("\nDigite os dados do segundo campeão:");
        System.out.print("Nome: ");
        String nome2 = sc.nextLine();
        int vida2 = lerInteiro("Vida inicial: ", sc);
        int ataque2 = lerInteiro("Ataque: ", sc);
        int armadura2 = lerInteiro("Armadura: ", sc);

        Campeao campeao1 = new Campeao(nome1, vida1, ataque1, armadura1);
        Campeao campeao2 = new Campeao(nome2, vida2, ataque2, armadura2);

        int turnos = lerInteiro("\nQuantos turnos você deseja executar? ", sc);

        for (int turno = 1; turno <= turnos; turno++) {
            campeao1.takeDamage(campeao2.getAtaque());
            campeao2.takeDamage(campeao1.getAtaque());

            System.out.println(ANSI_YELLOW + "\nResultado do turno " + turno + ":" + ANSI_RESET);
            System.out.println(campeao1.status());
            System.out.println(campeao2.status());

            if (!campeao1.isAlive() || !campeao2.isAlive()) {
                break;
            }
        }

        System.out.println(ANSI_BLUE + "\n===== FIM DO COMBATE =====" + ANSI_RESET);
        sc.close();
    }

    public static int lerInteiro(String mensagem, Scanner sc) {
        int numero;
        while (true) {
            try {
                System.out.print(mensagem);
                numero = sc.nextInt();
                sc.nextLine(); 
                break;
            } catch (InputMismatchException e) {
                System.out.println(ANSI_RED + "ERRO: Por favor, digite um número inteiro." + ANSI_RESET);
                sc.nextLine(); 
            }
        }
        return numero;
    }
}
