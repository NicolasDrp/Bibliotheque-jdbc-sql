package Bibliotheque.Bibliotheque;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Table;
import javax.persistence.TypedQuery;


@Entity
@Table(name ="livre")
public class Livre implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idlivre",unique = true)
	private int id;
	
	@Column(name = "titre",nullable = false)
	private String titre;
	
	@Column(name = "auteur",nullable = false)
	private String auteur;
	
	@Column(name = "genre",nullable = false)
	private String genre;
	
	@Column(name = "nbrpages",nullable = false)
	private int nbrpages;
	
	@Column(name = "nbrexmp",nullable = false)
	private int nbrexmp;

	
	
	private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
			.createEntityManagerFactory("Bibliotheque");
	
	
	
	//ajouter un livre
	
	public void addLivre(String titre,String auteur,String genre,int nbrpages,int nbrexmp) {
		 // The EntityManager class allows operations such as create, read, update, delete
       EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
       // Used to issue transactions on the EntityManager
       EntityTransaction et = null;

       try {
           // Get transaction and start
           et = em.getTransaction();
           et.begin();

           // Create and set values for new customer
           Livre livre = new Livre();
           livre.setTitre(titre);
           livre.setAuteur(auteur);
           livre.setGenre(genre);
           livre.setNbrpages(nbrpages);
           livre.setNbrexmp(nbrexmp);

           // Save the customer object
           em.persist(livre);
           et.commit();
       } catch (Exception ex) {
           // If there is an exception rollback changes
           if (et != null) {
               et.rollback();
           }
           ex.printStackTrace();
       } finally {
           // Close EntityManager
           em.close();
       }
	}
	
	
	
	//afficher un livre selon le titre 
	
	 public void getLivre(String titre) {
	    	EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
	    	
	    	// the lowercase c refers to the object
	    	// :custID is a parameterized query thats value is set below
	    	String query = "SELECT l FROM Livre l WHERE lower(l.titre) like lower(CONCAT('%',:titre ,'%'))";
	    	
	    	// Issue the query and get a matching Customer
	    	TypedQuery<Livre> tq = em.createQuery(query, Livre.class);
	    	tq.setParameter("titre", titre);
	    	
	    	List<Livre> livres = null;
	    	try {
	    		// Get matching customer object and output
	    		livres = tq.getResultList();
	    		livres.forEach(livreS->System.out.println("\rTitre: "+livreS.getTitre() + "\rAuteur: " + livreS.getAuteur()+"\rGenre: "+livreS.getGenre()+"\rNombre de pages: "+livreS.getNbrpages()+"\rNombre d'exmplaire disponible: "+livreS.getNbrexmp()));
	    	}
	    	catch(NoResultException ex) {
	    		ex.printStackTrace();
	    	}
	    	finally {
	    		em.close();
	    	}
	    }
	 
	 //changer le titre d'un livre
	 
	 public void changerTitre(String titre,String nvtitre) {
		
		 	EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		 	EntityTransaction et = null;
		 	String query = "SELECT l FROM Livre l WHERE l.titre LIKE :titre";
			TypedQuery<Livre> tq = em.createQuery(query, Livre.class);
			Livre livre = null;
			tq.setParameter("titre", titre);
			
			try {
				livre = tq.getSingleResult();
				et = em.getTransaction();
				et.begin();
				livre = em.find(Livre.class, livre.getId());
				livre.setTitre(nvtitre);
				em.persist(livre);
				et.commit();
			}catch (NoResultException e) {
				System.out.println("le livre n'a pas pu etre trouver");
			} catch (Exception ex) {
				if (et != null) {
					et.rollback();
				}
				ex.printStackTrace();
			}
		}
	 
	 
	 //afficher tout les livres
	 public void getLivres() {
	    	EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
	    	
	    	// the lowercase c refers to the object
	    	// :custID is a parameterized query thats value is set below
	    	String strQuery = "SELECT l FROM Livre l WHERE l.id IS NOT NULL";
	    	
	    	// Issue the query and get a matching Customer
	    	TypedQuery<Livre> tq = em.createQuery(strQuery, Livre.class);
	    	List<Livre> livre;
	    	try {
	    		// Get matching customer object and output
	    		livre = tq.getResultList();
	    		livre.forEach(livreS->System.out.println("\rTitre: "+livreS.getTitre() + "\rAuteur: " + livreS.getAuteur()+"\rGenre: "+livreS.getGenre()+"\rNombre de pages: "+livreS.getNbrpages()+"\rNombre d'exmplaire disponible: "+livreS.getNbrexmp()));
	    	}
	    	catch(NoResultException ex) {
	    		ex.printStackTrace();
	    	}
	    	finally {
	    		em.close();
	    	}
	    }
	 
	 
	 //permet de supprimer un livre
	 public void supprimerLivre(String titre) {
		  EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		  EntityTransaction et = null;
		  String query = "SELECT l FROM Livre l WHERE l.titre LIKE :titre";
		  TypedQuery<Livre> tq = em.createQuery(query, Livre.class);
		  Livre livre = null;
		  tq.setParameter("titre", titre);

		  try {
		    livre = tq.getSingleResult();
		    et = em.getTransaction();
		    et.begin();
		    livre = em.find(Livre.class, livre.getId());
		    em.remove(livre);
		    et.commit();
		  } catch (NoResultException e) {
		    System.out.println("le livre n'a pas pu etre trouver");
		  } catch (Exception ex) {
		    if (et != null) {
		      et.rollback();
		    }
		    ex.printStackTrace();
		  }
		}

	
	
	
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the titre
	 */
	public String getTitre() {
		return titre;
	}

	/**
	 * @param titre the titre to set
	 */
	public void setTitre(String titre) {
		this.titre = titre;
	}

	/**
	 * @return the auteur
	 */
	public String getAuteur() {
		return auteur;
	}

	/**
	 * @param auteur the auteur to set
	 */
	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	/**
	 * @return the genre
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * @param genre the genre to set
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}

	/**
	 * @return the nbrpages
	 */
	public int getNbrpages() {
		return nbrpages;
	}

	/**
	 * @param nbrpages the nbrpages to set
	 */
	public void setNbrpages(int nbrpages) {
		this.nbrpages = nbrpages;
	}

	/**
	 * @return the nbrexmp
	 */
	public int getNbrexmp() {
		return nbrexmp;
	}

	/**
	 * @param nbrexmp the nbrexmp to set
	 */
	public void setNbrexmp(int nbrexmp) {
		this.nbrexmp = nbrexmp;
	}
	
	
	
}
