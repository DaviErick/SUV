
import java.util.*;
//esse projeto foi desenvolvido no eclipse
public class Main {

    // VARIAVEIS PUBLICAS
    static int contagemU = 0;
    static int contagemV = 0;
    static int contagemR = 0;

    static String usuarios[][] = new String[99][4];
    static String vacina[][] = new String[99][5];
    static String registro[][] = new String[99][4];

    static Scanner ler = new Scanner(System.in);

    public static void main(String[] args) {

        boolean sair = false;

        do {
            exibirMenu();
            String opcao = ler.next();

            switch (opcao) {
                case "1":
                    cadastrarNovoUsuario();
                    break;
                case "2":
                    cadastrarVacina();
                    break;
                case "3":
                    if (usuarios[0][0] == null && vacina[0][0] == null) {
                        System.out.println("Primeiro Cadastre-se e adicione uma vacina.");
                        continue;
                    } else {
                        Registro();
                        break;
                    }
                    // verificar ser ja tem alguém cadastrado.
                case "4":
                    if (registro[0][0] == null) {
                        System.out.println("Primeiro se registre.");
                        continue;
                    } else {
                        Estatistica();
                        break;
                    }
                case "5":
                    if (registro[0][0] == null) {
                        System.out.println("Registre-se antes de usar essa opção.");
                        continue;
                    } else {
                        tabelas();
                        break;
                    }
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

            System.out.print("Deseja voltar ao menu principal? (s/n): ");
            String resposta;
            resposta = ler.next().toLowerCase();
            /*
             * O método .toLowerCase() em Java é usado para converter todos os caracteres de
             * uma string para minúsculas. Ele não altera a string original, mas retorna uma
             * nova string com todos os caracteres em minúsculas.
             * 
             */
            sair = !resposta.equals("s");

        } while (!sair);

        System.out.println("Programa encerrado.");

    }

    private static void exibirMenu() {
        System.out.println("Menu:");
        System.out.println("1- Cadastrar novo usuario.");
        System.out.println("2- Cadastrar vacina.");
        System.out.println("3- Registrar vacinação.");
        System.out.println("4- Requerer relatório do usuário.");
        System.out.println("5- Requerer relatório de vacina.");
        System.out.print("Escolha uma opção: ");
    }

    private static void cadastrarNovoUsuario() {
        // CADASTRO DE USUARIOS

        int i = contagemU;
        String cpf, numero, data;
        long xi = 0;
        String nomeCompleto;
        boolean nomeValido = true;
        boolean teste = true;
        boolean verificar = false;

        if (usuarios[0][0] == null || usuarios[0][0].isEmpty()) {
            contagemU++;
            verificar = !verificar;
        }

        System.out.println("CADASTRANDO USUARIOS");
        String continua = "s";

        ler.nextLine();
        while (continua.equals("s")) {
            do {
                System.out.println("Digite o seu nome completo:");
                nomeCompleto = ler.nextLine();
                nomeValido = validarNomeCompleto(nomeCompleto);

                if (!nomeValido) {
                    System.out.println("O nome não é válido. Por favor, tente novamente.");
                } else {
                    usuarios[i][0] = nomeCompleto;
                }
            } while (!nomeValido);

            while (teste) {
                System.out.print("Digite o seu CPF: ");
                try {
                    cpf = ler.next();
                    if (cpf.length() == 11) {
                        xi = Long.parseLong(cpf);
                        if (validarCPF(cpf)) {
                            usuarios[i][1] = Long.toString(xi);
                            teste = false;
                        } else
                            System.out.println("CPF Invalido");
                    } else {
                        System.out.println("Infome 11 digitos númericos");
                    }

                } catch (Exception e) {

                    System.out.println("Digite somente números");

                }
            }
            ler.nextLine();// serve para capturar o ENTER, assim evitando erros.

            teste = true;

            while (teste) {
                System.out.println("Adote que todos os meses têm 30 dias.");
                System.out.print("Digite a data (dia/mês/ano): ");
                try {
                    data = ler.nextLine();

                    if (validarD(data)) {
                        usuarios[i][2] = data;
                        teste = false;
                    } else {
                        System.out.println("Data inválida. Tente novamente.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Erro de formato. Tente novamente.");
                }
            }

            teste = true;
            while (teste) {
                System.out.print("Digite o seu número de celular, com o DDD: ");
                try {
                    numero = ler.next();
                    if (numero.length() == 11) {
                        xi = Long.parseLong(numero);
                        if (xi >= 0) {
                        } else {
                            System.out.println("Não coleque números negativos");
                            continue;
                        }

                        if (validarN(numero)) {
                            usuarios[i][3] = Long.toString(xi);
                            teste = false;
                        } else {
                            System.out.println("Numero invalido");
                        }

                    } else {
                        System.out.println("Informe 11 dígitos numéricos, sem caracteres especiais ou espaços.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Digite somente números");
                }
            }

            i++;
            contagemU++;
            System.out.print("Deseja cadastrar mais um usuario [s/n]:  ");
            teste = true;
            continua = ler.next();
        }

        if (verificar) {
            contagemU--;
        }

    }

    private static void cadastrarVacina() {

        // CADASTRO DE VACINAÇÃO
        boolean verificar = false;
        boolean teste = true;
        int l;
        String dataValidade;
        String teste12;

        for (l = contagemV; l < 99; l++) {
            if (vacina[l][0] == null || vacina[l][0].isEmpty()) {
                contagemV++;
                verificar = true;
            }

            System.out.print("Escreva o nome da vacina: ");
            vacina[l][0] = ler.next();

            teste = true;
            while (teste) {
                System.out.print("Escreva o lote da vacina: ");
                teste12 = ler.next();
                if (verificarLN(teste12)) {
                    vacina[l][1] = teste12;
                    teste = false;
                } else {
                    System.out.println("Não digite caracteres especiais, só numeros e letras!");
                    continue;

                }

            }

            teste = true;
            ler.nextLine();

            while (teste) {
                System.out.print("Escreva a validade da vacina (mês/ano): ");

                try {
                    dataValidade = ler.nextLine();

                    if (validarDataDeValidade(dataValidade)) {
                        vacina[l][2] = dataValidade;
                        teste = false;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Erro de formato. Tente novamente.");
                }
            }

            System.out.print("Escreva a data de Fabricação da vacina: ");

            teste = true;

            while (teste) {
                System.out.println("Adote que todos os meses têm 30 dias.");
                System.out.print("Digite a data (dia/mês/ano): ");
                try {
                    String data2 = ler.nextLine();

                    if (validarD(data2)) {
                        vacina[l][3] = data2;
                        teste = false;
                    } else {
                        System.out.println("Data inválida. Tente novamente.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Erro de formato. Tente novamente.");
                }
            }

            System.out.print("Escreva o nome do fabricante da vacina: ");
            vacina[l][4] = ler.next();

            System.out.println();
            if (verificar) {
                contagemV--;
                verificar = false;
            }
            contagemV++;
            System.out.println("O nome da vacina é: " + vacina[l][0]);
            System.out.println("O lote da vacina é: " + vacina[l][1]);
            System.out.println("A validade da vacina é: " + vacina[l][2]);
            System.out.println("A data de Fabricação da vacina é: " + vacina[l][3]);
            System.out.println("A Fabricante da vacina é: " + vacina[l][4]);

            System.out.println();

            System.out.println("Deseja cadastrar mais uma vacina?");
            System.out.print("Escreva s ou n: ");
            String opcao2 = ler.next();

            if (!opcao2.equals("s")) {
                break;
            }
        }

    }

    private static void Registro() {

        int cont = contagemR;
        int XX = 0;
        int x = 0;

        int tempora = 0;
        String test;

        String data;
        boolean teste = true;
        boolean teste1 = true;
        boolean verificar = false;
        int numero;

        while (true) {

            if (registro[0][0] == null || registro[0][0].isEmpty()) {
                contagemR++;
                verificar = true;
            }
            System.out.println("Selecione qual usuario vai ser vacinado: ");
            while (x < contagemU) {
                XX = x + 1;
                System.out.println(XX + " - " + usuarios[x][0]);
                x = x + 1;
            }

            while (teste1) {
                if (teste1 == true) {

                    test = ler.next();

                    if (verificarLetras(test)) {
                        System.out.println("Não digite letras.");
                        continue;
                    } else {
                        tempora = Integer.parseInt(test);
                        teste1 = false;
                    }
                }
            }
            teste1 = true;
            tempora = tempora - 1;
            registro[cont][0] = usuarios[tempora][0];

            x = 0;
            XX = 0;

            System.out.println("Selecione qual vacina vai ser aplicada: ");
            while (x < contagemV) {
                XX = x + 1;
                System.out.println(XX + " - " + vacina[x][0]);
                x = x + 1;
                tempora = 0;
            }

            while (teste1) {
                if (teste1 == true) {

                    test = ler.next();

                    if (verificarLetras(test)) {
                        System.out.println("Não digite letras.");
                        continue;
                    } else {
                        tempora = Integer.parseInt(test);
                        teste1 = false;
                    }
                }
            }
            teste1 = true;
            tempora = tempora - 1;
            registro[cont][1] = vacina[tempora][0];

            while (true) {
                System.out.println("Infome quantas doses, vai ser aplicada: ");

                if (teste == true) {
                    // Se a entrada é um número inteiro, atribui à variável 'numero' e sai do loop
                    numero = ler.nextInt();
                    registro[cont][2] = Integer.toString(numero);
                    break;
                } else {
                    // Se a entrada não for um número inteiro, exibe mensagem
                    System.out.println("Por favor, digite um número inteiro válido.");
                    ler.nextLine();
                }
            }

            ler.nextLine();
            while (teste) {
                System.out.println("Adote que todos os meses têm 30 dias.");
                System.out.print("Qual data voce deseja vacinar: ");
                try {
                    data = ler.nextLine();

                    if (validarD(data)) {
                        registro[cont][3] = data;
                        teste = false; // Saia do loop quando a data for válida
                    } else {
                        System.out.println("Digite a data assim dia/mês/ano.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Erro de formato. Tente novamente.");
                }
            }
            if (verificar) {
                contagemR--;
                verificar = false;
            }

            contagemR++;

            System.out.println("Deseja lançar mais um registro: ");
            System.out.print("Escreva s ou n: ");
            x = 0;
            String opc = ler.next();

            if (opc.equals("s")) {
                cont++;
                teste = true; // Resetar teste para o próximo registro
            } else {
                break;
            }
        }
    }

    private static void Estatistica() {

        int XX = 0;
        int x = 0;
        int tempora1 = 0;
        int i = 0;
        int j; // contagem de vacinas filtradas
        int y = 0;
        int dose = 0;
        int total = 0;
        String nome;
        String vacinas[][] = new String[99][2];
        boolean teste = false;
        boolean teste1 = true;

        while (teste1) {
            System.out.println("Selecione qual usuário você vai ver o registro: ");

            while (x < contagemU) {
                XX = x + 1;
                System.out.println(XX + " - " + usuarios[x][0]);
                x = x + 1;
            }

            while (!teste) {
                try {
                    System.out.println("Escolha o número: ");
                    tempora1 = ler.nextInt();

                    if (verificarLetras(Integer.toString(tempora1))) {
                        System.out.println("Não coloque letras, só o número");
                    } else {
                        // Lógica adicional se necessário
                        teste = true; // Se a entrada for válida, define teste como true para sair do loop
                        break;
                    }
                } catch (Exception e) {
                    System.out.println("Digite somente números");
                    ler.next();
                }
            }

            teste = false;

            tempora1 = tempora1 - 1;
            nome = usuarios[tempora1][0];

            // processamento
            j = 0;
            // nomes igual
            for (i = 0; i < contagemR; i++) {
                if (nome.equals(registro[i][0])) {
                    vacinas[j][0] = registro[i][1];
                    vacinas[j][1] = registro[i][2];
                    j++;

                }
            }

            // vacinas e doses

            for (i = 0; i < contagemV; i++) {
                dose = 0;
                total = 0;
                for (y = 0; y < j; y++) {
                    if (vacina[i][0].equals(vacinas[y][0])) {
                        dose = Integer.parseInt(vacinas[y][1]);
                        total = total + dose;
                        teste = true;
                    }
                }
                if (teste) {

                    System.out.println("O " + nome + " tomou " + total + " doses da vacina " + vacina[i][0]);
                    teste = false;
                }

            }
            System.out.println("Deseja lançar mais um registro: ");
            System.out.print("Escreva s ou n: ");
            x = 0;
            String opc = ler.next();

            if (opc.equals("s")) {

                teste = true; // Resetar teste para o próximo registro
            } else {
                break;
            }

        }

    }

    public static void tabelas() {
        String resp = "s";
        int tempora = 0;
        int XX = 0;
        int cont = 0;
        int x = 0;
        String hoje = "0";
        System.out.println("Digite a data de hoje");
        hoje = ler.next();

        if (validarD(hoje)) {
            System.out.println("Data válida.");
        } else {
            System.out.println("Data inválida. Tente novamente.");
        }

        while (resp.equals("s")) {
            System.out.println("Digite o número da vacina que você deseja exibir a tabela");
            System.out.println("Selecione qual vacina vai ser aplicada: ");
            while (x < contagemV) {
                XX = x + 1;
                System.out.println(XX + " - " + vacina[x][0]);
                x = x + 1;
            }

            // colocando vacina e usuario no mesmo registro
            tempora = ler.nextInt();
            tempora = tempora - 1;
            registro[cont][1] = vacina[tempora][0];
            // fim do registro

            // convertendo data em idade e atualizando a tabela
            int idadeGerada = 0;
            String dataHoje[] = hoje.split("/");
            int idades[] = new int[5];

            for (int n = 0; n < contagemR; n++) {
                if (vacina[tempora][0].equals(registro[n][1])) {
                    // aqui executa caso a validacao seja bem sucedida
                    String idadeUsuario = registro[n][0];

                    for (x = 0; x < contagemU; x++) {
                        if (idadeUsuario.equals(usuarios[x][0])) {
                            idadeUsuario = usuarios[x][2];
                            break;
                        } else {
                            continue;
                        }
                    }

                    String idadesUsuarios[] = idadeUsuario.split("/");
                    int anoHoje = Integer.parseInt(dataHoje[2]);
                    int diaUsuario = Integer.parseInt(idadesUsuarios[0]);
                    int mesUsuario = Integer.parseInt(idadesUsuarios[1]);
                    int anoUsuario = Integer.parseInt(idadesUsuarios[2]);
                    int doses = Integer.parseInt(registro[n][2]);

                    // Verificando a diferença de anos
                    idadeGerada = anoHoje - anoUsuario;

                    // Verificando os casos de mês e dia, caso o Usuário ainda não tenha feito
                    // aniversario
                    if (dataHoje[1].equals(idadesUsuarios[1]) && Integer.parseInt(dataHoje[0]) < diaUsuario) {
                        idadeGerada--;
                    } else if (Integer.parseInt(dataHoje[1]) < mesUsuario) {
                        idadeGerada--;
                    }

                    System.out.println("Usuário: " + usuarios[n][0]);
                    System.out.println("Idade gerada: " + idadeGerada);

                    // Armazenando as idades nas tabelas
                    if (0 <= idadeGerada && idadeGerada <= 5) {
                        idades[0] = idades[0] + doses;
                        doses = 0;
                    } else if (6 <= idadeGerada && idadeGerada <= 10) {
                        idades[1] = idades[1] + doses;
                        doses = 0;
                    } else if (11 <= idadeGerada && idadeGerada <= 20) {
                        idades[2] = idades[2] + doses;
                        doses = 0;
                    } else if (21 <= idadeGerada && idadeGerada <= 35) {
                        idades[3] = idades[3] + doses;
                        doses = 0;
                    } else if (36 <= idadeGerada && idadeGerada <= 50) {
                        idades[4] = idades[4] + doses;
                        doses = 0;
                    }
                    System.out.println(doses);
                } // fim da verificacao

                // aqui termina
            }

            // fim da atualizacao
            // verifica se quer mostrar a tabela de mais um
            System.out.println(registro[cont][1]);
            System.out.println("idade | quantidade");
            System.out.println("0-5 | " + idades[0]);
            System.out.println("6-10 |" + idades[1]);
            System.out.println("11-20 |" + idades[2]);
            System.out.println("20-35 |" + idades[3]);
            System.out.println("35-50 |" + idades[4]);
            System.out.println("Deseja ver a tabela de mais uma vacina?");
            resp = ler.next();
            tempora = 0;
            XX = 0;
            cont = 0;
            x = 0;
            idades[0] = 0;
            idades[1] = 0;
            idades[2] = 0;
            idades[3] = 0;
            idades[4] = 0;

        }

    }

    public static boolean validarCPF(String cpf) {

        int[] numeros = new int[11];
        char primeiroDigito = cpf.charAt(0);

        // Verifica se todos os dígitos são iguais
        for (int i = 0; i < cpf.length(); i++) {
            if (cpf.charAt(i) != primeiroDigito) {
                break;
            }
            if (i == cpf.length() - 1) {
                return false; // Todos os dígitos são iguais, CPF inválido
            }
        }

        for (int i = 0; i < 11; i++) {
            numeros[i] = Character.getNumericValue(cpf.charAt(i));
        }

        int soma = 0;
        int primeiroCalculado, segundoCalculado;

        for (int i = 0, peso = 10; i < 9; i++, peso--) {
            soma += numeros[i] * peso;
        }

        primeiroCalculado = 11 - (soma % 11);
        if (primeiroCalculado > 9) {
            primeiroCalculado = 0;
        }

        soma = 0;

        for (int i = 0, peso = 11; i < 10; i++, peso--) {
            soma += numeros[i] * peso;
        }

        segundoCalculado = 11 - (soma % 11);
        if (segundoCalculado > 9) {
            segundoCalculado = 0;
        }

        for (int i = 0; i < contagemU; i++) {
            if (cpf.equals(usuarios[i][1])) {
                System.out.println("CPF já cadastrado, tente novamente com um CPF válido.");
                return false;
            }
        }
        return primeiroCalculado == numeros[9] && segundoCalculado == numeros[10];
    }

    public static boolean validarN(String numero) {
        char validar = numero.charAt(0);
        int temporal = 0;

        // Verifica se todos os dígitos são iguais
        for (int i = 0; i < numero.length(); i++) {
            if (numero.charAt(i) != validar) {
                temporal = (int) (numero.charAt(i));
                break;
            }
            if (temporal >= 0) {
                System.out.println("Informe um número não negativo");
            }

            if (i == numero.length() - 1) {
                System.out.println("Não existe número com todos os digitos iguais, por conta do DDD");
                return false; // Todos os dígitos são iguais, número inválido
            }
        }

        return true;
    }

    public static boolean validarD(String data) {
        // Verificar se há letras na entrada
        if (verificarLetras(data)) {
            System.out.println("Não digite letras.");
            return false;
        }

        if (data.length() == 10) {
            if (data.contains(" ")) {
                System.out.println("Não digite com espaços em branco.");
                return false;
            }

            // Divisão da data em dia, mês e ano
            String[] parteData = data.split("/");

            if (parteData.length != 3) {
                System.out.println("Formato de data inválido.");
                return false;
            }

            try {
                int dia = Integer.parseInt(parteData[0]);
                int mes = Integer.parseInt(parteData[1]);
                int ano = Integer.parseInt(parteData[2]);

                // Verificação das condições
                if (dia >= 1 && dia <= 30 && mes >= 1 && mes <= 12 && ano <= 2023 && ano > 1920) {

                    return true;
                } else {
                    System.out.println("Data inválida!");
                    return false;
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro de formato na data.");
                return false;
            }
        } else {
            System.out.println("Digite a data correta.");
            System.out.println("Exemplo: 00/00/0000");
            return false;
        }
    }

    public static boolean verificarLetras(String verificarDATA) {
        return verificarDATA.matches(".*[a-zA-Z]+.*");

    }

    public static boolean verificarLN(String verificarLN) {
        return verificarLN != null && verificarLN.matches("^[a-zA-Z0-9]*$");

    }

    public static boolean validarNomeCompleto(String nomeCompleto) {
        if (nomeCompleto == null || nomeCompleto.isEmpty()) {
            return false;
        }
        if (nomeCompleto.trim().isEmpty()) {
            // .trim remove os espaços
            System.out.println("Por favor, digite o seu nome.");
            return false;
        }
        if (nomeCompleto.contains("  ")) {
            System.out.println("Não pode conter mais de dois espaços em sequencia.");
            return false;
        }

        // split serve para localizar os espaços e partir em substrings
        // neste contexto e usado para separar o primeiro nome dos demais
        // "\\" indica localizar e "s" indica o espaço
        // partes representa todos os nomes repartidos pelo espaco
        // parte representa apenas um nome
        char lol = nomeCompleto.charAt(0);

        // Verifica se tem uma vogal
        if (" ".indexOf(lol) != -1) {
            return false;
        }
        String[] partes = nomeCompleto.split("\\s+");

        // percorre o loop foreach
        for (String parte : partes) {
            // parte = parte.replaceAll("\\s+", "+");

            // verifica se a primeira letra e maiuscula
            if (!Character.isUpperCase(parte.charAt(0))) {
                System.out.println("A primeira letra de um nome deve ser maiuscula");
                return false;
            }

            // verifica se possui alguma letra maiuscula alem da primeira letra
            for (int i = 1; i < parte.length(); i++) {
                if (Character.isUpperCase(parte.charAt(i))) {
                    System.out.println("Somente a primeira letra do nome deve ser maiuscula");
                    return false;
                }
            }
            // verifica se tem numero
            for (int i = 0; i < parte.length(); i++) {
                if (Character.isDigit(parte.charAt(i))) {
                    System.out.println("Um nome não pode conter numeros");
                    return false;
                }
            }
            // fim da verificacao se tem numero
            // a partir daqui todas as letras se tornam minusculas para verificar se tem
            // mais de 3 letras iguais
            // essa medida e necessaria pois A em maiusculo e diferente de "a" em minusculo
            // A != a
            parte = parte.toLowerCase();
            if (parte.length() < 3 || contemSequenciaRepetida(parte)) {
                return false;
            }

            // Parte em que verifica se tem pelo menos uma vogal e uma consoante

            boolean temVogal = false;
            boolean temConsoante = false;
            boolean temCaractere = true;

            // Percorre cada caractere do nome
            for (int i = 0; i < parte.length(); i++) {
                char caractere = parte.charAt(i);

                // Verifica se tem uma vogal
                if ("aeiou".indexOf(caractere) != -1) {
                    temVogal = true;
                }
                // Verifica se é uma consoante
                else if ("qwrtypsdfghjklçzxcvbnm".indexOf(caractere) != -1) {
                    temConsoante = true;
                }
                if ("!@#$%&*(){}?/:;<>|.,[]º-+=_".indexOf(caractere) != -1) {
                    System.out.println("O nome não deve ter caracteres especiais");
                    temCaractere = false;
                    return temCaractere;
                }

            }
            if (!temVogal || !temConsoante) {
                System.out.println("O nome deve ter no minimo uma vogal e uma consoante");
            }

            // Retorna true se o nome contiver pelo menos uma vogal e uma consoante
            return temVogal && temConsoante;

        }
        return true;
    }

    public static boolean contemSequenciaRepetida(String parte) {
        for (int i = 0; i < parte.length() - 2; i++) {
            if (parte.charAt(i) == parte.charAt(i + 1) && parte.charAt(i) == parte.charAt(i + 2)) {
                System.out.println("O nome não pode conter 3 letras repetidas em sequencia");
                return false;
            }
        }

        return false;
    }

    public static boolean validarDataDeValidade(String dataValidade) {
        // Verificar se há letras na entrada
        if (verificarLetras(dataValidade)) {
            System.out.println("Não digite letras.");
            return false;
        }

        if (dataValidade.length() == 7) {
            if (dataValidade.contains(" ")) {
                System.out.println("Não digite com espaços em branco.");
                return false;
            }

            // Divisão da data em mês e ano
            String[] parteData = dataValidade.split("/");

            if (parteData.length != 2) {
                System.out.println("Formato de data inválido.");
                return false;
            }

            try {
                int mes = Integer.parseInt(parteData[0]);
                int ano = Integer.parseInt(parteData[1]);

                // Verificação das condições
                if (mes >= 1 && mes <= 12 && ano > 1900) {
                    System.out.println("Data válida!");
                    return true;
                } else {
                    System.out.println("Data inválida!");
                    return false;
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro de formato na data.");
                return false;
            }
        } else {
            System.out.println("Digite a data correta.");
            System.out.println("00/0000");
            return false;
        }
    }

}
