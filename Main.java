import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.math.BigDecimal;

public class Main {
  public static void main(String[] args) {
    ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();

    // leitura do arquivo csv com as informações dos funcionários
    try {
      BufferedReader br = new BufferedReader(new FileReader("funcionarios-info.csv"));
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
      String linha;
      String[] informacao;
      String nome;
      LocalDate dataNascimento;
      BigDecimal salario;
      String funcao;

      while ((linha = br.readLine()) != null) {
        informacao = linha.split(",");
        nome = informacao[0];
        dataNascimento = LocalDate.parse(informacao[1], formatter);
        salario = new BigDecimal(informacao[2]);
        funcao = informacao[3];

        Funcionario funcionario = new Funcionario(nome, dataNascimento, salario, funcao);
        funcionarios.add(funcionario);
      }

      br.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    // resolução dos requisitos
    FuncionarioServices funcionarioServices = new FuncionarioServices(funcionarios);
    funcionarioServices.remover("João");
    funcionarioServices.imprimirTodos();
    funcionarioServices.aumentarSalarios(10.0);
    funcionarioServices.imprimirTodos();
    funcionarioServices.agruparPorFuncao();
    funcionarioServices.imprimirTodosPorFuncao();
    int[] meses = {10, 12};
    funcionarioServices.imprimirPorMesesAniversario(meses);
    funcionarioServices.imprimirMaiorIdade();
    funcionarioServices.imprimirTodosPorOrdemAlfabetica();
    funcionarioServices.imprimirTotalSalarios();
    funcionarioServices.imprimirTodosPorSalariosMinimos();
  }
}