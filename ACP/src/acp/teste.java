/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acp;

import java.util.ArrayList;

/**
 *
 * @author erickkimura
 */
public class teste {

    Erros erros;
    ArrayList<String> er = new ArrayList<String>();

    /*public static void main(String[] args) {
        teste teste = new teste();
        //teste.bnf("funcao(ola,ola).", true);
        System.out.println("jdsvbjsnd : " + teste.bnf("gos_92384_(olajsb_kndfm,jbdbdsf_du4iwbewhr473gywrbij).", true));
       
    }*/
   

    public ArrayList<String> resposta(String var) {

        String res = bnf(var, true);
        if (!(res.equals(""))) {
            return er;
        } else {

            return er;
        }
    }

    public String bnf(String var, boolean primeiro) {

        var = var.replaceAll("\\s+", " ");

        //verifica se é um predicado e a primeira consulta
        if (var.matches("\\p{Lower}+[a-zA-Z0-9_]*\\(.+\\)\\.") && primeiro) {

            var = var.replaceFirst("\\p{Lower}+[a-zA-Z0-9_]*\\(", "");
            System.out.println(var);
            var = var.replaceFirst("\\.", "");
            System.out.println(var);
            var = var.substring(0, var.length() - 1);

            System.out.println("predicado : " + var);

            
            if (var.matches("[a-zA-Z_0-9()]+\\,[a-zA-Z_0-9(),]+")) { // verifica se os termos sao divididos por ','
                String array[];

                //caso seja divide os termos utilizando as ',' como referencia
                array = var.split(",");
                
                
                for (String each : array) {
                    if(each.equals("")){
                        return "Erro";
                    }
                    System.out.println("bnf : " + each);
                    String bfn = bnf(each, false);
                    System.out.println("Recebi predicado : " + bfn);
                    if (bfn == "Erro") {
                        return "Erro";
                    }
                }
                return "";

            }

            String v = bnf(var, false);
            if (v == "Erro") {
                return "Erro";
            } else {
                return "";
            }

        } else if (primeiro) { // caso contrario retorna erro pois é obrigado a se predicado
            //nao e uma formula bem formada pois nao comeca com um predicado
            er.add("Nao e uma fbf. Erro 005");
            return "Erro";
        } else { // se nao for o primeiro entao entra nesta condicao

            if (var.matches("\\p{Lower}+[a-zA-Z0-9_]*\\(.+\\)")) { // verifica se é uma funcao terminado em )

                //var.replaceFirst(".", "");
                //var.replaceFirst("\\p{Lower}+\\_*\\p{Lower}*\\", var);
                System.out.println("funcao sem , : " + var);
                var = var.replaceFirst("\\p{Lower}+[a-zA-Z0-9_]*\\(", "");
                System.out.println(var);
                var = var.substring(0, var.length() - 1);

                if (var.matches("[a-zA-Z0-9_()]+\\,[a-zA-Z0-9_()]+")) {
                    String array[];

                    array = var.split(",");

                    for (String each : array) {
                        System.out.println("bnf : " + each);
                        String bfn = bnf(each, false);
                        System.out.println("Recebi predicado : " + bfn);
                        if (bfn == "Erro") {
                            return "Erro";
                        }

                    }
                    return "";

                }

                String v = bnf(var, false);
                if (v == "Erro") {
                    return "Erro";
                } else {
                    return "";
                }

            } else if (var.matches("\\p{Lower}+[a-zA-Z0-9_]*\\(.+\\)\\,")) { // verifica se e uma funcao terminado em ,

                System.out.println("funcao com , :" + var);
                var = var.replaceFirst("\\p{Lower}+[a-zA-Z0-9_]*\\(", "");
                System.out.println(var);
                var = var.replaceFirst("\\,", "");
                System.out.println(var);
                var = var.substring(0, var.length() - 1);

                if (var.matches("[a-zA-Z0-9_()]+\\,[a-zA-Z0-9_()]+")) {
                    String array[];

                    array = var.split(",");

                    for (String each : array) {
                        System.out.println("bnf : " + each);
                        String bfn = bnf(each, false);
                        System.out.println("Recebi predicado : " + bfn);
                        if (bfn == "Erro") {
                            return "Erro";
                        }

                    }
                    return "";

                }

                String v = bnf(var, false);
                if (v == "Erro") {
                    return "Erro";
                } else {
                    return "";
                }

            } else if (var.matches("\\p{Lower}+[a-z_]*\\(.+\\)[a-zA-Z0-9_()]*")) { //verifica se e uma funcao terminado em qualquer letra

                System.out.println("funcao com , :" + var);
                var = var.replaceFirst("\\p{Lower}+[a-zA-Z0-9_]*\\(", "");
                System.out.println(var);
                var = var.replaceFirst("\\,", "");
                System.out.println(var);
                var = var.substring(0, var.length() - 1);

                if (var.matches("[a-z_()]+\\,[a-zA-Z_]+")) {
                    String array[];

                    array = var.split(",");

                    for (String each : array) {
                        System.out.println("bnf : " + each);
                        String bfn = bnf(each, false);
                        System.out.println("Recebi funcao : " + bfn);
                        if (bfn == "Erro") {
                            return "Erro";
                        }
                    }
                    return "";

                }

                String v = bnf(var, false);
                if (v == "Erro") {
                    return "Erro";
                } else {
                    return "";
                }

            } else if (var.matches("\\p{Upper}+[a-zA-Z0-9_]*")) { // verifica se é uma variavel 

                System.out.println("variavel");
                return "";

            } else if (var.matches("\\p{Lower}+[a-zA-Z0-9_]*")) { // verifica se é uma constante

                System.out.println("constante");
                return "";
            } else if (var == "") { // verifica se foi retornado ""
                return "";
            }

            // caso todas as opcoes citadas acima forem falsas retorna Erro
            //nao e uma fbf pois nao e uma variavel, constante ou uma funcao 
            er.add("Nao e uma fbf. Termo ("+var+"). Erro 006");
            System.out.println("Erro1");
            return "Erro";
            
        }

    }

}
