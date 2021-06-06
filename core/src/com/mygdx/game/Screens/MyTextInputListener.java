package com.mygdx.game.Screens;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.Array;


import java.util.Arrays;
import java.util.Random;


public class MyTextInputListener implements Input.TextInputListener {
        Field field;
        int phase;
        Field from;
        int min;
        int max;
//    private  int[] NorthAmericaFields;
//    private  int[] SouthAmericaFields;
//    private  int[] EuropeFields;
//    private  int[] AfricaFields;
//    private  int[] AsiaFields;
//    private  int[] AustraliaFields;
    MyTextInputListener(Field field, int phase, Field from, int min, int max   /*int[] NorthAmericaFields,int[] SouthAmericaFields,int[] EuropeFields,int[] AfricaFields,int[] AsiaFields,int[] AustraliaFields*/) {
            this.field = field;
            this.phase=phase;
            this.from=from;
            this.min=min;
            this.max=max;
//            this.NorthAmericaFields=NorthAmericaFields;
//        this.SouthAmericaFields=SouthAmericaFields;
//        this.AfricaFields=AfricaFields;
//        this.EuropeFields=EuropeFields;
//        this.AsiaFields=AsiaFields;
//        this.AustraliaFields=AustraliaFields;

        }


        @Override
        public void input (String text) {
            switch(phase) {
                case (1):
                    if (Integer.parseInt(text) <= PlayScreen.max) {
                        field.addUnits(Integer.parseInt(text));
                        PlayScreen.max -= Integer.parseInt(text);
                    } else {
                        field.addUnits(PlayScreen.max);
                        PlayScreen.max = 0;
                    }
                    break;
                case (2):
                    int[] attack;
                    int[] defend;
                    int au;
                    boolean breakl=true;


                    if (Integer.parseInt(text) <= PlayScreen.max) {
                        au=Integer.parseInt(text);
                    } else {
                        au=PlayScreen.max;
                    }

                    while (breakl&&au>0){
                        if (au>=3){
                            attack=new int[3];
                        }else{
                            attack=new int[au];
                        }
                        if (field.units>=2&&au>1){
                            defend=new int[2];
                        }else{
                            defend=new int[1];
                        }
                        for (int a=0;a<attack.length;a++){

                            attack[a]=new Random().nextInt(6)+1;
                        }
                        Arrays.sort(attack);
                        for (int d=0;d<defend.length;d++){
                            defend[d]=new Random().nextInt(6)+1;
                        }
                        Arrays.sort(defend);
                        for (int d=0;d<defend.length;d++){
                            if (field.units>0) {
                                if (attack[attack.length - 1 - d] > defend[defend.length - 1 - d]) {
                                    field.addUnits(-1);
                                }else{
                                    from.addUnits(-1);
                                    au-=1;
                                    if (au==0)
                                        break;
                                }
                            }
                            else{
                                field.setPlayer(PlayScreen.active_player);
                                if (au>3) {


                                   // PlayScreen.move(field, 4, from, 3, au);
                                }
                                else {
                                    field.addUnits(au);
                                    from.addUnits(-1*au);
                                }
                                breakl=false;
                                break;
                            }
                        }
                    }

                     break;
                case (3):
                    int fr;
                    if (Integer.parseInt(text) <= PlayScreen.max) {
                        fr=Integer.parseInt(text);
                    } else {
                       fr=PlayScreen.max;
                    }
                    field.addUnits(fr);
                    from.addUnits(-1*fr);
                    break;
                case (4):
                    int m;
                    if (Integer.parseInt(text) <= max&&Integer.parseInt(text) >=min) {
                        m=Integer.parseInt(text);
                    } else if (Integer.parseInt(text) <= max){
                        m=max;
                    }else{
                        m=min;
                    }
                    field.addUnits(m);
                    from.addUnits(-1*m);
                    break;
            }

        }

        @Override
        public void canceled () {
            if (phase==4){
                field.addUnits(min);
                from.addUnits(-1*min);
            }
        }
    }

