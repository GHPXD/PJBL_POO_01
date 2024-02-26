//-------@autors-----------//
//Guilherme Henrique Pereira//
//Léo Gustavo Del Ré//

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class PetShop {
    public static void main(String[] args) {
        ArrayList<Pet> petList = new ArrayList<>();

        // Recuperação das instâncias da classe Pet do arquivo
        try {
            FileInputStream fileIn = new FileInputStream("pets.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            while (true) {
                try {
                    Pet pet = (Pet) in.readObject();
                    petList.add(pet);
                } catch (EOFException e) {
                    break;
                }
            }
            in.close();
            fileIn.close();
            System.out.println("--- Fim da leitura do arquivo pets.ser ---");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);

        String option;
        do {
            System.out.println("ESCOLHER UMA OPÇÃO:\n" +
                    "c: cadastrar pet | i: imprimir cadastro | b: buscar por código\n" +
                    "e: excluir por código | x: encerrar.");

            option = scanner.nextLine();

            switch (option) {
                case "c":
                    cadastrarPet(scanner, petList);
                    break;
                case "i":
                    imprimirCadastro(petList);
                    break;
                case "b":
                    buscarPorCodigo(scanner, petList);
                    break;
                case "e":
                    excluirPorCodigo(scanner, petList);
                    break;
                case "x":
                    System.out.println("--- Programa de cadastro encerrado ---");
                    break;
                default:
                    System.out.println("--- Opção inválida ---");
                    break;
            }
        } while (!option.equals("x"));

        // Gravação das instâncias da classe Pet no arquivo
        try {
            FileOutputStream fileOut = new FileOutputStream("pets.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            for (Pet pet : petList) {
                out.writeObject(pet);
            }
            out.close();
            fileOut.close();
            System.out.println("--- Arquivo pets.ser atualizado com sucesso ---");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void cadastrarPet(Scanner scanner, ArrayList<Pet> petList) {
        System.out.println("Digite o código do pet:");
        String codigo = scanner.nextLine();
        System.out.println("Digite o nome do dono:");
        String nomeDono = scanner.nextLine();
        System.out.println("Digite o nome do pet:");
        String nomePet = scanner.nextLine();
        System.out.println("Digite o tipo do pet:");
        String tipoPet = scanner.nextLine();

        Pet pet = new Pet(codigo, nomeDono, nomePet, tipoPet);
        petList.add(pet);

        System.out.println("--- Pet cadastrado com sucesso ---");
    }

    private static void imprimirCadastro(ArrayList<Pet> petList) {
        System.out.println("--- CADASTRO PETS -----------------------------------------");
        for (Pet pet : petList) {
            System.out.println(pet);
        }
        System.out.println("--- Fim do cadastro ------------------------------------------\n" +
                "\n" +
                "ESCOLHER UMA OPÇÃO:\n" +
                "c: cadastrar pet | i: imprimir cadastro | b: buscar por código\n" +
                "e: excluir por código | x: encerrar.");
    }

    private static void buscarPorCodigo(Scanner scanner, ArrayList<Pet> petList) {
        System.out.println("Digite o código do pet:");
        String codigo = scanner.nextLine();

        boolean encontrado = false;
        for (Pet pet : petList) {
            if (pet.getCodigo().equals(codigo)) {
                System.out.println("--- Pet encontrado ---");
                System.out.println(pet);
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("--- Pet com código " + codigo + " inexistente ---");
        }
    }

    private static void excluirPorCodigo(Scanner scanner, ArrayList<Pet> petList) {
        System.out.println("Digite o código do pet a ser excluído:");
        String codigo = scanner.nextLine();

        boolean removido = false;
        for (int i = 0; i < petList.size(); i++) {
            Pet pet = petList.get(i);
            if (pet.getCodigo().equals(codigo)) {
                petList.remove(i);
                removido = true;
                System.out.println("--- Pet com código " + codigo + " excluído com sucesso ---");
                break;
            }
        }

        if (!removido) {
            System.out.println("--- Pet com código " + codigo + " inexistente ---");
        }
    }
}
