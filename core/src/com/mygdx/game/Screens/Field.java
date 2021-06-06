package com.mygdx.game.Screens;


import com.badlogic.gdx.graphics.Color;

public class Field {
    int number;
    int[] neighbours=new int[6];
    int player=0;
    int[][] position=new int[2][2];
    int units=0;
    String selected;
    float xstart;
    float ystart;
    float xend;
    float yend;
    int textx;
    int texty;
    float r;
    float g;
    float b;
    float a;
    String group;
    public void setNumber(int number){
        this.number=number;
        switch (number) {
            case  (0):
                this.position[0][0]=360;
                this.position[0][1]=40;
                this.position[1][0]=470;
                this.position[1][1]=170;
                this.neighbours[0]=1;
                this.neighbours[1]=10;
                this.neighbours[2]=-1;
                this.neighbours[3]=-1;
                this.neighbours[4]=-1;
                this.neighbours[5]=-1;
                this.textx=427;
                this.texty=87;
                this.group="NorthAmerica";
                break;
            case (1):
                this.position[0][0]=30;
                this.position[0][1]=100;
                this.position[1][0]=380;
                this.position[1][1]=230;
                this.neighbours[0]=0;
                this.neighbours[1]=2;
                this.neighbours[2]=13;
                this.neighbours[3]=-1;
                this.neighbours[4]=-1;
                this.neighbours[5]=-1;
                this.textx=201;
                this.texty=163;
                this.group="NorthAmerica";
                break;
            case (2):
                this.position[0][0]=160;
                this.position[0][1]=280;
                this.position[1][0]=330;
                this.position[1][1]=340;
                this.neighbours[0]=1;
                this.neighbours[1]=3;
                this.neighbours[2]=-1;
                this.neighbours[3]=-1;
                this.neighbours[4]=-1;
                this.neighbours[5]=-1;
                this.textx=230;
                this.texty=290;
                this.group="NorthAmerica";
                break;
            case (3):
                this.position[0][0]=160;
                this.position[0][1]=350;
                this.position[1][0]=250;
                this.position[1][1]=430;
                this.neighbours[0]=2;
                this.neighbours[1]=4;
                this.neighbours[2]=-1;
                this.neighbours[3]=-1;
                this.neighbours[4]=-1;
                this.neighbours[5]=-1;
                this.textx=227;
                this.texty=393;
                this.group="NorthAmerica";
                break;
            case  (4):
                this.position[0][0]=250;
                this.position[0][1]=420;
                this.position[1][0]=350;
                this.position[1][1]=480;
                this.neighbours[0]=3;
                this.neighbours[1]=5;
                this.neighbours[2]=6;
                this.neighbours[3]=-1;
                this.neighbours[4]=-1;
                this.neighbours[5]=-1;
                this.textx=283;
                this.texty=444;
                this.group="SouthAmerica";
                break;
            case (5):
                this.position[0][0]=250;
                this.position[0][1]=540;
                this.position[1][0]=350;
                this.position[1][1]=770;
                this.neighbours[0]=4;
                this.neighbours[1]=6;
                this.neighbours[2]=-1;
                this.neighbours[3]=-1;
                this.neighbours[4]=-1;
                this.neighbours[5]=-1;
                this.textx=320;
                this.texty=605;
                this.group="SouthAmerica";
                break;
            case (6):
                this.position[0][0]=360;
                this.position[0][1]=480;
                this.position[1][0]=450;
                this.position[1][1]=610;
                this.neighbours[0]=4;
                this.neighbours[1]=5;
                this.neighbours[2]=7;
                this.neighbours[3]=-1;
                this.neighbours[4]=-1;
                this.neighbours[5]=-1;
                this.textx=383;
                this.texty=530;
                this.group="SouthAmerica";
                break;
            case (7):
                this.position[0][0]=490;
                this.position[0][1]=420;
                this.position[1][0]=630;
                this.position[1][1]=540;
                this.neighbours[0]=6;
                this.neighbours[1]=8;
                this.neighbours[2]=12;
                this.neighbours[3]=15;
                this.neighbours[4]=-1;
                this.neighbours[5]=-1;
                this.textx=580;
                this.texty=490;
                this.group="Africa";
                break;
            case  (8):
                this.position[0][0]=650;
                this.position[0][1]=500;
                this.position[1][0]=730;
                this.position[1][1]=640;
                this.neighbours[0]=7;
                this.neighbours[1]=9;
                this.neighbours[2]=15;
                this.neighbours[3]=-1;
                this.neighbours[4]=-1;
                this.neighbours[5]=-1;
                this.textx=690;
                this.texty=570;
                this.group="Africa";
                break;
            case (9):
                this.position[0][0]=610;
                this.position[0][1]=640;
                this.position[1][0]=720;
                this.position[1][1]=770;
                this.neighbours[0]=8;
                this.neighbours[1]=20;
                this.neighbours[2]=-1;
                this.neighbours[3]=-1;
                this.neighbours[4]=-1;
                this.neighbours[5]=-1;
                this.textx=663;
                this.texty=696;
                this.group="Africa";
                break;
            case (10):
                this.position[0][0]=480;
                this.position[0][1]=150;
                this.position[1][0]=550;
                this.position[1][1]=190;
                this.neighbours[0]=0;
                this.neighbours[1]=11;
                this.neighbours[2]=12;
                this.neighbours[3]=-1;
                this.neighbours[4]=-1;
                this.neighbours[5]=-1;
                this.textx=516;
                this.texty=163;
                this.group="Europe";
                break;
            case (11):
                this.position[0][0]=450;
                this.position[0][1]=200;
                this.position[1][0]=530;
                this.position[1][1]=290;
                this.neighbours[0]=10;
                this.neighbours[1]=12;
                this.neighbours[2]=-1;
                this.neighbours[3]=-1;
                this.neighbours[4]=-1;
                this.neighbours[5]=-1;
                this.textx=510;
                this.texty=267;
                this.group="Europe";
                break;
            case  (12):
                this.position[0][0]=560;
                this.position[0][1]=110;
                this.position[1][0]=740;
                this.position[1][1]=360;
                this.neighbours[0]=7;
                this.neighbours[1]=10;
                this.neighbours[2]=11;
                this.neighbours[3]=13;
                this.neighbours[4]=14;
                this.neighbours[5]=15;
                this.textx=667;
                this.texty=264;
                this.group="Europe";
                break;
            case (13):
                this.position[0][0]=790;
                this.position[0][1]=40;
                this.position[1][0]=1140;
                this.position[1][1]=260;
                this.neighbours[0]=1;
                this.neighbours[1]=12;
                this.neighbours[2]=14;
                this.neighbours[3]=-1;
                this.neighbours[4]=-1;
                this.neighbours[5]=-1;
                this.textx=930;
                this.texty=180;
                this.group="Asia";
                break;
            case (14):
                this.position[0][0]=770;
                this.position[0][1]=280;
                this.position[1][0]=1040;
                this.position[1][1]=380;
                this.neighbours[0]=12;
                this.neighbours[1]=13;
                this.neighbours[2]=15;
                this.neighbours[3]=16;
                this.neighbours[4]=-1;
                this.neighbours[5]=-1;
                this.textx=873;
                this.texty=315;
                this.group="Asia";
                break;
            case (15):
                this.position[0][0]=690;
                this.position[0][1]=360;
                this.position[1][0]=800;
                this.position[1][1]=520;
                this.neighbours[0]=8;
                this.neighbours[1]=12;
                this.neighbours[2]=14;
                this.neighbours[3]=16;
                this.neighbours[4]=7;
                this.neighbours[5]=-1;
                this.textx=735;
                this.texty=430;
                this.group="Asia";
                break;
            case  (16):
                this.position[0][0]=810;
                this.position[0][1]=400;
                this.position[1][0]=1010;
                this.position[1][1]=520;
                this.neighbours[0]=14;
                this.neighbours[1]=15;
                this.neighbours[2]=17;
                this.neighbours[3]=-1;
                this.neighbours[4]=-1;
                this.neighbours[5]=-1;
                this.textx=873;
                this.texty=425;
                this.group="Asia";
                break;
            case (17):
                this.position[0][0]=920;
                this.position[0][1]=530;
                this.position[1][0]=1010;
                this.position[1][1]=620;
                this.neighbours[0]=16;
                this.neighbours[1]=18;
                this.neighbours[2]=20;
                this.neighbours[3]=-1;
                this.neighbours[4]=-1;
                this.neighbours[5]=-1;
                this.textx=990;
                this.texty=570;
                this.group="Australia";
                break;
            case (18):
                this.position[0][0]=1040;
                this.position[0][1]=520;
                this.position[1][0]=1130;
                this.position[1][1]=590;
                this.neighbours[0]=17;
                this.neighbours[1]=19;
                this.neighbours[2]=-1;
                this.neighbours[3]=-1;
                this.neighbours[4]=-1;
                this.neighbours[5]=-1;
                this.textx=1088;
                this.texty=555;
                this.group="Australia";
                break;
            case (19):
                this.position[0][0]=1120;
                this.position[0][1]=610;
                this.position[1][0]=1170;
                this.position[1][1]=770;
                this.neighbours[0]=5;
                this.neighbours[1]=18;
                this.neighbours[2]=20;
                this.neighbours[3]=-1;
                this.neighbours[4]=-1;
                this.neighbours[5]=-1;
                this.textx=1107;
                this.texty=657;
                this.group="Australia";
                break;
            case (20):
                this.position[0][0]=980;
                this.position[0][1]=680;
                this.position[1][0]=1110;
                this.position[1][1]=770;
                this.neighbours[0]=9;
                this.neighbours[1]=17;
                this.neighbours[2]=19;
                this.neighbours[3]=-1;
                this.neighbours[4]=-1;
                this.neighbours[5]=-1;
                this.textx=1035;
                this.texty=695;
                this.group="Australia";
                break;
        }
            this.texty=830-this.texty+12;
        }
    public void setPlayer(int player){
        this.player=player;
        switch (player) {
            case 1:
               this.r=1f;
              this.g=0.27f;
              this.b=0.27f;

              break;
            case 2:
               this.r=0.27f;
               this.g=0.27f;
               this.b=1f;

               break;
            case 3:
               this.r=0.27f;
               this.g=1f;
               this.b=0.27f;

               break;
            case 4:
               this.r=0.96f;
               this.g=0.67f;
               this.b=0.2f;

               break;
        }
    }

    public void addUnits(int units){
        this.units+=units;
    }
    public void removeUnits(int units){
        this.units-=units;
    }
    public void setSelection(String selected){
        this.selected=selected;
        switch (selected) {
            case "-":
                this.a=1f;
                break;
            case "add":
                this.a=0.25f;
                break;
            case "attack":
                this.a=0.25f;
                break;
            case "attack_threat":
                this.a=0.5f;
                break;
            case "army_regroup_from":
                this.a=0.25f;
                break;
            case "army_regroup_to":
                this.a=0.5f;
                break;
        }
    }
    public void setCoordinates(float xstart,float ystart,float xend,float yend){
        this.xstart=xstart;
        this.ystart=ystart;
        this.xend=xend;
        this.yend=yend;
    }
}

