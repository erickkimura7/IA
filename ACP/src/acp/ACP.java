/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acp;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.print.DocFlavor;

/**
 *
 * @author erick BD1C7A580E9F32854EF534590C3245F8287C2D26486AB68724357555743536C2
 */
public class ACP {

    /**
     * @param args the command line arguments
     */
    public boolean debugUnificar = false;
    public boolean debugMetodos = false;

    // p(X,f(A),f(y)). -> ( p X ( f A ) ( f y ) )
    // p(a,Z). -> ( p a Z )
    // subs1: { /A, /f} - E1: ( ( f A ) ( f y ) )
    // subs1: { /A, /f} - E1: ( ( f A ) )
    // unificar(( ( f A ) ( f y ) ) ,( ( f A ) ) ).
    // Flag 25 subs1: / - E1: ( ( ) ( h Y ) )
    // Flag 25 subs1: { /h,( g a ) /Y, / , /g, /a} - E1: ( ( h ( g a ) ) )
    // { /h,( g a ) /Y, / , /g, /a} - E1: ( ( h ( g a ) ) ) - ( ( ) )
    // Flag 25a subs1: / - E1: ( ( ) ( h ( g a ) ) )
    /*
    public static void main(String[] args) {
        // TODO code application logic here
        ACP acp = new ACP();

        ArrayList<String> BC = new ArrayList<String>();
        ArrayList<String> consulta = new ArrayList<String>();

    
        ((gosta x )) (gosta )
    
        (gosta x ) , gosta
    
    
        
    
    
        BC.add("p(a,Z).");
        BC.add("ancestral(bill,pai(bill)).");
        BC.add("ancestral(david,george).");
        BC.add("q(a).");
        BC.add("p(y,a).");
        BC.add("p(Y,f(W)).");
        BC.add("p(Z,h(y),h(y)).");
        BC.add("p(y,f(y)).");
        BC.add("p(Z,W,f(B)).");

        consulta.add("p(X,Y).");
        consulta.add("q(x).");
        consulta.add("p(f(X),Z).");
        consulta.add("p(f(X),a).");
        consulta.add("p(a,x,h(g(z))).");
        consulta.add("p(X,X).");
        consulta.add("p(X,f(A),f(y)).");

        for (String cons : consulta) {
            for (String base : BC) {
                System.out.println("Teste : " + cons + " - " + base + "\n");
                System.out.println(acp.rodar_unificacao(cons, base));
                System.out.println("\n\n\n\n");
            }

        }
        // System.out.println(acp.primeiro("((()))"));
        // System.out.println(acp.rodar_unificacao(acp.formatar("gosta(maria,Y)"),
        // acp.formatar("gosta(X,andre)")));
        // System.out.println("gosta(X p (Y )Z ,(s ,(a ) aa )asd,asdas
        // )".replace(","," ").replaceAll("\\s+", " "));
        // System.out.println(acp.formatar("gosta(X p(Y ) Z ( s (a)aa ) asd
        // asdas)"));
        // System.out.println(acp.aplicar(" / ", "( ( ) ( h ( g a ) ) )"));
        // System.out.println(acp.aplicar("{ /a, /g, / ,( g a ) /Y, /h}","( ( h
        // ( g a ) ) )"));
        // System.out.println(acp.aplicar("{ /h,( g a ) /Y, / , /g, /a}", "( ( h
        // ( g a ) ) )"));
        // System.out.println(acp.aplicar(" / ","( ( ) ( h Y ) )"));
        //System.out.println(acp.rodar_unificacao("( p a X ( h ( g Z ) ) )", "( p Z ( h Y ) ( h Y ) )"));
        // System.out.println(acp.rodar_unificacao(acp.unificar("( p a X ( h ( g Z ) )
        // )", "( p Z ( h Y ) ( h Y ) )")));
        // System.out.println(acp.aplicar("{ /A, /f}", "( ( f A ) ( f y ) )"));
        // System.out.println(acp.unificar("( p X ( f A ) ( f y ) )", "( p a Z
        // )"));
        // System.out.println(acp.composicao(" /maria", "{joao/X}"));
        // System.out.println(acp.unificar("( ( mae bill ) )","( Y )"));
        // System.out.println(acp.unificar("( gosta joao maria )","( gosta Y
        // maria )"));
        // System.out.println(acp.aplicar(" /gosta","gosta X karina"));
        // System.out.println(acp.constante("( gosta joao maria )"));
        // System.out.println(acp.vazio("(  )"));
     
    }*/

    // E1 e E2 precisao entrar ja em forma de lista
    String unificar(String E1, String E2) {
        if (debugUnificar) {
            System.out.println("unificar(" + E1 + "," + E2 + ").");
        };

        // primeira etapa "E1 quanto E2 sejam constantes ou lista vazia"
        if (debugUnificar) {
            System.out.println("Flag 1");
        };
        if ((constante(E1) && constante(E2))) {
            if (debugUnificar) {
                System.out.println("Flag 2");
            };
            if (E1.equals(E2)) {
                if (debugUnificar) {
                    System.out.println("Flag 3");
                };
                return " /" + E1;
            } else {
                if (debugUnificar) {
                    System.out.println("Flag 4");
                };
                return "Falha";
            }
        }
        if (debugUnificar) {
            System.out.println("Flag 5");
        };
        if (vazio(E1) && vazio(E2)) {
            if (debugUnificar) {
                System.out.println("Flag 6");
            };
            if (E1.equals(E2)) {
                if (debugUnificar) {
                    System.out.println("Flag 7");
                };
                return " / ";
            } else {
                if (debugUnificar) {
                    System.out.println("Flag 8");
                };
                return "Falha";
            }
        }
        if (debugUnificar) {
            System.out.println("Flag 9");
        };
        // verifica se E1 é uma variavel
        if (variavel(E1)) {
            if (debugUnificar) {
                System.out.println("Flag 10");
            };
            // verifica se E1 ocorre em E2
            if (E1.equals(E2)) {
                if (debugUnificar) {
                    System.out.println("Flag 11");
                };
                return " /" + E1;
            }
            if (ocorre(E1, E2)) {
                if (debugUnificar) {
                    System.out.println("Flag 12");
                };
                return "Falha";
            } else {
                if (debugUnificar) {
                    System.out.println("Flag 13");
                };
                if (E2.isEmpty()) {
                    if (debugUnificar) {
                        System.out.println("Flag 14");
                    };
                    E2 = " ";
                }
                return E2 + "/" + E1;
            }
        }
        if (debugUnificar) {
            System.out.println("Flag 15");
        };
        // verifica se E2 é uma variavel
        if (variavel(E2)) {
            if (debugUnificar) {
                System.out.println("Flag 16");
            };
            // verifica se E2 ocorre em E1
            if (ocorre(E2, E1)) {
                if (debugUnificar) {
                    System.out.println("Flag 17");
                };
                return "Falha";
            } else {
                if (debugUnificar) {
                    System.out.println("Flag 18");
                };
                if (E1.isEmpty()) {
                    if (debugUnificar) {
                        System.out.println("Flag 19");
                    };
                    E1 = " ";
                }
                if (debugUnificar) {
                    System.out.println("Flag 20");
                };
                return E1 + "/" + E2;
            }
        }
        if (debugUnificar) {
            System.out.println("Flag 21");
        };
        if (!vazio(E1) && constante(E2)) {
            return "Falha";
        }
        if (!vazio(E2) && constante(E1)) {
            return "Falha";
        }
        // verifica se E1 ou E2 sao listas vazias
        if (vazio(E1) || vazio(E2)) {
            if (debugUnificar) {
                System.out.println("Flag 22");
            };
            return "Falha";

        } else {
            if (debugUnificar) {
                System.out.println("Flag 23");
            };
            String he1 = primeiro(E1);// pega o primeiro elemento de E1
            if (debugUnificar) {
                System.out.println("Flag 24");
            };
            String he2 = primeiro(E2);// pega o primeiro elemento de E2
            if (debugUnificar) {
                System.out.println("Flag 25");
            };
            String subs1 = unificar(he1, he2);// unifica he1 com he2
            if (debugUnificar) {
                System.out.println("Flag 26");
            };
            // se subs1 for falha entao vai retornar falha
            if (subs1 == "Falha") {
                if (debugUnificar) {
                    System.out.println("Flag 27");
                };
                return "Falha";
            }
            if (debugUnificar) {
                System.out.println("Flag 28");
            };
            E1 = E1.replaceAll("\\s+", " ");
            E2 = E2.replaceAll("\\s+", " ");
            String te1 = aplicar(subs1, E1);
            if (debugUnificar) {
                System.out.println("Flag 29");
            };
            String te2 = aplicar(subs1, E2);
            if (debugUnificar) {
                System.out.println("Flag 30");
            };
            String subs2 = unificar(te1, te2);
            if (debugUnificar) {
                System.out.println("Flag 31");
            };
            if (subs2 == "Falha") {
                if (debugUnificar) {
                    System.out.println("Flag 32");
                };
                return "Falha";
            } else {
                if (debugUnificar) {
                    System.out.println("Flag 33");
                };
                return composicao(subs1, subs2);
            }

        }

    }

    // retorna true se for uma lista vazia ou seja "( )" ou "()"
    boolean vazio(String var) {
        if (debugMetodos) {
            System.out.println("Flag 34");
        };
        var = var.replaceAll(" ", "");

        if (var.equals("()")) {
            if (debugMetodos) {
                System.out.println("Flag 35");
            };
            return true;
        } else {
            if (debugMetodos) {
                System.out.println("Flag 36");
            };
            return false;
        }
    }

    // verifica se e uma variavel
    // valido
    boolean variavel(String var) {
        if (debugMetodos) {
            System.out.println("Flag 37");
        };
        var = var.replaceAll("\\s+", " ");
        String x = Character.toString(var.charAt(0));
        if (var.charAt(0) == '(') {
            if (debugMetodos) {
                System.out.println("Flag 38");
            };
            return false;
        }
        if (x.toUpperCase().equals(x)) {
            if (debugMetodos) {
                System.out.println("Flag 39");
            };
            return true;
        } else {
            if (debugMetodos) {
                System.out.println("Flag 40");
            };
            return false;
        }

    }

    // verifica se e uma constante
    // valido
    boolean constante(String var) {
        if (debugMetodos) {
            System.out.println("Flag 41");
        };
        var = var.replaceAll("\\s+", " ");
        String x = Character.toString(var.charAt(0));
        if (var.charAt(0) == '(') {
            if (debugMetodos) {
                System.out.println("Flag 42");
            };
            return false;
        }
        if (x.toLowerCase().equals(x)) {
            if (debugMetodos) {
                System.out.println("Flag 43");
            };
            return true;
        } else {
            if (debugMetodos) {
                System.out.println("Flag 44");
            };
            return false;
        }
    }

    // verifica se E1 ocorre em E2
    // valido
    boolean ocorre(String var, String var1) {
        if (debugMetodos) {
            System.out.println("Flag 45");
        };
        // retira todos os espacos que nao sao necessarios
        var = var.replaceAll("\\s+", " ");
        var1 = var1.replaceAll("\\s+", " ");

        /*
         * Teste System.out.println(var); System.out.println(var1);
         */
        String array[];
        String array1[];

        array = var.split(" ");
        array1 = var1.split(" ");

        /*
         * Teste for (int i = 0 ; i<array.length ; i++){
         * System.out.println(array[i]);
         * 
         * } for (int i = 0 ; i<array1.length ; i++){
         * System.out.println(array1[i]);
         * 
         * }
         */
        for (int i = 0; i < array.length; i++) {

            for (int j = 0; j < array1.length; j++) {
                // System.out.println("Testando "+array[i]+" com "+array1[j]);
                // para que seja testado é necessario que todos sejam diferentes
                // de ( ou )
                if (array[i] != "(" && array[i] != ")" && array1[j] != "(" && array1[j] != ")") {
                    if (array[i] == array1[j]) {
                        // retorna True se E1 ocorrer em E2
                        if (debugMetodos) {
                            System.out.println("Flag 46");
                        };
                        return true;

                    } else {
                        /*
                         * Teste System.out.println("Diferente");
                         */
                    }

                }

            }

        }
        // Retorna False se var nao ocorrer em var2
        if (debugMetodos) {
            System.out.println("Flag 47");
        };
        return false;
    }

    // retorna o primeiro elemento da lista
    // valido
    String primeiro(String var) {
        if (debugMetodos) {
            System.out.println("Flag 48");
        };
        var = var.replaceAll("\\s+", " ");
        String array[];
        array = var.split(" ");
        ArrayList<Integer> abre = new ArrayList<Integer>();
        ArrayList<Integer> fecha = new ArrayList<Integer>();
        ArrayList<Integer> dupla1 = new ArrayList<Integer>();
        ArrayList<Integer> dupla2 = new ArrayList<Integer>();
        String novo = "";
		// System.out.println("Check 1");

        // Faz as distribuicoes dos parenteses nos ArrayList abre e fecha
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals("(")) {
                abre.add(i);
            } else if (array[i].equals(")")) {
                fecha.add(i);
            }
        }
        if (debugMetodos) {
            System.out.println("Flag 49");
        };
        // faz o mapeamento obs:olhar como este algoritmo funciona esta no papel
        // System.out.println("Check 2");
        for (int i = abre.size() - 1; i >= 0; i--) {

            for (int j = 0; j < fecha.size(); j++) {
                if (fecha.get(j) > abre.get(i)) {
                    dupla1.add(abre.get(i));
                    dupla2.add(fecha.get(j));
                    // System.out.println("Excluindo = "+fecha.get(j));
                    fecha.remove(fecha.get(j));

                    break;
                }
            }

        }
        if (debugMetodos) {
            System.out.println("Flag 50");
        };
        // System.out.println("Check 3");
        for (int i = 0; i < dupla1.size(); i++) {
            // System.out.println("Dupla "+ i+" : " + dupla1.get(i));
            // System.out.println("Dupla "+ i+" : " + dupla2.get(i));
        }
        if (debugMetodos) {
            System.out.println("Flag 51");
        };
        int i;
        // System.out.println("Check 4");
        // pega a posicao do segundo parenteses aberto e salva em i
        for (i = 0; i < dupla1.size(); i++) {
            if (dupla1.get(i) == 1) {
                break;
            }
        }
        if (debugMetodos) {
            System.out.println("Flag 52");
        };
        // System.out.println("Check 5");
        if (array[1].equals("(")) {

            for (i = 0; i < dupla1.size(); i++) {
                if (dupla1.get(i) == 1) {
                    break;
                }
            }
            if (debugMetodos) {
                System.out.println("Flag 53");
            };
            // pegar todos os valores entre dupla1 e dupla2
            // dupla1 = segundo parenteses
            for (int j = dupla1.get(i); j <= dupla2.get(i); j++) {
                novo += array[j] + " ";
            }
        } else {
            if (debugMetodos) {
                System.out.println("Flag 54");
            };
            novo = array[1];
        }

        // System.out.println(novo);
        if (debugMetodos) {
            System.out.println("Flag 55");
        };
        return novo;
    }

    // faz a substituicao das variaveis pelas constantes ou variaveis pelas
    // variaveis
    String aplicar(String var1, String var2) {
        if (debugMetodos) {
            System.out.println("Flag 56");
        };

        if (var1.charAt(0) == '{') {
            if (debugMetodos) {
                System.out.println("Flag 57");
            };
            String novo = "";
            // System.out.println("Flag 58");
            var1 = var1.replace('{', ' ');
            var1 = var1.replace('}', ' ');

            // var1 = var1.replaceAll(" ", "");
            //System.out.println(var1);
            String partes[];
            partes = var1.split(",");
            String array2[];
            array2 = var2.split(" ");
            int fim = 0;
            int inicio = 0;
            for (String parte : partes) {
                //System.out.println(parte);
                String array[];
                array = parte.split("/");

//				for (String l : array2) {
//					System.out.println("Array2 : " + l);
//				}
//				for (String x : array) {
//					System.out.println("Flag array : " + x);
//				}
                array[0] = array[0].replaceAll("\\s+", " ");

                array[1] += " ";
                array[1] = array[1].replaceAll(" ", "");
//				System.out.println("Flag 59");
//				System.out.println(array[0].equals(" "));
//				System.out.println(array[1].equals(""));
//				System.out.println("(" + array[1] + ")");

                if (array[0].equals(" ") && array[1].equals("")) {
                    //System.out.println("Flag 2 ");

                    for (int i = 0; i < array2.length; i++) {
                        if (array2[i].equals("(") && array2[i + 1].equals(")")) {
                            array2[i] = "";
                            array2 = novo(array2, i);
                            //System.out.println("-");
                            array2[i + 1] = "";
                            array2 = novo(array2, i + 1);
                        }
                    }

                    for (int i = 0; i < array2.length; i++) {
                        novo += array2[i] + " ";
                    }
                    novo = novo.replaceAll("\\s+", " ");

                } else if (array[0].equals(" ") || array[0].charAt(0) == '(') {
                    //System.out.println("Flag 3");
                    for (int i = 0; i < array2.length; i++) {
                        //System.out.println("comparando " + array[1] + " com " + array2[i]);
                        if (array[1].equals(array2[i])) {
                            //System.out.println("replace ( ) : " + array2[i]);
                            array2[i] = "";
                            array2 = novo(array2, i);
                            break;
                        }
                    }
                    novo = "";
                    for (int i = 0; i < array2.length; i++) {

                        novo += array2[i] + " ";
                    }

                    novo = novo.replaceAll("\\s+", " ");
                    // System.out.println("Flag 2 : "+novo);

                } else {
                    //System.out.println("Flag 4");
                    var2 = var2.replaceAll(array[1], array[0]);
                    // System.out.println("Flag 3: "+var2);
                    novo = var2;
                }
            }
            if (debugMetodos) {
                System.out.println("Flag 60");
            };
            return novo;

        } else {
            if (debugMetodos) {
                System.out.println("Flag 61");
            };
            String array[];
            array = var1.split("/");
            String array2[];
            array2 = var2.split(" ");
            String novo = "";
            boolean segundo = false;
            int inicio = 0;
            int fim = 0;

            // System.out.println("Check 1");
            // System.out.println(array[0]);
//			System.out.println(array[1]);
//			System.out.println("Aqui 1");
            if (array[0].equals(" ") && array[1].equals(" ")) {
                //System.out.println("Aqui 2");

                for (int i = 0; i < array2.length; i++) {
                    if (array2[i].equals("(") && array2[i + 1].equals(")")) {
                        array2[i] = "";
                        array2 = novo(array2, i);
                        array2[i] = "";
                        array2 = novo(array2, i);

                    }
                }

                for (int i = 0; i < array2.length; i++) {
                    novo += array2[i] + " ";
                }
                novo = novo.replaceAll("\\s+", " ");
                return novo;

            } else if (array[0].equals(" ")) {
                if (array[1].equals(" ")) {
                    var2 = var2.replace("( )", "");
                    return var2.replaceAll("\\s+", " ");
                }
                for (int i = 0; i < array2.length; i++) {
                    if (array[1].equals(array2[i])) {
                        array2[i] = "";
                        break;
                    }
                }
                for (int i = 0; i < array2.length; i++) {
                    novo += array2[i] + " ";
                }
                novo = novo.replaceAll("\\s+", " ");
                return novo;

            } else {
                var2 = var2.replaceAll(array[1], array[0]);
                return var2;
            }
        }

    }

    /*
     * else if (var.charAt(0) == '{') { var = var.replace('{', ','); var1 =
     * var1.replace("}", var); return var1;
     */
    String composicao(String var, String var1) {
        if (debugMetodos) {
            System.out.println("Flag 62");
        };
        String temp = var1;
        temp = temp.replaceAll(" ", "");
        if (temp.equals("/")) {
            if (debugMetodos) {
                System.out.println("Flag 63");
            };
            return "{" + var + "}";

        } else {
            var1 = var1.replace('}', ',');
            var = var.replace('{', ' ');
            var = var.replaceAll("\\s+", " ");
            if (debugMetodos) {
                System.out.println("Flag 64");
            };
            return var1 + var + "}";
        }

    }

    // retorna o resto de E1 sendo E1 - HE1
    // ou
    // retorna o resto de E2 sendo E2 - HE2
    String resto(String var, String var1) {

        return "";
    }

    // retorna a string formatada a partir de ex. gosta(joao,maria). -> ( gosta joao maria )
    // 
    String formatar(String var) {

        ArrayList<String> array = new ArrayList<String>();
        String temp = "";
        if (debugMetodos) {
            System.out.println("Flag 65");
        };

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
                temp = "";
            } else {
                temp += var.charAt(i);
            }

        }
        if (debugMetodos) {
            System.out.println("Flag 66");
        };
        ArrayList<String> temp00 = new ArrayList<String>();
        for (String x : array) {
            //System.out.println(x);
            if (!x.replace(" ", "").equals("")) {
                temp00.add(x);
            }
        }
        if (debugMetodos) {
            System.out.println("Flag 67");
        };
//		for (String x : temp00) {
//			System.out.println("temp00 : " + x);
//		}
        if (debugMetodos) {
            System.out.println("Flag 68");
        };
        ArrayList<String> a = new ArrayList<String>();
        int maior = 0;

        for (int i = 0; i < temp00.size(); i++) {
            if (temp00.get(i).equals("(")) {
                if (i > maior) {
                    maior = i;
                }
            }

        }
        if (debugMetodos) {
            System.out.println("Flag 69");
        };
        int p;
        for (p = 0; p < temp00.size(); p++) {

            if (temp00.get(p + 1).equals("(")) {
                a.add(temp00.get(p + 1));
                a.add(temp00.get(p));
                p++;
            } else {
                a.add(temp00.get(p));
            }

            if (p > maior) {
                break;
            }
        }
        if (debugMetodos) {
            System.out.println("Flag 70");
        };
        for (int i = p + 1; i < temp00.size(); i++) {
            a.add(temp00.get(i));
        }
        if (debugMetodos) {
            System.out.println("Flag 71");
        };
        String novo = "";
        for (String g : a) {
            novo += g + " ";
            //System.out.println("g : " + g);
        }
        if (debugMetodos) {
            System.out.println("Flag 72");
        };
        novo = novo.replace(",", " ");
        novo = novo.replaceAll("\\s+", " ");
        if (debugMetodos) {
            System.out.println("Flag 73");
        };
        return novo;
    }

    String[] novo(String[] var, int index) {

        for (int i = index; i < var.length - 1; i++) {
            //System.out.println(var[i]);
            var[i] = var[i + 1];
        }

        var[var.length - 1] = "";
        if (debugMetodos) {
            System.out.println("Flag 74");
        };
        return var;
    }

    String rodar_unificacao(String primeiro, String segundo) {
        String var;

        var = unificar(formatar(primeiro), formatar(segundo));
        if (!var.equals("Falha")) {
            if (debugMetodos) {
                System.out.println("Flag 75");
            };
            var = var.replace("{", "");
            var = var.replace("}", "");
            String novo = "}";
            String array[];

            array = var.split(",");
            if (debugMetodos) {
                System.out.println("Flag 76");
            };
            for (String each : array) {
                if (!each.isEmpty()) {
                    String array2[] = each.split("/");
                    // array2[1]=array2[1].replace(" ", "");
                    if (variavel(array2[1]) && (constante(array2[0]) || variavel(array2[0]))
                            && !array2[0].equals(" ")) {
                        if (debugMetodos) {
                            System.out.println("Flag 77");
                        };
                        novo = each + "," + novo;

                    }
                }

            }
            if (debugMetodos) {
                System.out.println("Flag 78");
            };
            String novonovo = "";
            for (int i = 0; i < novo.length(); i++) {
                if (i != novo.lastIndexOf(',')) {
                    novonovo += novo.charAt(i);
                }
            }
            if (debugMetodos) {
                System.out.println("Flag 79");
            };
            novonovo = "{" + novonovo;

            return novonovo;
        } else {
            if (debugMetodos) {
                System.out.println("Flag 80");
            };
            return "Falha";
        }
    }

}
