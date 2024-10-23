package acquario;
import acquario.connection.ConnectionDB;
import acquario.dto.PesceDTO;
import acquario.dto.PescePalla;
import acquario.dto.PesceRosso;

import java.sql.*;

public class Acquario {
    public void vediPesci() {
        String query = "SELECT * FROM pesci";
        try (PreparedStatement stmt = ConnectionDB.getConnection().prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            if (!rs.isBeforeFirst()) {
                System.out.println("Nessun pesce nell'acquario.");
            } else {
                while (rs.next()) {
                    String nome = rs.getString("nome");
                    String colore = rs.getString("colore");
                    String grandezza = rs.getString("grandezza");
                    String tipoPesce = rs.getString("tipoPesce");
                    System.out.println("Nome: " + nome + ", Colore: " + colore + ", Grandezza: " + grandezza + ", Tipo: " + tipoPesce);
                }
            }
        } catch (SQLException e) {
            System.out.println("Errore nel caricamento dei pesci: " + e.getMessage());
        }
    }
    public PesceDTO cercaPesce(String nome) {
        String query = "SELECT * FROM pesci WHERE nome = ?";
        try (PreparedStatement stmt = ConnectionDB.getConnection().prepareStatement(query)) {
            stmt.setString(1, nome);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String colore = rs.getString("colore");
                    String grandezza = rs.getString("grandezza");
                    String tipoPesce = rs.getString("tipoPesce");

                    if ("Pesce Palla".equals(tipoPesce)) {
                        return new PescePalla(nome, colore, grandezza, tipoPesce);
                    } else if ("Pesce Rosso".equals(tipoPesce)) {
                        return new PesceRosso(nome, colore, grandezza, tipoPesce);
                    }
                } else {
                    System.out.println("Pesce non trovato.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Errore nella ricerca del pesce: " + e.getMessage());
        }
        return null;
    }
    public void aggiungiPesce(String tipoPesce, String nome, String colore, String grandezza) {
        String query = "INSERT INTO pesci (nome, colore, grandezza, tipoPesce) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = ConnectionDB.getConnection().prepareStatement(query)) {
            stmt.setString(1, nome);
            stmt.setString(2, colore);
            stmt.setString(3, grandezza);
            stmt.setString(4, tipoPesce);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Aggiunto nuovo pesce: " + nome);
            } else {
                System.out.println("Errore durante l'aggiunta del pesce.");
            }
        } catch (SQLException e) {
            System.out.println("Errore nell'aggiunta del pesce: " + e.getMessage());
        }
    }
    public boolean eliminaPesce(String nome) {
        String query = "DELETE FROM pesci WHERE nome = ?";
        try (PreparedStatement stmt = ConnectionDB.getConnection().prepareStatement(query)) {
            stmt.setString(1, nome);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Pesce " + nome + " rimosso dall'acquario.");
                return true;
            } else {
                System.out.println("Pesce " + nome + " non trovato.");
            }
        } catch (SQLException e) {
            System.out.println("Errore nell'eliminazione del pesce: " + e.getMessage());
        }
        return false;
    }

}
