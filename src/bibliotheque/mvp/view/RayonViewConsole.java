package bibliotheque.mvp.view;

import bibliotheque.metier.Lecteur;
import bibliotheque.metier.Rayon;

import java.time.LocalDate;

import static bibliotheque.utilitaires.Utilitaire.*;

public class RayonViewConsole extends AbstractViewConsole{
    @Override
    protected void rechercher() {
        System.out.println("code du rayon : ");
       String code = sc.nextLine();
       Rayon rech = new Rayon(code,null);
        presenter.search(rech);
    }

    @Override
    protected void modifier() {

    }

    @Override
    protected void ajouter() {
        System.out.println("code rayon ");
        String code = sc.nextLine();
        System.out.println("genre ");
        String genre = sc.nextLine();
        Rayon r = new Rayon(code,genre);
        presenter.add(r);
        ldatas=presenter.getAll();//rafraichissement
        affListe(ldatas);
    }

    @Override
    protected void special() {

    }
}
