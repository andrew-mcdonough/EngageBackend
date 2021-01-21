package engagebackend;


import org.junit.Test;
import com.engage.mapping.ExpenseMapper;
import com.engage.repository.model.ExpenseModel;
import com.engage.spring.pojo.Expense;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.equalTo;

public class MapperTest {

   private java.sql.Date expenseDate=new java.sql.Date(System.currentTimeMillis());

   @Test
   public void mapperTestDBtoPojo() {
	   ExpenseMapper  expenseMapper = new ExpenseMapper();
	   ExpenseModel expenseDB = new ExpenseModel();
	   expenseDB.setAmount(5.5f);
	   expenseDB.setExpenseDate(expenseDate );
	   expenseDB.setReason("reason");
	   expenseDB.setVat(5.5f);
	   
	   Expense expense = expenseMapper.mapExpenseDB(expenseDB );
	   assertThat(expense.getAmount(), notNullValue());
	   assertThat(expense.getAmount(), equalTo(5.5f));
	   assertThat(expense.getDate(), notNullValue());
	   assertThat(expense.getReason(), notNullValue());
	   assertThat(expense.getReason(), equalTo("reason"));
	   assertThat(expense.getVat(), notNullValue());
	   assertThat(expense.getVat(), equalTo(5.5f));
   }
   
   @Test
   public void mapperTestPojotoDB() {
	   ExpenseMapper  expenseMapper = new ExpenseMapper();
	   Expense expense = new Expense();
	   expense.setAmount(5.5f);
	   expense.setDate("26/04/1963");
	   expense.setReason("reason");
	   expense.setVat(5.5f);
	   ExpenseModel expenseDB = expenseMapper.mapExpensePojo(expense);
	   assertThat(expenseDB.getAmount(), notNullValue());
	   assertThat(expenseDB.getAmount(), equalTo(5.5f));
	   assertThat(expenseDB.getReason(), notNullValue());
	   assertThat(expenseDB.getReason(), equalTo("reason"));
	   assertThat(expenseDB.getVat(), notNullValue());
	   assertThat(expenseDB.getVat(), equalTo(5.5f));
   }
}