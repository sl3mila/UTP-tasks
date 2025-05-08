package appeals;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Appeal {

    private boolean state;
    public Boolean getState() {
        return state;
    }
    public void setState(Boolean state) {
        this.state = state;
    }

    private boolean notDone;
    public Boolean isNotDone() {
        return notDone;
    }
    public void setNotDone(boolean notDone) {
        this.notDone = notDone;
    }


    // all Ministries
    String dOrder = "it refers to anything based on Law like theft, harassment and more";
    Ministry mOrder = new Ministry(1, "Order", dOrder);

    String dPatriotysm  = "it refers to anything for the country, leader, and war. (the national song is a patriotism)";
    Ministry mPatriotism = new Ministry(2, "Patriotism", dPatriotysm);

    String dSocialCare = "it refers to anything associated with education, health, housing and more";
    Ministry mSocialCare = new Ministry(3, "Social Care", dSocialCare);

    String dLabor = "it refers to anything related to working in different jobs";
    Ministry mLabor = new Ministry(4, "Labor", dLabor);

    String dCultureSports = "it refers to anything related to theater, sports and more";
    Ministry mCultureSports = new Ministry(5, "Culture and Sports", dCultureSports);

    String dScienceAndTech = "it refers to anything related to upgrades, invention, machinery and more";
    Ministry mScienceAndTech = new Ministry(6, "Science and Technology", dScienceAndTech);

    private List<Ministry> lMinistry = new ArrayList<>();


    // all types
    String dComplaint = "these appeals are issued by people who do not know who did something in a specific event";
    TypeOfAppeal aComplaint = new TypeOfAppeal(1, "Complaint", dComplaint, Color.yellow);

    String dDenunciation = " these appeals happens when one person gives another person name and want them to be punished";
    TypeOfAppeal aDenunciation = new TypeOfAppeal(2, "Denunciation", dDenunciation, Color.red);

    String dRequest = "these appeals are easy to recognize as most people with these appeals are requesting something from you";
    TypeOfAppeal aRequest = new TypeOfAppeal(3, "Request", dRequest, Color.green);

    String dInformation = "these appeals happens when a person is giving you information";
    TypeOfAppeal aInformation = new TypeOfAppeal(4, "Information", dInformation, Color.blue);

    private List<TypeOfAppeal> lType = new ArrayList<>();



    //Appeals
    TypeOfAppeal type;
    public String getTypeName() {
        return type.getType();
    }
    public String getTypeDetails() {
        return type.getDetails();
    }
    public Color getTypeColor() {
        return type.getColor();
    }

    Ministry ministry;
    public String getMinistryName() {
        return ministry.getName();
    }
    public String getMinistryDetails() {
        return ministry.getDetails();
    }

    Random random = new Random();



    public Appeal(Boolean state) {

        lMinistry.add(mOrder);
        lMinistry.add(mPatriotism);
        lMinistry.add(mSocialCare);
        lMinistry.add(mLabor);
        lMinistry.add(mCultureSports);
        lMinistry.add(mScienceAndTech);

        lType.add(aComplaint);
        lType.add(aDenunciation);
        lType.add(aRequest);
        lType.add(aInformation);

        int mId = random.nextInt(6) + 1;
        int tId = random.nextInt(4) + 1;

        this.ministry = lMinistry.get(mId - 1);
        this.type = lType.get(tId - 1);
        this.state = true;
        this.notDone = true;

    }

    public void generateNewAppeal() {
        lMinistry.add(mOrder);
        lMinistry.add(mPatriotism);
        lMinistry.add(mSocialCare);
        lMinistry.add(mLabor);
        lMinistry.add(mCultureSports);
        lMinistry.add(mScienceAndTech);

        lType.add(aComplaint);
        lType.add(aDenunciation);
        lType.add(aRequest);
        lType.add(aInformation);

        int mId = random.nextInt(6) + 1;
        int tId = random.nextInt(4) + 1;

        this.ministry = lMinistry.get(mId - 1);
        this.type = lType.get(tId - 1);
        this.state = true;
    }
}
