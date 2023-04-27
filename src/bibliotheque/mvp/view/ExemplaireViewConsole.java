package bibliotheque.mvp.view;

import bibliotheque.metier.*;
import bibliotheque.mvp.model.SpecialExemplaire;
import bibliotheque.mvp.presenter.AuteurPresenter;
import bibliotheque.mvp.presenter.ExemplairePresenter;
import bibliotheque.utilitaires.Utilitaire;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static bibliotheque.utilitaires.Utilitaire.*;
import static bibliotheque.utilitaires.Utilitaire.modifyIfNotBlank;

public class ExemplaireViewConsole extends AbstractViewConsole<Exemplaire> {
    @Override
    protected void rechercher() {
        try{
            System.out.println("matricule : ");
            String mat= sc.next();
            Ouvrage obidon = new Livre("zzz",0,null,1,"","","",1, TypeLivre.ROMAN,"");
            Exemplaire rech = new Exemplaire(mat,"",obidon);
            presenter.search(rech);
        } catch (Exception e) {
            System.out.println("erreur "+e);
        }
    }

    @Override
    protected void modifier() {
        int choix = choixElt(ldatas);
        Exemplaire ex = ldatas.get(choix-1);
         do {
            try {
                String etat = modifyIfNotBlank("etat", ex.getDescriptionEtat());
                ex.setDescriptionEtat(etat);
                break;
            } catch (Exception e) {
                System.out.println("erreur :" + e);
            }
        }while(true);
        presenter.update(ex);
        ldatas=presenter.getAll();//rafraichissement
        affListe(ldatas);
    }

    @Override
    protected void ajouter() {
        System.out.println("matricule ");
        String mat=sc.next();
        System.out.println("etat  ");
        String etat=sc.next();
        System.out.println("ouvrage ");
        Ouvrage o = ((ExemplairePresenter)presenter).choixOuvrage();
        Exemplaire ex = null;
        try {
            ex = new Exemplaire(mat,etat,o);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        presenter.add(ex);
       Rayon r = ((ExemplairePresenter)presenter).choixRayon();
       ex.setRayon(r);
    }

    @Override
    protected void special() {
        int choix =  choixElt(ldatas);
        Exemplaire ex = ldatas.get(choix-1);

        List options = new ArrayList<>(Arrays.asList(
                "changer état",
                "lecteur actuel",
                "tous les lecteurs",
                "envoi mail lecteur actuel",
                "envoi mail tous les lecteurs",
                "en retard ?",
                "jours de retard",
                "en location ?",
                "fin"));
        do {
            int ch = choixListe(options);

            switch (ch) {
                case 1:
                    System.out.println("nouvel état :");
                    String etat = sc.nextLine();
                    ((ExemplairePresenter)presenter).modifierEtat(ex,etat) ;
                    break;

                case 2 :

                    ((ExemplairePresenter)presenter).LecteurActuel(ex);
                    break;

                case 3 :
                    ((ExemplairePresenter)presenter).lecteurs(ex);
                     break;

                case 4 :
                    ((ExemplairePresenter)presenter).envoiMailLecteurActuel(ex);
                    break;

                case 5:
                    ((ExemplairePresenter)presenter).envoiMailLecteurs(ex);
                    break;

                case 6 :
                    ((ExemplairePresenter)presenter).enRetard(ex);
                    break;

                case 7 :
                    ((ExemplairePresenter)presenter).joursRetard(ex);
                    break;

                case 8 :
                    ((ExemplairePresenter)presenter).enLocation(ex);
                    break;

                case 9 : return;
                }
        } while (true);

    }
}
