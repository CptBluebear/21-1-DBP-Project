package org.corodiak.library.service;

import java.util.ArrayList;

import org.corodiak.library.mapper.DigitalMapper;
import org.corodiak.library.model.Digital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DigitalService {

	@Autowired
	DigitalMapper digitalMapper;
	
	public ArrayList<Digital> searchDigital(Digital digital) throws Exception
	{
		return digitalMapper.selectDigitalList(digital);
	}
}
