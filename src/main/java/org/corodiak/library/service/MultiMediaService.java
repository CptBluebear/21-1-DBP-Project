package org.corodiak.library.service;

import java.util.ArrayList;

import org.corodiak.library.mapper.MultiMediaMapper;
import org.corodiak.library.model.MultiMedia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MultiMediaService {

	@Autowired
	MultiMediaMapper multiMediaMapper;
	
	public ArrayList<MultiMedia> searchMultiMedia(MultiMedia multiMedia) throws Exception
	{
		return multiMediaMapper.selectMultiMediaList(multiMedia);
	}
}
