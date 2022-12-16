package Bibliotheque.Bibliotheque;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		
		Livre livre = new Livre();
		//livre.addLivre("modification", "tempo", "culinaire", 342, 123);
		//livre.getLivre("Champion java");
		//livre.getLivres();
		//livre.changerTitre("l'Homme,le retour", "encore un nouveau titre");
		//livre.changerTitre("modification", "encore un nouveau titre");
		//livre.supprimerLivre("encore un nouveau titre");
		
		Scanner scan = new Scanner(System.in);
		
		try {
			System.out.println("Que voulez vous faire ?"+"\n \n"+"afficher la liste des livres :  tapez 1"+"\n"+"rechercher un livre :  tapez 2"+"\n"+"saisir un nouveau livre :  tapez 3"+"\n"+"en modifier un existant :  tapez 4"+"\n"+"annuler :  tapez 0");
			int saisiUtilisateur  = scan.nextInt();
			scan.nextLine();
			
			switch (saisiUtilisateur) {
			case 1:
				//afficher liste des livre
				livre.getLivres();
				break;
			case 2:
				//rechercher un livre
				System.out.println("Quel livre cherchez vous ?");
				String valeur = scan.nextLine();
				System.out.println(valeur);
				livre.getLivre(valeur);
				break;
			case 3:
				//si l'utilisateur veut creer un nouveau livre    saisi = 3
				System.out.println("Quel est le titre du livre");
				String titre = scan.nextLine();
				while (titre.length()<2) {
					System.out.println("Entrée invalide"+"\n"+"Quel est le titre du livre");
					titre = scan.nextLine();
				}
				System.out.println("Qui est l'auteur du livre");
				String nom = scan.nextLine();
				while (nom.length()<2) {
					System.out.println("Entrée invalide"+"\n"+"Qui est l'auteur du livre");
					nom = scan.nextLine();
				}
				System.out.println("Quel est le genre du livre");
				String genre = scan.nextLine();
				while (genre.length()<2) {
					System.out.println("Entrée invalide"+"\n"+"Qui est l'auteur du livre");
					genre = scan.nextLine();
				}
				System.out.println("Combien d'exemplaire de ce livre avons nous");
				//etre sur que ce soit un nombre qui soit entré
			    boolean bool = false;
			    int Nbrexemplaire = 1;
			    while(!bool)
			    {
			        try
			        {
			            Nbrexemplaire = scan.nextInt();
			            bool = true;
			        }
			        catch(InputMismatchException e)
			        {
			            System.out.println("Veuillez saisir un chiffre");
			            bool = false;
			            scan.nextLine();
			        }
			    }
				System.out.println("Combien de pages contient t-il ?");
				//etre sur que ce soit un nombre qui soit entré
			    boolean bool2 = false;
			    int Nbrpages = -1;
			    while(!bool2)
			    {
			        try
			        {
			            Nbrpages = scan.nextInt();
			            bool2 = true;
			        }
			        catch(InputMismatchException e)
			        {
			            System.out.println("Veuillez saisir un chiffre");
			            bool2 = false;
			           scan.nextLine();
			        }
			    }			
				//creation du livre
				livre.addLivre(titre, nom, genre, Nbrpages, Nbrexemplaire);
				
				break;
		/*	case 4:
				//modifier un livre existant
				System.out.println("Quel livre voulez vous modifier ?");
				//scan le nom puis demander en quoi il veut le modifier
				break;
			case 0:
				//fermer le programme
				System.exit(0);
				break;
			*/
				
			}
		} catch (InputMismatchException e) {
			System.out.println("veuillez entrer un chiffre entre 0 et 4");
		}
		
		
		
		
		
		
		
		scan.close();
		

	}
	
}
