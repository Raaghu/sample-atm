package in.krraghavendra.assignment.atm.tests.account;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import in.krraghavendra.assignment.atm.account.InvalidTransactionException;
import in.krraghavendra.assignment.atm.account.Transaction;
import junit.framework.TestCase;

@RunWith(Parameterized.class)
public class TestTransaction extends TestCase {
	
	private Map<String,Object> args = null;
	private Map<String,Object> expectedProperties = null;
	private Map<String,Object> expectedException = null;
	
	public TestTransaction(Map<String, Object> args, Map<String, Object> expectedProperties,
			Map<String, Object> expectedException) {
		super();
		this.args = args;
		this.expectedProperties = expectedProperties;
		this.expectedException = expectedException;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Parameters
	public static Collection<Object> parameters() throws InvalidTransactionException {
		List<Object> data = new ArrayList<Object>();
		
		// dataset 1
		Map[] dataSet1 = new Map[3];
		dataSet1[0] = new HashMap<String,Object>();
		dataSet1[0].put("isCredit", true);
		dataSet1[0].put("amount", 100.0);
		dataSet1[0].put("previousTransaction", null);

		dataSet1[1] = new HashMap<String,Object>();
		dataSet1[1].put("isCredit", true);
		dataSet1[1].put("amount", 100.0);
		dataSet1[1].put("previousTransaction", null);
		dataSet1[1].put("balance", 100.0);
		
		dataSet1[2] = new HashMap<String,Object>();
		dataSet1[2].put("type", null);
		dataSet1[2].put("message", null);
		
		data.add(dataSet1);
		
		// dataset 2
		Map[] dataSet2 = new Map[3];
		dataSet2[0] = new HashMap<String,Object>();
		dataSet2[0].put("isCredit", false);
		dataSet2[0].put("amount", 100.0);
		dataSet2[0].put("previousTransaction", null);
		
		dataSet2[1] = new HashMap<String,Object>();
		
		dataSet2[2] = new HashMap<String,Object>();
		dataSet2[2].put("type", InvalidTransactionException.class.getName());
		dataSet2[2].put("message", "Insufficient funds to debit");

		data.add(dataSet2);
		
		// dataset 3
		Transaction previousTransaction = new Transaction(true, 1000.0, null);
		Map[] dataSet3 = new Map[3];
		dataSet3[0] = new HashMap<String,Object>();
		dataSet3[0].put("isCredit", true);
		dataSet3[0].put("amount", 100.0);
		dataSet3[0].put("previousTransaction", previousTransaction);
		
		dataSet3[1] = new HashMap<String,Object>();
		dataSet3[1].put("isCredit", true);
		dataSet3[1].put("amount", 100.0);
		dataSet3[1].put("previousTransaction", previousTransaction);
		dataSet3[1].put("balance", 1100.0);
		
		dataSet3[2] = new HashMap<String,Object>();
		
		data.add(dataSet3);
		
		// dataset 4
		Map[] dataSet4 = new Map[3];
		dataSet4[0] = new HashMap<String,Object>();
		dataSet4[0].put("isCredit", false);
		dataSet4[0].put("amount", 100.0);
		dataSet4[0].put("previousTransaction", previousTransaction);
		
		dataSet4[1] = new HashMap<String,Object>();
		dataSet4[1].put("isCredit", false);
		dataSet4[1].put("amount", 100.0);
		dataSet4[1].put("previousTransaction", previousTransaction);
		dataSet4[1].put("balance", 900.0);
		
		dataSet4[2] = new HashMap<String,Object>();
		data.add(dataSet4);

		// dataset 5
		Map[] dataSet5 = new Map[3];
		dataSet5[0] = new HashMap<String,Object>();
		dataSet5[0].put("isCredit", false);
		dataSet5[0].put("amount", 1100.0);
		dataSet5[0].put("previousTransaction", previousTransaction);
		
		dataSet5[1] = new HashMap<String,Object>();
		
		dataSet5[2] = new HashMap<String,Object>();
		dataSet5[2].put("type", InvalidTransactionException.class.getName());
		dataSet5[2].put("message", "Insufficient funds to debit");
		data.add(dataSet5);
		
		return data;
	}
	
	
	
	@Test
	public void testConstructor(){
		try{
			Transaction transaction = new Transaction((Boolean)args.get("isCredit"), (Double)args.get("amount"), (Transaction)args.get("previousTransaction"));
			Date now = new Date();
			assertTrue(transaction.getDate().before(now) || transaction.getDate().equals(now));
			assertEquals(expectedProperties.get("isCredit"), transaction.isCredit());
			assertEquals(expectedProperties.get("amount"), transaction.getAmount());
			assertEquals(expectedProperties.get("previousTransaction"), transaction.getPreviousTransaction());
			assertEquals(expectedProperties.get("balance"), transaction.getBalance());
			
		}catch (Exception e) {
			assertEquals(e.getClass().getName(), expectedException.get("type"));
			assertEquals(e.getMessage(), expectedException.get("message"));
		}
	}

}
