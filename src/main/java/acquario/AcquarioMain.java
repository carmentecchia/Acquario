package acquario;

import acquario.connection.ConnectionDB;
import acquario.dto.PesceDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AcquarioMain {
	public static void main(String[] args) {

		Acquario acquario  = new Acquario();
		Scanner scanner = new Scanner(System.in);

        ConnectionDB.getConnection();

        while (true) {
            System.out.println("\nScegli un'operazione:");
            System.out.println("1 - Visualizza i pesci");
            System.out.println("2 - Cerca un pesce");
            System.out.println("3 - Aggiungi un pesce");
            System.out.println("4 - Elimina un pesce");
            System.out.print("Comando: ");
            String comando = scanner.nextLine();

            switch (comando) {
                case "1":
                    acquario.vediPesci();
                    break;
                case "2":
                    System.out.print("Inserisci il nome del pesce da cercare: ");
                    String pesceDaCercare = scanner.nextLine();
                    PesceDTO pesceTrovato = acquario.cercaPesce(pesceDaCercare);
                    if (pesceTrovato != null) {
                        System.out.println("Pesce trovato: " + pesceTrovato);
                    } else {
                        System.out.println("Pesce non trovato.");
                    }
                    break;
                case "3":
                    System.out.print("Inserisci il tipo di pesce da aggiungere (pesce palla/pesce rosso): ");
                    String tipoPesce = scanner.nextLine();
                    System.out.print("Inserisci Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Inserisci Colore: ");
                    String colore = scanner.nextLine();
                    System.out.print("Inserisci Grandezza: ");
                    String grandezza = scanner.nextLine();
                    acquario.aggiungiPesce(tipoPesce, nome, colore, grandezza);
                    break;
                case "4":
                    System.out.print("Inserisci il nome del pesce da eliminare: ");
                    String pesceDaEliminare = scanner.nextLine();
                    if (acquario.eliminaPesce(pesceDaEliminare)) {
                        System.out.println("Pesce eliminato.");
                    } else {
                        System.out.println("Pesce non trovato.");
                    }
                    break;
                default:
                    System.out.println("Comando non riconosciuto.");
            }
        }

    }
}
