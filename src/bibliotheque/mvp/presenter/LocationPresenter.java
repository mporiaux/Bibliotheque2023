package bibliotheque.mvp.presenter;

import bibliotheque.metier.Exemplaire;
import bibliotheque.metier.Lecteur;
import bibliotheque.metier.Location;
import bibliotheque.mvp.model.DAO;
import bibliotheque.mvp.model.Speciallocation;
import bibliotheque.mvp.view.ViewInterface;

import java.util.List;

public class LocationPresenter extends Presenter<Location> {
    private Presenter<Exemplaire> exemplairePresenter;
    private Presenter<Lecteur> lecteurPresenter;


    public void setExemplairePresenter(Presenter<Exemplaire> exemplairePresenter) {
        this.exemplairePresenter = exemplairePresenter;
    }

    public void setLecteurPresenter(Presenter<Lecteur> lecteurPresenter) {
        this.lecteurPresenter = lecteurPresenter;
    }


    public Exemplaire choixExemplaire(){
        return exemplairePresenter.selection();
    }

    public Lecteur choixLecteur(){
        return lecteurPresenter.selection();
    }
    public LocationPresenter(DAO<Location> model, ViewInterface<Location> view) {
        super(model,view);
    }

    public void  calculerAmende(Location l){
        view.affMsg("amende = "+((Speciallocation)model).calculerAmende(l));
    }
    public void enregistrerRetour(Location l){
        ((Speciallocation)model).enregistrerRetour(l);
        view.affMsg("retour enregistré");
    }

}
