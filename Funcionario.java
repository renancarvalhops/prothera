import java.math.BigDecimal;
import java.time.LocalDate;
import java.text.NumberFormat;
import java.util.Locale;

public class Funcionario extends Pessoa {
  private BigDecimal salario;
  private String funcao;

  public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
    super(nome, dataNascimento);
    this.salario = salario;
    this.funcao = funcao;
  }

  public BigDecimal getSalario() {
    return salario;
  }

  public String getSalarioString() {
    NumberFormat nf = NumberFormat.getInstance(new Locale("pt", "BR"));
    nf.setMaximumFractionDigits(2);
    
    return nf.format(salario);
  }

  public void setSalario(BigDecimal salario) {
    this.salario = salario;
  }

  public String getFuncao() {
    return funcao;
  }

  public void setFuncao(String funcao) {
    this.funcao = funcao;
  }

  public String toString() {
    return  "Nome: " + this.getNome() + "  ||  " +
            "Data de nascimento: " + this.getDataNascimentoString() + "  ||  " +
            "Salário: R$ " + this.getSalarioString() + "  ||  " +
            "Função: " + this.funcao;
  }
}