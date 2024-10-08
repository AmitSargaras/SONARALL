package com.integrosys.cms.app.leiDateValidation.bus;

import com.integrosys.base.businfra.search.SearchResult;
import com.integrosys.base.businfra.transaction.TransactionException;
import com.integrosys.base.businfra.transaction.TrxParameterException;
import com.integrosys.base.techinfra.ejbsupport.ConcurrentUpdateException;

public interface ILeiDateValidationBusManager {
	
	SearchResult getAllLeiDateValidation()throws LeiDateValidationException,TrxParameterException,TransactionException,ConcurrentUpdateException;
	SearchResult getAllFilteredLeiDateValidation(String code,String name)throws LeiDateValidationException,TrxParameterException,TransactionException,ConcurrentUpdateException;

	ILeiDateValidation createLeiDateValidation(ILeiDateValidation leiDateValidation)throws LeiDateValidationException;
	ILeiDateValidation getLeiDateValidationById(long id) throws LeiDateValidationException,TrxParameterException,TransactionException;
	ILeiDateValidation updateLeiDateValidation(ILeiDateValidation item) throws LeiDateValidationException,TrxParameterException,TransactionException,ConcurrentUpdateException;
	public boolean isPartyIDUnique(String partyID);
	ILeiDateValidation updateToWorkingCopy(ILeiDateValidation workingCopy, ILeiDateValidation imageCopy) throws LeiDateValidationException,TrxParameterException,TransactionException,ConcurrentUpdateException;
	
}
