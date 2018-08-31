package com.mytest.billapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BEntity implements Comparable<BEntity> {

	@Override
	public int compareTo(BEntity o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
