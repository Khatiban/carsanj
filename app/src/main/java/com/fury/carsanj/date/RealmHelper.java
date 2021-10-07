package com.fury.carsanj.date;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.exceptions.RealmException;

/**
 * Created by fury on 9/14/2017.
 */
public class RealmHelper {

    Realm realm;
    RealmResults<Spacecraft> spacecrafts;
    Boolean saved = null;

    public RealmHelper(Realm realm){
        this.realm = realm;
    }

    //Write
    public Boolean save(final Spacecraft spacecraft){
        if (spacecraft == null){
            saved = false;
        }else {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    try {
                        realm.copyToRealm(spacecraft);
                        saved = true;
                    } catch (RealmException e) {
                        saved = false;
                    }
                }
            });
        }


        return saved;
    }

    //Read
    public void retrievefromDB(){
        spacecrafts = realm.where(Spacecraft.class).findAll();
    }

    //Refresh
    public ArrayList<Spacecraft> refresh(){
        ArrayList<Spacecraft> lasted = new ArrayList<>();
        for (Spacecraft s : spacecrafts){
            lasted.add(s);
        }
        return lasted;
    }

}
