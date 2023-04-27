package bibliotheque.mvp.view;

import bibliotheque.metier.*;
import bibliotheque.mvp.presenter.AuteurPresenter;
import bibliotheque.mvp.presenter.LecteurPresenter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static bibliotheque.utilitaires.Utilitaire.*;


public class AuteurViewConsole extends AbstractViewConsole<Auteur>{
    @Override
    protected void rechercher() {
        try {
            System.out.println("nom ");
            String nom = sc.nextLine();
            System.out.println("prénom ");
            String prenom = sc.nextLine();
            System.out.println("nationalité");
            String nat = sc.nextLine();
            Auteur rech = new Auteur(nom, prenom, nat);
            presenter.search(rech);
        }catch(Exception e){
            System.out.println("erreur : "+e);
        }

    }

    @Override
    protected void modifier() {
        int choix = choixElt(ldatas);
        Auteur a = ldatas.get(choix-1);
         do {
            try {
                String nom = modifyIfNotBlank("nom", a.getNom());
                String prenom = modifyIfNotBlank("prénom", a.getPrenom());
                String nat = modifyIfNotBlank("nationalité", a.getNationalite());
                a.setNom(nom);
                a.setPrenom(prenom);
                a.setNationalite(nat);
                break;
            } catch (Exception e) {
                System.out.println("erreur :" + e);
            }
        }while(true);
        presenter.update(a);
        ldatas=presenter.getAll();//rafraichissement
        affListe(ldatas);
    }

    @Override
    protected void ajouter() {
        Auteur a;
        do {
            try {
                System.out.println("nom ");
                String nom = sc.nextLine();
                System.out.println("prénom ");
                String prenom = sc.nextLine();
                System.out.println("nationalité");
                String nat = sc.nextLine();
                a = new Auteur(nom, prenom, nat);
                break;
            } catch (Exception e) {
                System.out.println("une erreur est survenue : "+e.getMessage());
            }
        }while(true);
        presenter.add(a);
    }

    @Override
    protected void special() {
        int choix =  choixElt(ldatas);
        Auteur a = ldatas.get(choix-1);

        List options = new ArrayList<>(Arrays.asList("lister ouvrages", "lister livres", "lister par genre","fin"));
        do {
            int ch = choixListe(options);

            switch (ch) {

                case 1:
                    ((AuteurPresenter)presenter).listerOuvrages(a);
                    break;
                case 2:
                    TypeLivre[] tlv = TypeLivre.values();
                    int ch2 = choixListe(List.of(tlv));
                    TypeLivre tl = tlv[ch2-1];
                    ((AuteurPresenter)presenter).listerLivre(a,tl);
                    break;
                case 3:
                    System.out.println("genre :");
                    String genre = sc.nextLine();
                    ((AuteurPresenter)presenter).listerOuvrages(a,genre);
                    break;
                  case 4 :return;
            }
        } while (true);


    }
}
