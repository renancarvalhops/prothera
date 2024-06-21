import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.text.NumberFormat;

public class FuncionarioServices {
    private ArrayList<Funcionario> funcionarios;
    private HashMap<String, ArrayList<Funcionario>> funcionariosAgrupadosPorFuncao = new HashMap<>();

    public FuncionarioServices(ArrayList<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public boolean remover(String nome) {
        return funcionarios.removeIf(funcionario -> funcionario.getNome().equals(nome));
    }

    public void aumentarSalarios(double porcentagem) {
        BigDecimal taxa = new BigDecimal(1 + porcentagem / 100);
        BigDecimal salarioAtual;

        for(Funcionario funcionario : funcionarios) {
            salarioAtual = funcionario.getSalario();
            funcionario.setSalario(salarioAtual.multiply(taxa));
        }
    }

    public void agruparPorFuncao() {
        String funcao;

        for(Funcionario funcionario : funcionarios) {
            funcao = funcionario.getFuncao();
            ArrayList<Funcionario> listaFuncionario = funcionariosAgrupadosPorFuncao.get(funcao);

            if(listaFuncionario == null) {
                listaFuncionario = new ArrayList<>();
                listaFuncionario.add(funcionario);
                funcionariosAgrupadosPorFuncao.put(funcao, listaFuncionario);
            } else {
                listaFuncionario.add(funcionario);
            }
        }
    }

    public void imprimirTodos(ArrayList<Funcionario> funcionarios, String titulo) {
        String base = "=";
        String tituloFormatado = "\n\n\n\n" + base.repeat(40) + " " + titulo + 
                                " " + base.repeat(40) + "\n\n";

        System.out.println(tituloFormatado);

        if(funcionarios.size() == 0) {
            System.out.println("Não há correspondências para essa requisição");
        } else {
            for(Funcionario funcionario : funcionarios) {
                System.out.println(funcionario);
            }
        }

        System.out.println("\n" + base.repeat(tituloFormatado.length() - 6));
    }

    public void imprimirTodos() {
        imprimirTodos(funcionarios, "Lista de funcionários");
    }

    public void imprimirTodosPorFuncao() {
        for(String funcao : funcionariosAgrupadosPorFuncao.keySet()) {
            imprimirTodos(funcionariosAgrupadosPorFuncao.get(funcao),
                            "Lista de funcionários (Função: " + funcao + " )");
        }
    }

    public void imprimirPorMesesAniversario(int[] meses) {
        for(int mes : meses) {
            ArrayList<Funcionario> funcionariosMes = new ArrayList<>();

            for(Funcionario funcionario : funcionarios) {
                if(funcionario.getDataNascimento().getMonthValue() == mes) {
                    funcionariosMes.add(funcionario);
                }
            }

            imprimirTodos(funcionariosMes, "Lista de Funcionários (Aniversário mês " + mes + " )");
        }
    }

    public void imprimirMaiorIdade() {
        String nomeMaiorIdade = "";
        int maiorIdade = 0;

        int anoAtual = LocalDate.now().getYear();
        int anoNascimento;
        int idade = 0;

        for(Funcionario funcionario : funcionarios) {
            anoNascimento = funcionario.getDataNascimento().getYear();
            idade = anoAtual - anoNascimento;

            if(idade > maiorIdade) {
                nomeMaiorIdade = funcionario.getNome();
                maiorIdade = idade;
            }
        }

        System.out.println("\n\nFuncionário (maior idade):\nNome:" + nomeMaiorIdade +
                            "\nIdade: " + maiorIdade);
    }

    public void imprimirTodosPorOrdemAlfabetica() {
        funcionarios.sort((func1, func2) -> func1.getNome().compareTo(func2.getNome()));
        imprimirTodos(funcionarios, "Lista de funcionarios (por ordem alfabética)");
    }

    public void imprimirTotalSalarios() {
        BigDecimal total = new BigDecimal(0);

        for(Funcionario funcionario : funcionarios) {
            total = total.add(funcionario.getSalario());
        }

        NumberFormat nf = NumberFormat.getInstance(new Locale("pt", "BR"));
        nf.setMaximumFractionDigits(2);

        System.out.println("\n\nTotal de todos os salários: R$ " + nf.format(total));
    }

    public void imprimirTodosPorSalariosMinimos() {
        BigDecimal salarioMinimo = new BigDecimal(1212);
        BigDecimal salariosMinimos = new BigDecimal(0);
        NumberFormat nf = NumberFormat.getInstance(new Locale("pt", "BR"));
        nf.setMaximumFractionDigits(2);

        System.out.println("\n\n");

        for(Funcionario funcionario : funcionarios) {
            salariosMinimos = funcionario.getSalario().divide(salarioMinimo, RoundingMode.HALF_EVEN);
            System.out.println("Nome: " + funcionario.getNome() + 
                                "  ||  Salários Mínimos: " + nf.format(salariosMinimos));
        }
    }
}