package com.example.ehCachecacheprovider.config.cache;

import javax.cache.configuration.Configuration;

import com.example.ehCachecacheprovider.model.Student;

public class StudentCacheConfig implements Configuration<Long, Student> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4975591790126545628L;

	@Override
	public Class<Long> getKeyType() {
		// TODO Auto-generated method stub
		return Long.class;
	}

	@Override
	public Class<Student> getValueType() {
		// TODO Auto-generated method stub
		return Student.class;
	}

	@Override
	public boolean isStoreByValue() {
		// TODO Auto-generated method stub
		return false;
	}

}
