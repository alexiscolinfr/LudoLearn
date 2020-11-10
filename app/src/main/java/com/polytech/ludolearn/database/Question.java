package com.polytech.ludolearn.database;

import com.orm.SugarRecord;

public class Question extends SugarRecord {

    private String intitule;
    private String reponseFausse1, reponseFausse2, reponseFausse3;
    private String reponseVrai;
    private String tag;

    public Question(){}

    public Question(String intitule, String reponseFausse1, String reponseFausse2, String reponseFausse3,
                    String reponseVrai, String tag) {
        this.intitule = intitule;
        this.reponseFausse1 = reponseFausse1;
        this.reponseFausse2 = reponseFausse2;
        this.reponseFausse3 = reponseFausse3;
        this.reponseVrai = reponseVrai;
        this.tag = tag;
    }

    public String getIntitule() {
        return intitule;
    }

    public String getReponseFausse1() {
        return reponseFausse1;
    }

    public String getReponseFausse2() {
        return reponseFausse2;
    }

    public String getReponseFausse3() {
        return reponseFausse3;
    }

    public String getReponseVrai() {
        return reponseVrai;
    }

    public String getTag() {
        return tag;
    }
}
