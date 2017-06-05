package acp;

import java.util.ArrayList;

public class Erros {

    public ArrayList<String> Erros;
    public ArrayList<String> BC;
    public ArrayList<String> consulta;
    public ArrayList<Integer> dupla1;
    public ArrayList<Integer> dupla2;
    public ArrayList<String> ErrosBC;
    public ArrayList<String> ErrosConsulta;
    public ArrayList<String> resposta;
    public ArrayList<String> ErroTemp;
    public ArrayList<Integer> Excluidos;
    public String temporario;
    ACP acp = new ACP();
    private static final String OK = "[a-zA-Z0-9_(),!]";

    /*
     public static void main(String[] args) {
     Erros acp = new Erros();
     acp.BC.add("gosta");
     acp.tratamento_erros(1);
     for(String in : acp.ErrosBC){
     System.out.println(in);
     }
        
     //        acp.BC.add("(()asd()()))");
     //        acp.BC.add("(()()())).");
     //        acp.BC.add("(()()())).");
     //        acp.tratamento_erros(acp.BC);
     }*/
    Erros() {
        ErrosConsulta = new ArrayList<String>();
        ErrosBC = new ArrayList<String>();
        resposta = new ArrayList<String>();
        BC = new ArrayList<String>();
        Excluidos = new ArrayList<Integer>();
    }

    public ArrayList<String> getErros() {
        return Erros;
    }

    public void setErros(ArrayList<String> Erros) {
        this.Erros = Erros;
    }

    public ArrayList<String> getErroTemp() {
        return ErroTemp;
    }

    public void setErroTemp(ArrayList<String> ErroTemp) {
        this.ErroTemp = ErroTemp;
    }

    public String getTemporario() {
        return temporario;
    }

    public void setTemporario(String temporario) {
        this.temporario = temporario;
        //fazer lista de erros para o temp
        this.tratamento_erros(3);
        if (!(ErroTemp.size() > 0)) {
            ErroTemp.clear();
        }
    }

    public ArrayList<String> getConsulta() {
        return consulta;
    }

    public void setConsulta(ArrayList<String> consulta) {
        this.consulta = consulta;
        //colocar tratamento de erros
        this.tratamento_erros(2);

        if (!(ErrosConsulta.size() > 0)) {
            ErrosConsulta.clear();
        }
    }

    public ArrayList<String> getBC() {
        return BC;
    }

    public void setBC(ArrayList<String> BC) {
        this.BC = BC;
        //chama funcao de tratamento de erros
        this.tratamento_erros(1);
        if (!(ErrosBC.size() > 0)) {
            ErrosConsulta.clear();
        }

    }

    public ArrayList<String> getResposta() {

        return resposta;
    }

    public void setResposta(ArrayList<String> resposta) {
        this.resposta = resposta;
    }

    public ArrayList<String> getErrosBC() {
        return ErrosBC;
    }

    public void setErrosBC(ArrayList<String> ErrosBC) {
        this.ErrosBC = ErrosBC;
    }

    public ArrayList<String> getErrosConsulta() {
        return ErrosConsulta;
    }

    public void setErrosConsulta(ArrayList<String> ErrosConsulta) {
        this.ErrosConsulta = ErrosConsulta;
    }

    void tratamento_erros(int escolha) {
        this.Erros = new ArrayList<String>();
        ArrayList<String> utilizar = new ArrayList<String>();
        if (escolha == 1) {
            utilizar = BC;
        } else if (escolha == 2) {
            utilizar = consulta;
        } else {
            utilizar.add(temporario);
        }

        int linha = 1;

        //tratamento do erro de parenteses
        if ((utilizar.size() > 0)) {
            for (String x : utilizar) {

                if (!parenteses(x, linha, escolha)) {
                    System.out.println("Erro");
                    Excluidos.add(linha-1);
                }
                linha++;
            }
        }

        for (String x : Erros) {
            System.out.println("Errosssss : " + x);
        }

        //tratamento de caracteres especiais
        linha = 1;
        if ((utilizar.size() > 0)) {
            System.out.println("aqui 1");
            for (String x : utilizar) {
                x = x.replace(" ", "");

                //verificacao dos caracteres especiais
                for (int i = 0; i < x.length() - 1; i++) {

                    String temp = "";
                    temp = Character.toString(x.charAt(i));
                    if (temp.matches("[a-zA-Z0-9_(),!]+")) {
                        //System.out.println("OKKKK");
                    } else {
                        //erro de caractere nao aceitos
                        //Caracter nao permitido na posicao : " + i
                        Erros.add("Não é uma fbf. Erro 001. Posição: " + i);
                        Excluidos.add(linha-1);
                        if (escolha == 1) {
                            //erro de caractere nao aceitos informando a linha do erro somente para BC
                            //Caracter nao permitido na linha : " + linha
                            Erros.add("Não é uma fbf. Erro 001. Posição: " + i + ". Linha: " + linha);
                            Excluidos.add(linha-1);
                        }
                        
                        System.out.println(":(");
                    }
                }

                System.out.println("Teste : " + x.charAt(x.length() - 1));

                //verificacao de ponto final
                if (x.charAt(x.length() - 1) == '.') {
                    System.out.println("OKK2");
                } else if (escolha == 1) {
                    //verificacao de ponto final para BC
                    //Faltando ponto final na linha : " + linha
                    Erros.add("Não é uma fbf. Erro 002. Linha : " + linha);
                    System.out.println("Faltando ponto final na linha : " + linha);
                    Excluidos.add(linha-1);
                } else {
                    //verificacao de ponto final para a consulta
                    //Faltando ponto final na consulta : " + x
                    Erros.add("Não é uma fbf. Erro 002");
                    System.out.println("Faltando ponto final na consulta : " + x);
                    Excluidos.add(linha-1);
                }

                //teste para ver se é uma formula bem formada
                ArrayList<String> temp3 = new ArrayList<String>();
                teste teste = new teste();
                temp3 = teste.resposta(x);
                if (temp3.size() > 0) {
                    for (String er : temp3) {
                        if (escolha == 1) {
                            Erros.add(er + " na linha : " + linha);
                            Excluidos.add(linha-1);
                        } else {
                            Erros.add(er);
                            Excluidos.add(linha-1);
                        }
                    }
                }

                linha++;
            }
        }
        for (String x : Erros) {
            System.out.println("Errosssss : " + x);
        }

        if (escolha == 1) {
            setErrosBC(Erros);
        } else if (escolha == 2) {
            setErrosConsulta(Erros);
        } else {
            setErroTemp(Erros);
        }

    }

    void unificar(int i) {
        boolean algum = false;
        if (i == 1) {
            for (String cons : consulta) {
                ArrayList<String> reps = new ArrayList<String>();
                String res = acp.rodar_unificacao(cons, temporario);
                ArrayList<String> reps1 = new ArrayList<String>();
                if (res.matches("\\{.*\\}")) {

                    reps1 = formatar(cons, res);
                    algum = true;
                    for (String l : reps1) {
                        reps.add(l);
                    }
                }
                if (!algum) {
                    resposta.add("Não unificavel");
                } else {
                    resposta.add("\n-? " + cons);
                    resposta.add("\nUnificável");
                    for (String in : reps) {
                        resposta.add(in);
                    }

                }
            }

        } else if (i == 2) {
            for (String cons : consulta) {
                ArrayList<String> reps = new ArrayList<String>();

                for (String bc : BC) {
                    ArrayList<String> reps1 = new ArrayList<String>();
                    String res = acp.rodar_unificacao(cons, bc);
                    if (res.matches("\\{.*\\}")) {

                        reps1 = formatar(cons, res);
                        algum = true;
                        for (String l : reps1) {
                            reps.add(l);
                        }
                    }

                }
                if (!algum) {
                    resposta.add("Não unificavel");
                } else {
                    resposta.add("\n-? " + cons);
                    resposta.add("\nUnificável");
                    for (String in : reps) {
                        resposta.add(in);
                    }

                }

            }
        }
    }

    ArrayList<String> getarray() {

        return null;
    }

    ArrayList<String> setarray() {
        return null;
    }

    boolean parenteses(String x, int linha, int escolha) {
        ArrayList<Integer> abre = new ArrayList<Integer>();
        ArrayList<Integer> fecha = new ArrayList<Integer>();
        this.dupla1 = new ArrayList<Integer>();
        this.dupla2 = new ArrayList<Integer>();

        //contar parenteses
        for (int i = 0; i < x.length(); i++) {

            if (x.charAt(i) == '(') {
                abre.add(i);
            } else if (x.charAt(i) == ')') {
                fecha.add(i);
            }

        }
        if (!(abre.size() > 0 && fecha.size() > 0)) {
            //tudo certo
            if (escolha == 1) {
                //erro BC - sem parenteses 
                //Esta faltando parenteses linha ("+linha+") - aberto : "+abre.size()+" fechado : "+fecha.size()
                this.Erros.add("Não é uma fbf. Erro 003. Linha: " + linha);
            } else {
                //erro consulta - sem parenteses
                //Esta faltando parenteses na consulta ("+x+") - aberto : "+abre.size()+" fechado : "+fecha.size()
                this.Erros.add("Não é uma fbf. Erro 003. Consulta: " + x);
            }
            return false;
        }
        //formar duplas
        for (int i = abre.size() - 1; i >= 0; i--) {

            for (int j = 0; j < fecha.size(); j++) {

                if (abre.get(i) < fecha.get(j)) {
                    dupla1.add(abre.get(i));
                    dupla2.add(fecha.get(j));
                    abre.remove(abre.get(i));
                    fecha.remove(fecha.get(j));
                    break;
                }

            }
        }
        System.out.println("size abre : " + abre.size());
        System.out.println("size fecha : " + fecha.size());
        if (abre.size() > 0) {

            for (Integer posicao : abre) {
                //parenteses de abrir a mais
                //erro para se caso houver parenteses "(" a mais
                this.Erros.add("Não é uma fbf. Erro 004. Posição: " + posicao);

            }
            return false;
        } else if (fecha.size() > 0) {

            //parenteses de fechar a mais
            for (Integer posicao : fecha) {

                //erro para se caso houver parenteses ")" a mais 
                //"Parentes ')' a mais na posicao : " + posicao + " linha : " + linha
                this.Erros.add("Não é uma fbf. Erro 004. Posição: " + posicao);

            }
            return false;
        } else {
            System.out.println("Aewww");
            return true;
        }
    }

    ArrayList<String> formatar(String var, String comp) {
        String pppp = var;
        ArrayList<String> resp = new ArrayList<String>();
        //cria um array list
        ArrayList<String> array = new ArrayList<String>();

        String temp = "";

        //divide a sentenca e coloca cada parte em uma posicao do array list
        //ex. gosta(joao,maria). 
        /*array(0) = gosta
         array(1) = (
         array(2) = joao
         array(3) = ,
         array(4) = maria
         array(5) = )
         array(6) = .
         */
        for (int i = 0; i < var.length(); i++) {
            if (var.charAt(i) == '(') {
                array.add(temp);
                array.add("(");
                temp = "";
            } else if (var.charAt(i) == ')') {
                array.add(temp);
                array.add(")");
                temp = "";
            } else if (var.charAt(i) == ',') {
                array.add(temp);
                array.add(",");
                temp = "";
            } else {
                temp += var.charAt(i);
            }

        }
        boolean algum = false;
        comp = comp.replace("{", "");
        comp = comp.replace("}", "");
        if (comp.matches(".+\\,.+")) {
            String array1[];
            array1 = comp.split(",");
            for (String each : array1) {
                String array2[];
                array2 = each.split("/");
                //array2[0] = variavel
                //array2[1] = constante

                for (String checar : array) {
                    if (checar.equals(array2[1])) {
                        array.set(array.indexOf(array2[1]), array2[0]);
                    }

                }

            }
            String novo = "";
            for (String x : array) {
                novo += x;
            }
            resp.add("\n" + novo + ".");
            resp.add("Composição: {" + comp + "}");

        } else if (comp.replace(" ", "").equals("")) {
            resp.add("\n" + var);
            resp.add("Composição: {" + comp + "}");
            algum = true;
        } else {
            String array2[];
            array2 = comp.split("/");

            for (String checar : array) {
                if (checar.equals(array2[1])) {
                    array.set(array.indexOf(array2[1]), array2[0]);
                }

            }
            String novo = "";
            for (String x : array) {
                novo += x;
            }
            resp.add("\n" + novo + ".");
            resp.add("Composição: {" + comp + "}");
        }
        return resp;
    }

}
