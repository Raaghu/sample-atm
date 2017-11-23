package in.krraghavendra.assignment.atm.vault;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Vault {
	
	private static Vault vaultObj = null;
	
	/**
	 * vault stores the number of currency notes for each allowed denominations
	 */
	private Map<Integer, Integer> vault = new HashMap<Integer, Integer>();
	
	private Vault(int[] allowedDenominations){
		for (Integer allowedDenomination : allowedDenominations) {
			vault.put(allowedDenomination, 0);
		}
	}
	
	public static Vault getVault() {
		if(vaultObj == null){
			synchronized (Vault.class) {
				if(vaultObj == null){
					vaultObj = new Vault(new int[]{50,20,10});
				}
			}
		}
		return vaultObj;
	}
	
	/**
	 * adds a curreny note to the vault , one note for each invokation
	 * @param value of the currenly note
	 * @return Vault returns this object for method chaining
	 * @throws InvalidDenominationException if supplied denomination is not allowed
	 */
	public Vault add(Integer value) throws InvalidDenominationException {
		if(vault.containsKey(value)){
			vault.put(value, vault.get(value)+1);
		}else{
			throw new InvalidDenominationException();
		}
		return this;
	}
	
	/**
	 * removes the currency notes from the vault matching the total value of supplied amount
	 * @param totalAmount total Amount to be removed from the vault
	 * @return List<Integer> list of each notes removed from the vault
	 * @throws InsufficientFundsException
	 */
	public List<Integer> remove(Integer totalAmount) throws InsufficientFundsException {
		List<Integer> denominations = new ArrayList<Integer>(vault.keySet());
		denominations.sort(new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				return o2-o1; // sort descending
			}
		});
		
		Map<Integer,Integer> _vault = new HashMap<>(vault);
		
		int indexOfHighestDenomination = 0;
		
		List<Integer> removedCurrency = new ArrayList<Integer>();
		
		while(totalAmount > 0 && indexOfHighestDenomination < denominations.size()){
			Integer currentDenomination = denominations.get(indexOfHighestDenomination);
			if(totalAmount < currentDenomination){
				indexOfHighestDenomination++;
				continue;
			}
			if(_vault.get(currentDenomination) == 0){
				indexOfHighestDenomination++;
				continue;
			}
			
			_vault.put(currentDenomination, _vault.get(currentDenomination) - 1);
			removedCurrency.add(currentDenomination);
			totalAmount = totalAmount - currentDenomination;
		}
		
		if(totalAmount == 0 && indexOfHighestDenomination < denominations.size()){
			vault = _vault;
		}else{
			throw new InsufficientFundsException();
		}
		
		return removedCurrency;
	}
	
	

}
