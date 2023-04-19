package bibliotheque.mvp.presenter;

import bibliotheque.metier.Exemplaire;
import bibliotheque.metier.Lecteur;
import bibliotheque.metier.Location;
import bibliotheque.mvp.model.DAO;
import bibliotheque.mvp.model.SpecialLecteur;
import bibliotheque.mvp.view.ViewInterface;

import java.util.List;

public class LocationPresenter extends Presenter<Location> {


    public LocationPresenter(DAO<Location> model, ViewInterface<Location> view) {
        super(model,view);
    }


}
